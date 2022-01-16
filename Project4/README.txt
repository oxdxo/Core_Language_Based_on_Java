Name: Xiaodan Zhao.3065

Core.java
    The list of all core that provided by professor.
Main.java
    Declare Sacnner, Parser, and Program, to call the parse() semantic(), print() add execute() functions.
Executor.java
    I begin with my code from lab3. Since we need to handle the function definitions and function calls,
    I change the variable to Stack of stack of hashmap. The first layer of stack used to handle the function
    call, and the stack of hashmap is same with what we have in lab3.
    More than that, I add a HashMao<String, FuncDecl> variable to handle all defined functions. The string
    (the key) will be the function name, and the FuncDecl(value) is the code of function. In this way, we 
    can manage all function to check the body, and repeated names.
    The initialize() function used to new all nesessay global variable.
    The newInHeap() function used to create a new position in the heap and change the value of the 
    variable in the stack to the index of the new position. 
    The getCoreVarFromSpace() function used to search a specific variable in current globalSpace and 
    peek of stackSpace and return the CoreVar of the variable so that we can make change on it. It 
    will search the closest variable in the stackSpace first, and then search the globalSpace.
    The getValue() function will return the current value of a variable stored in space or heap.
    The setValue() function will update the value of the variable stored in the space or heap.
    The getInputData() function will return the next data in the .data file by using scanner.
    The pushStackForFunc() function will get function parameter and parameter used to call this function, and
    create a stack to the store them. It will first get all function parameter, and then get all variables called
    this function. After that it will set the value from the call variable to the function parameter, thus, we
    can go throught the function code, and do normal push and pop like what we have in lab3. After going
    throught the whole function, it will push this new stack to the stackSpace, as a new layer. This stack 
    should be removed after calling the function.
Scanner.java
    I used what I have in lab2.
Parser.java
    Like what we do in executor, I change the allScope to Stack of stack of hashmap to handle the defined 
    function and function calls. In this way, we need to let functions checkDeclared(), checkDeclared(), and 
    checkType() to only check the peek of the allScope, since the peek of allScope is the method that we are
    really working on at this point. 
    The pushStackForFunc() is similar to the pushStackForFunc() in Executor. It used to create new stack for
    the function call.
Prog.java
    parse() will analyze the input, check for the "program", "begin", "end" and "eof" and call parse() for
    DeclSeq and StmtSeq.
    semantic() will initialize all global variables in parser, and call semantic for DeclSeq and StmtSet to 
    check semantic errors.
    print() will print 'program', 'begin', 'end', in format and call print() for DeclSeq and StmtSeq.
    execute() will initialize the Executor and call the execute() for DeclSeq and StmtSeq. 
    It will also push the stack for the 'main' method, push and pop a map for the local space in the main method.
DeclSeq.java
    parse() will check the condition to know whether to call parse for FuncDecl or Decl.
    semantic() will call semantic() for FuncDecl or Decl.
    print() will call print() for FuncDecl or Decl.
    execute() will check the condition, and call execute() for FuncDecl or Decl
Decl.java
    parse() will check the condition, and call parse() for DeclClass and DeclInt
    semantic() will check the condition, and call semantic() for DeclClass and DeclInt
    print() will check the condition, and call print() for DeclClass and DeclInt
    execute() will check the condition, and call execute() for DeclClass and DeclInt
DeclClass.java
    parse() will check for required 'ref' and ';' and call the parse() for IdList
    semantic() will call semantic() for IdList
    print() will print nesessay blank space and 'ref' and ';', and call print() for IdList.
    execute() will call execute() for IdList
DeclInt.java
    parse() will check for required 'int' and ';' and call the parse() for IdList
    semantic() will call semantic() for IdList
    print() will print nesessay blank space and 'int' and ';', and call print() for IdList.
    execute() will call execute() for IdList
FuncDecl.java
    Id is the name of this function, formals are the parameters of teh function, and stmtSeq is the body 
    code of this function.
    parse() will check for required '(', 'ref', ')', 'begin' and 'endfunc' and call the parse() for Formals 
    and StmtSeq.
    semantic will check for the repeated name for this function, and placed it into the hashMap to store it
    for later use.
    print() will print required '(', 'ref', ')', 'begin' and 'endfunc' and call the print() for Formals and
    StmtSeq.
    execute() will placed the function into the Funclist map to store for later call.
FuncCall.java
    Id is the name of the function be called, and f is the variable used to call this method.
    parse() will check for the required '(', ')', and ';' and call parse() for the id and Formals.
    semantic() will check whether this function has been declared before of not, whether the number of 
    varibale used match the number of parameter or not, and whetehr the variables has been declared 
    before or not. It will also push the stack and call the semantic for formals.
    print() will print the '(', ')', and ';' and call print for id and formals.
    execute() will get the function, function parameter, and functin body, then it will pus a stack for the 
    function, let the parameter get corrent value, call by sharing and call the execute() for stmtSeq to 
    run the function and get corrent value.
