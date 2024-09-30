package domain.repository.veiculo;

import domain.exception.veiculo.AlreadyExistsVeiculoException;
import domain.model.veiculo.Carro;
import domain.model.veiculo.Moto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarroRepository implements ICarroRepository{

    private static final String FILE_PATH = "carros.csv";

    @Override
    public void save(Carro carro) {
        if (carroExists(carro)) {
            throw new AlreadyExistsVeiculoException("Veículo already exists");
        }
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
    public void update(int id, Carro updatedCarro) {
        List<Carro> carros = findAll();
        boolean updated = false;

        for (Carro carro : carros) {
            if (carro.getId() == id) {

                carro.setFabricante(updatedCarro.getFabricante());
                carro.setModelo(updatedCarro.getModelo());
                carro.setPlaca(updatedCarro.getPlaca());
                carro.setAnoFabricacao(updatedCarro.getAnoFabricacao());
                carro.setAnoModelo(updatedCarro.getAnoModelo());
                carro.setCor(updatedCarro.getCor());
                carro.setDisponivel(updatedCarro.isDisponivel());
                carro.setLocalizacao(updatedCarro.getLocalizacao());
                if (carroExists(carro)) {
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
                for (Carro carro : carros) {
                    writer.write(carro.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Carro com ID " + id + " não encontrado.");
        }
    }

    @Override
    public List<Carro> findByNameContains(String substring) {
        List<Carro> carros = findAll();
        List<Carro> result = new ArrayList<>();

        for (Carro carro : carros) {
            if (carro.getModelo().toLowerCase().contains(substring.toLowerCase())) {
                result.add(carro);
            }
        }
        return result;
    }

    @Override
    public List<Carro> findAll() {
        List<Carro> carros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                carros.add(Carro.fromCSV(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carros;
    }

    @Override
    public int getNextId() {
        List<Carro> carros = findAll();
        if (carros.isEmpty()) {
            return 1;
        }
        int maxId = carros.stream()
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

    private boolean carroExists(Carro carro) {
        List<Carro> carros = findAll();
        return carros.stream().anyMatch(u ->
                u.getPlaca().equalsIgnoreCase(carro.getPlaca())
        );
    }
}
