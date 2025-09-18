package Pessoas.example.CadastroPessoas.repository;

import Pessoas.example.CadastroPessoas.model.PessoaJuridicaModel;
import Pessoas.example.CadastroPessoas.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridicaModel, Integer>{
    Optional<PessoaJuridicaModel> findByCnpj(String cnpj) ;
    Optional<PessoaJuridicaModel> findByPessoa(PessoaModel pessoaModel);

}
