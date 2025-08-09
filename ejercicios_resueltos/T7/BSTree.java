import java.util.NoSuchElementException;

public class BSTree<E extends Comparable<E>> extends Tree<E> implements BSTreeIF<E> {

	private BSTree<E> leftChild;
	private BSTree<E> rightChild;
	private BSTreeIF.Order insertionOrder;

	public BSTree() {
		super();
		this.leftChild = null;
		this.rightChild = null;
		this.insertionOrder = BSTreeIF.Order.ASCENDING;
	}

	public BSTree(BSTreeIF.Order instOrder) {
		super();
		this.leftChild = null;
		this.rightChild = null;
		this.insertionOrder = instOrder;
	}

	/* Devuelve el hijo izquierdo del Arbol */
	public BSTree<E> getLeftChild() {
		return this.leftChild;
	}

	/* Devuelve el hijo derecho del Arbol */
	public BSTree<E> getRightChild() {
		return this.rightChild;
	}

	/* Modifica la raíz */
	private void setRoot(E e) {
		this.root = e;
	}

	/* Modifica el hijo izquierdo */
	private void setLeftChild(BSTree<E> child) {
		this.leftChild = child;
	}

	/* Modifica el hijo derecho */
	private void setRightChild(BSTree<E> child) {
		this.rightChild = child;
	}

	/*
	 * Metodo que devuelve el padre del nodo buscado.
	 **/
	private BSTree<E> searchParent(BSTree<E> node, BSTree<E> root) {
		if (root == null) {
			return null;
		} else {
			if (node.getRoot().compareTo(root.getRoot()) == 0) {
				return null;
			} else if (placeOnTheLeft(node, root)) {
				BSTree<E> child = searchParent(node, root.getLeftChild());
				if (child == null) {
					return root;
				}
				return child;
			} else {
				BSTree<E> child = searchParent(node, root.getRightChild());
				if (child == null) {
					return root;
				}
				return child;
			}
		}
	}

	private boolean placeOnTheLeft(BSTreeIF<E> node, BSTreeIF<E> parent) {
		return (this.getOrder().equals(Order.ASCENDING) && node.getRoot().compareTo(parent.getRoot()) < 0)
				|| (this.getOrder().equals(Order.DESCENDING) && node.getRoot().compareTo(parent.getRoot()) > 0);
	}
	
	public void add(E newValue) {

		if (this.getRoot() == null) {
			this.setRoot(newValue);

		} else {
			BSTree<E> newNode = new BSTree<>(this.getOrder());
			newNode.setRoot(newValue);
			BSTree<E> parent = searchParent(newNode, this);

			if (parent == null) {
				this.setRoot(newValue);
			} else if (placeOnTheLeft(newNode, parent)) {
				if(parent.getLeftChild()==null)
				{
					parent.setLeftChild(newNode);
				}
			} else {
				if(parent.getRightChild()==null)
				{
					parent.setRightChild(newNode);
				}
			}
		}
	}

	public void remove(E newValue) {
		if (this.getRoot() != null) {
			
			if (this.getRoot().compareTo(newValue) == 0 && this.getNumChildren() == 0) {
				this.setRoot(null);
			} else {
				
				BSTree<E> newNode = new BSTree<>(this.getOrder());
				newNode.setRoot(newValue);
				BSTree<E> parent = searchParent(newNode, this);
				if(parent==null)
				{
					if (this.getLeftChild() == null)
					{
						this.setRoot(this.getRightChild().getRoot());						
						this.setLeftChild(this.getRightChild().getLeftChild());
						this.setRightChild(this.getRightChild().getRightChild());
					}
					else if(this.getRightChild()==null)
					{
						this.setRoot(this.getLeftChild().getRoot());						
						this.setRightChild(this.getLeftChild().getRightChild());
						this.setLeftChild(this.getLeftChild().getLeftChild());
					}
					else
					{
						this.setRightChild(replaceNode(this, this.getRightChild()));
					}					
				}
				else
				{
					BSTree<E> child = null;
					if (placeOnTheLeft(newNode, parent)) 
					{
						child = parent.getLeftChild();
						if (child.getLeftChild() == null)
						{
							parent.setLeftChild(child.getRightChild());
						}
						else if(child.getRightChild()==null)
						{
							parent.setLeftChild(child.getLeftChild());
						}
						else
						{
							child.setRightChild(replaceNode(child, child.getRightChild()));
						}	
					} 
					else {
						child = parent.getRightChild();
						if (child.getLeftChild() == null)
						{
							parent.setRightChild(child.getRightChild());
						}
						else if(child.getRightChild()==null)
						{
							parent.setRightChild(child.getLeftChild());
						}
						else
						{
							child.setRightChild(replaceNode(child, child.getRightChild()));
						}
					}
				}				
			}
		}
	}
	
