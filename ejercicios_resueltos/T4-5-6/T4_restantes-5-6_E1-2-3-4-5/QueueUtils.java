public class QueueUtils<E> extends Queue{
    public QueueIF<E> invierteIt1(QueueIF<E> s) {
        QueueIF<E> inS = new Queue<>();
        StackIF<E> auxS = new Stack<>();
        IteratorIF<E> i = s.iterator();

        while (i.hasNext()) {
            auxS.push(i.getNext());
        }

        IteratorIF<E> iAux = auxS.iterator();

        while (iAux.hasNext()) {
            inS.enqueue(iAux.getNext());
        }
        return inS;
    } // O(n) donde n = s.size()

    public QueueIF<E> invierteIt2(QueueIF<E> s) {
        QueueIF<E> inS = new Queue<>();
        StackIF<E> auxS = new Stack<>();
        QueueIF<E> copy = new Queue<>((Queue<E>) s);
        while (!copy.isEmpty()) {
            auxS.push(copy.getFirst());
            copy.dequeue();
        }
        while (!auxS.isEmpty()){
            inS.enqueue(auxS.getTop());
            auxS.pop();
        }
        return inS;
    } // O(n) donde n = s.size()

    public QueueIF<E> invierteRe(QueueIF<E> s) {
        QueueIF<E> inS = new Queue<>();
        QueueIF<E> copy = new Queue<>((Queue<E>) s);
        StackIF<E> auxS = new Stack<>();
        invierteReAux(copy, inS, auxS);
        return inS;
    } // O(n) donde n = s.size()

    private void invierteReAux(QueueIF<E> copy, QueueIF<E> inS, StackIF<E> auxS) {
        if(!copy.isEmpty()){
            auxS.push(copy.getFirst());
            copy.dequeue();
            invierteReAux(copy, inS, auxS);
        } else {
            while(!auxS.isEmpty()){
                inS.enqueue(auxS.getTop());
                auxS.pop();
            }
        }
    }

    public QueueIF<E> rotateQUtils(QueueIF<E> q, int c) {
        QueueIF<E> copy = new Queue<>((Queue<E>) q);
        int n = size();
        int rot = (((c % n) + n) % n);
        for (int i = 0; i < rot; i++) {
            copy.enqueue(copy.getFirst());
            copy.dequeue();
        }
        return copy;
    } //O(n) donde n es q.size()
}
