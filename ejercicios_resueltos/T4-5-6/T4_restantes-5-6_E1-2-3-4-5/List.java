
public class List<E> extends Sequence<E> implements ListIF<E> {

	/* Constructor por defecto: crea una lista vacía */
	public List() {
		super();
	}

	/*
	 * Constructor por copia: delega en el constructor por copia *
	 * de la secuencia
	 */
	public List(List<E> s) {
		super(s);
	}

	/*
	 * Constructor por copia: delega en el constructor por copia *
	 * de la secuencia
	 */
	public List(Sequence<E> s) {
		super(s);
	}

	/* Devuelve el elemento pos-ésimo de la lista */
	public E get(int pos) {
		NodeSequence node = getNode(pos);
		return node.getValue();
	}

	/* Modifica el elemento pos-ésimo de la lista */
	public void set(int pos, E e) {
		NodeSequence node = getNode(pos);
		node.setValue(e);
	}

	/*
	 * Inserta un nuevo elemento en la lista en la posición *
	 * indicada
	 */
	public void insert(int pos, E elem) {
		NodeSequence newNode = new NodeSequence(elem);
		if (pos == 1) {
			newNode.setNext(this.firstNode);
			this.firstNode = newNode;
		} else {
			NodeSequence previousNode = getNode(pos - 1);
			NodeSequence nextNode = previousNode.getNext();
			previousNode.setNext(newNode);
			newNode.setNext(nextNode);
		}
		this.size++;
	}

	/* Elimina el elemento pos-ésimo de la lista */
	public void remove(int pos) {
		if (pos == 1) {
			this.firstNode = this.firstNode.getNext();
		} else {
			NodeSequence previousNode = getNode(pos - 1);
			NodeSequence nextNode = previousNode.getNext().getNext();
			previousNode.setNext(nextNode);
		}
		this.size--;
	}

	/**
	 * T4 EP5
	 */

	public ListIF<E> invierte(ListIF<E> l) {
		ListIF<E> invertedList = new List<>(); // O(1)
		IteratorIF<E> i = l.iterator(); // O(1)
		if (!l.isEmpty()) { // O(1)
			while (i.hasNext()) { // O(n)
				E node = i.getNext(); // O(1)
				invertedList.insert(1, node); // O(1)
			}
		}
		return invertedList; // O(1)
	}
	// Coste de invierteIt O(n), donde n = l.size()

	public ListIF<E> invierte(ListIF<E> l, ListIF<E> iL) {
		if (!l.isEmpty()) { // O(1)
			E node = l.get(1); // O(1)
			iL.insert(1, node); // O(1)
			l.remove(1); // O(1)
			invierte(l, iL); // O(n)
		}
		return iL; // O(1)
	}
	// Coste de invierteRe O(n), se hacen n llamadas recursivas, luego el coste es
	// O(n) donde pos es n = l.size() en el peor de los casos

	public static <E extends Comparable<? super E>> boolean isSorted(ListIF<E> l) {
		IteratorIF<E> i = l.iterator();

		if (l.isEmpty()) {
			return false;
		}
		if (l.size() == 1) {
			return true;
		}

		boolean ascendente = true;
		boolean descendente = true;
		E current = i.getNext();

		while (i.hasNext()) {
			E next = i.getNext();
			int orden = current.compareTo(next);
			if (orden < 0) {
				ascendente = false;
			} else if (orden > 0) {
				descendente = false;
			}

			if (!ascendente && !descendente) {
				return false;
			}
			current = next;
		}

		return ascendente || descendente;
	}

	public static <E extends Comparable<? super E>> boolean esRellano(List<E> l) {
		if (l.isEmpty() || l.size() < 2) {
			return false;
		}
		IteratorIF<E> i = l.iterator();
		E previous = i.getNext();
		while (i.hasNext()) {
			E current = i.getNext();
			if (previous.compareTo(current) == 0) {
				return true;
			}
		}
		return false;
	}

	public static <E extends Comparable<? super E>> ListIF<E> mezclar(ListIF<E> a, ListIF<E> b) {
		ListIF<E> sortedList = new List<>();
		if (a.isEmpty() && b.isEmpty()) {
			return sortedList;
		} else if (a.isEmpty()) {
			return b;
		} else if (b.isEmpty()) {
			return a;
		} else {
			IteratorIF<E> itA = a.iterator();
			IteratorIF<E> itB = b.iterator();
			E nodeA = null;
			E nodeB = null;
			if (itA.hasNext()){
				nodeA = itA.getNext();
			}
			if (itB.hasNext()){
				nodeB = itB.getNext();
			}

			while (nodeA != null && nodeB != null) {
				if (nodeA.compareTo(nodeB) < 0) {
					sortedList.insert(sortedList.size() + 1, nodeA);
					if (itA.hasNext()){
						nodeA = itA.getNext();
					} else {
						nodeA = null;
					}
				} else if (nodeA.compareTo(nodeB) > 0) {
					sortedList.insert(sortedList.size() + 1, nodeB);
					if (itB.hasNext()) {
						nodeB = itB.getNext();
					} else {
						nodeB = null;
					}
				} else {
					sortedList.insert(sortedList.size() + 1, nodeA);
					sortedList.insert(sortedList.size() + 1, nodeB);
					if (itA.hasNext()) {
						nodeA = itA.getNext();
					} else {
						nodeA = null;
					}
					if (itB.hasNext()) {
						nodeB = itB.getNext();
					} else {
						nodeB = null;
					}
				}
			}

			while (nodeA != null) {
				sortedList.insert(sortedList.size() + 1, nodeA);
				if (itA.hasNext()) {
					nodeA = itA.getNext();
				} else {
					nodeA = null;
				}
			}

			while (nodeB != null) {
				sortedList.insert(sortedList.size() + 1, nodeB);
				if (itB.hasNext()) {
					nodeB = itB.getNext();
				} else {
					nodeB = null;
				}
			}
		}
		return sortedList;

	}
}
