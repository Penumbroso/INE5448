package Tests;
import static org.junit.Assert.*;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;
import org.junit.Test;


public class Date {

	@Test
	public void criaDataValida() {
		//Fixture Setup
		//Exercise SUT
		LocalDate natal = new LocalDate(2017,12,25);
		  //Result Verification
		assertEquals(2017, natal.getYear());
		assertEquals(12, natal.getMonthOfYear());
		assertEquals(25, natal.getDayOfMonth());
		//Fixture Teardown
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaDiaNegativo(){
		//Fixture Setup
		//Exercise SUT
		LocalDate diaNegativo = new LocalDate(2017,1,-1);
	    //Result Verification
		//Fixture Teardown
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaDiaMaiorQue31(){
		//Fixture Setup
		//Exercise SUT
		LocalDate diaInexistente = new LocalDate(2017,1, 32);
	    //Result Verification
		//Fixture Teardown
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaDiaZero(){
		//Fixture Setup
		//Exercise SUT
		LocalDate diaInexistente = new LocalDate(2017, 1, 0);
	    //Result Verification
		//Fixture Teardown
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaMesNegativo(){
		//Fixture Setup
		//Exercise SUT
		LocalDate mesNegativo = new LocalDate(2017, -1, 1);
	    //Result Verification
		//Fixture Teardown
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaMesMaiorQue12(){
		//Fixture Setup
		//Exercise SUT
		LocalDate mesMaiorQue12 = new LocalDate(2017, 13, 1);
	    //Result Verification
		//Fixture Teardown
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaMesZero(){
		//Fixture Setup
		//Exercise SUT
		LocalDate mesZero = new LocalDate(2017, 0, 1);
	    //Result Verification
		//Fixture Teardown
	}

	@Test
	public void criaAnoNegativo(){
		//Fixture Setup
		//Exercise SUT
		LocalDate anoNegativo = new LocalDate(-1, 1, 1);
	    //Result Verification
		assertEquals(-1, anoNegativo.getYear());
		//Fixture Teardown
	}
	
	@Test
	public void criaAnoZero(){
		//Fixture Setup
		//Exercise SUT
		LocalDate anoNegativo = new LocalDate(0, 1, 1);
	    //Result Verification
		assertEquals(0, anoNegativo.getYear());
		//Fixture Teardown
	}
	
	@Test
	public void somaDiasNatal() {
		//Fixture Setup
		LocalDate natal = new LocalDate(2017,12,25);
		//Exercise SUT
		LocalDate novaData = natal.plusDays(7);
		//Result Verification
		LocalDate dataEsperada = new LocalDate(2018, 1, 1);
		assertEquals(dataEsperada, novaData);
		//Fixture Teardown
	}
	
	@Test
	public void subtraiDiasAnoNovo() {
		//Fixture Setup
		LocalDate anoNovo = new LocalDate(2018,1,1);
		//Exercise SUT
		LocalDate novaData = anoNovo.minusDays(7);
		//Result Verification
		LocalDate natal = new LocalDate(2017, 12, 25);
		assertEquals(natal, novaData);
		//Fixture Teardown
	}
	
	@Test
	public void somaMeses() {
		//Fixture Setup
		LocalDate janeiro = new LocalDate(2018,1,1);
		//Exercise SUT
		LocalDate fevereiro = janeiro.plusMonths(1);
		//Result Verification
		assertEquals(2, fevereiro.getMonthOfYear());
		//Fixture Teardown
	}
	
	@Test
	public void soma12Meses() {
		//Fixture Setup
		LocalDate janeiro = new LocalDate(2018,1,1);
		//Exercise SUT
		LocalDate fevereiro = janeiro.plusMonths(12);
		//Result Verification
		assertEquals(2019, fevereiro.getYear());
		//Fixture Teardown
	}
	
	@Test
	public void somaMesNoFimDoMes() {
		//Fixture Setup
		LocalDate janeiro = new LocalDate(2018,1,31);
		//Exercise SUT
		LocalDate fevereiro = janeiro.plusMonths(1);
		//Result Verification
		LocalDate fimDeFevereiro = new LocalDate(2018, 2, 28);
		assertEquals(fimDeFevereiro, fevereiro);
		//Fixture Teardown
	}
	
	@Test
	public void somaAnos() {
		//Fixture Setup
		LocalDate ano2018 = new LocalDate(2018,1,1);
		//Exercise SUT
		LocalDate ano2020 = ano2018.plusYears(2);
		//Result Verification
		assertEquals(2020, ano2020.getYear());
		//Fixture Teardown
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaDataDeAnoBissextoInvalido(){
		//Fixture Setup
		//Exercise SUT
		LocalDate dia29Fevereiro = new LocalDate(2018,2,29);
		//Result Verification
		//Fixture Teardown
	}
	
	@Test
	public void criaDataDeAnoBissextoValido(){
		//Fixture Setup
		//Exercise SUT
		LocalDate dia29Fevereiro = new LocalDate(2020,2,29);
		//Result Verification
		assertEquals(29, dia29Fevereiro.getDayOfMonth());
		//Fixture Teardown
	}
	
}
