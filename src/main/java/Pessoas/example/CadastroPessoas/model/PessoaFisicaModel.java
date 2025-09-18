package Pessoas.example.CadastroPessoas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String rg;
    @Column
    private String datanasc;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private PessoaModel pessoa;

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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public PessoaModel getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaModel pessoa) {
        this.pessoa = pessoa;
    }
}
