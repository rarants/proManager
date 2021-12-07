/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rarantes.ProManager.Controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rarantes.ProManager.Models.Pessoa;
import rarantes.ProManager.Repositories.PessoaRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Raíssa
 */
@Api(value = "Swagger2RestController", description = "REST APIs realacionada aos CRUD de PESSOAS!")
@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    // @RequestMapping("/get") fica //http://localhost:8080/pessoa/get
    /*
    @GetMapping
    public String listar(){
        return "Testando API com Spring Boot";
    }
    */
    @Autowired
    private PessoaRepository pessoaRepository;
    
    // index
    // Iterable.class = precisa pois retorna pessoas 
    @ApiOperation(
            value = "Retorna todas as pessoas  cadastradas", 
            response = Iterable.class)
    @GetMapping
    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }
    
    // show
    @ApiOperation(
            value = "Retorna uma pessoa conforme o id (int) recebendo na URL (@PathVariable)", 
            response = Iterable.class)
    @GetMapping("/{id}")
    public Optional<Pessoa> listarPessoa(@PathVariable Long id) {
        try {
            return pessoaRepository.findById(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    // create
    @ApiOperation(
            value = "Cadastra uma nova pessoa na base de dados!", 
            response = Iterable.class)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa cadastrar(@RequestBody Pessoa pessoa) {
        System.out.println(pessoa.getNome());
        return pessoaRepository.save(pessoa);
    }
    
    // destroy
    @ApiOperation(
            value = "Exclusão da pessoa pelo id informado (@PathVariable)!", 
            response = Iterable.class)
    @DeleteMapping("/{id}")
    public void deletarPessoa(@PathVariable Long id) {
        System.out.println("Delete pessoa = " + id);
        pessoaRepository.deleteById(id);
    }
    
    // update
    @ApiOperation(
            value = "Altera uma pessoa, recebendo os dados da pessoa no RequestBody e o id no @PathVariable", 
            response = Iterable.class)
    @PutMapping("/{id}")
    public Pessoa updatePessoa(@RequestBody Pessoa pessoa, @PathVariable Long id) {
        Pessoa p = pessoaRepository.getOne(id);
        p.setNome(pessoa.getNome());
        
        return pessoaRepository.save(p);
    }
}
