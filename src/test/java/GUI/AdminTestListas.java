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
import excepciones.ChoferRepetidoException;
import excepciones.VehiculoRepetidoException;
import modeloDatos.Auto;
import modeloDatos.ChoferPermanente;
import modeloDatos.ChoferTemporario;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloDatos.Chofer;
import modeloNegocio.Empresa;
import util.Constantes;
import vista.Ventana;

public class AdminTestListas {

    protected Robot robot;
	protected Controlador controlador;
	protected FalsoOptionPane op = new FalsoOptionPane();
	JButton aceptarLog, cerrarSesion, aceptarChofer, aceptarVehiculo;
	JTextField nombreUsuario, contrasenia, dni, nombre, hijos, año, patente, plazas;
    JRadioButton permanente, temporario, auto, moto, combi;
    JCheckBox mascota;


	public AdminTestListas() {
		try {
			robot = new Robot();
		} catch (AWTException e) {}
	}

	@Before
	public void setUp() throws Exception {
		controlador = new Controlador();
		controlador.getVista().setOptionPane(op);

		nombreUsuario = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NOMBRE_USUARIO);
		contrasenia = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.PASSWORD);
		aceptarLog = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LOGIN);
        this.logAdm();
        dni = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.DNI_CHOFER);
        nombre = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NOMBRE_CHOFER);
        temporario = (JRadioButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.TEMPORARIO);
        permanente = (JRadioButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.PERMANENTE);
        hijos = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CH_CANT_HIJOS);
        año = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CH_ANIO);
        aceptarChofer = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NUEVO_CHOFER);
        patente = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.PATENTE);
        auto = (JRadioButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.AUTO);
        moto = (JRadioButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.MOTO);
        combi = (JRadioButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.COMBI);
        plazas = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CANTIDAD_PLAZAS);
        aceptarVehiculo = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NUEVO_VEHICULO);
        cerrarSesion = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CERRAR_SESION_ADMIN);
    }

	@After
	public void tearDown() throws Exception {
		Ventana ventana = (Ventana) controlador.getVista();
		ventana.setVisible(false);
        Empresa.getInstance().getPedidos().clear();
        Empresa.getInstance().getChoferes().clear();
        Empresa.getInstance().getVehiculos().clear();
        Empresa.getInstance().getVehiculosDesocupados().clear();
        Empresa.getInstance().getViajesTerminados().clear();
        Empresa.getInstance().getViajesIniciados().clear();
        Empresa.getInstance().getClientes().clear();
	}    

    public void agregaCombi(){
        TestUtils.clickComponent(combi, robot);
        TestUtils.clickComponent(patente, robot);
        TestUtils.tipeaTexto("asd123", robot);
        TestUtils.clickComponent(plazas, robot);
        TestUtils.tipeaTexto("5", robot);
        TestUtils.clickComponent(aceptarVehiculo, robot);
    }

    public void agregaAuto(){
        TestUtils.clickComponent(auto, robot);
        TestUtils.clickComponent(patente, robot);
        TestUtils.tipeaTexto("asd123", robot);
        TestUtils.clickComponent(plazas, robot);
        TestUtils.tipeaTexto("4", robot);
        TestUtils.clickComponent(aceptarVehiculo, robot);
    }

    public void agregaMoto(){
        TestUtils.clickComponent(moto, robot);
        TestUtils.clickComponent(patente, robot);
        TestUtils.tipeaTexto("asd123", robot);
        TestUtils.clickComponent(aceptarVehiculo, robot);
    }

    public void agregaTemp(){
        TestUtils.clickComponent(temporario, robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("A", robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("1", robot);
        TestUtils.clickComponent(aceptarChofer, robot);
    }
    
    public void logAdm(){
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(contrasenia, robot);
        TestUtils.tipeaTexto("admin", robot);
        TestUtils.clickComponent(aceptarLog, robot);
        robot.delay(500);
    }

    public void logCliente(){
        TestUtils.clickComponent(nombreUsuario, robot);
        TestUtils.tipeaTexto("tomitomitomi", robot);
        TestUtils.clickComponent(contrasenia, robot);
        TestUtils.tipeaTexto("tomitomitomi", robot);
        TestUtils.clickComponent(aceptarLog, robot);
        robot.delay(500);
    }

    public void regCliente(){
        JButton registroButton = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(),Constantes.REGISTRAR);
		TestUtils.clickComponent(registroButton, robot);
		robot.delay(500);
		registroButton = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REG_BUTTON_REGISTRAR);
		JTextField nombreUsuario = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REG_USSER_NAME);
		JTextField contrasenia = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REG_PASSWORD);
		JTextField confirmarContrasenia = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REG_CONFIRM_PASSWORD);
		JTextField nombreReal = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.REG_REAL_NAME);  
		TestUtils.clickComponent(nombreUsuario, robot);
		TestUtils.tipeaTexto("tomitomitomi", robot);
		TestUtils.clickComponent(contrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(confirmarContrasenia, robot);
		TestUtils.tipeaTexto("123456", robot);
		TestUtils.clickComponent(nombreReal, robot);
		TestUtils.tipeaTexto("Tomas Salig", robot);
		TestUtils.clickComponent(registroButton, robot);
		robot.delay(500);
    }

    public void generaPedido(){
        TestUtils.clickComponent(cerrarSesion, robot);
        robot.delay(500);
        this.logCliente();

        JTextField cantpax = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CANT_PAX);
        JTextField cantkm = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CANT_KM);
        JButton nuevopedido = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);

        TestUtils.clickComponent(cantpax, robot);
        TestUtils.tipeaTexto("3", robot);
        TestUtils.clickComponent(cantkm, robot);
        TestUtils.tipeaTexto("10", robot);
        TestUtils.clickComponent(nuevopedido, robot);
        TestUtils.clickComponent(cerrarSesion, robot);
        robot.delay(500);
        this.logAdm();
    }

    public void generaPedidoParaMoto(){
        TestUtils.clickComponent(cerrarSesion, robot);
        robot.delay(500);
        this.logCliente();

        JTextField cantpax = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CANT_PAX);
        JTextField cantkm = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CANT_KM);
        JButton nuevopedido = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NUEVO_PEDIDO);
        JButton cerrarSesion = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);

        TestUtils.clickComponent(cantpax, robot);
        TestUtils.tipeaTexto("1", robot);
        TestUtils.clickComponent(cantkm, robot);
        TestUtils.tipeaTexto("10", robot);
        TestUtils.clickComponent(nuevopedido, robot);
        TestUtils.clickComponent(cerrarSesion, robot);
        robot.delay(500);
        this.logAdm();
    }

    public void selectListasViaje(){
        JList<String> pedido = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
        pedido.setSelectedIndex(0);
        TestUtils.clickComponent(pedido, robot);
        JList<String> choferes = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
        choferes.setSelectedIndex(0);
        TestUtils.clickComponent(choferes, robot);
        JList<String> vehiculos = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
        vehiculos.setSelectedIndex(0);
        TestUtils.clickComponent(vehiculos, robot);
    }  

    public void calificaYpaga(){
        TestUtils.clickComponent(cerrarSesion, robot);
        this.logCliente();
        JTextField calificacion = (JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICACION_DE_VIAJE);
        TestUtils.clickComponent(calificacion, robot);
        TestUtils.tipeaTexto("5", robot);
        JButton pagar = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICAR_PAGAR);
        TestUtils.clickComponent(pagar, robot);
        cerrarSesion = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CERRAR_SESION_CLIENTE);
        TestUtils.clickComponent(cerrarSesion, robot);
    }

        @Test
    public void testSueldosVacio(){
        robot.delay(TestUtils.getDelay());
        Assert.assertTrue("No deberian aparecer sueldos",((JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.TOTAL_SUELDOS_A_PAGAR)).getText().equals("0,00"));
    }
    
    @Test
    public void testSueldos(){
        robot.delay(TestUtils.getDelay());
        Chofer choferaux = new ChoferTemporario("44690191", "Tomas Salig");
        try{Empresa.getInstance().agregarChofer(choferaux);} catch(ChoferRepetidoException e){}
        Assert.assertFalse("Deberian aparecer sueldos",((JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.TOTAL_SUELDOS_A_PAGAR)).getText().trim().isEmpty());
    }    

    @Test
    public void testViajesVacio(){
        robot.delay(TestUtils.getDelay());
        Assert.assertTrue("No deberian aparecer viajes",((JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VIAJES_HISTORICOS)).getModel().getSize()==0);
    }

    @Test
    public void testVehiculosTotalesVacio(){
        robot.delay(TestUtils.getDelay());
        Assert.assertTrue("No deberian aparecer vehiculos",((JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VEHICULOS_TOTALES)).getModel().getSize()==0);
    }

    @Test
    public void testVehiculosTotales(){
        robot.delay(TestUtils.getDelay());
        this.agregaCombi();
        Assert.assertFalse("Deberian aparecer vehiculos",((JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VEHICULOS_TOTALES)).getModel().getSize()==0);
    }

    @Test
    public void testClientesTotalesVacio(){
        robot.delay(TestUtils.getDelay());
        Assert.assertTrue("No deberian aparecer clientes",((JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTADO_DE_CLIENTES)).getModel().getSize()==0);
    }

    @Test
    public void testClientesTotales(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(cerrarSesion, robot);
        robot.delay(500);
        this.regCliente();
        this.logAdm();
        Assert.assertFalse("Deberian aparecer clientes",((JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTADO_DE_CLIENTES)).getModel().getSize()==0);
    }

    @Test
    public void testChoferesTotalesSinSeleccionar(){
        robot.delay(TestUtils.getDelay());
        Assert.assertTrue("No deberian aparecer viajes",((JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VIAJES_DE_CHOFER)).getModel().getSize()==0);
        Assert.assertTrue("No deberia aparece la calificacion",((JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICACION_CHOFER)).getText().trim().isEmpty());
        Assert.assertTrue("No deberia aparecer el sueldo",((JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.SUELDO_DE_CHOFER)).getText().trim().isEmpty());  
    }

    @Test
    public void testChoferesTotalesSeleccionado(){
        robot.delay(TestUtils.getDelay());
        try{Empresa.getInstance().agregarCliente("tomitomitomi", "tomitomitomi", "tomi");}catch(Exception e){}
        this.agregaCombi();
        this.agregaTemp();
        this.generaPedido();
        this.selectListasViaje();
        JButton nuevoviaje = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NUEVO_VIAJE);
        TestUtils.clickComponent(nuevoviaje, robot); 
        this.calificaYpaga();
        this.logAdm();
        JList<String> listaChoferes = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_CHOFERES_TOTALES);
        listaChoferes.setSelectedIndex(0);
        TestUtils.clickComponent(listaChoferes, robot);
        Assert.assertTrue("Deberian aparecer viajes",((JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VIAJES_DE_CHOFER)).getModel().getSize()>0);
        Assert.assertFalse("Deberia aparecer la calificacion",((JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.CALIFICACION_CHOFER)).getText().trim().isEmpty());
        Assert.assertFalse("Deberia aparecer el sueldo",((JTextField) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.SUELDO_DE_CHOFER)).getText().trim().isEmpty());  
    }

    @Test
    public void testListaSinPedidos(){
        robot.delay(TestUtils.getDelay());
        JList<String> pedido = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
        Assert.assertTrue("La lista de pedidos deberia estar vacia",pedido.getModel().getSize()==0);
    } 

    @Test
    public void testListaConPedidos(){
        robot.delay(TestUtils.getDelay());
        try{Empresa.getInstance().agregarCliente("tomitomitomi", "tomitomitomi", "tomi");}catch(Exception e){}
        this.agregaMoto();
        this.generaPedidoParaMoto();
        JList<String> pedido = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
        Assert.assertTrue("La lista de pedidos deberia tener el pedido",pedido.getModel().getSize()>0);
    } 

    @Test
    public void testListaSinChoferes(){
        robot.delay(TestUtils.getDelay());
        JList<String> choferes = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
        Assert.assertTrue("La lista de choferes deberia estar vacia",choferes.getModel().getSize()==0);
    } 

    @Test
    public void testListaConChoferes(){
        robot.delay(TestUtils.getDelay());
        this.agregaTemp();
        JList<String> choferes = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_CHOFERES_LIBRES);
        Assert.assertTrue("La lista de choferes deberia tener al chofer",choferes.getModel().getSize()>0);
    }

    @Test
    public void testListaVehiculosSinTocarPedido(){
        robot.delay(TestUtils.getDelay());
        JList<String> vehiculos = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
        Assert.assertTrue("La lista de pedidos deberia estar vacia",vehiculos.getModel().getSize()==0);
    } 

    @Test
    public void testListaVehiculosSinPedido(){
        robot.delay(TestUtils.getDelay());
        try{Empresa.getInstance().agregarCliente("tomitomitomi", "tomitomitomi", "tomi");}catch(Exception e){}
        this.agregaMoto();
        this.generaPedido();
        JList<String> pedido = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
        pedido.setSelectedIndex(0);
        TestUtils.clickComponent(pedido, robot);
        JList<String> vehiculos = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
        Assert.assertTrue("La lista de vehiculos deberia estar vacia",vehiculos.getModel().getSize()==0);
    }

    @Test
    public void testListaVehiculosConPedido(){
        robot.delay(TestUtils.getDelay());
        try{Empresa.getInstance().agregarCliente("tomitomitomi", "tomitomitomi", "tomi");}catch(Exception e){}
        this.agregaMoto();
        this.generaPedidoParaMoto();
        JList<String> pedido = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_PEDIDOS_PENDIENTES);
        pedido.setSelectedIndex(0);
        TestUtils.clickComponent(pedido, robot);
        JList<String> vehiculos = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VEHICULOS_DISPONIBLES);
        Assert.assertTrue("La lista de vehiculos no deberia estar vacia",vehiculos.getModel().getSize()>0);
    }

    @Test
    public void testListaViajesHistoricos(){
        robot.delay(TestUtils.getDelay());
        try{Empresa.getInstance().agregarCliente("tomitomitomi", "tomitomitomi", "tomi");}catch(Exception e){}
        this.agregaCombi();
        this.agregaTemp();
        this.generaPedido();
        this.selectListasViaje();
        JButton nuevoviaje = (JButton) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.NUEVO_VIAJE);
        TestUtils.clickComponent(nuevoviaje, robot); 
        this.calificaYpaga();
        this.logAdm();
        JList<String> historicoViajes = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VIAJES_HISTORICOS);
        Assert.assertFalse("La lista de viajes deberia no estar vacia",historicoViajes.getModel().getSize()==0);
    } 
}
