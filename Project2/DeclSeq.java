class DeclSeq {
    Decl decl;
    DeclSeq ds;

    void parse(Scanner S){
        decl = new Decl();
        decl.parse(S);
        if(S.currentToken() != Core.BEGIN){
            ds = new DeclSeq();
            ds.parse(S);
        }
    }

    void semantic(Parser P){
        decl.semantic(P);
        if(ds != null){
            ds.semantic(P);
        }
    }
    
    void print(int indent){
        decl.print(indent);
        if(ds != null){
            ds.print(indent);
        }
    }
}
