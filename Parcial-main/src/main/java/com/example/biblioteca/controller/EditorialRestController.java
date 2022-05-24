/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.controller;

import com.example.biblioteca.model.Editorial;
import com.example.biblioteca.servicee.EditorialService;
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
@RequestMapping("/edi")
public class EditorialRestController {
    @Autowired
    private EditorialService ediService;
    
     @GetMapping("/all")
    public List<Editorial> getPosts() {
        return ediService.readAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Editorial> getPost(@PathVariable int id) {
        Editorial edi = ediService.read(id);
        return ResponseEntity.ok(edi);
    }
    
    @DeleteMapping("/{id}")
    public int deletePost(@PathVariable int id) {        
        return ediService.delete(id);
    }
    
    @PostMapping("/add")
    public int addPost(@RequestBody Editorial edi) {  
        System.out.println(edi.getNombre());
        return ediService.create(edi);
    }
    @PutMapping("/edit")
    public int editPost(@RequestBody Editorial edi) {  
        Editorial pos = new Editorial(edi.getIdeditorial(),edi.getNombre(),edi.getPais());
        System.out.println(edi.getIdeditorial()+" , "+edi.getNombre()+" , "+edi.getPais());
        return ediService.update(edi);
    }
}
