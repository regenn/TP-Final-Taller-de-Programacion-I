package integracion;

import static org.junit.Assert.*;
import org.junit.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;

import controlador.*;
import util.Mensajes;
import GUI.FalsoOptionPane;
import vista.Ventana;

public class LoginTest {
    Empresa empresa;
    Controlador controlador;
    Usuario usuariologeado;
    FalsoOptionPane op;
    Ventana ventana;

    @Before
    public void setUp() throws Exception{

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
    public void tearDown() throws Exception{
        empresa.getChoferes().clear();
        empresa.getClientes().clear();
        empresa.getPedidos().clear();
    }

    @Test 
    public void LoginClienteExitosoTest(){
        try{
            empresa.agregarCliente("usuario1","contrasenia1","nombre");
            usuariologeado= this.empresa.login("usuario1","contrasenia1");
            this.empresa.setUsuarioLogeado(usuariologeado);
        }
        catch(Exception e){}

        when(ventana.getUsserName()).thenReturn("usuario1");
        when(ventana.getPassword()).thenReturn("contrasenia1");
        this.controlador.login();

        assertEquals(usuariologeado,this.empresa.getUsuarioLogeado());
    }

    @Test
    public void LoginUsuarioNoExisteTest(){

        try{
           empresa.agregarCliente("usuario1","contrasenia1","nombre");
           usuariologeado= this.empresa.login("usuario1","contrasenia1");
        }
        catch(Exception e){}

        when(ventana.getUsserName()).thenReturn("usuario2");
        when(ventana.getPassword()).thenReturn("contrasenia1");
        this.controlador.login();

        assertEquals(Mensajes.USUARIO_DESCONOCIDO.getValor(),op.getMensaje());
    }

    @Test
    public void LoginContraseniaIncorrectaTest(){
        try{
            empresa.agregarCliente("usuario1","contrasenia1","nombre");
            usuariologeado= this.empresa.login("usuario1","contrasenia1");
        }
        catch(Exception e){}
        
        when(ventana.getUsserName()).thenReturn("usuario1");
        when(ventana.getPassword()).thenReturn("contraseña2");
        this.controlador.login();

        assertEquals(Mensajes.PASS_ERRONEO.getValor(),op.getMensaje());
    }
}
