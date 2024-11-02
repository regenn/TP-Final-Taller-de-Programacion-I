package test;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
//import Utils.MockUtils;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;
import java.util.ArrayList;


//escenario
public class EmpresaTest {
    Empresa empresa;

    Chofer choferTemp,choferPerm;
    Cliente cliente;
    Vehiculo auto,moto,combi;

    HashMap<String,Cliente>clientes;
    HashMap<String,Chofer>choferes;
    HashMap<String,Vehiculo>vehiculos;
    
    @Before
    public void setUp(){

        clientes=new HashMap<String,Cliente>();
        choferes=new HashMap<String,Chofer>();
        vehiculos=new HashMap<String,Vehiculo>();

        empresa = Empresa.getInstance();
        choferTemp = new ChoferTemporario("12345","nombreChofer");
        choferPerm = new ChoferPermanente("12346","nombreChofer2", 2023, 2);

        choferes.put("12345",choferTemp);
        choferes.put("12346",choferPerm);

        empresa.setChoferes(choferes);

        moto = new Moto("123-456");
        auto = new Auto("234-567", 3, true);
        combi = new Combi("345-678", 6, false);

        vehiculos.put("123-456",moto);
        vehiculos.put("234-567",auto);
        vehiculos.put("345-678",combi);

        empresa.setVehiculos(vehiculos);

        cliente=new Cliente("usuario","pass","nombreCliente");
        clientes.put("usuario",cliente);
        
        empresa.setClientes(clientes);
    
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
        //TODO: limpiar la empresa.
        
    }
    @Test
    public void agregarChoferTest(){
        Chofer chofernuevo=new ChoferTemporario("7882345","nombreEjemplo");
        try{
            empresa.agregarChofer(chofernuevo);
        }
        catch(Exception ex){
            fail("Se lanzo una excepcion no esperada");
        }
        assertEquals(chofernuevo,empresa.getChoferes().get("7882345"));
        try{
            empresa.agregarChofer(chofernuevo);
        }
        catch(ChoferRepetidoException ex){
            assertEquals(chofernuevo,ex.getChoferExistente());
        }
        catch(Exception ex){
            fail("Se lanzo una excepcion no esperada");
        }
    }

    @Test
    public void agregarClienteTest(){
        try{
            empresa.agregarCliente("usuario1","pass1","nombreEjemplo");
        }
        catch(Exception ex){
            fail("Se lanzo una excepcion no esperada");
        }
        assertEquals("usuario1",empresa.getClientes().get("usuario1").getNombreUsuario());
        try{
            empresa.agregarCliente("usuario1","pass1","nombreEjemplo");
        }
        catch(UsuarioYaExisteException ex){
            assertEquals(ex.getUsuarioPretendido(),empresa.getClientes().get("usuario1"));
        }
        catch(Exception ex){
            fail("Se lanzo una excepcion no esperada");
        }
    }

    @Test
    public void agregarVehiculoTest(){
        Vehiculo vehiculonuevo=new Auto("453524",3,true);
        try{
            empresa.agregarVehiculo(vehiculonuevo);
        }
        catch(Exception ex){
            fail("Se lanzo una excepcion no esperada");
        }
        //TODO: fijarse que los valores de testeo sean iguales a los del excel!!!!1
        assertEquals(vehiculonuevo,empresa.getVehiculos().get("453524"));
        try{
            empresa.agregarVehiculo(vehiculonuevo);
        }
        catch(VehiculoRepetidoException ex){
            assertEquals(vehiculonuevo,ex.getVehiculoExistente());
        }
        catch(Exception ex){
            fail("Se lanzo una excepcion no esperada");
        }
    }

    @Test
    public void getTotalSalariosTest(){
        assertEquals(empresa.getTotalSalarios(),(choferPerm.getSueldoBruto()+choferTemp.getSueldoBruto()),0.001);
    }
    
    @Test
    public void loginTest1(){
        try{
            Usuario u = empresa.login("admin", "1234");   
            empresa.logout();    
        }
        catch(PasswordErroneaException e){}//error esperado
        catch(UsuarioNoExisteException e){}
    }

    @Test
    public void loginTest2(){
        try{
            Usuario u = empresa.login("Paulmescal", "1234"); 
            empresa.logout();      
        }
        catch(PasswordErroneaException e){}
        catch(UsuarioNoExisteException e){}//error esperado
    }
    
