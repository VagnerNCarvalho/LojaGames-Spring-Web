package com.vagnercarvalho.lojagames_backend.controllers;

import com.vagnercarvalho.lojagames_backend.entities.Cliente;
import com.vagnercarvalho.lojagames_backend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository repository;
    // GET  /clientes   => retornar todos os clientes
    @GetMapping
    public List<Cliente> listarTodosOsClientes(){
        return repository.findAll();
    }

    //Get /clientes/id => retornar o cliente com o id especificado
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientePorId(@PathVariable Long id){
        try {
            return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
        } catch(NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST  /clientes  => adicionar um novo cliente
    @PostMapping
    public Cliente adicionarCliente(@RequestBody Cliente novo){
        return repository.save(novo);
    }

    //DELETE  /clientes/id  => remover com o id especificado
    @DeleteMapping("/{id}")
    public ResponseEntity removerPorId(@PathVariable Long id){
        try {
            repository.deleteById(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException ex){
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }
    }

    //ALTERAR  /clientes/id  => alterar com o id especificado
    @PutMapping("/{id}")
    public ResponseEntity <Cliente> alterarClientePorId(@PathVariable Long id, @RequestBody Cliente novosDados){
        try {
            //#1 - pegar um cliente do banco de dados pelo id
            Cliente clienteAntigo = repository.findById(id).get();
            //#2 - altero os dados que eu quero
            clienteAntigo.setNome(novosDados.getNome());
            clienteAntigo.setCpf(novosDados.getCpf());
            //#3 - salvo
            return new ResponseEntity<>(repository.save(clienteAntigo), HttpStatus.OK);
        } catch(NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
