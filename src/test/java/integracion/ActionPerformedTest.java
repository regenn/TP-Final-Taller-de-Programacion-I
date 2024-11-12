package integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.event.ActionEvent;
import java.util.HashMap;

import javax.swing.JButton;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import GUI.FalsoOptionPane;
import controlador.Controlador;
import util.Constantes;
import vista.Ventana;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;
import java.util.ArrayList;
import controlador.*;
import util.Constantes;
import util.Mensajes;

public class ActionPerformedTest {
    Empresa empresa;
    Controlador controlador;
    Usuario usuariologeado;
    FalsoOptionPane op;
    Ventana ventana;
    Cliente cliente;

    @Before
    public void setUp() throws Exception{

        empresa=Empresa.getInstance();
        empresa.setClientes(new HashMap<String,Cliente>());
        controlador= new Controlador();
        op = new FalsoOptionPane();
        ventana = mock(Ventana.class);
        controlador.setVista(ventana);
		controlador.getVista().setOptionPane(op);
        when(ventana.getOptionPane()).thenReturn(op);
        
    }

    @After
    public void tearDown() throws Exception{
        empresa.getChoferes().clear();
        empresa.getClientes().clear();
        empresa.getPedidos().clear();
    }

    @Test
    public void actionPerformedLoginTest(){
        try{
            empresa.agregarCliente("usuario1","contrasenia1","nombre");
            usuariologeado= this.empresa.login("usuario1","contrasenia1");
            this.empresa.setUsuarioLogeado(usuariologeado);
        }
        catch(Exception e){}

        when(ventana.getUsserName()).thenReturn("usuario1");
        when(ventana.getPassword()).thenReturn("contrasenia1");
        
        ActionEvent login = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.LOGIN);
        controlador.actionPerformed(login);

