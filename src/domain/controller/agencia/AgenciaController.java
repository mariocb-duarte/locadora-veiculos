package domain.controller.agencia;

import domain.model.agencia.Agencia;
import domain.service.agencia.AgenciaService;

import java.util.List;

public class AgenciaController {
    private AgenciaService agenciaService;

    public AgenciaController() {
        this.agenciaService = new AgenciaService();
    }

    public void saveAgenda(String cnpj, String razaoSocial, String nomeFantasia, String telefone, String email) {
        agenciaService.saveAgencia(cnpj, razaoSocial, nomeFantasia, telefone, email);
        System.out.println("Agência cadastrada com sucesso!");
    }

    public void findAllAgencias() {
        List<Agencia> agencias = agenciaService.findAgencias();
        if (agencias.isEmpty()) {
            System.out.println("Nenhuma agência cadastrada.");
        } else {
            agencias.forEach(System.out::println);
        }
    }

    public void deleteAgencias() {
        agenciaService.deleteAgencias();
        System.out.println("Todas as agências foram deletadas.");
    }

    public void atualizarAgencia(int id,String cnpj, String razaoSocial, String nomeFantasia, String telefone, String email) {
        agenciaService.updateAgencia(id, cnpj, razaoSocial, nomeFantasia, telefone, email);
        System.out.println("Agência atualizada com sucesso!");
    }

    public void buscarAgenciasPorNome(String parteNome) {
        List<Agencia> agencias = agenciaService.buscarAgenciasPorNome(parteNome);
        if (agencias.isEmpty()) {
            System.out.println("Nenhum usuário encontrado com o nome: " + parteNome);
        } else {
            agencias.forEach(System.out::println);
        }
    }
}