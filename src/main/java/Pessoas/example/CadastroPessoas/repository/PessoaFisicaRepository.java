package Pessoas.example.CadastroPessoas.repository;

import Pessoas.example.CadastroPessoas.model.PessoaFisicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisicaModel, Integer> {

}
