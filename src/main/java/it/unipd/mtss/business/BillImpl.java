////////////////////////////////////////////////////////////////////
// [MATTIA] [PANDOLFO] [2008085]
// [FEDERICO] [YE] [2000549]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import it.unipd.mtss.model.TipoItem;
import java.util.List;

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
        return total;
    }
}