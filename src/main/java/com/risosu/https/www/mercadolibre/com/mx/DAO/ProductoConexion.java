/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.risosu.https.www.mercadolibre.com.mx.DAO;

import com.risosu.https.www.mercadolibre.com.mx.ML.Producto;
import com.risosu.https.www.mercadolibre.com.mx.ML.Result;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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
        if (productos == null || productos.isEmpty()) {
            System.out.println("No hay productos para insertar.");
            return;
        }

        String sql = "{CALL insertar_producto(?, ?)}";

        try (Connection connection = dataSource.getConnection(); CallableStatement cs = connection.prepareCall(sql)) {

            for (Producto producto : productos) {
                if (producto.getNombre() == null || producto.getUrl() == null) {
                    System.out.println("Producto inválido, se omitirá: " + producto);
                    continue;
                }

                cs.setString(1, producto.getNombre());
                cs.setString(2, producto.getUrl());
                cs.addBatch();
            }

            int[] resultados = cs.executeBatch();
            System.out.println("Se insertaron " + resultados.length + " productos usando la SP.");

        } catch (SQLException e) {
            System.err.println("Error al insertar productos usando la SP:");
            e.printStackTrace();
        }
    }

}
