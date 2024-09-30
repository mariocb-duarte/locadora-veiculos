package domain.repository.agencia;

import domain.model.agencia.Agencia;

import java.util.List;

public interface IAgenciaRepository {

    void save(Agencia agencia);
    void update(int id, Agencia agencia);
    List<Agencia> findByNameContains(String substring);
    List<Agencia> findByEnderecoContains(String substring);
    List<Agencia> findAll();
    int getNextId();
    void deleteAll();
}