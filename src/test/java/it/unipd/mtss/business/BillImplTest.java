////////////////////////////////////////////////////////////////////
// [MATTIA] [PANDOLFO] [2008085]
// [FEDERICO] [YE] [2000549]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.business;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.TipoItem;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import it.unipd.mtss.model.Order;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BillImplTest {
    
    private List<EItem> itemsOrdered;
    private BillImpl testBill; 
    private User user; 
    private LocalTime oreSeiMezza;
    
    @Before
    public void setup() {
        itemsOrdered = new ArrayList<EItem>();
        testBill = new BillImpl();
        oreSeiMezza = LocalTime.of(18,30,00,00);
        user = new User("FedeYe","Federico","Ye",LocalDate.of(2001, 2, 22));
    }
    
    @Test
    public void testCalcoloPrezzoTotale(){
        
        itemsOrdered.add(new EItem( TipoItem.Processor, "Amd Mark2",150.00));
        itemsOrdered.add(new EItem( TipoItem.Motherboard, "Madre Mia", 69.00));
        itemsOrdered.add(new EItem( TipoItem.Mouse, "Poseidon", 40.00));
        itemsOrdered.add(new EItem( TipoItem.Keyboard, "Logitech perry", 55.00));
        itemsOrdered.add(new EItem( TipoItem.Mouse, "Zeus",5.00));
        
        assertEquals(319, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }
    
    @Test(expected=BillException.class)
    public void testCalcoloPrezzoTotaleDataUnaListaOrdiniVuota() {
        
        testBill.getOrderPrice(itemsOrdered, user);
    }
    
    @Test(expected=NullPointerException.class)
    public void testCalcoloPrezzoTotaleDataUnaListaOrdiniNulla() {

        itemsOrdered = null;
        testBill.getOrderPrice(itemsOrdered, user);
    }
    
    @Test
    public void testTotaleConScontoSulMenoCostosoSeNumeroDiProcessoriPiùDiCinque() {

        for(int i=0; i<6; i++) {
            itemsOrdered.add(new EItem( TipoItem.Processor, "Amd Mark2",150.00));
        }       
        assertEquals(825.00, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }

    @Test
    public void testTotaleConMouseMenoCostosoRegalatoSeNumeroDiMousePiùDiDieci() {

        for(int i=0; i<11; i++) {
            itemsOrdered.add(new EItem( TipoItem.Mouse, "Poseidon",40.00));
        }       
        assertEquals(400.00, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }

    @Test
    public void testTotaleConScontoSulMenoCostosoSeNumeroMouseUgualeNumeroTastiere() {

        for(int i=0; i<4; i++) {
            itemsOrdered.add(new EItem( TipoItem.Mouse, "Poseidon",40.00));
        }       
        for(int i=0; i<4; i++) {
            itemsOrdered.add(new EItem( TipoItem.Keyboard, "Logitech perry",55.00));
        }
        assertEquals(340.00, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    } 

    @Test
    public void testTotaleConScontoSeLaSpesaTotaleSuperioreAi1000Euro(){

        for(int i=0; i<20; i++) {
            itemsOrdered.add(new EItem( TipoItem.Motherboard, "Madre Mia",69.00));
        }    
        assertEquals(1242.00, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }

    @Test(expected=BillException.class)
    public void testPiùDiTrentaElementiPerOrdine() {

        for(int i=0; i<40; i++) {
            itemsOrdered.add(new EItem( TipoItem.Motherboard, "Madre Mia",69.00));
        }

        testBill.getOrderPrice(itemsOrdered, user);
    }

    @Test
    public void testAggiuntaCommissioneDi2euroSeTotaleInferioreA10Euro(){

        itemsOrdered.add(new EItem( TipoItem.Mouse, "Zeus",5.00)); 
        assertEquals(7.00, testBill.getOrderPrice(itemsOrdered,user), 0.0);
    }

    @Test
    public void testOrdiniRegalatiAdUserCheSonoMinorenni() {

        //vettore che rappresenta lista con tutti gli ordini
        List<Order> ordini = new ArrayList<Order>();

        itemsOrdered.add(new EItem( TipoItem.Processor, "Amd Mark2", 150.00));
        itemsOrdered.add(new EItem( TipoItem.Motherboard, "Madre Mia", 69.00));
        //11 utenti minorenni
        String[] nomiUtentiMinori =new String[]{"Mario", "Luigi", "Peach", "Wario", "Waluigi",
                        "Pandolfo", "Federico", "Rossi", "Yoshi", "Sonic", "Pikachu", "Raichu"};
        User user = null;
        //inserisco 12 ordini
	  int i = 11;
	  while(i > 0) {
            //nuovo utente minorenne
            user = new User(nomiUtentiMinori[i]+"_test",nomiUtentiMinori[i],"prova",LocalDate.of(2010, 1, 1));;
            //aggiungo il suo ordine
            ordini.add(new Order(itemsOrdered, user,  oreSeiMezza, testBill.getOrderPrice(itemsOrdered, user)));
		i--;
        }

        List<Order> ordiniGratis = testBill.getFreeOrders(ordini);
        int numeroOrdiniRegalati = 0;

        for (Order ord : ordiniGratis) {
            if(ord.getPrice() == 0) {
                numeroOrdiniRegalati++;
            }
        }
        assertEquals(10,numeroOrdiniRegalati); 
    }

    //se non vengono effettuati almeno 10 ordini, nessun regalo 
    @Test(expected=BillException.class)
    public void testOrdiniRegalatiSeNumeroDiOrdiniMinoreDi10() { 

        //vettore che rappresenta con tutti gli ordini
        List<Order> ordini = new ArrayList<Order>();

        itemsOrdered.add(new EItem( TipoItem.Processor, "Amd Mark2", 150.00));
        //solo 4 utenti minorenni
        String[] nomiUtentiMinori =new String[]{"Mario", "Luigi", "Yoshi", "Peach"};
        User user = null;
        //inserisco 4 ordini
	 int i = 3;
        while(i > 0) {
            //nuovo utente minorenne
            user = new User(nomiUtentiMinori[i]+"_test",nomiUtentiMinori[i],"prova",LocalDate.of(2010, 1, 1));;
            //aggiungo il suo ordine
            ordini.add(new Order(itemsOrdered, user,  oreSeiMezza, testBill.getOrderPrice(itemsOrdered, user)));
		i--;
        }

        testBill.getFreeOrders(ordini);
    }
}