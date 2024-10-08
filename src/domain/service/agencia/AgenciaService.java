package domain.service.agencia;

import domain.model.agencia.Agencia;
import domain.repository.agencia.AgenciaRepository;

import java.util.List;

public class AgenciaService {
    private AgenciaRepository agenciaRepository;

    public AgenciaService() {
        this.agenciaRepository = new AgenciaRepository();
    }

    public void saveAgencia(String cnpj, String razaoSocial, String nomeFantasia, String telefone, String email, String endereco) {
        Agencia agencia = new Agencia(0, cnpj, razaoSocial, nomeFantasia, telefone, email, endereco);
        agenciaRepository.save(agencia);
    }

    public void updateAgencia(int id, String cnpj, String razaoSocial, String nomeFantasia, String telefone, String email,  String endereco) {
        Agencia agencia = new Agencia(id, cnpj, razaoSocial, nomeFantasia, telefone, email, endereco);
        agenciaRepository.update(id, agencia);
    }

    public List<Agencia> buscarAgenciasPorNome(String parteNome) {
        return agenciaRepository.findByNameContains(parteNome);
    }

    public List<Agencia> buscarAgenciasPorEndereco(String parteEndereco) {
        return agenciaRepository.findByEnderecoContains(parteEndereco);
    }

    public List<Agencia> findAgencias() {
        return agenciaRepository.findAll();
    }

    public void deleteAgencias() {
        agenciaRepository.deleteAll();
    }
}