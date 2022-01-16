import java.util.*;

class CoreVar {
    Core type;
    Integer value;

    public CoreVar(Core t){
        type = t;
        if(type == Core.INT){
            value = 0;
        } else{
            value = null;
        }
    }
}

class Executor {

    static HashMap<String, CoreVar> globalSpace;
    // as professor said, used a stack of stack of hashmap to store all stack
    static Stack<Stack<HashMap<String, CoreVar>>> stackSpace;
    static ArrayList<Integer> heapSpace;
    // using a hashMap to store all FuncDecl and name
    static HashMap<String, FuncDecl> funcList;

    static Scanner S;

    static void initialize(Scanner scanner){
        globalSpace = new HashMap<String, CoreVar>();
        stackSpace = new Stack<Stack<HashMap<String, CoreVar>>>(); 
        //stackSpace is an empty stack at this point, need to remember to do stackSpace.push(new HashMap<String, CoreVar>()) later
        heapSpace = new ArrayList<Integer>();
        funcList = new HashMap<String, FuncDecl>();
        S = scanner;    
    }

    static void newInHeap(String id){
        CoreVar c = getCoreVarFromSpace(id);
        if(c.type != Core.REF){
            System.out.println("ERROR: The " + id + " here needs to have been declared as ref. It cannot perform new. (Assign)");
            System.exit(1);
        }
        heapSpace.add(null);
        c.value = heapSpace.size()-1;
    }

    static CoreVar getCoreVarFromSpace(String id){
        CoreVar result = null;
        // find the id from the last in the stackSpace, which means it is the nearest variable in local
        for(int i = stackSpace.peek().size()-1; i >= 0; i--){
            if(stackSpace.peek().get(i).containsKey(id)){
                result = stackSpace.peek().get(i).get(id);
                break;
            }
        }
        // if it is not in the local variable, find it in global variable
        if(result == null){
            result = globalSpace.get(id);
        }
        return result;
    }

    static int getValue(String id){
        CoreVar c = getCoreVarFromSpace(id);
        int result = c.value;
        if(c.type == Core.REF){
            if(result < heapSpace.size()){
                if(heapSpace.get(result) == null){
                    System.out.println("ERROR: The " + id + " cannot be null. (Factor)");
                    System.exit(1);
                }
                result = heapSpace.get(result);
            }else{
                System.out.println("ERROR: The " + id + " has not been allocated in heap. Invalid read. (Factor)");
                System.exit(1);
            }
        }
        return result;
    }

    static void setValue(String id, int value){
        CoreVar c = getCoreVarFromSpace(id);
        if(c.type == Core.REF){
            if(c.value == null){
                System.out.println("ERROR: The " + id + " cannot be null. Invalid write. (Assign)");
                System.exit(1);
            }
            if(c.value < heapSpace.size()){
                heapSpace.set(c.value, value);
            }else{
                System.out.println("ERROR: The position of " + id +" " + c.value + " in the heap is out of bounds. Invalid write. (Assign)");
                System.exit(1);
            }
        }else{
            c.value = value;
        }
    }

    static int getInputData(){
        int result;
        if(S.currentToken() == Core.EOF){
            System.out.println("ERROR: reach the end of the data file.");
            System.exit(1);
        }
        result = S.getCONST();
        S.nextToken();
        return result;
    }

    /* new method for the function stack */
    
    static void pushStackForFunc(Formals funcParam, Formals mainParam){
        List<Id> funcP = funcParam.getAllParamIds();
        List<Id> mainP = mainParam.getAllParamIds();

        Stack<HashMap<String, CoreVar>> funcStack = new Stack<HashMap<String, CoreVar>>();
        funcStack.push(new HashMap<String, CoreVar>());

        for(int i = 0; i < funcP.size(); i ++){
            CoreVar mainValue = new CoreVar(Core.REF);
            mainValue.value = getCoreVarFromSpace(mainP.get(i).id).value;
            funcStack.peek().put(funcP.get(i).id, mainValue);

            //Increment the ref variable in the main program when function make call
            if(getCoreVarFromSpace(mainP.get(i).id).type == Core.REF){
                GarbageCollecter.incrementCounter(mainP.get(i).id);
            }
        }
        stackSpace.push(funcStack);
    }

    /* new method for garbage collector*/
    static void checkDeclaredBeforePopFrame(){
        Stack<HashMap<String, CoreVar>> currentFrame = stackSpace.peek();
		for(int i=0; i<currentFrame.size();i++) {
            for (String id : currentFrame.pop().keySet()) {
                if(getCoreVarFromSpace(id) != null) {
                    //System.out.println("pop frame " + id);
                    CoreVar c = getCoreVarFromSpace(id);
					if (c.type == Core.REF) {
						GarbageCollecter.decrementCounter(id);
					}
				}
			}
		}
    }

    static void checkDeclaredBeforePopLocal(){
        HashMap<String, CoreVar> map = stackSpace.peek().peek();
		for (String id : map.keySet()) {
            //System.out.println("pop local " + id + getCoreVarFromSpace(id).type);
            if (getCoreVarFromSpace(id).type == Core.REF) {
                GarbageCollecter.decrementCounter(id);
            }
        }
    }
}