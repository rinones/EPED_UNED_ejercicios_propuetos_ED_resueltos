public abstract class Sequence<E> extends Collection<E> implements SequenceIF<E> {
	/* Clase privada que implementa la estructura de nodos de la secuencia */
	protected class NodeSequence {

		private E value;
		private NodeSequence next;

		NodeSequence() {
			this.value = null;
			this.next = null;
		}
		// Comentario corregido:
		// Constructor por defecto: realiza dos asignaciones simples.
		// Coste: O(1)
		// Tamaño del problema irrelevante, ya que no depende de n.

		NodeSequence(E e) {
			this.value = e;
			this.next = null;
		}
		// Comentario corregido:
		// Constructor con parámetro: realiza dos asignaciones simples.
		// Coste: O(1)
		// Tamaño del problema irrelevante, ya que no depende de n.

		public E getValue() {
			return this.value;
		}
		/*
		 * Realiza una operación de salida return cuyo coste es constante O(1)
		 *
		 * Comentario corregido:
		 * Operación de retorno de atributo.
		 * Coste: O(1)
		 */

		public void setValue(E e) {
			this.value = e;
		}
		/*
		 * Realiza una operación de asignación cuyo coste es constante O(1)
		 *
		 * Comentario corregido:
		 * Asignación simple.
		 * Coste: O(1)
		 */

		public NodeSequence getNext() {
			return this.next;
		}
		/*
		 * Realiza una operación de salida return cuyo coste es constante O(1)
		 *
		 * Comentario corregido:
		 * Operación de retorno de atributo.
		 * Coste: O(1)
		 */

		public void setNext(NodeSequence n) {
			this.next = n;
		}
		/*
		 * Realiza una operación de asignación cuyo coste es constante O(1)
		 *
		 * Comentario corregido:
		 * Asignación simple.
		 * Coste: O(1)
		 */
	}

	/* Clase privada que implementa un iterador para la secuencia */
	private class SequenceIterator implements IteratorIF<E> {

		private NodeSequence currentNode;

		SequenceIterator() {
			this.currentNode = firstNode;
		}
		// Comentario corregido:
		// Constructor: asignación simple.
		// Coste: O(1)

		public E getNext() {
			E elem = this.currentNode.getValue();
			this.currentNode = this.currentNode.getNext();
			return elem;
		}
		/*
		 * Realiza dos operaciónes de asignación y una operación de salida return, cuyo
		 * coste es constante O(1)
		 *
		 * Comentario corregido:
		 * Dos asignaciones y un return, todas O(1).
		 * Coste: O(1)
		 */

		public boolean hasNext() {
			return this.currentNode != null;
		}
		/*
		 * Realiza una expresión de comparación y una operación de salida, cuyo coste es
		 * constante O(1)
		 *
		 * Comentario corregido:
		 * Comparación y return.
		 * Coste: O(1)
		 */

		public void reset() {
			this.currentNode = firstNode;
		}
		/*
		 * Realiza una operación de asignación cuyo coste es constante O(1)
		 *
		 * Comentario corregido:
		 * Asignación simple.
		 * Coste: O(1)
		 */

	}

	protected NodeSequence firstNode;

	/* Devuelve el primer nodo de la secuencia */
	private NodeSequence getFirstNode() {
		return this.firstNode;
	}
	// Comentario corregido:
	// Método privado: acceso a atributo.
	// Coste: O(1)
	// No es necesario analizarlo para el ejercicio salvo que se use en métodos
	// públicos.

	/* Constructor de una secuencia vacía */
	public Sequence() {
		super(); /* Creamos una colección */
		this.firstNode = null; /* La secuencia es vacía */
	}
	// Comentario corregido:
	// Constructor por defecto: dos asignaciones simples.
	// Coste: O(1)
	// Tamaño del problema irrelevante.

	/* Constructor por copia */
	public Sequence(Sequence<E> s) {
		this();
		if (!s.isEmpty()) {
			this.size = s.size();
			NodeSequence nodeS = s.getFirstNode();
			NodeSequence pNode = new NodeSequence(nodeS.getValue());
			this.firstNode = pNode;
			while (nodeS.getNext() != null) {
				nodeS = nodeS.getNext();
				NodeSequence newNode = new NodeSequence(nodeS.getValue());
				pNode.setNext(newNode);
				pNode = newNode;
			}
		}
	}
	// Comentario corregido:
	// Todas las operaciones fuera del bucle son O(1).
	// El bucle recorre todos los nodos de la secuencia s, es decir, n veces, siendo
	// n = número de elementos de s.
	// Cada iteración es O(1).
	// Coste total: O(n), donde n = s.size()

	/* Reimplementación/Especialización de algunos métodos de Collection */

	/* Elimina todos los elementos de la secuencia */
	public void clear() {
		super.clear(); /* Vaciamos la colección */
		this.firstNode = null; /* La secuencia es vacía */
	}
	/*
	 * Análisis de coste:
	 * - La llamada a super.clear() invoca el método clear() de Collection, que
	 * únicamente pone el atributo size a cero mediante una asignación simple.
	 * Por tanto, el coste de super.clear() es O(1).
	 * - La asignación this.firstNode = null; desconecta la secuencia de nodos,
	 * también es una operación de coste constante O(1).
	 * Por tanto, el coste total del método clear() es O(1).
	 */

	/* Métodos heredados de CollectionIF */

	/* Comprueba si la secuencia contiene el elemento */
	public boolean contains(E e) {
		NodeSequence node = this.firstNode;
		while (node != null) {
			E next = node.getValue();
			if (next.equals(e)) {
				return true;
			}
			node = node.getNext();
		}
		return false;
	}
	/*
	 * Realiza operaciones de asignacion. calculo de expresiones y operaciones de
	 * salida de coste constante O(1).
	 * Realiza una sentencia condicional if cuyo coste sera el máximo de calcular la
	 * expresion e y ejecutar cada uno de los casos, de coste constante O(1) en este
	 * caso,
	 * luego el coste de la sentencia condicional es constante O(1)
	 * Realiza un bucle while cuyo coste sera el máximo entre multiplicar el numero
	 * de vueltas igual a size() por el coste de calcular la expresión y ejecutar el
	 * cuerpo del
	 * bucle. Luego el coste del bucle sera O(n) * O(1) = O(n) = O(size())
	 *
	 * Comentario corregido:
	 * Asignación y comprobación inicial: O(1)
	 * En el peor caso (elemento no encontrado), el bucle recorre todos los nodos,
	 * es decir, n veces, siendo n = número de elementos de la secuencia
	 * (this.size()).
	 * Cada iteración realiza operaciones O(1).
	 * Coste total: O(n), donde n = this.size()
	 */

	/* Devuelve un iterador para la secuencia */
	public IteratorIF<E> iterator() {
		return new SequenceIterator();
	}
	/*
	 * Realiza una operacion de salida return cuyo coste es constante O(1)
	 *
	 * Comentario corregido:
	 * Llama al constructor de SequenceIterator, que realiza una asignación simple.
	 * Coste: O(1)
	 */

	/*
	 * Devuelve el nodo i-ésimo de la secuencia *
	 * 
	 * @Pre: 1 <= i <= size()
	 */
	protected NodeSequence getNode(int i) {
		NodeSequence node = this.firstNode;
		for (int aux = 1; aux < i; aux++) {
			node = node.getNext();
		}
		return node;
	}
	/*
	 * Realiza operaciones de asignacion. calculo de expresiones y operaciones de
	 * salida. cuyo coste constante O(1).
	 * Realiza un bucle for, cuyo coste sera el máximo entre calcular la inic y
	 * multiplicar el numero de vueltas por el coste de calcular la expresión, el
	 * cuerpo del bucle y el incremento. Luego el coste del bucle sera O(n) * O(1) =
	 * O(n) = O(size())
	 *
	 * Comentario corregido:
	 * Método protegido, no público.
	 * Asignación inicial y bucle for.
	 * El bucle se repite (i-1) veces, cada iteración hace operaciones O(1).
	 * Coste: O(i), siendo i el parámetro de entrada.
	 * En el peor caso, cuando i=n (último nodo), el coste es O(n).
	 * Aunque es método protegido, su coste afecta a métodos públicos que lo usen.
	 */
}