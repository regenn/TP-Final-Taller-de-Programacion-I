package persistencia;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.*;
import static org.junit.Assert.*;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import util.*;
import persistencia.*;


public class UtilPersistenciaTest {
    UtilPersistencia testDePersistencia;
    @Before
    public void setUp() throws Exception{

    }
    @After
    public void tearDown() throws Exception{
        
    }
    
    @Test
    public void EmpresaDTOfromEmpresaTest(){
    try{
        EmpresaDTO empresaDTO;
        Empresa empresa = Empresa.getInstance();

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

            empresa.setChoferes(choferesTest);
            empresa.setChoferesDesocupados(choferesDesocupadosTest);
            empresa.setClientes(clientesTest);
            empresa.setPedidos(pedidosTest);
            empresa.setUsuarioLogeado(admin);
            empresa.setVehiculos(vehiculosTest);
            empresa.setVehiculosDesocupados(vehiculosDesocupadosTest);
            empresa.setViajesIniciados(viajesIniciadosTest);
            empresa.setViajesTerminados(viajesTerminadosTest);

            empresaDTO= UtilPersistencia.EmpresaDtoFromEmpresa();

            assertEquals(empresaDTO.getChoferes(),empresa.getChoferes());
            assertEquals(empresaDTO.getChoferesDesocupados(),empresa.getChoferesDesocupados());
            assertEquals(empresaDTO.getClientes(),empresa.getClientes());
            assertEquals(empresaDTO.getPedidos(),empresa.getPedidos());
            assertEquals(empresaDTO.getUsuarioLogeado(),empresa.getUsuarioLogeado());
            assertEquals(empresaDTO.getVehiculos(),empresa.getVehiculos());
            assertEquals(empresaDTO.getVehiculosDesocupados(),empresa.getVehiculosDesocupados());
            assertEquals(empresaDTO.getViajesIniciados(),empresa.getViajesIniciados());
            assertEquals(empresaDTO.getViajesTerminados(),empresa.getViajesTerminados());

    } 
    catch (Exception e){
        fail("No tendria que lanzar una excepcion en este caso");
    }  
    }
    
    @Test
    public void EmpresaFromEmpresaDTOTest(){
        try{
            
             EmpresaDTO empresaDTO = new EmpresaDTO();
             Empresa empresa = Empresa.getInstance();

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

            UtilPersistencia.empresaFromEmpresaDTO(empresaDTO);

            assertEquals(empresa.getChoferes(),empresaDTO.getChoferes());
            assertEquals(empresa.getChoferesDesocupados(),empresaDTO.getChoferesDesocupados());
            assertEquals(empresa.getClientes(),empresa.getClientes());
            assertEquals(empresa.getPedidos(),empresaDTO.getPedidos());
            assertEquals(empresa.getUsuarioLogeado(),empresaDTO.getUsuarioLogeado());
            assertEquals(empresa.getVehiculos(),empresaDTO.getVehiculos());
            assertEquals(empresa.getVehiculosDesocupados(),empresaDTO.getVehiculosDesocupados());
            assertEquals(empresa.getViajesIniciados(),empresaDTO.getViajesIniciados());
            assertEquals(empresa.getViajesTerminados(),empresaDTO.getViajesTerminados());
            
        }
        catch(Exception e){
            fail("No tendria que lanzar una excepcion");
        }
    
    }        
}

