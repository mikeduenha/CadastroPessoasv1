package Pessoas.example.CadastroPessoas.restcontroller;

import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
import Pessoas.example.CadastroPessoas.model.PessoaModel;
import Pessoas.example.CadastroPessoas.restcontroller.dto.EnderecoResponseDto;
import Pessoas.example.CadastroPessoas.service.PessoaEnderecoService;
import Pessoas.example.CadastroPessoas.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

    private final PessoaService pessoaService;
    private final PessoaEnderecoService pessoaEnderecoService;

    public PessoaController(PessoaService pessoaService, PessoaEnderecoService pessoaEnderecoService) {
        this.pessoaService = pessoaService;
        this.pessoaEnderecoService = pessoaEnderecoService;
    }

    @GetMapping("/")
    public List<PessoaModel> findAll() {return pessoaService.listar();}


    @PostMapping("/")
    public ResponseEntity<Void> addPessoa(@RequestBody PessoaModel pessoa) {
        try{
            pessoaService.addPessoa(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaModel> findById(@PathVariable int id) {
        var pessoaopt = pessoaService.findById(id);

        if (pessoaopt.isPresent()) {
            return ResponseEntity.ok(pessoaopt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/celular/{celular}")
    public ResponseEntity<PessoaModel> findByCelular(@PathVariable String celular) {
        var pessoaopt = pessoaService.findByCelular(celular);

        if (pessoaopt.isPresent()) {
            return ResponseEntity.ok(pessoaopt.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<PessoaModel> findByEmail(@PathVariable String email) {
        var pessoaopt = pessoaService.findByCelular(email);

        if (pessoaopt.isPresent()) {
            return ResponseEntity.ok(pessoaopt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updPessoa(@PathVariable int id, @RequestBody PessoaModel pessoa) {
        try{
            var opt = pessoaService.updPessoa(id,  pessoa);

            if (opt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex){

            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable int id) {
        try{
            var opt = pessoaService.deletePessoa(id);

            if (!opt) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex){

            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/endereco")
    public ResponseEntity<Void> createEndereco(@PathVariable int id, @RequestBody PessoaEnderecoModel pessoaEnderecoModel) {
        try{
            var opt = pessoaService.createEndereco(id, pessoaEnderecoModel);

            if (!opt.toString().isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception ex){

            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/endereco")
    public ResponseEntity<List<EnderecoResponseDto>> findByEndereco(@PathVariable int id) {

        var pessoaoend = pessoaService.listEndereco(id);

        return ResponseEntity.ok(pessoaoend);
    }

}
