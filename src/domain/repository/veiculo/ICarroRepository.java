package domain.repository.veiculo;

import domain.model.veiculo.Carro;
import domain.model.veiculo.Moto;


import java.util.List;

public interface ICarroRepository {
    void save(Carro carro);
    void update(int id, Carro carro);
    List<Carro> findByNameContains(String substring);
    List<Carro> findAll();
    int getNextId();
    void deleteAll();
}
