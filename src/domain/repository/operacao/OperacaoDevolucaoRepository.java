package domain.repository.operacao;

import domain.exception.operacao.AlreadyExistsOperacaoException;
import domain.model.operacao.OperacaoDevolucao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OperacaoDevolucaoRepository implements IOperacaoDevolucao {

    private static final String FILE_PATH = "devolucoes.csv";


    @Override
    public void save(OperacaoDevolucao operacaoDevolucao) {
        if (operacaoExists(operacaoDevolucao)) {
            throw new AlreadyExistsOperacaoException("Operação already exists");
        }
        int nextId = getNextId();
        operacaoDevolucao.setId(nextId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(operacaoDevolucao.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, OperacaoDevolucao updatedOperacaoDevolucao) {
        List<OperacaoDevolucao> operacaos = findAll();
        boolean updated = false;

        for (OperacaoDevolucao operacao : operacaos) {
            if (operacao.getId() == id) {

                operacao.setDataHoraOperacao(updatedOperacaoDevolucao.getDataHoraOperacao());
                operacao.setEmailCliente(updatedOperacaoDevolucao.getEmailCliente());
                operacao.setCnpjAgencia(updatedOperacaoDevolucao.getCnpjAgencia());
                operacao.setPlacaVeiculo(updatedOperacaoDevolucao.getPlacaVeiculo());
                operacao.setCusto(updatedOperacaoDevolucao.getCusto());
                if (operacaoExists(operacao)) {
                    throw new AlreadyExistsOperacaoException("Operação already exists");
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
            System.out.println("Operação com ID " + id + " não encontrada.");
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
        return List.of();
    }

    @Override
    public int getNextId() {
        return 0;
    }

    @Override
    public void deleteAll() {

    }

    private boolean operacaoExists(OperacaoDevolucao operacao) {
        List<OperacaoDevolucao> operacoes = findAll();
        return operacoes.stream().anyMatch(u ->
                u.getDataHoraOperacao() == (operacao.getDataHoraOperacao()) &&
                        u.getPlacaVeiculo().equalsIgnoreCase(operacao.getPlacaVeiculo())
        );
    }
}