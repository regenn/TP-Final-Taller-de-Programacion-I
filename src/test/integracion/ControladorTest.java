/*
 metodos de controlador
 actionPerformed
 
Metodo de la interfaz ActionListener. dependiendo el valor del actionCommand del parametro de tipo ActionEvent se invoca a los siguientes metodos:
Constantes.CERRAR_SESION_CLIENTE or Constantes.CERRAR_SESION_ADMIN se invoca a logout()
Constantes.LOGIN) se invoca a login()
Constantes.REG_BUTTON_REGISTRAR se invoca a registrar()
Constantes.NUEVO_PEDIDO se invoca a nuevoPedido()
Constantes.CALIFICAR_PAGAR se invoca a calificarPagar()
Constantes.NUEVO_CHOFER se invoca a nuevoChofer()
Constantes.NUEVO_VEHICULO se invoca a nuevoVehiculo()
Constantes.NUEVO_VIAJE se invoca a nuevoViaje()
Cualquier otro valor es ignorado.


package test.integracion;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
//import Utils.MockUtils;
import excepciones.*;
import modeloNegocio.*;
import util.Constantes;

import java.util.HashMap;

import javax.swing.*;
import util.*;

import java.util.ArrayList;
import controlador.*;
import java.awt.event.*;
//import java.awt.event.ActionEvent;
 
 public class ControladorTest {
    Controlador controlador;
    //FalsoOptionPane op;
    //Ventana ventana;
    Empresa empresa;

    @Before
    public void setUp(){
        empresa = Empresa.getInstance();       
        controlador = new Controlador();
    }

    @After
    public void tearDown(){

    }

    @Test
    public void actionPerformedTest_login(){
        ActionEvent eventLogin = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED,Constantes.LOGIN);
        controlador.actionPerformed(eventLogin);
        Usuario clienteLogueado = empresa.getUsuarioLogeado();
        assertEquals(clienteLogueado.getPass(),controlador.getVista().getPassword());
   
    }

    @Test
    public void actionPerformedTest_logoutCliente(){
        ActionEvent eventLogout = new ActionEvent(new JButton(),ActionEvent.ACTION_PERFORMED,Constantes.CERRAR_SESION_CLIENTE);
        controlador.actionPerformed(eventLogout);
        controlador.getVista().get
        Cliente cliente = new Cliente("Cliente1","pass","Cliente1");
        
    }

    @Test
    public void actionPerformedTest_logoutAdmin(){
        
        ActionEvent eventLogout = new ActionEvent(new JButton(),ActionEvent.ACTION_PERFORMED,Constantes.CERRAR_SESION_ADMIN);
        controlador.actionPerformed(eventLogout);
        Administrador admin = Administrador.getInstance();
    }
 }
     */