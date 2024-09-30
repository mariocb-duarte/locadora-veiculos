package domain.controller.cliente;

import domain.model.cliente.ClientePessoaFisica;
import domain.model.cliente.ClientePessoaJuridica;
import domain.service.cliente.ClientePessoaJuridicaService;

import java.util.List;

public class ClientePessoaJuridicaController {


    private ClientePessoaJuridicaService clientePessoaJuridicaService;

    public ClientePessoaJuridicaController() {
        this.clientePessoaJuridicaService = new ClientePessoaJuridicaService();
    }

    public void saveClientePessoaJuridica(String nome, String email, String telefone, String cnpj) {
        clientePessoaJuridicaService.saveClientePessoaJuridica(nome, email, telefone, cnpj);
        System.out.println("Cliente pessoa juridica cadastrado com sucesso!");
    }

    public void findClientesPessoaJuridica() {
        List<ClientePessoaJuridica> clientesPessoaJuridica = clientePessoaJuridicaService.findClientesPessoaJuridica();
        if (clientesPessoaJuridica.isEmpty()) {
            System.out.println("Nenhuma cliente cadastrado.");
        } else {
            clientesPessoaJuridica.forEach(System.out::println);
        }
    }

    public void deleteClientesPessoaJuridica() {
        clientePessoaJuridicaService.deletarClientesPessoaJuridica();
        System.out.println("Todos os clientes pessoas juridicas foram deletados.");
    }
    public void atualizarClientePessoaJuridica(int id, String nome, String email, String telefone, String cnpj) {
        clientePessoaJuridicaService.updateClientePessoaJuridica(id, nome, email, telefone, cnpj);
        System.out.println("Cliente pessoa jurídica atualizado com sucesso!");
    }

    public void buscarClientesPessoaJuridicaPorNome(String parteNome) {
        List<ClientePessoaJuridica> clientesPessoaJuridica = clientePessoaJuridicaService.buscarClientesPessoaJuridicaPorNome(parteNome);
        if (clientesPessoaJuridica.isEmpty()) {
            System.out.println("Nenhum cliente pessoa jurídica encontrado com o nome: " + parteNome);
        } else {
            clientesPessoaJuridica.forEach(System.out::println);
        }
    }
}
