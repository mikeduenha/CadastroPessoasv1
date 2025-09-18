package Pessoas.example.CadastroPessoas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "C_CADASTRO_PJ")
public class PessoaJuridicaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer idPessoaPJ;
    @Column
    private String cnpj;
    @Column
    private String razaosocial;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private PessoaModel pessoa;

    public Integer getIdPessoaPJ() {
        return idPessoaPJ;
    }

    public void setIdPessoaPJ(Integer idPessoaPJ) {
        this.idPessoaPJ = idPessoaPJ;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaosocial() {
        return razaosocial;
    }

    public void setRazaosocial(String razao_social) {
        this.razaosocial = razao_social;
    }

    public PessoaModel getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaModel pessoa) {
        this.pessoa = pessoa;
    }
}
