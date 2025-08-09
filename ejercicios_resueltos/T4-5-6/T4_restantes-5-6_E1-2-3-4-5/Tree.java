

public abstract class Tree<E> extends Collection<E> implements TreeIF<E> {

	protected E root;
	protected TreeIF<E> parent;
	protected int fanout;
	protected int height;

	Tree() {
		super();
		this.root = null;
		this.parent = null;
		this.fanout = 0;
		this.height = 0;
	} // O(1)
	
	/* Devuelve el elemento situado en la raíz del árbol */
	public E getRoot() {
		return this.root;
	} // O(1)

	public TreeIF<E> getParent() {
		return this.parent;
	}

	public void increaseSize(){
		if(this.parent != null){
			++size;
			parent.increaseSize();
		} else {
			++size;
		}
	}

	public void decreaseSize() {
		if (this.parent != null) {
			--size;
			parent.decreaseSize();
		} else {
			--size;
		}
	}

	/* Decide si el árbol es una hoja */
	public boolean isLeaf() {
		return this.root!=null && getNumChildren() == 0;
	} // No es posible obtener el coste porque getNumChildren es un método abstracto en esta clase

	/* Reimplementación de algunos métodos de Collection */
	
	/* Decide si el árbol es vacío */
	public boolean isEmpty() {
		return this.root==null && this.parent==null && this.size==0 && this.fanout == 0 && this.height == 0;
	} // O(1)
	
	/* Vacía el árbol */
	public void clear() {
		super.clear();
		this.root = null;
		this.parent = null;
		this.fanout = 0;
		this.height = 0;
	} // O(1)

	protected void updateHeight(){
		int newHeight = calculateHeight();
		if(newHeight != height){
			height = newHeight;
			if (this.parent != null) {
				((Tree<E>) this.parent).updateHeight();
			}
		}
	}

	protected void updateFanOut(){
		int newFanot = calculateFanOut();
		if (newFanot != fanout) {
			fanout = newFanot;
			if (this.parent != null) {
				((Tree<E>) this.parent).updateFanOut();
			}
		}
	}
	
	abstract public int getNumChildren();

	abstract public IteratorIF<E> iterator(Object mode);

	abstract public boolean contains(E e);

	abstract public int calculateHeight();

	abstract public int calculateFanOut();

}
