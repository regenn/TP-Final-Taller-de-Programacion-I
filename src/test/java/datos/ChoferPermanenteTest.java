package datos;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import util.*;

public class ChoferPermanenteTest {
    ChoferPermanente chofer;


    @Before
    public void startUp(){
        System.out.println("Runneando: startUp");
        chofer=new ChoferPermanente("12345","nombreChofer",2024,2);
    }

    @Test
    public void getAnioIngresoTest(){
        assertEquals(2024,chofer.getAnioIngreso());
    }

    @Test
    public void getAntiguedadTest(){
        //ATENCION IMPORTANTE si esto tira error es pq el anio actual no es 2024
        //no lo encontre en ninguna parte
        assertEquals(0,chofer.getAntiguedad());
    }

    @Test
    public void getCantidadHijosTest(){
        assertEquals(2,chofer.getCantidadHijos());
    }
    
    @Test
    public void getSueldoBrutoTest(){
        Chofer choferaux=new ChoferPermanente("123457","nombreChofer2",2020,0);
        assertEquals(chofer.getSueldoBruto(),Chofer.getSueldoBasico()*1.14,0.001);
        assertEquals(choferaux.getSueldoBruto(),Chofer.getSueldoBasico()*1.2,0.001);
    }
    @Test
    public void getDniTest(){
        assertEquals(chofer.getDni(),"12345");
    }

    @Test
    public void getNombreTest(){
        assertEquals(chofer.getNombre(),"nombreChofer");
    }

    @Test
    public void getSueldoNeto(){
        assertEquals(chofer.getSueldoNeto(),chofer.getSueldoBruto()*0.86,0.0);
    }

    @Test
    public void setCantidadHijosTest(){
        chofer.setCantidadHijos(3);
        assertEquals(3,chofer.getCantidadHijos());
    }

    @After
    public void tearDown(){
        chofer=null;
        assertNull(chofer);
    }

}
