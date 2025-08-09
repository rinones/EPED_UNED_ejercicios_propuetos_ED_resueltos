public interface ListHTIFchatgpt<E> extends SequenceIF<E> {

    /**
     * Devuelve la cabeza de la lista.
     * 
     * @pre !isEmpty()
     * @return el primer elemento de la lista
     */
    E getHead();

    /**
     * Devuelve la cola de la lista (la lista sin la cabeza).
     * Actua como removeHead().
     * 
     * @pre !isEmpty()
     * @return una nueva lista que es la cola de esta lista
     */
    ListHTIFchatgpt<E> getTail();

    /**
     * Devuelve una nueva lista en la que la cabeza es e y el resto es la lista
     * actual.
     * 
     * @param e elemento a insertar como nueva cabeza
     * @return una nueva lista con e como cabeza y esta lista como cola
     * @post la cabeza de la lista resultante es e, la cola es la lista actual
     */
    ListHTIFchatgpt<E> insertHead(E e);

    /**
     * Devuelve una nueva lista en la que la cabeza ha sido sustituida por e.
     * 
     * @pre !isEmpty()
     * @param e elemento que sustituirá la cabeza
     * @return una nueva lista con e como cabeza y la cola de esta lista como cola
     * @post la cabeza de la lista resultante es e, la cola es la cola de esta lista
     */
    ListHTIFchatgpt<E> setHead(E e);

    /**
     * Indica si la lista está vacía.
     * 
     * @return true si la lista no contiene elementos, false en caso contrario
     */
    boolean isEmpty();
}