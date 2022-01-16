class Main {
	public static void main(String[] args) {
		// Initialize the scanner with the input file
		Scanner S = new Scanner(args[0]);
		Parser P = new Parser();
		
		Prog root = new Prog();
		
		root.parse(S);
		
		root.semantic(P);
		
		//root.print();

		root.execute(new Scanner(args[1]));
	}
}