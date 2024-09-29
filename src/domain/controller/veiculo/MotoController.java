package domain.controller.veiculo;


import domain.model.veiculo.Moto;
import domain.service.veiculo.MotoService;

import java.util.List;

public class MotoController {

    private MotoService motoService;

    public MotoController() {
        this.motoService = new MotoService();
    }

    public void saveMoto(String fabricante, String modelo, String placa, int anoFabricacao, int anoModelo, String cor, boolean disponivel, String localizacao) {
        motoService.saveMoto(fabricante, modelo, placa, anoFabricacao, anoModelo, cor, disponivel, localizacao);
        System.out.println("Moto cadastrada com sucesso!");
    }

    public void findAllMotos() {
        List<Moto> motos = motoService.findMotos();
        if (motos.isEmpty()) {
            System.out.println("Nenhuma moto cadastrada.");
        } else {
            motos.forEach(System.out::println);
        }
    }

    public void deleteMotos() {
        motoService.deleteMotos();
        System.out.println("Todas as motos foram deletadas.");
    }
}
