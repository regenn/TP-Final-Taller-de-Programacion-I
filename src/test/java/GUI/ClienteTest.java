package GUI;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
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
	JButton aceptarLog, nuevopedido, cerrarSesion, pagar;
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

    public void generaPedido(){
        TestUtils.clickComponent(cantpax, robot);
        TestUtils.tipeaTexto("3", robot);
        TestUtils.clickComponent(cantkm, robot);
        TestUtils.tipeaTexto("10", robot);
        TestUtils.clickComponent(nuevopedido, robot);
    }

    public void generaPedidoParaCombi(){
        TestUtils.clickComponent(cantpax, robot);
        TestUtils.tipeaTexto("5", robot);
        TestUtils.clickComponent(cantkm, robot);
        TestUtils.tipeaTexto("10", robot);
        TestUtils.clickComponent(nuevopedido, robot);
    }

    public void admCheckPedido(){
        TestUtils.clickComponent(cerrarSesion, robot);
        robot.delay(500);

        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(contrasenia, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(aceptarLog, robot);
        robot.delay(500);
        
        JList<String> pedido = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
        pedido.setSelectedIndex(0);
        TestUtils.clickComponent(pedido, robot);
        JList<String> choferes = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
        choferes.setSelectedIndex(0);
        TestUtils.clickComponent(choferes, robot);
        JList<String> vehiculos = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
        vehiculos.setSelectedIndex(0);
        TestUtils.clickComponent(vehiculos, robot);
        JButton nuevoviaje = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NUEVO_VIAJE);
        TestUtils.clickComponent(nuevoviaje, robot);
        JButton cierrasesion = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);
        TestUtils.clickComponent(cierrasesion, robot);
        robot.delay(500);

		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("tomitomitomi", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("tomitomitomi", robot);
		TestUtils.clickComponent(aceptarLog, robot);
		robot.delay(500);
    }

	@Test
	public void testBotonCerrarSesion() {
		robot.delay(TestUtils.getDelay());
		TestUtils.clickComponent(cerrarSesion, robot);
        robot.delay(TestUtils.getDelay());
		JPanel panelLogin = (JPanel) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.PANEL_LOGIN);
		Assert.assertTrue("deberia abrirse un PaneldeRegistro", panelLogin.isEnabled());
	}

    @Test
    public void testPedidoSinPax(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(cantkm, robot);
        TestUtils.tipeaTexto("0", robot);
        Assert.assertFalse("El boton de nuevo pedido deberia estar deshabilitado",nuevopedido.isEnabled());
    }

    @Test
    public void testPedidoSinKm(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(cantpax, robot);
        TestUtils.tipeaTexto("1", robot);
        Assert.assertFalse("El boton de nuevo pedido deberia estar deshabilitado",nuevopedido.isEnabled());
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
    public void testPagarYCalificarOff(){
        robot.delay(TestUtils.getDelay());
        calificacion = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        JTextField valor = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.VALOR_VIAJE);
        Assert.assertFalse("El text de calificar deberia estar deshabilitado", calificacion.isEnabled());
        Assert.assertTrue("El text de pagar deberia estar vacio", !valor.isEditable());
    }

    @Test
    public void testPedidoSinVehiculo(){
        robot.delay(TestUtils.getDelay());
        this.generaPedidoParaCombi();
		Assert.assertEquals("El pedido no deberia tener vehiculo",Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), op.getMensaje());
    }

    @Test
    public void testPedidoConVehiculo(){
        robot.delay(TestUtils.getDelay());
        this.generaPedido();
        
		Assert.assertFalse("El text de cant pax deberia estar deshabilitado",cantpax.isEnabled());
		Assert.assertTrue("El text de cant pax deberia estar vacio",cantpax.getText().trim().isEmpty());
		Assert.assertFalse("El text de cant km deberia estar deshabilitado",cantkm.isEnabled());
		Assert.assertTrue("El text de cant km deberia estar vacio",cantkm.getText().trim().isEmpty());
		Assert.assertFalse("El text de zona standard deberia estar deshabilitado",zstandard.isEnabled());
		Assert.assertFalse("El text de zona peligrosa deberia estar deshabilitado",zpeligrosa.isEnabled());
		Assert.assertFalse("El text de zona sin asfaltar deberia estar deshabilitado",zsinasf.isEnabled());
		Assert.assertFalse("El baul deberia estar deshabilitado",baul.isEnabled());
		Assert.assertFalse("La mascota deberia estar deshabilitado",mascota.isEnabled());
		Assert.assertFalse("El nuevo pedido deberia estar deshabilitado",nuevopedido.isEnabled());

        calificacion = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        JTextField valor = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.VALOR_VIAJE);
        Assert.assertFalse("El text de calificar deberia estar deshabilitado", calificacion.isEnabled());
        Assert.assertTrue("El text de pagar deberia estar vacio", !valor.isEditable());
    }

    @Test
    public void testCalificarYPagar(){ //este lo pregunto mañana
        robot.delay(TestUtils.getDelay());
        this.generaPedido();
        this.admCheckPedido();

		Assert.assertFalse("El text de cant pax deberia estar deshabilitado",cantpax.isEnabled());
		Assert.assertFalse("El text de cant km deberia estar deshabilitado",cantkm.isEnabled());
		Assert.assertFalse("El text de zona standard deberia estar deshabilitado",zstandard.isEnabled());
		Assert.assertFalse("El text de zona peligrosa deberia estar deshabilitado",zpeligrosa.isEnabled());
		Assert.assertFalse("El text de zona sin asfaltar deberia estar deshabilitado",zsinasf.isEnabled());
		Assert.assertFalse("El baul deberia estar deshabilitado",baul.isEnabled());
		Assert.assertFalse("La mascota deberia estar deshabilitado",mascota.isEnabled());
		Assert.assertFalse("El nuevo pedido deberia estar deshabilitado",nuevopedido.isEnabled());

        calificacion = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        TestUtils.clickComponent(calificacion, robot);
        TestUtils.tipeaTexto("5", robot);
        pagar = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICAR_PAGAR);
        TestUtils.clickComponent(pagar, robot);
        Assert.assertTrue("El pedido actual deberia estar vacio", pedidoviaje.getText().trim().isEmpty());
        Assert.assertTrue("La calificacion deberia estar vacia", calificacion.getText().trim().isEmpty());

        JList<String> historicoviajes = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VIAJES_CLIENTE);
        Assert.assertTrue("El historico deberia tener el viaje", historicoviajes.getModel().getSize()>0);
    }

    @Test
    public void testCalificarMaxYPagar(){ //este lo pregunto mañana
        robot.delay(TestUtils.getDelay());
        this.generaPedido();
        this.admCheckPedido();

        calificacion = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        TestUtils.clickComponent(calificacion, robot);
        TestUtils.tipeaTexto("6", robot);
        pagar = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICAR_PAGAR);
        TestUtils.clickComponent(pagar, robot);
        Assert.assertFalse("El boton pagar deberia estar deshabilitado", pagar.isEnabled());
    }

    @Test
    public void testCalificarNegYPagar(){
        robot.delay(TestUtils.getDelay());
        this.generaPedido();
        this.admCheckPedido();

        calificacion = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        TestUtils.clickComponent(calificacion, robot);
        TestUtils.tipeaTexto("-1", robot);
        pagar = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICAR_PAGAR);
        TestUtils.clickComponent(pagar, robot);
        Assert.assertFalse("El boton pagar deberia estar deshabilitado", pagar.isEnabled());
    }
}
