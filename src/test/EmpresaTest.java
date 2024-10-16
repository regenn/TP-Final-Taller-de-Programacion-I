package test;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import util.*;

//escenario
public class EmpresaTest {

    //setup de una empresa vacia -> instancia=Empresa.getInstance()
    @Before
    public void setUp() throws Exception{
        //deberia hacer diferentes setups?


    }
    //Al tener un singleton -> en cada metodo Test tengo q limpiar los datos del singleton!! 
    //CADA clase de test se maneja por un escenario especifico. Aunque puede haber mas de una clase usando ese escenario
    //por ahi esta bueno tener metodos que armen escenarios -> public void ArmarEscenario1()
    //
    @Test
    public void crearViajeTest(){
        Cliente cliente1 = new Cliente("usuario1","pass1","Cliente1");
        Chofer chofer = new ChoferPermanente("documento","chofer1",2023,1);
        Vehiculo auto = new Auto("ABC123",3,true);
        Vehiculo moto = new Moto("ABC124");
        Vehiculo combi = new Combi("ABC125",6,false);
        Pedido pedido = new Pedido(cliente1,1,true,true,3, "ZONA_STANDARD");
        Empresa instancia = Empresa.getInstance();

        
        //como 
        try{
            instancia.agregarPedido(pedido);
           instancia.crearViaje(pedido,chofer,auto);
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
        //catch (Cliente)
        /*
        SinVehiculoParaPedidoException,
ClienteNoExisteException,
ClienteConViajePendienteException,
ClienteConPedidoPendienteException
        */
      
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
