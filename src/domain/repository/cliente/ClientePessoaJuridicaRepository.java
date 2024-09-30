package domain.repository.cliente;

import domain.exception.cliente.AlreadyExistsClienteException;
import domain.model.cliente.ClientePessoaJuridica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ClientePessoaJuridicaRepository implements IClientePessoaJuridicaRepository {

    private static final String FILE_PATH = "clientes-pessoa-juridica.csv";

    @Override
    public void save(ClientePessoaJuridica clientePessoaJuridica) {
        if (clienteExists(clientePessoaJuridica)) {
            throw new AlreadyExistsClienteException("Cliente already exists");
        }
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
    public void update(int id, ClientePessoaJuridica updatedClientePessoaJuridica) {
        List<ClientePessoaJuridica> clientesPessoaJuridica = findAll();
        boolean updated = false;

        for (ClientePessoaJuridica clientePessoaJuridica : clientesPessoaJuridica) {
            if (clientePessoaJuridica.getId() == id) {

                clientePessoaJuridica.setNome(updatedClientePessoaJuridica.getNome());
                clientePessoaJuridica.setEmail(updatedClientePessoaJuridica.getEmail());
                clientePessoaJuridica.setTelefone(updatedClientePessoaJuridica.getTelefone());
                clientePessoaJuridica.setCnpj(updatedClientePessoaJuridica.getCnpj());
                if (clienteExists(clientePessoaJuridica)) {
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
                for (ClientePessoaJuridica clientePessoaJuridica : clientesPessoaJuridica) {
                    writer.write(clientePessoaJuridica.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Cliente pessoa jurídca com ID " + id + " não encontrado.");
        }
    }

    @Override
    public List<ClientePessoaJuridica> findByNameContains(String substring) {
        List<ClientePessoaJuridica> clientesPessoaJuridica = findAll();
        List<ClientePessoaJuridica> result = new ArrayList<>();

        for (ClientePessoaJuridica clientePessoaJuridica : clientesPessoaJuridica) {
            if (clientePessoaJuridica.getNome().toLowerCase().contains(substring.toLowerCase())) {
                result.add(clientePessoaJuridica);
            }
        }
        return result;
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

    private boolean clienteExists(ClientePessoaJuridica clientePessoaJuridica) {
        List<ClientePessoaJuridica> clientes = findAll();
        return clientes.stream().anyMatch(u ->
                u.getCnpj().equalsIgnoreCase(clientePessoaJuridica.getCnpj()) ||
                u.getTelefone().equalsIgnoreCase(clientePessoaJuridica.getTelefone()) ||
                u.getEmail().equalsIgnoreCase(clientePessoaJuridica.getEmail())
        );
    }
}

