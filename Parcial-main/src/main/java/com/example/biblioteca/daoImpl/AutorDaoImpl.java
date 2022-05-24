/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.daoImpl;

import com.example.biblioteca.dao.AutorDao;
import com.example.biblioteca.model.Autor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AutorDaoImpl implements AutorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(Autor au) {
        String SQL = "INSERT INTO autor(nombre, apellido, correo) VALUES(?,?,?)";
        return jdbcTemplate.update(SQL, new Object[]{au.getNombre(), au.getApellido(), au.getCorreo()});
    }

    @Override
    public int update(Autor au) {
        String SQL = "UPDATE autor SET nombre=?, apellido=?, correo=? WHERE idautor=?";
        return jdbcTemplate.update(SQL, new Object[]{au.getNombre(), au.getApellido(), au.getCorreo(), au.getIdautor()});
    }

    @Override
    public int delete(int id) {
        String SQL = "DELETE FROM autor WHERE idautor=?";
        return jdbcTemplate.update(SQL, id);
    }

    @Override
    public Autor read(int id) {
        String SQL = "SELECT * FROM autor WHERE idautor=?";
        try {
            Autor au = jdbcTemplate.queryForObject(SQL, BeanPropertyRowMapper.newInstance(Autor.class), id);
            return au;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Autor> readAll() {
       String SQL ="SELECT idautor, nombre, apellido, correo  FROM autor ORDER BY idautor ASC";        
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Autor.class));
    }

}