        assertEquals(usuariologeado,this.empresa.getUsuarioLogeado());
    }

    @Test
    public void actionPerformedLogoutAdminTest(){
        try{
            when(ventana.getUsserName()).thenReturn("admin");
            when(ventana.getPassword()).thenReturn("admin");        
            this.controlador.login();
            
            ActionEvent logoutadmin = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.CERRAR_SESION_ADMIN);
            controlador.actionPerformed(logoutadmin);
            assertNull(empresa.getUsuarioLogeado());


        } catch (Exception e){
            fail ("No tendria que haber lanzado una exception");
        }
    }

    @Test
    public void actionPerformedLogoutClienteTest(){
        try{
            empresa.agregarCliente("usuario1","contrasenia1","nombre");
            usuariologeado= this.empresa.login("usuario1","contrasenia1");
            this.empresa.setUsuarioLogeado(usuariologeado);
            when(ventana.getUsserName()).thenReturn("usuario1");
            when(ventana.getPassword()).thenReturn("contrasenia1");
            controlador.login();

            ActionEvent logoutadmin = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.CERRAR_SESION_CLIENTE);
            controlador.actionPerformed(logoutadmin);
            assertNull(empresa.getUsuarioLogeado());


        } catch (Exception e){
            fail ("No tendria que haber lanzado una exception");
        }
    }


    @Test
    public void actionPerformedRegistroTest(){
        try{
            when(ventana.getRegNombreReal()).thenReturn("nombreNuevo");
            when(ventana.getRegUsserName()).thenReturn("usuarioNuevo");
            when(ventana.getRegPassword()).thenReturn("passNuevo");
            when(ventana.getRegConfirmPassword()).thenReturn("passNuevo");

            ActionEvent registrarUsuario = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.REG_BUTTON_REGISTRAR);
            controlador.actionPerformed(registrarUsuario);
            
            Usuario nuevoUsuario = empresa.getClientes().get("usuarioNuevo");
            assertNotNull(nuevoUsuario);

            assertEquals(ventana.getRegUsserName(),nuevoUsuario.getNombreUsuario());
            assertEquals(ventana.getRegPassword(),nuevoUsuario.getPass());
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion");
        }

    }
  

    @Test
    public void actionPerformedNuevoPedidoTest(){
        try{
        	when(ventana.getUsserName()).thenReturn("user");
            when(ventana.getPassword()).thenReturn("pass");
            this.controlador.login();
            
            empresa.agregarCliente("user","pass","user");
            Cliente cliente = empresa.getClientes().get("user");
            
            empresa.setUsuarioLogeado(cliente);
            assertNotNull(cliente);

            when(ventana.getCantidadPax()).thenReturn(3);
            when(ventana.isPedidoConMascota()).thenReturn(false);
            when(ventana.isPedidoConBaul()).thenReturn(false);
            when(ventana.getCantKm()).thenReturn(10);
            when(ventana.getTipoZona()).thenReturn(Constantes.ZONA_STANDARD);
            
            ActionEvent nuevoPedidoUsuario = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.NUEVO_PEDIDO);
            controlador.actionPerformed(nuevoPedidoUsuario);

            Pedido pedidoAgregado = empresa.getPedidoDeCliente(cliente);
            assertNotNull(pedidoAgregado);

            assertEquals(ventana.getCantidadPax(), pedidoAgregado.getCantidadPasajeros());
            assertEquals(ventana.isPedidoConMascota(), pedidoAgregado.isMascota());
            assertEquals(ventana.isPedidoConBaul(), pedidoAgregado.isBaul());
            assertEquals(ventana.getCantKm(), pedidoAgregado.getKm());
        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion");
        }
    }



    @Test
    public void actionPerformedCalificarPagarTest(){
        try{
        	
        	when(ventana.getUsserName()).thenReturn("user");
            when(ventana.getPassword()).thenReturn("pass");
            this.controlador.login();
            
            empresa.agregarCliente("user","pass","user");
            Cliente cliente = empresa.getClientes().get("user");
            
            empresa.setUsuarioLogeado(cliente);
            assertNotNull(cliente);
            
            empresa.agregarVehiculo(new Auto("123-456",3,false));
            empresa.agregarChofer(new ChoferTemporario("234455","nombreChofer"));
            empresa.agregarPedido(new Pedido(cliente,2,false,false,5,Constantes.ZONA_STANDARD));
            empresa.crearViaje(empresa.getPedidos().get(cliente),empresa.getChoferes().get("234455"),empresa.getVehiculos().get("123-456"));

            when(ventana.getCalificacion()).thenReturn(5);
            ActionEvent nuevaCalificacionUsuario = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.CALIFICAR_PAGAR);
            controlador.actionPerformed(nuevaCalificacionUsuario);
            
            //this.controlador.calificarPagar();

        } catch (Exception e){
            fail("No deberia lanzarse la excepcion: "+ e.getMessage());
        }
    }
       

    @Test
    public void actionPerformedNuevoChoferTest(){
        try{
            when(ventana.getUsserName()).thenReturn("admin");
            when(ventana.getPassword()).thenReturn("admin");        
            this.controlador.login();
            when(ventana.getTipoChofer()).thenReturn(Constantes.TEMPORARIO);
            when(ventana.getNombreChofer()).thenReturn("choferTemp");

            when(ventana.getDNIChofer()).thenReturn("123456778");

            ActionEvent nuevoChoferAgregado = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.NUEVO_CHOFER);
            this.controlador.actionPerformed(nuevoChoferAgregado);; // tendria que haber llammado a empresa.agregarChofer();
        
            Chofer choferAgregado = empresa.getChoferes().get("123456778");
            assertNotNull(choferAgregado);

            assertEquals(ventana.getDNIChofer(),choferAgregado.getDni());
            assertEquals(ventana.getNombreChofer(), choferAgregado.getNombre());

        } catch (Exception e){
            fail ("No tendria que haber lanzado una exception");
        }
    }

    @Test
    public void actionPerformedNuevoVehiculoTest(){
        try{
            when(ventana.getUsserName()).thenReturn("admin");
            when(ventana.getPassword()).thenReturn("admin");        
            this.controlador.login();
            
            when(ventana.getTipoVehiculo()).thenReturn(Constantes.AUTO);
            when(ventana.getPatente()).thenReturn("ABC123");
            when(ventana.getPlazas()).thenReturn(4);
            when(ventana.isVehiculoAptoMascota()).thenReturn(false);

            ActionEvent nuevoVehiculoAgregado = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.NUEVO_VEHICULO);
            this.controlador.actionPerformed(nuevoVehiculoAgregado); // tendria que haber llammado a empresa.agregarChofer();

            Vehiculo vehiculoAgregado = empresa.getVehiculos().get("ABC123");
            assertNotNull(vehiculoAgregado);

            assertEquals(ventana.getPatente(),vehiculoAgregado.getPatente());
            assertEquals(ventana.getPlazas(), vehiculoAgregado.getCantidadPlazas());
            assertEquals(ventana.isVehiculoAptoMascota(), vehiculoAgregado.isMascota());

        } catch (Exception e){
            fail ("No tendria que haber lanzado una exception");
        }
    }

    @Test
    public void actionPerformedNuevoViajeTest(){
        try{
            empresa.agregarCliente("cliente1", "pass", "cliente1");
            Cliente cliente1 = empresa.getClientes().get("cliente1");
            Chofer choferTemp = new ChoferTemporario("123456", "choferTemp");
            empresa.agregarChofer(choferTemp);
            Vehiculo auto = new Auto("ABC123", 3, false);
            empresa.agregarVehiculo(auto);
            Pedido pedido = new Pedido(cliente1, 3, false, false, 10, Constantes.ZONA_STANDARD);
            empresa.agregarPedido(pedido);
    
            when(ventana.getPedidoSeleccionado()).thenReturn(pedido);
            when(ventana.getChoferDisponibleSeleccionado()).thenReturn(choferTemp);
            when(ventana.getVehiculoDisponibleSeleccionado()).thenReturn(auto);

            ActionEvent nuevoViajeAgregado = new ActionEvent(new JButton(), ActionEvent.ACTION_PERFORMED, Constantes.NUEVO_VIAJE);
            this.controlador.actionPerformed(nuevoViajeAgregado); // tendria que haber llammado a empresa.agregarChofer();

            Viaje viajeAgregado = empresa.getViajeDeCliente(cliente1);
            assertNotNull(viajeAgregado);

            assertEquals(ventana.getPedidoSeleccionado(), viajeAgregado.getPedido());
            assertEquals(ventana.getChoferDisponibleSeleccionado(), viajeAgregado.getChofer());
            assertEquals(ventana.getVehiculoDisponibleSeleccionado(), viajeAgregado.getVehiculo());

        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
    }

}
