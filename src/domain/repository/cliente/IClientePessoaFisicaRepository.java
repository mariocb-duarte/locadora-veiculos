package domain.repository.cliente;

import domain.model.agencia.Agencia;
import domain.model.cliente.ClientePessoaFisica;

import java.util.List;

public interface IClientePessoaFisicaRepository {
    void save(ClientePessoaFisica clientePessoaFisica);
    void update(int id, ClientePessoaFisica clientePessoaFisica);
    List<ClientePessoaFisica> findByNameContains(String substring);
    List<ClientePessoaFisica> findAll();
    int getNextId();
    void deleteAll();
}
