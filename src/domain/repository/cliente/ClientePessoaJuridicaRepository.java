package domain.repository.cliente;

import domain.model.cliente.ClientePessoaJuridica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ClientePessoaJuridicaRepository implements IClientePessoaJuridicaRepository {

    private static final String FILE_PATH = "clientes-pessoa-juridica.csv";

    @Override
    public void save(ClientePessoaJuridica clientePessoaJuridica) {
        int nextId = getNextId();
        clientePessoaJuridica.setId(nextId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(clientePessoaJuridica.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClientePessoaJuridica> findAll() {
        List<ClientePessoaJuridica> clientesPessoaJuridica = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line; while ((line = reader.readLine()) != null) {
                clientesPessoaJuridica.add(ClientePessoaJuridica.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientesPessoaJuridica;
    }

    @Override
    public int getNextId() {
        List<ClientePessoaJuridica> clientesPessoaJuridica = findAll();
        if (clientesPessoaJuridica.isEmpty()) {
            return 1;
        }

        return clientesPessoaJuridica.stream()
                .mapToInt(ClientePessoaJuridica::getId)
                .max()
                .orElse(0);
    }

    @Override
    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

