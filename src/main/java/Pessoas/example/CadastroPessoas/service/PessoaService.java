package Pessoas.example.CadastroPessoas.service;

import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
import Pessoas.example.CadastroPessoas.model.PessoaFisicaModel;
import Pessoas.example.CadastroPessoas.model.PessoaJuridicaModel;
import Pessoas.example.CadastroPessoas.model.PessoaModel;
import Pessoas.example.CadastroPessoas.repository.PessoaEnderecoRepository;
import Pessoas.example.CadastroPessoas.repository.PessoaFisicaRepository;
import Pessoas.example.CadastroPessoas.repository.PessoaJuridicaRepository;
import Pessoas.example.CadastroPessoas.repository.PessoaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private PessoaEnderecoRepository pessoaEnderecoRepository;

    public PessoaService(PessoaRepository pessoaRepository, PessoaEnderecoRepository pessoaEnderecoRepository, PessoaFisicaRepository pessoaFisicaRepository, PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    public void addPessoa(PessoaModel pessoaModel){pessoaRepository.save(pessoaModel);}
    public List<PessoaModel> listar(){return pessoaRepository.findAll();}
    public Optional<PessoaModel> findById(Integer id){return pessoaRepository.findById(id);}
    public Optional<PessoaModel> findByCelular(String id){return pessoaRepository.findByCelular(id);}
    public Optional<PessoaModel> updPessoa(Integer id, PessoaModel pessoaModel) {
        var optional = findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        var pessoaloriginal = optional.get();
        pessoaloriginal.setNome(pessoaModel.getNome());
        pessoaloriginal.setCelular(pessoaModel.getCelular());
        pessoaloriginal.setEmail(pessoaModel.getEmail());
        pessoaloriginal.setTelefone(pessoaModel.getTelefone());

        return Optional.of(pessoaRepository.save(pessoaloriginal)) ;
    }

    public boolean deletePessoa(Integer id) {
        try{
            var optional = pessoaRepository.findById(id);

            if (optional.isEmpty()) {
                return false;
            }
            pessoaRepository.deleteById(id);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    //Metodos Endereço da Pessoa
    public PessoaEnderecoModel createEndereco(int id, PessoaEnderecoModel pessoaEnderecoModel) {

        var optional = findById(id);

        if (optional.isEmpty()) {
            return null;
        }
        var pessoa = optional.get();

        PessoaEnderecoModel pessoaEndereco = new PessoaEnderecoModel();

        pessoaEndereco.setPessoa(pessoa);
        pessoaEndereco.setCep(pessoaEnderecoModel.getCep());
        pessoaEndereco.setLogradouro(pessoaEnderecoModel.getLogradouro());
        pessoaEndereco.setComplemento(pessoaEnderecoModel.getComplemento());
        pessoaEndereco.setUnidade(pessoaEnderecoModel.getUnidade());
        pessoaEndereco.setBairro(pessoaEnderecoModel.getBairro());
        pessoaEndereco.setLocalidade(pessoaEnderecoModel.getLocalidade());
        pessoaEndereco.setUf(pessoaEnderecoModel.getUf());
        pessoaEndereco.setEstado(pessoaEnderecoModel.getEstado());
        pessoaEndereco.setRegiao(pessoaEnderecoModel.getRegiao());
        pessoaEndereco.setIbge(pessoaEnderecoModel.getIbge());
        pessoaEndereco.setGia( pessoaEnderecoModel.getGia());
        pessoaEndereco.setDdd(pessoaEnderecoModel.getDdd());
        pessoaEndereco.setSiafi(pessoaEnderecoModel.getSiafi());

        return pessoaEnderecoRepository.save(pessoaEndereco);
    }

    public Optional<PessoaEnderecoModel> findEndereco(int id) {

        var pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

         var pessoaend = pessoaEnderecoRepository.findByPessoa(pessoa);
      return pessoaend ;
    }

    public Optional<PessoaEnderecoModel> updEndereco(Integer id, PessoaEnderecoModel pessoaEnderecoModel) {
        var optional = findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        var pessoa = optional.get();

        var pessoaEndereco = pessoaEnderecoRepository.findByPessoa(pessoa);
        var endereco = pessoaEndereco.get();

        endereco.setPessoa(pessoa);
        endereco.setCep(pessoaEnderecoModel.getCep());
        endereco.setLogradouro(pessoaEnderecoModel.getLogradouro());
        endereco.setComplemento(pessoaEnderecoModel.getComplemento());
        endereco.setUnidade(pessoaEnderecoModel.getUnidade());
        endereco.setBairro(pessoaEnderecoModel.getBairro());
        endereco.setLocalidade(pessoaEnderecoModel.getLocalidade());
        endereco.setUf(pessoaEnderecoModel.getUf());
        endereco.setEstado(pessoaEnderecoModel.getEstado());
        endereco.setRegiao(pessoaEnderecoModel.getRegiao());
        endereco.setIbge(pessoaEnderecoModel.getIbge());
        endereco.setGia( pessoaEnderecoModel.getGia());
        endereco.setDdd(pessoaEnderecoModel.getDdd());
        endereco.setSiafi(pessoaEnderecoModel.getSiafi());

        return Optional.of(pessoaEnderecoRepository.save(endereco)) ;
    }

    //Metodos Pessoa Fisica da Pessoa
    public PessoaFisicaModel createPF(int id, PessoaFisicaModel pessoaFisicaModel) {

        var optional = findById(id);

        if (optional.isEmpty()) {
            return null;
        }
        var pessoa = optional.get();

        PessoaFisicaModel pessoaFisica = new PessoaFisicaModel();
        pessoaFisica.setPessoa(pessoa);
        pessoaFisica.setCpf(pessoaFisicaModel.getCpf());
        pessoaFisica.setRg(pessoaFisicaModel.getRg());
        pessoaFisica.setDatanasc(pessoaFisicaModel.getDatanasc());

        return pessoaFisicaRepository.save(pessoaFisica);
    }

    public Optional<PessoaFisicaModel> findPF(int id) {
        var pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var pessoapf = pessoaFisicaRepository.findByPessoa(pessoa);
        return pessoapf ;
    }

    public Optional<PessoaFisicaModel> updPF(Integer id, PessoaFisicaModel pessoaFisicaModel) {
        var optional = findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        var pessoa = optional.get();

        var pessoaPFRepo = pessoaFisicaRepository.findByPessoa(pessoa);
        var pessoapf = pessoaPFRepo.get();

        pessoapf.setPessoa(pessoa);
        pessoapf.setPessoa(pessoa);
        pessoapf.setCpf(pessoaFisicaModel.getCpf());
        pessoapf.setRg(pessoaFisicaModel.getRg());
        pessoapf.setDatanasc(pessoaFisicaModel.getDatanasc());

        return Optional.of(pessoaFisicaRepository.save(pessoapf));
    }

    //Metodos Pessoa Juridica da Pessoa
    public PessoaJuridicaModel createPJ(int id, PessoaJuridicaModel pessoaJuridicaModel) {

        var optional = findById(id);

        if (optional.isEmpty()) {
            return null;
        }
        var pessoa = optional.get();

        PessoaJuridicaModel pessoaJuridica = new PessoaJuridicaModel();
        pessoaJuridica.setPessoa(pessoa);
        pessoaJuridica.setCnpj(pessoaJuridicaModel.getCnpj());
        pessoaJuridica.setRazaosocial(pessoaJuridicaModel.getRazaosocial());

        return pessoaJuridicaRepository.save(pessoaJuridica);
    }

    public Optional<PessoaJuridicaModel> findPJ(int id) {
        var pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var pessoapj = pessoaJuridicaRepository.findByPessoa(pessoa);
        return pessoapj ;
    }

    public Optional<PessoaJuridicaModel> updPJ(Integer id, PessoaJuridicaModel pessoaJuridicaModel) {
        var optional = findById(id);
        if (optional.isEmpty()) {
            return Optional.empty();
        }
        var pessoa = optional.get();

        var pessoaPJRepo = pessoaJuridicaRepository.findByPessoa(pessoa);
        var pessoapj = pessoaPJRepo.get();

        pessoapj.setPessoa(pessoa);
        pessoapj.setCnpj(pessoaJuridicaModel.getCnpj());
        pessoapj.setRazaosocial(pessoaJuridicaModel.getRazaosocial());

        return Optional.of(pessoaJuridicaRepository.save(pessoapj));
    }
}
