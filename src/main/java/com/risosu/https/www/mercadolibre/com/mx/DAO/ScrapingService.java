/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.risosu.https.www.mercadolibre.com.mx.DAO;

import com.risosu.https.www.mercadolibre.com.mx.ML.Producto;
import com.risosu.https.www.mercadolibre.com.mx.ML.Result;
import com.risosu.https.www.mercadolibre.com.mx.Scraper.MercadoLibreScraper;
import java.io.IOException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alien 13
 */
@Service
public class ScrapingService {

    private final MercadoLibreScraper scraper;
    private final ProductoConexion productoConexion;

    public ScrapingService(MercadoLibreScraper scraper, ProductoConexion productoConexion) {
        this.scraper = scraper;
        this.productoConexion = productoConexion;
    }

    public Result<Producto> scrapeAndSave(String termino) throws IOException {
        Result<Producto> result = scraper.scrape(termino);
        productoConexion.insertarProductos(result.objects); 
        return result;
    }
}

