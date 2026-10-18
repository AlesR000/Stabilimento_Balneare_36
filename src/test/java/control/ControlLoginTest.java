package control;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControlLoginTest {

    @BeforeAll
    static void setUp(){
    }

    @Test
    void salvaClienteTest(){

        int salvato = ControlLogin.salvaUtente("Antonio", "Giallo", "AntonioGiallo@gmail.com",
                "Antonio22!!", "0811231234", "Cliente");

        Assertions.assertEquals(1, salvato);

    }

    @Test
    void salvaGestoreTest(){

        int salvato = ControlLogin.salvaUtente("Antonio", "Giallo", "AntonioGiallo@gmail.com",
                "Antonio22!!", "0811231234", "Gestore");

        Assertions.assertEquals(2, salvato);
    }

    @Test
    void salvaPasswordErrataTest(){

        int salvato = ControlLogin.salvaUtente("Antonio", "Giallo", "AntonioGiallo@gmail.com",
                "giallo", "0811231234", "Gestore");

        Assertions.assertEquals(0, salvato);

    }


}