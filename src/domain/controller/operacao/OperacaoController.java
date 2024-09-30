package domain.controller.operacao;


import domain.model.operacao.Operacao;
import domain.service.operacao.OperacaoService;

import java.time.LocalDateTime;
import java.util.List;

public class OperacaoController {
    private OperacaoService operacaoService;

    public OperacaoController() {
        this.operacaoService = new OperacaoService();
    }

    public void saveOperacao(LocalDateTime dataHoraOperacao, String tipoOperacao, String emailCliente, String cnpjOperacao, String placaVeiculo) {
        operacaoService.saveOperacao(dataHoraOperacao, tipoOperacao, emailCliente, cnpjOperacao, placaVeiculo);
        System.out.println("Operação cadastrada com sucesso!");
    }

    public void findAllOperacoes() {
        List<Operacao> operacaos = operacaoService.findOperacoes();
        if (operacaos.isEmpty()) {
            System.out.println("Nenhuma operação cadastrada.");
        } else {
            operacaos.forEach(System.out::println);
        }
    }

    public void deleteOperacoes() {
        operacaoService.deleteOperacoes();
        System.out.println("Todas as operações foram deletadas.");
    }

    public void atualizarOperacao(int id, LocalDateTime dataHoraOperacao, String tipoOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo) {
        operacaoService.updateOperacao( id, dataHoraOperacao, tipoOperacao, emailCliente, cnpjAgencia, placaVeiculo);
        System.out.println("operação atualizada com sucesso!");
    }

    public void buscarOperacoesPorNome(String parteNome) {
        List<Operacao> operacaos = operacaoService.buscarOperacoesPorNome(parteNome);
        if (operacaos.isEmpty()) {
            System.out.println("Nenhuma operação encontrado com o tipo: " + parteNome);
        } else {
            operacaos.forEach(System.out::println);
        }
    }
}
