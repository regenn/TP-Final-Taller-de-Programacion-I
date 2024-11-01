package test;
/*
METODOS A TESTEAR:

getSueldoNeto()
*/ 
import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import util.*;

public class ChoferTemporarioTest {
    Chofer chofer;


    @Before
    public void startUp(){
        System.out.println("Runneando: startUp");
        chofer=new ChoferTemporario("123456","nombreChofer");
    }
    @Test
    public void getSueldoBrutoTest(){
        assertEquals(chofer.getSueldoBruto(),Chofer.getSueldoBasico(),0.001);
        //deberiamos testear de esta manera?
    }
    @Test
    public void getDniTest(){
        assertEquals(chofer.getDni(),"123456");
    }

    @Test
    public void getNombreTest(){
        assertEquals(chofer.getNombre(),"nombreChofer");
    }

    @Test
    public void getSueldoNeto(){
        assertEquals(chofer.getSueldoNeto(),chofer.getSueldoBruto()*0.86,0.001);
    }

    @Test
    public void SueldoBasicoTest(){
        Chofer.setSueldoBasico(300000.0);
        assertEquals(300000.0,Chofer.getSueldoBasico(),0.001);
    }


    @After
    public void tearDown(){
        chofer=null;
        assertNull(chofer);
    }
    

}

