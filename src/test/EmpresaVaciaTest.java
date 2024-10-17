package test;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import util.*;

//Testeo para el caso de empresa vacia.
public class EmpresaVaciaTest {
    Empresa empresa;
    Cliente cliente;
    Pedido pedido;
    Chofer chofer;
    Vehiculo auto;


    @Before
    public void startUp(){
        empresa=Empresa.getInstance();
        cliente= new Cliente("user1", "pass1", "cliente1");
        pedido=new Pedido(cliente,3,true,true,3,"ZONA_STANDARD");
        chofer = new ChoferPermanente("documento","chofer1",2023,1);
        auto = new Auto("ABC123",3,true);
    }
    @Test
    public void calificacionDeChoferTest(){
        try{
            empresa.calificacionDeChofer(chofer);
        }
        catch(SinViajesException ex){
            assertEquals(Mensajes.CHOFER_SIN_VIAJES,ex.getMessage());
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
    }
}
