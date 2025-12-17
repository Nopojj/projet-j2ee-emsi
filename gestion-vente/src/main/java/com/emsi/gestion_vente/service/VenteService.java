package com.emsi.gestion_vente.service;

import com.emsi.gestion_vente.dto.ProduitInfoDTO;
import com.emsi.gestion_vente.dto.ProduitPrixDTO;
import com.emsi.gestion_vente.dto.ProduitStockDTO;
import com.emsi.gestion_vente.entity.Commande;
import com.emsi.gestion_vente.repository.CommandeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VenteService {
    
    private final CommandeRepository commandeRepository;
    private final StockServiceClient stockClient;
    private final CommercialServiceClient commercialClient;
    
    public VenteService(CommandeRepository commandeRepository,
                       StockServiceClient stockClient,
                       CommercialServiceClient commercialClient) {
        this.commandeRepository = commandeRepository;
        this.stockClient = stockClient;
        this.commercialClient = commercialClient;
    }
    
    /**
     * Récupère tous les produits avec leurs informations complètes
     * (fusion des données de stock et commercial)
     */
    public List<ProduitInfoDTO> getAllProduitsInfo() {
        try {
            List<ProduitStockDTO> stocks = stockClient.getAllStock();
            List<ProduitPrixDTO> prixList = commercialClient.getAllProduitsPrix();

            Map<Integer, ProduitPrixDTO> prixMap = prixList.stream()
                .collect(Collectors.toMap(ProduitPrixDTO::getCodePdt, p -> p));

            List<ProduitInfoDTO> produits = new ArrayList<>();

            for (ProduitStockDTO stock : stocks) {
                ProduitPrixDTO prixPdt = prixMap.get(stock.getCodePdt());
                if (prixPdt != null) {
                    ProduitInfoDTO info = new ProduitInfoDTO(
                        stock.getCodePdt(),
                        prixPdt.getNomPdt(),
                        prixPdt.getPrixPdt(), 
                        stock.getQtePdt()
                    );
                    produits.add(info);
                }
            }
            return produits;

        } catch (Exception e) {
            System.err.println("Erreur fusion produits: " + e.getMessage());
            e.printStackTrace(); // Pour voir l'erreur exacte dans la console

            // Mock avec le bon constructeur (Double pour prix)
            return List.of(
                new ProduitInfoDTO(1, "Laptop Dell", 12000.0, 10),
                new ProduitInfoDTO(2, "Souris Logitech", 250.0, 50),
                new ProduitInfoDTO(3, "Clavier mécanique", 800.0, 30)
            );
        }
    }
    /**
     * Créer une commande complète
     * 1. Enregistrer dans la base locale (commandes)
     * 2. Soustraire du stock
     * 3. Ajouter dans Tous_commandes (commercial)
     */
    @Transactional
    public Commande creerCommande(String client, Integer codePdt, Integer qteCmd) {
        // 1. Sauvegarder la commande localement
        Commande commande = new Commande(client, codePdt, qteCmd);
        commande = commandeRepository.save(commande);
        
        // 2. Soustraire du stock
        boolean stockOk = stockClient.soustraireStock(codePdt, qteCmd);
        
        if (!stockOk) {
            throw new RuntimeException("Erreur lors de la mise à jour du stock");
        }
        
        // 3. Ajouter dans Tous_commandes (commercial)
        boolean commercialOk = commercialClient.ajouterCommandeCommercial(
            commande.getCodeCmd(),
            client,
            codePdt,
            qteCmd,
            commande.getDateCmd()
        );
        
        if (!commercialOk) {
            throw new RuntimeException("Erreur lors de l'ajout dans Tous_commandes");
        }
        
        return commande;
    }
    
    /**
     * Récupérer toutes les commandes
     */
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }
    
    /**
     * Récupérer une commande par ID
     */
    public Commande getCommandeById(Integer id) {
        return commandeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
    }
}