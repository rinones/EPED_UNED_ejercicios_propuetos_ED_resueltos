import java.security.cert.CertPathBuilderException;

public class GTree<E> extends Tree<E> implements GTreeIF<E> {

	private ListIF<GTreeIF<E>> children;

	/* Constructor por defecto: crea un árbol vacío */
	public GTree() {
		super();
		this.children = new List<GTreeIF<E>>();
	} // O(1)

	public void setRoot(E e) {
		if (this.getRoot()==null){
			this.increaseSize();
			this.updateHeight();
		}
		this.root = e;
	} // O(1)

	public ListIF<GTreeIF<E>> getChildren() {
		return this.children;
	} // O(1)

	public GTreeIF<E> getChild(int pos) {
		return this.children.get(pos);
	} // en el peor caso O(n) donde n = children.size()

	public void addChild(int pos, GTreeIF<E> e) {
		((Tree<E>)e).parent = this;
		this.children.insert(pos, e);
		this.increaseSize();
		this.updateHeight();
		this.updateFanOut();
	} // en el peor caso O(n) donde n = children.size()

	public void removeChild(int pos) {
		((Tree<E>)this.getChild(pos)).parent = null;
		this.children.remove(pos);
		this.decreaseSize();
		this.updateHeight();
		this.updateFanOut();
	} // en el peor caso O(n) donde n = children.size()

	/* Reimplementación/Especialización de algunos métodos de Collection */

	/* Devuelve el número de nodos del árbol */
	public int size() {
		// if (isEmpty()) {
		// 	return 0;
		// }
		// int s = 1;
		// IteratorIF<GTreeIF<E>> childIt = this.children.iterator();
		// while (childIt.hasNext()) {
		// 	s = s + childIt.getNext().size();
		// }
		// return s;
		return this.size;
	} // O(N) donde N es el número total de nodos del árbol
		// tras la modificacion de la clase Tree<E> añadiendole los atributos parent y size, el coste de obtener size() pasa a ser O(1)

	/* Vacía el árbol */
	public void clear() {
		super.clear();
		this.children.clear();
	} // O(1)

	/* Métodos heredados de CollectionIF */

	/* Comprueba si el árbol contiene el elemento */
	public boolean contains(E e) {
		if (isEmpty()) {
			return false;
		}
		boolean found = getRoot().equals(e);
		IteratorIF<GTreeIF<E>> childIt = this.children.iterator();
		while (!found && childIt.hasNext()) {
			found = childIt.getNext().contains(e);
		}
		return found;
	} // O(N) donde N = size()

	/* Métodos heredados de TreeIF */

	/* Devuelve el número de hijos del árbol */
	public int getNumChildren() {
		return this.children.size();
	} // O(1) si la implementación de ListIF mantiene el tamaño en una variable; de lo
		// contrario O(n) donde n = children.size(). (En la mayoría de implementaciones
		// es O(1))

	/* Devuelve el fan-out del árbol */
	public int getFanOut() {
		// if (isEmpty()) {
		// 	return 0;
		// }
		// int fOut = getNumChildren();
		// IteratorIF<GTreeIF<E>> childIt = this.children.iterator();
		// while (childIt.hasNext()) {
		// 	int aux = childIt.getNext().getFanOut();
		// 	if (aux > fOut) {
		// 		fOut = aux;
		// 	}
		// }
		// return fOut;
		return this.fanout;
	} // O(N) donde N = size()
		// tras la modificacion de la clase Tree<E> añadiendole los atributos parent y
		// fanout, el coste de obtener getFanout() pasa a ser O(1)

	/* Devuelve la altura del árbol */
	public int getHeight() {
		// if (isEmpty()) {
		// 	return 0;
		// }
		// int height = 0;
		// IteratorIF<GTreeIF<E>> childIt = this.children.iterator();
		// while (childIt.hasNext()) {
		// 	int aux = childIt.getNext().getHeight();
		// 	if (aux > height) {
		// 		height = aux;
		// 	}
		// }
		// return 1 + height;
		return this.height;
	} // O(N) donde N = size()
		// tras la modificacion de la clase Tree<E> añadiendole los atributos parent y
		// height, el coste de obtener getHeight() pasa a ser O(1)

