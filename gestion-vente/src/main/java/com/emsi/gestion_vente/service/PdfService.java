package com.emsi.gestion_vente.service;

import com.emsi.gestion_vente.entity.Commande;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Service
public class PdfService {

    public ByteArrayInputStream generateFacturePdf(Commande commande, String nomProduit, double prixUnitaire) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Titre
        document.add(new Paragraph("FACTURE DE COMMANDE")
                .setFontSize(20)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph("\n"));

        // Infos commande
        document.add(new Paragraph("N° Commande : " + commande.getCodeCmd()));
        document.add(new Paragraph("Client      : " + commande.getClient()));
        document.add(new Paragraph("Date        : " + commande.getDateCmd().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        document.add(new Paragraph("\n"));

        // Tableau produit
        Table table = new Table(UnitValue.createPercentArray(new float[]{3, 2, 2}));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell("Produit");
        table.addHeaderCell("Quantité");
        table.addHeaderCell("Prix unitaire");
        table.addHeaderCell("Sous-total");

        double sousTotal = prixUnitaire * commande.getQteCmd();

        table.addCell(nomProduit);
        table.addCell(String.valueOf(commande.getQteCmd()));
        table.addCell(prixUnitaire + " DH");
        table.addCell(sousTotal + " DH");

        document.add(table);

        document.add(new Paragraph("\n"));

        // Total
        document.add(new Paragraph("TOTAL : " + sousTotal + " DH")
                .setFontSize(16)
                .setBold()
                .setTextAlignment(TextAlignment.RIGHT));

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Merci pour votre achat !")
                .setTextAlignment(TextAlignment.CENTER));

        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}