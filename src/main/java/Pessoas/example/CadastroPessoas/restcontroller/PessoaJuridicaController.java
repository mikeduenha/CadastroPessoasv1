package Pessoas.example.CadastroPessoas.restcontroller;

import Pessoas.example.CadastroPessoas.model.PessoaFisicaModel;
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

    @GetMapping("/{id}")
    public ResponseEntity<PessoaJuridicaModel> findById(@PathVariable int id) {
        var pessoaopj = pessoaJuridicaService.findById(id);

        if (pessoaopj.isPresent()) {
            return ResponseEntity.ok(pessoaopj.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public List<PessoaJuridicaModel> findAll() {return pessoaJuridicaService.listar();}
    }
