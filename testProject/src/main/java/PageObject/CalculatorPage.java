package PageObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.StringUtilities;

public class CalculatorPage {
	private WebDriver driver;
	private final String PREFIX = "Btn";
	private final String CALC = "Calc";
	private final By INPUT = By.id("input");
	private final By COOKIES = By.name("cookies");
	private final By LOADING = By.className("loading");
	private final String URL = "https://web2.0calc.com/";
	private final String CLEAR = "Clear";
	private WebDriverWait wait;
	
	public CalculatorPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30000);
		this.driver.navigate().to(URL);
		if(!driver.findElements(COOKIES).isEmpty()){
			wait.until(ExpectedConditions.elementToBeClickable(COOKIES));
		}
	    driver.findElement(COOKIES).click();
	}

	public void clearResult() {
		typeFromKey(CLEAR);
	}


	public String getCalculationResult(String formula) throws Exception {
		insertCalculationFormula(formula);
		return  calculateAndGetResult();
	}

	
	private String calculateAndGetResult() {
		calculate();
		return getResult();
	}

	private void typeFromKey(String input) {
		String id = String.format("%s%s",PREFIX, input);
		wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
		WebElement inputElement = driver.findElement(By.id(id));
		inputElement.click();
	}
	
	private String getResult() {
		return driver.findElement(INPUT).getAttribute("value");
		}

	private void calculate() {
			typeFromKey(CALC);
			wait.until(ExpectedConditions.numberOfElementsToBe(LOADING, 0));
		}

	private void insertCalculationFormula (String formula) throws Exception {
		char[] formulaArray = formula.toCharArray();
		StringBuilder alphabeticFunctionBuilder = new StringBuilder();
		Boolean alphabeticFunctionInserted = false;
		for(int i=0; i< formulaArray.length; i++){
			char charInput = formulaArray[i];
			if(Character.isAlphabetic(charInput)) {
				alphabeticFunctionBuilder.append(charInput);
			}
			else{
				String alphabeticFunctionStr = alphabeticFunctionBuilder.toString();
				if(alphabeticFunctionStr != null && !alphabeticFunctionStr.isEmpty()) {
					if(getTrigoFunctions().contains(alphabeticFunctionStr)) {
						typeFromKey(StringUtilities.ReplaceFristLetterToCapital(alphabeticFunctionStr));
						alphabeticFunctionBuilder = new StringBuilder();
						alphabeticFunctionInserted = true;
						alphabeticFunctionStr = "";
						continue;
					}
					else
						throw new Exception("invalid input");
				}								
			if(alphabeticFunctionInserted && charInput == '(')
				alphabeticFunctionInserted = false;
			else if(getSpecialCharacters().containsKey(charInput))
				typeFromKey(getSpecialCharacters().get(charInput));
			else if(Character.isDigit(charInput))
				typeFromKey(Character.toString(charInput));
			}
				
		}			
	}
		
		private static Map<Character, String> getSpecialCharacters()
		{
		    Map<Character, String> specialCharacters = new HashMap<Character, String>();
			specialCharacters.put('.', "Dot");
			specialCharacters.put('-', "Sign");
			specialCharacters.put('(', "ParanL");
			specialCharacters.put(')', "ParanR");		
			specialCharacters.put('-', "Minus");
			specialCharacters.put('+', "Plus");
			specialCharacters.put('*', "Mult");
			return specialCharacters;
		}
		
		private static ArrayList<String> getTrigoFunctions(){
			ArrayList<String> trigoFunctions = new ArrayList<String>();
			trigoFunctions.add("sin");
			trigoFunctions.add("cos");
			trigoFunctions.add("tan");
			return trigoFunctions;
		}
		

		
}
