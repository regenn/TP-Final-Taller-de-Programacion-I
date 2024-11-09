package test.java.modelo;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
//import Utils.MockUtils;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;
import java.util.ArrayList;
import util.*;

public class EmpresaTest2 {
    Empresa empresa;

    Chofer choferTemp,choferPerm;
    Cliente cliente1,cliente2,cliente3,cliente4;
    Vehiculo auto,auto2,moto,combi;
    Pedido pedido1,pedido2,pedido3,pedido4,pedido5,pedido6;

    HashMap<String,Cliente>clientes;
    HashMap<String,Chofer>choferes;
    HashMap<String,Vehiculo>vehiculos;

    @Before
    public void setUp(){

        //clientes=new HashMap<String,Cliente>();
        choferes=new HashMap<String,Chofer>();
        vehiculos=new HashMap<String,Vehiculo>();

        empresa = Empresa.getInstance();
        choferTemp = new ChoferTemporario("12345","nombreChofer");
        choferPerm = new ChoferPermanente("12346","nombreChofer2", 2023, 2);

        //choferes.put("12345",choferTemp);
        //choferes.put("12346",choferPerm);

       // empresa.setChoferes(choferes);

        auto = new Auto("abc123", 4, false);
        auto2 = new Auto("abc1238",4, false);
        moto = new Moto("abc3456");

        //vehiculos.put("123-456",moto);
        //vehiculos.put("234-567",auto);
        //vehiculos.put("345-678",combi);

        //cliente1=new Cliente("cliente1","pass","cliente1");
        //clientes.put("usuario",cliente1);

        //cliente2=new Cliente("cliente2","pass","cliente2");
        //clientes.put("usuario",cliente2);

        //cliente3=new Cliente("cliente3","pass","cliente3");
        //clientes.put("usuario",cliente3);
        
        cliente4=new Cliente("cliente4","pass","cliente44");

        try{
            empresa.agregarCliente("cliente1","pass","cliente1");
            empresa.agregarCliente("cliente2","pass","cliente2");
            empresa.agregarCliente("cliente3","pass","cliente3");
            empresa.agregarVehiculo(auto);
            empresa.agregarVehiculo(auto2);
            empresa.agregarVehiculo(moto);
            empresa.agregarChofer(choferPerm);
            empresa.agregarChofer(choferTemp);

        }catch (Exception ex){}
        

        //empresa.setClientes(clientes);
        
        pedido1= new Pedido(empresa.getClientes().get("cliente1"),3,false,false,10,Constantes.ZONA_STANDARD);
        pedido2= new Pedido(empresa.getClientes().get("cliente1"),3,true,false,10,Constantes.ZONA_STANDARD);//ningun vehiculo cubre
        pedido3= new Pedido(cliente4,3,false,false,10,Constantes.ZONA_STANDARD);// cliente no existe
        pedido4= new Pedido(empresa.getClientes().get("cliente1"),3,false,false,10,Constantes.ZONA_STANDARD);
        pedido5= new Pedido(empresa.getClientes().get("cliente1"),3,false,false,2,Constantes.ZONA_STANDARD);//no va en la lista.
        pedido6= new Pedido(empresa.getClientes().get("cliente2"),3,false,false,10,Constantes.ZONA_STANDARD);

        //try{
        //    empresa.agregarPedido(pedido1);
        //}
        //catch(Exception ex){}
        //empresa.agregarPedido(pedido2);
        //empresa.agregarPedido(pedido3);
        //empresa.agregarPedido(pedido4);
       // empresa.agregarPedido(pedido6);

        //empresa.setVehiculos(vehiculos);
    
        /*try{
            empresa.agregarChofer(choferPerm);
            empresa.agregarChofer(choferTemp);
            empresa.agregarVehiculo(moto);
            empresa.agregarVehiculo(auto);
            empresa.agregarVehiculo(combi);
            empresa.agregarCliente("usuario","pass","nombreCliente");
        }
        catch(Exception ex){
            fail("No deberia tirar una excepcion");
        }*/
    }
    
