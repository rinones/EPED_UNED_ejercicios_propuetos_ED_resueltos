/**
 * Interfaz ListIPIF que extiende Sequence para representar una lista con puntero interno
 * que permite navegar y manipular elementos en la posición actual del puntero.
 * 
 * @param <E> el tipo de elementos que almacena la lista
 */
public interface ListIPIF<E> extends SequenceIF<E> {

    /**
     * Obtiene la posición del puntero.
     * @return int de la posición del puntero
     */
    public int getPosition();


    /**
     * Mueve el puntero una posición hacia adelante.
     * @Pre !isEmpty()
     * @Pre getPosition() < size() + 1
     * @Post el puntero se encuentra en la posición getPosition() + 1
     */
    public void moveForward();

    // //Devuelve True si existe una posición anterior a la del puntero
    // @Pre !isEmpty();
    // @return True si existe una posición anterior a la posición donde se encuentra el puntero;
    // public boolean hasPrevious();

    /**
     * Mueve el puntero una posición hacia atrás.
     * @Pre !isEmpty()
     * @Pre getPosition() > 1
     * @Post el puntero se encuentra en la posición getPosition() - 1
     */
    public void moveBackward();

    /**
     * Obtiene el elemento en la posición del puntero.
     * @Pre !isEmpty()
     * @return el elemento en la posición actual del puntero
     */
    public E get();

    /**
     * Elimina el elemento en la posición del puntero.
     * @Pre !isEmpty()
     */
    public void remove();

    /**
     * Inserta el elemento e de tipo E en la posición del puntero.
     * @param e el elemento de tipo E a insertar
     * @Post el elemento e de tipo E es insertado en la posición getPosition()
     */
    public void insert(E e);

    /**
     * Modifica el elemento en la posición del puntero por un elemento e de tipo E.
     * @param e el elemento de tipo E por el que se modificará el contenido en la posición del puntero
     * @Post el elemento en la posición getPosition() es sustituido por el elemento e de tipo E
     */
    public void set(E e);


}
