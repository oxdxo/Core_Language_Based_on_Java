Name: Xiaodan Zhao.3065

Core.java
    The list of all core that provided by professor.
Main.java
    Remove the semantic() and print() function, and add execute()
Executor.java
    I begin this class with professor's frame. The CoreVar is used to store type and current value.
    I add a variable for it, so it can distinguish int type and ref type and initialize the value.
    The initialize function create all need stack, map and heap, and also pass the scanner that used 
    to read the .data file. 
    The newInHeap() function used to create a new position in the heap and change the value of the 
    variable in the stack to the index of the new position. 
    The getCoreVarFromSpace() function used to search a specific variable in current globalSpace and 
    stackSpace and return the CoreVar of the variable so that we can make change on it. It will search
    the closest variable in the stackSpace first, and then search the globalSpace.
    The getValue() function will return the current value of a variable stored in space or heap.
    The setValue() function will update the value of the variable stored in the space or heap.
    The getInputData() function will return the next data in the .data file by using scanner.
Scanner.java
    I used what I have in lab2.
Parser.java
    I used what I have in lab2.
Prog.java
    execute() will initialize the Executor and call the execute() for DeclSeq and StmtSeq. 
    It will also push and pop a map for the localSpace.
DeclSeq.java
    execute() will check the condition, and call execute() for Decl
Decl.java
    execute() will check the condition, and call execute() for DeclClass and DeclInt
DeclClass.java
    execute() will call execute() for IdList
DeclInt.java
    execute() will call execute() for IdList
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

OverallDesign
    The overall structure is based on Project 2. I added execute() function to each class. I started 
    with professor's frame, and all helper method in executor were created when I used them. I started
    from writing the execute() for the Prog and then each class in order. It basically need to allocate
    for new variable, change value when needed, and pass the reference. When changing variable, we need
    to check the closest varibale in local space first, since there might be variable declared in both 
    global and local space. The program will hidde the declaration in global space and execute the local
    space first. After the variable in local space has been poped, the declaration in global space will
    be visible again. When the varible is a ref, we need to update the value in heap, not the real value
    in space. That means we need to consider it as a special case for getValue() and setValue().

Special Features
    All calss have method called execute(). The allocation of the new declared variable is in id, since
    it will only happened on id. Also for the passReference(). The stackSpace and globalSpace will store
    variable's name, type and value. If the variable is a int, the value will be the real value. However,
    if the variable is a ref, the value will be the location of the value pointed in heap. The heap will
    contain the real value of the variable. The new ref will point to a position in heap with null.

Test the Parser
    The first time I runn the tester.sh, I got a lot of question, for example for 7.code. It is basically
    read the value from .data file and output. The problem is it only return 0. Then I found that I forgot
    to set the reading value to the varibale. I only read it, and didn't set it. The second question is the
    failure of assign to a variable that currenly had null value. Since I am using the int as a return
    type for getValue(). If it is a null, it will report error, since int cannot handle null. After I take
    a look and rethinking the whole process, I found the getValue() method will only be called in factor,
    which means for id on the right side. For id on the right side, it cannot be null. Thus, I added a error
    checking.

Remaining Bugs
    I didn't find any remaining bugus.