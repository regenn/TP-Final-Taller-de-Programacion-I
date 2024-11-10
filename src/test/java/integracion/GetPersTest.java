package integracion;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
//import Utils.MockUtils;
import excepciones.*;
import modeloNegocio.*;
import persistencia.PersistenciaBIN;
import GUI.FalsoOptionPane;
import util.Constantes;

import java.util.HashMap;

import javax.swing.*;
import util.*;

import java.util.ArrayList;
import controlador.*;
import java.awt.event.*;

import vista.Ventana;

//CORREGIDO
public class GetPersTest {
    Empresa empresa;
    Controlador controlador;

    @Before
    public void setUp(){
        empresa = Empresa.getInstance();
        controlador = new Controlador();

    }
    @Test
    public void getPersistenciaTest(){
        assertEquals(PersistenciaBIN.class,controlador.getPersistencia().getClass());
    }
}
