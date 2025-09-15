package Pessoas.example.CadastroPessoas.service;

import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
import Pessoas.example.CadastroPessoas.model.PessoaModel;
import Pessoas.example.CadastroPessoas.repository.PessoaEnderecoRepository;
import Pessoas.example.CadastroPessoas.repository.PessoaRepository;
import Pessoas.example.CadastroPessoas.restcontroller.dto.EnderecoResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private PessoaEnderecoRepository pessoaEnderecoRepository;

    public PessoaService(PessoaRepository pessoaRepository, PessoaEnderecoRepository pessoaEnderecoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;
    }

    public void addPessoa(PessoaModel pessoaModel){pessoaRepository.save(pessoaModel);}
    public List<PessoaModel> listar(){return pessoaRepository.findAll();}
    public Optional<PessoaModel> findById(Integer id){return pessoaRepository.findById(id);}
    public Optional<PessoaModel> findByCelular(String id){return pessoaRepository.findByCelular(id);}
    public Optional<PessoaModel> findByEmail(String id){return pessoaRepository.findByEmail(id);}

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

    public Object createEndereco(int id, PessoaEnderecoModel pessoaEnderecoModel) {

        var optional = findById(id);

        if (optional.isEmpty()) {
            return Optional.empty();
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

        return Optional.of(pessoaEnderecoRepository.save(pessoaEndereco)) ;

    }

      public List<EnderecoResponseDto> listEndereco(int id) {

        var pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));


      return () ;
    }
}
