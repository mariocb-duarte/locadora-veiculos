package domain.repository.cliente;

import domain.model.agencia.Agencia;
import domain.model.cliente.ClientePessoaJuridica;

import java.util.List;

public interface IClientePessoaJuridicaRepository {
    void save(ClientePessoaJuridica clientePessoaJuridica);
    void update(int id, ClientePessoaJuridica clientePessoaJuridica);
    List<ClientePessoaJuridica> findByNameContains(String substring);
    List<ClientePessoaJuridica> findAll();
    int getNextId();
    void deleteAll();
}
