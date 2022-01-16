class Expr {
    Term term;
    Expr expr;
    int type;

    void parse(Scanner S){
        term = new Term();
        term.parse(S);
        if(S.currentToken() == Core.ADD){
            type = 0;
        }else if (S.currentToken() == Core.SUB){
            type = 1;
        }else{
            type = 2;
        }
        if(type != 2){
            S.nextToken();
            expr = new Expr();
            expr.parse(S);
        }
    }

    void semantic(Parser P) {
        term.semantic(P);
        if(expr != null){
            expr.semantic(P);
        }
	}

    void print(){
        term.print();
        if(type == 0){
            System.out.print("+");
            expr.print();
        }else if(type == 1){
            System.out.print("-");
            expr.print();
        }
    }
}
