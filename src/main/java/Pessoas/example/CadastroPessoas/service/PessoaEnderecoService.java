package Pessoas.example.CadastroPessoas.service;

import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
import Pessoas.example.CadastroPessoas.repository.PessoaEnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaEnderecoService {

    private final PessoaEnderecoRepository pessoaEnderecoRepository;

    public PessoaEnderecoService(PessoaEnderecoRepository pessoaEnderecoRepository){
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;
    }

    public Optional<PessoaEnderecoModel> findById(Integer id){return pessoaEnderecoRepository.findById(id);}

    public List<PessoaEnderecoModel> listar(){return pessoaEnderecoRepository.findAll();}
}
