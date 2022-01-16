Name: Xiaodan Zhao.3065

Core.java
    The list of all core that provided by professor.
Main.java
    Declare Sacnner, Parser, and Program, to call the parse() semantic(), print() add execute() functions.
GarbageCollector.java
    The initialize() function used to new all necessary global variables.
    The newReferece() function used to add an additional position to the reference count list. It will also
    increase the totalVariableNumber, and print the necessary gc: information. The order of variable in the
    list is same as the the order of variable in the heap.
    The invrementCounter() function will get the position of id in the heap and increment the corresponding
    counter value in the list.
    The decrementCounter() function will get the position of id in the heap and decrement the corresponding
    counter value in the list. Once the reference count reaches 0,  that position in the heap can be garbage 
    collected, and the method will decrement the totalVariableNumber and print corresponding gc: message.
Executor.java
    The initialize() function used to new all necessary global variables.
    The newInHeap() function used to create a new position in the heap and change the value of the variable 
    in the stack to the index of the new position. 
    The getCoreVarFromSpace() function used to search a specific variable in current globalSpace and peek of 
    stackSpace and return the CoreVar of the variable so that we can make change on it. It will search the 
    closest variable in the stackSpace first, and then search the globalSpace.
    The getValue() function will return the current value of a variable stored in space or heap.
    The setValue() function will update the value of the variable stored in the space or heap.
    The getInputData() function will return the next data in the .data file by using scanner.
    The pushStackForFunc() function will get function parameter and parameter used to call this function, 
    and create a stack to the store them. It will first get all function parameter, and then get all variables 
    called this function. After that it will set the value from the call variable to the function parameter,
    thus, we can go throught the function code, and do normal push and pop like what we have in lab3. After 
    going throught the whole function, it will push this new stack to the stackSpace, as a new layer. This 
    stack should be removed after calling the function. It will alco increment the ref variable in the main 
    program when function make calls.
    The checkDeclaredBeforePopFrame() function will go through all variables store in the newest frame, and
    decrement the necessary reference counter in the list. After calling this method, the user can pop this
    frame out of the stackSpace.
    The checkDeclaredBeforePopLocal() function does the similar thing with the checkDeclaredBeforePopFrame().
    But the checkDeclaredBeforePopLocal() will only check the hashMap which is the local part.
Scanner.java
    I used what I have in lab2.
Parser.java
    I used what I have in lab4.
Prog.java
    parse() will analyze the input, check for the "program", "begin", "end" and "eof" and call parse() for
    DeclSeq and StmtSeq.
    semantic() will initialize all global variables in parser, and call semantic for DeclSeq and StmtSet to 
    check semantic errors.
    print() will print 'program', 'begin', 'end', in format and call print() for DeclSeq and StmtSeq.
    execute() will initialize the Executor and call the execute() for DeclSeq and StmtSeq. 
    It will also push the stack for the 'main' method, push and pop a map for the local space in the main 
    method. It will call checkDeclaredBeforePopFrame() method before pop. And after the pop, which means it
    is the end of this program, it will print all the rest gc: message out to show that they go out of the
    scope of this program.
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
    print() will print necessary blank space and 'ref' and ';', and call print() for IdList.
    execute() will call execute() for IdList
DeclInt.java
    parse() will check for required 'int' and ';' and call the parse() for IdList
    semantic() will call semantic() for IdList
    print() will print necessary blank space and 'int' and ';', and call print() for IdList.
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
    run the function and get corrent value. It will call checkDeclaredBeforePopFrame() method before pop.
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
    passReference() funtion will cope the rightId value to the left id value. It will also handle
    the reference counte. It will increment the counter for rightId. If the left id is not null,
    it will also decrement the rightId.
StmtSeq.java
    execute() will check the condition, and call execute() for Stmt
Stmt.java
    execute() will check the condition, and call execute() for Assign, If, Loop, Input, Output 
    or Decl.
Assign.java
    execute() will check it is id = new, id = ref id or id = <expr>.
    If it is id = new, it will call newInHeap() to create a new position in heap, and set the value.
    It will also call newReferece() function to create a new position in the referenceCounter list in
    the GarbageCollector 
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
    false. It will also call checkDeclaredBeforePopLocal() before pop.
Loop.java
    execute() will go into the while loop when condition is true, and push a new map in the stackSpace.
    It will also call checkDeclaredBeforePopLocal() before pop.
Input.java
    execute() will call getInputData() to read value in .data file and set it to input variable.
Output.java
    execute() will print the value of the output variable.

Overall Design
    The overall structure is based on Project 4. I followed the professor's idea that started with a 
    ArraytList of integers to keep track of the reference counts. I also used a Integer to attract the 
    number of variables. Since I add additional positions to the reference count list as the time the heap 
    grows, the order of variables in the list is similar as the order of variables in the heap. In this 
    case, when we wat to increment or decrement the specific order, we can know to change which value by 
    getting its position in the heap. The gc: message will only be printed when there is a new variable, 
    there is one count reach to zero and when it is the end of the program.
  
Special Features
    I add two new methods in the executor, checkDeclaredBeforePopFrame() and checkDeclaredBeforePopLocal(). 
    They used to check all necessary variables that is REF and need to do decrement. Another thing is the 
    totalVariableNumber Integer varibale in the GarbageCollector, which keep track the total number of the
    variable that still need to count. I used to want to use the length of the lsit, but then I realized that
    cannot handle the situation that the count reach to zero. When it reach to the zero, it still in the list,
    since we didn't make any change in the heap, the position and order cannot change. Thus, we need this 
    totalVariableNumber variable to track it, so we can know whether we need to print thing at the end of the
    program. 
   
Test the Parser
    The first time I runn the tester.sh, I got a lot of difference. After I print lots of information in the
    GarbageCollector, I realized that is because I didn't decrement thing before I pop anything. After I add 
    the method in Executor to handle the decrement, I fixed most of it, except the error from the 5.code. I 
    realized that is because I didn't control the x = ref y while x is not null. I was confused at that point.
    I misunderstand that I need to decrement the reference cout for the left variable and increment for the 
    right. However, for this, since the left is not null, we need to decrement the count for right variable 
    first and then increment, since it lost the reference. 

Remaining Bugs
    I didn't find any remaining bugus.