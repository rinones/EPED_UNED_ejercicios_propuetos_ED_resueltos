public interface SequenceMSIF<E> extends SequenceIF<E> {

    /**
     * Devuelve el tamaño máximo de la sequencia
     */

    public int getMaxSize();

    /**
     * Devuelve true si esta lleno
     */
    public boolean isFull();
    
}
