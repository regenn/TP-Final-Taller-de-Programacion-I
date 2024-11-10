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
import util.Mensajes;
import GUI.FalsoOptionPane;
import vista.Ventana;

public class RegistrarTest {
    Empresa empresa;
    Controlador controlador;
    Usuario usuariologeado;
    FalsoOptionPane op;
    Ventana ventana;

    @Before
    public void setUp(){
        empresa=Empresa.getInstance();
        empresa.setClientes(new HashMap<String,Cliente>());
        controlador= new Controlador();
        op = new FalsoOptionPane();
        ventana = mock(Ventana.class);
        controlador.setVista(ventana);
		controlador.getVista().setOptionPane(op);
        when(ventana.getOptionPane()).thenReturn(op);
    }

    @After
    public void tearDown(){
        empresa.getChoferes().clear();
        empresa.getClientes().clear();
        empresa.getPedidos().clear();
    }

    @Test
    public void RegistroExitosoTest(){
        try{
            when(ventana.getRegNombreReal()).thenReturn("nombreNuevo");
            when(ventana.getRegUsserName()).thenReturn("usuarioNuevo");
            when(ventana.getRegPassword()).thenReturn("passNuevo");
            when(ventana.getRegConfirmPassword()).thenReturn("passNuevo");

            this.controlador.registrar();
            Usuario nuevoUsuario = empresa.getClientes().get("usuarioNuevo");
            assertNotNull(nuevoUsuario);

            assertEquals(ventana.getRegUsserName(),nuevoUsuario.getNombreUsuario());
            assertEquals(ventana.getRegPassword(),nuevoUsuario.getPass());

        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }

    }

    @Test
    public void RegistroConfirmacionNoExitosaTest(){
        try{  
            when(ventana.getRegNombreReal()).thenReturn("nombreNuevo");
            when(ventana.getRegUsserName()).thenReturn("usuarioNuevo");
            when(ventana.getRegPassword()).thenReturn("passNuevo");
            when(ventana.getRegConfirmPassword()).thenReturn("passOtra");

            this.controlador.registrar();//pass y confirm no coinciden
        } catch (Exception e){
        	assertEquals(Mensajes.PASS_NO_COINCIDE.getValor(), op.getMensaje());
        }
    }
}