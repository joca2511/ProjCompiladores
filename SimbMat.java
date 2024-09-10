import java.text.CharacterIterator;
public class SimbMat extends AFD{
    @Override
    public Token evaluate(CharacterIterator code){
        switch(code.current()){
            case '+':
                code.next();
                return new Token("+","PLUS");
            case '-':
                code.next();
                return new Token("-","MINUS");
            case '*':
                code.next();
                return new Token("*","MULTIPLY");
            case '/':
                code.next();
                return new Token("/","DIVIDE");
            case '(':
                code.next();
                return new Token("(","PA");

            case ')':
                code.next();
                return new Token(")","PF");

            default:
                return null;
        }
        
    }
}