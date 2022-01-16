import java.util.HashMap;
import java.util.List;
import java.util.Stack;

class Parser {
    public Stack<Stack<HashMap<String, Core>>> allScope;
    public HashMap<String, FuncDecl> funcList;

    // return true if the name of variabloe has been declared before.
    boolean checkDeclared(String variable){
        Stack<HashMap<String, Core>> copy = new Stack<HashMap<String, Core>>();
        boolean result = false;
        while(allScope.peek().size() != 0){
            HashMap<String, Core> scope = allScope.peek().pop();
            if(scope.containsKey(variable)){
                result = true;
            }
            copy.push(scope);
        }
        while(copy.size() != 0){
            HashMap<String, Core> scope = copy.pop();
            allScope.peek().push(scope);
        }
        return result;
    }

    // return true if the name of variable has already been declared in current scope
    boolean checkDoubleDeclared(String variable){
        boolean result = false;
        if(allScope.peek().size() != 0){
            if(allScope.peek().peek().containsKey(variable)){
                result = true;
            }
        }
        return result;
    }

    // return Core.Int or Core.REF for the name of variable, and return Core.ERROR if not found
    Core checkType(String variable){
        Core result = Core.ERROR;
        if(allScope.peek().size() != 0){
            HashMap<String, Core> scope = allScope.peek().pop();
			if (scope.containsKey(variable)) {
				result = scope.get(variable);
			} else {
				result = checkType(variable);
			}
			allScope.peek().push(scope);
        }
        return result;
    }

    void pushStackForFunc(List<Id> funcP, List<Id> mainP){
        Stack<HashMap<String, Core>> funcStack = new Stack<HashMap<String, Core>>();
        funcStack.push(new HashMap<String, Core>());
        for(int i = 0; i < funcP.size(); i ++){
            funcStack.peek().put(funcP.get(i).id, Core.REF);
        }
        allScope.push(funcStack);
    }
}