	/**
	 * Metodo que reemplaza el valor de uno nodo a eliminar con dos hijos por su nodo sucesor.
	 * */
	private BSTree<E> replaceNode(BSTree<E> nodeToRemove, BSTree<E> succesor) {
		if (succesor.getLeftChild() == null) {
			nodeToRemove.setRoot(succesor.getRoot());
			return succesor.getRightChild();
		} else {
			succesor.setLeftChild(replaceNode(nodeToRemove, succesor.getLeftChild()));
			return succesor;
		}
	}

	public Order getOrder() {
		return this.insertionOrder;
	}

	public int getNumChildren() {
		int nC = 0;
		if (this.leftChild != null) {
			nC++;
		}
		if (this.rightChild != null) {
			nC++;
		}
		return nC;
	}

	public int getFanOut() {
		if (getNumChildren() == 2) {
			return 2;
		}
		if (this.leftChild != null) {
			return Math.max(1, this.leftChild.getFanOut());
		}
		if (this.rightChild != null) {
			return Math.max(1, this.rightChild.getFanOut());
		}
		return 0;
	}

	/* Devuelve la altura del árbol */
	public int getHeight() {
		if ( isEmpty() ) { return -1; }
		int hLC = -1;
		if ( this.leftChild != null ) { hLC = this.leftChild.getHeight(); }
		int hRC = -1;
		if ( this.rightChild != null ) { hRC = this.rightChild.getHeight(); }
		return 1 + ((hLC > hRC)?hLC:hRC);
	}

	/* Reimplementación/Especialización de algunos métodos de Collection */
	
	/* Devuelve el número de nodos del árbol */
	public int size() {
		if ( isEmpty() ) { return 0; }
		int s = 1;
		if ( this.leftChild != null ) { s = s + this.leftChild.size(); }
		if ( this.rightChild != null ) { s = s + this.rightChild.size(); }
		return s;
	}

	/* Nos dice si el árbol binario de búsqueda está vacío o no */
	public boolean isEmpty() {
		return (this.getRoot() == null && this.getRightChild() == null && this.getLeftChild() == null);
	}
	
	/* Vacía el árbol binario */
	public void clear() {
		super.clear();
		this.leftChild = null;
		this.rightChild = null;
	}
	
	public boolean contains(E newValue) {
		if (this.getRoot() == null) {
			return false;
		}

		BSTree<E> newNode = new BSTree<>(this.getOrder());
		newNode.setRoot(newValue);
		BSTree<E> parent = searchParent(newNode, this);

		if (parent == null) {
			return true;
		} else {
			if (placeOnTheLeft(newNode, parent)) {
				return parent.getLeftChild() != null;
			} else {
				return parent.getRightChild() != null;
			}
		}
	}

	
	public IteratorIF<E> iterator(Object mode) {
		if (mode.equals(IteratorModes.DIRECTORDER)) {
			QueueIF<E> queue = new Queue<E>();
			inorder(this, queue);
			return queue.iterator();
		} else if (mode.equals(IteratorModes.REVERSEORDER)) {
			StackIF<E> stack = new Stack<E>();
			inorder(this, stack);
			return stack.iterator();
		}
		return null;
	}
	
	/* Recorre el árbol en inorden */
	private void inorder(BSTree<E> t, QueueIF<E> q) {
		if ( !t.isEmpty() ) {
			if ( t.getLeftChild() != null ) { inorder(t.getLeftChild(),q); }
			q.enqueue(t.getRoot());
			if ( t.getRightChild() != null ) { inorder(t.getRightChild(),q); }
		}
	}
	
	/* Recorre el árbol en inorden */
	private void inorder(BSTree<E> t, StackIF<E> stack) {
		if ( !t.isEmpty() ) {
			if ( t.getLeftChild() != null ) { inorder(t.getLeftChild(),stack); }
			stack.push(t.getRoot());
			if ( t.getRightChild() != null ) { inorder(t.getRightChild(),stack); }
		}
	}
	
	
	/* Recorre el arbol en preorden */	
	public IteratorIF<E> preOrden(BSTree<E> t, Queue<E> q) {
		if ( !t.isEmpty() ) {
			q.enqueue(t.getRoot());
			if ( t.getLeftChild() != null ) { preOrden(t.getLeftChild(),q); }			
			if ( t.getRightChild() != null ) { preOrden(t.getRightChild(),q); }
			return q.iterator();
		}
		return null;
	}
	
