package domain.service.veiculo;

import domain.model.agencia.Agencia;
import domain.model.veiculo.Moto;
import domain.repository.veiculo.MotoRepository;

import java.util.List;

public class MotoService {

    private MotoRepository motoRepository;

    public MotoService() {
        this.motoRepository = new MotoRepository();
    }

    public void saveMoto(String fabricante, String modelo, String placa, int anoFabricacao, int anoModelo, String cor, boolean disponivel, String localizacao) {
        Moto moto = new Moto(0, fabricante, modelo, placa, anoFabricacao, anoModelo, cor, disponivel, localizacao);
        motoRepository.save(moto);
    }

    public void updateMoto(int id, String fabricante, String modelo, String placa, int anoFabricacao, int anoModelo, String cor, boolean disponivel, String localizacao) {
        Moto moto = new Moto(id, fabricante, modelo, placa, anoFabricacao, anoModelo, cor, disponivel, localizacao);
        motoRepository.update(id, moto);
    }

    public List<Moto> buscarMotosPorNome(String parteNome) {
        return motoRepository.findByNameContains(parteNome);
    }


    public List<Moto> findMotos() {
        return motoRepository.findAll();
    }

    public void deleteMotos() {
        motoRepository.deleteAll();
    }
}
