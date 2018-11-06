package UiTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import PageObject.CalculatorPage;
import enums.CalculatorOperator;


public class CalculatorTests {
	
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
	}


	@Test
	public void testAddition() throws Exception {
		String expectedResult = "5";
		CalculatorPage calculator = new CalculatorPage(driver);
		String result = calculator.getBinaryResult(2, 3, CalculatorOperator.ADDITION);
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testSubtraction() throws Exception {
		String expectedResult = "8";
		CalculatorPage calculator = new CalculatorPage(driver);
		String result = calculator.getBinaryResult(10, 2, CalculatorOperator.SUBTRACTION);
		Assert.assertEquals(expectedResult, result);
	}
	
	@Test
	public void testComplex() throws NumberFormatException, Exception {
		String notValidResult = "20";
		CalculatorPage calculator = new CalculatorPage(driver);
		String substractionResult = calculator.getBinaryResult(10, 2, CalculatorOperator.SUBTRACTION);		
		String result = calculator.getBinaryResult(Double.parseDouble(substractionResult), 2, CalculatorOperator.MULTIPLICATION);
		Assert.assertNotEquals(notValidResult, result);
	}
	
	@Test
	public void testSinus() throws Exception {
		String expectedResult = "0.5";
		CalculatorPage calculator = new CalculatorPage(driver);
		String result = calculator.getTrigoResult(30, CalculatorOperator.SINUS);
		Assert.assertEquals(expectedResult, result);
	}
	
	
	@After
	public void tearDown() throws Exception {
		driver.close();
	}

}
