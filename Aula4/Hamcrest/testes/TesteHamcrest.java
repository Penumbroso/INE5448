package testes;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.List;


import org.junit.Test;

public class TesteHamcrest {
	
	@Test
	public void testeIgualdadeDeObjeto(){
		assertThat("A" , equalTo("A"));
	}
	
	@Test
	public void testeNumeroEhMaiorQueEMenorQue(){
		assertThat(9.9, is(both(greaterThan(9.8)).and(lessThan(10.0))));
	}
	
	@Test
	public void testeTextoIgnorandoMaiusculasEMinusculas(){
		assertThat("UfSc", equalToIgnoringCase("UFSC"));
	}
	
	@Test
	public void testeArrayContemElementos() throws Exception {
		Integer[] numeros = {1, 2, 3, 4};
		assertThat(numeros, arrayContainingInAnyOrder(3, 2, 4, 1));
	}
	
	@Test
	public void verificarItemPresente() throws Exception {
		List<String> pessoas = Arrays.asList("Maria", "Pedro", "Jessica");
		assertThat(pessoas, hasItem("Jessica"));
	}
	
	@Test
	public void testeAllOf(){
		assertThat("Maria Machado", allOf(startsWith("Maria"), containsString("Machado")));
	}
	
	@Test
	public void testeAnyOf(){
		assertThat("Maria Machado", anyOf(startsWith("Maria"), containsString("Nada")));
	}

}