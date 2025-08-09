

public interface ListIPIF<E> extends SequenceIF<E> {
    /**
     * Devuelve la posición del puntero.
     * 
     * @return la posición actual del puntero (entre 1 y size()+1)
     */
    int getPosition();

    /**
     * Mueve el puntero una posición hacia adelante.
     * 
     * @pre 1 <= getPosition() && getPosition() < size() + 1
     * @post el puntero se encuentra en la posición getPosition() previa + 1
     */
    void moveForward();

    /**
     * Mueve el puntero una posición hacia atrás.
     * 
     * @pre 1 < getPosition() && getPosition() <= size() + 1
     * @post el puntero se encuentra en la posición getPosition() previa - 1
     */
    void moveBackward();

    /**
     * Obtiene el elemento en la posición del puntero.
     * 
     * @pre 1 <= getPosition() && getPosition() <= size()
     * @return el elemento en la posición actual del puntero
     */
    E get();

    /**
     * Elimina el elemento en la posición del puntero.
     * 
     * @pre 1 <= getPosition() && getPosition() <= size()
     * @post el elemento en la posición getPosition() previa es eliminado,
     *       el puntero permanece en la misma posición (que ahora puede estar tras
     *       el último)
     */
    void remove();

    /**
     * Inserta el elemento e de tipo E en la posición del puntero.
     * 
     * @pre 1 <= getPosition() && getPosition() <= size() + 1
     * @post el elemento e es insertado en la posición actual del puntero,
     *       los elementos a partir de esa posición se desplazan una posición hacia
     *       atrás. El puntero sigue apuntando al elemento que apuntaba antes de la inserción.
     */
    void insert(E e);

    /**
     * Modifica el elemento en la posición del puntero por un elemento e de tipo E.
     * 
     * @pre 1 <= getPosition() && getPosition() <= size()
     * @post el elemento en la posición getPosition() es sustituido por e.
     */
    void set(E e);
}