package UiTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class CalculatorTests {
	
	private WebDriver driver;
	private final int DELTA = 0;

	@Before
	public void setUp() throws Exception {
		driver = new ChromeDriver();
	}


	@Test
	public void test1() {
		double expectedResult = 5;
		CalculatorPage calculator = new CalculatorPage(driver);
		double result = calculator.getBinaryResult(2, 3, CalculatorBinaryOperator.ADDITION);
		Assert.assertEquals(expectedResult, result, DELTA);
	}
	
	@Test
	public void test2() {
		double expectedResult = 8;
		CalculatorPage calculator = new CalculatorPage(driver);
		double result = calculator.getBinaryResult(10, 2, CalculatorBinaryOperator.SUBTRACTION);
		Assert.assertEquals(expectedResult, result, DELTA);
	}
	
	@Test
	public void test3() {
		double notValidResult = 20;
		CalculatorPage calculator = new CalculatorPage(driver);
		double substractionResult = calculator.getBinaryResult(10, 2, CalculatorBinaryOperator.SUBTRACTION);
		double result = calculator.getBinaryResult(substractionResult, 2, CalculatorBinaryOperator.MULTIPLICATION);
		Assert.assertNotEquals(notValidResult, result);
	}
	
	@Test
	public void test4() {
		double expectedResult = 0.5;
		CalculatorPage calculator = new CalculatorPage(driver);
		double result = calculator.getTrigoResult(30, CalculatorTrigoOperator.SINUS);
		Assert.assertEquals(expectedResult, result, DELTA);
	}
	
	
	@After
	public void tearDown() throws Exception {
		driver.close();
	}

}
