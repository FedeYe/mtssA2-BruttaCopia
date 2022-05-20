////////////////////////////////////////////////////////////////////
// [MATTIA] [PANDOLFO] [2008085]
// [FEDERICO] [YE] [2000549]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

import java.util.List;
import java.time.LocalTime;

public class Order {
    List<EItem> listaElementi; 
    User utente;
    LocalTime oraOrdine;
    double price; 

    public Order(List<EItem> listaElementi, 
            User utente,
            LocalTime oraOrdine,
            double price) {
        if(listaElementi.isEmpty()) {
            throw new IllegalArgumentException("Elenco vuoto");
        }
        if(utente == null) {
            throw new IllegalArgumentException("Utente non valido");
        }
        if(oraOrdine == null) {
            throw new IllegalArgumentException("Orario non valido");
        }
        this.listaElementi = listaElementi; 
        this.utente = utente;
        this.oraOrdine = oraOrdine;
        this.price = price;
    }

    public double getPrice() {
        return price; 
    }

    public LocalTime getOraOrdine() {
        return oraOrdine;
    }

    public User getUser() {
        return utente;
    }

    public List<EItem> getListaElementi(){
        return listaElementi;
    }

    public void setPrice(double newPrice) {
        price = newPrice;
    }
} 

