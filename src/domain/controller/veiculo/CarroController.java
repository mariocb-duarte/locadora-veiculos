package domain.controller.veiculo;

import domain.model.veiculo.Carro;
import domain.service.veiculo.CarroService;

import java.util.List;

public class CarroController {

    private CarroService carroService;

    public CarroController() {
        this.carroService = new CarroService();
    }

    public void saveCarro(String fabricante, String modelo, String placa, int anoFabricacao, int anoModelo, String cor, boolean disponivel, String localizacao) {
        carroService.saveCarro(fabricante, modelo, placa, anoFabricacao, anoModelo, cor, disponivel, localizacao);
        System.out.println("Carro cadastrado com sucesso!");
    }

    public void findAllCarros() {
        List<Carro> carros = carroService.findCarros();
        if (carros.isEmpty()) {
            System.out.println("Nenhuma carro cadastrado.");
        } else {
            carros.forEach(System.out::println);
        }
    }

    public void deleteCarros() {
        carroService.deleteCarros();
        System.out.println("Todos os carros foram deletados.");
    }

    public void atualizarCarro(int id, String fabricante, String modelo, String placa, int anoFabricacao, int anoModelo, String cor, boolean disponivel, String localizacao) {
        carroService.updateCarro(id, fabricante, modelo, placa, anoFabricacao, anoModelo, cor, disponivel, localizacao);
        System.out.println("Carro atualizada com sucesso!");
    }

    public void buscarCarrosPorNome(String parteNome) {
        List<Carro> carro = carroService.buscarCarrosPorNome(parteNome);
        if (carro.isEmpty()) {
            System.out.println("Nenhuma carroencontrada com o modelo: " + parteNome);
        } else {
            carro.forEach(System.out::println);
        }
    }
}
