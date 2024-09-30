package domain.repository.veiculo;

import domain.exception.agencia.AlreadyExistsAgenciaException;
import domain.exception.veiculo.AlreadyExistsVeiculoException;
import domain.model.agencia.Agencia;
import domain.model.veiculo.Moto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MotoRepository implements IMotoRepository {

    private static final String FILE_PATH = "motos.csv";


    @Override
    public void save(Moto moto) {
        if (motoExists(moto)) {
            throw new AlreadyExistsVeiculoException("Veículo already exists");
        }
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
    public void update(int id, Moto updatedMoto) {
        List<Moto> motos = findAll();
        boolean updated = false;

        for (Moto moto : motos) {
            if (moto.getId() == id) {

                moto.setFabricante(updatedMoto.getFabricante());
                moto.setModelo(updatedMoto.getModelo());
                moto.setPlaca(updatedMoto.getPlaca());
                moto.setAnoFabricacao(updatedMoto.getAnoFabricacao());
                moto.setAnoModelo(updatedMoto.getAnoModelo());
                moto.setCor(updatedMoto.getCor());
                moto.setDisponivel(updatedMoto.isDisponivel());
                moto.setLocalizacao(updatedMoto.getLocalizacao());
                if (motoExists(moto)) {
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
                for (Moto moto : motos) {
                    writer.write(moto.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Moto com ID " + id + " não encontrada.");
        }
    }

    @Override
    public List<Moto> findByNameContains(String substring) {
        List<Moto> motos = findAll();
        List<Moto> result = new ArrayList<>();

        for (Moto moto : motos) {
            if (moto.getModelo().toLowerCase().contains(substring.toLowerCase())) {
                result.add(moto);
            }
        }
        return result;
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

    private boolean motoExists(Moto moto) {
        List<Moto> motos = findAll();
        return motos.stream().anyMatch(u ->
                u.getPlaca().equalsIgnoreCase(moto.getPlaca())
        );
    }
}
