class Term {
    Factor factor;
    Term term;

    void parse(Scanner S){
        factor = new Factor();
        factor.parse(S);
        if(S.currentToken() == Core.MULT){
            S.nextToken();
            term = new Term();
            term.parse(S);
        }
    }
    
    void semantic(Parser P){
        factor.semantic(P);
        if(term != null){
            term.semantic(P);
        }
    }
    
    void print(){
        factor.print();
        if(term != null){
            System.out.print("*");
            term.print();
        }
    }
}
