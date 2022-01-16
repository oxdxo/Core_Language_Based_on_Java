import java.util.ArrayList;
import java.util.List;

class GarbageCollecter{
    public static List<Integer> referenceCounter;
    public static Integer totalVariableNumber;

    static void initialize(){
        referenceCounter = new ArrayList<Integer>();
        totalVariableNumber = 0;
    }

    //add new to the reference counter
    static void newReferece(String id){
        totalVariableNumber ++;
        referenceCounter.add(1);
        System.out.println("gc:" + totalVariableNumber);
        //System.out.println("new " + referenceCounter);
    }

    //increase the reference count
    static void incrementCounter(String id){
        //System.out.println("inc "+ referenceCounter);
        CoreVar positionInHeap = Executor.getCoreVarFromSpace(id); 
        if(positionInHeap.value != null){
            int currentValue = referenceCounter.get(positionInHeap.value);
        	referenceCounter.set(positionInHeap.value, currentValue + 1);
            //System.out.println("increment " + id + " " + currentValue + "total " + totalVariableNumber);
        }
    }

    //decrease the reference count
    static void decrementCounter(String id){
        CoreVar positionInHeap = Executor.getCoreVarFromSpace(id); 
        if(positionInHeap.value != null){
            int currentValue = referenceCounter.get(positionInHeap.value);
            // System.out.println("decrement " + id + " " + currentValue);
            
            referenceCounter.set(positionInHeap.value, currentValue - 1);
            // System.out.println("dec " + referenceCounter);
            // After this decrement, this reference count reaches 0
            if(currentValue == 1){
                totalVariableNumber --;
                System.out.println("gc:" + totalVariableNumber);
            }
        }
    }
}