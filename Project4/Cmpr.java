class Cmpr {
    Expr expr1;
    Expr expr2;
    int type;

    void parse(Scanner S){
        expr1 = new Expr();
        expr1.parse(S);
        if(S.currentToken() == Core.EQUAL){
            type = 0;
        }else if(S.currentToken() == Core.LESS){
            type = 1;
        }else if(S.currentToken() == Core.LESSEQUAL){
            type = 2;
        }else{
            System.out.println("ERROR: " + S.currentToken() + " is not a valid input. Expected ==, < or <=. (Cmpr)");
            System.exit(1);
        }
        S.nextToken();
        expr2 = new Expr();
        expr2.parse(S);
    }

    void semantic(Parser P){
        expr1.semantic(P);
        expr2.semantic(P);
    }

    void print(){
        expr1.print();
        if(type == 0){
            System.out.print("==");
        }else if(type == 1){
            System.out.print("<");
        }else if(type == 2){
            System.out.print("<=");
        }
        expr2.print();
    }

    boolean execute(){
        boolean result = false;
        int iL = expr1.execute();
        int iR = expr2.execute();
        if(type == 0){
            result = iL == iR;
        }else if(type == 1){
            result = iL < iR;
        }else if(type == 2){
            result = iL <= iR;
        }
        return result;
    }
}
