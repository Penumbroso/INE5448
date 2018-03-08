package Tests;
import static org.junit.Assert.*;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;


public class Time {

	@Test
	public void criaMeioDia() {
		//Fixture Setup
		//Exercise SUT
		LocalTime meioDia = new LocalTime(12,0,0);
		//Result Verification
		assertEquals(12, meioDia.getHourOfDay());
		assertEquals(0, meioDia.getMinuteOfHour());
		assertEquals(0, meioDia.getSecondOfMinute());
		//Fixture Teardown
		
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaHoraNegativa() {
		//Fixture Setup
		//Exercise SUT
		LocalTime horaNegativa = new LocalTime(-1,0,0);
		//Result Verification
		//Fixture Teardown
		
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaMinutosNegativo() {
		//Fixture Setup
		//Exercise SUT
		LocalTime minNegativo = new LocalTime(12,-1,0);
		//Result Verification
		//Fixture Teardown
		
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaSegundosNegativo() {
		//Fixture Setup
		//Exercise SUT
		LocalTime minNegativo = new LocalTime(12,10,-1);
		//Result Verification
		//Fixture Teardown
		
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaHora24() {
		//Fixture Setup
		//Exercise SUT
		LocalTime horario24 = new LocalTime(24,0,0);
		//Result Verification
		//Fixture Teardown
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void cria60Minutos() {
		//Fixture Setup
		//Exercise SUT
		LocalTime minutos60 = new LocalTime(23,60,0);
		//Result Verification
		//Fixture Teardown
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void cria60Segundos() {
		//Fixture Setup
		//Exercise SUT
		LocalTime segundos60 = new LocalTime(23,10,60);
		//Result Verification
		//Fixture Teardown
	}
	
	@Test
	public void somaMeioDiaMais12horas() {
		//Fixture Setup
		LocalTime meioDia = new LocalTime(12,0,0);
		//Exercise SUT
		LocalTime meiaNoite = meioDia.plusHours(12);
		//Result Verification
		assertEquals(new LocalTime(0,0,0), meiaNoite);
		//Fixture Teardown
	}
	
	@Test
	public void somaMinutosMenorQue60() {
		//Fixture Setup
		LocalTime umaPrasDoze = new LocalTime(11,59,0);
		//Exercise SUT
		LocalTime meioDia = umaPrasDoze.plusMinutes(1);
		//Result Verification
		assertEquals(new LocalTime(12,0,0), meioDia);
		//Fixture Teardown
	}
	
	@Test
	public void somaMinutosMaiorQue60() {
		//Fixture Setup
		LocalTime dezHoras = new LocalTime(10,0,0);
		//Exercise SUT
		LocalTime meioDia = dezHoras.plusMinutes(120);
		//Result Verification
		assertEquals(new LocalTime(12,0,0), meioDia);
		//Fixture Teardown
	}
	
	@Test
	public void somaMinutosNegativo() {
		//Fixture Setup
		LocalTime dezHoras = new LocalTime(10,0,0);
		//Exercise SUT
		LocalTime oitoHorasEMeia = dezHoras.plusMinutes(-90);
		//Result Verification
		assertEquals(new LocalTime(8,30,0), oitoHorasEMeia);
		//Fixture Teardown
	}
	
	@Test
	public void somaZeroMinutos() {
		//Fixture Setup
		LocalTime meioDia = new LocalTime(12,0,0);
		//Exercise SUT
		LocalTime meioDia2 = meioDia.plusMinutes(0);
		//Result Verification
		assertEquals(meioDia, meioDia2);
		//Fixture Teardown
	}
	
	@Test
	public void somaSegundosMaiorQue60() {
		//Fixture Setup
		LocalTime umaPrasDoze = new LocalTime(11,59,0);
		//Exercise SUT
		LocalTime meioDia = umaPrasDoze.plusSeconds(60);
		//Result Verification
		assertEquals(new LocalTime(12,0,0), meioDia);
		//Fixture Teardown
	}
	
	@Test
	public void somaSegundosMenorQue60() {
		//Fixture Setup
		LocalTime umaPrasDoze = new LocalTime(11,59,30);
		//Exercise SUT
		LocalTime meioDia = umaPrasDoze.plusSeconds(30);
		//Result Verification
		assertEquals(new LocalTime(12,0,0), meioDia);
		//Fixture Teardown
	}
	
	@Test
	public void somaSegundosNegativos() {
		//Fixture Setup
		LocalTime umaPrasDoze = new LocalTime(12,0,30);
		//Exercise SUT
		LocalTime meioDia = umaPrasDoze.plusSeconds(-30);
		//Result Verification
		assertEquals(new LocalTime(12,0,0), meioDia);
		//Fixture Teardown
	}
	
	@Test
	public void somaZeroSegundos() {
		//Fixture Setup
		LocalTime umaPrasDoze = new LocalTime(12,0,0);
		//Exercise SUT
		LocalTime meioDia = umaPrasDoze.plusSeconds(0);
		//Result Verification
		assertEquals(new LocalTime(12,0,0), meioDia);
		//Fixture Teardown
	}
	
	@Test
	public void subtraiHoras() {
		//Fixture Setup
		LocalTime meioDia = new LocalTime(12,0,0);
		//Exercise SUT
		LocalTime horas23 = meioDia.minusHours(13);
		//Result Verification
		assertEquals(new LocalTime(23,0,0), horas23);
		//Fixture Teardown
	}
	
	@Test
	public void subtrai24Horas() {
		//Fixture Setup
		LocalTime meioDia = new LocalTime(12,0,0);
		//Exercise SUT
		LocalTime meioDia2 = meioDia.minusHours(24);
		//Result Verification
		assertEquals(meioDia, meioDia2);
		//Fixture Teardown
	}
	
	@Test
	public void subtraiMinutosMaiorQue60() {
		//Fixture Setup
		LocalTime meioDia = new LocalTime(12,0,0);
		//Exercise SUT
		LocalTime horas10 = meioDia.minusMinutes(120);
		//Result Verification
		assertEquals(new LocalTime(10,0,0), horas10);
		//Fixture Teardown
	}
	
	@Test
	public void subtraiMinutosMenorQue60() {
		//Fixture Setup
		LocalTime meioDia = new LocalTime(12,0,0);
		//Exercise SUT
		LocalTime horas10 = meioDia.minusMinutes(30);
		//Result Verification
		assertEquals(new LocalTime(11,30,0), horas10);
		//Fixture Teardown
	}
	
	@Test
	public void subtraiZeroMinutos() {
		//Fixture Setup
		LocalTime meioDia = new LocalTime(12,0,0);
		//Exercise SUT
		LocalTime meioDia2 = meioDia.minusMinutes(0);
		//Result Verification
		assertEquals(meioDia, meioDia2);
		//Fixture Teardown
	}
	
	@Test
	public void horarioEhMaisCedoVerdadeiro() {
		//Fixture Setup
		LocalTime meioDia = new LocalTime(12, 0, 0);
		LocalTime trezeHoras = new LocalTime(13, 0, 0);
		//Exercise SUT
		boolean meioDiaEhMaisCedoQue13Horas = meioDia.isBefore(trezeHoras);
		//Result Verification
		assertTrue(meioDiaEhMaisCedoQue13Horas);
		//Fixture Teardown
	}
	
	@Test
	public void horarioEhMaisCedoFalso() {
		//Fixture Setup
		LocalTime meioDia = new LocalTime(12, 0, 0);
		LocalTime onzeHoras = new LocalTime(11, 0, 0);
		//Exercise SUT
		boolean meioDiaEhMaisCedoQue11Horas = meioDia.isBefore(onzeHoras);
		//Result Verification
		assertFalse(meioDiaEhMaisCedoQue11Horas);
		//Fixture Teardown
	}
	
	@Test
	public void horarioIgualEhMaisCedo() {
		//Fixture Setup
		LocalTime meioDia = new LocalTime(12, 0, 0);
		LocalTime meioDia2 = new LocalTime(12, 0, 0);
		//Exercise SUT
		boolean meioDiaEhMaisCedoQueMeioDia = meioDia.isBefore(meioDia2);
		//Result Verification
		assertFalse(meioDiaEhMaisCedoQueMeioDia);
		//Fixture Teardown
	}
	
	@Test
	public void horarioEhMaisCedoMinutos() {
		//Fixture Setup
		LocalTime meioDia = new LocalTime(12, 0, 0);
		LocalTime meioDiaEUm = new LocalTime(12, 1, 0);
		//Exercise SUT
		boolean meioDiaEhMaisCedoQueMeioDiaEUm = meioDia.isBefore(meioDiaEUm);
		//Result Verification
		assertTrue(meioDiaEhMaisCedoQueMeioDiaEUm);
		//Fixture Teardown
	}
	
	@Test
	public void horarioEhMaisCedoSegundos() {
		//Fixture Setup
		LocalTime meioDia = new LocalTime(12, 0, 0);
		LocalTime meioDiaEUmSegundo = new LocalTime(12, 0, 1);
		//Exercise SUT
		boolean meioDiaEhMaisCedoQueMeioDiaEUmSegundo = meioDia.isBefore(meioDiaEUmSegundo);
		//Result Verification
		assertTrue(meioDiaEhMaisCedoQueMeioDiaEUmSegundo);
		//Fixture Teardown
	}
}
