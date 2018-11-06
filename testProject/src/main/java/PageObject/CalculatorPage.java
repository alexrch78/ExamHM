package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import enums.CalculatorOperator;

public class CalculatorPage {
	private WebDriver driver;
	private final String PREFIX = "Btn";
	private final String CALC = "Calc";
	private final String DOT = "Dot";
	private final String SIGN = "Sign";
	private final By INPUT = By.id("input");
	
	public CalculatorPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getBinaryResult(double firstParameter, double secondParameter, CalculatorOperator operator) throws Exception {
		insertDoubleNumber(firstParameter);
		typeFromKey(operator.getValue());
		insertDoubleNumber(secondParameter);
		calculate();
		return getResult();
	}

	private String getResult() {
	return driver.findElement(INPUT).getAttribute("value");
	}

	private void calculate() {
		typeFromKey(CALC);
	}

	private void insertDoubleNumber(double number) throws Exception {
		String numberStr = Double.toString(number);
		char[] numberArray = numberStr.toCharArray();
		for(char charInput : numberArray) {
			if(Character.isDigit(charInput))
				typeFromKey(Character.toString(charInput));
			else if (charInput == '.')
				typeFromKey(DOT);
			else if (charInput == '-')
				typeFromKey(SIGN);
			else
				throw new Exception("invalid input");
		}

		}

	private void typeFromKey(String input) {
		String id = String.format("%s%s",PREFIX, input);
		WebElement inputElement = driver.findElement(By.id(id));
		inputElement.click();
	}

	public String getTrigoResult(double input, CalculatorOperator operator) throws Exception {
		typeFromKey(operator.getValue());
		insertDoubleNumber(input);
		calculate();
		return getResult();
	}	

}
