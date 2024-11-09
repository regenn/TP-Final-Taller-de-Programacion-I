package test.java.integracion;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
//import Utils.MockUtils;
import excepciones.*;
import modeloNegocio.*;
import util.Constantes;
import vista.Ventana;

import java.util.HashMap;

import javax.swing.*;
import util.*;

import java.util.ArrayList;
import controlador.*;
import java.awt.event.*;

public class LeerYEscribirTest {
    Empresa empresa;
    Controlador controlador;

    @Before
    public void setUp(){
        empresa = Empresa.getInstance();
        controlador = new Controlador();


    }

    @Test
    public void leerYEscribirTest(){
         try{
            
            Ventana ventana= mock(Ventana.class);
            this.controlador.setVista(ventana);
            Vehiculo auto = new Auto("ABC123", 4, true);
            empresa.agregarVehiculo(auto);

            this.controlador.escribir();
            this.controlador.leer();

            Vehiculo vehiculoPersistido = empresa.getVehiculos().get("ABC123");
            assertEquals("ABC123",vehiculoPersistido.getPatente());
            assertEquals(4,vehiculoPersistido.getCantidadPlazas());
            assertEquals(true, vehiculoPersistido.isMascota());

        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }
}
