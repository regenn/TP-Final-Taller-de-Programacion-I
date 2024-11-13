package modelo;


import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import util.*;
import java.util.ArrayList;
import java.util.HashMap;

//Testeo para el caso de empresa vacia.
public class EmpresaVaciaTest {
    Empresa empresa;
    Cliente cliente;
    Pedido pedido;
    Chofer chofer;
    Vehiculo auto;
    // son instanciados pero no agregados a la empresa.

    @Before
    public void startUp(){
        empresa=Empresa.getInstance();
        cliente= new Cliente("user1", "pass1", "cliente1");
        pedido=new Pedido(cliente,3,true,true,3,"ZONA_STANDARD");
        chofer = new ChoferPermanente("documento","chofer1",2023,1);
        auto = new Auto("ABC123",3,true);

        empresa.setChoferes(new HashMap<String,Chofer>());
        empresa.setChoferesDesocupados(new ArrayList<Chofer>());
        empresa.setClientes(new HashMap<String,Cliente>());
        empresa.setPedidos(new HashMap<Cliente,Pedido>());
        empresa.setVehiculos(new HashMap<String,Vehiculo>());
        empresa.setVehiculosDesocupados(new ArrayList<Vehiculo>());
        empresa.setViajesIniciados(new HashMap<Cliente,Viaje>());
        empresa.setViajesTerminados(new ArrayList<Viaje>());
    }

    @Test
    public void calificacionDeChoferTest(){
        try{
            empresa.calificacionDeChofer(chofer);
        }
        catch(SinViajesException ex){
            assertEquals(Mensajes.CHOFER_SIN_VIAJES,ex.getMessage());
        }
        catch (Exception ex){
            fail("Excepcion no esperada");
        }
    }

    @Test
    public void loginTest(){
        try{
            empresa.login(this.cliente.getNombreUsuario(),this.cliente.getPass());
        }
        catch(UsuarioNoExisteException ex){
            assertEquals(cliente.getNombreUsuario(),ex.getUsuarioPretendido());
        }
        catch(PasswordErroneaException ex){
            fail("PasswordErroneaException lanzada");
        }
        catch (Exception ex){
            fail("Excepcion no esperada");
        }
    }

    @Test
    public void loginAdminTest(){
        try{
            empresa.login("admin","admin");
        }
        catch(Exception e){
             fail("Excepcion no esperada");
        }
    }

    @Test
    public void logoutTest(){
        try{
            empresa.logout();
        }
        catch (Exception ex){
            fail("Excepcion lanzada");
        }
    }
    
    @Test 
    public void pagarYFinalizarViajeTest(){
        int calificacion=3;
        try{
            empresa.pagarYFinalizarViaje(calificacion);
        }
        catch(ClienteSinViajePendienteException ex){
            assertEquals(Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE.getValor(),ex.getMessage());
        }
    }
    @Test
    public void crearViajeTest(){

        try{
            empresa.crearViaje(pedido,chofer,auto);
        }
        catch (PedidoInexistenteException ex) {
            assertEquals(pedido,ex.getPedido());
        } 
        catch (ClienteConViajePendienteException ex){
            fail("Excepcion de cliente con viaje pendiente lanzada");
        }        
        catch (ChoferNoDisponibleException ex){
            assertEquals(chofer,ex.getChofer());    
        }
        catch(VehiculoNoDisponibleException ex){
            assertEquals(auto,ex.getVehiculo());
        }
        catch (VehiculoNoValidoException ex){
            fail ("El vehiculo no es valido para este pedido");
        } 

        catch (Exception ex){
            fail("Excepcion no esperada");
        }
    }

    public void validarPedidoTest(){
        try{
            empresa.validarPedido(pedido);
        }
        catch (Exception ex){
            fail("No se pudo validar el pedido");
        }
    }
    
    public void vehiculosOrdenadosPorPedidoTest(){
        ArrayList <Vehiculo> listaVehiculos=empresa.vehiculosOrdenadosPorPedido(pedido);
        assertTrue(listaVehiculos.isEmpty()); 
    }

    @After
    public void tearDown(){
        System.out.println("Runneando: tear down");
        empresa = Empresa.getInstance();
        chofer = null;
        assertNull(chofer);
        auto=null;
        empresa.setChoferes(null);
        empresa.setChoferesDesocupados(null);
        empresa.setClientes(null);
        empresa.setPedidos(null);
        empresa.setVehiculos(null);
        empresa.setVehiculosDesocupados(null);
        empresa.setViajesIniciados(null);
        empresa.setViajesTerminados(null);
           
    }


}
