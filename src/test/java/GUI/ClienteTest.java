package test.java.GUI;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import util.Mensajes;
import controlador.Controlador;
import modeloDatos.Auto;
import modeloDatos.ChoferPermanente;
import modeloDatos.Vehiculo;
import modeloDatos.Chofer;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.Ventana;

public class ClienteTest {
    protected Robot robot;
	protected Controlador controlador;
	protected FalsoOptionPane op = new FalsoOptionPane();
	JButton aceptarLog, nuevopedido, cerrarSesion;
    JRadioButton zstandard, zsinasf, zpeligrosa;
	JTextField nombreUsuario, contrasenia, calificacion, cantpax, cantkm;
    JTextArea pedidoviaje;
    JCheckBox mascota, baul;

	public ClienteTest() {
		try {
			robot = new Robot();
		} catch (AWTException e) {}
	}

	@Before
	public void setUp() throws Exception {
		Empresa.getInstance().agregarCliente("tomitomitomi", "tomitomitomi", "tomas");
        Vehiculo auto = new Auto("asd123", 4, true);
        Chofer chofer = new ChoferPermanente("44690191", "Aragorn", 2024, 6);
        Empresa.getInstance().agregarChofer(chofer);
        Empresa.getInstance().agregarVehiculo(auto);
		controlador = new Controlador();
		controlador.getVista().setOptionPane(op);

		nombreUsuario = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		contrasenia = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.PASSWORD);
		aceptarLog = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LOGIN);

		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("tomitomitomi", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("tomitomitomi", robot);
		TestUtils.clickComponent(aceptarLog, robot);
		robot.delay(500);

        pedidoviaje = (JTextArea) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.PEDIDO_O_VIAJE_ACTUAL);
        mascota = (JCheckBox) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CHECK_MASCOTA);
        baul = (JCheckBox) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CHECK_BAUL);
        cantpax = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CANT_PAX);
        cantkm = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CANT_KM);
        zstandard = (JRadioButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.ZONA_STANDARD);
        zsinasf = (JRadioButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.ZONA_SIN_ASFALTAR);
        zpeligrosa = (JRadioButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.ZONA_PELIGROSA);
        nuevopedido = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        cerrarSesion = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);
    }

	@After
	public void tearDown() throws Exception {
		Ventana ventana = (Ventana) controlador.getVista();
		ventana.setVisible(false);
        Empresa.getInstance().getClientes().clear();
        Empresa.getInstance().getChoferes().clear();
        Empresa.getInstance().getVehiculos().clear();
	}    

	@Test
	public void testBotonCerrarSesion() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(cerrarSesion, robot);
        robot.delay(TestUtils.getDelay());
		JButton registroButton = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REGISTRAR);
		Assert.assertTrue("deberia abrirse un PaneldeRegistro", (registroButton instanceof JButton));
	}

    @Test
    public void testPedidoPaxNeg(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(cantpax, robot);
        TestUtils.tipeaTexto("-1", robot);
        TestUtils.clickComponent(cantkm, robot);
        TestUtils.tipeaTexto("0", robot);
        Assert.assertFalse("El boton de nuevo pedido deberia estar deshabilitado",nuevopedido.isEnabled());
    }

    @Test
    public void testPedidoPaxBig(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(cantpax, robot);
        TestUtils.tipeaTexto("11", robot);
        TestUtils.clickComponent(cantkm, robot);
        TestUtils.tipeaTexto("0", robot);
        Assert.assertFalse("El boton de nuevo pedido deberia estar deshabilitado",nuevopedido.isEnabled());
    }

    @Test
    public void testPedidoKmNeg(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(cantpax, robot);
        TestUtils.tipeaTexto("5", robot);
        TestUtils.clickComponent(cantkm, robot);
        TestUtils.tipeaTexto("-5", robot);
        Assert.assertFalse("El boton de nuevo pedido deberia estar deshabilitado",nuevopedido.isEnabled());
    }

    @Test
    public void testPedidoSinVehiculo(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(cantpax, robot);
        TestUtils.tipeaTexto("5", robot);
        TestUtils.clickComponent(cantkm, robot);
        TestUtils.tipeaTexto("5", robot);
        TestUtils.clickComponent(nuevopedido, robot);
		Assert.assertEquals("El pedido no deberia tener vehiculo",Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), op.getMensaje());
    }

    @Test
    public void testPedidoConVehiculo(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(cantpax, robot);
        TestUtils.tipeaTexto("3", robot);
        TestUtils.clickComponent(cantkm, robot);
        TestUtils.tipeaTexto("10", robot);
        TestUtils.clickComponent(nuevopedido, robot);
		Assert.assertFalse("El text de cant pax deberia estar deshabilitado",cantpax.isEnabled());
    }

    @Test
    public void testCalificarYPagar(){ //este lo pregunto mañana
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(cantpax, robot);
        TestUtils.tipeaTexto("3", robot);
        TestUtils.clickComponent(cantkm, robot);
        TestUtils.tipeaTexto("10", robot);
        TestUtils.clickComponent(nuevopedido, robot);
		TestUtils.clickComponent(cerrarSesion, robot);
        robot.delay(TestUtils.getDelay());

        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(contrasenia, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(aceptarLog, robot);
        robot.delay(TestUtils.getDelay());

        JList pedido = (JList) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
        TestUtils.clickComponent(pedido, robot);
        JList choferes = (JList) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
        TestUtils.clickComponent(choferes, robot);
        JList vehiculos = (JList) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
        TestUtils.clickComponent(vehiculos, robot);
        JButton nuevoviaje = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NUEVO_VIAJE);
        TestUtils.clickComponent(nuevoviaje, robot);
        JButton cierrasesion = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);
        TestUtils.clickComponent(cierrasesion, robot);
        robot.delay(TestUtils.getDelay());

		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("tomitomitomi", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("tomitomitomi", robot);
		TestUtils.clickComponent(aceptarLog, robot);
		robot.delay(TestUtils.getDelay());

        JTextField calificacion = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        TestUtils.clickComponent(calificacion, robot);
        TestUtils.tipeaTexto("5", robot);
        JButton pagar = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICAR_PAGAR);
        TestUtils.clickComponent(pagar, robot);
        Assert.assertTrue("El boton de cantpax deberia estar habilitado", cantpax.isEnabled());
    }
}
