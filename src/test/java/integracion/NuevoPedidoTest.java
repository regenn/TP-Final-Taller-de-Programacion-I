package integracion;

import static org.junit.Assert.*;
import org.junit.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;
import java.util.ArrayList;
import controlador.*;
import util.Constantes;
import util.Mensajes;
import GUI.FalsoOptionPane;
import vista.Ventana;

public class NuevoPedidoTest {
    Empresa empresa;
    Controlador controlador;
    Chofer choferTemp,choferPerm;
    Ventana ventana;
    FalsoOptionPane op;
    Cliente cliente;
    
    @Before
    public void setUp() throws Exception{
        
        empresa=Empresa.getInstance();
        controlador= new Controlador();
        op = new FalsoOptionPane();
        ventana = mock(Ventana.class);
        controlador.setVista(ventana);
		controlador.getVista().setOptionPane(op);
        when(ventana.getOptionPane()).thenReturn(op);

        empresa.agregarCliente("user","pass","nombre");
       
        when(ventana.getUsserName()).thenReturn("user");
        when(ventana.getPassword()).thenReturn("pass");
        this.controlador.login();
    }

    @After
    public void tearDown() throws Exception{
        empresa.getChoferes().clear();
        empresa.getClientes().clear();
        empresa.getPedidos().clear();
        empresa.getVehiculos().clear();
        
    }

    @Test 
    public void nuevoPedidoExitosoTest(){
        try{
            cliente = (Cliente) empresa.getUsuarioLogeado();
            assertNotNull(cliente);

            when(ventana.getCantidadPax()).thenReturn(3);
            when(ventana.isPedidoConMascota()).thenReturn(false);
            when(ventana.isPedidoConBaul()).thenReturn(false);
            when(ventana.getCantKm()).thenReturn(10);
            when(ventana.getTipoZona()).thenReturn(Constantes.ZONA_STANDARD);
            controlador.nuevoPedido();

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
    public void nuevoPedidoFallidoTest(){
        try{
            cliente = (Cliente) empresa.getUsuarioLogeado();
            assertNotNull(cliente);

            when(ventana.getCantidadPax()).thenReturn(3);
            when(ventana.isPedidoConMascota()).thenReturn(true);
            when(ventana.isPedidoConBaul()).thenReturn(false);
            when(ventana.getCantKm()).thenReturn(10);
            when(ventana.getTipoZona()).thenReturn(Constantes.ZONA_STANDARD);

            this.controlador.nuevoPedido();

            assertEquals(Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), op.getMensaje());
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }       
    }
}