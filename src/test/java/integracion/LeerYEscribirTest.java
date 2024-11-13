package integracion;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import org.junit.*;

import GUI.FalsoOptionPane;
import modeloDatos.*;

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
    Ventana ventana;
    FalsoOptionPane op;

    @Before
    public void setUp(){

        empresa = Empresa.getInstance();
        controlador = new Controlador();
        ventana= mock(Ventana.class);
        op = new FalsoOptionPane();
        this.controlador.setVista(ventana);
        this.controlador.getVista().setOptionPane(op);
        when(ventana.getOptionPane()).thenReturn(op);
    }
    
    @After
    public void tearDown(){
        empresa.getChoferes().clear();
        empresa.getClientes().clear();
        empresa.getPedidos().clear();
        empresa.getVehiculos().clear();
    }

    @Test
    public void leerYEscribirTest(){
         try{
            
            Vehiculo auto = new Auto("ABC123", 4, true);
            empresa.agregarVehiculo(auto);

            this.controlador.escribir();
            this.controlador.leer();

            Vehiculo vehiculoPersistido = empresa.getVehiculos().get("ABC123");
            assertNotNull(vehiculoPersistido);

            assertEquals("ABC123",vehiculoPersistido.getPatente());
            assertEquals(4,vehiculoPersistido.getCantidadPlazas());
            assertEquals(true, vehiculoPersistido.isMascota());

        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }
}
