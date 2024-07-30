import java.util.Objects;

public class List <T> {
    public class Node {
        private T _data;
        private Node _prev, _next;

        public Node() {
            this._data = null;
            this._prev = null;
            this._next = null;
        }

        public void unlink() {
            this._prev = null;
            this._next = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (o == null || getClass() != o.getClass())
                return false;

            Node node = (Node) o;
            return (Objects.equals(this._data, node._data));
        }

        @Override
        public int hashCode() {
            return (_data != null ? _data.hashCode() : 0);
        }

        @Override
        public String toString() {
            return _data.toString();
        }
    }

    private final Node _head, _tail;
    private int _size;

    // Default constructor
    public List() {
        this._head = new Node();
        this._tail = new Node();

        this._head._next = _tail;
        this._head._prev = null;
        this._tail._prev = _head;
        this._tail._next = null;

        this._size = 0;
    }

//    public List(final List other) {
//        // Copy constructor
//    }

    private Node nodeAtPos(final int index) {
        Node temp = this._head._next;
        int count = 0;
        while (count < index && temp != this._tail) {
            temp = temp._next;
            count++;
        }
        return temp;
    }

    private Node searchNode(final Node node) {
        Node temp = this._head._next;
        while (temp != this._tail) {
            if (temp.equals(node))
                return temp;
            temp = temp._next;
        }
        return null;
    }


    public int size() {
        return this._size;
    }

    /* Node retrieval */

    public int indexOf(final Node node) {
        Node temp = this._head._next;
        for (int i = 0; temp != this._tail; i++) {
            if (temp.equals(node))
                return i;
            temp = temp._next;
        }
        return -1;
    }

    public Node getFirst() {
        return (_size > 0 ?  this._head._next : null);
    }

    public Node getLast() {
        return (_size > 0 ? this._tail._prev : null);
    }

    public Node get(final int index) {
        return (_size > 0 ? nodeAtPos(index) : null);
    }

    // return and unlink first node from the list
    public Node pollFirst() {
        if (this._size < 1)
            return null;

        Node temp = this._head._next;
        this._head._next = temp._next;
        this._head._next._prev = this._head;
        temp.unlink();
        this._size--;
        return temp;
    }

    // return and unlink last node from the list
    public Node pollLast() {
        if (this._size < 1)
            return null;

        Node temp = this._tail._prev;
        this._tail._prev = temp._prev;
        temp._prev._next = this._tail;
        temp.unlink();
        this._size--;
        return temp;
    }

    /* Node insertion methods */

    public void add(final int index, final T data) {  // zero based index
        if (index < 0)
            throw new IndexOutOfBoundsException("Index out of bounds!");

        Node temp = nodeAtPos(index);
        Node newNode = new Node();
        newNode._data = data;

        newNode._prev = temp._prev;
        newNode._next = temp;
        temp._prev._next = newNode;
        temp._prev = newNode;
        this._size++;
    }

    public void addFirst(final T data) {
        Node temp = this._head._next;
        Node newNode = new Node();
        newNode._data = data;

        newNode._prev = this._head;
        newNode._next = temp;
        temp._prev = newNode;
        this._head._next = newNode;
        this._size++;
    }

    public void addLast(final T data) {
        Node temp = this._tail._prev;
        Node newNode = new Node();
        newNode._data = data;

        newNode._prev = temp;
        newNode._next = this._tail;
        temp._next = newNode;
        this._tail._prev = newNode;
        this._size++;
    }

    /* Node removal methods */
    // remove first occurence of node 'node' in the last
    public Node remove(final Node node) {
        Node temp = searchNode(node);
        if (temp == null)
            return null;

        temp._prev._next = temp._next;
        temp._next._prev = temp._prev;
        this._size--;
        return temp;
    }

    // remove node at index 'index'
    public void remove(final int index) {
        if (index < 0 || index >= this._size)
            throw new IndexOutOfBoundsException("Index out of bounds!");

        // unlink node
        Node temp = nodeAtPos(index);
        temp._prev._next = temp._next;
        temp._next._prev = temp._prev;

        // nullify temp for garbage collection
        temp._prev = null;
        temp._next = null;
        this._size--;
    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(this._size - 1);
    }

    public void clear() {
        int counter = 0;
        while (counter < _size) {
            remove(counter);
        }
    }

    public void print() {
        Node temp = this._head._next;
        while (temp != this._tail) {
            System.out.println(temp._data);
            temp = temp._next;
        }
    }

    public static void main(String[] args) {
        List<String> myList = new List<>();
        myList.add(0, "a");
        myList.add(1, "b");
        myList.add(1, "c");
        myList.add(0, "d");
        myList.print();

        System.out.println();

      /*  myList.removeFirst();
        myList.removeLast();
        myList.print();*/

        List<String>.Node temp = myList.pollFirst();
        System.out.println(temp);

        System.out.println();

        myList.print();

        System.out.println();

        List<String>.Node temp1 = myList.pollLast();
        System.out.println(temp1);

        System.out.println();

        myList.print();
    }
}