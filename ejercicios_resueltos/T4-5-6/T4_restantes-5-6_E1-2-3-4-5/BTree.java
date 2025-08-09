
public class BTree<E> extends Tree<E> implements BTreeIF<E> {

	private BTreeIF<E> leftChild;
	private BTreeIF<E> rightChild;

	/* Constructor por defecto: crea un árbol binario vacío */
	public BTree() {
		super();
		this.leftChild = null;
		this.rightChild = null;
	} // O(1)

	/* Devuelve el hijo izquierdo del árbol */
	public BTreeIF<E> getLeftChild() {
		return this.leftChild;
	} // O(1)

	/* Devuelve el hijo derecho del árbol */
	public BTreeIF<E> getRightChild() {
		return this.rightChild;
	} // O(1)

	/* Modifica la raíz */
	public void setRoot(E e) {
		if (this.getRoot() == null) {
			this.increaseSize();
			this.updateHeight();
		}
		this.root = e;
	} // O(1)

	/* Modifica el hijo izquierdo */
	public void setLeftChild(BTreeIF<E> child) {
		((Tree<E>)child).parent = this;
		if (this.leftChild == null) {
			this.increaseSize();
		}
		this.leftChild = child;
		this.updateHeight();
		this.updateFanOut();
	} // O(1)

	/* Modifica el hijo derecho */
	public void setRightChild(BTreeIF<E> child) {
		((Tree<E>) child).parent = this;
		if (this.rightChild == null) {
			this.increaseSize();
		}
		this.rightChild = child;
		this.updateHeight();
		this.updateFanOut();
	} // O(1)

	/* Elimina el hijo izquierdo */
	public void removeLeftChild() {
		((Tree<E>) this.leftChild).parent = this;
		this.leftChild = null;
		this.updateHeight();
		this.decreaseSize();
		this.updateFanOut();
	} // O(1)

	/* Elimina el hijo derecho */
	public void removeRightChild() {
		((Tree<E>) this.rightChild).parent = this;
		this.rightChild = null;
		this.updateHeight();
		this.decreaseSize();
		this.updateFanOut();
	} // O(1)

	/* Reimplementación/Especialización de algunos métodos de Collection */

	/* Devuelve el número de nodos del árbol */
	public int size() {
		// if (isEmpty()) {
		// 	return 0;
		// }
		// int s = 1;
		// if (this.leftChild != null) {
		// 	s = s + this.leftChild.size();
		// }
		// if (this.rightChild != null) {
		// 	s = s + this.rightChild.size();
		// }
		// return s;
		return this.size;
	} // O(N) donde N es el número total de nodos del árbol.
		// tras la modificacion de la clase Tree<E> añadiendole los atributos parent y
		// size, el coste de obtener size() pasa a ser O(1)

	/*
	 * Explicación: Se visita cada nodo una sola vez, sumando el trabajo constante
	 * O(1) por cada nodo.
	 * Por la fórmula de reducción por sustracción, T(N) = O(1) + T(left) +
	 * T(right), así que el coste total es O(N).
	 */

	/* Vacía el árbol binario */
	public void clear() {
		super.clear();
		this.leftChild = null;
		this.rightChild = null;
	} // O(1)

	/* Métodos heredados de CollectionIF */

	/* Comprueba si el árbol binario contiene el elemento */
	public boolean contains(E e) {
		return (!isEmpty() && (this.root.equals(e) ||
				(this.leftChild != null && this.leftChild.contains(e)) ||
				(this.rightChild != null && this.rightChild.contains(e))));
	} // O(N) donde N es el número total de nodos del árbol.
		// Explicación: Se recorre cada nodo una sola vez, por lo que el coste total es
		// proporcional a N.

	/* Métodos heredados de TreeIF */

	/* Devuelve el número de hijos del árbol */
	public int getNumChildren() {
		int nC = 0;
		if (this.leftChild != null) {
			nC++;
		}
		if (this.rightChild != null) {
			nC++;
		}
		return nC;
	} // O(1)
		// Explicación: Solo comprueba dos referencias, no depende del tamaño del árbol.

	/* Devuelve el fan-out del árbol */
	public int getFanOut() {
		// if (getNumChildren() == 2) {
		// 	return 2;
		// }
		// if (this.leftChild != null) {
		// 	return Math.max(1, this.leftChild.getFanOut());
		// }
		// if (this.rightChild != null) {
		// 	return Math.max(1, this.rightChild.getFanOut());
		// }
		// return 0;
		return this.fanout;
	} // O(N) donde N es el número total de nodos del árbol.
		// Explicación: Se realiza una llamada recursiva por cada nodo, coste O(1) cada
		// vez, coste total O(N).
		// tras la modificacion de la clase Tree<E> añadiendole los atributos parent y
		// fanout, el coste de obtener getFanout() pasa a ser O(1)

