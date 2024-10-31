package test;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;
import java.util.ArrayList;


//escenario
public class EmpresaTest {
    Empresa empresa; 
    Chofer chofer;
    Vehiculo auto;
    Vehiculo moto;
    Vehiculo combi;
    
    //setup de una empresa vacia -> instancia=Empresa.getInstance()
    @Before
    public void setUp() throws Exception{
        empresa = Empresa.getInstance();
        chofer = new ChoferPermanente("documento","chofer1",2023,1);
        auto = new Auto("ABC123",3,true);
        moto = new Moto("ABC124");
        combi = new Combi("ABC125",6,false);

        empresa.setChoferes(new HashMap<String,Chofer>());
        empresa.setChoferesDesocupados(new ArrayList<Chofer>());
        empresa.setClientes(new HashMap<String,Cliente>());
        empresa.setPedidos(new HashMap<Cliente,Pedido>());
        empresa.setVehiculos(new HashMap<String,Vehiculo>());
        empresa.setVehiculosDesocupados(new ArrayList<Vehiculo>());
        empresa.setViajesIniciados(new HashMap<Cliente,Viaje>());
        empresa.setViajesTerminados(new ArrayList<Viaje>());


      
    }
    //Al tener un singleton -> en cada metodo Test tengo q limpiar los datos del singleton!! 
    //CADA clase de test se maneja por un escenario especifico. Aunque puede haber mas de una clase usando ese escenario
    //por ahi esta bueno tener metodos que armen escenarios -> public void ArmarEscenario1()
    
    @Test
    public void crearViajeTest(){
        //Cliente cliente1 = new Cliente("usuario1","pass1","Cliente1");
        Pedido pedido;
        //Empresa instancia = Empresa.getInstance();
        //how... just.. hoow?
        try{
            empresa.agregarVehiculo(auto);
            empresa.agregarVehiculo(moto);
            empresa.agregarVehiculo(combi);
            empresa.agregarChofer(chofer);
            empresa.agregarCliente("usuario1","pass1","Cliente1");
            pedido = new Pedido(empresa.getClientes().get("usuario1"),1,true,true,3, "ZONA_STANDARD");
            empresa.agregarPedido(pedido);
            empresa.crearViaje(pedido,chofer,auto);
        }
        catch (PedidoInexistenteException ex) {
            fail("Excepcion de pedido inexistente lanzada");
        } 
        catch (ClienteConViajePendienteException ex){
            fail("Excepcion de cliente con viaje pendiente lanzada");
        }        
        catch (ChoferNoDisponibleException ex){
            fail("Excepcion de chofer no disponible lanzada");     
        }
        catch(VehiculoNoDisponibleException ex){
            fail("Excepcion de vehiculo no disponible lanzada");
        }
        catch (VehiculoNoValidoException ex){
            fail ("El vehiculo no es valido para este pedido");
        } 
        catch (ClienteNoExisteException  ex){
            fail("El cliente no existe");
        }
        catch(SinVehiculoParaPedidoException ex){
            fail("No hay ningun vehiculo para ese pedido");
        }
        catch (ClienteConPedidoPendienteException ex){
            fail("El cliente ya tiene un pedido pendiente");
        }
        catch (UsuarioYaExisteException ex){
            fail("Este usuario ya existe");
        }
        catch(VehiculoRepetidoException ex){
            fail("El vehiculo ya ha sido creado");
        }
        catch(ChoferRepetidoException ex){
            fail("El chofer ya existe");
        }
        // esta es una prueba a ver si se guardan los cambios

        //catch (Cliente)
        /*
        SinVehiculoParaPedidoException,
ClienteNoExisteException,
ClienteConViajePendienteException,
ClienteConPedidoPendienteException
        */
      
    }

    @After
    public void tearDown(){

        combi=null;
        moto=null;
        auto=null;
        chofer=null;
        empresa.setChoferes(null);
        empresa.setChoferesDesocupados(null);
        empresa.setClientes(null);
        empresa.setPedidos(null);
        empresa.setVehiculos(null);
        empresa.setVehiculosDesocupados(null);
        empresa.setViajesIniciados(null);
        empresa.setViajesTerminados(null);
    }

    /*
    TESTEO DE EXCEPCIONES
     valor esperado -> SE TIENE QUE LANZAR LA EXCEPCION
     DENTRO DEL TRY:
     invoco al metodo metodo(cliente)
     Assert.fail("debio lanzarse la excepcion tal tal")

     catch(TalException ex)
     Assert.equals(cliente, ex.getCliente()) -> cuando es la excepcion que debio lanzar
    la excepcion tiene parametros basically

     que pasa si lanza mas de una excepcion?
     catch (Exceptiontipo2 ex)
        Assert.fail() -> este es un catch donde se lanzo una excepcion no esperada para este escenario
     
     */

}
