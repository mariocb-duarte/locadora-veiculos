package domain.service.cliente;

import domain.model.cliente.ClientePessoaFisica;
import domain.repository.cliente.ClientePessoaFisicaRepository;

import java.util.List;

public class ClientePessoaFisicaService {
    private ClientePessoaFisicaRepository clientePessoaFisicaRepository;

    public ClientePessoaFisicaService() {
        this.clientePessoaFisicaRepository = new ClientePessoaFisicaRepository();
    }

    public void saveClientePessoaFisica(String nome, String telefone, String email, String cpf){
        ClientePessoaFisica clientePessoaFisica = new ClientePessoaFisica(0, nome, email, telefone, cpf);
        clientePessoaFisicaRepository.save(clientePessoaFisica);
    }

    public List<ClientePessoaFisica> findClientesPessoaFisica(){
        return clientePessoaFisicaRepository.findAll();
    }

    public void deletarClientesPessoaFisica(){
        clientePessoaFisicaRepository.deleteAll();
    }

}

