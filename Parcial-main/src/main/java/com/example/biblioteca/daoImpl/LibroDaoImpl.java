/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.daoImpl;

import com.example.biblioteca.dao.LibroDao;

import com.example.biblioteca.model.Libro;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LibroDaoImpl implements LibroDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(Libro li) {
        String SQL = "INSERT INTO libro(titulo, idioma, paginas, descripcion, idautor, ideditorial) VALUES(?,?,?,?,?,?)";
        return jdbcTemplate.update(SQL, new Object[]{li.getTitulo(), li.getIdioma(), li.getPaginas(), li.getDescripcion(), li.getIdautor(), li.getIdeditorial()});
    }

    @Override
    public int update(Libro li) {
        String SQL = "UPDATE libro SET titulo=?, idioma=?, paginas=?, descripcion=?, idautor=?, ideditorial=? WHERE idlibro=?";
        return jdbcTemplate.update(SQL, new Object[]{li.getTitulo(), li.getIdioma(), li.getPaginas(), li.getDescripcion(), li.getIdautor(), li.getIdeditorial(), li.getIdlibro()});
    }

    @Override
    public int delete(int id) {
        String SQL = "DELETE FROM libro WHERE idlibro=?";
        return jdbcTemplate.update(SQL, id);
    }

    @Override
    public Libro read(int id) {
        String SQL = "SELECT * FROM libro WHERE idlibro=?";
        try {
            Libro li = jdbcTemplate.queryForObject(SQL, BeanPropertyRowMapper.newInstance(Libro.class), id);
            return li;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Libro> readAll() {
        String SQL = "SELECT idlibro, titulo, idioma, paginas, descripcion, idautor, ideditorial  FROM libro ORDER BY idlibro ASC";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Libro.class));
    }

}
