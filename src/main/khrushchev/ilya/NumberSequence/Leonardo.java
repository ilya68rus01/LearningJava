package khrushchev.ilya.NumberSequence;

public class Leonardo implements INumberSequence {

    @Override
    public int[] generateSequence(int count) {
        int[] sequence = new int[count];
        for (int i = 0;i < count;i++){
            if((i == 0) || (i == 1)){
                sequence[i] = 1;
            }else
                sequence[i] = sequence[i-1] + sequence[i-2] + 1;
        }
        return sequence;
    }
}
