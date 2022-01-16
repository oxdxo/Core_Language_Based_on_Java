class Cond {
    Cmpr cmpr;
    Cond cond;
    
    void parse(Scanner S){
        if(S.currentToken() == Core.NEGATION){
            S.nextToken();
            S.expectedToken(Core.LPAREN);
            S.nextToken();
            cond = new Cond();
            cond.parse(S);
            S.expectedToken(Core.RPAREN);
            S.nextToken();
        }else{
            cmpr = new Cmpr();
            cmpr.parse(S);
            if(S.currentToken() == Core.OR){
                S.nextToken();
                cond = new Cond();
                cond.parse(S);
            }
        }
    }

    void semantic(Parser P){
        if(cmpr == null){
            cond.semantic(P);
        }else{
            cmpr.semantic(P);
            if(cond != null){
                cond.semantic(P);
            }
        }
    }
    
    void print(){
        if(cmpr != null){
            cmpr.print();
            if(cond != null){
                System.out.print(" or ");
                cond.print();
            }
        }else{
            System.out.print("!(");
            cond.print();
            System.out.print(")");
        }
    }
}