	/* Devuelve la altura del árbol */
	public int getHeight() {
		// if (isEmpty()) {
		// 	return 0;
		// }
		// int hLC = 0;
		// if (this.leftChild != null) {
		// 	hLC = this.leftChild.getHeight();
		// }
		// int hRC = 0;
		// if (this.rightChild != null) {
		// 	hRC = this.rightChild.getHeight();
		// }
		// return 1 + ((hLC > hRC) ? hLC : hRC);
		return this.height;
	} // O(N) donde N es el número total de nodos del árbol.
		// Explicación: Cada nodo se visita una sola vez y la recursión sigue por los
		// hijos, coste total O(N).
		// tras la modificacion de la clase Tree<E> añadiendole los atributos parent y
		// height, el coste de obtener getHeight() pasa a ser O(1)

	/* Devuelve un iterador sobre el árbol según el recorrido elegido */
	public IteratorIF<E> iterator(Object mode) {
		QueueIF<E> queue = new Queue<E>();
		if (mode instanceof BTreeIF.IteratorModes) {
			switch ((BTreeIF.IteratorModes) mode) {
				case PREORDER:
					preorder(this, queue);
					break;
				case INORDER:
					inorder(this, queue);
					break;
				case POSTORDER:
					postorder(this, queue);
					break;
				case BREADTH:
					breadthLR(this, queue);
					break;
				case RLBREADTH:
					breadthRL(this, queue);
					break;
			}
		}
		return queue.iterator();
	} // O(N) donde N es el número total de nodos del árbol.
		// Explicación: Cualquier recorrido visita cada nodo una vez, por lo que el
		// coste es O(N).

	/* Recorre el árbol en preorden */
	private void preorder(BTreeIF<E> t, QueueIF<E> q) {
		if (!t.isEmpty()) {
			q.enqueue(t.getRoot());
			if (t.getLeftChild() != null) {
				preorder(t.getLeftChild(), q);
			}
			if (t.getRightChild() != null) {
				preorder(t.getRightChild(), q);
			}
		}
	} // O(N) donde N es el número total de nodos del árbol.

	/* Recorre el árbol en inorden */
	private void inorder(BTreeIF<E> t, QueueIF<E> q) {
		if (!t.isEmpty()) {
			if (t.getLeftChild() != null) {
				inorder(t.getLeftChild(), q);
			}
			q.enqueue(t.getRoot());
			if (t.getRightChild() != null) {
				inorder(t.getRightChild(), q);
			}
		}
	} // O(N) donde N es el número total de nodos del árbol.

	/* Recorre el árbol en postorden */
	private void postorder(BTreeIF<E> t, QueueIF<E> q) {
		if (!t.isEmpty()) {
			if (t.getLeftChild() != null) {
				postorder(t.getLeftChild(), q);
			}
			if (t.getRightChild() != null) {
				postorder(t.getRightChild(), q);
			}
			q.enqueue(t.getRoot());
		}
	} // O(N) donde N es el número total de nodos del árbol.

	/* Recorre el árbol en anchura de izquierda a derecha */
	private void breadthLR(BTreeIF<E> t, QueueIF<E> q) {
		if (!t.isEmpty()) {
			QueueIF<BTreeIF<E>> auxQ = new Queue<BTreeIF<E>>();
			auxQ.enqueue(t);
			while (!auxQ.isEmpty()) {
				BTreeIF<E> cBT = auxQ.getFirst();
				q.enqueue(cBT.getRoot());
				if (cBT.getLeftChild() != null) {
					auxQ.enqueue(cBT.getLeftChild());
				}
				if (cBT.getRightChild() != null) {
					auxQ.enqueue(cBT.getRightChild());
				}
				auxQ.dequeue();
			}
		}
	} // O(N) donde N es el número total de nodos del árbol.

	/* Recorre el árbol en anchura de derecha a izquierda */
	private void breadthRL(BTreeIF<E> t, QueueIF<E> q) {
		if (!t.isEmpty()) {
			QueueIF<BTreeIF<E>> auxQ = new Queue<BTreeIF<E>>();
			auxQ.enqueue(t);
			while (!auxQ.isEmpty()) {
				BTreeIF<E> cBT = auxQ.getFirst();
				q.enqueue(cBT.getRoot());
				if (cBT.getRightChild() != null) {
					auxQ.enqueue(cBT.getRightChild());
				}
				if (cBT.getLeftChild() != null) {
					auxQ.enqueue(cBT.getLeftChild());
				}
				auxQ.dequeue();
			}
		}
	} // O(N) donde N es el número total de nodos del árbol.

