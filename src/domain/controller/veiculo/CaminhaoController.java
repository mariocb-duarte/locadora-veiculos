package domain.controller.veiculo;

import domain.model.veiculo.Caminhao;
import domain.model.veiculo.Moto;
import domain.service.veiculo.CaminhaoService;

import java.util.List;

public class CaminhaoController {


    private CaminhaoService caminhaoService;

    public CaminhaoController() {
        this.caminhaoService = new CaminhaoService();
    }

    public void saveCaminhao(String fabricante, String modelo, String placa, int anoFabricacao, int anoModelo, String cor, boolean disponivel, String localizacao) {
        caminhaoService.saveCaminhao(fabricante, modelo, placa, anoFabricacao, anoModelo, cor, disponivel, localizacao);
        System.out.println("Caminhao cadastrado com sucesso!");
    }

    public void findAllCaminhoes() {
        List<Caminhao> caminhoes = caminhaoService.findCaminhoes();
        if (caminhoes.isEmpty()) {
            System.out.println("Nenhuma caminhão cadastrado.");
        } else {
            caminhoes.forEach(System.out::println);
        }
    }

    public void deleteCaminhoes() {
        caminhaoService.deleteCamihoes();
        System.out.println("Todos os caminhoes foram deletadas.");
    }

    public void atualizarCaminhao(int id, String fabricante, String modelo, String placa, int anoFabricacao, int anoModelo, String cor, boolean disponivel, String localizacao) {
        caminhaoService.updateCaminhao(id, fabricante, modelo, placa, anoFabricacao, anoModelo, cor, disponivel, localizacao);
        System.out.println("Caminhao atualizada com sucesso!");
    }

    public void buscarCaminhoesPorNome(String parteNome) {
        List<Caminhao> caminhoes = caminhaoService.buscarCaminhoesPorNome(parteNome);
        if (caminhoes.isEmpty()) {
            System.out.println("Nenhuma caminhao encontrada com o modelo: " + parteNome);
        } else {
            caminhoes.forEach(System.out::println);
        }
    }
}
