package khrushchev.ilya.NumberSequence;

public class Fibonacci implements INumberSequence {

    @Override
    public int[] generateSequence(int count) {
        int[] sequence = new int[count];
        for (int i = 0;i < count;i++){
            if((i == 0) || (i == 1)){
                sequence[i] = 1;
            }else
                sequence[i] = sequence[i-1]+ sequence[i-2];
        }
        return sequence;
    }
}
