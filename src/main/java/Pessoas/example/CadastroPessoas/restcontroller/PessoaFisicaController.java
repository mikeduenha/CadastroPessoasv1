package Pessoas.example.CadastroPessoas.restcontroller;

import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
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

    @GetMapping("/{id}")
    public ResponseEntity<PessoaFisicaModel> findById(@PathVariable int id) {
        var pessoaopf = pessoaFisicaService.findById(id);

        if (pessoaopf.isPresent()) {
            return ResponseEntity.ok(pessoaopf.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public List<PessoaFisicaModel> findAll() {return pessoaFisicaService.listar();}
}
