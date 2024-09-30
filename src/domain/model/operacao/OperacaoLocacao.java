package domain.model.operacao;

import java.time.LocalDateTime;

public class OperacaoLocacao {
    private int id;
    private LocalDateTime dataHoraOperacao;
    private String emailCliente;
    private String cnpjAgencia;
    private String placaVeiculo;

    public OperacaoLocacao(int id, LocalDateTime dataHoraOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo) {
        this.id = id;
        this.dataHoraOperacao = dataHoraOperacao;
        this.emailCliente = emailCliente;
        this.cnpjAgencia = cnpjAgencia;
        this.placaVeiculo = placaVeiculo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraOperacao() {
        return dataHoraOperacao;
    }

    public void setDataHoraOperacao(LocalDateTime dataHoraOperacao) {
        this.dataHoraOperacao = dataHoraOperacao;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getCnpjAgencia() {
        return cnpjAgencia;
    }

    public void setCnpjAgencia(String cnpjAgencia) {
        this.cnpjAgencia = cnpjAgencia;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    @Override
    public String toString() {
        return getId() + ", " + getDataHoraOperacao() + ", " + getEmailCliente() + ", " + getCnpjAgencia()+ ", " + getPlacaVeiculo();
    }


    public static OperacaoLocacao fromCSV(String csvLine) {
        String[] campos = csvLine.split(",");
        return new OperacaoLocacao(Integer.parseInt(campos[0]), LocalDateTime.parse(campos[1]), campos[2], campos[3], campos[4]);
    }
}


