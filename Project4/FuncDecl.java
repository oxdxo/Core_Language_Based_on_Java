class FuncDecl {
    Id id;
    Formals fParam;
    StmtSeq ssBody;

    void parse(Scanner S){
        id = new Id();
        id.parse(S);
        S.expectedToken(Core.LPAREN);
        S.nextToken();
        S.expectedToken(Core.REF);
        S.nextToken();
        fParam = new Formals();
        fParam.parse(S);
        S.expectedToken(Core.RPAREN);
        S.nextToken();
        S.expectedToken(Core.BEGIN);
        S.nextToken();
        ssBody = new StmtSeq();
        ssBody.parse(S);
        S.expectedToken(Core.ENDFUNC);
        S.nextToken();
    }

    void semantic(Parser P){
        if(P.funcList.containsKey(id.id)){
            System.out.println("ERROR: function name " + id.id + " has been declared before. The function name need to be a unique name. (FuncDecl)");
            System.exit(1);
        }else{
            P.funcList.put(id.id, this);
        }
    }

    void print(int indent){
        for(int i = 0; i < indent; i++){
            System.out.print("\t");
        }
        id.print();
        System.out.print("(ref ");
        fParam.print();
        System.out.println(") begin");
        ssBody.print(indent + 1);
        for(int i = 0; i < indent; i++){
            System.out.print("\t");
        }
        System.out.println("endfunc");
    }

    void execute(){
        Executor.funcList.put(id.id, this);
    }
}
