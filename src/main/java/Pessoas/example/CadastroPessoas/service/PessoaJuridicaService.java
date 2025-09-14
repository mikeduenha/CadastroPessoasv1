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
        PessoaJuridicaModel pessoapj = new PessoaJuridicaModel();

        pessoaJuridicaRepository.save(pessoapj);
    }

    public void addPessoa(PessoaJuridicaModel pessoaJuridicaModel){pessoaJuridicaRepository.save(pessoaJuridicaModel);}

    public List<PessoaJuridicaModel> listar(){return pessoaJuridicaRepository.findAll();}
    public Optional<PessoaJuridicaModel> findById(Integer id){return pessoaJuridicaRepository.findById(id);}
    public Optional<PessoaJuridicaModel> findByCnpj(String id){return pessoaJuridicaRepository.findByCnpj(id);}
    public Optional<PessoaJuridicaModel> findByCelular(String id){return pessoaJuridicaRepository.findByCelular(id);}

    public Optional<PessoaJuridicaModel> updPessoa(Integer id, PessoaJuridicaModel pessoaJuridicaModel){
        var optional = pessoaJuridicaRepository.findById(id);
        if(optional.isEmpty()) {
            return Optional.empty();
        }
        var pessoaloriginal = optional.get();
        pessoaloriginal.setNome(pessoaJuridicaModel.getNome());
        pessoaloriginal.setCnpj(pessoaJuridicaModel.getCnpj());
        pessoaloriginal.setEmail(pessoaJuridicaModel.getEmail());
        pessoaloriginal.setTelefone(pessoaJuridicaModel.getTelefone());

        return Optional.of(pessoaJuridicaRepository.save(pessoaloriginal));
    }

    public boolean deletePessoa(Integer id) {
        try{
            var optional = pessoaJuridicaRepository.findById(id);

            if (optional.isEmpty()) {
                return false;
            }
            pessoaJuridicaRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
