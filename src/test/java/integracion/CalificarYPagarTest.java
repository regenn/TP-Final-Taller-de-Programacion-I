package integracion;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
//import Utils.MockUtils;
import excepciones.*;
import modeloNegocio.*;
import GUI.FalsoOptionPane;

import java.util.HashMap;

import javax.swing.*;
import util.*;

import java.util.ArrayList;
import controlador.*;
import java.awt.event.*;

import vista.Ventana;

public class CalificarYPagarTest {
    Empresa empresa;
    Controlador controlador;
    Cliente usuariologeado;
    FalsoOptionPane op;
    Ventana ventana;

    @Before
    public void setUp(){

        empresa=Empresa.getInstance();
        controlador= new Controlador();
        op = new FalsoOptionPane();
        ventana = mock(Ventana.class);
        controlador.setVista(ventana);
		controlador.getVista().setOptionPane(op);
        when(ventana.getOptionPane()).thenReturn(op);

        try{
            empresa.agregarCliente("cliente1","pass1","nombre1");
             usuariologeado = (Cliente) this.empresa.login("cliente1","pass1");
        } catch(Exception e){}
        

    }

    @After
    public void tearDown(){
        empresa.getChoferes().clear();
        empresa.getClientes().clear();
        empresa.getPedidos().clear();
    }
    
    @Test
    public void CalificarExitosoTest(){
        try{
            empresa.agregarVehiculo(new Auto("123-456",3,false));
            empresa.agregarChofer(new ChoferTemporario("234455","nombreChofer"));
            empresa.agregarPedido(new Pedido(usuariologeado,2,false,false,5,Constantes.ZONA_STANDARD));
            empresa.crearViaje(empresa.getPedidos().get(usuariologeado),empresa.getChoferes().get("234455"),empresa.getVehiculos().get("123-456"));

            when(ventana.getCalificacion()).thenReturn(5);
            this.controlador.calificarPagar();

        } catch (Exception e){
            fail("No deberia lanzarse la excepcion: "+ e.getMessage());
        }
    }

    @Test
    public void CalificarErroneoTest(){
        try{
            when(ventana.getCalificacion()).thenReturn(5);
            this.controlador.calificarPagar();
            
        }catch(Exception e){
             assertEquals(Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE.getValor(), op.getMensaje());
        }
    }
}