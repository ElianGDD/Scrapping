package com.risosu.https.www.mercadolibre.com.mx.Scraper;

import com.risosu.https.www.mercadolibre.com.mx.ML.Producto;
import com.risosu.https.www.mercadolibre.com.mx.ML.Result;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MercadoLibreScraper {

    public Result scrape(String terminoBusqueda) throws IOException {
        Result result = new Result();
        String url = "https://www.mercadolibre.com.mx/"
                + "busqueda?q=" + terminoBusqueda;

        Document doc = Jsoup.connect(url).get();
        Elements titulos = doc.select(".ui-search-item__title");
        Elements enlaces = doc.select("a.ui-search-item__group__element");

        for (int i = 0; i < Math.min(titulos.size(), enlaces.size()); i++) {
            String titulo = titulos.get(i).text();
            String link = enlaces.get(i).attr("href");

            Producto producto = new Producto(); 
            producto.setNombre(titulo);
            producto.setUrl(link);

            result.objects.add(producto); 
        }

        return result;
    }
}
