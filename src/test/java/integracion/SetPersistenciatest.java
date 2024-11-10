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

//bien
public class SetPersistenciatest {
    Empresa empresa;
    Controlador controlador;
    PersistenciaBIN persistencia;

    @Before
    public void setUp(){
        empresa= Empresa.getInstance();
        controlador= new Controlador();
        persistencia= new PersistenciaBIN();
    }

    @After
    public void tearDown(){

    }

    @Test 
    public void setPersistenciaTest(){
    this.controlador.setPersistencia(persistencia);
    assertEquals(persistencia,this.controlador.getPersistencia());
    }
}
