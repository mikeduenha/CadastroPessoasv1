package Pessoas.example.CadastroPessoas.restcontroller;

import Pessoas.example.CadastroPessoas.model.PessoaJuridicaModel;
import Pessoas.example.CadastroPessoas.service.PessoaJuridicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoajuridica")
public class PessoaJuridicaController {
    private final PessoaJuridicaService pessoaJuridicaService;

    public PessoaJuridicaController(PessoaJuridicaService pessoaJuridicaService){
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @GetMapping("/")
    public List<PessoaJuridicaModel> findAll(){return  pessoaJuridicaService.listar();}

    @PostMapping("/")
    public ResponseEntity<Void> addPessoa(@RequestBody PessoaJuridicaModel pessoapj){
        try{
            pessoaJuridicaService.addPessoa(pessoapj);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex){
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
            }
    }
    @GetMapping("/{id}")
    public ResponseEntity<PessoaJuridicaModel>findPessoaById(@PathVariable Integer id){
        var pessoaoptpj = pessoaJuridicaService.findById(id);

        if(pessoaoptpj.isPresent()) {
            return ResponseEntity.ok(pessoaoptpj.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<PessoaJuridicaModel>findByCnpj(@PathVariable String cnpj){
        var pessoaoptpj = pessoaJuridicaService.findByCnpj(cnpj);

        if(pessoaoptpj.isPresent()) {
            return ResponseEntity.ok(pessoaoptpj.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<PessoaJuridicaModel>findPessoaByEmail(@PathVariable String email){
        var pessoaoptpj = pessoaJuridicaService.findByCelular(email);

        if(pessoaoptpj.isPresent()) {
            return ResponseEntity.ok(pessoaoptpj.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updPessoa(@PathVariable int id, @RequestBody PessoaJuridicaModel pessoajuridica){
        try{
            var opt = pessoaJuridicaService.updPessoa(id, pessoajuridica);

            if (opt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable int id){
        try{
            var opt = pessoaJuridicaService.deletePessoa(id);

            if (!opt) {
                return ResponseEntity.notFound().build();
        }
            return ResponseEntity.status(HttpStatus.OK).build();

    } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        }
    }
