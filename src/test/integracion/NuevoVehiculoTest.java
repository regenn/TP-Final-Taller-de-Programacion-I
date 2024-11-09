package test.integracion;

import static org.junit.Assert.*;
import org.junit.*;

import static org.mockito.Mockito.mock;//hay que instalar algo?
import static org.mockito.Mockito.when;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;
import java.util.ArrayList;
import controlador.*;
import util.Constantes;
import util.Mensajes;
import test.GUI.FalsoOptionPane;
import vista.Ventana;//??

public class NuevoVehiculoTest {
    Empresa empresa;
    Controlador controlador;
    Chofer choferTemp,choferPerm;
    

    @Before
    public void setUp() throws Exception{
        empresa=Empresa.getInstance();
        empresa.setVehiculos(new HashMap<String,Vehiculo>());
    }

    @After
    public void tearDown() throws Exception{

    }

    @Test 
    public void nuevoAutoExitosoTest(){   
        try{
            Ventana ventana= mock(Ventana.class);
            when(ventana.getTipoVehiculo()).thenReturn(Constantes.AUTO);
            when(ventana.getPatente()).thenReturn("ABC123");
            when(ventana.getPlazas()).thenReturn(4);
            when(ventana.isVehiculoAptoMascota()).thenReturn(false);
            this.controlador.setVista(ventana);
            this.controlador.nuevoVehiculo(); 
            Vehiculo vehiculoAgregado = empresa.getVehiculos().get("ABC123");
            assertNotNull(vehiculoAgregado);

            assertEquals(ventana.getPatente(),vehiculoAgregado.getPatente());
            assertEquals(ventana.getPlazas(), vehiculoAgregado.getCantidadPlazas());
            assertEquals(ventana.isVehiculoAptoMascota(), vehiculoAgregado.isMascota());
       
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }

    @Test 
    public void nuevaCombiExitosoTest(){   
        try{
            Ventana ventana= mock(Ventana.class);
            when(ventana.getTipoVehiculo()).thenReturn(Constantes.COMBI);
            when(ventana.getPatente()).thenReturn("DEF456");
            when(ventana.getPlazas()).thenReturn(8);
            when(ventana.isVehiculoAptoMascota()).thenReturn(false);
            this.controlador.setVista(ventana);
            this.controlador.nuevoVehiculo(); 
            Vehiculo vehiculoAgregado = empresa.getVehiculos().get("DEF456");
            assertNotNull(vehiculoAgregado);

            assertEquals(ventana.getPatente(),vehiculoAgregado.getPatente());
            assertEquals(ventana.getPlazas(), vehiculoAgregado.getCantidadPlazas());
            assertEquals(ventana.isVehiculoAptoMascota(), vehiculoAgregado.isMascota());
       
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }

    @Test 
    public void nuevaMotoExitosoTest(){   
        try{
            Ventana ventana= mock(Ventana.class);
            when(ventana.getTipoVehiculo()).thenReturn(Constantes.MOTO);
            when(ventana.getPatente()).thenReturn("GHI789");
            this.controlador.setVista(ventana);
            this.controlador.nuevoVehiculo(); 
            Vehiculo vehiculoAgregado = empresa.getVehiculos().get("GHI789");
            assertNotNull(vehiculoAgregado);

            assertEquals(ventana.getPatente(),vehiculoAgregado.getPatente());
            assertEquals(ventana.getPlazas(), vehiculoAgregado.getCantidadPlazas());
            assertEquals(ventana.isVehiculoAptoMascota(), vehiculoAgregado.isMascota());
       
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }

    @Test 
    public void nuevoVehiculoFallidoTest(){   
        try{
            Ventana ventana= mock(Ventana.class);
            FalsoOptionPane op = new FalsoOptionPane();
            when(ventana.getTipoVehiculo()).thenReturn(Constantes.AUTO);
            when(ventana.getPatente()).thenReturn("ABC123");
            when(ventana.getPlazas()).thenReturn(10);
            when(ventana.isVehiculoAptoMascota()).thenReturn(false);   
            this.controlador.setVista(ventana);
            this.controlador.getVista().setOptionPane(op);
            this.controlador.nuevoVehiculo(); 
            assertEquals(Mensajes.VEHICULO_NO_VALIDO, op.getMensaje());
            
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }
}
