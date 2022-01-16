class Input {
    Id id;

    void parse(Scanner S) {
		S.nextToken();
		id = new Id();
		id.parse(S);
		S.expectedToken(Core.SEMICOLON);
		S.nextToken();
	}
	
	void semantic(Parser P) {
		id.semantic(P, 1);
	}
	
	void print(int indent) {
		for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.print("input ");
		id.print();
		System.out.println(";");
	}

	void execute(){
		id.setValue(Executor.getInputData());
	}
}
