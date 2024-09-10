import java.text.CharacterIterator;
public class Identificador extends AFD {
    @Override
    public Token evaluate(CharacterIterator code){

        if (Character.isLetter(code.current())){
            String identificador = readLetter(code);
            if (endLetter(code)){
                return new Token(identificador,"ID");
            }
        }
        return null;
    }
    private String readLetter(CharacterIterator code){
        String identificador = "";
        
        while (Character.isLetter(code.current())){
            identificador+= code.current();
            code.next();
        }
        return identificador;
    }
    private boolean endLetter(CharacterIterator code){
        
        return code.current() == ' ' ||
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
