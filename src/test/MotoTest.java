package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;

public class MotoTest {
    Empresa empresa;
    Cliente cliente;
    Pedido pedido, pedido1, pedido2, pedido3; 
    Chofer chofer;
    Vehiculo moto;

    @Before
    public void setUp() throws Exception{
        empresa = Empresa.getInstance();
        cliente= new Cliente("user1", "pass1", "cliente1");
        pedido = new Pedido(cliente,1,false,false,3,"ZONA_STANDARD");
        pedido1 = new Pedido(cliente,2,false,false,3,"ZONA_STANDARD");
        pedido2 = new Pedido(cliente,1,true,false,3,"ZONA_STANDARD");
        pedido3 = new Pedido(cliente,1,false,true,3,"ZONA_STANDARD");


        chofer = new ChoferPermanente("documento","chofer1",2023,1);
        moto = new Moto("DEF456");
    }

    @Test
    public void getPatenteTest(){
        try{
            assertEquals(moto.getPatente(), "DEF456");
        }
        catch (Exception ex){
            fail("getPatente no funciona correctamente");
        }
    }

    @Test
    public void getCantidadPlazasTest(){
        try{
            assertEquals(1,moto.getCantidadPlazas());
        }
        catch (Exception ex){
            fail("getCantidadPlazas no funciona correctamente");
        }
    }

    @Test
    public void isMascotaTest(){
        try{
            assertFalse(moto.isMascota());
        }
        catch (Exception ex){
            fail("isMascota no funciona correctamente");
        }
    }

    @Test
    public void getPuntajePedidoTest(){
        try{
            assertEquals(moto.getPuntajePedido(pedido), Integer.valueOf(1000));
            // 1 pasajero, sin baul y sin mascota == 1000
        }
        catch (Exception ex){
            fail("getPuntajePedido no funciona correctamente en Moto");
        }
    }

    @Test
    public void getPuntajePedido2pasajerosTest(){
        try{
            assertEquals(moto.getPuntajePedido(pedido1), null);
            // 2 pasajeros = null
        }
        catch (Exception ex){
            fail("getPuntajePedido no funciona correctamente en Moto");
        }
    }

    @Test
    public void getPuntajePedidoconMascotaTest(){
        try{
            assertEquals(moto.getPuntajePedido(pedido2), null);
            // 1 pasajero, sin baul y con mascota == null
        }
        catch (Exception ex){
            fail("getPuntajePedido no funciona correctamente en Moto");
        }
    }
    
    @Test
    public void getPuntajePedidoconBaulTest(){
        try{
            assertEquals(moto.getPuntajePedido(pedido3), null);
            // 1 pasajero, sin baul y con mascota == null
        }
        catch (Exception ex){
            fail("getPuntajePedido no funciona correctamente en Moto");
        }
    }
    
}
