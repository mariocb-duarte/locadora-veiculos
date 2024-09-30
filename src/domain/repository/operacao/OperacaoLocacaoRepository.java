package domain.repository.operacao;

import domain.exception.operacao.AlreadyExistsOperacaoException;
import domain.model.operacao.OperacaoLocacao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OperacaoLocacaoRepository implements IOperacaoLocacaoRepository{

    private static final String FILE_PATH = "locacoes.csv";


    @Override
    public void save(OperacaoLocacao operacao) {
        if (operacaoExists(operacao)) {
            throw new AlreadyExistsOperacaoException("Locação already exists");
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
    public void update(int id, OperacaoLocacao updatedOperacao) {
        List<OperacaoLocacao> operacaos = findAll();
        boolean updated = false;

        for (OperacaoLocacao operacao : operacaos) {
            if (operacao.getId() == id) {

                operacao.setDataHoraOperacao(updatedOperacao.getDataHoraOperacao());
                operacao.setEmailCliente(updatedOperacao.getEmailCliente());
                operacao.setCnpjAgencia(updatedOperacao.getCnpjAgencia());
                operacao.setPlacaVeiculo(updatedOperacao.getPlacaVeiculo());
                if (operacaoExists(operacao)) {
                    throw new AlreadyExistsOperacaoException("Locação already exists");
                }else {
                    updated = true;
                    break;
                }
            }
        }
        //int id, LocalDateTime dataHoraOperacaoLocacao, String tipoOperacaoLocacao, String emailCliente, String cnpjAgencia, String placaVeiculo

        if (updated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (OperacaoLocacao operacao : operacaos) {
                    writer.write(operacao.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Locação com ID " + id + " não encontrada.");
        }
    }

    @Override
    public List<OperacaoLocacao> findByNameContains(String substring) {
        List<OperacaoLocacao> operacaos = findAll();
        List<OperacaoLocacao> result = new ArrayList<>();

        for (OperacaoLocacao operacao : operacaos) {
            if (operacao.getPlacaVeiculo().toLowerCase().contains(substring.toLowerCase())) {
                result.add(operacao);
            }
        }
        return result;
    }

    @Override
    public List<OperacaoLocacao> findAll() {
        List<OperacaoLocacao> operacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                operacoes.add(OperacaoLocacao.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return operacoes;
    }


    @Override
    public int getNextId() {
        List<OperacaoLocacao> operacoes = findAll();
        if (operacoes.isEmpty()) {
            return 1;
        }
        int maxId = operacoes.stream()
                .mapToInt(OperacaoLocacao::getId)
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


    private boolean operacaoExists(OperacaoLocacao operacao) {
        List<OperacaoLocacao> operacoes = findAll();
        return operacoes.stream().anyMatch(u ->
                        u.getDataHoraOperacao() == (operacao.getDataHoraOperacao()) &&
                        u.getPlacaVeiculo().equalsIgnoreCase(operacao.getPlacaVeiculo())
        );
    }
}
