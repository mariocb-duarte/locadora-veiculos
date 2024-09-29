package domain.repository.veiculo;

import domain.model.veiculo.Carro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarroRepository implements ICarroRepository{

    private static final String FILE_PATH = "carros.csv";

    @Override
    public void save(Carro carro) {
        int nextId = getNextId();
        carro.setId(nextId);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(carro.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Carro> findAll() {
        List<Carro> motos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                motos.add(Carro.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return motos;
    }

    @Override
    public int getNextId() {
        List<Carro> motos = findAll();
        if (motos.isEmpty()) {
            return 1;
        }
        int maxId = motos.stream()
                .mapToInt(Carro::getId)
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
