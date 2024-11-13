package modelo;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;
import java.util.ArrayList;


//Escenario con un cliente, un vehiculo de cada tipo y un chofer de cada tipo. En este escenario no se generan pedidos. 
public class EmpresaTest {
    Empresa empresa;

    Chofer choferTemp,choferPerm;
    Cliente cliente;
    Vehiculo auto,moto,combi;

    HashMap<String,Cliente>clientes;
    //HashMap<String,Chofer>choferes;
    //HashMap<String,Vehiculo>vehiculos;
    
    @Before
    public void setUp(){

        clientes=new HashMap<String,Cliente>();
       // choferes=new HashMap<String,Chofer>();
        //vehiculos=new HashMap<String,Vehiculo>();

        empresa = Empresa.getInstance();
        choferTemp = new ChoferTemporario("12345","nombreChofer");
        choferPerm = new ChoferPermanente("12346","nombreChofer2", 2023, 2);
        try{
            empresa.agregarChofer(choferTemp);
            empresa.agregarChofer(choferPerm);
        } catch (Exception e){

        }

        moto = new Moto("123-456");
        auto = new Auto("234-567", 3, true);
        combi = new Combi("345-678", 6, false);

        try{
            empresa.agregarVehiculo(moto);
            empresa.agregarVehiculo(auto);
            empresa.agregarVehiculo(combi);
        } catch (Exception e){

        }

        cliente=new Cliente("usuario","pass","nombreCliente");
        clientes.put("usuario",cliente);
        
        empresa.setClientes(clientes);
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
        }catch(Exception ex){
            fail("Se lanzo una excepcion no esperada");
        }
        assertEquals("usuario1",empresa.getClientes().get("usuario1").getNombreUsuario());
        try{
            empresa.agregarCliente("usuario1","pass1","nombreEjemplo");
        }
        catch(UsuarioYaExisteException ex){
            assertEquals(ex.getUsuarioPretendido(),empresa.getClientes().get("usuario1").getNombreUsuario());
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
        assertEquals(vehiculonuevo,empresa.getVehiculos().get("453524"));
        try{
            empresa.agregarVehiculo(vehiculonuevo);
        }
        catch(VehiculoRepetidoException ex){
            assertEquals(vehiculonuevo.getPatente(),ex.getPatentePrentendida());
        }
        catch(Exception ex){
            fail("Se lanzo una excepcion no esperada");
        }
    }

    @Test
    public void getTotalSalariosTest(){
        assertEquals(empresa.getTotalSalarios(),(choferPerm.getSueldoNeto()+choferTemp.getSueldoNeto()),0.001);
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
       assertEquals(2, empresa.getChoferes().size());
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
        assertEquals(empresa.getVehiculos().size(), 3);
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
     
   assertEquals(clientes, empresa.getClientes());
    }
}
