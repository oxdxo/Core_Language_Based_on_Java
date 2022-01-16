import java.util.HashMap;

class Loop {
    Cond cond;
    StmtSeq ss;

    void parse(Scanner S){
        S.nextToken();
        cond = new Cond();
        cond.parse(S);
        S.expectedToken(Core.BEGIN);
        S.nextToken();
        ss = new StmtSeq();
        ss.parse(S);
        S.expectedToken(Core.ENDWHILE);
        S.nextToken();
    }

    void semantic(Parser P){
        cond.semantic(P);
        P.allScope.peek().push(new HashMap<String, Core>());
        ss.semantic(P);
        P.allScope.pop();
    }

    void print(int indent){
        for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.print("while ");
		cond.print();
		System.out.println(" begin");
		ss.print(indent + 1);
		for (int i = 0; i < indent; i++) {
			System.out.print("\t");
		}
		System.out.println("endwhile");
    }

    void execute(){
        while(cond.execute()){
            Executor.stackSpace.peek().push(new HashMap<String, CoreVar>());
            ss.execute();
            Executor.checkDeclaredBeforePopLocal();
            Executor.stackSpace.peek().pop();
        }
    }
}
