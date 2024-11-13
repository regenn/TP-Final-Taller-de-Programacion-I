package datos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import modeloDatos.Cliente;

public class ClienteTest {

    Cliente cliente;

    @Before
    public void setUp() throws Exception{
        cliente = new Cliente("ABC123", "030303", "Tomas Salig");
    }

    @Test
    public void getNombreRealTest(){
        try{
            assertEquals(cliente.getNombreReal(), "Tomas Salig");
        }
        catch (Exception ex){
            fail("getNombre real no funciona correctamente");
        }
    }
}