package domain.service.operacao;

import domain.model.operacao.OperacaoDevolucao;
import domain.repository.operacao.OperacaoDevolucaoRepository;


import java.time.LocalDateTime;
import java.util.List;

public class OperacaoDevolucaoService {
    private OperacaoDevolucaoRepository operacaoLocacaoRepository;

    public OperacaoDevolucaoService() {
        this.operacaoLocacaoRepository = new OperacaoDevolucaoRepository();
    }

    public void saveOperacao(LocalDateTime dataHoraOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo, double custo) {
        OperacaoDevolucao operacao = new OperacaoDevolucao(0, dataHoraOperacao, emailCliente, cnpjAgencia, placaVeiculo, custo);
        operacaoLocacaoRepository.save(operacao);
    }

    public void updateOperacao(int id, LocalDateTime dataHoraOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo, double custo) {
        OperacaoDevolucao operacao = new OperacaoDevolucao(id, dataHoraOperacao,emailCliente, cnpjAgencia, placaVeiculo, custo);
        operacaoLocacaoRepository.update(id, operacao);
    }

    public List<OperacaoDevolucao> buscarOperaoesPorNome(String parteNome) {
        return operacaoLocacaoRepository.findByNameContains(parteNome);
    }


    public List<OperacaoDevolucao> findOperacoes() {
        return operacaoLocacaoRepository.findAll();
    }

    public void deleteOperacoes() {
        operacaoLocacaoRepository.deleteAll();
    }
}

