package Tests;
import static org.junit.Assert.assertEquals;

import org.joda.time.IllegalFieldValueException;
import org.joda.time.Interval;
import org.joda.time.LocalDateTime;
import org.joda.time.Period;
import org.junit.Test;


public class DateTime {

	@Test
	public void criaDataValida() {
		//Fixture Setup
		//Exercise SUT
		LocalDateTime natal = new LocalDateTime(2017, 12, 25 ,12 ,30);
		  //Result Verification
		assertEquals(2017, natal.getYear());
		assertEquals(12, natal.getMonthOfYear());
		assertEquals(25, natal.getDayOfMonth());
		assertEquals(12, natal.getHourOfDay());
		assertEquals(30, natal.getMinuteOfHour());
		//Fixture Teardown
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaDataComMesNegativo() {
		//Fixture Setup
		//Exercise SUT
		LocalDateTime natal = new LocalDateTime(2017, -12, 25 ,12 ,30);
		//Result Verification
		//Fixture Teardown
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaDataComHorarioNegativo() {
		//Fixture Setup
		//Exercise SUT
		LocalDateTime natal = new LocalDateTime(2017, 12, 25 ,-12 ,30);
		//Result Verification
		//Fixture Teardown
	}
	
	@Test(expected=IllegalFieldValueException.class)
	public void criaCom60Segundos() {
		//Fixture Setup
		//Exercise SUT
		LocalDateTime natal = new LocalDateTime(2017, 12, 25 ,12 , 60);
		//Result Verification
		//Fixture Teardown
	}
	
	@Test
	public void criaIntervaloValido() {
		//Fixture Setup
		LocalDateTime dataInicial = new LocalDateTime(2018, 10, 25, 1, 0, 0);
		LocalDateTime dataFinal = new LocalDateTime(2018, 12, 26, 1, 0, 0);
				
		//Exercise SUT
		Interval intervalo = new Interval(dataInicial.toDateTime(), dataFinal.toDateTime());
		    		
		//Result Verification
		assertEquals(dataInicial.toDateTime(), intervalo.getStart());
		assertEquals(dataFinal.toDateTime(), intervalo.getEnd());
		        
		//Fixture Teardown
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void criaIntervaloComDataFinalAntesDaDataInicial() {
		//Fixture Setup
		LocalDateTime dataInicial = new LocalDateTime(2018, 10, 25, 1, 0, 0);
		LocalDateTime dataFinal = new LocalDateTime(2017, 12, 26, 1, 0, 0);
				
		//Exercise SUT
		Interval intervalo = new Interval(dataInicial.toDateTime(), dataFinal.toDateTime());
		    		
		//Result Verification    
		//Fixture Teardown
	}
	
	@Test
	public void criaIntervaloComFimEInicioIdenticos() {
		//Fixture Setup
		LocalDateTime dataInicial = new LocalDateTime(2018, 10, 25, 1, 0, 0);
		LocalDateTime dataFinal = new LocalDateTime(2018, 10, 25, 1, 0, 0);
				
		//Exercise SUT
		Interval intervalo = new Interval(dataInicial.toDateTime(), dataFinal.toDateTime());
		
		//Result Verification
		assertEquals(0, intervalo.toDuration().getStandardSeconds());
		//Fixture Teardown
	}
	
	@Test
	public void criaPeriodoValido() {
		//Fixture Setup
		
		//Exercise SUT
		Period period = new Period(30, 15, 0, 10);
		//Result Verification
		assertEquals(30, period.getHours());
		assertEquals(15, period.getMinutes());
		assertEquals(0, period.getSeconds());
		assertEquals(10, period.getMillis());
		//Fixture Teardown
	}
	
	@Test
	public void criaPeriodoNegativo() {
		//Fixture Setup
		
		//Exercise SUT
		Period period = new Period(-30, 15, 0, 10);
		//Result Verification
		assertEquals(-30, period.getHours());
		assertEquals(15, period.getMinutes());
		assertEquals(0, period.getSeconds());
		assertEquals(10, period.getMillis());
		//Fixture Teardown
	}
	
	@Test
	public void criaPeriodoZero() {
		//Fixture Setup
		
		//Exercise SUT
		Period period = new Period(0, 0, 0, 0);
		//Result Verification
		assertEquals(0, period.getHours());
		assertEquals(0, period.getMinutes());
		assertEquals(0, period.getSeconds());
		assertEquals(0, period.getMillis());
		//Fixture Teardown
	}

}
