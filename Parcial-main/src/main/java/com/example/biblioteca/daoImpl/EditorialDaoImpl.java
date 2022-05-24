/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.biblioteca.daoImpl;

import com.example.biblioteca.dao.EditorialDao;
import com.example.biblioteca.model.Editorial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EditorialDaoImpl implements EditorialDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(Editorial edi) {
        String SQL = "INSERT INTO editorial(nombre, pais) VALUES(?,?)";
        return jdbcTemplate.update(SQL, new Object[]{edi.getNombre(), edi.getPais()});
    }

    @Override
    public int update(Editorial edi) {
        String SQL = "UPDATE editorial SET nombre=?, pais=? WHERE ideditorial=?";
        return jdbcTemplate.update(SQL, new Object[]{edi.getNombre(), edi.getPais(), edi.getIdeditorial()});
    }

    @Override
    public int delete(int id) {
    String SQL ="DELETE FROM editorial WHERE ideditorial=?"; 
        return jdbcTemplate.update(SQL,id);    
    }

    @Override
    public Editorial read(int id) {
        String SQL ="SELECT *FROM editorial WHERE ideditorial=?";
        try {
            Editorial edi = jdbcTemplate.queryForObject(SQL, BeanPropertyRowMapper.newInstance(Editorial.class),id);
            return edi;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Editorial> readAll() {
        String SQL ="SELECT ideditorial, nombre, pais FROM editorial ORDER BY ideditorial ASC";        
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Editorial.class));
    }

}
