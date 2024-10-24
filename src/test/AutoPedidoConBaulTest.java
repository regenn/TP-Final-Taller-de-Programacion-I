package test;

import static org.junit.Assert.*;
import org.junit.*;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Auto;
import modeloDatos.Chofer;
import modeloDatos.ChoferPermanente;
import modeloDatos.Cliente;
import modeloDatos.Combi;
import modeloDatos.Moto;
import modeloDatos.Pedido;
import modeloDatos.Vehiculo;
import modeloNegocio.Empresa;

//getPatente
    //getCantidadPlazas
    //isMascota
    //getPuntajePedido

public class AutoPedidoSinBaulTest {
    Empresa empresa;
    Cliente cliente;
    Pedido pedido; 
    Chofer chofer;
    Vehiculo auto;

    @Before
    public void setUp() throws Exception{
        empresa = Empresa.getInstance();
        cliente= new Cliente("user1", "pass1", "cliente1");
        pedido = new Pedido(cliente,3,true,false,3,"ZONA_STANDARD");
        chofer = new ChoferPermanente("documento","chofer1",2023,1);
        auto = new Auto("ABC123",3,true);
    }

    @Test
    public void getPatenteTest(){
        try{
            assertEquals(auto.getPatente(), "ABC123");
        }
        catch (Exception ex){
            fail("getPatente no funciona correctamente");
        }
    }

    @Test
    public void getCantidadPlazasTest(){
        try{
            assertEquals(auto.getCantidadPlazas(), 3);
        }
        catch (Exception ex){
            fail("getCantidadPlazas no funciona correctamente");
        }
    }

    @Test
    public void isMascotaTest(){
        try{
            assertEquals(auto.isMascota(), true);
        }
        catch (Exception ex){
            fail("isMascota no funciona correctamente");
        }
    }

    @Test
    public void getPuntajePedidoTest(){
        try{
            assertEquals(auto.getPuntajePedido(pedido), 30 * 3);
            //si solicita uso de baul, valor = 40 * cantPasajeros

        }
    }











    
}