	/* Recorre el arbol en postorden */	
	public IteratorIF<E> postOrden(BSTree<E> t, Queue<E> q) {
		if ( !t.isEmpty() ) {			
			if ( t.getLeftChild() != null ) { postOrden(t.getLeftChild(),q); }			
			if ( t.getRightChild() != null ) { postOrden(t.getRightChild(),q); }
			q.enqueue(t.getRoot());
			return q.iterator();
		}
		return null;
	}

	public E getMin() {
		if(this.isEmpty()){
			throw new NoSuchElementException("El árbol está vacío");
		}
		BSTreeIF<E> current = this;
		while (current.getLeftChild() != null){
			current = current.getLeftChild();
		}
		return current.getRoot();

	} // El coste computacional es funcion del numero de hijos izquierdos, partiendo
		// desde la raiz O(n). No es necesario recorrer todos los nodos.
		// El coste computacional en un BTree seria igual al numero total de nodos. Es
		// necesario recorrer todos los nodos

	public E getMax() {
		if (this.isEmpty()) {
			throw new NoSuchElementException("El árbol está vacío");
		}
		BSTreeIF<E> current = this;
		while (current.getRightChild() != null) {
			current = current.getRightChild();
		}
		return current.getRoot();

	} // El coste computacional es funcion del numero de hijos derechos, partiendo
	// desde la raiz No es necesario recorrer todos los nodos.
	// El coste computacional en un BTree seria igual al numero total de nodos. Es
	// necesario recorrer todos los nodos

	public ListIF<E> getInBetween(BSTreeIF<E> bs, E v1, E v2){
		ListIF<E> inB = new List<E>();
		if (!bs.isEmpty()){
			E max = null;
			E min = null;
			if (v1 != null && v2 != null) {
				if (v1.compareTo(v2) >= 0) {
					max = v1;
					min = v2;
				} else {
					max = v2;
					min = v1;
				}
			}
			inB = getInBetweenAux(bs, inB, min, max);
			return inB;
		}
		return inB;
	} // El tamaño del problema depende del numero de nodos cuyo valor sea igual o se
	// encuentre entre los valores minimo y máximo
	// En el peor de los casos O(n) donde n es el numero de nodos del arbol completo

	private ListIF<E> getInBetweenAux(BSTreeIF<E> bs, ListIF<E> inB, E min, E max){
		if(!bs.isEmpty() && min != null && max != null && inB != null){

			E nValue = bs.getRoot();
			
			if (nValue.compareTo(max) < 0 && bs.getLeftChild() != null) {
				getInBetweenAux(bs.getLeftChild(), inB, min, max);
			}

			if (nValue.compareTo(max) <= 0 && nValue.compareTo(min) >= 0) {
				inB.insert(inB.size(), nValue);
			}
			
			if (nValue.compareTo(min) > 0 && bs.getRightChild() != null) {
				getInBetweenAux(bs.getRightChild(), inB, min, max);
			}
		}
		return inB;
	} 

	public boolean isBSTree(BSTreeIF<E> bs){
		return isBSTreeAux(bs, null, null);
	}

    private boolean isBSTreeAux(BSTreeIF<E> bs, E min, E max) {
		E value = bs.getRoot();

		if(bs.isEmpty()){
			return true;
		}
		if(max != null && value.compareTo(max) >= 0){
			return false;
		}
		if (min != null && value.compareTo(min) <= 0){
			return false;
		}
		return isBSTreeAux(bs.getLeftChild(), min, value) 
		&& isBSTreeAux(bs.getRightChild(), value, max);
	}

	public BSTreeIF<E> genBST (ListIF<E> list){
		BSTreeIF<E> bst = new BSTree<>();
		if(!list.isEmpty()){
			genBSTAux(list, bst, null, null);
		}
		return bst;
	}

    private BSTreeIF<E> genBSTAux(ListIF<E> list, BSTreeIF<E> bst, E min, E max) {
        while(!list.isEmpty()){

			return genBSTAux(list, bst, min, max);
		} 
		return bst;
    }

