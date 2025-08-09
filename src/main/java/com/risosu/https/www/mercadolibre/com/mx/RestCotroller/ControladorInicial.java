/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.risosu.https.www.mercadolibre.com.mx.RestCotroller;

import com.risosu.https.www.mercadolibre.com.mx.DAO.ProductoService;
import com.risosu.https.www.mercadolibre.com.mx.DAO.ScrapingService;
import com.risosu.https.www.mercadolibre.com.mx.ML.Producto;
import com.risosu.https.www.mercadolibre.com.mx.ML.Result;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alien 13
 */
@RestController
@RequestMapping("/demo")
public class ControladorInicial {

    @Autowired
    ProductoService ProductoService;

    @Autowired
    private ScrapingService scrapingService;

    @GetMapping("/scrape")
    public ResponseEntity<Result<Producto>> scrapeGet(@RequestParam String q) {
        try {
            Result<Producto> result = scrapingService.scrapeAndSave(q);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.getLocalizedMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
