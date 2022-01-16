import java.util.HashMap;
import java.util.Stack;

class Parser {
    public Stack<HashMap<String, Core>> allScope;

    // return true if the name of variabloe has been declared before.
    boolean checkDeclared(String variable){
        Stack<HashMap<String, Core>> copy = new Stack<HashMap<String, Core>>();
        boolean result = false;
        while(allScope.size() != 0){
            HashMap<String, Core> scope = allScope.pop();
            if(scope.containsKey(variable)){
                result = true;
            }
            copy.push(scope);
        }
        while(copy.size() != 0){
            HashMap<String, Core> scope = copy.pop();
            allScope.push(scope);
        }
        return result;
    }

    // return true if the name of variable has already been declared in current scope
    boolean checkDoubleDeclared(String variable){
        boolean result = false;
        if(allScope.size() != 0){
            if(allScope.peek().containsKey(variable)){
                result = true;
            }
        }
        return result;
    }

    // return Core.Int or Core.REF for the name of variable, and return Core.ERROR if not found
    Core checkType(String variable){
        Core result = Core.ERROR;
        if(allScope.size() != 0){
            HashMap<String, Core> scope = allScope.pop();
			if (scope.containsKey(variable)) {
				result = scope.get(variable);
			} else {
				result = checkType(variable);
			}
			allScope.push(scope);
        }
        return result;
    }
}
