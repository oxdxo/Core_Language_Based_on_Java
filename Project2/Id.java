class Id {
    String id;

    void parse(Scanner S){
        S.expectedToken(Core.ID);
        id = S.getID();
        S.nextToken();
    }

    //If type = 1, check declared. If type = 2, check doubly-declared.
    void semantic(Parser P, int type) {
        if(type == 1){
            if(!P.checkDeclared(id)){
                System.out.println("ERROR: " + id + " hasn't been declared before.");
                System.exit(1);
            }
        }else{
            if(P.checkDoubleDeclared(id)){
                System.out.println("ERROR: " + id + " has been doubly declared.");
                System.exit(1);
            }
        }
	}

    void print(){
        System.out.print(id);
    }
}
