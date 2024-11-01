package test;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import util.*;

public class AdministradorTest {
    Usuario administrador;

    @Before
    public void startUp(){
        administrador=Administrador.getInstance();
    }

    @Test
    public void getInstanceTest(){
        Administrador auxiliar=Administrador.getInstance();
        assertEquals(administrador,auxiliar);
    }

    @Test
    public void getNombreUsuarioTest(){
        assertEquals("admin",administrador.getNombreUsuario());
    }

    @Test
    public void getPassTest(){
        assertEquals("admin",administrador.getPass());
    }

    @After
    public void tearDown(){
        administrador=null;
        assertNull(administrador);
    }
}
