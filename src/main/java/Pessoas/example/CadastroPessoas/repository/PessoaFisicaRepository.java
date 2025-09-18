package Pessoas.example.CadastroPessoas.repository;

import Pessoas.example.CadastroPessoas.model.PessoaFisicaModel;
import Pessoas.example.CadastroPessoas.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaModel, Integer> {
        Optional<PessoaFisicaModel> findByCpf(String cpf) ;
        Optional<PessoaFisicaModel> findByRg(String rg) ;
        Optional<PessoaFisicaModel> findByPessoa(PessoaModel pessoaModel);
}
