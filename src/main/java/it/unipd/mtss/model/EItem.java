////////////////////////////////////////////////////////////////////
// [MATTIA] [PANDOLFO] [2008085]
// [FEDERICO] [YE] [2000549]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

public class EItem {
    TipoItem TipoItem;
    String name;
    double price;

    public EItem(TipoItem TipoItem, String name, double price){
        
        if(TipoItem == null){
            throw new IllegalArgumentException("Tipologia non presente");
        }
        if(name == null){
            throw new IllegalArgumentException("Nome non presente");
        }
        if(price <= 0){
            throw new IllegalArgumentException("Prezzo non accettabile");
        }

        this.TipoItem = TipoItem;
        this.name = name;
        this.price = price;
    }

    public double getPrice(){
        return price;
    }

    public TipoItem getTipo(){
        return TipoItem;
    }

    public String getName(){
        return name;
    }
}
