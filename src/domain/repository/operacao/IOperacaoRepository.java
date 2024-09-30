package domain.repository.operacao;

import domain.model.operacao.Operacao;

import java.util.List;

public interface IOperacaoRepository {
    void save(Operacao operacao);
    void update(int id, Operacao operacao);
    List<Operacao> findByNameContains(String substring);
    List<Operacao> findAll();
    int getNextId();
    void deleteAll();
}
