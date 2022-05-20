////////////////////////////////////////////////////////////////////
// [MATTIA] [PANDOLFO] [2008085]
// [FEDERICO] [YE] [2000549]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class EItemTest {

    private EItem Processor;
    private EItem Motherboard;
    private EItem Mouse;
    private EItem Keyboard;

    @Before
    public void setup() {
        Processor = new EItem( TipoItem.Processor, "Amd Mark2", 150.00);
        Motherboard = new EItem(TipoItem.Motherboard, "Madre Mia", 69.00);
        Mouse = new EItem( TipoItem.Mouse, "Poseidon", 40.00);
        Keyboard = new EItem( TipoItem.Keyboard, "Logitech perry", 55.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTipologiaElementoAppareNullo() {
        new EItem(null, "Amd Mark2", 150.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomeElementoAppareNullo() {
        new EItem(TipoItem.Processor, null, 150.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrezzoElementoAppareNegativo() {
        new EItem(TipoItem.Processor, "Amd Mark2", -10.00);
    }

    @Test
    public void testGetTipo() {
        assertEquals(TipoItem.Processor, Processor.getTipo());
        assertEquals(TipoItem.Motherboard, Motherboard.getTipo());
        assertEquals(TipoItem.Mouse, Mouse.getTipo());
        assertEquals(TipoItem.Keyboard, Keyboard.getTipo());
    }

    @Test
    public void testGetName() {
        assertEquals("Amd Mark2", Processor.getName());
        assertEquals("Madre Mia", Motherboard.getName());
        assertEquals("Poseidon", Mouse.getName());
        assertEquals("Logitech perry", Keyboard.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(150.00, Processor.getPrice(), 0.0);
        assertEquals(69.00, Motherboard.getPrice(), 0.0);
        assertEquals(40.00, Mouse.getPrice(), 0.0);
        assertEquals(55.00, Keyboard.getPrice(), 0.0);
    }
}