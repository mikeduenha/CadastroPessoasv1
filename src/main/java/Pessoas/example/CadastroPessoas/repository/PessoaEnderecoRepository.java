package Pessoas.example.CadastroPessoas.repository;

import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
import Pessoas.example.CadastroPessoas.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaEnderecoRepository extends JpaRepository<PessoaEnderecoModel, Integer> {
    Optional<PessoaEnderecoModel> findByPessoa(PessoaModel pessoaModel);
}