    @Test
    public void loginTest3(){
        try{
            Usuario u = empresa.login("admin", "admin");
            assertEquals(u, empresa.getUsuarioLogeado());
            empresa.logout();       
        }
        catch(PasswordErroneaException e){}
        catch(UsuarioNoExisteException e){}
    }    

    @Test
    public void logoutTest1(){
        try{
            Usuario u = empresa.login("admin", "admin");
            empresa.logout();
            assertEquals(null, empresa.getUsuarioLogeado());
        }
        catch(PasswordErroneaException e){}
        catch(UsuarioNoExisteException e){}
    }  
    
    @Test
    public void isAdminTest1(){
        try{
            Usuario u = empresa.login("admin", "admin");
            assertEquals(true, empresa.isAdmin());
            empresa.logout();
        }
        catch(PasswordErroneaException e){}
        catch(UsuarioNoExisteException e){}
    }       
    
    @Test
    public void setUsuarioLogeadoTest(){
        try{
            Usuario u= new Cliente("usuario2","pass2","nombre2");
            empresa.setUsuarioLogeado(u);
            assertEquals(u,empresa.getUsuarioLogeado());
            empresa.logout();            
        }
        catch(Exception ex){
            fail("Se lanzo una excepcion no esperada");
        }
    }
    
    @Test
    public void getUsuarioLogeadoTest1(){
        try{
            Usuario u = empresa.login("admin", "admin");
            assertEquals(u, empresa.getUsuarioLogeado());
            empresa.logout();
        }
        catch(PasswordErroneaException e){}
        catch(UsuarioNoExisteException e){}
    }  

    @Test
    public void getUsuarioLogeadoTest2(){
        assertEquals(null, empresa.getUsuarioLogeado());
    }        

    @Test
    public void setVehiculosTest(){
        HashMap<String,Vehiculo> vehiculos = new HashMap<String,Vehiculo>();
        empresa.setVehiculos(vehiculos);
        assertEquals(vehiculos, empresa.getVehiculos());
    }    

    @Test 
    public void getChoferesTest(){
       assertEquals(empresa.getChoferes(),choferes);
    }

    @Test 
    public void getChoferesDesocupadosTest(){
       assertEquals(2,empresa.getChoferesDesocupados().size());
       assertEquals(true,empresa.getChoferesDesocupados().contains(choferPerm));
       assertEquals(true,empresa.getChoferesDesocupados().contains(choferTemp));
    }

    @Test 
    public void getClientesTest(){
      assertEquals(empresa.getClientes(),clientes);
    }

    @Test
    public void getVehiculosTest(){
        assertEquals(vehiculos, empresa.getVehiculos());
    }         

    @Test
    public void getVehiculosDesocupadosTest(){
        assertEquals(3, empresa.getVehiculos().size());
        assertEquals(true, empresa.getVehiculosDesocupados().contains(moto));
        assertEquals(true, empresa.getVehiculosDesocupados().contains(auto));
        assertEquals(true, empresa.getVehiculosDesocupados().contains(combi));
    }      
    
    @Test
    public void setVehiculosDesocupadosTest(){
        ArrayList<Vehiculo> vehiculosDesocupados= new ArrayList<Vehiculo>();
        empresa.setVehiculosDesocupados(vehiculosDesocupados);
        assertEquals(vehiculosDesocupados,empresa.getVehiculosDesocupados());

    }

    @Test
    public void setChoferesTest(){
        HashMap<String,Chofer> choferes= new HashMap<String,Chofer>();
        empresa.setChoferes(choferes);
        assertEquals(choferes,empresa.getChoferes());
    }

    @Test
    public void setChoferesDesocupadosTest(){
        ArrayList<Chofer> choferesDesocupados= new ArrayList<Chofer>();
        empresa.setChoferesDesocupados(choferesDesocupados);
        assertEquals(choferesDesocupados,empresa.getChoferesDesocupados());
    
    }
    
    @Test
    public void setClientesTest(){
        HashMap<String,Cliente> clientes= new HashMap<String,Cliente>();
        empresa.setClientes(clientes);
     
   assertEquals(clientes,empresa.getChoferesDesocupados());
    }
    /*
    
    ##########################################################################################
    ##########################################################################################
    
    
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
