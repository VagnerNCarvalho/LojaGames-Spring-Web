package com.vagnercarvalho.lojagames_backend.controllers;

import com.vagnercarvalho.lojagames_backend.entities.Jogo;
import com.vagnercarvalho.lojagames_backend.repositories.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/jogos")
public class JogoController {
    @Autowired
    private JogoRepository repository;
    // GET  /jogos   => retornar todos os jogos
    @GetMapping
    public List<Jogo> listarTodosOsJogos(){
        return repository.findAll();
    }

    //Get /jogos/id => retornar o jogo com o id especificado
    @GetMapping("/{id}")
    public ResponseEntity<Jogo> getJogoPorId(@PathVariable Long id){
        try {
            return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
        } catch(NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST  /jogos  => adicionar um novo jogo
    @PostMapping
    public Jogo adicionarJogo(@RequestBody Jogo novo){
        return repository.save(novo);
    }

    //DELETE  /jogos/id  => remover com o id especificado
    @DeleteMapping("/{id}")
    public ResponseEntity removerPorId(@PathVariable Long id){
        try {
            repository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException ex){
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
    }

    //ALTERAR  /jogos/id  => alterar com o id especificado
    @PutMapping("/{id}")
    public ResponseEntity <Jogo> alterarJogoPorId(@PathVariable Long id, @RequestBody Jogo novosDados){
        try {
            //#1 - pegar um jogo do banco de dados pelo id
            Jogo jogoAntigo = repository.findById(id).get();
            //#2 - altero os dados que eu quero
            jogoAntigo.setNome(novosDados.getNome());
            jogoAntigo.setDistribuidora(novosDados.getDistribuidora());
            jogoAntigo.setPlataforma(novosDados.getPlataforma());
            //#3 - salvo
            return new ResponseEntity<>(repository.save(jogoAntigo), HttpStatus.OK);
        } catch(NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