	/* Devuelve un iterador sobre el árbol según el recorrido elegido */
	public IteratorIF<E> iterator(Object mode) {
		QueueIF<E> queue = new Queue<E>();
		if (mode instanceof GTree.IteratorModes) {
			switch ((GTree.IteratorModes) mode) {
				case PREORDER:
					preorder(this, queue);
					break;
				case POSTORDER:
					postorder(this, queue);
					break;
				case BREADTH:
					breadthLR(this, queue);
					break;
			}
		}
		return queue.iterator();
	} // O(N) donde N = size()

	/* Recorre el árbol en preorden */
	private void preorder(GTreeIF<E> t, QueueIF<E> q) {
		if (!t.isEmpty()) {
			q.enqueue(t.getRoot());
			IteratorIF<GTreeIF<E>> childIt = t.getChildren().iterator();
			while (childIt.hasNext()) {
				preorder(childIt.getNext(), q);
			}
		}
	} // O(N) donde N = size()

	/* Recorre el árbol en postorden */
	private void postorder(GTreeIF<E> t, QueueIF<E> q) {
		if (!t.isEmpty()) {
			IteratorIF<GTreeIF<E>> childIt = t.getChildren().iterator();
			while (childIt.hasNext()) {
				postorder(childIt.getNext(), q);
			}
			q.enqueue(t.getRoot());
		}
	} // O(N) donde N = size()

	/* Recorre el árbol en anchura de izquierda a derecha */
	private void breadthLR(GTreeIF<E> t, QueueIF<E> q) {
		if (!t.isEmpty()) {
			QueueIF<GTreeIF<E>> auxQ = new Queue<GTreeIF<E>>();
			auxQ.enqueue(t);
			while (!auxQ.isEmpty()) {
				GTreeIF<E> cGT = auxQ.getFirst();
				q.enqueue(cGT.getRoot());
				IteratorIF<GTreeIF<E>> childIt = cGT.getChildren().iterator();
				while (childIt.hasNext()) {
					auxQ.enqueue(childIt.getNext());
				}
				auxQ.dequeue();
			}
		}
	} // O(N) donde N = size()

	// Explicación sobre los costes:
	// En todos los métodos que recorren el árbol (size, contains, getFanOut,
	// getHeight, iterator, preorder, postorder, breadthLR),
	// el coste es O(N) porque cada nodo se visita una vez y la operación en cada
	// nodo es O(1).
	// Esto se justifica por la abstracción en el análisis: el tamaño del problema
	// es el número total de nodos N,
	// y por la fórmula de reducción por sustracción: T(N) = O(1) + suma de
	// T(subárboles hijos).
	// La suma de O(1) por cada uno de los N nodos da como resultado O(N).
	// En cambio, los métodos que sólo operan sobre la lista de hijos (getChild,
	// addChild, removeChild) dependen del tamaño de la lista de hijos directos.
	// El método getNumChildren típicamente es O(1) si la lista mantiene el tamaño;
	// si no, sería O(n) donde n es el número de hijos directos.

	public  E getMax1() {
		if (!isEmpty()) {
			IteratorIF<E> it = iterator(GTree.IteratorModes.PREORDER);
			E max = it.getNext();

			while (it.hasNext()) {
				E aux = it.getNext();
				if (((Comparable)aux).compareTo(max) > 0) {
					max = aux;
				}
			}
			return max;
		}
        return null;
	}

	public E getMax2() {
		if (!isEmpty()) {
			return getMax2Aux(this);
		}
		return null;
	}

	private E getMax2Aux(GTreeIF<E> t) {
		if (t.isLeaf()){
			return t.getRoot();
		}
		IteratorIF<GTreeIF<E>> it = t.getChildren().iterator();
		E max = null;
		while (it.hasNext()) {
			E childMax = getMax2Aux(it.getNext());
			if (childMax != null && (max == null || ((Comparable)childMax).compareTo(max) > 0)){
				max = childMax;
			}
		}
		return max;
	}

	public int calculateHeight() {
		int maxChildHeight = 0;
		if (!children.isEmpty()){
			IteratorIF<GTreeIF<E>> it = children.iterator();
			while (it.hasNext()) {
				if (it.getNext().getHeight() > maxChildHeight) {
					maxChildHeight = it.getNext().getHeight();
				}
			}
		}
		return 1 + maxChildHeight;
	}

	public int calculateFanOut() {
		int maxChildFanOut = 0;
		if (!children.isEmpty()) {
			IteratorIF<GTreeIF<E>> it = children.iterator();
			while (it.hasNext()) {
				if (it.getNext().getFanOut() > maxChildFanOut) {
					maxChildFanOut = it.getNext().getFanOut();
				}
			}
		}
		return maxChildFanOut;
	} 

}