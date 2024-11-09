package test.java.integracion;

import static org.junit.Assert.*;
import org.junit.*;

import static org.mockito.Mockito.mock;//hay que instalar algo?
import static org.mockito.Mockito.when;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;
import controlador.*;
import util.Mensajes;
import test.GUI.FalsoOptionPane;
import test.GUI.TestUtils;
import vista.Ventana;//??
import util.Constantes;

public class GetFileNameTest {
    Empresa empresa;
    Controlador controlador;
    String fileName;

    @Before
    public void setUp(){
        empresa= new getInstance();
        controlador= new Controlador();
    }

    @After
    public void tearDown(){

    }

    @Test
    public void getFileNameTest(){
        assertEquals("empresa.bin",this.controlador.getFileName());
    }
    
}
