////////////////////////////////////////////////////////////////////
// [MATTIA] [PANDOLFO] [2008085]
// [FEDERICO] [YE] [2000549]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import it.unipd.mtss.model.Order;
import it.unipd.mtss.model.TipoItem;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalTime;

public class BillImpl implements Bill{

    @Override
    public double getOrderPrice(List<EItem> itemsOrdered, User user) throws BillException {
        double total = 0;
        int processori = 0;
        int mouse = 0;
        int tastiere = 0;
        EItem ProcessoreMenoCostoso = null;
        EItem MouseMenoCostoso = null;
        EItem TastieraMenoCostosa = null;                                                        
        EItem MouseOTastieraMenoCostosa = null;
        if(itemsOrdered.isEmpty()) {
            throw new BillException("Lista ordini vuota");
        }
        if(itemsOrdered == null) {
            throw new BillException("Lista ordini nulla");
        }
        if (itemsOrdered.size() > 30) {
            throw new BillException("Limite ordine superato");
        }
        for (EItem item : itemsOrdered) {
            total = total + item.getPrice(); 
            //trovo processore più economico in lista
            if (item.getTipo() == TipoItem.Processor) {
                processori++;

                if ((ProcessoreMenoCostoso == null) || (ProcessoreMenoCostoso.getPrice() > item.getPrice())) {
                    ProcessoreMenoCostoso = item;
                }
            }
            //trovo mouse più economico in lista
            if (item.getTipo() == TipoItem.Mouse) {
                mouse++;

                if ((MouseMenoCostoso == null) || (MouseMenoCostoso.getPrice() > item.getPrice())) {
                    MouseMenoCostoso = item;
                }
            } 
            //trovo tastiera più economica in lista
                if (item.getTipo() == TipoItem.Keyboard) {
                tastiere++;

                if ((TastieraMenoCostosa == null) || (TastieraMenoCostosa.getPrice() > item.getPrice())) {
                    TastieraMenoCostosa = item;
                }
            }
            //trovo mouse o tastiera meno costosa
            if (item.getTipo() == TipoItem.Keyboard || item.getTipo() == TipoItem.Mouse){
                if ((MouseOTastieraMenoCostosa == null) || (MouseOTastieraMenoCostosa.getPrice() > item.getPrice())) {
                    MouseOTastieraMenoCostosa = item;
                }
            }

        }

        // se più di 5 processori,sconto sul processore più economico 
        if (processori > 5) {
            total = total - (ProcessoreMenoCostoso.getPrice() * 0.5);
        }

        // se più di 10 mouse, il meno caro verrà regalato
        if (mouse > 10) {
            total = total - MouseMenoCostoso.getPrice();
        }

        //sconto quando numero di mouse uguale a numero di tastiere
        if (tastiere == mouse && tastiere != 0) {
            total = total - MouseOTastieraMenoCostosa.getPrice();
        }

        //se il totale supera 1000, si applica uno sconto del 10% sul totale
        if(total > 1000){
            total = total * 0.9;
        }

        //se il totale è inferiore ai 10 euro, aggiungo una commissione di 2 euro
        if(total < 10){
            total = total + 2;
        }

        return total;
    }

    public List<Order> getFreeOrders(List<Order> ordini) throws BillException {

        List<Order> ordiniGratis = new ArrayList<Order>();
        for (int i = 0; i < ordini.size(); i++) {

            if(ordini.get(i).getUser().getAge()<18 && //se l'utente è minorenne
             ordini.get(i).getOraOrdine().isAfter(LocalTime.of(18,00,00,00)) && //l'ora locale è dopo le 18
             ordini.get(i).getOraOrdine().isBefore(LocalTime.of(19,00,00,00))){ //l'ora locale prima delle 19

                ordiniGratis.add(ordini.get(i)); //ordine in regalo
            }
        }

        if(ordiniGratis.size() > 9){
              for(int i=0; i<10; i++) {
              //numero a caso tra 1 e numero ordini
              int randomIndex = (int)(ordiniGratis.size() * Math.random());
              if(ordiniGratis.get(randomIndex).getPrice() == 0) {
                  i--;
              }
              else {
              ordiniGratis.get(randomIndex).setPrice(0);
              }
            }
        }
        else {
            throw new BillException("Non ci sono abbastanza ordini per regali");
        }
        return ordiniGratis;
    }

}