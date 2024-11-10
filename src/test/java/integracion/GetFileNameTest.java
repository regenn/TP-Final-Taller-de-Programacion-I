package integracion;

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
import GUI.FalsoOptionPane;
import GUI.TestUtils;
import vista.Ventana;//??
import util.Constantes;

//CORREGIDO
public class GetFileNameTest {
    Empresa empresa;
    Controlador controlador;
    String fileName;

    @Before
    public void setUp(){
        empresa= Empresa.getInstance();
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
