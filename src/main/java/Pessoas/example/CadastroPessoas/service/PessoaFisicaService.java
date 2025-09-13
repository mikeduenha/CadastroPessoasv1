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
      
        pessoaFisicaRepository.save(pessoapf);
    }

    public void addPessoa(PessoaFisicaModel pessoaFisicaModel) {
        pessoaFisicaRepository.save(pessoaFisicaModel);
    }

    public List<PessoaFisicaModel> listar() {
        return pessoaFisicaRepository.findAll();
    }

    public Optional<PessoaFisicaModel> findById(Integer id) {return pessoaFisicaRepository.findById(id);}
    public Optional<PessoaFisicaModel> findByCpf(String cpf) {return pessoaFisicaRepository.findByCpf(cpf);}
    public Optional<PessoaFisicaModel> findByCelular(String celular) {return pessoaFisicaRepository.findByCelular(celular);}
    public Optional<PessoaFisicaModel> updPessoa(Integer id, PessoaFisicaModel pessoaFisicaModel) {
        var optional = findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        var pessoaloriginal = optional.get();
        pessoaloriginal.setNome(pessoaFisicaModel.getNome());
        pessoaloriginal.setCpf(pessoaFisicaModel.getCpf());
        pessoaloriginal.setRg(pessoaFisicaModel.getRg());
        pessoaloriginal.setEmail(pessoaFisicaModel.getEmail());
        pessoaloriginal.setTelefone(pessoaFisicaModel.getTelefone());
        pessoaloriginal.setIdEndereco(pessoaFisicaModel.getIdEndereco());

        return Optional.of(pessoaFisicaRepository.save(pessoaloriginal)) ;
    }

    public boolean deletePessoa(Integer id) {
        try{
            var optional = findById(id);

            if (optional.isEmpty()) {
                return false;
            }
            pessoaFisicaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
