package domain.repository.veiculo;

import domain.model.veiculo.Caminhao;

import java.util.List;

public interface ICaminhaoRepository {
    void save(Caminhao caminhao);
    void update(int id, Caminhao caminhao);
    List<Caminhao> findByNameContains(String substring);
    List<Caminhao> findAll();
    int getNextId();
    void deleteAll();
}
