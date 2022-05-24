/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.controller;



import com.example.biblioteca.model.Libro;
import com.example.biblioteca.servicee.LibroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/li")
public class LibroRestController {
    @Autowired
    private LibroService liService;
    
     @GetMapping("/all")
    public List<Libro> getPosts() {
        return liService.readAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getPost(@PathVariable int id) {
        Libro edi = liService.read(id);
        return ResponseEntity.ok(edi);
    }
    
    @DeleteMapping("/{id}")
    public int deletePost(@PathVariable int id) {        
        return liService.delete(id);
    }
    
    @PostMapping("/add")
    public int addPost(@RequestBody Libro li) {  
        System.out.println(li.getTitulo());
        return liService.create(li);
    }
    
    @PutMapping("/edit")
    public int editPost(@RequestBody Libro li) {  
        Libro pos = new Libro(li.getIdlibro(),li.getTitulo(),li.getIdioma(),li.getPaginas(),li.getDescripcion(),li.getIdautor(),li.getIdeditorial());
        System.out.println(li.getIdlibro()+" , "+li.getTitulo()+" , "+li.getIdioma()+" , "+li.getPaginas()+" , "+li.getDescripcion()+" , "+li.getIdautor()+" , "+li.getIdeditorial());
        return liService.update(li);
    }
    
}
