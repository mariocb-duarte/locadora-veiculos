package domain.controller.operacao;


import domain.model.operacao.OperacaoDevolucao;
import domain.service.operacao.OperacaoDevolucaoService;


import java.time.LocalDateTime;
import java.util.List;

public class OperacaoDevolucaoController {
    private OperacaoDevolucaoService operacaoDevolucaoService;

    public OperacaoDevolucaoController() {
        this.operacaoDevolucaoService = new OperacaoDevolucaoService();
    }

    public void saveOperacaoLocacao(int id, LocalDateTime dataHoraOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo, double custo) {
        operacaoDevolucaoService.saveOperacao(dataHoraOperacao, emailCliente, cnpjAgencia, placaVeiculo, custo);
        System.out.println("Devolução cadastrada com sucesso!");
    }

    public void findAllOperacoesLocacoes() {
        List<OperacaoDevolucao> operacoes = operacaoDevolucaoService.findOperacoes();
        if (operacoes.isEmpty()) {
            System.out.println("Nenhuma devolução cadastrada.");
        } else {
            operacoes.forEach(System.out::println);
        }
    }

    public void deleteOperacoesLocacoes() {
        operacaoDevolucaoService.deleteOperacoes();
        System.out.println("Todas as devoluções foram deletadas.");
    }

    public void atualizarOperacaoLocacao(int id, LocalDateTime dataHoraOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo, double custo) {
        operacaoDevolucaoService.updateOperacao(id, dataHoraOperacao, emailCliente, cnpjAgencia, placaVeiculo, custo);
        System.out.println("Devolução atualizada com sucesso!");
    }

    public void buscarOperacaosPorNome(String parteNome) {
        List<OperacaoDevolucao> operacoes = operacaoDevolucaoService.buscarOperaoesPorNome(parteNome);
        if (operacoes.isEmpty()) {
            System.out.println("Nenhuma devolução encontrada com a placa: " + parteNome);
        } else {
            operacoes.forEach(System.out::println);
        }
    }
}

