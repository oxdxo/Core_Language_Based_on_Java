import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

class Scanner {
	Core current;
	BufferedReader reader;
	StringBuilder token;
	String id = "[a-zA-Z][a-zA-Z0-9]*";

	private static final Map<String, Core> coreSearchStringMap;	
	private static final Map<Character, Core> coreSearchCharMap;

	static {
		coreSearchStringMap = new HashMap<String, Core>();
		coreSearchStringMap.put("program", Core.PROGRAM);
		coreSearchStringMap.put("begin", Core.BEGIN);
		coreSearchStringMap.put("end", Core.END);
		coreSearchStringMap.put("new", Core.NEW);
		coreSearchStringMap.put("define", Core.DEFINE);
		coreSearchStringMap.put("extends", Core.EXTENDS);
		coreSearchStringMap.put("class", Core.CLASS);
		coreSearchStringMap.put("endclass", Core.ENDCLASS);
		coreSearchStringMap.put("int", Core.INT);
		coreSearchStringMap.put("endfunc", Core.ENDFUNC);
		coreSearchStringMap.put("if", Core.IF);
		coreSearchStringMap.put("then", Core.THEN);
		coreSearchStringMap.put("else", Core.ELSE);
		coreSearchStringMap.put("while", Core.WHILE);
		coreSearchStringMap.put("endwhile", Core.ENDWHILE);
		coreSearchStringMap.put("endif", Core.ENDIF);
		coreSearchStringMap.put("input", Core.INPUT);
		coreSearchStringMap.put("output", Core.OUTPUT);
		coreSearchStringMap.put("ref", Core.REF);		
		coreSearchStringMap.put("or", Core.OR);

		coreSearchCharMap = new HashMap<Character, Core>();
		coreSearchCharMap.put(';', Core.SEMICOLON);
		coreSearchCharMap.put('(', Core.LPAREN);
		coreSearchCharMap.put(')', Core.RPAREN);
		coreSearchCharMap.put(',',Core.COMMA);
		coreSearchCharMap.put('=', Core.ASSIGN);
		coreSearchCharMap.put('!', Core.NEGATION);
		coreSearchCharMap.put('<', Core.LESS);
		coreSearchCharMap.put('+', Core.ADD);
		coreSearchCharMap.put('-', Core.SUB);
		coreSearchCharMap.put('*', Core.MULT);
	}

	// Constructor should open the file and find the first token
	Scanner(String filename) {
		try {
            this.reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Cannot Find File "+ filename + ".");
        }
		this.nextToken();		
	}

	// nextToken should advance the scanner to the next token
	public void nextToken() {
		try{
			// Get the first char
			int input = this.reader.read();
			this.token = new StringBuilder();

			// Handle with white space
			while(Character.isWhitespace(input) && input != -1){
				input = this.reader.read();
			}

			if(input == -1){
				this.current = Core.EOF;
			}else{
				// Change the input to character
				char c = (char) input;
				// Hanle Specials
				if(!Character.isLetter(c) && !Character.isDigit(c)){
					// Handle '==' and '<='
					this.reader.mark(1);
					char next = (char)this.reader.read();
					if(c == '=' && next == '='){
						this.current = Core.EQUAL;
					}else if(c == '<' && next == '='){
						this.current = Core.LESSEQUAL;
					}else{
						this.reader.reset();
						// Handle the rest Specials
						if(coreSearchCharMap.containsKey(c)){
							this.current = coreSearchCharMap.get(c);
						}else{
							// Handle invalid input
							this.token.append(c);
							throw new Exception();
						}
					}
				}else{
					boolean continued = true;
					// Read the continued digit and build the string
					if(Character.isDigit(c)){
						while(continued){
							this.token.append(c);
							this.reader.mark(1);
							input = this.reader.read();
							c = (char) input;
							if(input == -1 || !Character.isDigit(c)){
								continued = false;
							}
							if(!continued){
								this.reader.reset();
							}
						}
					}
					// Read the continued letters and digits, and build the string
					else if (Character.isLetter(c)){ 
						while(continued){
							this.token.append(c);
							this.reader.mark(1);
							input = this.reader.read();
							c = (char) input;
							if(input == -1 || !Character.isLetterOrDigit(c)){
								continued = false;
							}
							if(!continued){
								this.reader.reset();
							}
						}
					}

					String s = this.token.toString();
					// Handle keywords
					if(coreSearchStringMap.containsKey(s)){
						this.current = coreSearchStringMap.get(s);
					}// Handle ID
					else if(s.matches(this.id)){
						this.current = Core.ID;
					}// Handle constants
					else{
						int i = Integer.parseInt(s);
						if(i < 1024){
							this.current = Core.CONST;
						}
					}
				}

			}

		} catch(Exception e){
			System.out.println("ERROR: Invalid Input " + this.token.toString());
			this.current = Core.ERROR;
		}
	}

	// currentToken should return the current token
	public Core currentToken() {
		return this.current;
	}

	// If the current token is ID, return the string value of the identifier
	// Otherwise, return value does not matter
	public String getID() {
		return this.token.toString();
	}

	// If the current token is CONST, return the numerical value of the constant
	// Otherwise, return value does not matter
	public int getCONST() {
		return Integer.parseInt(this.token.toString());
	}

}