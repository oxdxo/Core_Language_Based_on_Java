import java.util.HashMap;

class If {
    Cond cond;
    StmtSeq ss;
    StmtSeq ssElse;

    void parse(Scanner S){
        S.nextToken();
        cond = new Cond();
        cond.parse(S);
        S.expectedToken(Core.THEN);
        S.nextToken();
        ss = new StmtSeq();
        ss.parse(S);
        if(S.currentToken() == Core.ELSE){
            S.nextToken();
            ssElse = new StmtSeq();
            ssElse.parse(S);
        }
        S.expectedToken(Core.ENDIF);
        S.nextToken();
    }

    void semantic(Parser P){
        cond.semantic(P);
        P.allScope.peek().push(new HashMap<String, Core>());
		ss.semantic(P);
		P.allScope.peek().pop();
		if (ssElse != null) {
			P.allScope.peek().push(new HashMap<String, Core>());
			ssElse.semantic(P);
            P.allScope.peek().pop();
		}
    }

    void print(int indent){
        for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.print("if ");
		cond.print();
		System.out.println(" then");
		ss.print(indent + 1);
		if (ssElse != null) {
			for (int i = 0; i < indent; i++) {
				System.out.print("\t");
			}
			System.out.println("else");
			ssElse.print(indent+1);
		}
		for (int i=0; i<indent; i++) {
			System.out.print("\t");
		}
		System.out.println("endif");
    }

    void execute(){
        Executor.stackSpace.peek().push(new HashMap<String, CoreVar>());
        if(cond.execute()){
            ss.execute();
        }else if(ssElse != null){
            ssElse.execute();
        }
        Executor.checkDeclaredBeforePopLocal();
        Executor.stackSpace.peek().pop();
    }
}
