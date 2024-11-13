package integracion;

import static org.junit.Assert.*;
import org.junit.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;
import java.util.ArrayList;
import controlador.*;
import util.Constantes;
import util.Mensajes;
import GUI.FalsoOptionPane;
import vista.Ventana;

public class NuevoVehiculoTest {
    Empresa empresa;
    Controlador controlador;
    Chofer choferTemp,choferPerm;
    Ventana ventana;
    FalsoOptionPane op;
    
    @Before
    public void setUp() throws Exception{
        empresa=Empresa.getInstance();
        empresa.setVehiculos(new HashMap<String,Vehiculo>());
        controlador= new Controlador();
        op = new FalsoOptionPane();
        ventana = mock(Ventana.class);
        controlador.setVista(ventana);
		controlador.getVista().setOptionPane(op);
        op = new FalsoOptionPane();
        ventana = mock(Ventana.class);
        controlador.setVista(ventana);
		controlador.getVista().setOptionPane(op);
        when(ventana.getOptionPane()).thenReturn(op);

        when(ventana.getUsserName()).thenReturn("admin");
        when(ventana.getPassword()).thenReturn("admin");
        this.controlador.login();
    }

    @After
    public void tearDown() throws Exception{
        empresa.getChoferes().clear();
        empresa.getClientes().clear();
        empresa.getPedidos().clear();
    }

    @Test 
    public void nuevoAutoExitosoTest(){   
        try{
            when(ventana.getTipoVehiculo()).thenReturn(Constantes.AUTO);
            when(ventana.getPatente()).thenReturn("ABC123");
            when(ventana.getPlazas()).thenReturn(4);
            when(ventana.isVehiculoAptoMascota()).thenReturn(false);
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
            when(ventana.getTipoVehiculo()).thenReturn(Constantes.COMBI);
            when(ventana.getPatente()).thenReturn("DEF456");
            when(ventana.getPlazas()).thenReturn(8);
            when(ventana.isVehiculoAptoMascota()).thenReturn(false);
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
            when(ventana.getTipoVehiculo()).thenReturn(Constantes.MOTO);
            when(ventana.getPatente()).thenReturn("GHI789");
            this.controlador.nuevoVehiculo(); 
            Vehiculo vehiculoAgregado = empresa.getVehiculos().get("GHI789");
            assertNotNull(vehiculoAgregado);

            assertEquals(ventana.getPatente(),vehiculoAgregado.getPatente());
       
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }

    @Test 
    public void nuevoVehiculoFallidoTest(){   
        try{
            Vehiculo auto = new Auto("ABC123", 4, false);
            empresa.agregarVehiculo(auto);
            when(ventana.getTipoVehiculo()).thenReturn(Constantes.AUTO);
            when(ventana.getPatente()).thenReturn("ABC123");
            when(ventana.getPlazas()).thenReturn(3);
            when(ventana.isVehiculoAptoMascota()).thenReturn(false);   
            this.controlador.nuevoVehiculo(); 
            assertEquals(Mensajes.VEHICULO_YA_REGISTRADO.getValor(), op.getMensaje());
            
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }
}
