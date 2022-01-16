import java.util.HashMap;
import java.util.List;

class FuncCall {
    Id id;
    Formals f;

    void parse(Scanner S){
        S.nextToken();
        id = new Id();
        id.parse(S);
        S.expectedToken(Core.LPAREN);
        S.nextToken();
        f = new Formals();
        f.parse(S);
        S.expectedToken(Core.RPAREN);
        S.nextToken();
        S.expectedToken(Core.SEMICOLON);
        S.nextToken();
    }

    void semantic(Parser P){
        if(!P.funcList.containsKey(id.id)){
            System.out.println("ERROR: function name " + id + " hasn't been declared before.");
            System.exit(1);
        }
        FuncDecl funcD = P.funcList.get(id.id);
        List<Id> funcP = funcD.fParam.getAllParamIds();
        List<Id> mainP = f.getAllParamIds();
        if(funcP.size()!= mainP.size()){
            System.out.println("ERROR: the number of arguments for method " + id.id + " in the call is different with the number in the declaration. ");
            System.exit(1);
        }
        for(int i = 0; i < mainP.size(); i++){
            if(!P.checkDeclared(mainP.get(i).id)){
                System.out.println("ERROR: argument " + mainP.get(i).id + " which called method hasn't been declared before.");
                System.exit(1);
            }
        }
        P.pushStackForFunc(funcP, mainP);
        P.allScope.peek().push(new HashMap<String, Core>());
        f.semantic(P);
        P.allScope.pop();
        System.out.print(P.allScope);

    }

    void print(int indent){
        for(int i = 0; i < indent; i ++){
            System.out.print("\t");
        }
        System.out.print("begin ");
        id.print();
        System.out.print("(");
        f.print();
        System.out.println(");");
    }

    void execute(){ 
        FuncDecl funcD = Executor.funcList.get(id.id);
        Formals fParam = funcD.fParam;
        StmtSeq fBody = funcD.ssBody;
        Executor.pushStackForFunc(fParam, f);
        Executor.stackSpace.peek().push(new HashMap<String, CoreVar>());
        fBody.execute();
        Executor.checkDeclaredBeforePopFrame();
        Executor.stackSpace.pop();
    }

}
