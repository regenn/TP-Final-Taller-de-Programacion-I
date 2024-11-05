package test.GUI;


import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.UsuarioYaExisteException;
import util.Mensajes;
import controlador.Controlador;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.Ventana;


public class RegTest {
	protected Robot robot;
	protected Controlador controlador;
	protected FalsoOptionPane op = new FalsoOptionPane();
	JButton registroButton, cancelarButton;
	JTextField nombreUsuario, contrasenia, confirmarContrasenia, nombreReal;

	public RegTest() {
		try {
			robot = new Robot();
		} catch (AWTException e) {}
	}

	@Before
	public void setUp() throws Exception {
		Empresa.getInstance().getClientes().clear();
		controlador = new Controlador();
		controlador.getVista().setOptionPane(op);
        //mouskerramienta que nos ayudara mas tarde
		registroButton = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(),Constantes.REGISTRAR);
		TestUtils.clickComponent(registroButton, robot);
		robot.delay(500);
		registroButton = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		cancelarButton = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REG_BUTTON_CANCELAR);
		nombreUsuario = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REG_USSER_NAME);
		contrasenia = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REG_PASSWORD);
		confirmarContrasenia = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
		nombreReal = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REG_REAL_NAME);  
    }

	@After
	public void tearDown() throws Exception {
		Ventana ventana = (Ventana) controlador.getVista();
		ventana.setVisible(false);
	}    

	@Test
	public void testBotonCancelar() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(cancelarButton, robot);
		registroButton = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REGISTRAR);
		Assert.assertTrue("deberia abrirse un PaneldeRegistro", (registroButton instanceof JButton));
	}

	@Test
	public void testRegVacio() {
		robot.delay(TestUtils.getDelay());
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registroButton.isEnabled());
	}

	@Test
	public void testRegSoloNombre() {
		robot.delay(TestUtils.getDelay());
    	TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Paul Mescal", robot);
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registroButton.isEnabled());
	}

	@Test
	public void testRegSoloContrasenia() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registroButton.isEnabled());
	}

	@Test
	public void testRegSoloConfirmarContrasenia() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(confirmarContrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registroButton.isEnabled());
	}

	@Test
	public void testRegSoloNombreReal() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(nombreReal, robot);
		TestUtils.tipeaTexto("Brad Pitt", robot);
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registroButton.isEnabled());
	}

	@Test
	public void testRegSinUsuario() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(confirmarContrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(nombreReal, robot);
		TestUtils.tipeaTexto("Pedro Pascal", robot);
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registroButton.isEnabled());
	}

	@Test
	public void testRegSinConstrasenia() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Gandalf777", robot);
		TestUtils.clickComponent(confirmarContrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(nombreReal, robot);
		TestUtils.tipeaTexto("Frodo Bolson", robot);
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registroButton.isEnabled());
	}

	@Test
	public void testRegSinConstraseniaConf() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("DarthVader", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(nombreReal, robot);
		TestUtils.tipeaTexto("Anakin Skywalker", robot);
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registroButton.isEnabled());
	}

	@Test
	public void testRegSinNombreReal() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("Anonymous", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(confirmarContrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		Assert.assertFalse("El boton de registro deberia estar deshabilitado", registroButton.isEnabled());
	}

	@Test
	public void testRegCompleto() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("tomitomitomi", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(confirmarContrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(nombreReal, robot);
		TestUtils.tipeaTexto("Tomas Salig", robot);
		Assert.assertTrue("El boton de registro deberia estar habilitado", registroButton.isEnabled());

		TestUtils.clickComponent(registroButton, robot);
		robot.delay(TestUtils.getDelay());
		registroButton = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REGISTRAR);
		Assert.assertTrue("deberia abrirse un PaneldeRegistro", (registroButton instanceof JButton));
	}

	@Test
	public void testRegContraIncorrecta() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("tomitomitomi", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(confirmarContrasenia, robot);
		TestUtils.tipeaTexto("654321", robot);
		TestUtils.clickComponent(nombreReal, robot);
		TestUtils.tipeaTexto("Tomas Salig", robot);

		TestUtils.clickComponent(registroButton, robot);
		Assert.assertEquals("La contraseña no deberia coincidir",Mensajes.PASS_NO_COINCIDE.getValor(), op.getMensaje());
	}

	@Test
	public void testRegUsuarioRepe() {
		robot.delay(TestUtils.getDelay());

        try{
            Empresa.getInstance().agregarCliente("tomitomitomi", "123456", "Tomas Salig");
        }
        catch(UsuarioYaExisteException e){}

		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("tomitomitomi", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(confirmarContrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(nombreReal, robot);
		TestUtils.tipeaTexto("Tomas Salig", robot);

		TestUtils.clickComponent(registroButton, robot);
		Assert.assertEquals(Mensajes.USUARIO_REPETIDO.getValor(), op.getMensaje());
	}

}
