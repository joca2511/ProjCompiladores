import java.util.Scanner;
public class traducao{
public static void main(String args[]){
Scanner entrada = new Scanner(System.in);
float coisa=5;
coisa=(23+283/213*394);
int troco=0;
troco=1;
int inteiro;
inteiro=Integer.parseInt(entrada.nextLine());
int contador=0;
coisa=Float.parseFloat(entrada.nextLine());
for(contador=contador;(contador<10);++contador){
coisa=coisa+3;
if(coisa>1000){
troco=0;
}
else{
coisa=1000;
}
while(troco==0){
coisa=coisa+1;
if(coisa>=2000){
troco=1;
}
}
}
System.out.println(inteiro);
System.out.println(coisa);

}
}