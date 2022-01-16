class Output {
    Expr expr;

    void parse(Scanner S) {
		S.nextToken();
		expr = new Expr();
		expr.parse(S);
		S.expectedToken(Core.SEMICOLON);
		S.nextToken();
	}
	
	void semantic(Parser P) {
		expr.semantic(P);
	}
	
	void print(int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.print("output ");
		expr.print();
		System.out.println(";");
	}

	void execute(){
		System.out.println(expr.execute());
	}
}
