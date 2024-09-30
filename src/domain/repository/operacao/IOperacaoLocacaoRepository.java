package domain.repository.operacao;


import domain.model.operacao.OperacaoLocacao;

import java.util.List;

public interface IOperacaoLocacaoRepository {
    void save(OperacaoLocacao operacao);
    void update(int id, OperacaoLocacao operacao);
    List<OperacaoLocacao> findByNameContains(String substring);
    List<OperacaoLocacao> findAll();
    int getNextId();
    void deleteAll();
}
