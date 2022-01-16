class Stmt {
    Assign assign;
    If i;
    Loop loop;
    Input in;
    Output out;
    Decl decl;

    void parse(Scanner S){
        if (S.currentToken() == Core.ID) {
			assign = new Assign();
            assign.parse(S);
		}else if(S.currentToken() == Core.IF){
            i = new If();
            i.parse(S);
        }else if(S.currentToken() == Core.WHILE){
            loop = new Loop();
            loop.parse(S);
        }else if(S.currentToken() == Core.INPUT){
            in = new Input();
            in.parse(S);
        }else if(S.currentToken() == Core.OUTPUT){
            out = new Output();
            out.parse(S);
        }else if(S.currentToken() == Core.INT || S.currentToken() == Core.REF){
            decl = new Decl();
            decl.parse(S);
        }else{
            System.out.println("ERROR: " + S.currentToken() + " cannot start the statement.");
        }
     }
 
     void semantic(Parser P){
        if(assign != null){
            assign.semantic(P);
        }else if(i != null){
            i.semantic(P);
        }else if(loop != null){
            loop.semantic(P);
        }else if(in != null){
            in.semantic(P);
        }else if(out != null){
            out.semantic(P);
        }else if(decl != null){
            decl.semantic(P);
        }
     }

     void print(int indent){
        if(assign != null){
            assign.print(indent);
        }else if(i != null){
            i.print(indent);
        }else if(loop != null){
            loop.print(indent);
        }else if(in != null){
            in.print(indent);
        }else if(out != null){
            out.print(indent);
        }else if(decl != null){
            decl.print(indent);
        }

     }
}
