class DeclClass {
    IdList idList;

    void parse(Scanner S){
       S.expectedToken(Core.REF);
       S.nextToken();
       idList = new IdList();
       idList.parse(S);
       S.expectedToken(Core.SEMICOLON);
       S.nextToken();
    }

    void semantic(Parser P){
        idList.semantic(P, Core.REF);
    }

    void print(int indent){
        for(int i = 0; i < indent; i++){
            System.out.print("\t");
        }
        System.out.print("ref ");
        idList.print();
        System.out.println(";");
    }

    void execute(){
        idList.execute(Core.REF);
    }
}
