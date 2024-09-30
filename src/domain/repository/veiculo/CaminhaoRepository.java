package domain.repository.veiculo;

import domain.exception.veiculo.AlreadyExistsVeiculoException;
import domain.model.veiculo.Caminhao;
import domain.model.veiculo.Moto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CaminhaoRepository implements ICaminhaoRepository{

    private static final String FILE_PATH = "caminhoes.csv";

    @Override
    public void save(Caminhao caminhao) {
        if (caminhaoExists(caminhao)) {
            throw new AlreadyExistsVeiculoException("Veículo already exists");
        }
        int nextId = getNextId();
        caminhao.setId(nextId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(caminhao.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Caminhao updatedcaminhao) {
        List<Caminhao> caminhoes = findAll();
        boolean updated = false;

        for (Caminhao caminhao : caminhoes) {
            if (caminhao.getId() == id) {

                caminhao.setFabricante(updatedcaminhao.getFabricante());
                caminhao.setModelo(updatedcaminhao.getModelo());
                caminhao.setPlaca(updatedcaminhao.getPlaca());
                caminhao.setAnoFabricacao(updatedcaminhao.getAnoFabricacao());
                caminhao.setAnoModelo(updatedcaminhao.getAnoModelo());
                caminhao.setCor(updatedcaminhao.getCor());
                caminhao.setDisponivel(updatedcaminhao.isDisponivel());
                caminhao.setLocalizacao(updatedcaminhao.getLocalizacao());
                if (caminhaoExists(caminhao)) {
                    throw new AlreadyExistsVeiculoException("Veículo already exists");
                }else {
                    updated = true;
                    break;
                }
            }
        }
        //int id, String fabricante, String modelo, String placa, int anoFabricacao, int anoModelo, String cor, boolean disponivel, String localizacao

        if (updated) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Caminhao caminhao : caminhoes) {
                    writer.write(caminhao.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Caminhão com ID " + id + " não encontrado.");
        }
    }

    @Override
    public List<Caminhao> findByNameContains(String substring) {
        List<Caminhao> caminhoes = findAll();
        List<Caminhao> result = new ArrayList<>();

        for (Caminhao caminhao : caminhoes) {
            if (caminhao.getModelo().toLowerCase().contains(substring.toLowerCase())) {
                result.add(caminhao);
            }
        }
        return result;
    }

    @Override
    public List<Caminhao> findAll() {
        List<Caminhao> caminhoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                caminhoes.add(Caminhao.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return caminhoes;
    }

    @Override
    public int getNextId() {
        List<Caminhao> caminhoes = findAll();
        if (caminhoes.isEmpty()) {
            return 1;
        }
        int maxId = caminhoes.stream()
                .mapToInt(Caminhao::getId)
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

    private boolean caminhaoExists(Caminhao caminhao) {
        List<Caminhao> caminhoes = findAll();
        return caminhoes.stream().anyMatch(u ->
                u.getPlaca().equalsIgnoreCase(caminhao.getPlaca())
        );
    }
}
