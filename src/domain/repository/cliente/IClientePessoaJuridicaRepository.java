package domain.repository.cliente;

import domain.model.cliente.ClientePessoaJuridica;

import java.util.List;

public interface IClientePessoaJuridicaRepository {
    void save(ClientePessoaJuridica clientePessoaJuridica);
    List<ClientePessoaJuridica> findAll();
    int getNextId();
    void deleteAll();
}
