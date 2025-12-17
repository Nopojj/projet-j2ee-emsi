package com.emsi.gestion_vente.service;

import com.emsi.gestion_vente.dto.ProduitStockDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class StockServiceClient {

    private final RestTemplate restTemplate;

    @Value("${stock.service.url:http://localhost:8082}")
    private String stockServiceUrl;

    public StockServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProduitStockDTO> getAllStock() {
        try {
            String url = stockServiceUrl + "/api/stocks";
            ResponseEntity<List<ProduitStockDTO>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ProduitStockDTO>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Erreur appel gestion-stock: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean soustraireStock(Integer codePdt, Integer qteCmd) {
        try {
            String url = stockServiceUrl + "/api/stocks/soustraire";
            var request = Map.of("codePdt", codePdt, "qteCmd", qteCmd);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.err.println("Erreur soustraction stock: " + e.getMessage());
            return false;
        }
    }
}