/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.risosu.https.www.mercadolibre.com.mx.DAO;

import com.risosu.https.www.mercadolibre.com.mx.ML.Producto;
import com.risosu.https.www.mercadolibre.com.mx.ML.Result;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.jsoup.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoConexion implements ProductoInterface {

    @Autowired
    private DataSource dataSource; 

    @Override
    public Result DarProductos() {
        throw new UnsupportedOperationException("No implementado aún");
    }

    public void insertarProductos(List<Producto> productos) {
        String sql = "INSERT INTO producto (nombre, url) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            for (Producto producto : productos) {
                ps.setString(1, producto.getNombre());
                ps.setString(2, producto.getUrl());
                ps.addBatch(); // Agrega al lote
            }

            ps.executeBatch(); 

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

