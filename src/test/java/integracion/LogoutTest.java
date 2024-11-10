package integracion;
import static org.junit.Assert.*;
import org.junit.*;

import static org.mockito.Mockito.mock;//hay que instalar algo?
import static org.mockito.Mockito.when;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;
import controlador.*;
import util.Mensajes;
import GUI.FalsoOptionPane;
import GUI.TestUtils;
import vista.Ventana;//??
import util.Constantes;

import javax.swing.JPanel;

public class LogoutTest {
    Empresa empresa;
    Controlador controlador;
    Usuario usuariologeado;
    FalsoOptionPane op;
    Ventana ventana;
    JPanel panelLogin;

    @Before
    public void setUp(){

        empresa=Empresa.getInstance();
        controlador= new Controlador();
        op = new FalsoOptionPane();
        ventana = mock(Ventana.class);
        controlador.setVista(ventana);
		controlador.getVista().setOptionPane(op);
        when(ventana.getOptionPane()).thenReturn(op);

        usuariologeado= new Cliente("usuario1","contrasenia1","Cliente1");
        try{
            empresa.agregarCliente("usuario1","contrasenia1","nombre");
            usuariologeado= this.empresa.login("usuario1","contrasenia1");
            this.empresa.setUsuarioLogeado(usuariologeado);
        }
        catch(Exception e){}

        when(ventana.getUsserName()).thenReturn("usuario1");
        when(ventana.getPassword()).thenReturn("contrasenia1");
        this.controlador.login();

    }

    @After
    public void tearDown(){
        empresa.getChoferes().clear();
        empresa.getClientes().clear();
        empresa.getPedidos().clear();
    }

    @Test
    public void LogoutUsuarioExitosoTest(){
        assertNotNull(empresa.getUsuarioLogeado());
        this.controlador.logout();
        assertNull(empresa.getUsuarioLogeado());
        //assertTrue("Deberia abrirse un nuevo panel de inicio de sesion",.isEnabled());
        //panelLogin = (JPanel) TestUtils.getComponentForName((Ventana) controlador.getVista(),Constantes.PANEL_LOGIN);//
		//Assert.assertTrue("deberia abrirse un PaneldeRegistro", panelLogin.isEnabled());
        //Assert.assertTrue("deberia abrirse un PaneldeRegistro", panelLogin.isEnabled());
    }
}
