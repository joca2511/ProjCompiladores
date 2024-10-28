import java.util.List;
public class Parser {
    List<Token> tokens;
    Token token;
    public Parser(List<Token> tokens){
        this.tokens = tokens;
        this.token = nextToken(); //pega a 1a token
        //for (Token token:tokens){ //printa todas os lexemas debug
        //    System.out.println(token.getLexema());
        //}

    }
    public Token nextToken(){
        if (tokens.size() > 0){
            //System.out.println("NAO EH NULO!"); //debug
            Token aux = tokens.remove(0);
            //System.out.println(aux);
            return aux;
        }
        return null;
        //alternativamente
        //return token.size()>0 ? tokens.remove(0) : null;
    }
    private void erro(String regra){
        System.out.println("Regra "+ regra);
        if (regra.equals("main")){
            System.out.println("FALTOU MAIN!");
        }
        else{
            System.out.println("token invalido "+ token.getLexema());
        }
        System.exit(0);
    }
    public boolean deteccao(){ //detecta qual caso deve entrar!
        while(token.getTipo().equals("COMMENT")){ //pula comentarios!
            token = nextToken(); //pega a prox token
        }
        
        if (token.getLexema().equals("se")){ //caso se
            System.out.println("ENTROU SE!"); //debug
            se();
            System.out.println("TERMINOU SE! :)"); //debug
            if (token.getTipo().equals("EOF")){
                System.out.println("EOF SE!");
                
            }
            

        }
        else if (token.getLexema().equals("enquanto")){ //caso enquanto
            System.out.println("ENTROU ENQUANTO!"); //debug
            enquanto();
            System.out.println("TERMINOU ENQUANTO! :)"); //debug
            if (token.getTipo().equals("EOF")){
                
                System.out.println("EOF ENQUANTO!");
            }
            
            

        }
        else if (token.getLexema().equals("por")){ //caso por
            System.out.println("ENTROU POR!"); //debug
            por();
            System.out.println("TERMINOU POR :)"); //debug
            if (token.getTipo().equals("EOF")){
                System.out.println("EOF POR!"); //debug

            }
            
        }
        else if (token.getTipo().equals("ID")){ //caso onde comando comeca com ID ex: var = 2
            System.out.println("ENTROU EXPRESSAO!"); //debug
            atribuicao();
            System.out.println("TERMINOU EXPRESSAO! :)");
            if (token.getTipo().equals("EOF")){
                System.out.println("EOF EXPRESSAO!");
            }
            
        }
        else if (tipo()){ //caso de declaracao ex: bool foi = verdade
            System.out.println("ENTROU DECLARACAO!");
            declaracao();
            System.out.println("TERMINOU DECLARACAO! :)");
            if (token.getTipo().equals("EOF")){
                System.out.println("EOF DECLARACAO!");
            }

        }
        else if (token.getTipo().equals("EOF")){ //caso chegou no fim, EOF! (possivelmente caso de erro?)
            System.out.println("ENTROU CASO EOF!");
            return true;
        }
        else{ //caso nao entre em nenhum ja feito, vai pra o prox token! Caso de erro (estou deixando passar por agora para facilitar debuggagem!)
            System.out.println("POSSIVEL PROBLEMA! NAO ENTROU "+token); //debug
            token = nextToken();
        }
        return true;
    }
    public boolean main(){ //principal! logica comecao aqui!
        while(token.getLexema().equals("incluir")){ //inclusoes sao no comeco do codigo!
            token = nextToken();
            System.out.println("ENTROU INCLUIR!");
            incluir();
            System.out.println("TERMINOU INCLUIR! :)");
        }
        System.out.println("ACABOU INCLUSOES!"); //debug
        //PROGRAMA COMECA AQUI!!!
        if (matchL("main")){ //main logo apos inclusoes!
            while (!token.getTipo().equals("EOF")){ //vai pelos tokens ate achar EOF!
                deteccao();
            }
            System.out.println("FORA DO WHILE! EOF!"); //debug
            return true; //nao utilizado, mas ta aqui pra fazer companhia :)
        }
        erro("main"); //erro dado quando há falta de main!
        return false; //nao utilizado, mas ta aqui pra fazer companhia :)

    }

