package Pessoas.example.CadastroPessoas.restcontroller;

import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
import Pessoas.example.CadastroPessoas.model.PessoaFisicaModel;
import Pessoas.example.CadastroPessoas.model.PessoaJuridicaModel;
import Pessoas.example.CadastroPessoas.model.PessoaModel;
import Pessoas.example.CadastroPessoas.service.PessoaEnderecoService;
import Pessoas.example.CadastroPessoas.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //Controlllers endereco de pessoa

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
    public ResponseEntity<PessoaEnderecoModel> findEndereco(@PathVariable int id) {

        var pessoaoend = pessoaService.findEndereco(id);

        if (pessoaoend.isPresent()) {
            return ResponseEntity.ok(pessoaoend.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/endereco")
    public ResponseEntity<Void> updEndereco(@PathVariable int id, @RequestBody PessoaEnderecoModel endereco) {
        try{
            var opt = pessoaService.updEndereco(id,  endereco);

            if (opt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex){

            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //Controlllers pessoa pf de pessoa

    @PostMapping("/{id}/pessoapf")
    public ResponseEntity<Void> createPF(@PathVariable int id, @RequestBody PessoaFisicaModel pessoaFisicaModel) {
        try{
            var opt = pessoaService.createPF(id, pessoaFisicaModel);

            if (!opt.toString().isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception ex){

            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/pessoapf")
    public ResponseEntity<PessoaFisicaModel> findPF(@PathVariable int id) {

        var pessoaopf = pessoaService.findPF(id);

        if (pessoaopf.isPresent()) {
            return ResponseEntity.ok(pessoaopf.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/pessoapf")
    public ResponseEntity<Void> updPF(@PathVariable int id, @RequestBody PessoaFisicaModel pessoapf) {
        try{
            var opt = pessoaService.updPF(id, pessoapf);

            if (opt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex){

            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    //Controlllers pessoa pj de pessoa

    @PostMapping("/{id}/pessoapj")
    public ResponseEntity<Void> createPJ(@PathVariable int id, @RequestBody PessoaJuridicaModel pessoaJuridicaModel) {
        try{
            var opt = pessoaService.createPJ(id, pessoaJuridicaModel);

            if (!opt.toString().isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception ex){

            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/pessoapj")
    public ResponseEntity<PessoaJuridicaModel> findPJ(@PathVariable int id) {

        var pessoaopj = pessoaService.findPJ(id);

        if (pessoaopj.isPresent()) {
            return ResponseEntity.ok(pessoaopj.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/pessoapj")
    public ResponseEntity<Void> updPJ(@PathVariable int id, @RequestBody PessoaJuridicaModel pessoapj) {
        try{
            var opt = pessoaService.updPJ(id, pessoapj);

            if (opt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex){

            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    }