	/**
	 * 
	 * EJERCICIO 4
	 * 
	 * ¿Es posible generar unívocamente un árbol binario de búsqueda a partir de su
	 * preorden? ¿Y de su inorden? ¿Y de su postorden? Justifique la respuesta.
	 * 
	 * No, no es posible generar unívocamente un árbol binario de búsqueda (BST)
	 * únicamente a partir de su recorrido en preorden, inorden o postorden.
	 * 
	 * Preorden: El recorrido en preorden presenta los nodos en el orden en que se
	 * visitarían (raíz, subárbol izquierdo, subárbol derecho), pero no proporciona
	 * información suficiente sobre la estructura del árbol ni sobre cómo se
	 * relacionan los nodos entre sí. Por tanto, diferentes árboles pueden tener el
	 * mismo recorrido en preorden.
	 * 
	 * Inorden: En un árbol binario de búsqueda, el recorrido inorden siempre da los
	 * elementos ordenados de menor a mayor. Esto significa que el recorrido inorden
	 * de cualquier BST con los mismos elementos será idéntico, independientemente
	 * de su estructura. Por lo tanto, no es unívoco.
	 * 
	 * Postorden: El recorrido postorden muestra los nodos en el orden subárbol
	 * izquierdo, subárbol derecho y luego la raíz, pero tampoco aporta la
	 * información necesaria para reconstruir de forma única la estructura del
	 * árbol.
	 * 
	 * Justificación:
	 * La información que contienen estos recorridos por sí sola no permite
	 * distinguir la estructura específica del árbol, ya que varios árboles
	 * distintos pueden compartir el mismo recorrido preorden, inorden o postorden.
	 * Para reconstruir unívocamente un BST, se necesitan al menos dos recorridos
	 * diferentes (por ejemplo, preorden e inorden), que en conjunto sí contienen
	 * suficiente información para determinar la estructura exacta del árbol.
	 * 
	 */

	 /**
	 * Reconstruye un árbol binario de búsqueda a partir de las listas
	 * de recorrido en preorden e inorden.
	 * 
	 * @param preorder La lista con el recorrido en preorden.
	 * @param inorder  La lista con el recorrido en inorden.
	 * @return El árbol binario de búsqueda reconstruido.
	 */
	public BSTree<E> buildTreeFromPreInOrder(ListIF<E> preorder, ListIF<E> inorder) {
		// Llamamos al método auxiliar recursivo con los límites de las listas
		return buildTreeAux(preorder, 1, preorder.size(), inorder, 1, inorder.size());
	}

	/**
	 * Método auxiliar recursivo para reconstruir el árbol.
	 * Utiliza los índices para trabajar con las sublistas.
	 * 
	 * @param preorder Lista de preorden
	 * @param preStart Índice inicial en preorden
	 * @param preEnd   Índice final en preorden
	 * @param inorder  Lista de inorden
	 * @param inStart  Índice inicial en inorden
	 * @param inEnd    Índice final en inorden
	 * @return El subárbol reconstruido
	 */
	private BSTree<E> buildTreeAux(ListIF<E> preorder, int preStart, int preEnd,
			ListIF<E> inorder, int inStart, int inEnd) {
		// Caso base: si no hay elementos, retornamos un árbol vacío
		if (preStart > preEnd || inStart > inEnd) {
			return new BSTree<E>(this.getOrder());
		}

		// El primer elemento de preorder es la raíz del subárbol
		E rootValue = preorder.get(preStart);

		// Creamos el nodo raíz
		BSTree<E> root = new BSTree<E>(this.getOrder());
		root.setRoot(rootValue);

		// Buscamos la posición de la raíz en la lista de inorden
		int rootIndexInInorder = inStart;
		while (rootIndexInInorder <= inEnd && !inorder.get(rootIndexInInorder).equals(rootValue)) {
			rootIndexInInorder++;
		}

		// Calculamos el tamaño del subárbol izquierdo
		int leftTreeSize = rootIndexInInorder - inStart;

		// Construimos el subárbol izquierdo recursivamente
		root.setLeftChild(
				buildTreeAux(preorder, preStart + 1, preStart + leftTreeSize,
						inorder, inStart, rootIndexInInorder - 1));

		// Construimos el subárbol derecho recursivamente
		root.setRightChild(
				buildTreeAux(preorder, preStart + leftTreeSize + 1, preEnd,
						inorder, rootIndexInInorder + 1, inEnd));

		return root;
	}

	/**
	 * Reconstruye un árbol binario de búsqueda a partir de las listas
	 * de recorrido en postorden e inorden.
	 * 
	 * @param postorder La lista con el recorrido en postorden.
	 * @param inorder   La lista con el recorrido en inorden.
	 * @return El árbol binario de búsqueda reconstruido.
	 */
	public BSTree<E> buildTreeFromPostInOrder(ListIF<E> postorder, ListIF<E> inorder) {
		// Llamamos al método auxiliar recursivo con los límites de las listas
		return buildTreeAuxPost(postorder, 1, postorder.size(), inorder, 1, inorder.size());
	}

