import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Assert;

import org.junit.Test;

public class Teste {
	private WebDriver selenium;
	
	@Before
	public void configurar(){
		selenium = new FirefoxDriver();
		System.setProperty("webdriver.gecko.driver","/Selenium/geckodriver.exe");
		selenium.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		selenium.get("http://calculatoria.com");
		WebElement botao2 = selenium.findElement(By.id("btn27"));
		botao2.click();
	}

	@Test
	public void test1() throws Exception {   
		
		WebElement botao2 = selenium.findElement(By.id("btn98"));
		botao2.click();
		
		WebElement botao0 = selenium.findElement(By.id("btn96"));
		botao0.click();
		
		WebElement botaoMultiplicacao = selenium.findElement(By.id("btn106"));
		botaoMultiplicacao.click();
		
		WebElement botao4 = selenium.findElement(By.id("btn100"));
		botao4.click();
		
		WebElement botaoIgual = selenium.findElement(By.id("btn13"));
		botaoIgual.click();
		
		WebElement resultado = selenium.findElement(By.className("displaysum"));
		String result = resultado.getAttribute("value");
		
		assertEquals("80", result);
		
	}
	
	@Test
	public void test2(){
		WebElement botao1 = selenium.findElement(By.id("btn97"));
		botao1.click();
		
		WebElement botao0 = selenium.findElement(By.id("btn96"));
		botao0.click();
		botao0.click();
		
		WebElement botaoDivisao = selenium.findElement(By.id("btn111"));
		botaoDivisao.click();
		
		WebElement botao3 = selenium.findElement(By.id("btn99"));
		botao3.click();
		
		WebElement botaoIgual = selenium.findElement(By.id("btn13"));
		botaoIgual.click();
		
		WebElement resultado = selenium.findElement(By.className("displaysum"));
		String result = resultado.getAttribute("value");
		
		assertEquals("33.333", result);
		
	}
	
	@Test
	public void test3(){
		WebElement botao5 = selenium.findElement(By.id("btn101"));
		botao5.click();
		
		WebElement botaoDivisao = selenium.findElement(By.id("btn111"));
		botaoDivisao.click();
		
		WebElement botao2 = selenium.findElement(By.id("btn98"));
		botao2.click();
		
		WebElement botaoIgual = selenium.findElement(By.id("btn13"));
		botaoIgual.click();
		
		WebElement botaoDel = selenium.findElement(By.id("btn8"));
		botaoDel.click();
		botaoDel.click();
		
		WebElement botaoSoma = selenium.findElement(By.id("btn107"));
		botaoSoma.click();
		
		WebElement botao4 = selenium.findElement(By.id("btn100"));
		botao4.click();
		
		botaoIgual.click();
		
		WebElement resultado = selenium.findElement(By.className("displaysum"));
		String result = resultado.getAttribute("value");
		
		assertEquals("6", result);
		
	}
	
	
	@After
	public void terminar(){
		selenium.close();
	}

}