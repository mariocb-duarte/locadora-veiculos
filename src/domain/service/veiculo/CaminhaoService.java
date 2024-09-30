package domain.service.veiculo;

import domain.model.veiculo.Caminhao;
import domain.model.veiculo.Moto;
import domain.repository.veiculo.CaminhaoRepository;

import java.util.List;

public class CaminhaoService {

    private CaminhaoRepository caminhaoRepository;

    public CaminhaoService() {
        this.caminhaoRepository = new CaminhaoRepository();
    }

    public void saveCaminhao(String fabricante, String modelo, String placa, int anoFabricacao, int anoModelo, String cor, boolean disponivel, String localizacao) {
        Caminhao caminhao = new Caminhao(0, fabricante, modelo, placa, anoFabricacao, anoModelo, cor, disponivel, localizacao);
        caminhaoRepository.save(caminhao);
    }

    public void updateCaminhao(int id, String fabricante, String modelo, String placa, int anoFabricacao, int anoModelo, String cor, boolean disponivel, String localizacao) {
        Caminhao caminhao = new Caminhao(id, fabricante, modelo, placa, anoFabricacao, anoModelo, cor, disponivel, localizacao);
        caminhaoRepository.update(id, caminhao);
    }

    public List<Caminhao> buscarCaminhoesPorNome(String parteNome) {
        return caminhaoRepository.findByNameContains(parteNome);
    }
    public List<Caminhao> findCaminhoes() {
        return caminhaoRepository.findAll();
    }

    public void deleteCamihoes() {
        caminhaoRepository.deleteAll();
    }
}