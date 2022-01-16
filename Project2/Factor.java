 class Factor {
    Id id;
    int constant;
    Expr expr;

    void parse(Scanner S){
        if(S.currentToken() == Core.ID){
            id = new Id();
            id.parse(S);
        }else if(S.currentToken() == Core.CONST){
            constant = S.getCONST();
            S.nextToken();
        }else if(S.currentToken() == Core.LPAREN){
            S.nextToken();
            expr = new Expr();
            expr.parse(S);
            S.expectedToken(Core.RPAREN);
            S.nextToken();
        }else{
            System.out.println("ERROR: " + S.currentToken() + " is invalid. Expected ID, CONST, or (.");
            System.exit(1);
        }
    }

    void semantic(Parser P){
        if(id != null){
            id.semantic(P, 1);
        }else if(expr != null){
            expr.semantic(P);
        }
    }
    void print(){
        if(id != null){
            id.print();
        }else if(expr != null){
            System.out.print("(");
            expr.print();
            System.out.print(")");
        }else{
            System.out.print(constant);
        }
    }

}
