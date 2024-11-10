package integracion;

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
import GUI.FalsoOptionPane;
import vista.Ventana;//??

public class NuevoChoferTest {
    Empresa empresa;
    Controlador controlador;
    Usuario usuariologeado;
    FalsoOptionPane op;
    Ventana ventana;

    @Before
    public void setUp(){

        empresa=Empresa.getInstance();
        controlador= new Controlador();
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
    public void tearDown(){
        empresa.getChoferes().clear();
        empresa.getClientes().clear();
        empresa.getPedidos().clear();
    }


    @Test 
    public void nuevoChoferTempExitosoTest(){   
        try{
            //empresa.agregarCliente("usuario1","contrasenia1","nombre");
            //when(ventana.getUssername());
            when(ventana.getTipoChofer()).thenReturn(Constantes.TEMPORARIO);
            when(ventana.getNombreChofer()).thenReturn("choferTemp");

            when(ventana.getDNIChofer()).thenReturn("123456778");

            this.controlador.nuevoChofer(); // tendria que haber llammado a empresa.agregarChofer();
            // comparar el dni agregado con el dni del chofer cuto nombre es choferTemp
        
            Chofer choferAgregado = empresa.getChoferes().get("123456778");
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
            when(ventana.getTipoChofer()).thenReturn(Constantes.PERMANENTE);
            when(ventana.getNombreChofer()).thenReturn("choferPerm");
            when(ventana.getDNIChofer()).thenReturn("1234566");
            when(ventana.getAnioChofer()).thenReturn(2023);
            when(ventana.getHijosChofer()).thenReturn(2);
        
            this.controlador.nuevoChofer();

            ChoferPermanente choferNuevo = (ChoferPermanente) empresa.getChoferes().get("1234566");
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
    
    /* este error surge en la ventana y se propaga en integracion
    @Test 
    public void nuevoChoferFailTest(){   
        try{
            Chofer chofer1 = new ChoferTemporario("123456", "chofer1");
            empresa.agregarChofer(chofer1);
            when(ventana.getTipoChofer()).thenReturn(Constantes.TEMPORARIO);
            when(ventana.getNombreChofer()).thenReturn("choferTemp");
            when(ventana.getDNIChofer()).thenReturn("123456");

            this.controlador.nuevoChofer(); // tendria que haber llammado a empresa.agregarChofer();
            // comparar el dni agregado con el dni del chofer cuto nombre es choferTemp
            fail("Tendria que haber lanzado una excepcion");
        } catch (ChoferRepetidoException e){
            assertEquals(Mensajes.CHOFER_YA_REGISTRADO,e.getMessage());
        } catch (Exception e){
            fail("No lanzo la excepcion adecuada. " + e.getMessage());
        }
    } */
    
}