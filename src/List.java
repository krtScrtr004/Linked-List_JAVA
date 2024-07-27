public class List {
    private class Node {
        private Integer _data;
        private Node _prev, _next;

        public Node() {
            this._data = null;
            this._prev = null;
            this._next = null;
        }
    }

    private Node _head, _tail;
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

    private final Node nodeAtPos(final int index) {
        Node temp = this._head;
        int count = 0;
        while (count < index && temp._next != null && temp._next != this._tail) {
            temp = temp._next;
            count++;
        }
        return temp;
    }

    public List(final List other) {
        // Copy constructor
    }

    public final void insert(final int index, final int data) {  // zero based index
        Node temp = nodeAtPos(index);
        Node newNode = new Node();
        newNode._data = data;
        newNode._prev = temp._next._prev;
        newNode._next = temp._next;
        temp._next = newNode;
        newNode._next._prev = newNode;
    }

    public final void print() {
        Node temp = this._head._next;
        while (temp != this._tail) {
            System.out.println(temp._data);
            temp = temp._next;
        }
    }

    public static void main(String[] args) {
        List myList = new List();
        myList.insert(1, 0);
        myList.insert(2, 1);
        myList.insert(2, 4);
        myList.insert(1, 10);
        myList.print();
    }
}