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

public class NuevoViajeTest {
    Empresa empresa;
    Controlador controlador;
    Chofer choferTemp,choferPerm;
    

    @Before
    public void setUp() throws Exception{
        empresa=Empresa.getInstance();
        empresa.setPedidos(new HashMap<Cliente, Pedido>());
        empresa.setClientes(new HashMap<String, Cliente>());
        
    }

    @After
    public void tearDown() throws Exception{

    }

    @Test 
    public void nuevoViajeExitosoTest(){
        try{
            Ventana ventana= mock(Ventana.class);
            Cliente cliente1 = new Cliente("cliente1", "pass", "cliente1");

            Pedido pedido = new Pedido(cliente1, 3, false, false, 10, Constantes.ZONA_STANDARD);
            empresa.agregarPedido(pedido);
            Chofer choferTemp = new ChoferTemporario("123456", "choferTemp");
            Vehiculo auto = new Auto("ABC123", 3, false);
    
            when(ventana.getPedidoSeleccionado()).thenReturn(pedido);
            when(ventana.getChoferDisponibleSeleccionado()).thenReturn(choferTemp);
            when(ventana.getVehiculoDisponibleSeleccionado()).thenReturn(auto);
            this.controlador.setVista(ventana);

            this.controlador.nuevoViaje(); 

            Viaje viajeAgregado = empresa.getViajeDeCliente(cliente1);
            assertNotNull(viajeAgregado);

            assertEquals(ventana.getPedidoSeleccionado(), viajeAgregado.getPedido());
            assertEquals(ventana.getChoferDisponibleSeleccionado(), viajeAgregado.getChofer());
            assertEquals(ventana.getVehiculoDisponibleSeleccionado(), viajeAgregado.getVehiculo());

        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }
    
    @Test
    public void nuevoViajeFallidoTest(){
        try{
            FalsoOptionPane op = new FalsoOptionPane();
            Ventana ventana= mock(Ventana.class);
            Cliente cliente1 = new Cliente("cliente1", "pass", "cliente1");

            Pedido pedido = new Pedido(cliente1, 3, true, false, 10, Constantes.ZONA_STANDARD);
            empresa.agregarPedido(pedido);
            Chofer choferTemp = new ChoferTemporario("123456", "choferTemp");
            Vehiculo auto = new Auto("ABC123", 3, false);
    
            when(ventana.getPedidoSeleccionado()).thenReturn(pedido);
            when(ventana.getChoferDisponibleSeleccionado()).thenReturn(choferTemp);
            when(ventana.getVehiculoDisponibleSeleccionado()).thenReturn(auto);
            this.controlador.setVista(ventana);
            this.controlador.getVista().setOptionPane(op);
            this.controlador.nuevoViaje();
            assertEquals(Mensajes.SIN_VEHICULO_PARA_PEDIDO, op.getMensaje());
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }
}