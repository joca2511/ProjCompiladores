import java.text.CharacterIterator;
public class SimbUnit extends AFD{
    @Override
    public Token evaluate(CharacterIterator code){
        switch(code.current()){
            case '[':
                code.next();
                return new Token("[","CoA");
            case ']':
                code.next();
                return new Token("]","CoF");
            case '(':
                code.next();
                return new Token("(","PA");
            case ')':
                code.next();
                return new Token(")","PF");
            case '{':
                code.next();
                return new Token("{","ChA");

            case '}':
                code.next();
                return new Token("}","ChF");

            case ';':
                code.next();
                return new Token(";","EL");
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
            case ',':
                code.next();
                return new Token(",","COMMA");

            default:
                return null;
        }
        
    }
}