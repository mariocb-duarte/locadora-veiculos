package domain.service.operacao;


import domain.model.operacao.OperacaoLocacao;
import domain.repository.operacao.OperacaoLocacaoRepository;


import java.time.LocalDateTime;
import java.util.List;

public class OperacaoLocacaoService {
    private OperacaoLocacaoRepository operacaoLocacaoRepository;

    public OperacaoLocacaoService() {
        this.operacaoLocacaoRepository = new OperacaoLocacaoRepository();
    }

    public void saveOperacao(LocalDateTime dataHoraOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo) {
        OperacaoLocacao operacao = new OperacaoLocacao(0, dataHoraOperacao, emailCliente, cnpjAgencia, placaVeiculo);
        operacaoLocacaoRepository.save(operacao);
    }

    public void updateOperacao(int id, LocalDateTime dataHoraOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo) {
        OperacaoLocacao operacao = new OperacaoLocacao(id, dataHoraOperacao, emailCliente, cnpjAgencia, placaVeiculo);
        operacaoLocacaoRepository.update(id, operacao);
    }

    public List<OperacaoLocacao> buscarOperaoesPorNome(String parteNome) {
        return operacaoLocacaoRepository.findByNameContains(parteNome);
    }


    public List<OperacaoLocacao> findOperacoes() {
        return operacaoLocacaoRepository.findAll();
    }

    public void deleteOperacoes() {
        operacaoLocacaoRepository.deleteAll();
    }
}

