package test.java.persistencia;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.fail;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;
import persistencia.EmpresaDTO;
import persistencia.PersistenciaBIN;

public class PersistenciaTest {
	
	PersistenciaBIN persistencia;

    @Before
	public void setUp() {
		persistencia = new PersistenciaBIN();
	}

	@After
	public void tearDown() {

	}

    @Test
    public void AbrirInputTest() throws IOException  {
		try {
			persistencia.abrirInput("archivo.bin");
		}
		catch (Exception e){
			fail("No tendria que lanzar una excepcion");
		}

		try {
			persistencia.abrirInput(null);
			fail("Tiene que lanzar una null pointer exception");
		}
		catch (Exception e1){
		}
    }

    @Test
    public void AbrirOutputTest() {
		try {
			persistencia.abrirOutput("archivo.bin");
		} catch (IOException e1) {
			fail("no tendria que lanzar una excepcion");
		}
		try {
			persistencia.abrirOutput(null);
			fail("Tiene que lanzar una null pointer exception");
		} catch (Exception e) {
		}
	}

    @Test
	public void CerrarInputTest() {
		try {
			persistencia.abrirInput("archivo.bin");			
			persistencia.cerrarInput();
		} catch (IOException e) {
			fail("no debería lanzar una excepcion");	
		}
	}

    @Test // PREGUNTAR
	public void CerrarInputExceptionTest() {
        try {		
			persistencia.cerrarInput();
		}
        catch(IOException ex){
     		fail("Tiene que generar esta excepcion en este caso");
		}
	}

    @Test
	public void CerrarOutputTest() {
		try {
			persistencia.abrirOutput("archivo.bin");			
			persistencia.cerrarOutput();
		} catch (IOException e) {
			fail("no tendria que lanzar una excepcion");
		}
	}

	@Test
	public void LeerTest() {
		try{
			persistencia.abrirInput("archivo.bin");
			EmpresaDTO empresa;
			empresa = (EmpresaDTO) persistencia.leer();
		}
		catch (Exception e){
			fail("No tendría que lanzar una excepcion");
		}
	}

	@Test 
	public void LeerFallidoTest(){ //archivo sin abrir
		try{
			EmpresaDTO empresa;
			empresa = (EmpresaDTO) persistencia.leer();
		}
		catch (IOException e){
			//TODO:pass(,e.getCause()); // como lo escribimos??
		}
		catch (Exception e){
			fail("No tendria que lanzar una excepcion que no sea IOException");
		}
	}

	@Test
	public void testEscribir(){ //TODO:pregntar si hay que testear en archivo no vacio tmb
		EmpresaDTO empresa = new EmpresaDTO();
		try{
			persistencia.abrirOutput("archivo.bin");
			persistencia.escribir(empresa);
		}
		catch(Exception e){
			fail("No deberia lanzar ninguna excepcion");
		}
	}




    
}
