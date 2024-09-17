import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;



import java.util.List;
public class MainAutomato{
    public static void main(String[] args){
        try{
        File arquivo = new File(args[0]);
        Scanner leitor = new Scanner(arquivo);
        String tudo = "";
        while(leitor.hasNextLine()){
            tudo += leitor.nextLine(); //adiciona tudo do arquivo para a string
            tudo += "\n";
        }
        leitor.close(); //fecha leitor
        System.out.println(tudo); //mostra o conteudo do arquivo
        
        
        Lexico l1 = new Lexico(tudo);
        List<Token> t1 = l1.getTokens();
        for (Token token:t1){
            System.out.println(token);
        }

        }
        catch (FileNotFoundException e){
            throw new RuntimeException("\nArquivo nao encontrado!\nVerifique se o arquivo esta no diretorio correto e se o nome e extensao estao corretos!");
        }
        

    }
}