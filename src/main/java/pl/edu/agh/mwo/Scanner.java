package pl.edu.agh.mwo;

public class Scanner {

	String text;
	static final int substrinBeginIndex = 0;
	static final int substringEndIndex = 1;
	static final int substringNewStringIndex = 1;
	static final int stringIsEmpty = 0;
	static final String whitespace =" ";
	static final String horizontalTab ="\t";
	static final String newLine ="\n";
	static final String percentSign ="%";
	static final String leftParenthesis ="(";
	static final String rightParenthesis =")";
	
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
			else if(substringMatchesExpression(whitespace+horizontalTab+newLine)) {//substringMatchesExpression("[ \t\n]")
				createNewText();
			} 
			else if (substringMatchesExpression(percentSign)) {
				while (!substringMatchesExpression(newLine)) {
					createNewText();
				}
			} 
			else {
				functionReturnValue = true;
			}
		}

		if (!stringIsEmptyOrNull() && substringMatchesExpression(leftParenthesis+rightParenthesis)) {
			resultText = text.substring(substrinBeginIndex, substringEndIndex);
			createNewText();
		}
		while (!stringIsEmptyOrNull() && !substringMatchesExpression(whitespace+horizontalTab+newLine+percentSign+leftParenthesis+rightParenthesis)) {//"[ \t\n%()]"
			resultText += text.substring(substrinBeginIndex, substringEndIndex);
			createNewText();
		}
		return resultText.toLowerCase();
	}
	
	
	private boolean stringIsEmptyOrNull(){
		return text.length() == stringIsEmpty;
	}
	

	private boolean substringMatchesExpression(String regularExpression){
		return text.substring(substrinBeginIndex, substringEndIndex).matches("["+regularExpression+"]");
	}
	
	private void createNewText(){
		text = text.substring(substringNewStringIndex);
	}
}
