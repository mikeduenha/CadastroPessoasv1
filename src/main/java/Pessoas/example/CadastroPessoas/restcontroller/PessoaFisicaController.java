package Pessoas.example.CadastroPessoas.restcontroller;

import Pessoas.example.CadastroPessoas.model.PessoaFisicaModel;
import Pessoas.example.CadastroPessoas.service.PessoaFisicaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PessoaFisicaModel findById(@PathVariable int id) {return null;}

}
