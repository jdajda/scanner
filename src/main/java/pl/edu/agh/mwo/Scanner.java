package pl.edu.agh.mwo;

public class Scanner {

	String text;

	public Scanner(String initText) {
		text = initText;
	}

	public String get() {
		String resultText = "";
		boolean functionReturnValue = false;
		while (!functionReturnValue) {
			if (text.length() == 0) {
				return resultText;
			} 
			else if (text.substring(0, 1).matches("[ \t\n]")) {
				text = text.substring(1);
			} 
			else if (text.substring(0, 1).matches("%")) {
				while (!text.substring(0, 1).matches("\n")) {
					text = text.substring(1);
				}
			} 
			else {
				functionReturnValue = true;
			}
		}

		if (text.length() > 0 && text.substring(0, 1).matches("[()]")) {
			resultText = text.substring(0, 1);
			text = text.substring(1);
		}
		while (text.length() > 0 && !text.substring(0, 1).matches("[ \t\n%()]")) {
			resultText += text.substring(0, 1);
			text = text.substring(1);
		}
		return resultText.toLowerCase();
	}
}
