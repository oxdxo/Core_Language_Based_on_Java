class Assign {
    int type;
    Id id;
    Id idR;
    Expr expr;

    void parse(Scanner S){
        id = new Id();
        id.parse(S);
        S.expectedToken(Core.ASSIGN);
        S.nextToken();
        if(S.currentToken() == Core.NEW){
            type = 0;
            S.nextToken();
        }else if(S.currentToken() == Core.REF){
            type = 1;
            S.nextToken();
            idR = new Id();
            idR.parse(S);
        }else{
            type = 2;
            expr = new Expr();
            expr.parse(S);
        }
        S.expectedToken(Core.SEMICOLON);
        S.nextToken();
    }

    void semantic(Parser P){
        id.semantic(P, 1);
        if(type == 0){
            if(P.checkType(id.id) != Core.REF){
                System.out.println("ERROR: The " + id.id + " here needs to have been declared as ref, not int. (Assign)");
                System.exit(1);
            }
        }else if(type == 1){
            if(P.checkType(id.id) != Core.REF){
                System.out.println("ERROR: The " + id.id + " here needs to have been declared as ref, not int. (Assign)");
                System.exit(1);
            }
            if(P.checkType(idR.id) != Core.REF){
                System.out.println("ERROR: The " + id.id + " here needs to have been declared as ref, not int. (Assign)");
                System.exit(1);
            }
        }else{
            expr.semantic(P);
        }
    }

    void print(int indent){
        for(int i = 0; i < indent; i++){
            System.out.print("\t");
        }
        id.print();
        System.out.print("=");
        if(type == 0){
            System.out.print("new");
        }else if(type == 1){
            System.out.print("ref ");
            idR.print();
        }else{
            expr.print();
        }
        System.out.println(";");
    }

}

