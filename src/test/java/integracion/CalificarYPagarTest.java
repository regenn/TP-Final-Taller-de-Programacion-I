package test.java.integracion;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import org.junit.*;

import modeloDatos.*;
//import Utils.MockUtils;
import excepciones.*;
import modeloNegocio.*;
import test.GUI.FalsoOptionPane;
import util.Constantes;

import java.util.HashMap;

import javax.swing.*;
import util.*;

import java.util.ArrayList;
import controlador.*;
import java.awt.event.*;

import vista.Ventana;

//import java.awt.event.ActionEvent;
 

public class CalificarYPagarTest {
    Controlador controlador;
    Empresa empresa;

    @Before
    public void setUp(){
        empresa = Empresa.getInstance();
        controlador = new Controlador();
    }

    @Test
    public void CalificarExitosoTest(){
        try{

            empresa.agregarCliente("cliente1","pass1","nombre1");
            Usuario usuario= this.empresa.login("usuario1","pass1");
            empresa.setUsuarioLogeado(usuario);
            empresa.agregarVehiculo(new Auto("123-456",3,false));
            empresa.agregarChofer(new ChoferTemporario("234455","nombreChofer"));
            empresa.agregarPedido(new Pedido(empresa.getClientes().get("cliente1"),2,false,false,5,Constantes.ZONA_STANDARD));
            empresa.crearViaje(empresa.getPedidos().get(empresa.getClientes().get("cliente1")),empresa.getChoferes().get("234455"),empresa.getVehiculos().get("123-456"));


            Ventana ventana = mock(Ventana.class);
            FalsoOptionPane op = new FalsoOptionPane();
            when(ventana.getCalificacion()).thenReturn(5);
            this.controlador.setVista(ventana);
            this.controlador.getVista().setOptionPane(op);
            this.controlador.calificarPagar();

        } catch (Exception e){
            fail("No deberia lanzarse la excepcion: "+ e.getMessage());
        }
    }

    @Test
    public void CalificarErroneoTest(){
        FalsoOptionPane op;
        Ventana ventana;

        ventana = mock(Ventana.class);
        op = new FalsoOptionPane();

        try{
            empresa.agregarCliente("cliente1","pass1","nombre1");
            Usuario usuario= this.empresa.login("usuario1","pass1");
            empresa.setUsuarioLogeado(usuario);

            when(ventana.getCalificacion()).thenReturn(5);
            this.controlador.setVista(ventana);
            this.controlador.getVista().setOptionPane(op);
            this.controlador.calificarPagar();
            assertEquals(Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE, op.getMensaje());

        }catch(Exception e){
             assertEquals(Mensajes.CLIENTE_SIN_VIAJE_PENDIENTE, op.getMensaje());
            //fail("No deberia lanzarse la excepcion: "+ e.getMessage());
        }
    }
}
/*try{
            Ventana ventana = mock(Ventana.class);
            when(ventana.getRegNombreReal()).thenReturn("nombreNuevo");
            when(ventana.getRegUsserName()).thenReturn("usuarioNuevo");
            when(ventana.getRegPassword()).thenReturn("passNuevo");
            when(ventana.getRegConfirmPassword()).thenReturn("passNuevo");
            this.controlador.setVista(ventana);

            this.controlador.registrar();//pass y confirm coinciden -> se invoca a agregarCliente(...)
            Usuario nuevoUsuario = empresa.getClientes().get("usuarioNuevo");
            assertNotNull(nuevoUsuario);

            assertEquals(ventana.getRegUsserName(),nuevoUsuario.getNombreUsuario());
            assertEquals(ventana.getRegPassword(),nuevoUsuario.getPass());

        } catch (Exception e){
            fail("No tendria que haber lanzado una excepcion: " + e.getMessage());
        }
 */
/*Se invoca al metodo pagarYFinalizarViaje(int calificacion) de la clase Empresa utilizando el parametro proporcionado por el atributo vista
int calificacion = this.vista.getCalificacion()
luego se actualiza la vista
Si la accion no se puede realizar entonces se delega en el atributo vista mostrar el mensaje correspondiente a la excepcion ClienteSinViajePendienteException lanzada */