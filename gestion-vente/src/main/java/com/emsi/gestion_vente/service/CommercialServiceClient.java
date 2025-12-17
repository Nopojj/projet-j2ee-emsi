package com.emsi.gestion_vente.service;

import com.emsi.gestion_vente.dto.ProduitPrixDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class CommercialServiceClient {

    private final RestTemplate restTemplate;

    @Value("${commercial.service.url:http://localhost:8081}")
    private String commercialServiceUrl;

    public CommercialServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    
    public List<ProduitPrixDTO> getAllProduitsPrix() {
        try {
            String url = commercialServiceUrl + "/api/produits";  

            ResponseEntity<List<ProduitPrixDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProduitPrixDTO>>() {}
            );

            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (Exception e) {
            System.err.println("Erreur récupération produits commercial: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // CORRIGÉ : endpoint réel = /api/commandes
    public boolean ajouterCommandeCommercial(Integer codeCmd, String client, Integer codePdt, Integer qteCmd, LocalDate dateCmd) {
        try {
            String url = commercialServiceUrl + "/api/commandes";  // <-- CHANGÉ ICI

            var request = new java.util.HashMap<String, Object>();
            request.put("codeCmd", codeCmd);
            request.put("client", client);
            request.put("codePdt", codePdt);
            request.put("qteCmd", qteCmd);
            request.put("dateCmd", dateCmd.toString());

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.err.println("Erreur ajout commande commercial: " + e.getMessage());
            return false;
        }
    }

    public String getNomProduit(int codePdt) {
        try {
            String url = commercialServiceUrl + "/api/produits/" + codePdt + "/nom";
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Produit inconnu";
        }
    }

    public double getPrixProduit(int codePdt) {
        try {
            String url = commercialServiceUrl + "/api/produits/" + codePdt + "/prix";
            Double prix = restTemplate.getForObject(url, Double.class);
            return prix != null ? prix : 0.0;
        } catch (Exception e) {
            return 0.0;
        }
    }
}