package Pessoas.example.CadastroPessoas.service;

import Pessoas.example.CadastroPessoas.model.PessoaFisicaModel;
import Pessoas.example.CadastroPessoas.repository.PessoaFisicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaFisicaService {

    private final PessoaFisicaRepository pessoaFisicaRepository;

    public PessoaFisicaService(PessoaFisicaRepository pessoaFisicaRepository) {
      this.pessoaFisicaRepository = pessoaFisicaRepository;
       }

    public Optional<PessoaFisicaModel> findById(Integer id){return pessoaFisicaRepository.findById(id);}

    public List<PessoaFisicaModel> listar(){return pessoaFisicaRepository.findAll();}

}
