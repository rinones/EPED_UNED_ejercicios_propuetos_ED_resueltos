

public class Stack<E> extends Sequence<E> implements StackIF<E> {
	
	/* Constructor por defecto: crea una pila vacía */
	public Stack(){
		super(); //O(1)
	}//O(1)
	
	/* Constructor por copia: delega en el constructor por copia *
	 * de la secuencia                                           */
    public Stack(Stack<E> s) {
    	super(s); //O(n)
    } //O(n) n = s.zize()

	/*
	 * Constructor por copia: delega en el constructor por copia *
	 * de la secuencia
	 */
	public Stack(Sequence<E> s) {
		super(s);
	}
	
	/* Devuelve el elemento en la cima de la pila */
	public E getTop() {
		return this.firstNode.getValue(); //O(1)
	} //O(1)

	/* Añade un nuevo elemento a la cima de la pila */
	public void push(E elem) {
		NodeSequence newNode = new NodeSequence(elem); //O(1)
		if(!isEmpty()){ //O(1)
			newNode.setNext(this.firstNode); //O(1)
		}
		this.firstNode = newNode; // O(1)
		this.size++; // O(1)
	}

	/* Elimina el elemento situado en la cima de la pila */
	public void pop() {
		this.firstNode = this.firstNode.getNext(); //O(1)
		this.size--; //O(1)
	}

	public void rotateS(int c) {
		StackIF<E> aux = new Stack<>();
		StackIF<E> result = new Stack<>();
		int n = size();
		int rot = (((c % n) + n) % n);
		for (int i = 0; i < rot; i++) {
			aux.push(getTop());
			pop();
		}
		while (!aux.isEmpty()) {
			result.push(aux.getTop());
			aux.pop();
		}
		while (!isEmpty()) {
			aux.push(getTop());
			pop();
		}
		while (!aux.isEmpty()) {
			result.push(aux.getTop());
			aux.pop();
		}
		while (!result.isEmpty()) {
			push(result.getTop());
			result.pop();
		}
	} //O(n) donde n = size()
	
}
