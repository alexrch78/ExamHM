package enums;

public enum CalculatorOperator {
	ADDITION("Plus"), 
	SUBTRACTION("Minus"), 
	MULTIPLICATION("Mult"),
	SINUS("Sin");
	
	 private String value;    

	  private CalculatorOperator(String value) {
	    this.value = value;
	  }

	  public String getValue() {
	    return value;
	  }
}
