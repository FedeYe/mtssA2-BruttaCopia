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
        EItem ProcessoreMenoCostoso = null;
        EItem MouseMenoCostoso = null;
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

        }

        // se più di 5 processori,sconto sul processore più economico 
        if (processori > 5) {
            total = total - (ProcessoreMenoCostoso.getPrice() * 0.5);
        }

        // se più di 10 mouse, il meno caro verrà regalato
        if (mouse > 10) {
            total = total - MouseMenoCostoso.getPrice();
        }
        
        return total;
    }
}