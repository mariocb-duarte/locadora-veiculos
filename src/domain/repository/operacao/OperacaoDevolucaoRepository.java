package domain.repository.operacao;

import domain.exception.operacao.AlreadyExistsOperacaoException;
import domain.model.operacao.OperacaoDevolucao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OperacaoDevolucaoRepository implements IOperacaoDevolucaoRepository{
    private static final String FILE_PATH = "devolucoes.csv";


    @Override
    public void save(OperacaoDevolucao operacao) {
        if (operacaoExists(operacao)) {
            throw new AlreadyExistsOperacaoException("Devolução already exists");
        }
        int nextId = getNextId();
        operacao.setId(nextId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(operacao.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, OperacaoDevolucao updatedOperacao) {
        List<OperacaoDevolucao> operacaos = findAll();
        boolean updated = false;

        for (OperacaoDevolucao operacao : operacaos) {
            if (operacao.getId() == id) {

                operacao.setDataHoraOperacao(updatedOperacao.getDataHoraOperacao());
                operacao.setEmailCliente(updatedOperacao.getEmailCliente());
                operacao.setCnpjAgencia(updatedOperacao.getCnpjAgencia());
                operacao.setPlacaVeiculo(updatedOperacao.getPlacaVeiculo());
                operacao.setCusto(updatedOperacao.getCusto());
                if (operacaoExists(operacao)) {
                    throw new AlreadyExistsOperacaoException("Devolução already exists");
                }else {
                    updated = true;
                    break;
                }
            }
        }
        //int id, LocalDateTime dataHoraOperacaoLocacao, String tipoOperacaoLocacao, String emailCliente, String cnpjAgencia, String placaVeiculo

        if (updated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (OperacaoDevolucao operacao : operacaos) {
                    writer.write(operacao.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Devolução com ID " + id + " não encontrada.");
        }
    }

    @Override
    public List<OperacaoDevolucao> findByNameContains(String substring) {
        List<OperacaoDevolucao> operacaos = findAll();
        List<OperacaoDevolucao> result = new ArrayList<>();

        for (OperacaoDevolucao operacao : operacaos) {
            if (operacao.getPlacaVeiculo().toLowerCase().contains(substring.toLowerCase())) {
                result.add(operacao);
            }
        }
        return result;
    }

    @Override
    public List<OperacaoDevolucao> findAll() {
        List<OperacaoDevolucao> operacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                operacoes.add(OperacaoDevolucao.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return operacoes;
    }


    @Override
    public int getNextId() {
        List<OperacaoDevolucao> operacoes = findAll();
        if (operacoes.isEmpty()) {
            return 1;
        }
        int maxId = operacoes.stream()
                .mapToInt(OperacaoDevolucao::getId)
                .max()
                .orElse(0);
        return maxId + 1;
    }

    @Override
    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean operacaoExists(OperacaoDevolucao operacao) {
        List<OperacaoDevolucao> operacoes = findAll();
        return operacoes.stream().anyMatch(u ->
                        u.getDataHoraOperacao() == (operacao.getDataHoraOperacao()) &&
                        u.getPlacaVeiculo().equalsIgnoreCase(operacao.getPlacaVeiculo())
        );
    }
}