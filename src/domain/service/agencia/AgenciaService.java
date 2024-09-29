package domain.service.agencia;

import domain.model.agencia.Agencia;
import domain.repository.agencia.AgenciaRepository;

import java.util.List;

public class AgenciaService {
    private AgenciaRepository agenciaRepository;

    public AgenciaService() {
        this.agenciaRepository = new AgenciaRepository();
    }

    public void saveAgencia(String cnpj, String razaoSocial, String nomeFantasia, String telefone, String email) {
        Agencia agencia = new Agencia(0, cnpj, razaoSocial, nomeFantasia, telefone, email);
        agenciaRepository.save(agencia);
    }

    public List<Agencia> findAgencias() {
        return agenciaRepository.findAll();
    }

    public void deleteAgencias() {
        agenciaRepository.deleteAll();
    }
}