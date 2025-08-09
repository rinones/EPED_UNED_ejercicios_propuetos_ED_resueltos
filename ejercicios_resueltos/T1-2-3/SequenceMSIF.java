/**
 * Interfaz SequenceMSIF que extiende Sequence para representar una secuencia con tamaño máximo
 * que permite insertar elementos con restricción de capacidad.
 * 
 * @param <E> el tipo de elementos que almacena la secuencia
 */
public interface SequenceMSIF<E> extends SequenceIF<E> {

    /**
     * Inserta un nuevo elemento en la lista.
     * @param e el elemento a insertar
     * @Pre size() + 1 <= MaxSize
     */
    public void insert(E e);
    
}
