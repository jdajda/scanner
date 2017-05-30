package pl.edu.agh.mwo;

public class Scanner {

	String text;
	
	static final int STRING_IS_EMPTY = 0;
	static final String WHITESPACE =" ";
	static final String HORIZONTAL_TAB ="\t";
	static final String NEW_LINE ="\n";
	static final String PERCENT_SIGN ="%";
	static final String LEFT_PARENTHESIS ="(";
	static final String RIGHT_PARENTHESIS =")";
	static final String ALL_SPECIAL_SYMBOLS = WHITESPACE+HORIZONTAL_TAB+NEW_LINE+PERCENT_SIGN+LEFT_PARENTHESIS+RIGHT_PARENTHESIS;
	
	public Scanner(String initText) {
		text = initText;
	}

	public String get() {
		String returnText = "";
		if(removeWhiteSpaceAndCommentsFromText())
			returnText = extractSingleTokenFromText();
		return returnText.toLowerCase();
	}
	
	
	private boolean stringIsEmptyOrNull(){
		return text.length() == STRING_IS_EMPTY;
	}
	

	private boolean textSubstringMatchesExpression(String regularExpression){
		if(!stringIsEmptyOrNull())
			return text.substring(0, 1).matches("["+regularExpression+"]");
		else return false;
	}
	
	
	private boolean removeWhiteSpaceAndCommentsFromText(){
		boolean allWhitespaceAndCommentsRemoved = false;
		while (!allWhitespaceAndCommentsRemoved) {
			if (stringIsEmptyOrNull()) {
				return false;
			} 
			else if(textSubstringMatchesExpression(WHITESPACE+HORIZONTAL_TAB+NEW_LINE)) {
				text = text.substring(1);
			} 
			else if (textSubstringMatchesExpression(PERCENT_SIGN)) {
				while (!textSubstringMatchesExpression(NEW_LINE)) {
					text = text.substring(1);
				}
			} 
			else {
				allWhitespaceAndCommentsRemoved = true;
			}
		}
		return true;
	}
	
	private String extractParenthesesFromText(){
		String returnText = "";
		if (textSubstringMatchesExpression(LEFT_PARENTHESIS+RIGHT_PARENTHESIS)) {
			returnText = text.substring(0, 1);
			text = text.substring(1);
		}
		return returnText;
	}
	
	private String extractSingleTokenFromText(){
		String returnText = "";
		returnText = extractParenthesesFromText();
		if(!returnText.contentEquals(LEFT_PARENTHESIS) && !returnText.contentEquals(RIGHT_PARENTHESIS)){
			while (!stringIsEmptyOrNull() && !textSubstringMatchesExpression(ALL_SPECIAL_SYMBOLS)) {
				returnText += text.substring(0, 1);
				text = text.substring(1);
			}
		}
		return returnText;
	}
	
}
