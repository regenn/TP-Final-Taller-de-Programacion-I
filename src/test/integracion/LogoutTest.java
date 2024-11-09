package test.integracion;

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
import test.GUI.FalsoOptionPane;
import test.GUI.TestUtils;
import vista.Ventana;//??
import util.Constantes;

import javax.swing.JPanel;
public class LogoutTest {
    Empresa empresa;
    Controlador controlador;
    Usuario usuariologeado;
    @Before
     public void setUp() throws Exception{ //y el catch?
        empresa=Empresa.getInstance();
        empresa.setClientes(new HashMap<String,Cliente>());
        controlador= new Controlador();
        usuariologeado= new Cliente("usuario1","contrasenia1","Cliente1");
    }   

    @After
    public void tearDown() throws Exception{
     //necesitaria algo que me borre los usuarios
    }

    @Test
    public void LogoutUsuarioExitosoTest(){
        JPanel panelLogin;
        FalsoOptionPane op= new FalsoOptionPane();
        try{
            empresa.agregarCliente("usuario1","contrasenia1","nombre");
            usuariologeado= this.empresa.login("usuario1","contrasenia1");
            this.empresa.setUsuarioLogeado(usuariologeado);
        }
        catch(Exception e){

        }
        Ventana ventana= mock(Ventana.class);
        this.controlador.setVista(ventana);
        this.controlador.getVista().setOptionPane(op);
        this.controlador.logout();
        //assertTrue("Deberia abrirse un nuevo panel de inicio de sesion",.isEnabled());
        panelLogin = (JPanel) TestUtils.getComponentForName((Ventana) controlador.getVista(),Constantes.PANEL_LOGIN);//
		Assert.assertTrue("deberia abrirse un PaneldeRegistro", panelLogin.isEnabled());
        //Assert.assertTrue("deberia abrirse un PaneldeRegistro", panelLogin.isEnabled());
    }
}
