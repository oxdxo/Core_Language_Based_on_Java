class Decl {
    DeclInt declInt;
    DeclClass declClass;

    void parse(Scanner S){
        if(S.currentToken() == Core.INT){
            declInt = new DeclInt();
            declInt.parse(S);
        }else{
            declClass = new DeclClass();
            declClass.parse(S);
        }
    }

    void semantic(Parser P){
        if(declInt != null){
            declInt.semantic(P);
        }else{
            declClass.semantic(P);
        }
    }

    void print(int indent){
        if(declInt != null){
            declInt.print(indent);
        }else{
            declClass.print(indent);
        }
    } 
}
