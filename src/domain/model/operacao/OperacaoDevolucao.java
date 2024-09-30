package domain.model.operacao;

import java.time.LocalDateTime;

public class OperacaoDevolucao {
    private int id;
    private LocalDateTime dataHoraOperacao;
    private String emailCliente;
    private String cnpjAgencia;
    private String placaVeiculo;
    private double custo;

    public OperacaoDevolucao(int id, LocalDateTime dataHoraOperacao, String emailCliente, String cnpjAgencia, String placaVeiculo, double custo) {
        this.id = id;
        this.dataHoraOperacao = dataHoraOperacao;
        this.emailCliente = emailCliente;
        this.cnpjAgencia = cnpjAgencia;
        this.placaVeiculo = placaVeiculo;
        this.custo = custo;
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

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }
}
