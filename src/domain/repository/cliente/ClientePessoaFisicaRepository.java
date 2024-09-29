package domain.repository.cliente;

import domain.model.cliente.ClientePessoaFisica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientePessoaFisicaRepository implements IClientePessoaFisicaRepository{

    private static final String FILE_PATH = "clientes-pessoa-fisica.csv";

    @Override
    public void save(ClientePessoaFisica clientePessoaFisica) {
        int nextId = getNextId();
        clientePessoaFisica.setId(nextId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(clientePessoaFisica.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClientePessoaFisica> findAll() {
        List<ClientePessoaFisica> clientesPessoaFisica = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                clientesPessoaFisica.add(ClientePessoaFisica.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clientesPessoaFisica;
    }

    @Override
    public int getNextId() {
        List<ClientePessoaFisica> clientesPessoaFisica = findAll();
        if (clientesPessoaFisica.isEmpty()) {
            return 1;
        }
        int maxId = clientesPessoaFisica.stream()
                .mapToInt(ClientePessoaFisica::getId)
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
}
