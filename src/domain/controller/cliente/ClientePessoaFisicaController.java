package domain.controller.cliente;

import domain.model.cliente.ClientePessoaFisica;
import domain.service.cliente.ClientePessoaFisicaService;

import java.util.List;

public class ClientePessoaFisicaController {

    private ClientePessoaFisicaService clientePessoaFisicaService;

    public ClientePessoaFisicaController() {
        this.clientePessoaFisicaService = new ClientePessoaFisicaService();
    }

    public void saveClientePessoaFisica(String nome, String email, String telefone, String cpf) {
        clientePessoaFisicaService.saveClientePessoaFisica(nome, email, telefone, cpf);
        System.out.println("Cliente pessoa física cadastrado com sucesso!");
    }

    public void findClientesPessoaFisicas() {
        List<ClientePessoaFisica> clientePessoaFisicas = clientePessoaFisicaService.findClientesPessoaFisica();
        if (clientePessoaFisicas.isEmpty()) {
            System.out.println("Nenhuma cliente cadastrado.");
        } else {
            clientePessoaFisicas.forEach(System.out::println);
        }
    }

    public void deleteClientesPessoaFisica() {
        clientePessoaFisicaService.deletarClientesPessoaFisica();
        System.out.println("Todos os clientes pessoas fisicas foram deletados.");
    }
}
