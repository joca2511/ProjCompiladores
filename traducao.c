#include <stdio.h>
int main(){
int ini=0;
int passo=0;
int limite;
scanf("%d",&limite);
scanf("%d",&passo);
int fim=0;
float testando=123+23-(123+2)*1.1;
while(fim!=1){
ini=ini+passo;
printf("%d\n",ini);
if(ini>=limite){
fim=1;
}
}
int passo=1;
return 0;
}