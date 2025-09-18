package Pessoas.example.CadastroPessoas.service;

import Pessoas.example.CadastroPessoas.model.PessoaJuridicaModel;
import Pessoas.example.CadastroPessoas.repository.PessoaJuridicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaJuridicaService {

    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    public PessoaJuridicaService(PessoaJuridicaRepository pessoaJuridicaRepository){
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
       }

    public Optional<PessoaJuridicaModel> findById(Integer id){return pessoaJuridicaRepository.findById(id);}

    public List<PessoaJuridicaModel> listar(){return pessoaJuridicaRepository.findAll();}

}
