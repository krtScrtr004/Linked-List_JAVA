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
        Node temp = this._head._next;
        int count = 0;
        while (count < index && temp != this._tail) {
            temp = temp._next;
            count++;
        }
        return temp;
    }

//    public List(final List other) {
//        // Copy constructor
//    }

    public final int size() { return this._size; }

    public final void insert(final int index, final int data) {  // zero based index
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

    public final void print() {
        Node temp = this._head._next;
        while (temp != this._tail) {
            System.out.println(temp._data);
            temp = temp._next;
        }
    }

    public static void main(String[] args) {
        List myList = new List();
        myList.insert(0, 0);
        myList.insert(1, 1);
        myList.insert(1, 4);
        myList.insert(0, 10);
        myList.insert(myList.size(), 100); 
        myList.print();
    }
}