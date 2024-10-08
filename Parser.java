import java.util.List;
public class Parser {
    List<Token> tokens;
    Token token;
    public Parser(List<Token> tokens){
        this.tokens = tokens;
        //for (Token token:tokens){ //printa todas os lexemas debug
        //    System.out.println(token.getLexema());
        //}

    }
    public Token nextToken(){
        if (tokens.size() > 0){
            //System.out.println("NAO EH NULO!"); //debug

            return tokens.remove(0);
        }
        return null;
        //alternativamente
        //return token.size()>0 ? tokens.remove(0) : null;
    }
    private void erro(String regra){
        System.out.println("Regra "+ regra);
        System.out.println("token invalido "+ token.getLexema());
        System.exit(0);
    }


    public void main(){ //principal! colocar logica de deteccao aqui!
        token = nextToken(); //pega a 1a token
        while (!token.getTipo().equals("EOF")){ //vai pelos tokens ate achar EOF
        
            //System.out.println(token.getLexema()); //debug
            if (token.getLexema().equals("se")){ //caso se
                //System.out.println("ENTROU IF!"); //debug
                se();
                System.out.println("TERMINOU IF! :)"); //debug
                if (token.getTipo().equals("EOF")){
                    System.out.println("EOF IF! :)");
                    
                }

            }
            if (token.getLexema().equals("enquanto")){ //caso enquanto
                //System.out.println("ENTROU ENQUANTO!"); //debug
                enquanto();
                System.out.println("TERMINOU ENQUANTO! :)"); //debug
                if (token.getTipo().equals("EOF")){
                    
                    System.out.println("EOF ENQUANTO! :)");
                }
                

            }
            //else{ //caso de faltar coisa
            //    erro("fim");
            //}
            while(token.getTipo().equals("COMMENT")){ //pula comentarios!
                token = nextToken(); //pega a prox token
            }
            
        }
        System.out.println("EOF!"); //debug

    }
    public boolean enquanto(){ //while = enquanto //PARA FAZER! IMPLEMENTAR WHILE DENTRO DE WHILE!
        //exemplo:
        //enquanto condicao {
        //  expressao }
        if (matchL("enquanto") && condicao() && matchL("{") && expressao() && matchL("}")){
            return true;
        }
        erro("enquanto");
        return false;
    }
    public boolean se(){ //if else = se senao //PARA FAZER! IMPLEMENTAR IF DENTRO DE IF!
        //exemplo:
        // se condicao{
        //      expressao
        //}
        // senao{
        //      expressao
        //}
        
        if (matchL("se")  && condicao() && matchL("{") && expressao() && matchL("}")){
                if (token.getLexema().equals("senao")){ //encontra senao (faz com que senao se torne opcional!)
                    senao();
                }
                return true;
        }

        return false;
    }
    public boolean senao(){ //else separado a fazer!
        if(matchL("senao") && matchL("{") && expressao() && matchL("}")){
            return true;
        }
        erro("senao");
        return false;
    }
    private boolean idounum(){ //retorna se eh id ou num
        if (matchT("ID") || num()){
            return true;
        }
        erro("idounum");
        return false;
    }
    private boolean expressao(){ //retorna se eh uma expressao
        if (matchT("ID") && matchL("=") && idounum()){ //colocar condicao
            return true;
        }
        erro("expressao");
        return false;
    }
    private boolean comparador(){ //retorna se eh um comparador
        if (matchL(">") || matchL("<") || matchL(">=")||matchL("<=") || matchL("!=") || matchL("==")){ 
            return true;
        }
        erro("comparador");
        return false;
    }
    private boolean condicao(){ //retorna se eh uma condicao
        if (idounum() && comparador() && idounum()){
            return true;
        }
        erro("condicao");
        return false;
    }
    private boolean num(){ //retorna se eh um tipo de num
        if (matchT("INT") || matchT("FLOAT")){
            return true;
        }
        erro("num");
        return false;
    }
    private boolean matchL(String lexema){ //retorna se o lexema dado bate com o lexema na token
        //System.out.println("ENTROU"); //debug
        if (token.getLexema().equals(lexema)){
            token = nextToken();
            return true;
        }
        return false;
    }
    private boolean matchT(String tipo){ //retorna se o tipo dado bate com o tipo na token
        if (token.getTipo().equals(tipo)){
            token = nextToken();
            return true;
        }
        return false;
    }
    
}
