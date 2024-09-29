package domain.repository.veiculo;

import domain.model.veiculo.Moto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MotoRepository implements IMotoRepository {

    private static final String FILE_PATH = "motos.csv";


    @Override
    public void save(Moto moto) {
        int nextId = getNextId();
        moto.setId(nextId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(moto.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Moto> findAll() {
        List<Moto> motos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                motos.add(Moto.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return motos;
    }

    @Override
    public int getNextId() {
        List<Moto> motos = findAll();
        if (motos.isEmpty()) {
            return 1;
        }
        int maxId = motos.stream()
                .mapToInt(Moto::getId)
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
