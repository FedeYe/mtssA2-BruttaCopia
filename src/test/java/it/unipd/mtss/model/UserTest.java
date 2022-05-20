////////////////////////////////////////////////////////////////////
// [MATTIA] [PANDOLFO] [2008085]
// [FEDERICO] [YE] [2000549]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import org.junit.Test;

public class UserTest {

    private User Fede = new User("FedeYe", "Federico", "Ye", LocalDate.of(2001, 2, 22));

    @Test(expected = IllegalArgumentException.class)
    public void testNicknameValoreAppareNullo() {
        new User(null,"Federico","Ye",LocalDate.of(2001, 2, 22));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomeValoreAppareNullo() {
        new User("FedeYe",null,"Ye",LocalDate.of(2001, 2, 22));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCognomeValoreAppareNullo() {
        new User("FedeYe","Federico",null,LocalDate.of(2001, 2, 22));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDataValoreAppareNullo() {
        new User("FedeYe","Federico","Ye",null);
    }

    @Test
    public void testGetNickname() {
        assertEquals(Fede.getNickname(), "FedeYe");
    }

    @Test
    public void testGetName() {
        assertEquals(Fede.getName(), "Federico");
    }

    @Test
    public void testGetCognome() {
        assertEquals(Fede.getCognome(), "Ye");
    }

    @Test
    public void testGetAge() {
        assertEquals(Fede.getAge(), 21);
    }
}