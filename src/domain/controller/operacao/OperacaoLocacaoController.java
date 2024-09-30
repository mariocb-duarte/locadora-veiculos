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

    public void saveOperacao(LocalDateTime dataHoraOperacao, String tipoOperacao, String emailCliente, String cnpjOperacao, String placaVeiculo) {
        operacaoLocacaoService.saveOperacao(dataHoraOperacao, tipoOperacao, emailCliente, cnpjOperacao, placaVeiculo);
        System.out.println("Operação cadastrada com sucesso!");
    }

    public void findAllOperacoes() {
        List<OperacaoLocacao> operacaos = operacaoLocacaoService.findOperacoes();
        if (operacaos.isEmpty()) {
            System.out.println("Nenhuma operação cadastrada.");
        } else {
            operacaos.forEach(System.out::println);
        }
    }

    public void deleteOperacoes() {
        operacaoLocacaoService.deleteOperacoes();
        System.out.println("Todas as operações foram deletadas.");
    }

    public void atualizarOperacao(int id, LocalDateTime dataHoraOperacao, String tipoOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo) {
        operacaoLocacaoService.updateOperacao( id, dataHoraOperacao, tipoOperacao, emailCliente, cnpjAgencia, placaVeiculo);
        System.out.println("operação atualizada com sucesso!");
    }

    public void buscarOperacoesPorNome(String parteNome) {
        List<OperacaoLocacao> operacaos = operacaoLocacaoService.buscarOperacoesPorNome(parteNome);
        if (operacaos.isEmpty()) {
            System.out.println("Nenhuma operação encontrado com o tipo: " + parteNome);
        } else {
            operacaos.forEach(System.out::println);
        }
    }
}
