/**
 * Interfaz para secuencias con tamaño máximo inmutable.
 * El tamaño máximo se fija en la construcción del objeto.
 */
public interface SequenceMSIFchatgpt<E> extends SequenceIF<E> {

    /**
     * Devuelve el tamaño máximo permitido de la secuencia.
     * 
     * @return el número máximo de elementos que puede contener la secuencia.
     */
    int maxSize();

    /**
     * Inserta un nuevo elemento en la secuencia.
     * 
     * @pre size() < maxSize()
     * @param e elemento a insertar
     */
    void insert(E e);
}