	/**
	 * Método auxiliar recursivo para reconstruir el árbol desde postorden e
	 * inorden.
	 * Utiliza los índices para trabajar con las sublistas.
	 * 
	 * @param postorder Lista de postorden
	 * @param postStart Índice inicial en postorden
	 * @param postEnd   Índice final en postorden
	 * @param inorder   Lista de inorden
	 * @param inStart   Índice inicial en inorden
	 * @param inEnd     Índice final en inorden
	 * @return El subárbol reconstruido
	 */
	private BSTree<E> buildTreeAuxPost(ListIF<E> postorder, int postStart, int postEnd,
			ListIF<E> inorder, int inStart, int inEnd) {
		// Caso base: si no hay elementos, retornamos un árbol vacío
		if (postStart > postEnd || inStart > inEnd) {
			return new BSTree<E>(this.getOrder());
		}

		// El último elemento de postorder es la raíz del subárbol
		E rootValue = postorder.get(postEnd);

		// Creamos el nodo raíz
		BSTree<E> root = new BSTree<E>(this.getOrder());
		root.setRoot(rootValue);

		// Buscamos la posición de la raíz en la lista de inorden
		int rootIndexInInorder = inStart;
		while (rootIndexInInorder <= inEnd && !inorder.get(rootIndexInInorder).equals(rootValue)) {
			rootIndexInInorder++;
		}

		// Calculamos el tamaño del subárbol izquierdo
		int leftTreeSize = rootIndexInInorder - inStart;

		// Construimos el subárbol izquierdo recursivamente
		root.setLeftChild(
				buildTreeAuxPost(postorder, postStart, postStart + leftTreeSize - 1,
						inorder, inStart, rootIndexInInorder - 1));

		// Construimos el subárbol derecho recursivamente
		root.setRightChild(
				buildTreeAuxPost(postorder, postStart + leftTreeSize, postEnd - 1,
						inorder, rootIndexInInorder + 1, inEnd));

		return root;
	}

	public boolean isBalanced(BSTreeIF<E> bst){
		if (bst.isEmpty()){
			return true;
		}
		return isBalancedAux(bst);
	}

    private boolean isBalancedAux(BSTreeIF<E> bst) {
		if (bst == null){
			return true;
		} 
		int leftH = 0;
		int rightH = 0;
        if (bst.getLeftChild() != null){
			leftH = bst.getLeftChild().getHeight();
		}
		if (bst.getRightChild() != null) {
			leftH = bst.getRightChild().getHeight();
		}
		return (Math.abs(leftH - rightH) <= 1)
		&& isBalancedAux(bst.getLeftChild())
		&& isBalancedAux(bst.getRightChild());
    }

	
	/**
	 * Comprueba si un árbol binario de búsqueda está balanceado en altura.
	 * Un árbol está balanceado si para cada nodo, la diferencia de alturas entre
	 * sus hijos izquierdo y derecho no es mayor que 1.
	 * Esta versión es eficiente (O(n)), ya que calcula la altura y el equilibrio en
	 * una sola pasada por el árbol.
	 */
	public boolean isBalancedGPT(BSTreeIF<E> bst) {
		// Llama a la función auxiliar y comprueba si el resultado es distinto de -1.
		// Si es distinto de -1, el árbol está balanceado.
		return checkBalanced(bst) != -1;
	}

	/**
	 * Función auxiliar que recorre el árbol en postorden.
	 * Devuelve la altura del árbol si está balanceado.
	 * Si encuentra algún nodo desequilibrado, devuelve -1.
	 */
	private int checkBalanced(BSTreeIF<E> bst) {
		// Caso base: si el árbol es nulo o vacío, su altura es 0 y está balanceado.
		if (bst == null || bst.isEmpty()) {
			return 0;
		}

		// Calcula la altura del hijo izquierdo.
		int leftHeight = checkBalanced(bst.getLeftChild());
		// Si el hijo izquierdo no está balanceado, propaga el error hacia arriba.
		if (leftHeight == -1)
			return -1;

		// Calcula la altura del hijo derecho.
		int rightHeight = checkBalanced(bst.getRightChild());
		// Si el hijo derecho no está balanceado, propaga el error hacia arriba.
		if (rightHeight == -1)
			return -1;

		// Si la diferencia de alturas es mayor que 1, el árbol no está balanceado.
		if (Math.abs(leftHeight - rightHeight) > 1)
			return -1;

		// Si está balanceado, devuelve la altura del árbol actual.
		return 1 + Math.max(leftHeight, rightHeight);
	}
}


