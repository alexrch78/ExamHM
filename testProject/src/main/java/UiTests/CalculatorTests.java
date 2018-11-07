package UiTests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import PageObject.CalculatorHistoryPageSection;
import PageObject.CalculatorPage;


public class CalculatorTests {
	
	private static WebDriver driver;
	private static CalculatorPage calculator;

	
	@BeforeClass
	public static void classSetUp() throws Exception {
	   driver = new ChromeDriver();
	   calculator = new CalculatorPage(driver);
	}
	
	
	@Before
	public void setUp() throws Exception {
		calculator.clearResult();
	}


	String formulaAdd = "2+3";
	String formulaSubstraction = "10-2";
	String formulaComplex = "(10-2)*2";
	String formulaTrigo = "sin(30)";
	
	String expectedResultAdd = "5";
	String expectedResultSubstraction = "8";
	String notValidResultComplex = "20";
	String expectedResultTrigo = "0.5";
	

	@Test
	public void testAddition() throws Exception {
		String result = calculator.getCalculationResult(formulaAdd);
		Assert.assertEquals(expectedResultAdd, result);
		
		CalculatorHistoryPageSection history = new CalculatorHistoryPageSection(driver);
		Assert.assertEquals(expectedResultAdd, history.getResult(formulaAdd));
	}
	
	@Test
	public void testSubtraction() throws Exception  {
		String result = calculator.getCalculationResult(formulaSubstraction);
		Assert.assertEquals(expectedResultSubstraction, result);
		
		CalculatorHistoryPageSection history = new CalculatorHistoryPageSection(driver);
		Assert.assertEquals(expectedResultSubstraction, history.getResult(formulaSubstraction));
	}
	
	@Test
	public void testComplex() throws Exception {
		String result = calculator.getCalculationResult(formulaComplex);
		Assert.assertNotEquals(notValidResultComplex, result);
		
		CalculatorHistoryPageSection history = new CalculatorHistoryPageSection(driver);
		Assert.assertNotEquals(notValidResultComplex, history.getResult(formulaComplex));
	}
	
	@Test
	public void testSinus() throws Exception {
		String result = calculator.getCalculationResult(formulaTrigo);
		Assert.assertEquals(expectedResultTrigo, result);
		
		CalculatorHistoryPageSection history = new CalculatorHistoryPageSection(driver);
		Assert.assertEquals(expectedResultTrigo, history.getResult(formulaTrigo));
	}
	
	//@Test
	public void testHistory() {
		//CalculatorHistoryPageSection history = new CalculatorHistoryPageSection(driver);
		//Assert.assertEquals(expectedResultAdd, history.getResult(formulaAdd));
		//Assert.assertEquals(expectedResultSubstraction, history.getResult(formulaSubstraction));
		//Assert.assertNotEquals(notValidResultComplex, history.getResult(formulaComplex));
		//Assert.assertEquals(expectedResultTrigo, history.getResult(formulaTrigo));
	}
	
	
	@After
	public void tearDown() throws Exception {
		calculator.clearResult();
	}
	
	@AfterClass
	public static void classTearDown() throws Exception {
		driver.close();
	}

}
