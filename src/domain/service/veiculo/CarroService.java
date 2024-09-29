package domain.service.veiculo;

import domain.model.veiculo.Carro;
import domain.repository.veiculo.CarroRepository;


import java.util.List;

public class CarroService {

    private CarroRepository carroRepository;

    public CarroService() {
        this.carroRepository = new CarroRepository();
    }

    public void saveCarro(String fabricante, String modelo, String placa, int anoFabricacao, int anoModelo, String cor, boolean disponivel, String localizacao) {
        Carro carro = new Carro(0, fabricante, modelo, placa, anoFabricacao, anoModelo, cor, disponivel, localizacao);
        carroRepository.save(carro);
    }

    public List<Carro> findCarros() {
        return carroRepository.findAll();
    }

    public void deleteCarros() {
        carroRepository.deleteAll();
    }
}
