import java.util.Stack;

import java.util.HashMap;

class Prog {
    DeclSeq ds;
    StmtSeq ss;

    void parse(Scanner S){
        S.expectedToken(Core.PROGRAM);
        S.nextToken();
        if(S.currentToken() != Core.BEGIN){
            ds = new DeclSeq();
            ds.parse(S);
        }
        S.expectedToken(Core.BEGIN);
        S.nextToken();
        ss = new StmtSeq();
        ss.parse(S);
        S.expectedToken(Core.END);
        S.nextToken();
        S.expectedToken(Core.EOF);
    }

    void semantic(Parser P){
        P.allScope = new Stack<Stack<HashMap<String, Core>>>();
        P.funcList = new HashMap<String, FuncDecl>();
        P.allScope.push(new Stack<HashMap<String, Core>>());
        P.allScope.peek().push(new HashMap<String, Core>());
        if(ds != null){
            ds.semantic(P);
        }
        P.allScope.peek().push(new HashMap<String, Core>());
        ss.semantic(P);
        P.allScope.pop();
    }


    void print(){
        System.out.println("program");
        if(ds != null){
            ds.print(1);
        }
        System.out.println("begin");
        ss.print(1);
        System.out.println("end");
    }

    void execute(Scanner S){
        Executor.initialize(S);
        if(ds != null){
            ds.execute();
        }
        Executor.stackSpace.push(new Stack<HashMap<String, CoreVar>>());
        Executor.stackSpace.peek().push(new HashMap<String, CoreVar>());
        ss.execute();
        Executor.checkDeclaredBeforePopFrame();
        Executor.stackSpace.pop();
        if(GarbageCollecter.totalVariableNumber != 0) {
			for(int i=GarbageCollecter.totalVariableNumber - 1; i>=0; i--) {
				System.out.println("gc:" + i);
			}
		}
    } 
    
}
