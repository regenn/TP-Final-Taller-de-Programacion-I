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
import util.Mensajes;
import test.GUI.FalsoOptionPane;
import vista.Ventana;//??

public class RegistrarTest {
    Empresa empresa;
    Controlador controlador;
    Usuario UsuarioNuevo;

    @Before
    public void setUp(){
        empresa = Empresa.getInstance();
        controlador = new Controlador();
    }
    /* Se reciben los parametros necesarios para un nuevo cliente del atributo vista:
    String nombreReal = this.vista.getRegNombreReal()
    String nombreUsuario = this.vista.getRegUsserName()
    String pass = this.vista.getRegPassword()
    String confirm = this.vista.getRegConfirmPassword()
    Si "pass" y "confirm" no coinciden, entonces se delega en el atributo vista mostrar el mensaje correspondiente a Mensajes.PASS_NO_COINCIDE.getValor()
    Si "pass" y "confirm" coinciden, se invoca al metodo agregarCliente(nombreUsuario, pass, nombreReal) de la clase Empresa.
    Si la accion no se puede realizar entonces se delega en el atributo vista mostrar el mensaje correspondiente a la excepcion lanzada 
    */
    @Test
    public void RegistroExitosoTest(){
        try{
            Ventana ventana = mock(Ventana.class);
            when(ventana.getRegNombreReal()).thenReturn("nombreNuevo");
            when(ventana.getRegUsserName()).thenReturn("usuarioNuevo");
            when(ventana.getRegPassword()).thenReturn("passNuevo");
            when(ventana.getRegConfirmPassword()).thenReturn("passNuevo");
            this.controlador.setVista(ventana);

            this.controlador.registrar();//pass y confirm coinciden -> se invoca a agregarCliente(...)
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
            Ventana ventana = mock(Ventana.class);
            FalsoOptionPane op = new FalsoOptionPane();
            when(ventana.getRegNombreReal()).thenReturn("nombreNuevo");
            when(ventana.getRegUsserName()).thenReturn("usuarioNuevo");
            when(ventana.getRegPassword()).thenReturn("passNuevo");
            when(ventana.getRegConfirmPassword()).thenReturn("passOtra");

            this.controlador.setVista(ventana);
            this.controlador.getVista().setOptionPane(op);

            this.controlador.registrar();//pass y confirm no coinciden
            assertEquals(Mensajes.PASS_NO_COINCIDE.getValor(),op.getMensaje());
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }
}
