package Pessoas.example.CadastroPessoas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "C_CADASTRO_PJ")
public class PessoaJuridicaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer idPessoaPJ;

    @OneToOne(mappedBy = "pessoapj", cascade = CascadeType.ALL)
    private PessoaEnderecoModel endereco;

    @Column
    private String cnpj;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String telefone;
    @Column
    private String celular;
    @Column
    private Integer idEndereco ;

    public Integer getIdPessoaPJ() {
        return idPessoaPJ;
    }

    public void setIdPessoaPJ(Integer idPessoaPJ) {
        this.idPessoaPJ = idPessoaPJ;
    }

    public PessoaEnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(PessoaEnderecoModel endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }
}
