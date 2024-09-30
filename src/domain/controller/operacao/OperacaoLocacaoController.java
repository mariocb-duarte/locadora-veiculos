package domain.controller.operacao;

import domain.model.operacao.OperacaoLocacao;
import domain.service.operacao.OperacaoLocacaoService;

import java.time.LocalDateTime;
import java.util.List;

public class OperacaoLocacaoController {
    private OperacaoLocacaoService operacaoLocacaoService;

    public OperacaoLocacaoController() {
        this.operacaoLocacaoService = new OperacaoLocacaoService();
    }

    public void saveOperacaoLocacao(int id, LocalDateTime dataHoraOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo) {
        operacaoLocacaoService.saveOperacao(dataHoraOperacao, emailCliente, cnpjAgencia, placaVeiculo);
        System.out.println("Locação cadastrada com sucesso!");
    }

    public void findAllOperacoesLocacoes() {
        List<OperacaoLocacao> operacoes = operacaoLocacaoService.findOperacoes();
        if (operacoes.isEmpty()) {
            System.out.println("Nenhuma locação cadastrada.");
        } else {
            operacoes.forEach(System.out::println);
        }
    }

    public void deleteOperacoesLocacoes() {
        operacaoLocacaoService.deleteOperacoes();
        System.out.println("Todas as locações foram deletadas.");
    }

    public void atualizarOperacaoLocacao(int id, LocalDateTime dataHoraOperacao,String emailCliente, String cnpjAgencia, String placaVeiculo) {
        operacaoLocacaoService.updateOperacao(id, dataHoraOperacao,emailCliente, cnpjAgencia, placaVeiculo);
        System.out.println("Locação atualizada com sucesso!");
    }

    public void buscarOperacaosPorNome(String parteNome) {
        List<OperacaoLocacao> operacoes = operacaoLocacaoService.buscarOperaoesPorNome(parteNome);
        if (operacoes.isEmpty()) {
            System.out.println("Nenhuma locação encontrada com a placa: " + parteNome);
        } else {
            operacoes.forEach(System.out::println);
        }
    }
}

