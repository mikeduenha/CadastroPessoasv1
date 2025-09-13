package Pessoas.example.CadastroPessoas.restcontroller;

import Pessoas.example.CadastroPessoas.model.PessoaFisicaModel;
import Pessoas.example.CadastroPessoas.service.PessoaFisicaService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoafisica")
public class PessoaFisicaController {
    private final PessoaFisicaService pessoaFisicaService;

    public PessoaFisicaController(PessoaFisicaService pessoaFisicaService) {
        this.pessoaFisicaService = pessoaFisicaService;
    }

    @GetMapping("/")
    public List<PessoaFisicaModel> findAll() {return pessoaFisicaService.listar();}

    @GetMapping("/{id}")
    public ResponseEntity<PessoaFisicaModel> findById(@PathVariable int id) {
        var pessoapfopt = pessoaFisicaService.findById(id);

        if (pessoapfopt.isPresent()) {
            return ResponseEntity.ok(pessoapfopt.get());
        }
       return ResponseEntity.notFound().build();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PessoaFisicaModel> findByCpf(@PathVariable String cpf) {
        var pessoapfopt = pessoaFisicaService.findByCpf(cpf);

        if (pessoapfopt.isPresent()) {
            return ResponseEntity.ok(pessoapfopt.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/celular/{celular}")
    public ResponseEntity<PessoaFisicaModel> findByCelular(@PathVariable String celular) {
        var pessoapfopt = pessoaFisicaService.findByCelular(celular);

        if (pessoapfopt.isPresent()) {
            return ResponseEntity.ok(pessoapfopt.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/")
    public ResponseEntity<Void> addPessoa(@RequestBody PessoaFisicaModel pessoapf) {
      try{
          pessoaFisicaService.addPessoa(pessoapf);
          return ResponseEntity.status(HttpStatus.CREATED).build();
      } catch (Exception ex){
          ex.printStackTrace();
          return ResponseEntity.notFound().build();
      }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updPessoa(@PathVariable int id, @RequestBody PessoaFisicaModel pessoapf) {
        try{
            var opt = pessoaFisicaService.updPessoa(id,  pessoapf);

            if (opt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex){

            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable int id) {
        try{
            var opt = pessoaFisicaService.deletePessoa(id);

            if (!opt) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex){

            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
