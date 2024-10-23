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
        chofer=new ChoferTemporario("dni","nombre");
    }
    @Test
    public void getSueldoBrutoTest(){
        assertEquals(chofer.getSueldoBruto(),Chofer.getSueldoBasico(),0.0);
        //DUDA: en la narrativa, no se puede saber el valor exacto del sueldo basico
        //deberiamos testear de esta manera?
    }
    @Test
    public void getDniTest(){
        assertEquals(chofer.getDni(),"dni");
    }

    @Test
    public void getNombreTest(){
        assertEquals(chofer.getNombre(),"nombre");
    }

    @Test
    public void getSueldoNeto(){
        assertEquals(chofer.getSueldoNeto(),chofer.getSueldoBruto()*0.86,0.0);
    }

}

