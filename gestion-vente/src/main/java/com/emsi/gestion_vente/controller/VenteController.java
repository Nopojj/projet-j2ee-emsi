package com.emsi.gestion_vente.controller;

import com.emsi.gestion_vente.dto.ProduitInfoDTO;
import com.emsi.gestion_vente.entity.Commande;
import com.emsi.gestion_vente.service.CommercialServiceClient;
import com.emsi.gestion_vente.service.PdfService;
import com.emsi.gestion_vente.service.VenteService;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/ventes")
public class VenteController {

    private final VenteService venteService;
    private final CommercialServiceClient commercialServiceClient;
    private final PdfService pdfService;

    // Injection par constructeur - OBLIGATOIRE pour que Spring trouve les beans
    public VenteController(VenteService venteService,
                           CommercialServiceClient commercialServiceClient,
                           PdfService pdfService) {
        this.venteService = venteService;
        this.commercialServiceClient = commercialServiceClient;
        this.pdfService = pdfService;
    }

    @GetMapping
    public String listeProduits(Model model) {
        List<ProduitInfoDTO> produits;
        try {
            produits = venteService.getAllProduitsInfo();
        } catch (Exception e) {
            System.err.println("Erreur chargement produits : " + e.getMessage());
            e.printStackTrace();
            produits = List.of(); // liste vide en cas d'erreur
            model.addAttribute("erreur", "Impossible de charger les produits (services indisponibles ou pas de données)");
        }

        model.addAttribute("produits", produits);
        return "produit/liste";
    }

    @GetMapping("/commandes/formulaire")
    public String afficherFormulaireCommande(@RequestParam("codePdt") Integer codePdt, Model model) {
        try {
            List<ProduitInfoDTO> produits = venteService.getAllProduitsInfo();
            ProduitInfoDTO produit = produits.stream()
                    .filter(p -> p.getCodePdt().equals(codePdt))
                    .findFirst()
                    .orElse(null);

            model.addAttribute("produit", produit);
            model.addAttribute("commande", new Commande());
            return "commandes/formulaire";
        } catch (Exception e) {
            model.addAttribute("erreur", "Impossible de charger le produit : " + e.getMessage());
            return "commandes/formulaire";  // affiche le formulaire avec message d'erreur
        }
    }

    @PostMapping("/commander")
    public String passerCommande(@RequestParam String client,
                                 @RequestParam Integer codePdt,
                                 @RequestParam Integer qteCmd,
                                 Model model) {
        try {
            Commande commande = venteService.creerCommande(client, codePdt, qteCmd);
            
            // Récupère les infos du produit pour l'afficher dans la confirmation
            String nomProduit = commercialServiceClient.getNomProduit(codePdt);
            double prixUnitaire = commercialServiceClient.getPrixProduit(codePdt);
            
            model.addAttribute("commande", commande);
            model.addAttribute("nomProduit", nomProduit);
            model.addAttribute("prixUnitaire", prixUnitaire);
            model.addAttribute("success", true);
            model.addAttribute("message", "Commande créée avec succès !");
            return "commandes/confirmation";
        } catch (Exception e) {
            model.addAttribute("success", false);
            model.addAttribute("message", "Erreur: " + e.getMessage());
            return "commandes/confirmation";
        }
    }

    @GetMapping("/commandes")
    public String listeCommandes(Model model) {
        List<Commande> commandes = venteService.getAllCommandes();
        model.addAttribute("commandes", commandes);
        return "commandes/liste";
    }



    // ENDPOINT  pdf
    @GetMapping("/commandes/{id}/facture")
    public ResponseEntity<byte[]> getFacturePdf(@PathVariable int id) throws IOException {
        Commande commande = venteService.getCommandeById(id);

        String nomProduit = commercialServiceClient.getNomProduit(commande.getCodePdt());
        double prixUnitaire = commercialServiceClient.getPrixProduit(commande.getCodePdt());

        ByteArrayInputStream pdfStream = pdfService.generateFacturePdf(commande, nomProduit, prixUnitaire);
        byte[] pdfBytes = pdfStream.readAllBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("inline")
                .filename("facture_" + id + ".pdf")
                .build());

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}