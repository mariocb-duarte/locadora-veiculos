package domain.model.cliente;

public class ClientePessoaJuridica extends Cliente{
    private String cnpj;

    public ClientePessoaJuridica(int id, String nome, String email, String telefone, String cnpj) {
        super(id, nome, email, telefone);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return getId() + ", " + getNome() + ", " + getEmail() + ", " + getTelefone() + ", " + cnpj;
    }

    public static ClientePessoaJuridica fromCSV(String csvLine) {
        String[] campos = csvLine.split(",");
        return new ClientePessoaJuridica(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], campos[4]);
    }
}
