import java.text.CharacterIterator;
public class Numero extends AFD {
    
    @Override
    public Token evaluate(CharacterIterator code){

        if (Character.isDigit(code.current())){
            String number = readNumber(code);
            if (code.current() == '.'){
                number = number + '.';
                code.next();
                if (Character.isDigit(code.current())){
                    number = number + readNumber(code);
                    if (endNumber(code)){
                        return new Token(number,"FLOAT");
                    }
                }
                
            }
            else if (endNumber(code)){
                
                return new Token(number,"INT");
                
                
            }
            
        }
        return null;
    }
    private String readNumber(CharacterIterator code){
        String number = "";
       
        while (Character.isDigit(code.current())){
            
            number+= code.current();
            code.next();
        }
        
       
        return number;
    }
    private boolean endNumber(CharacterIterator code){
        
        return 
        code.current() == ' ' ||
        code.current() == '+' ||
        code.current() == '-'||
        code.current() == '/' ||
        code.current() == '*'||
        code.current() == '('||
        code.current() == ')'||
        code.current() == '\n' ||
        code.current() == CharacterIterator.DONE;
    }
}
