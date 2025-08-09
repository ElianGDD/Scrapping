package com.risosu.https.www.mercadolibre.com.mx.Scraper;

import com.risosu.https.www.mercadolibre.com.mx.ML.Producto;
import com.risosu.https.www.mercadolibre.com.mx.ML.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jsoup.nodes.Element;

@Component
public class MercadoLibreScraper {

    public Result<Producto> scrape(String terminoBusqueda) throws IOException {
        Result<Producto> result = new Result<>();
        Set<String> urlsUnicas = new HashSet<>();


        Document document = Jsoup.connect("https://listado.mercadolibre.com.mx/" + terminoBusqueda).get();
        

        List<Element> items = document.getElementsByClass("poly-card");

        if (items.isEmpty()) {
            System.out.println("No se encontraron elementos con la clase 'ui-search-layout__item'");
            result.correct = false;
            result.errorMessage = "No se encontraron productos en la búsqueda.";
            return result;
        }

        int encontrados = 0;
        for (Element item : items) {
            Element linkElement = item.selectFirst("a.poly-component__title");
            if (linkElement == null) {
                continue;
            }

            String titulo = linkElement.text().trim();
            String link = linkElement.absUrl("href");

            if (urlsUnicas.add(link)) {
                Producto producto = new Producto();
                producto.setNombre(titulo);
                producto.setUrl(link);

                result.objects.add(producto);
                encontrados++;
            }
        }

        if (encontrados == 0) {
            System.out.println("Se encontraron productos pero no se extrajo ningún título/URL.");
            result.correct = false;
            result.errorMessage = "No se pudieron extraer los datos de los productos.";
            return result;
        }

        System.out.println("Productos extraídos: " + encontrados);
        result.correct = true;
        return result;
    }
}
