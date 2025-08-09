import DataStructures.ListIF;

public class List<E> extends Sequence<E> implements ListIF<E> {

	/* Constructor por defecto: crea una lista vacía */
	public List() {
		super();
	}
	/*
	 * Constructor simple.
	 * La llamada super() invoca el constructor simple de Sequence que realiza
	 * dos asignaciones, y su coste es constante O(1).
	 * Luego el coste de este constructor simple sera O(1).
	 * El tamaño del problema es irrelevante en este caso.
	 */
	// ✅ CORRECTO: Análisis bien realizado. Efectivamente las asignaciones son O(1)
	// y el tamaño del problema es irrelevante.

	/*
	 * Constructor por copia: delega en el constructor por copia *
	 * de la secuencia
	 */
	public List(List<E> s) {
		super(s);
	}
	/*
	 * Constructor por copia.
	 * La llamada super(s) invoca el constructor por copia de Sequence que
	 * recorre todos los elemetos de la secuencia, luego su coste depende del
	 * tamaño del problema n, O(n)
	 * Luego el coste del constructor por copia de List será O(n), donde n =
	 * s.size()
	 */
	// ✅ CORRECTO: Bien identificado que debe recorrer todos los elementos. El
	// análisis es acertado.

	/* Devuelve el elemento pos-ésimo de la lista */
	public E get(int pos) {
		NodeSequence node = getNode(pos);
		return node.getValue();
	}
	/*
	 * Obtiene el valor de la secuencia en la posición pos.
	 * Llama al constructor simple NodeSequence, crea el objeto nodo y le asigna el
	 * valor de getNode(pos).
	 * El coste del constructor simple es constante O(1)
	 * Llama al método privado getNode(int pos) definido en la clase Sequence.
	 * El coste del metodo privado getNode(pos) es O(n).
	 * Realiza una operaciñon de retorno de atributo, cuyo coste es O(1)
	 * Luego el coste de este metodo será O(n)
	 * El coste depende del tamaño del problema que sera n = this.size()
	 */
	// ❌ CORRECCIÓN 1: No se llama a ningún constructor. getNode(pos) devuelve una
	// referencia a un nodo existente. Es la creación y asignación de una variable.
	// ❌ CORRECCIÓN 2: getNode(pos) tiene coste O(pos), no O(n), ya que recorre
	// desde el inicio hasta la posición pos.
	// ✅ AÑADIR: node.getValue() es una operación básica con coste O(1).
	// ✅ PRECISAR: El coste final es O(pos), que en el peor caso es O(n) cuando pos
	// = n.

	/* Modifica el elemento pos-ésimo de la lista */
	public void set(int pos, E e) {
		NodeSequence node = getNode(pos);
		node.setValue(e);
	}
	/*
	 * Modifica el valor de la secuencia en la posición pos por un elemento e.
	 * Llama al constructor simple NodeSequence, crea el objeto nodo y le asigna el
	 * valor de getNode(pos).
	 * El coste del constructor simple es constante O(1)
	 * Llama al método privado getNode(int pos) definido en la clase Sequence.
	 * El coste del metodo privado getNode(pos) es O(n).
	 * Llama al método setNode(e) definido en la clase Sequence.
	 * El coste del metodo privado setNode(e) es O(1).
	 * Luego el coste de este metodo será O(n).
	 * El coste depende del tamaño del problema que sera n = this.size()
	 */
	// ❌ CORRECCIÓN 1: Similar al get(), no se crea constructor, getNode() devuelve
	// referencia. Es la creación y asignación de una variable.
	// ❌ CORRECCIÓN 2: getNode(pos) es O(pos), no O(n).
	// ❌ CORRECCIÓN 3: Es setValue(e), no setNode(e). setValue() es una operación
	// básica con coste O(1).
	// ✅ PRECISAR: El coste final es O(pos), que en el peor caso es O(n).

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
	/*
	 * Inserta un elemento elem en la posición pos.
	 * Llama al constructor por parametro de NodeSequence, crea el objeto nodo
	 * con el valor elem.
	 * El coste del constructor por parámetro es constante O(1).
	 * El coste de la sentencia condicional if else será el máximo de calcular la
	 * expresion
	 * y ejecutar el cuerpo del condicional.
	 * Llama al método público setNext(E e) definido en la clase Sequence.
	 * El coste del étodo público setNext(E e) es O(1).
	 * Llama al constructor simple NodeSequence, crea el objeto previousNode y le
	 * asigna
	 * el valor de getNode(pos-1).
	 * El coste del constructor simple es constante O(1)
	 * Llama al método privado getNode(int pos) definido en la clase Sequence.
	 * El coste del metodo privado getNode(pos) es O(n).
	 * Llama al método público getNext() definido en la clase Sequence.
	 * El coste del metodo público getNext() es O(1).
	 * Luego el coste de este metodo será O(n).
	 * El coste depende del tamaño del problema que sera n = this.size()
	 */
	// ✅ CORRECTO: Bien identificado el constructor de NodeSequence con coste O(1).
	// ❌ FALTA ANÁLISIS POR CASOS: Según la metodología del profesor, debes analizar
	// línea por línea para cada caso:
	//
	// CASO 1: pos == 1
	// - newNode.setNext(this.firstNode): O(1) (operación básica setNext)
	// - this.firstNode = newNode: O(1) (asignación)
	// - this.size++: O(1) (incremento)
	// Coste total caso 1: O(1)
	//
	// CASO 2: pos != 1
	// - getNode(pos-1): O(pos-1) ≈ O(pos) (recorrido hasta posición pos-1)
	// - previousNode.getNext(): O(1) (operación básica getNext)
	// - previousNode.setNext(newNode): O(1) (operación básica setNext)
	// - newNode.setNext(nextNode): O(1) (operación básica setNext)
	// - this.size++: O(1) (incremento)
	// Coste total caso 2: O(pos)
	//
	// ✅ CONCLUSIÓN CORREGIDA: O(1) para pos=1, O(pos) para pos>1, que en el peor
	// caso es O(n)

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
	/*
	 * Elimina el elemento en la posición pos.
	 * Llama al método privado getNode(int pos) definido en la clase Sequence.
	 * El coste del metodo privado getNode(pos) es O(n).
	 * Llama al método público getNext() definido en la clase Sequence.
	 * El coste del metodo público getNext() es O(1).
	 * Llama al método público setNext(E e) definido en la clase Sequence.
	 * El coste del étodo público setNext(E e) es O(1).
	 * Luego el coste de este metodo será O(n).
	 * El coste depende del tamaño del problema que sera n = this.size()
	 */
	// ✅ CORRECTO: Bien identificadas las operaciones básicas getNext() y setNext()
	// como O(1).
	// ❌ FALTA ANÁLISIS POR CASOS: Según la metodología del profesor:
	//
	// CASO 1: pos == 1
	// - this.firstNode.getNext(): O(1) (operación básica getNext)
	// - this.firstNode = ...: O(1) (asignación)
	// - this.size--: O(1) (decremento)
	// Coste total caso 1: O(1)
	//
	// CASO 2: pos != 1
	// - getNode(pos-1): O(pos-1) ≈ O(pos) (recorrido hasta posición pos-1)
	// - previousNode.getNext().getNext(): O(1) + O(1) = O(1) (dos operaciones
	// getNext consecutivas)
	// - previousNode.setNext(nextNode): O(1) (operación básica setNext)
	// - this.size--: O(1) (decremento)
	// Coste total caso 2: O(pos)
	//
	// ✅ CONCLUSIÓN CORREGIDA: O(1) para pos=1, O(pos) para pos>1, que en el peor
	// caso es O(n)

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}