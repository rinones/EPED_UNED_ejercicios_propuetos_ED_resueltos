

public class Queue<E> extends Sequence<E> implements QueueIF<E> {

	private NodeSequence lastNode;

	/* Constructor por defecto: crea una cola vacía */
	public Queue(){
		super();              /* Construimos la secuencia vacía */ // O(1)
		this.lastNode = null; /* No hay último nodo */ // O(1)
	} // O(1)
	
	/* Constructor por copia: delega en el constructor por copia *
	 * de la secuencia                                           */
    public Queue(Queue<E> s) {
		super(s);  /* Copiamos la secuencia de la cola original */ // O(n) n = s.size()
		/* Recorremos la secuencia de la cola copia para encontrar su último nodo */
		if ( this.isEmpty() ) {
			this.lastNode = null; // O(1)
		} else {
			NodeSequence node = this.getNode(this.size); // // O(n) n = s.size()
			this.lastNode = node; // O(1)
		} // O(n) n = s.size()
    }

	/*
	 * Constructor por copia: delega en el constructor por copia *
	 * de la secuencia
	 */
	public Queue(Sequence<E> s) {
		super(s);
	}
    
	/* Devuelve el primer elemento de la cola */
	public E getFirst() {
		return this.firstNode.getValue(); // O(1)
	} // O(1)

	/* Añade un nuevo elemento al final de la cola */
	public void enqueue(E elem) {
		NodeSequence newNode = new NodeSequence(elem); // O(1)
		if(isEmpty()){
			this.firstNode = newNode; // O(1)
		} else{
			this.lastNode.setNext(newNode); // O(1)
		}
		this.lastNode = newNode; // O(1)
		this.size++; // O(1)
	} // O(1)

	/* Elimina el primer elemento de la cola */
	public void dequeue() {
		this.firstNode = this.firstNode.getNext(); // O(1)
		this.size--; // O(1)
		if(this.size == 0){ // O(1)
			this.lastNode = null; // O(1)
		}
	} // O(1)
	
	/* Vacía la cola */
	public void clear() {
		super.clear();   /* Vaciamos la secuencia */ // O(1)
		this.lastNode = null; /* No hay último nodo */ // O(1)
	} // O(1)

	public void rotateQ(int c) {
		int n = size();
		int rot = (((c % n)+ n) % n); 
			for (int i = 0; i < rot; i++) {
				enqueue(getFirst());
				dequeue();
			}

	} //O(n) donde n = size()
	
}
