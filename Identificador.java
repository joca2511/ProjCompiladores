import java.text.CharacterIterator;
public class Identificador extends AFD {
    @Override
    public Token evaluate(CharacterIterator code){
        String[] reservadas = new String[] {"por","enquanto","se","se caso","senao","verdade","falso","int","float"};
        if (Character.isLetter(code.current())){
            String identificador = readLetter(code);
            if (endLetter(code)){
                for (int i = 0; i < reservadas.length; ++i){
                    if (identificador.equals(reservadas[i])){
                        return new Token(identificador,"RESERVADA");
                    }
                }
                return new Token(identificador,"ID");
            }
        }
        return null;
    }
    private String readLetter(CharacterIterator code){
        String identificador = "";
        
        while (Character.isLetter(code.current()) || code.current() == '_'){
            identificador+= code.current();
            code.next();
        }
        return identificador;
    }
    private boolean endLetter(CharacterIterator code){
        
        return code.current() == ' ' ||
        code.current() == '+'||
        code.current() == '-'||
        code.current() == '/'||
        code.current() == '*'||
        code.current() == '('||
        code.current() == ')'||
        code.current() == '{'||
        code.current() == '}'||
        code.current() == '['||
        code.current() == ']'||
        code.current() == ','||
        code.current() == ';'||
        code.current() == '\n'||
        code.current() == CharacterIterator.DONE;
    }
}
