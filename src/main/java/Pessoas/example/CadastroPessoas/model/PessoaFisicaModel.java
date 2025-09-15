package Pessoas.example.CadastroPessoas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "C_CADASTRO_PF")
public class PessoaFisicaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer idPessoaPF;
    @Column
    private String cpf;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String telefone;
    @Column
    private String celular;


    public Integer getIdPessoaPF() {
        return idPessoaPF;
    }

    public void setIdPessoaPF(Integer idPessoaPF) {
        this.idPessoaPF = idPessoaPF;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
