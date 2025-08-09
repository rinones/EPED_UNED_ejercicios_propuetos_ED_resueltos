


public class SequenceMS<E> extends Sequence<E> implements SequenceMSIF<E> {

    int maxSize = 10;

    public int getMaxSize() {
        return maxSize;
    }

    public boolean isFull() {
        return this.size() == maxSize;
    }
    
}