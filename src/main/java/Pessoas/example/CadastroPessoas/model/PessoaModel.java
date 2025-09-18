package Pessoas.example.CadastroPessoas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "C_CADASTRO_PESSOA")
public class PessoaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer idPessoa;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private PessoaEnderecoModel endereco;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private PessoaFisicaModel pessoapf;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private PessoaJuridicaModel pessoapj;

    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String telefone;
    @Column
    private String celular;

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public PessoaFisicaModel getPessoapf() {
        return pessoapf;
    }

    public void setPessoapf(PessoaFisicaModel pessoapf) {
        this.pessoapf = pessoapf;
    }

    public PessoaJuridicaModel getPessoapj() {
        return pessoapj;
    }

    public void setPessoapj(PessoaJuridicaModel pessoapj) {
        this.pessoapj = pessoapj;
    }

    public PessoaEnderecoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(PessoaEnderecoModel endereco) {
        this.endereco = endereco;
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
}
