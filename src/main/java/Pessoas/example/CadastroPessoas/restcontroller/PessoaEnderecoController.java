package Pessoas.example.CadastroPessoas.restcontroller;

import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
import Pessoas.example.CadastroPessoas.model.PessoaModel;
import Pessoas.example.CadastroPessoas.service.PessoaEnderecoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endereco")
public class PessoaEnderecoController {
    private final PessoaEnderecoService pessoaEnderecoService;

    public PessoaEnderecoController(PessoaEnderecoService pessoaEnderecoService) {
        this.pessoaEnderecoService = pessoaEnderecoService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PessoaEnderecoModel> findById(@PathVariable int id) {
        var pessoaoend = pessoaEnderecoService.findById(id);

        if (pessoaoend.isPresent()) {
            return ResponseEntity.ok(pessoaoend.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/")
    public List<PessoaEnderecoModel> findAll() {return pessoaEnderecoService.listar();}
}
