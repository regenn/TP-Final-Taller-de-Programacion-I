package test.java.integracion;

import static org.junit.Assert.*;
import org.junit.*;

import static org.mockito.Mockito.mock;//hay que instalar algo?
import static org.mockito.Mockito.when;

import modeloDatos.*;
import excepciones.*;
import modeloNegocio.*;
import java.util.HashMap;

import controlador.*;
import util.Mensajes;
import test.GUI.FalsoOptionPane;
import vista.Ventana;//??


public class LoginTest {
    Empresa empresa;
    Controlador controlador;
    Usuario usuariologeado;

    @Before
    public void setUp() throws Exception{ //y el catch?
        empresa=Empresa.getInstance();
        empresa.setClientes(new HashMap<String,Cliente>());
        controlador= new Controlador();
        usuariologeado=new Cliente("usuario1","contrasenia1","nombre1");
    }

    @After
    public void tearDown() throws Exception{
     //necesitaria algo que me borre los usuarios
    }

    @Test 
    public void LoginClienteExitosoTest(){
        try{
            empresa.agregarCliente("usuario1","contrasenia1","nombre");
            usuariologeado= this.empresa.login("usuario1","contrasenia1");
            this.empresa.setUsuarioLogeado(usuariologeado);
        }
        catch(Exception e){

        }
        Ventana ventana= mock(Ventana.class);
        when(ventana.getUsserName()).thenReturn("usuario1");
        when(ventana.getPassword()).thenReturn("contrasenia1");
        this.controlador.setVista(ventana);
        this.controlador.login();
        assertEquals(usuariologeado,this.empresa.getUsuarioLogeado());
        //assertEquals("usuario1",ventana.getUssername());
        
    }

    @Test
    public void LoginUsuarioNoExisteTest(){
        FalsoOptionPane op= new FalsoOptionPane();
        try{
           empresa.agregarCliente("usuario1","contrasenia1","nombre");
           usuariologeado= this.empresa.login("usuario1","contrasenia1");
        }
        catch(Exception e){

        }
        Ventana ventana=mock(Ventana.class);
        when(ventana.getUsserName()).thenReturn("usuario2");
        when(ventana.getPassword()).thenReturn("contrasenia1");
        this.controlador.setVista(ventana);
        this.controlador.getVista().setOptionPane(op);
        this.controlador.login();
        assertEquals(Mensajes.USUARIO_DESCONOCIDO,op.getMensaje());
    }

    @Test
    public void LoginContraseniaIncorrectaTest(){
        FalsoOptionPane op= new FalsoOptionPane();
        try{
            empresa.agregarCliente("usuario1","contrasenia1","nombre");
            usuariologeado= this.empresa.login("usuario1","contrasenia1");
        }
        catch(Exception e){

        }
        Ventana ventana= mock(Ventana.class);
        when(ventana.getUsserName()).thenReturn("usuario1");
        when(ventana.getPassword()).thenReturn("contraseña2");
        this.controlador.setVista(ventana);
        this.controlador.getVista().setOptionPane(op);
        this.controlador.login();
        assertEquals(Mensajes.PASS_ERRONEO,op.getMensaje());
    }
    


