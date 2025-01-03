package persistencia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.*;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import util.*;
import persistencia.*;

public class EmpresaDTOTest {
    
    @Test
    public void testEscenarioNoVacio(){
        try{
            EmpresaDTO empresaDTO = new EmpresaDTO();

            HashMap<String,Cliente> clientesTest= new HashMap<String,Cliente>();
            HashMap<Cliente,Pedido> pedidosTest= new HashMap<Cliente,Pedido>();
            HashMap<String,Chofer> choferesTest = new HashMap<String,Chofer>();
            HashMap<String,Vehiculo> vehiculosTest = new HashMap<String, Vehiculo>();
            HashMap<Cliente,Viaje> viajesIniciadosTest = new HashMap<Cliente,Viaje>();

            ArrayList<Chofer> choferesDesocupadosTest = new ArrayList<Chofer>();
            ArrayList<Viaje> viajesTerminadosTest = new ArrayList<Viaje>();
            ArrayList<Vehiculo>vehiculosDesocupadosTest = new ArrayList<Vehiculo>();
            
            Cliente cliente= new Cliente("usuarioTest","passTest","nombreTest");
            clientesTest.put("usuarioTest",cliente);

            Chofer chofer= new ChoferTemporario("12345","nombreChoferTest");
            choferesTest.put("12345",chofer);
            choferesDesocupadosTest.add(chofer);

            Usuario admin= Administrador.getInstance();

            empresaDTO.setChoferes(choferesTest);
            empresaDTO.setChoferesDesocupados(choferesDesocupadosTest);
            empresaDTO.setClientes(clientesTest);
            empresaDTO.setPedidos(pedidosTest);
            empresaDTO.setUsuarioLogeado(admin);
            empresaDTO.setVehiculos(vehiculosTest);
            empresaDTO.setVehiculosDesocupados(vehiculosDesocupadosTest);
            empresaDTO.setViajesIniciados(viajesIniciadosTest);
            empresaDTO.setViajesTerminados(viajesTerminadosTest);
        }
        catch(Exception ex){
            fail("Se lanzo una excepcion no esperada");
        }
    }
}