    @After
    public void tearDown(){
        empresa.getPedidos().clear();
        empresa.getChoferes().clear();
        empresa.getClientes().clear();
        empresa.getVehiculos().clear();
        empresa.getChoferesDesocupados().clear();
        empresa.getVehiculosDesocupados().clear();
        empresa.getViajesIniciados().clear();
        empresa.getViajesTerminados().clear();
        empresa.getHistorialViajeChofer(choferPerm).clear();
        empresa.getHistorialViajeChofer(choferTemp).clear();
        empresa.getHistorialViajeCliente(cliente1).clear();
        empresa.getHistorialViajeCliente(cliente2).clear();
    }

    @Test
    public void crearViajeTestExitoso(){
        try{
            empresa.agregarPedido(pedido1);
            empresa.crearViaje(pedido1, choferTemp, auto2);          
        }
        catch(Exception ex){
            fail(ex.getMessage());
        }  
    }

    @Test
    public void crearViajeTestPedidoInexistente(){
        try{
            //Pedido no existe en la lista de pedidos
            empresa.crearViaje(pedido5, choferPerm, auto);
            fail("Tendria que haberse lanzado una excepcion");
        }
        catch(PedidoInexistenteException ex){
            assertEquals(pedido5,ex.getPedido());
        }
        catch(Exception ex){
            fail("Se lanzo una excepcion no esperada");
        }
    }

    @Test
    public void crearViajeTestChoferNoDisponible(){
        try{
            empresa.agregarPedido(pedido1);
            empresa.agregarPedido(pedido6);
            empresa.crearViaje(pedido1, choferTemp, auto2);
            empresa.crearViaje(pedido6,choferTemp,auto);
            fail("Tendria que haberse lanzado una excepcion");
        }
        catch(ChoferNoDisponibleException ex){
            assertEquals(choferTemp,ex.getChofer());
        }
        catch(Exception ex){
            fail(ex.getMessage());
        }

    }

