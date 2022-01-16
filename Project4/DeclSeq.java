class DeclSeq {
    Decl decl;
    FuncDecl fd;
    DeclSeq ds;

    void parse(Scanner S){
        if(S.currentToken() == Core.ID){
            fd = new FuncDecl();
            fd.parse(S);
        }else{
            decl = new Decl();
            decl.parse(S);
        }
        if(S.currentToken() != Core.BEGIN){
            ds = new DeclSeq();
            ds.parse(S);
        }
    }

    void semantic(Parser P){
        if(fd != null){
            fd.semantic(P);
        }else{
            decl.semantic(P);
        }
        if(ds != null){
            ds.semantic(P);
        }
    }
    
    void print(int indent){
        if(fd != null){
            fd.print(indent);
        }else{
            decl.print(indent);
        }
        if(ds != null){
            ds.print(indent);
        }
    }

    void execute(){
        if(fd != null){
            fd.execute();
        }else{
            decl.execute();
        }
        if(ds != null){
            ds.execute();
        }
    }
}