    /*

    ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣤⣴⠶⡶⠾⠟⡞⠷⢻⠞⠷⠿⢶⢶⣦⣤⣄⣀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣤⣴⣦⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣴⠾⡛⢏⡹⣐⠢⣍⠒⣍⢚⡘⡔⣃⠚⡬⢡⢃⠲⡄⢎⠭⢛⠿⢶⣤⡀⠀⠀⢸⣿⡿⣧⡛⣽⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣯⡛⠿⢶⣶⣤⣤⣄⣀⣀⣀⣀⣀⣠⣴⠾⣛⠫⢍⢆⠣⢍⢢⡑⢆⠳⣠⢋⡔⠪⡔⢱⡈⡓⣌⢃⢎⡱⡘⣌⠲⢡⠚⡔⣊⠟⣷⣄⢸⣿⣿⣯⢷⠹⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠿⣦⠱⡐⢢⡙⢬⠩⣍⢋⠭⢋⡝⡌⢲⢡⠚⡌⢢⡙⢢⢅⡚⢌⡱⢂⠥⣊⠵⣈⠇⡴⢑⠢⢎⡂⢖⡡⢆⡙⠦⣙⠰⡡⠚⣄⠛⣿⣿⣿⣿⣟⣧⢻⡽⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⡿⢧⣃⡜⣂⢇⠢⣍⢊⠥⡒⢌⢣⢊⠵⣈⠇⡜⣡⠒⡜⢢⢅⠫⡔⣡⠒⢥⠚⣄⢋⡜⢢⢉⠖⡰⢡⠚⡔⣡⠣⣱⢉⢆⡹⢠⡙⢿⣿⣿⢾⣥⢻⣿⣷⡀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⢏⢾⣻⣿⣶⠮⣑⠢⢍⡒⡩⠜⢢⡉⠖⢬⡘⠴⡁⢞⠨⡅⢎⡒⢥⢢⢙⠢⡍⡔⢪⡐⡃⢎⡜⢡⢃⠣⡜⢄⠣⡜⢮⡒⢤⠣⢜⡐⢻⣿⣿⢾⡥⢻⣽⣿⢿⡿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⣿⣿⣊⠿⣋⡑⢦⠘⡤⢋⠆⡥⢃⡙⢢⠩⡜⢢⡜⢢⡙⢤⢓⡘⢆⡩⢒⡌⢎⡱⢸⢮⠣⡜⠡⢎⢌⡃⢎⡱⡘⡌⡓⣌⠒⡹⣦⢉⠖⢬⡑⢚⣿⣯⢿⡎⢷⣿⣯⡽⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠫⣙⡿⢏⡡⠣⢔⡘⠦⢩⣔⢃⡚⠔⣣⠘⡥⢃⡜⣱⢯⢃⡜⢂⠎⡜⢢⡑⢣⡘⠆⡥⢻⡅⡳⡜⣑⢊⠦⣉⠖⡰⠱⡘⠔⣬⠾⢡⡙⣮⡜⢢⡘⢥⣿⣿⡿⣽⢎⣟⣷⡻⣷⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢨⣟⡴⢋⠖⡌⠦⡙⢆⠹⠌⣥⠳⡌⡜⡡⢆⠹⣠⠣⢜⡏⣸⠆⡜⣡⠚⡌⢥⢊⠥⣘⠱⣂⠧⡇⡘⠹⣆⠜⢢⠱⣘⠡⣓⡵⢫⠱⡘⣂⠖⡹⣿⣆⠜⣸⣯⣟⣿⢯⣷⢸⣟⣟⣧⣟⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡾⢏⠢⡍⢎⢬⣡⣷⠏⡜⡌⡟⡰⢡⠲⣵⣈⠣⡔⡱⣾⠡⠜⡇⠦⡑⢎⠜⣢⢉⠖⡡⢓⡰⢊⡗⠠⠀⠙⣮⠅⢳⣤⠛⣡⠚⡤⢣⠱⢌⠒⣹⡽⣿⣞⡰⣷⢯⣻⣿⣷⢺⣿⣿⢾⣭⢿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠟⡍⡌⢣⣼⣾⣏⣻⢇⡚⡰⣽⠱⣈⢇⠲⡄⢭⠳⣴⣹⠇⠂⠈⢷⢡⡙⡌⢎⡔⢃⢎⡱⢡⠒⡥⣏⠄⠐⠀⣨⢿⡃⢦⡙⢄⠳⣨⠑⡎⡜⡘⠴⣿⡽⣾⣻⣽⣻⢿⣿⣿⢸⣿⣯⣟⡾⣯⢿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⢏⠲⣤⢗⡋⣾⣿⣷⠇⢎⠴⣹⡏⠴⣈⢆⠳⣘⢢⠃⣾⡏⠳⢤⡀⠸⡆⣱⠘⡆⣌⠣⢎⡰⢃⠭⡐⣿⠀⡴⠊⠁⠀⢻⣄⠚⣌⠱⣂⠭⡰⢌⠥⣋⣷⣻⣿⡷⣽⢯⣿⣿⡟⣼⣿⡷⣯⣻⢽⣻⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⢏⣼⠿⣿⢢⢱⡿⣿⡏⠜⣌⣾⣻⣇⢣⠒⣌⠲⢡⠢⣽⣿⠁⠀⠀⠈⠑⢻⡠⡙⠴⣈⠖⣡⢒⠩⡒⠥⣿⡀⠀⠀⠀⠀⠀⢹⡜⣄⠣⢌⡒⣑⢊⠖⡡⠟⠷⢿⣿⠹⢋⣿⣿⡟⣼⣿⣻⡵⣯⣟⣳⢿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢼⡿⠛⠁⢰⣏⠖⣼⣻⣿⠐⡆⣮⣅⣻⠳⠌⠲⣌⠱⡃⢼⣳⡏⠀⠀⠀⠀⠀⠀⢧⡙⢢⠱⡘⢤⠊⠵⠈⠓⠟⡇⠀⠀⠀⠀⠀⠀⠹⣤⠡⠤⡔⠤⣂⠒⡴⣻⢯⣿⣿⣿⣟⣿⣿⡏⣾⣿⣳⣟⣧⠿⣭⢿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⢃⢼⣳⢯⡇⠣⣜⡿⣞⣽⢿⡘⠴⣠⠡⣘⣚⣹⠃⠀⠀⠀⠀⠀⠀⠈⢇⢡⢂⡌⢆⠲⡌⣍⠣⣿⣧⠀⠀⠀⠀⠀⠀⠀⣸⣷⡿⢊⡱⢌⡱⢸⣯⣟⣾⣿⣿⣞⣿⣿⡇⣿⣟⡷⣾⣹⢿⣭⣟⣾⣿⡄⠀⣀⣠⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡏⡌⣾⣽⣻⢅⢣⡿⣝⣻⢾⣻⡝⠢⣅⠲⢸⣟⡟⠀⠀⠀⠀⠀⠀⠀⠀⠈⢧⢊⡜⢬⡑⡒⡌⡒⣽⣷⠀⠀⠀⠀⠀⣠⡾⠛⠙⣦⡃⢆⠣⠜⣸⢷⣛⣾⣿⣿⣯⣿⣿⡱⣿⡿⣽⣳⣯⣿⢾⣿⠉⣽⡿⠞⠋⣩⣿⣤⣴⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⡰⣸⣟⡼⣿⢈⡾⣽⢯⣟⣿⣳⢿⡡⢂⢣⡿⣾⠛⠻⢶⣤⡀⠀⠀⠀⠀⠀⠈⢧⡘⢤⣳⢉⡔⢣⢜⣿⡄⠀⣠⣴⠟⠋⠀⠀⠀⠘⣷⡌⢣⠱⣸⢯⣟⣾⣿⣯⢿⣿⣿⣾⣿⣿⣟⣯⣟⣾⣻⣾⡑⠦⣗⠂⢠⡇⣂⣠⣤⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡾⠛⠻⢿⣄⠈⠙⢯⣿⣏⢦⡿⣽⣿⣻⣼⣯⣟⡧⠡⣏⡿⣽⠀⠀⠀⠉⠙⠿⢶⣤⣀⠀⠀⠈⠳⣆⠹⣧⡌⢣⢸⣯⣷⣾⣋⡁⠀⠀⠀⠀⠀⠀⠹⣾⣁⠣⣟⣟⢾⣻⢧⣹⣿⣽⣿⣻⡟⣷⣻⢾⡽⣞⣷⣻⡇⡲⢹⡜⡨⡐⡏⣁⣉⣿⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣨⡿⠷⢒⡦⢌⢳⡀⢠⡾⣿⣸⢿⣽⣿⣷⢯⣷⢾⣽⢡⡿⣽⡗⠀⠀⠀⠀⠀⠀⢀⣬⡿⠇⠀⠀⠀⠘⢧⣻⡽⣆⢚⣷⣳⠈⠉⠛⠻⢶⣦⣄⣀⠄⠀⢹⣷⡂⣿⣞⣯⣿⣧⣾⢿⡽⣞⣷⣻⢷⣯⢿⣽⣻⣞⡷⣧⢑⠺⡇⡔⢹⣇⣉⣛⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣶⡴⠒⢯⡂⠦⡙⣼⠡⣿⣼⢿⣿⣿⣻⢯⣿⣻⢞⣯⡟⣷⡇⠀⠀⠀⢀⣤⡾⠟⠉⠀⠀⠀⠀⠀⠀⠀⠈⠻⢿⣧⣻⣽⠂⠀⠀⢀⠀⠠⢉⠛⠻⠷⢹⢻⣷⣻⣞⡷⣿⣿⣟⣯⢿⡽⣾⣽⣻⣞⡿⣞⣷⢯⣟⣿⢈⠖⢻⣤⡷⢦⣽⡏⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⢻⠤⡴⠚⣑⠢⢱⡇⢣⡿⣯⣿⣟⡾⣽⣿⢾⣿⣞⣳⢿⣽⠃⠀⣴⡾⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠳⣿⣧⣶⣬⡐⢈⠡⠀⢌⡁⠂⣽⢸⣷⣻⢾⣽⣿⡿⣾⡽⣯⣟⡷⣯⢷⣯⢿⡽⣞⡿⣾⣽⢊⠬⣙⣿⣳⠎⢿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⢟⡱⠢⣵⣟⣾⢿⣿⡿⢀⢳⣟⡿⣟⣯⣟⣷⣻⣿⡿⣯⢿⡽⣾⡅⠀⠄⡠⢐⠨⠐⡀⠂⠀⠀⠀⢰⣶⢶⡶⣶⡶⡾⠿⠟⠯⠷⣛⣞⡷⣤⠘⠀⠆⠠⠁⢼⢺⡽⣷⢯⣿⡿⣽⣳⣟⡷⣯⢿⣽⣻⣞⣯⢿⣽⣻⢷⣻⡏⡔⢢⢿⣝⣯⡘⢿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⡾⢏⠲⣐⣿⣳⢾⣽⣫⢾⣇⠃⣾⡿⣽⣻⢾⡽⣞⣷⣻⣽⣻⣯⢿⣽⡇⠐⢠⠀⢂⠃⡐⠀⠁⠀⠀⠀⣿⢏⠯⡙⡔⢢⡕⡩⢎⡱⢃⡖⣌⠳⡹⡆⠀⠈⠐⠀⣹⢾⣻⣿⣻⣿⢿⣽⣳⢯⡿⣽⣻⣞⣷⣻⣞⡿⣾⡽⣯⢷⣟⢐⡃⢾⣻⢾⣕⢊⢿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⠟⡰⢊⢶⣻⣞⣭⣟⡾⣽⣻⢄⢋⣿⣽⣳⣯⢿⣽⣻⣞⡷⣯⢷⣻⣿⣻⣿⠀⠂⠈⠠⠐⠀⠁⠀⠀⠀⠀⣯⠘⢦⡙⣌⠣⡜⡱⢊⡔⢣⠜⡤⢣⠱⣹⡀⠀⣀⣴⣏⣯⣿⣿⣿⣯⣟⡾⣽⢯⣟⣷⣻⣞⣷⣻⢾⣽⣳⣿⣽⣯⣿⣤⣾⣼⢯⣟⡾⡌⢎⣿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⡿⣉⠖⡡⣯⡟⣷⣞⣧⢿⣹⢷⣿⢈⢲⣿⣳⣟⣾⣻⣞⡷⣯⢿⡽⣯⣟⣧⢻⣽⣷⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡘⢦⠱⣌⠳⣌⠱⢣⠜⣣⠚⡔⣡⣣⡴⢗⠛⣿⠯⣯⣿⣿⣿⢿⣾⣹⣽⢯⣟⣾⣧⣿⣾⡷⠿⠿⠛⢿⣯⢷⣻⣞⣷⣛⣾⢻⡾⣽⣻⡐⡌⢿⡄⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡾⣋⠕⣢⠚⣼⢷⣻⣳⢾⣽⣫⢿⡽⣾⡨⢼⣷⣻⣞⣷⣻⢾⡽⣯⢿⣽⣳⣟⣾⣏⣿⣿⣿⣿⣷⣶⣦⣤⣄⣀⣀⣀⡘⣇⣎⣑⣆⣳⣈⣣⡷⢬⠖⠛⢩⡏⢡⠒⠈⠈⢹⢸⢟⣿⣿⣯⣿⣾⣽⠾⠿⠛⠙⠉⠁⠀⠀⠀⠀⠀⠈⣿⣯⢷⣻⢮⣟⡾⢯⣽⢶⣻⣇⢌⢋⣿⡄⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⢟⠣⡜⠒⣤⣻⢯⣟⣳⢯⣟⡶⢯⣟⡾⣽⣻⣟⣿⡿⠛⠛⠟⠿⠿⠿⠿⣾⣷⣯⣷⣿⣾⣿⣯⣟⣿⣻⡿⣿⡿⣟⡟⠨⠉⠭⠉⣉⣼⠁⠹⣤⣰⠋⠀⢄⡞⡧⠀⢀⣠⣴⠾⣷⠾⠟⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣯⢯⣟⡾⣽⣻⣞⣯⢷⣻⡆⢊⡜⣿⡄⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⠋⢆⠳⣈⠕⣾⡽⣛⡾⣽⣛⣮⣟⣿⣺⣽⣳⣟⣼⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠹⠿⠉⠉⠉⠛⠛⠻⠶⣾⠟⠛⠛⠷⢶⡞⠹⣄⣴⣿⣳⣦⡄⡼⠀⢳⡿⠟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣾⣛⡾⣽⡳⣷⢯⣞⣯⢷⣻⠰⢌⢚⣷⡀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⠟⡤⢋⡜⡰⢁⣾⢯⣷⣻⣽⣳⣟⡾⣵⣞⡷⣳⣽⣾⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠁⠀⠁⡧⢁⠣⣼⠈⠁⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣯⡿⣽⣳⢟⣳⣟⡾⣭⢿⣹⡇⢎⡒⠼⣷⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⣠⡿⢣⢚⡐⢣⢒⡱⣺⣭⣟⣶⣻⢶⣻⢾⡽⣳⢾⡽⢧⣷⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⡏⠀⠀⣠⠿⠿⣿⡇⠀⠀⠀⠐⣯⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣳⢯⣟⣳⢯⡽⣯⣻⢷⣻⡔⢌⠲⡹⣧⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⣼⠟⣌⢃⠦⣉⢆⢃⢶⣻⣞⡽⣶⢯⡿⣭⢷⣻⡽⣻⣞⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⡟⠀⠀⡴⣑⢊⠱⡘⡇⠀⠀⠀⠀⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣽⡳⢯⣯⠿⣽⣳⢯⣟⣧⡗⡌⢣⡑⢿⣇⠀⠀⠀
⠀⠀⠀⠀⠀⢀⣼⢏⡚⢤⡉⠖⡬⢌⠦⣟⣷⣫⣟⡷⣯⣻⡽⢯⣷⣛⣷⣻⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠀⠀⠀⣀⣤⣴⠿⠃⢀⢠⠞⡱⠌⣎⠱⡘⣇⠀⠀⠀⠀⠘⡷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣾⡽⣻⣼⣻⠷⣯⢷⣞⣳⢿⠨⡅⡜⢌⢿⡄⠀⠀
⠀⠀⠀⠀⢀⣾⠏⢲⢈⠖⡌⡓⡜⢰⢺⡽⣞⣷⣛⣾⣳⢯⣽⣻⡼⣏⣾⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣶⣿⣿⣿⣶⣾⠋⠁⠀⡀⠠⠀⢻⡌⡱⢊⡔⣉⢲⠝⠀⠀⠀⢀⠈⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⣛⡷⣽⣳⢿⡽⣛⡾⣽⢯⡗⢰⠘⣌⠚⣷⠀⠀
⠀⠀⠀⠀⣾⠏⡸⢡⢮⡟⡰⠱⣈⢇⡿⣽⣻⣼⣻⡼⢯⣟⣾⣳⣽⣻⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⡆⠠⠁⠠⠐⢀⠈⢷⢡⠃⣼⠜⠃⠀⠠⠐⠈⡀⠄⠘⢷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣭⡟⣷⢯⣯⠿⣽⣻⡽⣾⣽⠀⢏⡰⣉⢻⣇⠀
⠀⠀⠀⣼⡏⡒⠥⣳⣿⠱⣁⠳⠌⣼⣻⣳⡽⣶⢯⣟⡿⣺⣵⣻⣞⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣿⣷⣿⣿⡇⠀⠄⢁⠐⠠⠀⠘⠳⠋⠂⠀⠄⡈⠐⡀⢁⠠⠐⠀⠈⣿⣄⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣷⣳⢿⡽⣞⣷⣻⠷⣏⣷⣻⢾⡘⢢⠱⡌⣌⣿⡀
⠀⠀⢸⡟⡰⢉⣶⣿⣏⠳⢌⠲⡉⣾⣳⢯⣽⣳⣟⡾⣽⣳⢯⡷⣾⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣽⡷⠈⢀⠂⢀⠂⣁⠐⠠⠀⠄⡁⠀⠄⠂⡀⠂⠠⠐⣠⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣷⢫⣟⡾⣽⢾⣹⢿⡽⡾⣝⡿⡄⢣⠓⡌⠴⣸⣇
⠀⢠⣿⡙⡰⣣⡟⣸⣇⠣⢎⡑⢆⡿⣽⣛⣾⢳⣯⡽⡾⣽⣻⢞⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢻⣿⣿⢾⣿⣿⣠⣤⣾⣾⣿⣿⣷⣀⠐⠀⠠⠁⠄⢂⠀⢁⣰⣾⣿⢿⣿⣿⣿⣿⢯⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⡞⣟⡾⣽⣏⡿⣽⢾⣽⣻⡽⢯⡇⠥⢋⡔⢣⠜⣿
⠀⣾⢇⠲⣱⡿⠀⣿⢄⡋⢦⠱⢸⣻⢷⣻⢞⣟⡾⣽⣻⢷⣫⢿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣶⡟⢋⠹⣦⣿⡞⡻⣿⡽⣞⡷⣯⣟⢿⣦⣈⠠⠈⠀⠄⣠⣿⣟⣿⣿⣯⣟⢿⣳⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⡳⣿⣽⣫⢷⡾⣽⣛⡾⣧⢷⣻⢯⡗⢨⠣⡜⡰⢊⢿
⢠⣿⢊⣱⡿⠁⠀⣿⠢⢍⠆⣣⢹⣯⢯⣯⣟⡾⣝⡷⣿⣯⣟⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣤⣴⣾⣿⡿⣟⡿⣽⡄⠣⣘⠫⣙⣦⡙⠿⡽⡽⢧⡿⢿⣝⣿⣶⣬⣴⣿⡟⣋⣭⣽⣟⣷⠩⣶⡟⠙⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⡿⣽⡷⣾⢭⣿⣹⠷⣯⣟⡽⣯⢯⣟⢮⢡⢫⡗⡡⢃⢾
⣸⡇⢎⣾⠃⠀⠀⣿⠱⣊⠜⣄⢺⣽⣳⢯⡾⣽⣫⣽⣿⣷⢯⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣠⣴⣶⣾⡿⣿⣻⢯⣟⡾⣽⣻⣽⣳⢿⣦⡴⠟⠋⠙⠷⡷⢶⠾⢶⣶⡿⣟⣾⣳⡯⠷⡺⣿⣏⠉⠐⢿⠷⠿⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⢯⣿⡿⣭⢷⣏⣯⣟⣷⣫⣟⢷⣻⡞⡇⢎⣼⣧⠣⢩⢼
⣽⢋⣼⡏⠀⠀⠀⢽⡗⡨⠎⣄⢫⡷⣯⣻⣽⣳⡽⣾⣿⣿⢿⣿⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣻⣽⣳⣟⡷⣯⣟⡾⣽⢯⣷⣯⡿⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠘⠻⢶⣭⣍⣡⠶⡛⢍⣹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣞⣿⣿⡿⣽⣻⢞⣳⢯⣞⣷⣫⣟⡷⣽⢃⠆⣿⣧⠝⢢⢹
⣿⢣⣿⠁⠀⠀⠀⠸⣷⢡⠓⢬⢸⣟⣳⡽⣞⣷⣻⣽⡇⢻⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⢾⣿⣿⣿⣷⣻⣞⡷⣯⣟⡷⣯⣿⣽⠿⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡗⡈⠦⠉⢂⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣯⣿⢃⣿⣟⣳⡽⣯⣟⣻⢾⣵⣻⠾⣽⡽⢊⣼⡟⣿⠍⣆⢺
⣻⣷⡏⠀⠀⠀⠀⠈⣿⣆⡙⢢⠅⣿⣳⠿⣽⢶⣻⣾⡇⠘⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣿⣿⣷⣯⣟⣷⣿⡿⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣷⣤⣤⣤⣴⣾⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣿⣟⣿⠃⢸⣿⡽⣎⡿⣵⢯⣯⢷⣛⣾⡻⣗⡟⣰⡟⠀⣿⢊⡔⣿
⢹⣿⡇⠀⠀⠀⠀⠀⠸⣷⡈⢇⡘⣳⢯⡿⣽⣞⣳⣿⡇⠀⢹⣿⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⠻⠛⠟⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣯⢷⣻⢯⣟⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⣿⠇⠀⢸⣿⡽⣯⡽⢯⣟⡾⣏⡿⣞⡽⣯⣷⠟⠀⠀⣿⠣⣰⣿
⠀⠻⠃⠀⠀⠀⠀⠀⠀⠻⣯⠆⡱⢸⣯⢷⣻⡼⢯⣿⡇⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣯⢿⣽⣻⢾⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⠏⠀⠀⣾⣿⣽⡳⣟⣻⠾⣽⡽⣽⣳⣟⣿⠋⠀⠀⠀⣿⢓⣸⡏
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣯⠴⡙⣾⢧⡿⣽⢻⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣺⣿⣽⣻⢾⣽⣻⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⣿⡏⠀⠀⠀⣿⡷⣯⡽⣏⣷⣻⢷⣻⢷⣽⡟⠁⠀⠀⠀⢸⣿⢰⣿⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣦⢹⣷⣻⣭⣟⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⢾⣽⣻⢾⣽⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⡟⠀⠀⠀⠀⣿⣟⣷⣻⡽⣶⢯⣾⣽⣿⠋⠀⠀⠀⠀⠀⢸⣏⣼⠏⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣞⡷⣽⣺⣽⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⣯⣟⡾⣽⣻⢾⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠁⠀⠀⠀⢠⣿⣟⡶⣯⡽⢯⣻⣾⠟⠁⠀⠀⠀⠀⠀⠀⣼⣷⡟⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠿⣷⣯⢾⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⢧⡿⣽⣳⢯⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⠇⠀⠀⠀⠀⢸⣿⢯⣟⡷⣻⣯⡿⠁⠀⠀⠀⠀⠀⠀⠀⢠⣿⡿⠁⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣯⣻⣽⣳⢯⡿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠋⠀⠀⠀⠀⠀⢸⣿⣟⡾⣽⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠁⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣷⣿⣽⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣟⣾⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠟⠁⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⣿⣿⣿⣿⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
    */