    @Test
    public void crearViajeTestVehiculoNoDisponible(){
        try{
            empresa.agregarPedido(pedido1);
            empresa.agregarPedido(pedido6);
            empresa.crearViaje(pedido1, choferPerm, auto);
            empresa.crearViaje(pedido6,choferTemp,auto);
            fail("Tendria que haberse lanzado una excepcion");
        }
        catch(VehiculoNoDisponibleException ex){
            assertEquals(auto, ex.getVehiculo());
        }
        catch(Exception ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void crearViajeTestVehiculoNoValido(){
        try{
            empresa.agregarPedido(pedido1);
            empresa.crearViaje(pedido1, choferPerm, moto);
            fail("Tendria que haberse lanzado una excepcion");
        }
        catch(VehiculoNoValidoException ex){
            assertEquals(moto, ex.getVehiculo());
        }
        catch(Exception ex){
            fail("Se lanzo una excepcion no esperada");
        }
    }

    @Test
    public void crearViajeTestClienteConViajePendiente(){
        try{
            empresa.agregarPedido(pedido1);
            empresa.agregarPedido(pedido4);
            empresa.crearViaje(pedido1, choferPerm, auto);
            empresa.crearViaje(pedido4,choferPerm,auto);
            fail("Tendria que haberse lanzado una excepcion");
        }
        catch(ClienteConViajePendienteException ex){
            
        }
        catch(Exception ex){
            fail(ex.getMessage());
        }
    }

    @Test
    public void agregarPedidoExitosoTest(){
        try{
            empresa.agregarPedido(pedido1);
        } catch (Exception e){
            fail ("No tendria que saltar ninguna exception");
        }
    }

    @Test
    public void agregarPedidoSinVehiculoTest(){
        try{
            empresa.agregarPedido(pedido2);
            fail("Debe lanzar un sin vehiculo para pedido exception");
        } catch (SinVehiculoParaPedidoException e){
            assertEquals(pedido2,e.getPedido());
        } catch (Exception e){
            fail("Se lanzo otra excepcion");
        }
    }

    @Test
    public void agregarPedidoSinClienteTest(){
        try{
            empresa.agregarPedido(pedido3);
            fail("Debe lanzar un clienteNoExisteException");
        } catch (ClienteNoExisteException e){


        } catch (Exception e){
            fail("Se lanzo otra excepcion");
        }
    }

    @Test
    public void agregarPedidoConClienteViajePendienteTest(){
        try{
            empresa.agregarPedido(pedido1);
            empresa.crearViaje(pedido1, choferTemp, auto);
            empresa.agregarPedido(pedido4);
            fail("Tendria que haber lanzado la ClienteConViajePendienteException");
        } catch (ClienteConViajePendienteException e){    

        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void pagarYFinalizarViajeTestExitoso(){
        try{
            empresa.agregarPedido(pedido1);
            empresa.crearViaje(pedido1, choferPerm, auto2);
            empresa.login(pedido1.getCliente().getNombreUsuario(),pedido1.getCliente().getPass());
            empresa.pagarYFinalizarViaje(5);
        }
        catch (Exception e){
            fail("No se deberia lanzar una excepcion");
        }
    }

    @Test
    public void pagarYFinalizarViajeTestSinViaje(){
        try{
            empresa.agregarPedido(pedido1);
            empresa.login(pedido1.getCliente().getNombreUsuario(),pedido1.getCliente().getPass());
            empresa.pagarYFinalizarViaje(5);
            fail("Deberia haberse lanzado una excepcion");
        }
        catch (ClienteSinViajePendienteException e){

        } catch (Exception e){
            fail("Se lanzo otra excepcion");
        }
    }

    @Test
    public void vehiculosOrdenadosPorPedidoTestExitoso(){
        try{
            empresa.agregarPedido(pedido1);
            ArrayList<Vehiculo> vehiculos =empresa.vehiculosOrdenadosPorPedido(pedido1);
            assertEquals(2,vehiculos.size());
        }catch (Exception e){
            fail("No se deberia lanzar una excepcion");
        }


    }

    @Test
    public void agregarPedidoConClientePedidoPendienteTest(){
        try{
            empresa.agregarPedido(pedido1);
            empresa.agregarPedido(pedido4);
            fail("Tendria que haber lanzado la ClienteConPedidoPendienteException");
        } catch (ClienteConPedidoPendienteException e){    


        } catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void setPedidosTest(){


        HashMap<Cliente, Pedido> pedidos = new HashMap<Cliente, Pedido>();
        empresa.setPedidos(pedidos);
        assertEquals(pedidos, empresa.getPedidos());
    }


    @Test
    public void setViajesIniciadosTest(){
        HashMap<Cliente, Viaje> viajesIniciados = new HashMap<Cliente, Viaje>();
        empresa.setViajesIniciados(viajesIniciados);
        assertEquals(viajesIniciados, empresa.getViajesIniciados());
    }


    @Test
    public void setViajesTerminadosTest(){
        ArrayList<Viaje> viajesTerminados = new ArrayList<Viaje>();
        empresa.setViajesTerminados(viajesTerminados);
        assertEquals(viajesTerminados, empresa.getViajesTerminados());
    }

    @Test
    public void validarPedidoExitosoTest(){
        assertEquals(true,empresa.validarPedido(pedido1));
    }
    @Test
    public void validarPedidoFalseTest(){
        assertEquals(false,empresa.validarPedido(pedido2));
    }

    @Test
    public void getPedidoDeClienteExitosoTest(){
        try{
            empresa.agregarPedido(pedido1);
            assertEquals(pedido1,empresa.getPedidoDeCliente(empresa.getClientes().get("cliente1")));
        } catch(Exception ex){
            fail("No deberia lanzarse una excepcion");
        }
    }
   
    @Test
    public void getPedidoDeClienteSinPedidosTest(){
       assertEquals(null,empresa.getPedidoDeCliente(cliente2));
    }

    @Test
    public void getPedidosExitosoTest(){
        assertEquals(0,empresa.getPedidos().size());
    }

    @Test
    public void getViajesIniciadosExitosoTest(){
        assertEquals(0,empresa.getViajesIniciados().size());
        //para testear que el hashmap esté vacio, no deberiamos tener ningun viaje iniciado
        //habria un viaje iniciado para cliente1 supuestamente
    }

    @Test
    public void getHistorialViajeChoferExitosoTest(){
        ArrayList <Viaje> viajes = new ArrayList<Viaje>();
        assertEquals(empresa.getHistorialViajeChofer(choferTemp), viajes);
    }


    @Test
    public void getHistorialViajeClienteExitosoTest(){
        ArrayList <Viaje> viajes = new ArrayList<Viaje>();
        assertEquals(empresa.getHistorialViajeCliente(cliente1), viajes);
    }
        
    @Test
    public void getViajesTerminadosTest(){
        assertTrue(empresa.getViajesTerminados().isEmpty());
    }

}
