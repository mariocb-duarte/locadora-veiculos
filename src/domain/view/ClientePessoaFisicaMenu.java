package domain.view;


import domain.controller.cliente.ClientePessoaFisicaController;

import java.util.Scanner;

public class ClientePessoaFisicaMenu {

    ClientePessoaFisicaController clienteController = new ClientePessoaFisicaController();
    Scanner scanner = new Scanner(System.in);

    public void clienteMenu() {
        while (true) {
            System.out.println("1. Adicionar Agências");
            System.out.println("2. Listar Agências");
            System.out.println("3. Deletar Todas as Agências");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o email: ");
                    String email = scanner.nextLine();
                    System.out.print("Digite o telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Digite o cpf: ");
                    String cpf = scanner.nextLine();
                    clienteController.saveClientePessoaFisica(nome, email, telefone, cpf);
                    //int id, String nome, String email, String telefone, String cpf
                    break;
                case 2:
                    clienteController.findClientesPessoaFisicas();
                    break;
                case 3:
                    clienteController.deleteClientesPessoaFisica();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}

