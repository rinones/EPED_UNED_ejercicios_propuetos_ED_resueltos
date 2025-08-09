/**
 * T4 EP4
 */


public class ListIP<E> extends Sequence<E> implements ListIPIF<E>{

    private Stack<E> previousNodeStack;
    private Stack<E> nextNodeStack;
    private int position = 1;

    public ListIP() {
        super();
        previousNodeStack = new Stack<>();
        nextNodeStack = new Stack<>();
    }

    public int getPosition() {
        return position;
    }

    /**
     * Mueve el puntero una posición hacia adelante.
     * 
     * @pre 1 <= getPosition() && getPosition() < size() + 1
     * @post el puntero se encuentra en la posición getPosition() previa + 1
     */
    public void moveForward() {
        // if (1 <= getPosition() && getPosition() < nextNodeStack.size() + 1) {
            E node = nextNodeStack.getTop();
            nextNodeStack.pop();
            previousNodeStack.push(node);
            position++;
        // }
    }

    /**
     * Mueve el puntero una posición hacia atrás.
     * 
     * @pre 1 < getPosition() && getPosition() <= size() + 1
     * @post el puntero se encuentra en la posición getPosition() previa - 1
     */
    public void moveBackward() {
        // if (1 < getPosition() && getPosition() <= previousNodeStack.size() + 1) {
        E node = previousNodeStack.getTop();
        previousNodeStack.pop();
        nextNodeStack.push(node);
        position--;
        // }
    }

    /**
     * Obtiene el elemento en la posición del puntero.
     * 
     * @pre 1 <= getPosition() && getPosition() <= size()
     * @return el elemento en la posición actual del puntero
    */
    public E get() {
        // if (1 <= getPosition() && getPosition() <= this.size()) {
            return nextNodeStack.getTop();
        // } else {return null;}
    }

    /**
     * Elimina el elemento en la posición del puntero.
     * 
     * @pre 1 <= getPosition() && getPosition() <= size()
     * @post el elemento en la posición getPosition() previa es eliminado,
     *       el puntero permanece en la misma posición (que ahora puede estar tras
     *       el último)
     */
    public void remove() {
        // if (1 <= getPosition() && getPosition() <= this.size()) {
            nextNodeStack.pop();
            this.size--;
        // }
    }

    /**
     * Inserta el elemento e de tipo E en la posición del puntero.
     * 
     * @pre 1 <= getPosition() && getPosition() <= size() + 1
     * @post el elemento e es insertado en la posición actual del puntero,
     *       los elementos a partir de esa posición se desplazan una posición hacia
     *       atrás.
     */
    public void insert(E e) {
        // if (1 <= getPosition() && getPosition() <= this.size() + 1) {
            if (nextNodeStack.isEmpty()){
                nextNodeStack.push(e);
            } else {previousNodeStack.push(e);}
            this.size++;
        // }
    }

    /**
     * Modifica el elemento en la posición del puntero por un elemento e de tipo E.
     * 
     * @pre 1 <= getPosition() && getPosition() <= size()
     * @post el elemento en la posición getPosition() es sustituido por e.
     */
    public void set(E e) {
        // if (1 <= getPosition() && getPosition() <= this.size()) {
            nextNodeStack.pop();
            nextNodeStack.push(e);
        // }
    }
}
