class StmtSeq{
    Stmt s;
    StmtSeq ss;

    void parse(Scanner S){
       s = new Stmt();
       s.parse(S);
       if((S.currentToken() != Core.END) && (S.currentToken() != Core.ENDIF) && 
        (S.currentToken() != Core.ENDWHILE) && (S.currentToken() != Core.ELSE) && 
        (S.currentToken() != Core.ENDFUNC)){
           ss = new StmtSeq();
           ss.parse(S);
       }

    }

    void semantic(Parser P){
        s.semantic(P);
        if(ss != null){
            ss.semantic(P);
        }
    }
    
    void print(int indent){
        s.print(indent);
		if (ss != null) {
			ss.print(indent);
		}
    }

    void execute(){
        s.execute();
        if(ss != null){
            ss.execute();
        }
    }
}