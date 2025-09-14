package Pessoas.example.CadastroPessoas.service;


import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
import Pessoas.example.CadastroPessoas.model.PessoaFisicaModel;
import Pessoas.example.CadastroPessoas.repository.PessoaEnderecoRepository;
import jakarta.persistence.Column;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaEnderecoService {

    private final PessoaEnderecoRepository pessoaEnderecoRepository;

    public PessoaEnderecoService(PessoaEnderecoRepository pessoaEnderecoRepository){
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;

        PessoaEnderecoModel pessoaend = new PessoaEnderecoModel();
        pessoaEnderecoRepository.save(pessoaend);
    }

    public void addEndereco(PessoaEnderecoModel pessoaFisicaModel){
        pessoaEnderecoRepository.save(pessoaFisicaModel);}

    public List<PessoaEnderecoModel> findAll(){
        return pessoaEnderecoRepository.findAll();
    }
    public Optional<PessoaEnderecoModel> findById(Integer id) {return pessoaEnderecoRepository.findById(id);}
    public Optional<PessoaEnderecoModel> findByCep(String id) {return pessoaEnderecoRepository.findByCep(id);}

    public Optional<PessoaEnderecoModel> updEndereco(Integer id,  PessoaEnderecoModel pessoaFisicaModel) {
        var opt = pessoaEnderecoRepository.findById(id);
        if (opt.isEmpty()) {
            return Optional.empty();
        }
        var pessoaEnderecoOrig = opt.get();

        pessoaEnderecoOrig.setCep(pessoaFisicaModel.getCep());
        pessoaEnderecoOrig.setLogradouro(pessoaFisicaModel.getLogradouro());
        pessoaEnderecoOrig.setComplemento( pessoaFisicaModel.getComplemento());
        pessoaEnderecoOrig.setUnidade(pessoaFisicaModel.getUnidade());
        pessoaEnderecoOrig.setBairro(pessoaFisicaModel.getBairro());
        pessoaEnderecoOrig.setLocalidade(pessoaFisicaModel.getLocalidade());
        pessoaEnderecoOrig.setUf(pessoaFisicaModel.getUf());
        pessoaEnderecoOrig.setEstado(pessoaFisicaModel.getEstado());
        pessoaEnderecoOrig.setRegiao(pessoaFisicaModel.getRegiao());
        pessoaEnderecoOrig.setIbge(pessoaFisicaModel.getIbge());
        pessoaEnderecoOrig.setGia(pessoaFisicaModel.getGia());
        pessoaEnderecoOrig.setDdd(pessoaFisicaModel.getDdd());
        pessoaEnderecoOrig.setSiafi(pessoaFisicaModel.getSiafi());

        return Optional.of(pessoaEnderecoRepository.save(pessoaEnderecoOrig));
    }

    public boolean deleteEndereco(Integer id) {
        try {
            var opt = pessoaEnderecoRepository.findById(id);
            if (opt.isEmpty()) {
                return false;
            }

            pessoaEnderecoRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
