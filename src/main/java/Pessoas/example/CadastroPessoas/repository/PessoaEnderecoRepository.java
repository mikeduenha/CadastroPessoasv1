package Pessoas.example.CadastroPessoas.repository;


import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaEnderecoRepository extends JpaRepository<PessoaEnderecoModel, Integer> {
    Optional<PessoaEnderecoModel> findByCep(String cep);
}
