package domain.view;

import domain.controller.agencia.AgenciaController;

import java.util.Scanner;

public class AgenciaMenu {
    AgenciaController agenciaController = new AgenciaController();
    Scanner scanner = new Scanner(System.in);

    public void agenciaMenu() {
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
                    System.out.print("Digite o CNPJ: ");
                    String cnpj = scanner.nextLine();
                    System.out.print("Digite o razão social: ");
                    String razaoSocial = scanner.nextLine();
                    System.out.print("Digite o nome fantasia: ");
                    String nomeFantasia = scanner.nextLine();
                    System.out.print("Digite o telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Digite o email: ");
                    String email = scanner.nextLine();
                    System.out.print("Digite o endereco: ");
                    String endereco = scanner.nextLine();
                    agenciaController.saveAgencia(cnpj, razaoSocial, nomeFantasia, telefone, email, endereco);
                    break;
                case 2:
                    agenciaController.findAllAgencias();
                    break;
                case 3:
                    agenciaController.deleteAgencias();
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
