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
        PessoaFisicaModel pessoapf = new PessoaFisicaModel();
        pessoapf.setNome("Mike");
        pessoapf.setCpf("123");
        pessoapf.setRg("123");
        pessoapf.setTelefone("11");
        pessoapf.setCelular("11");
        pessoapf.setEmail("mikeduenha@gmail.com");
        pessoapf.setIdEndereco(1);

        pessoaFisicaRepository.save(pessoapf);

    }

    public List<PessoaFisicaModel> listar() {
        return pessoaFisicaRepository.findAll();
    }

    public Optional<PessoaFisicaModel> findById(Integer id) {return pessoaFisicaRepository.findById(id);}
}
