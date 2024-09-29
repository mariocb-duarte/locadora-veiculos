package domain.repository.cliente;

import domain.model.cliente.ClientePessoaFisica;

import java.util.List;

public interface IClientePessoaFisicaRepository {
    void save(ClientePessoaFisica clientePessoaFisica);
    List<ClientePessoaFisica> findAll();
    int getNextId();
    void deleteAll();
}
