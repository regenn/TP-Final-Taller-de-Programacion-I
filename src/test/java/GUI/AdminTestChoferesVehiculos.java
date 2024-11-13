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

public class AdminTestChoferesVehiculos{

    protected Robot robot;
	protected Controlador controlador;
	protected FalsoOptionPane op = new FalsoOptionPane();
	JButton aceptarLog, cerrarSesion, aceptarChofer, aceptarVehiculo;
	JTextField nombreUsuario, contrasenia, dni, nombre, hijos, año, patente, plazas;
    JRadioButton permanente, temporario, auto, moto, combi;
    JCheckBox mascota;


	public AdminTestChoferesVehiculos() {
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

    @Test
    public void testChoferTempSoloNombre(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(temporario, robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("a", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    } 

    @Test
    public void testChoferTempSoloDNI(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(temporario, robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("1", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    } 

    @Test
    public void testChoferTempRepe(){
        robot.delay(TestUtils.getDelay());

        Chofer chofer = new ChoferTemporario("44690191", "Tomas Salig");
        try{Empresa.getInstance().agregarChofer(chofer);} catch (ChoferRepetidoException e){}

        TestUtils.clickComponent(temporario, robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("Tomas Salig", robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("44690191", robot);
        TestUtils.clickComponent(aceptarChofer, robot);
		Assert.assertEquals(Mensajes.CHOFER_YA_REGISTRADO.getValor(), op.getMensaje());
    } 

    @Test
    public void testChoferTempCorrecto(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(temporario, robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("A", robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("1", robot);
        TestUtils.clickComponent(aceptarChofer, robot);

        JList<String> listaChoferes = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_CHOFERES_TOTALES);
        Assert.assertTrue("El listado de chofer deberia agregar al chofer", listaChoferes.getModel().getSize()>0);

        Assert.assertTrue("El dni deberia estar vacio", dni.getText().trim().isEmpty());
        Assert.assertTrue("El nombre deberia estar vacio", nombre.getText().trim().isEmpty());
    } 

    @Test
    public void testChoferPermSoloNombre(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("a", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    } 

    @Test
    public void testChoferPermSoloDNI(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("1", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    }

    @Test
    public void testChoferPermSoloAño(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(año, robot);
        TestUtils.tipeaTexto("2025", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    } 

    @Test
    public void testChoferPermSoloHijos(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(hijos, robot);
        TestUtils.tipeaTexto("0", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    } 

    @Test
    public void testChoferPermSinHijos(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(año, robot);
        TestUtils.tipeaTexto("2025", robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("1", robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("a", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    } 
    
    @Test
    public void testChoferPermSinAño(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(hijos, robot);
        TestUtils.tipeaTexto("0", robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("1", robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("a", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    } 

    @Test
    public void testChoferPermSinDNI(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(hijos, robot);
        TestUtils.tipeaTexto("0", robot);
        TestUtils.clickComponent(año, robot);
        TestUtils.tipeaTexto("2025", robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("a", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    } 

    @Test
    public void testChoferPermSinNombre(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(hijos, robot);
        TestUtils.tipeaTexto("0", robot);
        TestUtils.clickComponent(año, robot);
        TestUtils.tipeaTexto("2025", robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("1", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    } 

    @Test
    public void testChoferPermHijosNeg(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(hijos, robot);
        TestUtils.tipeaTexto("-1", robot);
        TestUtils.clickComponent(año, robot);
        TestUtils.tipeaTexto("2025", robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("1", robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("a", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    } 

    @Test
    public void testChoferPermAñoMax(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(hijos, robot);
        TestUtils.tipeaTexto("0", robot);
        TestUtils.clickComponent(año, robot);
        TestUtils.tipeaTexto("3001", robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("1", robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("a", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    } 

    @Test
    public void testChoferPermAñoMin(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(hijos, robot);
        TestUtils.tipeaTexto("0", robot);
        TestUtils.clickComponent(año, robot);
        TestUtils.tipeaTexto("1899", robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("1", robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("a", robot);
        Assert.assertFalse("El boton de nuevo chofer deberia estar deshabilitado",aceptarChofer.isEnabled());
    } 

    @Test
    public void testChoferPermRepe(){
        robot.delay(TestUtils.getDelay());
        Chofer chofer = new ChoferTemporario("44690191", "Tomas Salig");
        try{Empresa.getInstance().agregarChofer(chofer);} catch (ChoferRepetidoException e){}
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(hijos, robot);
        TestUtils.tipeaTexto("0", robot);
        TestUtils.clickComponent(año, robot);
        TestUtils.tipeaTexto("2025", robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("44690191", robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("a", robot);
        TestUtils.clickComponent(aceptarChofer, robot);
		Assert.assertEquals(Mensajes.CHOFER_YA_REGISTRADO.getValor(), op.getMensaje());
    } 

    @Test
    public void testChoferPermCorrecto(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(permanente, robot);
        TestUtils.clickComponent(hijos, robot);
        TestUtils.tipeaTexto("0", robot);
        TestUtils.clickComponent(año, robot);
        TestUtils.tipeaTexto("2025", robot);
        TestUtils.clickComponent(dni, robot);
        TestUtils.tipeaTexto("44690191", robot);
        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("a", robot);
        TestUtils.clickComponent(aceptarChofer, robot);
        JList<String> listaChoferes = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_CHOFERES_TOTALES);
        Assert.assertTrue("El listado de chofer deberia agregar al chofer", listaChoferes.getModel().getSize()>0);
        Assert.assertTrue("El dni deberia estar vacio", dni.getText().trim().isEmpty());
        Assert.assertTrue("El nombre deberia estar vacio", nombre.getText().trim().isEmpty());
        Assert.assertTrue("El año deberia estar vacio", año.getText().trim().isEmpty());
        Assert.assertTrue("La cantidad de hijos deberia estar vacia", hijos.getText().trim().isEmpty());
    }

    @Test
    public void testMotoSinPatente(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(moto, robot);
        Assert.assertFalse("El boton de nuevo vehiculo deberia estar deshabilitado",aceptarVehiculo.isEnabled());
    } 

    @Test
    public void testMotoConPlazas(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(moto, robot);
        TestUtils.clickComponent(patente, robot);
        TestUtils.tipeaTexto("asd123", robot);
        TestUtils.clickComponent(plazas, robot);
        TestUtils.tipeaTexto("1", robot);
        Assert.assertTrue("El boton de nuevo vehiculo deberia estar habilitado",aceptarVehiculo.isEnabled());
    } 

    @Test
    public void testMotoRepe(){
        robot.delay(TestUtils.getDelay());
        Vehiculo motoaux = new Moto("asd123");
        try{Empresa.getInstance().agregarVehiculo(motoaux);}catch(VehiculoRepetidoException e){}
        this.agregaMoto();
		Assert.assertEquals(Mensajes.VEHICULO_YA_REGISTRADO.getValor(), op.getMensaje());
    } 

    @Test
    public void testMotoCorrecto(){
        robot.delay(TestUtils.getDelay());
        this.agregaMoto();
        JList<String> listaVehiculos = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VEHICULOS_TOTALES);
        Assert.assertTrue("El listado de vehiculos deberia agregar el vehiculo", listaVehiculos.getModel().getSize()>0);
        Assert.assertTrue("La patente deberia estar vacia", patente.getText().trim().isEmpty());
    } 

    @Test
    public void testAutoSinPatente(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(auto, robot);
        Assert.assertFalse("El boton de nuevo vehiculo deberia estar deshabilitado",aceptarVehiculo.isEnabled());
    }
    
    @Test
    public void testAutoPlazaMin(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(auto, robot);
        TestUtils.clickComponent(patente, robot);
        TestUtils.tipeaTexto("asd123", robot);
        TestUtils.clickComponent(plazas, robot);
        TestUtils.tipeaTexto("0", robot);
        Assert.assertFalse("El boton de nuevo vehiculo deberia estar deshabilitado",aceptarVehiculo.isEnabled());
    } 

    @Test
    public void testAutoPlazaMax(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(auto, robot);
        TestUtils.clickComponent(patente, robot);
        TestUtils.tipeaTexto("asd123", robot);
        TestUtils.clickComponent(plazas, robot);
        TestUtils.tipeaTexto("5", robot);
        Assert.assertFalse("El boton de nuevo vehiculo deberia estar deshabilitado",aceptarVehiculo.isEnabled());
    } 

    @Test
    public void testAutoRepe(){
        robot.delay(TestUtils.getDelay());
        Vehiculo autoaux = new Auto("asd123", 4, true);
        try{Empresa.getInstance().agregarVehiculo(autoaux);}catch(VehiculoRepetidoException e){}
        this.agregaAuto();
		Assert.assertEquals(Mensajes.VEHICULO_YA_REGISTRADO.getValor(), op.getMensaje());
    } 

    @Test
    public void testAutoCorrecto(){
        robot.delay(TestUtils.getDelay());
        this.agregaAuto();
        JList<String> listaVehiculos = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VEHICULOS_TOTALES);
        Assert.assertTrue("El listado de vehiculos deberia agregar el vehiculo", listaVehiculos.getModel().getSize()>0);
        Assert.assertTrue("La patente deberia estar vacia", patente.getText().trim().isEmpty());
        Assert.assertTrue("La cantidad de plazas deberia estar vacia", plazas.getText().trim().isEmpty());
    } 

    @Test
    public void testCombiSinPatente(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(combi, robot);
        Assert.assertFalse("El boton de nuevo vehiculo deberia estar deshabilitado",aceptarVehiculo.isEnabled());
    } 

    @Test
    public void testCombiPlazaMin(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(combi, robot);
        TestUtils.clickComponent(patente, robot);
        TestUtils.tipeaTexto("asd123", robot);
        TestUtils.clickComponent(plazas, robot);
        TestUtils.tipeaTexto("0", robot);
        Assert.assertFalse("El boton de nuevo vehiculo deberia estar deshabilitado",aceptarVehiculo.isEnabled());
    } 

    @Test
    public void testCombiPlazaMax(){
        robot.delay(TestUtils.getDelay());
        TestUtils.clickComponent(combi, robot);
        TestUtils.clickComponent(patente, robot);
        TestUtils.tipeaTexto("asd123", robot);
        TestUtils.clickComponent(plazas, robot);
        TestUtils.tipeaTexto("11", robot);
        Assert.assertFalse("El boton de nuevo vehiculo deberia estar deshabilitado",aceptarVehiculo.isEnabled());
    } 

    @Test
    public void testCombiRepe(){
        robot.delay(TestUtils.getDelay());
        Vehiculo combiaux = new Combi("asd123", 5, true);
        try{Empresa.getInstance().agregarVehiculo(combiaux);}catch(VehiculoRepetidoException e){}
        this.agregaCombi();
		Assert.assertEquals(Mensajes.VEHICULO_YA_REGISTRADO.getValor(), op.getMensaje());
    } 

    @Test
    public void testCombiCorrecto(){
        robot.delay(TestUtils.getDelay());
        this.agregaCombi();
        JList<String> listaVehiculos = (JList<String>) TestUtils.getComponentForName((Ventana) controlador.getVista(), Constantes.LISTA_VEHICULOS_TOTALES);
        Assert.assertTrue("El listado de vehiculos deberia agregar el vehiculo", listaVehiculos.getModel().getSize()>0);
        Assert.assertTrue("La patente deberia estar vacia", patente.getText().trim().isEmpty());
        Assert.assertTrue("La cantidad de plazas deberia estar vacia", plazas.getText().trim().isEmpty());
    } 
}