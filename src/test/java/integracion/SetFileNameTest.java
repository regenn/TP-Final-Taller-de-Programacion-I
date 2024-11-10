package integracion;

import static org.junit.Assert.*;
import org.junit.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;

import controlador.*;
import util.Mensajes;
import GUI.FalsoOptionPane;
import vista.Ventana;

//bien
public class SetFileNameTest {
    Empresa empresa;
    Controlador controlador;
    
    @Before
    public void setUp(){
        empresa= Empresa.getInstance();
        controlador= new Controlador();
    }

    @After
    public void tearDown(){

    }
    
    @Test
    public void setFileNameTest(){
        this.controlador.setFileName("archivoprueba.bin");
        assertEquals("archivoprueba.bin",this.controlador.getFileName());
    }
}