    public boolean bloco(){ //bloco! tipo o main, mas para quando ve }!
        
        while (!token.getLexema().equals("}")){ //vai pelos tokens ate achar fechamento do bloco!
            deteccao();
        }
        System.out.println("ACABOU BLOCO!"); //debug
        return true; //utilizado na recursao, para saber se deu certo!

    }
    public boolean incluir(){ //a fazer
        if (matchT("ID") && matchL(".") && matchL("h")){
            return true;
        }
        erro("incluir");
        return false;
    }
    public boolean saida(){ //a fazer

        return true;
    }
    public boolean entrada(){ //a fazer
        return true;
    }


    

    public boolean declaracao(){ //indica se ha uma declaracao ex: bool foi = true //ja foi confirmado o tipo
        if (matchT("ID")){
            if (matchL("=") && (verdadefalso() || num())){
                return true;
            }
            return true; //caso tipo var, onde nao eh declarado o valor inicial!
        }
        erro("declaracao");
        return false;
    }

    public boolean enquanto(){ //while = enquanto 
        //exemplo:
        //enquanto condicao {
        //  bloco 
        //}
        if (matchL("enquanto") && condicao() && matchL("{") && bloco() && matchL("}")){
            return true;
        }
        erro("enquanto");
        return false;
    }

    public boolean por(){ //for = por 
        //exemplo:
        // por x x<2 + {
        // bloco
        //}
        if (matchL("por") && matchT("ID") && condicao() && (matchL("+") || matchL("-")) && matchL("{") && bloco() && matchL("}")){
            return true;
        }
        erro("por");
        return false;
    }

    public boolean se(){ //if else = se senao 
        //exemplo:
        // se condicao{
        //      bloco
        //}
        // senao{
        //      bloco
        //}
        
        if (matchL("se")  && condicao() && matchL("{") && bloco() && matchL("}")){
                if (token.getLexema().equals("senao")){ //encontra senao (faz com que senao se torne opcional!)
                    senao();
                }
                return true;
        }

        return false;
    }

    public boolean senao(){ //else separado!
        if(matchL("senao") && matchL("{") && bloco() && matchL("}")){
            return true;
        }
        erro("senao");
        return false;
    }

    private boolean idounum(){ //retorna se eh id ou num
        if (matchT("ID") || num()){
            return true;
        }
        return false;
    }

    private boolean atribuicao(){ //retorna se eh uma atribuicao ( id = idounum)
        if (matchT("ID") && matchL("=") && Eexpressao()){ 
            return true;
        }
        erro("atribuicao");
        return false;
    }
    // EXPRESSOES!!!!!!!!!
    private boolean Eexpressao(){ //retorna se eh uma expressao matematica ex: (10+2)/3
        if(Texpressao() && Elinha()){
            return true;
        }
        return false;
        
    }
    private boolean Elinha(){
        if (matchL("+") || matchL("-")){
            Texpressao();
            Elinha();
            
            
        }
        return true; //caso E
    }
    private boolean Texpressao(){ //continuacao de expressao
        if (Fexpressao() && Tlinha()){
            return true;
        }
        return false;

    }
    private boolean Tlinha(){
        if (matchL("*") ||matchL("/")){
            Fexpressao();
            Tlinha();
                
                
            
        }
        return true; //caso E
    }
    private boolean Fexpressao(){
        if (idounum()){
            return true;
        }
        else if (matchL("(")){
            Eexpressao();
            if(matchL(")")){
                return true;
            }
            return false; //parenteses nao fechados
        }
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
        return false;
    }

    private boolean verdadefalso(){
        if (matchT("FALSE") || matchT("TRUE")){
            return true;
        }
        return false;
    }

    private boolean simbcalc(){
        if (matchL("+") || matchL("-") || matchL("/") || matchL("*")){
            return true;
        }
        return false;
    }

    private boolean tipo(){
        if (matchL("int") || matchL("float") || matchL("bool")){
            return true;
        }
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
