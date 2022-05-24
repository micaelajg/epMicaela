/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.service.Impl;

import com.example.biblioteca.dao.LibroDao;
import com.example.biblioteca.model.Libro;
import com.example.biblioteca.servicee.LibroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroDao libroDao;

    @Override
    public int create(Libro li) {
        return libroDao.create(li);
    }

    @Override
    public int update(Libro li) {
        return libroDao.update(li);
    }

    @Override
    public int delete(int id) {
        return libroDao.delete(id);
    }

    @Override
    public Libro read(int id) {
        return libroDao.read(id);
    }

    @Override
    public List<Libro> readAll() {
        return libroDao.readAll();
    }

}
