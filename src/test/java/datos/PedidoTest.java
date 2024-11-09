package test.java.datos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;
import modeloDatos.Pedido;

public class PedidoTest {
    
    Cliente cliente = new Cliente("ABC123", "030303", "Tomas Salig");
    Pedido pedido, pedido2;

    @Before
    public void setUp() throws Exception{
        pedido = new Pedido(cliente, 2, true, true, 0, "ZONA_PELIGROSA");
        pedido2 = new Pedido(cliente, 2, false, false, 0, "ZONA_PELIGROSA");
    }

    @Test
    public void getCantidadPasajerosTest(){
        try{
            assertEquals(pedido.getCantidadPasajeros(), 2);
        }
        catch (Exception ex){
            fail("getCantidadPasajeros no funciona correctamente");
        }
    }

    @Test
    public void getClienteTest(){
        try{
            assertEquals(pedido.getCliente(), cliente);
        }
        catch (Exception ex){
            fail("getCliente no funciona correctamente");
        }
    }

    @Test
    public void getKmTest(){
        try{
            assertEquals(pedido.getKm(), 0);
        }
        catch (Exception ex){
            fail("getKm no funciona correctamente");
        }
    }

    @Test
    public void getZonaTest(){
        try{
            assertEquals(pedido.getZona(), "ZONA_PELIGROSA");
        }
        catch (Exception ex){
            fail("getZona no funciona correctamente");
        }
    }

    @Test
    public void isBaulTest(){
        try{
            assertEquals(pedido.isBaul(), true);
            assertEquals(pedido2.isBaul(), false);
        }
        catch (Exception ex){
            fail("isBaul no funciona correctamente");
        }
    }

    @Test
    public void isMascotaTest(){
        try{
            assertEquals(pedido.isMascota(), true);
            assertEquals(pedido2.isMascota(), false);
        }
        catch (Exception ex){
            fail("isMascota no funciona correctamente");
        }
    }
}