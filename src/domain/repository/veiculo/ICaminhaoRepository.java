package domain.repository.veiculo;

import domain.model.veiculo.Caminhao;

import java.util.List;

public interface ICaminhaoRepository {
    void save(Caminhao caminhao);
    List<Caminhao> findAll();
    int getNextId();
    void deleteAll();
}
