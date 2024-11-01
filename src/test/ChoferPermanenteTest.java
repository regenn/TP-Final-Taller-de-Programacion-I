package test;
/*
METODOS A TESTEAR:


getAnioIngreso()
 
int
getAntiguedad()
 
int
getCantidadHijos()
 
double
getSueldoBruto()
El sueldo bruto se calcula incrementando el sueldo basico a partir de un plus por antiguedady un plus por cantidad de hijos.
void
setCantidadHijos(int cantidadHijos)
*/ 
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
        //ATENCION IMPORTANTE si esto tira error es pq el anio actual no es 2024 PROBABLY
        //no lo encontre en ninguna parte tbh
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
        //deberiamos testear de esta manera?
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
//EL TEST DE INTEGRACION QUEDA EN EL CONTROLADOR, NO SE DEBE TESTEAR LA VENTANAA
/**
 * setOptionPanel: lo tiene la vista
 * haces uno de esos, conserva el dato que estoy pasandole, ESTO ESTA EN EL VIDEO
 * el show message del controlador, va ser derivado al mensaje del option panel, que sera
 * el que le pasemos nosotros
 * ahi verifico que el controlador capturo la excepcion que correspondia!!
 * 
 * 
 * ej: no hay cliente -> en el optionPanel tiene que decirme que no hay cliente loggeado
 * ej: generarPedido, puede tener varios errores en dif situaciones. que hace el controlador?
 * debo reducir esa cantidad de errores a 1: genera pedido no exitosamente
 * que es la que muestra el controlador en pantalla.
 * 
 * para diferentes escenarios -> se van a tirar diferentes excepciones.
 * 
 * 
 */