    /*
    ⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⡄⡽⠀⢰⣿⡟⠉⢀⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠡⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣷⠇⢠⠋⢹⠃⣰⠏⠀⠀⠀⢀⠄⢀⡤⠀⠀⠀⠠⢈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⠠⠈⢀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠂⢬⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣟⡟⣠⠃⢠⣏⡼⠁⠀⠀⠀⡠⠊⢠⠏⠀⢀⠐⠈⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⡇⠀⠐⡄⠂⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⢀⠀⣟⡲⢀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣟⣾⣷⣿⣇⣻⠏⠀⠀⠀⣠⠞⢀⡴⠃⠀⠄⠂⢄⠁⠀⡀⠌⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠂⡌⢱⡈⠄⠀⠀⠀⠀⠀⠀⡀⠁⠠⠀⢘⣯⣇⠧⡐⠈⢀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠈⠀⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⡐⡈⠄⢢⠐⠠⡐⣀⢂⡐⣀⠂⡄⢸⣿⣿⣻⢼⣿⣹⡟⠁⠀⠀⠀⡴⢁⡴⠋⠀⢠⠘⡠⡉⠄⡀⢂⠀⢰⠂⠀⠀⠀⠀⠀⠀⠀⠠⠈⢸⡇⠀⠘⣄⠣⠆⠄⠀⠀⠀⠀⠀⢀⠀⠐⠀⡐⢨⣿⢮⡱⢌⡂⠄⡀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⡀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⢆⡑⡊⢤⠃⡅⠒⡅⢢⠐⡄⢊⠔⣹⣿⢷⣫⡟⣷⠋⠀⠀⠀⢀⡞⣁⠋⡀⡐⢌⠢⣡⠃⡐⠠⡐⠠⢪⠏⠀⠀⠀⠀⠀⠀⠀⠠⠐⢀⢺⡅⠀⠒⣌⢣⡝⡀⠀⠀⠀⠀⠀⡀⠠⠈⢀⠠⠀⣿⣟⣆⠣⠜⡐⠠⠀⠐⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⢢⠐⡡⢢⠑⡈⠱⢈⠎⠱⡈⢆⢺⣿⡿⣏⡷⡽⠃⠀⠀⠀⢠⡟⡄⡁⢆⠰⣡⢊⡕⠠⢂⠔⡡⢌⢱⡏⠀⠀⠀⠀⠀⠀⠀⠈⠀⢄⢪⣽⠂⠀⠒⣌⡳⢆⡅⠀⠀⠀⠀⠀⠀⠄⠐⠀⠠⠀⢿⣿⣎⡱⠊⡌⠱⣀⠁⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠀⠠⠐⠀⠀⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⢂⠱⢀⠣⢀⠁⡃⠌⡐⢡⠘⢤⣿⣻⢿⣱⡟⠁⠀⠀⠀⣰⢏⡔⢠⡑⢪⡑⢦⠃⡔⢡⢊⠰⡐⢌⡾⠀⠀⠀⠂⠀⠀⠀⠀⡁⠤⣌⠷⣾⠁⠠⠑⣬⣛⡎⡄⠀⢀⠀⠀⠈⢀⠐⠈⡀⠁⠄⣺⣿⣷⣡⠃⡌⠱⣀⠣⢀⠂⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠀⠀⠀⠀⠀⠀⠠⠀⠀⠀⠀⠄⠂⠀⠀⠐⠀⠀⠐⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⢂⠐⠂⡐⠂⠁⠄⠂⠐⠠⡘⣼⣿⣯⢯⠟⠀⠀⠀⠀⣼⢣⡞⡨⢆⡙⢦⡙⠦⡉⢆⠡⢊⠔⡡⣾⠁⠀⠀⠁⠀⠀⡀⠀⢁⠀⣴⢫⡟⣿⠀⠠⡙⣴⢫⡜⡔⠀⠀⠀⠀⠠⠀⢀⠂⢀⠡⠐⣼⣿⡷⣭⢓⡈⡁⢆⡑⠢⢌⠐⠀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠀⠀⠠⠀⠀⠐⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠌⠀⠐⠀⠀⠀⠀⠁⡐⢰⣿⣿⣽⠋⠁⠀⢀⠐⣼⣣⢏⠲⣑⢎⡝⢦⡉⢆⠱⡈⢆⢡⢊⣱⠇⠀⠀⠁⠀⠂⠁⠀⠐⠀⡼⣎⠷⣽⣿⠀⠠⢓⡭⣷⡹⢄⠀⠂⠀⠀⠀⠀⠂⢀⠂⢀⢣⣾⠿⣿⡽⣎⡔⠡⠠⠌⡱⢈⠆⡡⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠀⠀⠀⠀⠀⠀⠀⡀⠄⠂⠀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⡐⣿⡿⣾⠃⠀⢀⠠⢁⣾⢳⣋⢬⠳⣩⠞⡼⢡⡘⢌⠢⡑⡈⢆⢢⡏⠀⡀⠁⠀⠂⢀⠂⠁⢠⣙⢾⣩⣟⣿⣿⠄⢢⡙⣞⣷⡹⢆⠠⠀⢀⠀⠀⠀⠀⠄⢀⠢⢜⣿⠠⢻⣿⡷⣎⠱⡀⠆⢡⠊⠴⡁⢆⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠐⠀⠈⠀⠄⠀⠀⠀⠐⠀⡀⠂⠈⢀⠀⠈⢀⠀⠐⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠠⢼⣿⣿⠃⠀⠀⡀⣠⣿⣳⢏⠺⣌⠳⣥⠻⡜⣡⠰⢈⠆⡑⠌⡄⣿⠀⣰⠁⠀⠂⠠⠀⠠⠘⣤⢻⡬⢷⣿⢃⣿⡃⠤⣙⢮⣷⡻⢬⠄⠀⠠⠀⠠⠐⠈⠀⠄⡘⢼⡇⡃⠆⠻⣿⣏⣳⡐⠌⡠⢉⠆⡱⢌⠢⠀⠄⠀⠀⠀⠀⠀⠀⠀⠄⠀⡀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠠⠀⠐⠀⡀⠄⢁⠠⠀⠂⠀⠁⠀⠂⠀⠄⠁⡀⠀⠂⠀⠀⠄⠀
⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⡿⠁⠀⠀⠄⣰⣿⣱⠫⣜⠳⣬⡛⢶⡙⡰⢂⠜⢠⠊⡔⢡⢸⠇⢰⢃⠠⠈⢀⠐⠈⠤⣙⢮⡳⣝⣿⡃⠇⣿⡅⢒⠽⣎⣷⣻⣃⠎⠐⠀⡐⠀⠄⢈⠀⢂⡘⢾⡇⡘⢀⠁⠺⣿⣧⡙⡤⢑⠠⡘⡐⢎⢡⠃⠄⠂⢀⠀⠠⠐⠀⠠⠀⠂⠀⠄⠁⡀⠂⠀⠄⠁⡀⠂⠀⢁⠠⠐⠀⢂⠀⠢⢀⢂⠡⠐⠈⢀⠠⠁⠈⠀⠄⠀⠐⠀⠈⠀⡀⠄
⠀⠀⠀⠀⠀⠀⠀⠀⣸⡿⠁⠀⠄⠁⣸⣿⢳⣣⢛⡴⣛⡴⣛⠦⢡⠑⡬⡘⣀⠣⠘⡄⡿⢠⠏⢄⠂⡁⢂⠐⡈⢶⣭⢳⢯⣿⠓⡐⠌⣾⡇⣌⢻⡼⣳⢧⡏⢞⠠⠀⠀⠄⠂⢀⠐⠠⣘⣻⣧⠂⠄⠈⠄⢹⣿⣷⡜⣠⠃⡔⢡⠊⢆⡍⡒⠠⠀⢀⠀⠀⠐⠀⡐⠀⠁⢂⠡⡀⠄⡁⢀⠂⠀⠄⠁⠠⠀⡐⠈⢀⠠⢡⠩⡌⢆⡱⢈⠀⠄⠀⠌⠀⡐⠈⠀⠀⠂⠁⠀⠀
⠀⠀⠀⠀⠀⠀⠀⢠⣿⠁⠀⠄⠀⣲⣿⢳⣏⣖⢫⠖⣧⢳⠡⠎⡄⠣⢄⠱⢢⢁⠣⣼⠃⡾⢁⠎⠤⢑⢂⡘⢬⠷⣎⣯⡿⡍⠐⢀⡼⢻⡇⡜⣣⢟⣽⣏⡾⣡⠎⠀⠁⠠⠐⠀⠂⢁⠦⣿⡙⢂⠀⠈⠐⠀⠸⣿⣿⣄⠣⠌⡂⢍⢂⠖⡉⢆⠡⠀⠠⠈⠀⠐⠀⠠⠁⢌⢢⡑⠬⣐⠢⢌⠒⡈⠄⠡⠀⠄⡈⠄⡒⢠⠓⡜⣬⠢⡅⡌⠂⠄⠐⢀⠠⠀⠁⠠⠀⠁⡐⠀
⠀⠀⠀⠀⠀⠀⢠⣻⠃⠀⠠⠀⢢⢿⣿⣛⢶⢪⣏⠿⣜⠣⣑⢊⠔⡡⢊⠔⡡⢌⠒⡯⣰⢃⠎⡜⢢⠃⡖⢬⣻⣝⣳⡿⠁⠀⠁⡞⠁⢸⡗⣸⢱⣏⣿⠾⣵⢣⢎⠠⠁⢀⠐⠈⡀⢌⡺⣿⠁⠎⢲⡀⠀⠀⠀⠹⣿⣿⣦⢃⠜⡠⢊⠴⣉⠬⡑⡌⡀⠐⠈⢀⠈⢀⠐⢠⢢⡙⢦⡑⢎⢆⡣⢜⡨⠑⡌⢢⠐⡡⢘⢢⡙⢼⡰⢳⡜⣤⢉⡒⠀⠠⠀⠠⠁⢀⠀⠂⠠⠀
⠀⠀⠀⠀⠀⠀⣾⠃⠀⠠⠀⢠⣿⢯⣟⡼⢎⡷⣎⠿⢤⡓⡌⢆⠚⡄⢣⠘⡰⢈⢼⣇⢏⠂⡧⡘⢤⢋⡜⣷⣻⣼⡟⠁⠀⠁⠀⠀⠀⠘⣇⢲⡹⣎⣿⢿⣭⡓⣎⠄⡐⠀⡀⠂⠀⢬⣱⣿⠀⠂⠀⠳⡀⠀⠀⠀⠈⢿⣿⣷⡨⡐⢡⠒⡌⠲⡑⠤⢃⡐⠀⡀⠀⠂⡈⠔⢢⡙⢦⡙⢎⡖⣙⠦⢣⡝⡰⢃⠎⡑⢎⠦⣙⠦⣝⢧⡻⣴⢣⡜⠤⠁⡀⠁⠠⠀⠠⠀⠡⠀
⠀⠀⠀⠀⠀⢠⠏⠀⣀⠒⡴⣿⣿⡟⣧⢻⡝⣾⣙⢮⢳⡘⡜⣌⠳⣌⢣⡘⡔⣉⣾⠘⡄⡋⠦⢱⡈⠶⣸⣏⢷⣿⣴⣼⣤⣦⣄⣀⡀⠀⣧⢎⡵⣏⣿⣻⠶⣽⡘⢆⠀⡐⠀⠠⠁⢢⡳⣿⠀⠀⠀⠀⠱⡄⠀⠀⠀⠀⠹⣿⣷⣑⠢⠡⢌⠡⢍⠒⡡⢆⠡⠀⠄⠁⡐⡈⢆⡙⢦⡘⠥⡎⢥⣋⢧⡚⢵⡩⢎⡱⢪⡜⣥⠻⣬⢳⡝⣮⡗⡾⣡⠃⠀⠄⠁⠐⠀⡁⠂⠄
⠀⠀⠀⠀⢠⠟⠀⠀⢦⡙⣼⡿⣿⡹⣎⢷⣛⡶⣹⢎⢧⢳⡱⢎⡱⢆⠧⡜⣰⢱⡇⡋⢔⠡⢎⡡⢜⣹⢳⢮⡿⠆⠀⢀⠄⠈⠉⠙⠻⢶⣿⢸⢲⡭⣿⣽⣛⡶⣙⠆⢂⠀⠄⠁⢈⠶⣹⣿⠀⠀⠀⠀⠀⠘⢄⠀⠀⠀⠀⠨⣿⣷⣭⠒⡌⠰⢈⠜⡰⢈⠎⡡⠀⠂⠐⠈⠤⣙⢢⡙⠲⣍⠲⡱⢆⡛⢦⡙⢶⡙⢶⡘⢶⡹⢖⣫⢞⡵⣫⣗⢧⣋⡄⠐⠈⢀⠐⡀⠂⠀
⠀⠀⠀⠀⡞⠀⠠⢍⢦⣹⣿⣻⣯⠷⣽⡞⣽⢺⡵⣫⢞⣥⢛⣬⠳⣭⢲⡙⣤⣻⠰⡑⢌⠎⡔⡱⢪⢧⣫⡿⠄⠀⣀⠊⠀⠀⠀⠀⠀⠀⢻⣇⢻⡜⣿⡷⣏⢾⡍⡞⠠⠀⠠⠈⠐⣎⢳⡯⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣷⣘⠰⠈⠤⠑⡌⢒⠡⡑⠄⠁⡈⠔⡠⢇⡚⠵⣈⠳⣍⢎⡝⢦⣙⠶⣙⢮⡙⣧⠹⡮⢵⢎⣳⢳⡞⣯⢶⡘⡄⠐⠀⡀⠔⠀⡁
⠀⠀⠀⡾⠁⡨⣑⢎⡞⣽⣿⣟⣮⢟⣧⣟⢧⡻⣜⢧⣛⢦⣛⠶⣛⡴⣣⡝⢦⣿⠠⡑⢊⠜⡰⣱⢏⣮⡟⠀⠀⡴⠋⠀⠄⠀⠀⠀⠀⠀⢸⢯⠜⡽⣿⣿⡽⣞⡹⢬⡁⠂⠁⠀⠌⣜⣣⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⣿⣷⢣⠍⡠⠑⡈⢆⠱⡘⠤⢃⠀⠄⠱⢌⢎⡱⢅⠳⣌⡚⡬⢓⡬⢳⠩⡖⢭⢲⡹⣱⢋⡮⢇⡟⣼⣛⣮⢷⣘⠀⠠⠀⢘⡀⠀
⠀⠀⣸⠁⠰⡱⣌⡾⣹⣿⣿⡽⢮⣟⠾⣜⢯⡳⡝⣮⢝⣎⢧⡻⣥⢻⡱⣎⢧⡏⢆⢡⢉⠲⣡⠟⣼⡞⠀⠀⠘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡏⡞⣽⣻⣿⣿⢵⣫⠳⡄⠐⠈⠀⠰⣘⠶⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣯⠞⡤⠡⠐⡈⠆⠱⡈⠆⠤⠈⡐⢌⠦⡱⣊⠕⢦⠱⣙⠼⣘⠥⡛⡜⣬⢃⠶⣡⢏⡼⣙⢎⣧⢻⣼⢻⣜⡣⢀⠐⠀⡆⠀
⠀⣰⠃⡔⣣⢳⢬⣷⣿⣿⢷⣻⣟⢾⡹⣎⢷⡹⣝⠶⣫⡜⣮⢳⢭⡳⡝⣜⣺⡇⢌⠢⢌⠳⣼⢻⣼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⣿⠸⣥⢻⡇⣿⢧⣏⠷⣘⠀⢀⠈⢠⢙⡞⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢀⢀⣤⣿⣿⣼⠡⡅⡐⠈⠥⡘⢌⠒⡡⠀⠎⡰⢥⢃⠞⣌⢣⡍⢲⢍⡚⠵⣘⠦⣙⡚⡴⢎⠶⣙⠮⣖⢫⡞⣟⡾⣵⠂⠀⡀⠥⠀
⢰⠇⡸⢼⡱⣯⣿⣻⣿⣯⣟⣳⡾⣿⢱⡏⢾⡱⣏⢾⡱⣞⡱⣏⢾⣱⡛⣬⣿⠐⢌⢢⢭⢾⣏⣿⢃⣤⣴⣶⣶⣤⣤⣀⡀⠀⠀⠀⢀⡀⠀⢸⡵⣮⢻⡇⢸⡷⣎⢟⡬⢀⠀⠀⢠⠓⣼⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣨⠿⡏⠁⠀⠘⢿⣷⡱⣌⢂⠑⠰⡈⢆⡑⢂⠄⠓⣌⢎⡹⢤⠣⡜⡡⠞⡌⣇⢓⠮⡱⡜⡸⣍⠞⣥⢛⣬⢳⠼⣭⣟⡷⣇⠀⠀⠱⢀
⠏⡰⣙⣶⠿⣧⢿⣿⣟⡷⣾⣿⣽⢧⣋⡞⣧⢻⡜⣧⢻⡜⣧⢏⡷⣣⢟⡴⣏⠌⣢⢳⢮⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣄⠀⠈⠻⣾⣷⢌⢿⣟⠀⣿⣝⣺⡜⡤⠀⠀⠠⢙⠼⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠙⠻⣷⡜⢦⡁⠆⡑⠤⡘⢂⢆⠐⡈⢦⡙⢦⠳⣌⠱⣉⠖⡬⢊⡵⡑⢮⢱⢊⡝⢦⢋⠶⣩⠞⡵⣾⣽⣻⡄⠀⠸⢠
⡰⣵⡟⠁⢸⣳⣿⣿⢯⣿⣿⡷⣿⢢⡝⣾⡱⣏⢾⡱⣏⢾⡱⣏⢷⡹⣎⢞⣧⠚⣼⣣⣿⣿⣿⣿⣿⠿⠛⠛⠙⣛⣻⣿⣿⣿⣿⣿⣿⣦⡀⠈⢻⣎⠷⣿⠀⢹⣿⡵⣺⠔⡀⠀⢀⠸⣘⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⣿⣇⡞⠴⢠⠑⡌⢡⠊⢖⡈⢦⡙⢦⡛⣤⠛⡤⢋⡴⢋⠴⡙⡬⢣⣋⡜⢎⡝⡎⡵⣋⠾⣱⢯⡷⣧⠀⠘⡸
⡿⠋⠀⠀⣼⣿⣿⣯⣿⣿⣟⣿⣿⢠⢟⡶⣹⢎⣷⡹⣞⢧⡟⣞⢧⡻⣜⡭⡗⣏⢾⣽⣿⣿⣿⠛⠁⠀⡀⠀⠙⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣄⠈⣯⢿⣿⠀⠈⣿⣿⡱⢏⡄⠀⠀⢘⠴⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣾⡱⣃⠒⠌⢢⠙⢢⠜⡤⢛⠦⡝⣆⠻⣌⠧⡜⡱⢊⠵⣘⢣⠜⡜⣪⠴⣙⠴⣩⠳⢭⢿⡽⣷⡄⠀⡵
⠀⠀⠀⢀⣿⣿⡿⣿⣿⣿⣿⣿⣿⢨⡞⣵⣏⡟⣶⢫⡽⣞⡽⡽⣎⢷⡹⣜⢳⣿⣚⣿⣿⣿⠁⠀⠀⢠⣧⡀⠀⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠘⣯⢿⠀⠀⠹⣿⣝⣣⠆⡀⠀⢸⡘⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣶⠿⠛⠉⠉⠀⠀⠀⠹⣿⣧⣋⣜⠠⡉⢆⡙⠴⣩⢳⡹⢬⠳⣜⢢⡕⣡⢋⠖⣡⢚⡜⡱⢆⡳⣉⢞⣡⢛⡬⣳⢟⡷⣧⠀⢸
⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⡏⠲⣝⣳⡞⣽⢞⣯⢳⣻⡼⣟⡽⣎⢷⡹⣎⣿⢞⣿⣿⡏⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⢹⣿⠀⠀⠀⢿⣿⢦⢓⡀⠀⠱⣈⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠿⠋⠀⠀⢀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⢷⡌⡼⡱⣌⢣⡙⢮⡹⢎⣇⠲⡡⠎⡜⠤⢣⠜⣡⠏⠶⣉⢎⠦⡓⡼⢱⢯⢿⣵⡂⢸
⠀⠀⢀⣿⣿⣽⣿⣿⣿⣿⣿⣿⡁⢙⢮⡷⣻⡽⣞⣧⣟⣳⢿⣝⡾⣹⢮⣽⢺⣝⣯⣿⣿⡇⡀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣎⢿⠀⠀⠀⠈⢻⣿⡎⡄⠀⠀⠔⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠟⠁⠀⢀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣿⡵⣳⢽⣳⣜⠦⣙⢯⡜⣣⢑⠣⡜⢡⢣⠚⡥⣚⠵⣉⠞⡬⠱⣌⠳⣎⡻⣼⢧⠀
⠀⠀⣼⣯⡷⣿⣾⣿⣿⣿⣿⡿⡇⢨⢷⣻⢷⣻⡽⣞⣾⡽⣟⣮⠷⣏⡷⣞⣯⣿⣻⡿⣿⡆⠀⠀⢸⣿⣟⢿⣿⣟⣳⢯⡽⣯⣟⣿⣿⣿⣿⣿⡟⠟⠀⠀⠀⠀⠀⠀⠀⠿⣿⡔⡠⠀⢌⣻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣽⣧⢯⣗⠿⣷⡩⢖⣯⢒⡍⡒⢌⠣⣌⠳⣐⢣⠞⣡⠞⡰⢋⡔⢣⢜⡳⣝⡾⡄
⠀⠀⢺⢿⣼⣷⣿⣿⣿⣿⣿⡝⣇⢸⣾⣟⣯⡷⣟⣯⣷⢿⣟⣞⣻⢽⣺⡽⣾⣿⣿⠀⢻⣇⠀⠀⠈⣿⡏⠌⢿⡿⣼⢣⡟⣶⢫⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⣿⡐⡄⠂⣽⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⠿⠛⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣺⢻⡽⣿⠸⣜⣧⢎⠱⢌⡱⢄⠳⢌⡖⣙⠦⡙⢦⣉⢿⣌⠲⣙⢮⡽⣧
⠀⠀⢸⡟⣾⣿⣿⣿⣿⣿⡟⡼⣷⠀⡿⣾⣯⣟⣿⣳⣯⣿⢾⣝⣮⢯⡷⣽⣿⣿⣿⡇⠀⠻⣆⠀⠀⠹⢿⡀⠀⠻⣿⢯⣽⡞⣯⣿⠟⣾⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣷⡰⠈⢾⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢘⣿⠟⣁⠀⠀⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠻⣿⣿⣿⣯⢞⣽⡗⢬⣹⣎⡱⢊⠔⠪⠔⡣⢜⠼⡸⢍⡲⢌⡞⣷⡹⣘⢎⡷⣻
⠀⠀⣿⡝⣿⣿⣿⣿⣿⣿⡽⣱⢻⠀⡿⣿⣳⣿⣞⣯⣿⢯⣟⡾⣭⢷⣻⡽⣿⣿⣿⣿⠀⠀⠈⠢⣀⠀⠻⣷⣀⠀⠈⠉⠓⠛⠁⠈⢮⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⣷⡅⠚⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠁⠀⣿⣷⣶⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⢸⣿⣷⣿⣯⢾⢋⠶⡌⣷⡥⢃⢎⠱⣉⠲⣉⠮⣑⢫⠔⣣⢚⡼⣷⡩⢞⡼⣽
⠀⢠⡗⣯⣿⣿⣿⣿⣿⢧⣳⢣⢻⡇⢸⢿⣟⣾⢿⣽⡿⣿⣽⣻⡽⣯⢷⣻⣿⣿⣿⣿⠀⠀⠀⠀⠀⠉⠑⠮⠛⠳⠶⠤⢤⡤⠶⠿⠛⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣆⢿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢃⠀⢸⣿⣿⣿⣿⣿⣿⣟⡷⣿⣻⡽⣿⣿⣿⣿⣿⣿⡿⠀⠀⠈⢻⣿⣯⣿⡘⣦⠹⣜⣧⠏⣌⠲⣀⠇⡜⢢⡹⢌⣋⠶⣩⢒⡹⣿⡌⣳⠽
⠀⢸⣛⠶⣿⣿⣿⣿⣿⠳⣬⡓⢧⣧⠘⣿⣿⡾⣿⣻⣿⣟⣾⣳⢿⡽⣯⢷⣿⣿⣿⣿⠐⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⢈⣿⣿⣿⣿⣿⣿⣞⣿⣳⢯⡿⣽⣿⡟⣷⣿⣿⠃⠀⠀⠀⣾⣿⣿⣷⣻⣜⡿⣴⣿⡯⡔⢣⡐⠎⠬⡑⢎⡱⣊⠵⢣⡹⣜⡻⣷⣡⢛
⠀⣼⢎⣿⣿⣿⣿⣿⡻⣝⡲⣝⢺⣿⡀⢿⣿⢿⣽⣿⣳⣿⢯⣟⣯⢿⣽⣻⣿⣻⣿⣿⡇⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢆⠀⢻⣿⡱⢛⢿⣯⣿⡷⣿⢿⣽⠟⠋⢘⣼⣿⠏⠀⠀⠀⣾⢿⡽⠃⢻⣿⣿⣿⣿⣿⣷⡙⢦⡑⡊⢥⠙⡌⣖⡡⢏⡱⢒⣭⢳⣹⣧⣋
⠀⡏⡾⣼⣿⣿⣿⡇⡿⣜⡱⢮⣹⡟⣇⢸⣾⣟⡿⣾⣟⣿⣯⣟⣾⣻⣞⣿⣯⢿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⢌⣿⣷⣌⠀⠉⠙⠋⠛⠉⠀⢐⣠⣼⡿⠁⠀⠀⣤⣼⡿⠋⠀⠀⣴⣿⣿⣿⣿⣿⣿⣝⢦⡱⡘⠤⢋⡔⢢⠕⡪⠔⠣⣜⢣⢿⡼⣧
⠀⣿⡱⢿⣿⣿⣿⢼⣟⢲⡙⣦⣻⣝⣻⡄⢿⣯⡿⣿⣽⣿⡾⣽⣞⡷⣯⣿⣽⣻⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠋⠙⠻⠶⠤⣤⣤⣤⣾⣿⠿⠂⠀⠀⢀⠴⠟⠋⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣎⠵⣈⢇⠣⡜⡡⢎⡱⢩⠱⢬⢣⣿⣳⣽
⠈⣧⢻⣽⣻⣿⣿⢸⡝⢮⠵⣒⣿⢮⡳⣷⠨⢿⣽⡿⣽⣷⣿⣳⢯⡿⣽⣟⣿⣳⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠒⠒⠒⠋⠁⠀⠀⠀⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢎⡕⣊⠱⢢⡑⢎⡔⢣⠘⣬⢓⣿⣳⢾
⢰⡱⣏⢾⣽⣿⢿⡘⣮⠝⣎⠧⣿⡳⣝⡾⡇⢻⣯⣿⢿⣟⣾⡽⣯⣟⣿⣽⣞⡷⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⡜⢢⠍⢦⡉⠖⡬⡁⠎⡴⢫⢿⣯⣟
⢸⢲⡝⣾⣽⣯⢿⣘⢦⠻⣜⠳⣿⡝⣮⢳⣿⠡⣿⣿⢿⣿⣳⣟⣷⣻⣿⢎⣷⣻⣽⣿⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣻⣿⣿⣻⣿⣿⣿⣿⣾⣿⣭⢣⡚⢤⡙⡜⣠⠑⡂⢽⢹⢺⡷⣯
⢸⢣⡟⣼⣟⡞⣿⡘⣎⡳⣍⡳⣿⡹⣎⢷⣻⣧⢛⣿⣿⣯⢷⣻⣞⣿⣳⢻⡼⣳⢿⣿⣿⣿⡷⣿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⢯⣿⣿⣟⣿⣽⣿⣿⣷⣿⣿⣿⣇⠞⡰⣘⠰⣁⠎⡰⣍⡿⢸⣟⡷
⡇⡿⣜⣳⢯⡽⣻⢱⢣⢳⡜⣱⣿⢳⡝⣾⣹⣿⣎⢿⣿⣽⢯⡷⣿⣿⡱⣏⡾⣝⣿⣿⣿⣿⡿⣷⣻⣿⣤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣷⢤⣀⣀⣀⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⣿⣿⢯⣿⣿⢿⡾⣯⣟⣿⣿⣿⣿⣿⣿⣿⡬⡱⢌⠣⡜⢢⠐⣾⡇⢸⣟⡾
⣇⠿⣜⢧⡻⣜⣿⢩⢞⡱⢮⡱⣿⢣⡟⣶⣻⣟⣯⠞⣿⣿⢯⣿⣿⣷⡻⣼⣹⠽⣾⣿⣿⡷⣿⣻⡽⣷⣻⢷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣎⠷⣭⢻⡼⣭⣯⣝⡳⢶⡤⣄⣀⡀⣀⣀⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⣿⣿⢯⣿⣿⢯⣿⣻⢷⣻⣾⣿⣿⣿⣿⣿⣿⣧⠓⣎⠱⣌⠣⣘⣿⠁⢸⣯⣟
⢎⡟⡼⣣⢟⡼⣻⠜⣮⡙⣖⢣⣿⢣⣛⢶⣻⢮⣽⡞⣽⡿⣯⣿⣿⣿⡳⢧⣏⠿⣽⣿⣿⣿⣻⣽⣟⡷⣯⣟⡿⣽⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢯⡻⣜⢧⡻⣴⡙⡶⣹⢲⡹⡼⣩⠟⣭⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⢯⣿⣿⢯⣿⣳⢯⣟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⢬⡓⣌⠣⢸⣿⠀⢸⣷⣿
⢸⡹⣵⢫⢞⡵⣿⠸⣥⢛⣬⢓⣿⣣⠟⣮⢿⣓⢮⣿⢼⣛⣷⣿⣿⣿⣟⡳⢮⣻⣽⣿⣿⣳⣿⣳⣯⢿⣻⣞⣿⡽⣯⢿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⢽⣮⡵⣣⢟⡱⢧⣏⣳⢳⣭⣛⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣾⣿⣿⣿⣿⣿⣿⣿⡿⣯⢿⡾⣝⣻⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢢⡝⢦⡙⢼⡗⣈⠰⣿⣽
⣸⢳⡜⢧⣏⢾⣱⣙⢦⣋⠶⣩⢾⡵⣛⡼⣻⡜⣎⢿⣚⣯⣿⣿⢷⣿⣿⣹⢧⡳⢿⣿⣿⣽⢾⣷⣻⣟⣯⣟⣾⡽⣯⣟⣾⢿⣿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠷⢮⣽⣧⣾⠥⠷⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣶⣿⣿⣿⣿⣿⣿⣿⣿⣳⣿⢿⡽⣯⢻⣽⣿⣿⣿⣯⣽⣿⣿⣿⣿⣿⣿⣿⣷⡘⢧⠜⣿⠃⠤⠘⣿⣿
⡜⣣⢞⣣⣞⠾⠿⠾⠷⠟⠛⠛⠛⠛⠛⠚⠿⠿⠿⠞⠛⠋⠉⠙⠛⠻⣿⣿⡮⣝⣻⣿⣿⣯⢿⣞⣷⣻⣾⡽⣷⣻⢷⣯⣟⣿⣿⠟⠈⢓⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣴⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣿⣯⢿⣝⣯⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣫⢓⣺⡿⡈⢆⠡⣿⣿
⣜⡷⠛⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣿⣎⡷⣿⣿⣯⣟⡿⣞⣷⢯⡿⣽⢯⣿⣾⡿⠉⠀⠀⠀⠈⣿⢝⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣶⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⣟⡾⣏⣾⡾⠻⠛⠛⠙⠋⠉⠉⠛⣿⣿⣿⣿⣿⣿⣿⣧⢏⣼⠧⡑⢌⡀⣿⢯
⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⠀⠀⠀⠀⠀⠀⡨⠙⠓⣿⣿⡷⣯⣟⡿⣾⣻⣽⣯⢿⠿⡏⠀⠀⠀⠀⠀⠀⠘⣞⢿⣎⡟⠦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣤⣴⠖⠒⠋⠋⠁⠘⠻⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡿⣝⣳⣽⣿⣶⣶⣶⣶⣦⣴⣤⣴⣼⣿⣿⣿⣿⣿⣿⣿⣿⣎⣿⠰⣁⠆⠄⣿⠹
⠀⠀⠄⠀⠀⠀⠀⠀⠀⠀⣴⡇⠀⠀⠀⠀⠀⠀⠀⢠⣿⡷⠀⠀⠀⠀⠀⢀⠇⠀⠀⠀⠻⣿⣽⢾⣻⢷⣻⣽⡿⠂⣼⠁⠀⠀⠀⠀⠀⠀⠀⠹⣟⣿⣿⡰⢌⡓⢦⣀⠀⠄⠀⠀⣀⣀⣤⠤⠖⡒⢋⣯⣭⡿⠛⠁⠀⠠⢀⠀⠀⠁⢀⠀⠙⡿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣽⢫⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⡟⠰⠰⣈⠂⠓⠀
⠀⢠⠀⠀⠀⠀⠀⠀⢀⣾⢿⠧⠀⠀⠀⠀⠀⠀⢀⣿⡿⠁⠀⠀⠀⠀⠀⡼⠂⠀⠀⠀⠀⢹⠯⠿⠛⠛⠋⠉⣽⠀⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡎⢿⣿⣷⣜⢢⠩⡙⣉⠋⡍⢡⠒⡄⢎⣔⣽⣶⠿⠏⠀⡀⠐⡈⠤⢁⠂⠄⠠⠀⠀⣼⣷⢠⠏⣙⣻⣿⣿⣿⣿⣿⣿⣿⡿⣿⣻⡭⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢏⠅⡓⠤⢈⠀⠀
⠀⡀⠀⠀⠀⠀⠀⠀⣸⠃⡾⠀⠀⠀⠀⠀⠀⠀⣾⡟⡕⠀⠀⠀⠀⠀⢰⡇⠀⠀⠀⠀⠀⣸⠀⠀⠀⠀⠀⢾⡏⢨⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢀⠙⢿⣿⣿⣔⡱⢂⡱⡘⢦⣙⣼⣿⡿⠛⠁⠀⡀⠐⡄⢃⠜⡐⠠⠈⠄⠡⢈⠀⣿⡏⣹⡰⠛⠻⠿⣿⣿⣿⣿⣿⣿⣿⣟⢧⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣏⢂⠚⣄⠣⠄⠂⠀
⠂⠀⠀⠀⠀⠀⠀⢀⡏⢠⡗⠀⠀⠀⠀⠀⠀⢰⣿⢱⠇⠀⠀⠀⠀⢀⡟⠆⠀⠀⠀⠀⠀⣇⡄⠀⠀⠀⠀⢸⣷⢸⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠄⠙⢿⣿⣷⣭⢒⣽⣾⠿⡋⠑⠀⠁⠀⡀⠄⠡⢈⠆⡐⢀⠃⠌⡈⠐⡠⢘⣿⡇⡼⢧⠀⠀⠀⠀⠉⠉⠉⠛⢻⣟⣮⡿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡮⢌⠱⢠⢃⠌⡀⠀
⢈⠀⠀⠀⠀⠀⠀⣼⠁⢸⡏⠁⠀⠀⠀⠀⢀⣿⣧⡞⠀⠀⠀⠀⠀⢸⢳⠀⠀⠀⠀⠀⣸⠁⠀⠀⠀⠀⣼⠟⣡⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠈⠄⡉⢻⣝⣿⣿⠟⣡⠞⠀⠀⠀⠀⢀⠀⠌⢠⠁⠂⠔⠂⠌⠐⠠⠁⠒⣹⣿⠁⣧⠛⠀⠀⠀⠀⠀⠀⠀⠀⣼⢣⡟⠀⠀⠀⠀⠀⠀⠉⠙⠻⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠤⣉⠦⡈⠆⠀⠀
⠃⠀⠀⠀⠀⠀⢰⠟⠛⣿⠀⠀⠀⠀⠀⠀⢸⢻⠹⠁⠀⠀⠀⠀⠀⣾⠄⠀⠀⠀⠀⢠⣏⡀⠀⠀⠀⠘⣯⣰⢁⢺⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢌⠐⡀⠈⢯⣹⠉⡸⠁⠀⠀⠀⢈⠀⠄⠂⠌⠠⢈⠁⢂⠡⠈⠄⠡⢈⠐⣿⣞⢸⡷⡀⠀⠀⠀⠀⠀⠀⠀⢰⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⡰⢁⠆⡱⠈⠄⠀
⠀⠀⠀⠀⠀⠀⣸⢀⡾⠁⠀⠀⠀⠀⠀⢀⣾⠟⠀⠀⠀⠀⠀⠀⢸⡇⡇⠀⠀⠀⠀⣸⠀⠳⣄⠀⢀⣼⢽⡏⢸⡘⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠊⠄⡀⠃⡸⣞⢸⡃⠀⠀⠂⠀⠄⢈⠐⡈⠄⠡⢈⣶⡿⢦⠈⠀⠁⠀⢢⣯⣏⠠⣇⠙⣄⠀⠀⠀⠀⠀⠀⠺⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠿⣿⣿⣿⣿⣿⣿⣷⡘⠤⠓⢤⠉⡀⠀
⠀⠀⠀⢀⣠⣴⢻⠟⠀⠀⠀⠀⠀⠀⣪⡟⠉⠀⠀⠀⠀⠀⠀⢀⡿⢀⣀⡀⠀⠀⣀⡿⠀⠀⢸⠄⣼⣁⣞⠁⢀⡁⢇⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠣⢐⠠⢁⣹⣷⣼⠀⡄⢡⠁⠌⠐⡀⠂⠐⢈⠀⣼⡟⡄⢈⣿⢤⡀⠀⣸⣿⡨⠇⢸⡼⠛⠠⠁⠌⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣿⣿⣿⣷⠱⡈⡕⢢⠁⠄⠀
⠒⠛⠋⠉⠀⣿⠋⠀⠀⠀⠀⠀⣀⣾⠋⠀⠀⠀⠀⠀⠀⠀⣠⡾⢡⡜⢸⡈⠛⠒⠹⠁⠀⠀⢸⡾⠓⣿⠄⠀⠀⣷⣾⠜⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⠐⢀⣦⣵⠾⠿⠟⠛⠻⣷⣼⣤⡘⠠⠁⠀⠄⠁⠀⣴⡿⣦⠀⣾⢃⣤⣿⢶⣻⠇⣽⠀⢼⡟⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣿⣿⣿⣯⢣⠑⡌⢢⢁⠂⠀
⠀⠀⠀⠀⠀⠹⢄⣂⠀⠀⢀⣴⡟⠉⠀⠀⠀⠀⠀⢀⣠⠴⣏⠁⠀⢷⡘⠧⣀⡀⠀⠀⠀⠀⣼⢹⣼⠋⠀⠀⠀⢿⣸⠀⠀⠀⠀⠈⠀⡁⠀⠄⠈⢀⢢⣾⠟⠀⠠⠀⠌⠐⢀⠀⠌⠙⣾⣤⠐⠀⠀⢂⣠⣿⠁⣹⡟⢿⡝⢃⡞⢠⡏⣽⠁⠀⢘⢷⣠⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣿⡅⢎⠰⡁⠆⡐⠀
⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠀⠉⣳⢤⣀⣀⣠⠴⠋⠁⠀⠈⠉⠉⠉⠛⠦⣤⣈⠑⠂⢀⣾⠟⢹⡇⠀⠀⠀⠀⣿⢺⣆⠀⠀⠀⡁⠂⠐⠠⠀⠌⢠⣿⠇⠀⠂⡁⠐⠠⠈⡀⠐⡀⠂⡘⣿⡆⠀⠀⢈⣿⣧⡾⠟⡹⠛⠹⡞⠀⣞⢠⣯⠀⠀⠈⡿⡏⢪⡅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⡘⢄⢣⠘⡰⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡏⠀⠀⠀⠀⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⣿⢠⠏⠁⠀⠀⠀⠀⣣⠽⡇⠆⠀⠀⠀⠡⠈⢀⣐⣾⠛⣿⠀⠀⡁⠠⠈⠄⠐⠀⡁⢀⠂⡱⠿⡷⣆⠈⠘⢺⣏⠀⡽⠃⠀⣰⠃⢰⢷⡻⠃⠀⠀⠀⢹⢳⣞⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣳⡜⡌⣂⠇⡡⠀⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠁⠀⠀⠀⠀⠈⠳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡾⢛⡏⠀⠀⠀⠀⠀⠀⣻⡓⣷⠀⠀⠀⢁⣤⣶⠿⡽⠛⣋⣽⠀⠐⠀⡁⠐⡈⢀⠁⡀⢂⠰⣹⢥⡙⢾⡦⡄⠈⠈⢳⣦⣀⣴⠃⢠⣾⠼⠟⠀⠈⠆⠀⢸⢼⡏⠛⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⡔⢢⢊⠔⠁⠀
⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⠀⠀⠀⠀⠀⠀⠀⠙⢆⠀⠀⠀⠀⠀⠀⠀⠀⠀⢺⢀⠾⠀⠀⠀⠀⠀⠀⠀⠘⠳⢾⣀⡲⠶⠟⣋⠓⠖⠃⠀⠌⠿⣧⠂⡁⠠⢁⠐⣀⠂⡐⣀⢳⡝⡾⠉⢁⡉⠉⢲⢤⠀⡀⢈⠁⣄⣾⠠⠇⠀⢀⠃⠈⠀⠀⠘⢻⣄⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣇⠬⠘⡀⠀
    
    
     */
 
}