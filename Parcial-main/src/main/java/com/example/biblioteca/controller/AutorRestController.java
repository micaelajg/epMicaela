/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.controller;

import com.example.biblioteca.model.Autor;
import com.example.biblioteca.servicee.AutorService;
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
@RequestMapping("/au")

public class AutorRestController {
    @Autowired
    private AutorService auService;
    
    
     @GetMapping("/all")
    public List<Autor> getPosts() {
        return auService.readAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Autor> getPost(@PathVariable int id) {
        Autor au = auService.read(id);
        return ResponseEntity.ok(au);
    }
    @DeleteMapping("/{id}")
    public int deletePost(@PathVariable int id) {        
        return auService.delete(id);
    }
    
    @PostMapping("/add")
    public int addPost(@RequestBody Autor au) {  
        System.out.println(au.getNombre());
        return auService.create(au);
    }
    @PutMapping("/edit")
    public int editPost(@RequestBody Autor au) {  
        Autor pos = new Autor(au.getIdautor(),au.getNombre(),au.getApellido(),au.getCorreo());
        System.out.println(au.getIdautor()+" , "+au.getNombre()+" , "+au.getApellido()+" , "+au.getCorreo());
        return auService.update(au);
    }
    
}
