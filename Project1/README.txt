Name: Xiaodan Zhao.3065
Core.java
    The list of all core that provided by professor.
Main.java
    The main method provided by professer. No change has been made. 
Scanner.java
    The scanner implementation. I created two map to cantain all things in core file.
    The map save the possible input as key, and the maching core value as value.
    Then core current to save the currentToken, and token as a string. 
    I used BufferedReader and FileReader to read the input, since we cannot use java.io.scanner,
    and the BufferedReader is the only method I know to read input.
    All the processing is in the nextToken() method. 
    Methods currentToken(), getID(), and getCONST() just used to return the corrent thing.