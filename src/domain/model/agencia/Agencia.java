package domain.model.agencia;

public class Agencia {


    private int id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String telefone;
    private String email;
    public String endereco;

    public Agencia(int id, String cnpj, String razaoSocial, String nomeFantasia, String telefone, String email, String endereco) {
        this.id = id;
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return id + ", " + cnpj + ", " + razaoSocial + ", " + nomeFantasia + ", " + telefone + ", " + email + ", " + endereco;
    }

    public static Agencia fromCSV(String csvLine) {
        String[] campos = csvLine.split(",");
        return new Agencia(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], campos[4], campos[5], campos[6]);
    }
}