Formals.java
    Formals will be a list of parameters (id) for the function. 
    parse() will call parse() for the id.
    semantic() will check for the repeated parameter name, and call semantic() for id.
    print() will call print() for id, and print the ',' between id if is needed.
    getAllParamIds() is a helper method to let the user get all parameter for a function.
IdList.java
    execute() will call executeAllocate() for id, and check for the condition
Id.java
    executeAllocate() will declare variable in the corresponding space. It will first check the 
    stackSpace, since it will keep empty untill it push a map in Prog.java. If it is empty, the
    variable will be declared in globalSpace, otherwise, it will be declared in stackSpace.
    passReference() will give the value of rightId to this.id.
StmtSeq.java
    execute() will check the condition, and call execute() for Stmt
Stmt.java
    execute() will check the condition, and call execute() for Assign, If, Loop, Input, Output 
    or Decl.
Assign.java
    execute() will check it is id = new, id = ref id or id = <expr>.
    If it is id = new, it will call newInHeap() to create a new position in heap, and set the value.
    If it is id = ref it, it will call passReference() to copy the stack value of the right variable 
    to the left variable.
    If it is id = <expr>, it will call execute() for expr, and set the value of the expr to id.
Expr.java
    execute() will call execute() for team and return the calculate result.
Term.java
    execute() will call execute() for factor and return the calculate result.
Factor.jaca
    execute() will return the value of the variable.
Cond.java
    execute() will call execute for cmpr, and return boolean result.
Cmpr.java
    execute() will call execute() for expr to get both value and return the boolean result.
If.java
    execute() will push a new map in the stackSpace, and check basic the logic. It will only go in
    the if when the condition is true, and only go in the else when the else exist and condition is
    false.
Loop.java
    execute() will go into the while loop when condition is true, and push a new map in the stackSpace.
Input.java
    execute() will call getInputData() to read value in .data file and set it to input variable.
Output.java
    execute() will print the value of the output variable.

Overall Design
    The overall structure is based on Project 2 and 3. I added FuncDecl, FuncCall, and Formals class to 
    handle the functions and make some changed in DeclSeq and Stmt class. I start with professor's idea, 
    that useing the stack of stack of hashmap data stucture to handle function defination and function call.
    My understanding for that is the first stack used to store all the function, including the main function,
    then each function is a stack of hashmap. When calling a function, we push a new stack to it, and after 
    calling, we pop that stack out. In this case, we can always keep working on the stack.peek(). Then there
    is a hashMap used to store all global variable, so we can always reach it. There is also a hashmap used to 
    store all function, so we can check for declared function name and find find function body really quick.
    After understanding everything, I start this project with changing the Stack of HashMap to Stack of Stack 
    of Hashmap in Parser and executor, and changing all relative function from stack to stack.peek(), since we 
    want to operate in current stack. After that, I start working on the FuncDecl, FuncCall, and Formals. For
    the parser(), I take care of tokens, check all expected. I check execute errors like repeated function name,
    invalid call, and so on in the semantic() function. Then the execute functin take care of the value.
    For the FuncDecl, we put the function into the hashmap used to stroe all function. For the funcCall, we go
    to the hashmap to find the body of function and change value in the Stack.
  
Special Features
    As the professor suggest, I used a stack of stack of hashmap to store everything, and use a hashmap
    to store all declared function. I called the first layer of stack as the method stack, which will 
    only push and pop with function call (also the 'main method'). The inner side stack of hashmap is just
    what we have for lab3. For the hashmap to store all function, the key is the name and the calue is the 
    body of function. With that we can make sure that all function has unique name, and easily get function's
    body to go throguh.
    For all class, they all have methods called parse(Scanner S), semantic(Parser P), and 
    print(), and execute(). I kept the consistency.
    The getAllParamIds() method in the formals is a special case, I set it as a helpmer method to return all 
    all parameter for a function. In this way we can check for the parameter of function and variable user used
    to call the function.
   
Test the Parser
    The first time I runn the tester.sh, I got evey test case failure. They all showing that they are not 
    expect empty. I found that I forgot the change the code about stack in all method, instead of just push 
    and pop for the stack, we need to work on the peek of stack. After changing that, I rerun the tester, 
    it shows I was in a infinied loop. When I run it separely, and remove several nextToken to force it stop 
    in the middle of progrm, I found that the reason is because I didn't change the parse in Stmt, it will 
    never stop when reaching the ENDFUNC. After I add that in the parse, semantic, print and execute, I 
    rerun the program, it is not in infineted loop again. I also found that it didn't find the error for 
    00.code, which is also because of the endfunc. I rewrite and add error message for that.

Remaining Bugs
    I didn't find any remaining bugus.