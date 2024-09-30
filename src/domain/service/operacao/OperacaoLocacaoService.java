package domain.service.operacao;

import domain.model.operacao.OperacaoLocacao;
import domain.repository.operacao.OperacaoLocacaoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class OperacaoLocacaoService {
    private OperacaoLocacaoRepository operacaoRepository;

    public OperacaoLocacaoService() {
        this.operacaoRepository = new OperacaoLocacaoRepository();
    }

    public void saveOperacao(LocalDateTime dataHoraOperacao, String tipoOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo) {
        OperacaoLocacao operacao = new OperacaoLocacao(0, dataHoraOperacao, tipoOperacao, emailCliente, cnpjAgencia, placaVeiculo);
        operacaoRepository.save(operacao);
    }

    public void updateOperacao(int id, LocalDateTime dataHoraOperacao, String tipoOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo) {
        OperacaoLocacao operacao = new OperacaoLocacao(id, dataHoraOperacao, tipoOperacao, emailCliente, cnpjAgencia, placaVeiculo);
        operacaoRepository.update(id, operacao);
    }

    public List<OperacaoLocacao> buscarOperacoesPorNome(String parteNome) {
        return operacaoRepository.findByNameContains(parteNome);
    }


    public List<OperacaoLocacao> findOperacoes() {
        return operacaoRepository.findAll();
    }

    public void deleteOperacoes() {
        operacaoRepository.deleteAll();
    }
}
