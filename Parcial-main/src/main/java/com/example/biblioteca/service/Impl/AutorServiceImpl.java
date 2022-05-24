/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.service.Impl;


import com.example.biblioteca.dao.AutorDao;
import com.example.biblioteca.model.Autor;
import com.example.biblioteca.servicee.AutorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServiceImpl implements AutorService {
    
    @Autowired
    private AutorDao autorDao;
    
    @Override
    public int create(Autor au) {
        return autorDao.create(au);
    }

    @Override
    public int update(Autor au) {
        return autorDao.update(au);
    }

    @Override
    public int delete(int id) {
       return autorDao.delete(id); 
    }

    @Override
    public Autor read(int id) {
        return autorDao.read(id);
    }

    @Override
    public List<Autor> readAll() {
     return autorDao.readAll();
    }
    
}
