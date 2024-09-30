package domain.repository.operacao;


import domain.exception.operacao.AlreadyExistsOperacaoException;
import domain.model.operacao.Operacao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OperacaoRepository implements IOperacaoRepository {

    private static final String FILE_PATH = "operacoes.csv";

    @Override
    public void save(Operacao operacao) {
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
    public void update(int id, Operacao updatedOperacao) {
        List<Operacao> operacaos = findAll();
        boolean updated = false;

        for (Operacao operacao : operacaos) {
            if (operacao.getId() == id) {

                operacao.setDataHoraOperacao(updatedOperacao.getDataHoraOperacao());
                operacao.setTipoOperacao(updatedOperacao.getTipoOperacao());
                operacao.setEmailCliente(updatedOperacao.getEmailCliente());
                operacao.setCnpjAgencia(updatedOperacao.getCnpjAgencia());
                operacao.setPlacaVeiculo(updatedOperacao.getPlacaVeiculo());
                if (operacaoExists(operacao)) {
                    throw new AlreadyExistsOperacaoException("Operação already exists");
                }else {
                    updated = true;
                    break;
                }
            }
        }
        //int id, LocalDateTime dataHoraOperacao, String tipoOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo

        if (updated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Operacao operacao : operacaos) {
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
    public List<Operacao> findByNameContains(String substring) {
        List<Operacao> operacaos = findAll();
        List<Operacao> result = new ArrayList<>();

        for (Operacao operacao : operacaos) {
            if (operacao.getTipoOperacao().toLowerCase().contains(substring.toLowerCase())) {
                result.add(operacao);
            }
        }
        return result;
    }

    @Override
    public List<Operacao> findAll() {
        return List.of();
    }

    @Override
    public int getNextId() {
        return 0;
    }

    @Override
    public void deleteAll() {

    }

    private boolean operacaoExists(Operacao operacao) {
        List<Operacao> operacoes = findAll();
        return operacoes.stream().anyMatch(u ->
                u.getTipoOperacao().equalsIgnoreCase(operacao.getTipoOperacao()) &&
                u.getDataHoraOperacao() == (operacao.getDataHoraOperacao()) &&
                u.getPlacaVeiculo().equalsIgnoreCase(operacao.getPlacaVeiculo())
        );
    }
}

