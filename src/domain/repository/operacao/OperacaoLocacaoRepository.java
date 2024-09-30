package domain.repository.operacao;


import domain.exception.operacao.AlreadyExistsOperacaoException;
import domain.model.operacao.OperacaoLocacao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OperacaoLocacaoRepository implements IOperacaoLocacaoRepository {

    private static final String FILE_PATH = "locacoes.csv";

    @Override
    public void save(OperacaoLocacao operacao) {
        if (operacaoExists(operacao)) {
            throw new AlreadyExistsOperacaoException("Operação already exists");
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
    public void update(int id, OperacaoLocacao updatedOperacaoLocacao) {
        List<OperacaoLocacao> operacaos = findAll();
        boolean updated = false;

        for (OperacaoLocacao operacao : operacaos) {
            if (operacao.getId() == id) {

                operacao.setDataHoraOperacao(updatedOperacaoLocacao.getDataHoraOperacao());
                operacao.setEmailCliente(updatedOperacaoLocacao.getEmailCliente());
                operacao.setCnpjAgencia(updatedOperacaoLocacao.getCnpjAgencia());
                operacao.setPlacaVeiculo(updatedOperacaoLocacao.getPlacaVeiculo());
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
                for (OperacaoLocacao operacao : operacaos) {
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
        return List.of();
    }

    @Override
    public int getNextId() {
        return 0;
    }

    @Override
    public void deleteAll() {

    }

    private boolean operacaoExists(OperacaoLocacao operacao) {
        List<OperacaoLocacao> operacoes = findAll();
        return operacoes.stream().anyMatch(u ->
                u.getDataHoraOperacao() == (operacao.getDataHoraOperacao()) &&
                u.getPlacaVeiculo().equalsIgnoreCase(operacao.getPlacaVeiculo())
        );
    }
}

