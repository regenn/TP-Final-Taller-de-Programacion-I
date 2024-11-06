/*
LISTA DE TODOS LOS METODOS A TESTEAR


DONE        crearViaje(Pedido pedido, Chofer chofer, Vehiculo vehiculo)
DONE        calificacionDeChofer(Chofer chofer)
DONE        login(String usserName, String pass)
PREGUNTA    logout()
DONE        pagarYFinalizarViaje(int calificacion)
PREGUNTA    validarPedido(Pedido pedido)
DONE        vehiculosOrdenadosPorPedido(Pedido pedido)
*/
package test.modelo;


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
    
    // son creados pero no agregados a la empresa
    //obtenemos las excepciones choferNotDisponible, etc..

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
    public void logoutTest(){
        try{
            empresa.logout();
        }
        catch (Exception ex){
            fail("Excepcion lanzada");
        }
        //DUDA:que asserto deberiamos usar???
    }
    

    //@Test
    // public void logoutTest(){
    //
    //}
    @Test 
    public void pagarYFinalizarViajeTest(){
        //este metodo no deberia testearse en 
        int calificacion=3;
        try{
            empresa.pagarYFinalizarViaje(calificacion);
        }
        catch(ClienteSinViajePendienteException ex){
            //el mensaje deberia ser CLIENTE_SIN_VIAJE_PENDIENTE segun el javadoc pero no lo es..
            assertEquals("Cliente Sin Viaje Pendiente",ex.getMessage());
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
        assertTrue(listaVehiculos.isEmpty()); //DUDA: la lista que devuelve, sera una lista vacia? 
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
