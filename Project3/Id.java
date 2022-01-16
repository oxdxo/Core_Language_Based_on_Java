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

    // Declaring variable
    void executeAllocate(Core type) {
        CoreVar c = new CoreVar(type);
        if(Executor.stackSpace.isEmpty()){
            Executor.globalSpace.put(id, c);
        }else{
            Executor.stackSpace.peek().put(id, c);
        }
	}

    // id = new, create a new position in heap
    void newInHeap(){
        Executor.newInHeap(id);
    }

    // id = ref id, pass the reference of the right id to the left id
    void passReference(String rightId){
        CoreVar cL = Executor.getCoreVarFromSpace(id);
        CoreVar cR = Executor.getCoreVarFromSpace(rightId);
        cL.value = cR.value;
    }

    int getValue(){
        return Executor.getValue(id);
    }

    void setValue(int value){
        Executor.setValue(id, value);
    }
}
