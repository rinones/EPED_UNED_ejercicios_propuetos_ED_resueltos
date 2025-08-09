
public class StackUtils<E> extends Stack<E>{

    public StackIF<E> invierteIt1(StackIF<E> s){
        StackIF<E> invertedS = new Stack<>();
        IteratorIF<E> i = s.iterator();
        while (i.hasNext()) {
            invertedS.push(i.getNext());
        }
        return invertedS;
    } // O(n) donde n = s.size()

    public StackIF<E> invierteIt2(StackIF<E> s) {
        StackIF<E> invertedS = new Stack<>();
        StackIF<E> copy = new Stack<>((Stack<E>)s);
        while (copy.size() > 0){
            invertedS.push(s.getTop());
            copy.pop();
        }
        return invertedS;
    } // O(n) donde n = s.size()

    public StackIF<E> invierteRe(StackIF<E> s) {
        StackIF<E> inS = new Stack<>();
        StackIF<E> copy = new Stack<>((Stack<E>) s);
        invierteReAux(copy, inS);
        return inS;
    } //O(n) donde n = s.size()

    private void invierteReAux(StackIF<E> copy, StackIF<E> inS) {
        if (!copy.isEmpty()) return;
        inS.push(copy.getTop());
        copy.pop();
        invierteReAux(copy, inS);
        
    }

    public StackIF<E> rotateS(StackIF<E> s, int c){
        StackIF<E> copy = new Stack<>((Stack<E>)s);
        StackIF<E> aux = new Stack<>();
        StackIF<E> result = new Stack<>();
        int n = s.size();
        int rot = (((c % n) + n) % n);
            for (int i = 0; i < rot; i++){
                aux.push(copy.getTop());
                copy.pop();
            }
            while (!aux.isEmpty()){
                result.push(aux.getTop());
                aux.pop();
            }
            while (!copy.isEmpty()) {
                aux.push(copy.getTop());
                copy.pop();
            }
            while (!aux.isEmpty()) {
                result.push(aux.getTop());
                aux.pop();
            }
        return result;
    } // O(n) donde n = s.size()

}
