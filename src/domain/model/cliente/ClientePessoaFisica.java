package domain.model.cliente;

public class ClientePessoaFisica extends Cliente{
    private String cpf;

    public ClientePessoaFisica(int id, String nome, String email, String telefone, String cpf) {
        super(id, nome, email, telefone);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return getId() + ", " + getNome() + ", " + getEmail() + ", " + getTelefone() + ", " + cpf;
    }


    public static ClientePessoaFisica fromCSV(String csvLine) {
        String[] campos = csvLine.split(",");
        return new ClientePessoaFisica(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], campos[4]);
    }
}
