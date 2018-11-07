package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CalculatorHistoryPageSection {
	
	private WebDriver driver;
	private final By HISTORY_LOGS = By.xpath("//div[@id='histframe']/ul/li");

	public CalculatorHistoryPageSection(WebDriver driver) {
		this.driver = driver;
	}

	public Object getResult(String formula) {
		List<WebElement>historyLogs = driver.findElements(HISTORY_LOGS);
		for (WebElement historyLog : historyLogs) {
			WebElement formulaElement = historyLog.findElements(By.tagName("p")).get(1);
			WebElement resultElement = historyLog.findElements(By.tagName("p")).get(0);
			if(formulaElement.getAttribute("title").equals(formula))
				return resultElement.getAttribute("title");
		}
		return "";

	}

}
