package domain.repository.veiculo;

import domain.model.veiculo.Moto;

import java.util.List;

public interface IMotoRepository {
    void save(Moto moto);
    List<Moto> findAll();
    int getNextId();
    void deleteAll();
}
