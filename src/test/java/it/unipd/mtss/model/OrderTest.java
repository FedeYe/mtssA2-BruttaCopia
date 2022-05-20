package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
    private EItem item;
    private List<EItem> itemsOrdered;
    private User user;
    private LocalTime time;
    private Order ordine;

    @Before
    public void setup() {
        itemsOrdered = new ArrayList<EItem>();
        user = new User("FedeYe","Federico","Ye",LocalDate.of(2001, 2, 22));
        time = LocalTime.of(12,00);
        item = new EItem(TipoItem.Processor, "Amd Mark2", 150.00);
        itemsOrdered.add(item);
        ordine = new Order(itemsOrdered, user, time, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testListaElementiVuota() {
        itemsOrdered.clear();
        new Order(itemsOrdered, user, time, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomeUtenteNullo() {
        new Order(itemsOrdered, null, time, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDataNulla() {
        new Order(itemsOrdered, user, null, 0);
    }

    @Test
    public void testGetListaElementi() {
        assertEquals(ordine.getListaElementi(), itemsOrdered);
    }

    @Test
    public void testGetOraOrdine() {
        assertEquals(ordine.getOraOrdine(), time);
    }

    @Test
    public void testSetAndGetPrice() {
        ordine.setPrice(12);
        assertEquals(12,ordine.getPrice(),0.0);
    }

    @Test
    public void testGetUser() {
        assertEquals(ordine.getUser(), user);
    }
}