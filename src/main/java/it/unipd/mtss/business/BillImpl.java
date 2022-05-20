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
        EItem ProcessoreMenoCostoso = null;
        if(itemsOrdered.isEmpty()) {
            throw new BillException("Lista ordini vuota");
        }
        if(itemsOrdered == null) {
            throw new BillException("Lista ordini nulla");
        }
        for (EItem item : itemsOrdered) {
            total = total + item.getPrice();  
             if (item.getTipo() == TipoItem.Processor) {
                processori++;

                if ((ProcessoreMenoCostoso == null) || (ProcessoreMenoCostoso.getPrice() > item.getPrice())) {
                    ProcessoreMenoCostoso = item;
                }
            } 
        }

        // sconto se più di 5 processori
        if (processori > 5) {
            total = total - (ProcessoreMenoCostoso.getPrice() * 0.5);
        }
        
        return total;
    }
}