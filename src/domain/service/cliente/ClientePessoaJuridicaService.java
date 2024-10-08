package domain.service.cliente;

import domain.model.cliente.ClientePessoaFisica;
import domain.model.cliente.ClientePessoaJuridica;
import domain.repository.cliente.ClientePessoaJuridicaRepository;

import java.util.List;

public class ClientePessoaJuridicaService {
    private ClientePessoaJuridicaRepository clientePessoaJuridicaRepository;

    public ClientePessoaJuridicaService() {
        this.clientePessoaJuridicaRepository = new ClientePessoaJuridicaRepository();
    }

    public void saveClientePessoaJuridica(String nome, String email, String telefone,  String cnpj){
        ClientePessoaJuridica clientePessoaJuridica = new ClientePessoaJuridica(0, nome, email, telefone, cnpj);
        clientePessoaJuridicaRepository.save(clientePessoaJuridica);
    }

    public void updateClientePessoaJuridica(int id, String nome, String email, String telefone, String cnpj) {
        ClientePessoaJuridica clientePessoaJuridica = new ClientePessoaJuridica(id, nome, email, telefone, cnpj);
        clientePessoaJuridicaRepository.update(id, clientePessoaJuridica);
    }

    public List<ClientePessoaJuridica> buscarClientesPessoaJuridicaPorNome(String parteNome) {
        return clientePessoaJuridicaRepository.findByNameContains(parteNome);
    }

    public List<ClientePessoaJuridica> findClientesPessoaJuridica(){
        return clientePessoaJuridicaRepository.findAll();
    }

    public void deletarClientesPessoaJuridica(){
        clientePessoaJuridicaRepository.deleteAll();
    }

}


