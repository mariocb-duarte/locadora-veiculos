package domain.controller.cliente;

import domain.model.agencia.Agencia;
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

    public void atualizarClientePessoaFisica(int id, String nome, String email, String telefone, String cpf) {
        clientePessoaFisicaService.updateClientePessoaFisica(id, nome, email, telefone, cpf);
        System.out.println("Cliente pessoa física atualizado com sucesso!");
    }

    public void buscarClientesPessoaFisicaPorNome(String parteNome) {
        List<ClientePessoaFisica> clientesPessoaFisica = clientePessoaFisicaService.buscarClientesPessoaFisicaPorNome(parteNome);
        if (clientesPessoaFisica.isEmpty()) {
            System.out.println("Nenhum cliente pessoa física encontrado com o nome: " + parteNome);
        } else {
            clientesPessoaFisica.forEach(System.out::println);
        }
    }
}
