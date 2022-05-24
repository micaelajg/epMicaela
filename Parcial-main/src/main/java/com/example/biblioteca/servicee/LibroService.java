/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.biblioteca.servicee;

import com.example.biblioteca.model.Libro;
import java.util.List;


public interface LibroService {
    int create(Libro li);
    int update(Libro li);
    int delete(int id);
    Libro read(int id);
    List<Libro> readAll();
}
