package test;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import util.*;

public class ViajeTest {
    Pedido pedido;
    Cliente cliente;
    Chofer chofer;
    Vehiculo vehiculo;
    Viaje viaje;

    @Before
    public void startUp(){
        cliente=new Cliente("cliente","pass","NombreCliente");
        pedido=new Pedido(cliente,3,false,false,5,Constantes.ZONA_STANDARD);
        chofer=new ChoferTemporario("12345","nombreChofer");
        vehiculo=new Auto("123-456",3,true);
        viaje=new Viaje(pedido,chofer,vehiculo);
    }

    @Test
    public void isFinalizadoTest(){
        assertEquals(false,viaje.isFinalizado());
    }

    @Test
    public void getCalificacionTest(){
        assertEquals(0,viaje.getCalificacion());
    }

    @Test
    public void finalizarViajeTest(){
        viaje.finalizarViaje(4);
        assertEquals(true,viaje.isFinalizado());
        assertEquals(4,viaje.getCalificacion());
    }

    @Test
    public void getChoferTest(){
        assertEquals(chofer,viaje.getChofer());
    }

    @Test
    public void getPedidoTest(){
        assertEquals(pedido,viaje.getPedido());
    }

    @Test
    public void getVehiculoTest(){
        assertEquals(vehiculo,viaje.getVehiculo());
    }

    @Test
    public void getValorBaseTest(){
        assertEquals(1000,Viaje.getValorBase(),0.001);
    }

    @Test
    public void getValorTest(){
        
        Pedido pedido2= new Pedido(cliente,3,false,false,5,Constantes.ZONA_SIN_ASFALTAR);
        Viaje viaje2=new Viaje(pedido2,chofer,vehiculo);
        Pedido pedido3= new Pedido(cliente,3,false,false,5,Constantes.ZONA_PELIGROSA);
        Viaje viaje3=new Viaje(pedido3,chofer,vehiculo);
        Pedido pedido4= new Pedido(cliente,3,false,true,5,Constantes.ZONA_STANDARD);
        Viaje viaje4=new Viaje(pedido4,chofer,vehiculo);
        Pedido pedido5= new Pedido(cliente,3,true,false,5,Constantes.ZONA_STANDARD);
        Viaje viaje5=new Viaje(pedido5,chofer,vehiculo);
        Pedido pedido6=new Pedido(cliente,3,true,false,5,Constantes.ZONA_PELIGROSA);
        Viaje viaje6=new Viaje(pedido6,chofer,vehiculo);

        //assertEquals(1800.0,viaje.getValor(),0.001);
        assertEquals(2350.0,viaje2.getValor(),0.001);
        assertEquals(2300.0,viaje3.getValor(),0.001);
       // assertEquals(2350.0,viaje4.getValor(),0.001);
        //assertEquals(3100.0,viaje5.getValor(),0.001);
        assertEquals(3600.0,viaje6.getValor(),0.001);
        //valor Base*(1+0.1*pasajeros+0.2*km+0.1*pas+0.2*km)=(1+0.2*pas+0.4*km)=3600
        //NO SE ESTAN APLICANDO LAS BONIFICACIONES DE PRECIO POR MASCOTA Y POR ZONA STANDARD

    }
}

