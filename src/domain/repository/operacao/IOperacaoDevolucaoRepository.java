package domain.repository.operacao;

import domain.model.operacao.OperacaoDevolucao;

import java.util.List;

public interface IOperacaoDevolucaoRepository {
    void save(OperacaoDevolucao operacao);
    void update(int id, OperacaoDevolucao operacao);
    List<OperacaoDevolucao> findByNameContains(String substring);
    List<OperacaoDevolucao> findAll();
    int getNextId();
    void deleteAll();
}
