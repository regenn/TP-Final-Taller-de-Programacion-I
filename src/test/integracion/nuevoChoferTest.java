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

public class NuevoChoferTest {
    Empresa empresa;
    Controlador controlador;
    Chofer choferTemp,choferPerm;
    

    @Before
    public void setUp() throws Exception{
        empresa=Empresa.getInstance();
        empresa.setChoferes(new HashMap<String,Chofer>());
        /* 
        controlador= new Controlador();
        choferTemp=new ChoferTemporario("123455778","chofer");
        choferPerm=new ChoferPermanente("1234566","nombre2",2023,2);
        */
    }

    @After
    public void tearDown() throws Exception{

    }

    @Test 
    public void nuevoChoferTempExitosoTest(){   
        try{
            Ventana ventana= mock(Ventana.class);
            when(ventana.getTipoChofer()).thenReturn(Constantes.TEMPORARIO);
            when(ventana.getNombreChofer()).thenReturn("choferTemp");
            when(ventana.getDNIChofer()).thenReturn("123456778");
            this.controlador.setVista(ventana);

            this.controlador.nuevoChofer(); // tendria que haber llammado a empresa.agregarChofer();
            // comparar el dni agregado con el dni del chofer cuto nombre es choferTemp

            Chofer choferAgregado = empresa.getChoferes().get("choferTemp");
            assertNotNull(choferAgregado);

            assertEquals(ventana.getDNIChofer(),choferAgregado.getDni());
            assertEquals(ventana.getNombreChofer(), choferAgregado.getNombre());
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }

    @Test
    public void nuevoChoferPermExitosoTest(){
        try{
            Ventana ventana = mock(Ventana.class);
            when(ventana.getTipoChofer()).thenReturn(Constantes.PERMANENTE);
            when(ventana.getNombreChofer()).thenReturn("choferPerm");
            when(ventana.getDNIChofer()).thenReturn("1234566");
            when(ventana.getAnioChofer()).thenReturn(2023);
            when(ventana.getHijosChofer()).thenReturn(2);
            this.controlador.setVista(ventana);
        
            this.controlador.nuevoChofer();

            ChoferPermanente choferNuevo = (ChoferPermanente) empresa.getChoferes().get("choferPerm");
            assertNotNull(choferNuevo);

            assertEquals(ventana.getDNIChofer(),choferNuevo.getDni());
            assertEquals(ventana.getNombreChofer(),choferNuevo.getNombre());
            assertEquals(ventana.getAnioChofer(),choferNuevo.getAnioIngreso());
            assertEquals(ventana.getHijosChofer(),choferNuevo.getCantidadHijos());
        }
        catch(Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }

    @Test 
    public void nuevoChoferFailTest(){   
        try{
            Chofer chofer1 = new ChoferTemporario("123456", "chofer1");
            empresa.agregarChofer(chofer1);
            Ventana ventana= mock(Ventana.class);
            when(ventana.getTipoChofer()).thenReturn(Constantes.TEMPORARIO);
            when(ventana.getNombreChofer()).thenReturn("choferTemp");
            when(ventana.getDNIChofer()).thenReturn("123456");
            this.controlador.setVista(ventana);

            this.controlador.nuevoChofer(); // tendria que haber llammado a empresa.agregarChofer();
            // comparar el dni agregado con el dni del chofer cuto nombre es choferTemp
            fail("Tendria que haber lanzado una excepcion");
        } catch (ChoferRepetidoException e){
            
        } catch (Exception e){
            fail("No lanzo la excepcion adecuada. " + e.getMessage());
        }
    }

   
}