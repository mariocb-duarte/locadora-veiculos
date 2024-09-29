package domain.model.veiculo;

public class Caminhao extends Veiculo {
    public Caminhao(int id, String fabricante, String modelo, String placa, int anoFabricacao, int anoModelo, String cor, boolean disponivel, String localizacao) {
        super(id, fabricante, modelo, placa, anoFabricacao, anoModelo, cor, disponivel, localizacao);
        this.setTarifaAluguel(200);
    }

    @Override
    public String toString() {
        return getId() + ", "
                + getFabricante() + ", "
                + getModelo() + ", "
                + getPlaca() + ", "
                + getAnoFabricacao() + ", "
                + getAnoModelo() + ", "
                + getCor() + ", "
                + isDisponivel() + ", "
                + getLocalizacao();
    }

    public static Caminhao fromCSV(String csvLine) {
        String[] campos = csvLine.split(",");
        return new Caminhao(Integer.parseInt(campos[0]),
                campos[1],
                campos[2],
                campos[3],
                Integer.parseInt(campos[4]),
                Integer.parseInt(campos[5]),
                campos[6],
                Boolean.parseBoolean(campos[7]),
                campos[8]);
    }
}
