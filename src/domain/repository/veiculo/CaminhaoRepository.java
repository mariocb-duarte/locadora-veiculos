package domain.repository.veiculo;

import domain.model.veiculo.Caminhao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CaminhaoRepository implements ICaminhaoRepository{

    private static final String FILE_PATH = "caminhoes.csv";

    @Override
    public void save(Caminhao caminhao) {
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
}
