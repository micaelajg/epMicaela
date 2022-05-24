/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.biblioteca.dao;

import com.example.biblioteca.model.Autor;
import java.util.List;
public interface AutorDao {
    int create(Autor au);
    int update(Autor au);
    int delete(int id);
    Autor read(int id);
    List<Autor> readAll();
}
