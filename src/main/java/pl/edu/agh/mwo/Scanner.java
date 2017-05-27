package pl.edu.agh.mwo;

public class Scanner {

	String text;
	static final int substrinBeginIndex = 0;
	static final int substringEndIndex = 1;
	static final int substringNewStringIndex = 1;
	static final int stringIsEmpty = 0;

	public Scanner(String initText) {
		text = initText;
	}

	public String get() {
		String resultText = "";
		boolean functionReturnValue = false;
		while (!functionReturnValue) {
			if (stringIsEmptyOrNull()) {
				return resultText;
			} 
			else if (substringMatchesExpression("[ \t\n]")) {
				text = text.substring(substringNewStringIndex);
			} 
			else if (substringMatchesExpression("%")) {
				while (!substringMatchesExpression("\n")) {
					text = text.substring(substringNewStringIndex);
				}
			} 
			else {
				functionReturnValue = true;
			}
		}

		if (!stringIsEmptyOrNull() && substringMatchesExpression("[()]")) {
			resultText = text.substring(substrinBeginIndex, substringEndIndex);
			text = text.substring(substringNewStringIndex);
		}
		while (!stringIsEmptyOrNull() && !substringMatchesExpression("[ \t\n%()]")) {
			resultText += text.substring(substrinBeginIndex, substringEndIndex);
			text = text.substring(substringNewStringIndex);
		}
		return resultText.toLowerCase();
	}
	
	
	public boolean stringIsEmptyOrNull(){
		return text.length() == stringIsEmpty;
	}
	

	public boolean substringMatchesExpression(String regularExpression){
		return text.substring(substrinBeginIndex, substringEndIndex).matches(regularExpression);
	}
}
