package domain.repository.veiculo;

import domain.model.veiculo.Carro;


import java.util.List;

public interface ICarroRepository {
    void save(Carro carro);
    List<Carro> findAll();
    int getNextId();
    void deleteAll();
}