	/*
	 * Explicación general de los costes:
	 * - Los métodos recursivos que procesan todo el árbol (size, contains,
	 * getFanOut, getHeight, iterator y los recorridos preorder, inorder, postorder,
	 * breadthLR, breadthRL) tienen coste O(N), donde N es el número total de nodos
	 * del árbol.
	 * - Esto se justifica porque cada nodo se visita una sola vez, y el trabajo en
	 * cada nodo es O(1). Por la fórmula de reducción por sustracción: T(N) = O(1) +
	 * T(left) + T(right), el trabajo total es proporcional a N.
	 * - Los métodos que solo consultan o modifican los hijos directos
	 * (getNumChildren, setLeftChild, setRightChild, removeLeftChild,
	 * removeRightChild, getLeftChild, getRightChild) tienen coste O(1), ya que
	 * trabajan solo con dos referencias.
	*/

	public E getMax() {
		if (!isEmpty()) {
			IteratorIF<E> it = iterator(BTree.IteratorModes.PREORDER);
			E max = it.getNext();

			while (it.hasNext()) {
				E aux = it.getNext();
				if (((Comparable) aux).compareTo(max) > 0) {
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

	private E getMax2Aux(BTreeIF<E> t) {
		if (t.isLeaf()) {
			return t.getRoot();
		}
		E max = null;
		E childMaxL = null;
		E childMaxR = null;

		if (t.getLeftChild() != null) {
			childMaxL = getMax2Aux(t.getLeftChild());
		}
		if (t.getRightChild() != null) {
			childMaxR = getMax2Aux(t.getRightChild());
		}		
		if (childMaxL != null && childMaxR != null){
			if (((Comparable) childMaxL).compareTo((Comparable)childMaxR) > 0) {
				max = childMaxL;
			} else {max = childMaxR;}
		} else if(childMaxL == null) {
			max = childMaxR;
		} else if (childMaxR == null) {
			max = childMaxL;
		}
		return max;
	}

	public E getMax3() {
		if (!isEmpty()) {
			return getMax3Aux(this);
		}
		return null;
	}

	private E getMax3Aux(BTreeIF<E> t) {
		BTreeIF<E> rightC = null;
		BTreeIF<E> leftC = null;

		if (t.getRightChild() != null) {
			rightC = t.getRightChild();
		}

		if (t.getLeftChild() != null) {
			leftC = t.getLeftChild();
		}
		
		if (rightC != null && leftC != null){
			if (((Comparable)rightC.getRoot()).compareTo((Comparable)leftC.getRoot()) > 0) {

				if (rightC.isLeaf()) {
					return rightC.getRoot();
				} 
				
				else {
					return getMax2Aux(rightC);
				}
			} 
			
			else {
				
				if (leftC.isLeaf()) {
					return leftC.getRoot();
				} 
				
				else {
					return getMax2Aux(leftC);
				}
			}

		} else if (rightC != null) {

			if (rightC.isLeaf()) {
				return rightC.getRoot();
			} 
			
			else {
				return getMax2Aux(rightC);
			}
		} 
		
		else if (leftC != null) {
			
			if (leftC.isLeaf()) {
				return leftC.getRoot();
			} 
			
			else {
				return getMax2Aux(leftC);
			}
		}
		
	return null;
	}

	/** Alternativa mas concisa propuesta por ChatGPT */
	private E getMax4Aux(BTreeIF<E> t) {
		// Si sólo hay un hijo, seguimos por él
		BTreeIF<E> child = null;
		if (t.getLeftChild() != null && t.getRightChild() != null) {
			// Elegimos el hijo cuyo root es mayor
			if (((Comparable) t.getRightChild().getRoot()).compareTo(t.getLeftChild().getRoot()) > 0) {
				child = t.getRightChild();
			} else {
				child = t.getLeftChild();
			}
		} else if (t.getRightChild() != null) {
			child = t.getRightChild();
		} else if (t.getLeftChild() != null) {
			child = t.getLeftChild();
		} else {
			// No hay hijos, no debería ocurrir en este supuesto, pero por seguridad
			return null;
		}

		// Si el hijo elegido es hoja, devolvemos su valor
		if (child.isLeaf()) {
			return child.getRoot();
		}
		// Si no es hoja, seguimos el camino recursivamente
		return getMax3Aux(child);
	}

	public int calculateHeight() {
		int leftH = 0;
		int rightH = 0;
		if(this.leftChild != null){
			leftH = this.leftChild.getHeight();
		} 
		if(this.rightChild != null ){
			rightH = this.rightChild.getHeight();
		}
		return 1 + Math.max(leftH, rightH);
	}

	public int calculateFanOut() {
		int leftF = 0;
		int rightF = 0;
		if(this.leftChild != null){
			leftF = this.leftChild.getFanOut();
		} 
		if(this.rightChild != null ){
			rightF = this.rightChild.getFanOut();
		}
		return Math.max(leftF, rightF);
	}


}

