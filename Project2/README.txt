Name: Xiaodan Zhao.3065

Core.java
    The list of all core that provided by professor.
Scanner.java
    I used the Scanner provided by professor. On top of what we have for Lab1, I put 
    the expectedToken() method in it, to check whether the type of varibale is what 
    we expected to be there.
Main.java
    I fllow the logic that professor said in the class, but I add one Parser variable, 
    in order to pass it in the semantic method. It will first create Scanner and Parser 
    for further use. Then it will create an Prog variable to start the parse, semantic 
    and print process.
Parser.java
    In the parser class file, there is a Stack<HashMap<String, Core>> allScope used to 
    record variables and their types in the correct scope. In the class file, there are 
    three helper methods for the overall parser() method: checkDeclared() which will 
    return true, if the name of variabloe has been declared before, checkDoubleDeclared() 
    which will return true, if the name of  variable has already been declared in current 
    scope, and checkType() which will return variable's Core type.
Prog.java
    In the Prog class file, the program create the parse tree. <prog> is handeled by this 
    Mainly deal with Core.PROGRAM, Core.BEGIN, and Core.END. pass the other value to the 
    corresponded class (Global variable <decl-seq> to DeclSeq and local variable <stmt-seq>
    to StmtSeq) and check for the semantic errors. This class is the root of the parse tree.
    The parse() function will generate a parse tree for the input program, semantic() function 
    will check the semantic error, and then print() function will print the core program 
    from the tree.
DeclSeq.java
    In the DeclSeq class file, the program will process the global variables <decl-seq>. 
    A DeclSeq will be a Decl with/without following DeclSeq. The parse() function will 
    generate a parse tree for the input program, semantic() function will check the semantic
    error, and then print() function will print the core program from the tree.
Decl.java
    Decl will handel <decl> and check whether it is an int or class. It will pass <decl-int>
    to DeclInt, and <decl-class> to DeclClass.The parse() function will generate a parse tree 
    for the input program, semantic() function will check the semantic error, and then print() 
    function will print the core program from the tree.
DeclClass.java
    DeclClass will handel <decl-class> form with ref <id-list>. And then it will pass <id-list>
    to IdList. The parse() function will generate a parse tree for the input program, semantic() 
    function will check the semantic error, and then print() function will print the core program 
    from the tree.
DeclInt.java
    DeclInt will handel <decl-int> form with int <id-list>. And then it will pass <id-list>
    to IdList. The parse() function will generate a parse tree for the input program, semantic() 
    function will check the semantic error, and then print() function will print the core program 
    from the tree.
IdList.java
    IdList will handle <id_list> which formed with id or id <id_list>. It will pass id to Id class.
    The parse() function will generate a parse tree for the input program, semantic() function will
    check the semantic error, and then print() function will print the core program from the tree.
Id.java
    Id will handle <id>. The parse() function will generate a parse tree for the input program, 
    semantic() function will check the semantic error, and then print() function will print the 
    core program from the tree.

StmtSeq.java
    In the StmtSeq class file, the program will process the local variables <stmt-seq>. 
    A StmtSeq will be a Stmt with/without following StmtSeq. The parse() function will 
    generate a parse tree for the input program, semantic() function will check the semantic
    error, and then print() function will print the core program from the tree.
Stmt.java
    In the Stmt class file, the program will process the <stmt>. The <stmt> might be 
    <assign>, <if>, <loop>, <in>, <out> or <decl>. The program will identify the core 
    type and send it to the matching class.  The parse() function will generate a parse 
    tree for the input program, semantic() function will check the semantic error, and 
    then print() function will print the core program from the tree.
Assign.java
    The Assign class file will process the <assign>, which might be id = <expr>, id = new or id = ref Id.
    It will identify the type and pass them to the corresponded class. The parse() function will generate 
    a parse tree for the input program, semantic() function will check the semantic error, and then print() 
    function will print the core program from the tree.
Expr.java
    The Expr class file will process the <expr>, which might be <term>, <term>+<expr> or <term>-<expr>.
    It will identify the type and pass them to the corresponded class. The parse() function will generate 
    a parse tree for the input program, semantic() function will check the semantic error, and then print() 
    function will print the core program from the tree.
Term.java
    The Term class file will process the <term>, which might be <factor>, or <factor>*<term>.
    It will identify the type and pass them to the corresponded class. The parse() function will generate 
    a parse tree for the input program, semantic() function will check the semantic error, and then print() 
    function will print the core program from the tree.
Cmpr.java
    The Cmpr class file will process the <cmpr>, which might be <expr>==<expr>, <expr><<expr> or <expr><=<expr>.
    It will identify the type and pass them to the corresponded class. The parse() function will generate 
    a parse tree for the input program, semantic() function will check the semantic error, and then print() 
    function will print the core program from the tree.
Cond.java
    The Cond class file will process the <cond>, which might be <cmpr>, !(<cond>)or <cmpr>or<cond>.
    It will identify the type and pass them to the corresponded class. The parse() function will generate 
    a parse tree for the input program, semantic() function will check the semantic error, and then print() 
    function will print the core program from the tree.
Factor.jaca
    The Factor class file will process the <factor>, which might be id, const or (expr).
    It will identify the type and pass them to the corresponded class. The parse() function will generate 
    a parse tree for the input program, semantic() function will check the semantic error, and then print() 
    function will print the core program from the tree.
If.java
    The If class file will process the <if>. It will check if, then, endif and else. It will pass <cond>, 
    and <stmt-seq>. During the process it will create a new hashmap in the stack since it is a new scope, but
    after processing, it will pop it out. The parse() function will generate a parse tree for the input program, 
    semantic() function will check the semantic error, and then print() function will print the core program 
    from the tree.
Loop.java
    The Loop class file will process the <loop>. It will check if, then, endif and else. 
    It will pass <cond>, and <stmt-seq>. During the process it will create a new hashmap 
    in the stack since it is a new scope, but after processing, it will pop it out. The 
    parse() function will generate a parse tree for the input program, semantic() function 
    will check the semantic error, and then print() function will print the core program 
    from the tree.
Input.java
    The Input class file will process the <in>. It will form with input id. It will pass 
    id to id class. The parse() function will generate a parse tree for the input program, 
    semantic() function will check the semantic error, and then print() function will print 
    the core program from the tree.
Output.java
    The Output class file will process the <out>. It will form with output <expr>. It 
    will pass expr to Expr class. The parse() function will generate a parse tree for 
    the input program, semantic() function will check the semantic error, and then print() 
    function will print the core program from the tree.

OverallDesign
    The orcerall design is based on a parse tree, whose root is PRAGRAM with two child 
    DeclSeq (global variables) and StmtSeq (local variables). The parse tree will be 
    created by recursive approach, start with PROGRAM and went all the way down to Factor.
    The parse process is to check each token's type and check whether it is what we expected
    to appera at there.

Special Features
    expectedToken(Core expected) was placed under Scanner. Thus, I don't need have Scanner 
    variable under Parser class. 
    For all class, they all have methods called parse(Scanner S), semantic(Parser P), and 
    print(). I kept the consistency.
    Also for id's semantic(), I add an variable called type, which will deside whether 
    we need to check for the whole scopes or only the current scope.

Test the Parser
    I used the file provided by the professor to test my code. And also I print the parser 
    tree out during the process to see whether it work as what I expected.
    I always analize the input and think about the correct parser tree for it before run 
    the program. Then I will check wehter the print out parser tree in each step is what I 
    expected. In this way, I can easily find, which part causing problems.

Remaining bugs
    I didn't find any remaining bugs.
