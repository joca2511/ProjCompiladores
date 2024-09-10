import java.util.ArrayList;
import java.util.List;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
public class Lexico {
    private List<Token> tokens;
    private List<AFD> afds;
    private CharacterIterator code;

    public Lexico(String code){
        tokens = new ArrayList<>();
        afds = new ArrayList<>();
        this.code = new StringCharacterIterator(code);
        afds.add(new SimbMat()); //adicionar todos os simbolos do alfabeto!
        

    }
    public void pularEspaco(){
        
        while (code.current() == ' ' || code.current() == '\n') {
           
            code.next();
        }
        
    }
    public List<Token> getTokens(){
        boolean aceito;
        int linha = 0;
        int posultimoln = 0;
        int coluna = 0;
        while(code.current() != CharacterIterator.DONE){ //percorre todo o arquivo, roda até EOF
            aceito = false;
            //pularEspaco();
            while (code.current() == ' ' || code.current() == '\n') { //roda ate chegar no 1o simbolo
                if (code.current() == '\n'){
                    posultimoln = code.getIndex();
                    linha +=1;
                }
                coluna = code.getIndex() - posultimoln;
                code.next();
                
            }
            
            if (code.current() == CharacterIterator.DONE){ //evita casos onde o arquivo nao contem nenhum simbolo do alfabeto
                break;
            }
            for (AFD afd:afds){ //percorre todos os afd
                int posatual = code.getIndex();
                System.out.println(posatual);
                Token t1 = afd.evaluate(code);
                if (t1 != null){ //caso seja valido
                    aceito = true;
                    tokens.add(t1);
                    break; //sai do for
                }
                else{
                    code.setIndex(posatual); //caso o afd atual seja invalido, coloca o leitor de volta na posicao inicial e testa o proximo afd
                }
            }
            if (aceito == false){
                throw new RuntimeException("\nErro! Token Nao Reconhecida!\nToken "+linha+":"+coluna+" >> "+ code.current());
            }

        }
        tokens.add(new Token("$","EOF"));
        return tokens;
    }
    
}
