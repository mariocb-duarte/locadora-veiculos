package domain.repository.agencia;

import domain.model.agencia.Agencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AgenciaRepository implements IAgenciaRepository{

    private static final String FILE_PATH = "agencias.csv";

    @Override
    public void save(Agencia agencia) {
        int nextId = getNextId();
        agencia.setId(nextId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(agencia.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(int id, Agencia updatedAgencia) {
        List<Agencia> agencias = findAll();
        boolean updated = false;

        for (Agencia agencia : agencias) {
            if (agencia.getId() == id) {

                agencia.setNomeFantasia(updatedAgencia.getNomeFantasia());
                agencia.setCnpj(updatedAgencia.getCnpj());
                agencia.setTelefone(updatedAgencia.getTelefone());
                agencia.setEmail(updatedAgencia.getEmail());
                agencia.setRazaoSocial(updatedAgencia.getRazaoSocial());
                updated = true;
                break;
            }
        }

        if (updated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Agencia agencia : agencias) {
                    writer.write(agencia.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Agência com ID " + id + " não encontrada.");
        }
    }

    @Override
    public List<Agencia> findByNameContains(String substring) {
        List<Agencia> agencias = findAll();
        List<Agencia> result = new ArrayList<>();

        for (Agencia agencia : agencias) {
            if (agencia.getNomeFantasia().toLowerCase().contains(substring.toLowerCase())) {
                result.add(agencia);
            }
        }
        return result;
    }

    @Override
    public List<Agencia> findAll() {
        List<Agencia> agencias = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                agencias.add(Agencia.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agencias;
    }

    @Override
    public int getNextId() {
        List<Agencia> agencias = findAll();
        if (agencias.isEmpty()) {
            return 1;
        }
        int maxId = agencias.stream()
                .mapToInt(Agencia::getId)
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