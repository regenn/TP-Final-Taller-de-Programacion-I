package GUI;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import controlador.Controlador;
import excepciones.UsuarioYaExisteException;
import modeloNegocio.Empresa;
import util.Mensajes;
import util.Constantes;
import vista.Ventana;

public class LoginTest {

	protected Robot robot;
	protected Controlador controlador;

	protected FalsoOptionPane op = new FalsoOptionPane();
	JButton aceptarLog, registroButton, cancelarButton;
	JTextField contra, nombreUsuario;
	public LoginTest() {
		try {
			robot = new Robot();
		} catch (AWTException e) {

		}
	}
/* */
	@Before
	public void setUp() throws Exception {
		Empresa.getInstance().getClientes().clear();
		controlador = new Controlador();
		controlador.getVista().setOptionPane(op);
		nombreUsuario = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		contra = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.PASSWORD);
		aceptarLog = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LOGIN);
		registroButton = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REGISTRAR);
		cancelarButton = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REG_BUTTON_CANCELAR);
	}

	@After
	public void tearDown() throws Exception {
		Ventana v = (Ventana) controlador.getVista();
		v.setVisible(false);
	}

	@Test
	public void testBotonRegistro() {
		robot.delay(TestUtils.getDelay());
	
		Assert.assertTrue("El boton de registro deberia estar habilitado", registroButton.isEnabled());
		TestUtils.clickComponent(registroButton, robot);
		robot.delay(TestUtils.getDelay());	
		Assert.assertTrue("deberia abrirse un PaneldeRegistro", cancelarButton.isEnabled());
	}

	@Test
	public void testVacio() {
		robot.delay(TestUtils.getDelay());
		Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	}

	public void testSoloContrasena() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(contra, robot);
		TestUtils.tipeaTexto("1234", robot);
		Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	}

	@Test
	public void testSoloNombre() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Tomson", robot);
		Assert.assertFalse("El boton de login deberia estar deshablitado", aceptarLog.isEnabled());
	}

	@Test
	public void testAmbosLlenos() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Tomson", robot);
		TestUtils.clickComponent(contra, robot);
		TestUtils.tipeaTexto("1234", robot);
		Assert.assertFalse("El boton de login deberia estar habilitado", !aceptarLog.isEnabled());
	}

	@Test
	public void testLoginUsuarioDesconocido() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Tomson", robot);
		TestUtils.clickComponent(contra, robot);
		TestUtils.tipeaTexto("1234", robot);
		TestUtils.clickComponent(aceptarLog, robot);

		Assert.assertEquals(Mensajes.USUARIO_DESCONOCIDO.getValor(), op.getMensaje());
	}

	@Test
	public void testAdminLog() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(contra, robot);
		TestUtils.tipeaTexto("admin", robot);
		TestUtils.clickComponent(aceptarLog, robot);
		robot.delay(500);
		Assert.assertTrue("deberia haber logeado",((JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN)).isEnabled());
	}

	@Test
	public void testLogCorrecto() {
		robot.delay(TestUtils.getDelay());
        try{
            Empresa.getInstance().agregarCliente("tomitomitomi", "123456", "Tomas Salig");
        }
        catch(UsuarioYaExisteException e){}

		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("tomitomitomi", robot);
		TestUtils.clickComponent(contra, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(aceptarLog, robot);
		robot.delay(500);
		Assert.assertTrue("deberia haber logeado",((JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE)).isEnabled());
	}

	@Test
	public void testLogContraIncorrecta() {
		robot.delay(TestUtils.getDelay());
        try{
            Empresa.getInstance().agregarCliente("tomitomitomi", "123456", "Tomas Salig");
        }
        catch(UsuarioYaExisteException e){}

		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("tomitomitomi", robot);
		TestUtils.clickComponent(contra, robot);
		TestUtils.tipeaTexto("654321", robot);
		TestUtils.clickComponent(aceptarLog, robot);
		robot.delay(500);
		Assert.assertEquals(Mensajes.PASS_ERRONEO.getValor(), op.getMensaje());
	}
