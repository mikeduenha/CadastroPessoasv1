package Pessoas.example.CadastroPessoas.restcontroller;


import Pessoas.example.CadastroPessoas.model.PessoaEnderecoModel;
import Pessoas.example.CadastroPessoas.service.PessoaEnderecoService;
import Pessoas.example.CadastroPessoas.service.PessoaJuridicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/endereco")
public class PessoaEnderecoController {
    private final PessoaEnderecoService pessoaEnderecoService;

    public PessoaEnderecoController(PessoaEnderecoService pessoaEnderecoService, PessoaJuridicaService pessoaJuridicaService) {
        this.pessoaEnderecoService = pessoaEnderecoService;
    }

    @GetMapping("/")
    public List<PessoaEnderecoModel> findAll(){return pessoaEnderecoService.findAll();}

    @PostMapping("/")
    public ResponseEntity<Void> addEndereco(@RequestBody PessoaEnderecoModel pessoaendereco){
        try{
            pessoaEnderecoService.addEndereco(pessoaendereco);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaEnderecoModel> findById(@PathVariable int id){
        var opt = pessoaEnderecoService.findById(id);
        if(opt.isPresent()){

            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/cep/{id}")
    public ResponseEntity<PessoaEnderecoModel> findByCep(@PathVariable String cep){
        var opt = pessoaEnderecoService.findByCep(cep);
        if(opt.isPresent()){

            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Void> updateEndereco(@PathVariable int id, @RequestBody PessoaEnderecoModel pessoaendereco){
        try{
        var opt = pessoaEnderecoService.updEndereco(id, pessoaendereco);

        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }catch(Exception ex){
        ex.printStackTrace();
        return ResponseEntity.notFound().build();}
    }

    public ResponseEntity<Void> deleteEndereco(@PathVariable int id){
        try{
            var opt= pessoaEnderecoService.deleteEndereco(id);
            if (!opt)
                return ResponseEntity.notFound().build();
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

}
