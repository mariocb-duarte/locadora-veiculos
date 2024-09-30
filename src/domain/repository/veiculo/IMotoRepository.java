package domain.repository.veiculo;

import domain.model.veiculo.Moto;

import java.util.List;

public interface IMotoRepository {
    void save(Moto moto);
    void update(int id, Moto moto);
    List<Moto> findByNameContains(String substring);
    List<Moto> findAll();
    int getNextId();
    void deleteAll();
}
