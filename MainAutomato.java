import java.util.List;
public class MainAutomato{
    public static void main(String[] args){
        String s1 = "+-/*   \n --++\n/ b";
        Lexico l1 = new Lexico(s1);
        List<Token> t1 = l1.getTokens();
        for (Token token:t1){
            System.out.println(token);
        }

    }
}