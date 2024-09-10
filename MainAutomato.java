import java.util.List;
public class MainAutomato{
    public static void main(String[] args){
        String s1 = "+-/*   \n --++\n/ 1234 1234 1 11 9374 37*72*123/283(()) abc +12 acccss 233.22222 222.1 333.45 222.1 0.";
        Lexico l1 = new Lexico(s1);
        List<Token> t1 = l1.getTokens();
        for (Token token:t1){
            System.out.println(token);
        }

    }
}