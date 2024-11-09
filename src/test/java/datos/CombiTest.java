package test.java.datos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;

public class CombiTest {
    Empresa empresa;
    Cliente cliente;
    Pedido pedido, pedido1, pedido2, pedido3; 
    Chofer chofer;
    Vehiculo combi1, combi2;

    @Before
    public void setUp() throws Exception{
        empresa = Empresa.getInstance();
        cliente= new Cliente("user1", "pass1", "cliente1");
        pedido = new Pedido(cliente, 7, true, true, 10, "ZONA_STANDARD");
        pedido1 = new Pedido(cliente, 7, true, false, 10,"ZONA_STANDARD");
        pedido2 = new Pedido(cliente, 10, true, true, 10, "ZONA_STANDARD");
        pedido3 = new Pedido(cliente, 7, true, true, 10, "ZONA_STANDARD");


        chofer = new ChoferPermanente("documento","chofer1",2023,1);
        combi1 = new Combi("GHI789", 7, true);
        combi2 = new Combi("GHI789", 7, false);
    }

    @Test
    public void getPatenteTest(){
        try{
            assertEquals(combi1.getPatente(), "GHI789");
        }
        catch (Exception ex){
            fail("getPatente no funciona correctamente");
        }
    }

    @Test
    public void getCantidadPlazasTest(){
        try{
            assertEquals(combi1.getCantidadPlazas(), 7);
        }
        catch (Exception ex){
            fail("getCantidadPlazas no funciona correctamente");
        }
    }

    @Test
    public void isMascota1Test(){
        try{
            assertTrue(combi1.isMascota());
        }
        catch (Exception ex){
            fail("isMascota no funciona correctamente");
        }
    }

    @Test
    public void isMascota2Test(){
        try{
            assertFalse(combi2.isMascota());
        }
        catch (Exception ex){
            fail("isMascota no funciona correctamente");
        }
    }

    @Test
    public void getPuntajePedidoTest(){
        try{
            assertEquals(Integer.valueOf(170),combi1.getPuntajePedido(pedido));
            // 1 pasajero, sin baul y sin mascota == 1000
        }
        catch (Exception ex){
            fail("getPuntajePedido no funciona correctamente en Combi");
            
        }
    }

    @Test
    public void getPuntajePedidosinBaulTest(){
        try{
            assertEquals(combi1.getPuntajePedido(pedido1), Integer.valueOf(70));
        }
        catch (Exception ex){
            fail("getPuntajePedido no funciona correctamente en Combi");
        }
    }

    /*(@Test
    public void getPuntajePedido10pasajerosTest(){
        try{
            assertEquals(null,combi1.getPuntajePedido(pedido2));
            // 11 pasajeros, devuelve null
        }
        catch (Exception ex){
            fail("getPuntajePedido no funciona correctamente en Combi");
        }
    }*/
    
    @Test
    public void getPuntajePedidoconMascotaTest(){ // con mascota en combi que no admite mascota
        try{
            assertEquals(combi2.getPuntajePedido(pedido3), null);
            // 1 pasajero, sin baul y con mascota == null
        }
        catch (Exception ex){
            fail("getPuntajePedido no funciona correctamente en Combi");
        }
    }
}
