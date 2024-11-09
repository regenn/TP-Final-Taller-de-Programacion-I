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
import util.Constantes;
import util.Mensajes;
import test.GUI.FalsoOptionPane;
import vista.Ventana;//??

/*

Se invoca al metodo agregarChofer(Chofer chofer) de la clase Empresa con los parametros obtenidos del atributo vista:
String tipo = this.vista.getTipoChofer()
String nombre = this.vista.getNombreChofer()
String dni = this.vista.getDNIChofer();
Si tipo== Constantes.TEMPORARIO se agrega un chofer temporario con los parametros "dni" y "nombre"
Si tipo== Constantes.PERMANENTE
int anio = this.vista.getAnioChofer()
int hijos = this.vista.getHijosChofer()
se agrega un chofer permanente con los parametros "dni", "nombre", "anio" e "hijos"
Luego se actualiza la vista
Si la accion no se puede realizar porque el dni esta repetido entonces se delega en el atributo vista mostrar el mensaje correspondiente a la excepcion ChoferRepetidoException lanzada

 */

public class NuevoPedidoTest {
    Empresa empresa;
    Controlador controlador;
    Chofer choferTemp,choferPerm;
    

    @Before
    public void setUp() throws Exception{
        empresa=Empresa.getInstance();
        empresa.setClientes(new HashMap<String,Cliente>());
        Vehiculo auto = new Auto("ABC123", 4, false);
        empresa.agregarVehiculo(auto);
    }

    @After
    public void tearDown() throws Exception{

    }

    @Test 
    public void nuevoPedidoExitosoTest(){
        try{
            Ventana ventana= mock(Ventana.class);
            Cliente cliente = (Cliente) empresa.getUsuarioLogeado();
            when(ventana.getCantidadPax()).thenReturn(3);
            when(ventana.isPedidoConMascota()).thenReturn(false);
            when(ventana.isPedidoConBaul()).thenReturn(false);
            when(ventana.getCantKm()).thenReturn(10);
            this.controlador.setVista(ventana);

            this.controlador.nuevoPedido(); // tendria que haber llammado a empresa.agregarChofer();
            // comparar el dni agregado con el dni del chofer cuto nombre es choferTemp

            Pedido pedidoAgregado = empresa.getPedidoDeCliente(cliente);
            assertNotNull(pedidoAgregado);

            assertEquals(ventana.getCantidadPax(), pedidoAgregado.getCantidadPasajeros());
            assertEquals(ventana.isPedidoConMascota(), pedidoAgregado.isMascota());
            assertEquals(ventana.isPedidoConBaul(), pedidoAgregado.isBaul());
            assertEquals(ventana.getCantKm(), pedidoAgregado.getKm());

        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }
    
    @Test
    public void nuevoPedidoFallidoTest(){ // falla porque ningun vehiculo puede tomarlo
        try{
            FalsoOptionPane op = new FalsoOptionPane();
            Ventana ventana= mock(Ventana.class);
            Cliente cliente = (Cliente) empresa.getUsuarioLogeado();
            when(ventana.getCantidadPax()).thenReturn(3);
            when(ventana.isPedidoConMascota()).thenReturn(true);
            when(ventana.isPedidoConBaul()).thenReturn(false);
            when(ventana.getCantKm()).thenReturn(10);
            this.controlador.setVista(ventana);
            this.controlador.getVista().setOptionPane(op);
            this.controlador.nuevoPedido();
            assertEquals(Mensajes.SIN_VEHICULO_PARA_PEDIDO, op.getMensaje());
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }       
    }
}