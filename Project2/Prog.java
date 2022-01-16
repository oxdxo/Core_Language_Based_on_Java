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
        P.allScope = new Stack<HashMap<String, Core>>();
        P.allScope.push(new HashMap<String, Core>());
        if(ds != null){
            ds.semantic(P);
        }
        P.allScope.push(new HashMap<String, Core>());
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
}
