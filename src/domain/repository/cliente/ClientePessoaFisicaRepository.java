package domain.repository.cliente;

import domain.exception.cliente.AlreadyExistsClienteException;
import domain.model.cliente.ClientePessoaFisica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientePessoaFisicaRepository implements IClientePessoaFisicaRepository{

    private static final String FILE_PATH = "clientes-pessoa-fisica.csv";

    @Override
    public void save(ClientePessoaFisica clientePessoaFisica) {
        if (clienteExists(clientePessoaFisica)) {
            throw new AlreadyExistsClienteException("Agencia already exists");
        }
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
    public void update(int id, ClientePessoaFisica updatedClientePessoaFisica) {
        List<ClientePessoaFisica> clientesPessoaFisica = findAll();
        boolean updated = false;

        for (ClientePessoaFisica clientePessoaFisica : clientesPessoaFisica) {
            if (clientePessoaFisica.getId() == id) {

                clientePessoaFisica.setNome(updatedClientePessoaFisica.getNome());
                clientePessoaFisica.setEmail(updatedClientePessoaFisica.getEmail());
                clientePessoaFisica.setTelefone(updatedClientePessoaFisica.getTelefone());
                clientePessoaFisica.setCpf(updatedClientePessoaFisica.getCpf());
                if (clienteExists(clientePessoaFisica)) {
                    throw new AlreadyExistsClienteException("Cliente already exists");
                }else {
                    updated = true;
                    break;
                }
            }
        }
        //int id, String nome, String email, String telefone, String cpf

        if (updated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (ClientePessoaFisica clientePessoaFisica : clientesPessoaFisica) {
                    writer.write(clientePessoaFisica.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Cliente pessoa física com ID " + id + " não encontrado.");
        }
    }

    @Override
    public List<ClientePessoaFisica> findByNameContains(String substring) {
        List<ClientePessoaFisica> clientesPessoaFisica = findAll();
        List<ClientePessoaFisica> result = new ArrayList<>();

        for (ClientePessoaFisica clientePessoaFisica : clientesPessoaFisica) {
            if (clientePessoaFisica.getNome().toLowerCase().contains(substring.toLowerCase())) {
                result.add(clientePessoaFisica);
            }
        }
        return result;
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

    private boolean clienteExists(ClientePessoaFisica clientePessoaFisica) {
        List<ClientePessoaFisica> clientes = findAll();
        return clientes.stream().anyMatch(u ->
                u.getCpf().equalsIgnoreCase(clientePessoaFisica.getCpf()) ||
                u.getTelefone().equalsIgnoreCase(clientePessoaFisica.getTelefone()) ||
                u.getEmail().equalsIgnoreCase(clientePessoaFisica.getEmail())
        );
    }
}
