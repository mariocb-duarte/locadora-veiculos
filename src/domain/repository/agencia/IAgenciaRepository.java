package domain.repository.agencia;

import domain.model.agencia.Agencia;

import java.util.List;

public interface IAgenciaRepository {

    void save(Agencia usuario);
    List<Agencia> findAll();
    int getNextId();
    void deleteAll();
}