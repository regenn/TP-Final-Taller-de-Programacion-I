package test.java.integracion;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
//import Utils.MockUtils;
import excepciones.*;
import modeloNegocio.*;
import test.GUI.FalsoOptionPane;
import util.Constantes;

import java.util.HashMap;

import javax.swing.*;
import util.*;

import java.util.ArrayList;
import controlador.*;
import java.awt.event.*;

import vista.Ventana;

public class GetVistaTest{
    Empresa empresa;
    Controlador controlador;

    @Before
    public void setUp(){
        empresa = Empresa.getInstance();
        controlador = new Controlador();
    }

    @Test
    public void getVistaTest(){
        //la clase controlador inicializa una vista de tipo Ventana y la hace visible.
        Ventana ventana = (Ventana) controlador.getVista();
        assertTrue(ventana.isEnabled());
    }
    
}
