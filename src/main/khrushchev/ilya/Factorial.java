package khrushchev.ilya;

public class Factorial {

    protected int getRecursiveFactorial(int number){
     if((number <= 1))
         return 1;
     else{
         return number*getFactorial(number-1);
     }
    }

    protected int getFactorial(int number){
        int value = 1;
        for(int i = 0;i<=number;++i){
            if (! ((i == 0) || (i == 1)) ){
                value = value*i;
            }
        }
        return value;
    }
}
