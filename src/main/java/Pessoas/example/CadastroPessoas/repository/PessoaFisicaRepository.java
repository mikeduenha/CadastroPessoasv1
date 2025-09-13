package Pessoas.example.CadastroPessoas.repository;

import Pessoas.example.CadastroPessoas.model.PessoaFisicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaModel, Integer> {
        Optional<PessoaFisicaModel> findByCpf(String cpf) ;
        Optional<PessoaFisicaModel> findByCelular(String celular) ;
}
