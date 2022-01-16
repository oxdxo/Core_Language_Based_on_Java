import java.util.ArrayList;
import java.util.List;

class Formals {
    Id id;
    Formals f;

    void parse(Scanner S){
        id = new Id();
        id.parse(S);
        if(S.currentToken() == Core.COMMA){
            S.nextToken();
            f = new Formals();
            f.parse(S);
        }
    }

    void semantic(Parser P){
        id.semantic(P, 2);
        P.allScope.peek().peek().put(id.id, Core.REF);
        if(f != null){
            f.semantic(P);
        }
    }

    void print(){
        id.print();
        if(f != null){
            System.out.print(",");
            f.print();
        }
    }

    List<Id> getAllParamIds(){
        List<Id> result;
        if(f == null){
            result = new ArrayList<Id>();
        }else{
            result = f.getAllParamIds();
        }
        result.add(id);
        return result;
    }
}
