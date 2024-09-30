package domain.service.operacao;

import domain.model.agencia.Agencia;
import domain.model.operacao.Operacao;
import domain.repository.operacao.OperacaoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class OperacaoService {
    private OperacaoRepository operacaoRepository;

    public OperacaoService() {
        this.operacaoRepository = new OperacaoRepository();
    }

    public void saveOperacao(LocalDateTime dataHoraOperacao, String tipoOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo) {
        Operacao operacao = new Operacao(0, dataHoraOperacao, tipoOperacao, emailCliente, cnpjAgencia, placaVeiculo);
        operacaoRepository.save(operacao);
    }

    public void updateOperacao(int id, LocalDateTime dataHoraOperacao, String tipoOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo) {
        Operacao operacao = new Operacao(id, dataHoraOperacao, tipoOperacao, emailCliente, cnpjAgencia, placaVeiculo);
        operacaoRepository.update(id, operacao);
    }

    public List<Operacao> buscarOperacoesPorNome(String parteNome) {
        return operacaoRepository.findByNameContains(parteNome);
    }


    public List<Operacao> findOperacoes() {
        return operacaoRepository.findAll();
    }

    public void deleteOperacoes() {
        operacaoRepository.deleteAll();
    }
}
