package domain.service.operacao;


import domain.model.operacao.OperacaoDevolucao;
import domain.model.operacao.OperacaoLocacao;
import domain.repository.operacao.OperacaoDevolucaoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class OperacaoDevolucaoService {
    private OperacaoDevolucaoRepository operacaoRepository;
    private OperacaoLocacao operacaoLocacao;

    public OperacaoDevolucaoService() {
        this.operacaoRepository = new OperacaoDevolucaoRepository();
    }

    public void saveOperacao(LocalDateTime dataHoraOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo, double custo) {

        OperacaoDevolucao operacaoDevolucao = new OperacaoDevolucao(0, dataHoraOperacao, emailCliente, cnpjAgencia, placaVeiculo, custo);
        operacaoRepository.save(operacaoDevolucao);
    }

    public void updateOperacao(int id, LocalDateTime dataHoraOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo, double custo) {
        OperacaoDevolucao operacao = new OperacaoDevolucao(id, dataHoraOperacao, emailCliente, cnpjAgencia, placaVeiculo, custo);
        operacaoRepository.update(id, operacao);
    }

    public List<OperacaoDevolucao> buscarOperacoesPorNome(String parteNome) {
        return operacaoRepository.findByNameContains(parteNome);
    }


    public List<OperacaoDevolucao> findOperacoesDevolucao() {
        return operacaoRepository.findAll();
    }

    public void deleteOperacoesDevolucao() {
        operacaoRepository.deleteAll();
    }
}

