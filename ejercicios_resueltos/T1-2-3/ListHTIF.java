public interface ListHTIF<E> extends SequenceIF<E> {

    /**
     * Obtiene el primer elemento (cabeza) de la lista.
     * @Pre !isEmpty();
     * @return la cabeza de la lista;
     */
    public E getHead();

    /**
     * Obtiene la cola (resto) de la lista sin el primer elemento.
     * @Pre !isEmpty();
     * @return la cola de la lista;
     */
    public ListHTIF<E> getTail();
    
    /**
     * Establece un nuevo elemento como cabeza de la lista.
     * @Pre getHead() != null;
     * @param e el elemento e de tipo E que sera la nueva cabeza de la lista;
     * @return True si la cabeza ha sido modificada;
     * @Post la cabeza de la lista pasa a ser el elemento e de tipo E;
     */
    public boolean setHead(E e);

    /**
     * Elimina la cabeza actual de la lista.
     * @Pre getHead() != null;
     * @return True si la cabeza ha sido eliminada;
     * @Post la cabeza de la lista pasa a ser el primer elemento de getTail();
     */
    public boolean removeHead();

    /**
     * Inserta un nuevo elemento como cabeza de la lista.
     * @param e el elemento e de tipo E que se insertara en la cabeza de la lista;
     * @return True si el elemento e de tipo E ha sido insertado en la posición de la cabeza.
     * @Post la cabeza de la lista pasa a ser el elemento e de tipo E insertado;
     */
    public boolean insertHead(E e);
    
}
