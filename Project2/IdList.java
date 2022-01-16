class IdList{
    Id id;
    IdList idList;

    void parse(Scanner S){
        id = new Id();
        id.parse(S);
        if(S.currentToken() == Core.COMMA){
            S.nextToken();
            idList = new IdList();
            idList.parse(S);
        }
    }
    
    void semantic(Parser P, Core type){
        id.semantic(P, 2);
        P.allScope.peek().put(id.id, type);
        if(idList != null){
            idList.semantic(P, type);
        }
    }

    void print(){
        id.print();
        if(idList != null){
            System.out.print(",");
            idList.print();
        }
    }

}