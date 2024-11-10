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

public class NuevoViajeTest {
    Empresa empresa;
    Controlador controlador;
    Chofer choferTemp,choferPerm;
    Usuario usuariologeado;
    FalsoOptionPane op;
    Ventana ventana;

    @Before
    public void setUp() throws Exception{
        empresa=Empresa.getInstance();
        controlador= new Controlador();
        op = new FalsoOptionPane();
        ventana = mock(Ventana.class);
        controlador.setVista(ventana);
		controlador.getVista().setOptionPane(op);
        when(ventana.getOptionPane()).thenReturn(op);
        
    }

    @After
    public void tearDown() throws Exception{
        empresa.getChoferes().clear();
        empresa.getClientes().clear();
        empresa.getPedidos().clear();
        empresa.getVehiculos().clear();
        
    }

    @Test 
    public void nuevoViajeExitosoTest(){
        try{
            empresa.agregarCliente("cliente1", "pass", "cliente1");
            Cliente cliente1 = empresa.getClientes().get("cliente1");
            Chofer choferTemp = new ChoferTemporario("123456", "choferTemp");
            empresa.agregarChofer(choferTemp);
            Vehiculo auto = new Auto("ABC123", 3, false);
            empresa.agregarVehiculo(auto);
            Pedido pedido = new Pedido(cliente1, 3, false, false, 10, Constantes.ZONA_STANDARD);
            empresa.agregarPedido(pedido);
    
            when(ventana.getPedidoSeleccionado()).thenReturn(pedido);
            when(ventana.getChoferDisponibleSeleccionado()).thenReturn(choferTemp);
            when(ventana.getVehiculoDisponibleSeleccionado()).thenReturn(auto);

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
            //Cliente cliente1 = new Cliente("cliente1", "pass", "cliente1");
            empresa.agregarCliente("cliente1", "pass", "cliente1");
            Cliente cliente1 = empresa.getClientes().get("cliente1");
            Chofer choferTemp = new ChoferTemporario("123456", "choferTemp");
            empresa.agregarChofer(choferTemp);
            Vehiculo auto = new Auto("ABC123", 3, false);
            empresa.agregarVehiculo(auto);
            Pedido pedido = new Pedido(cliente1, 3, true, false, 10, Constantes.ZONA_STANDARD);
            empresa.agregarPedido(pedido);
    
            when(ventana.getPedidoSeleccionado()).thenReturn(pedido);
            when(ventana.getChoferDisponibleSeleccionado()).thenReturn(choferTemp);
            when(ventana.getVehiculoDisponibleSeleccionado()).thenReturn(auto);
            this.controlador.nuevoViaje();

            fail("Tendria que haber lanzado una excepcion");
        } catch (Exception e) {
            assertEquals(Mensajes.SIN_VEHICULO_PARA_PEDIDO.getValor(), e.getMessage());
        }

    }
}