// TODO: remove final keyword to methods

public class List {
    public static class Node {
        private Integer _data;
        private Node _prev, _next;

        public Node() {
            this._data = null;
            this._prev = null;
            this._next = null;
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


    public final int size() {
        return this._size;
    }

    public final Node getFirst() {
        return (_size > 0 ? this._head._next : null);
    }

    public final Node getLast() {
        return (_size > 0 ? this._tail._prev : null);
    }

    public final Node get(final int index) {
        return (_size > 0 ? nodeAtPos(index) : null);
    }

    // Append node at the end of the list
    public void add(final int data) {
        Node temp = this._tail._prev;
        Node newNode = new Node();
        newNode._data = data;

        newNode._prev = temp;
        newNode._next = this._tail;
        temp._next = newNode;
        this._tail._prev = newNode;
        this._size++;
    }

    // Append the node before the current node at index 'index'
    public final void add(final int index, final int data) {  // zero based index
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

    public void addFirst(final int data) {
        Node temp = this._head._next;
        Node newNode = new Node();
        newNode._data = data;

        newNode._prev = this._head;
        newNode._next = temp;
        temp._prev = newNode;
        this._head._next = newNode;
        this._size++;
    }

    public void addLast(final int data) {
        add(data);
    }

    public final void remove(final int index) {
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

    public final void clear() {
        int counter = 0;
        while (counter < _size) {
            remove(counter);
        }
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
//        myList.add(0, 0);
//        myList.add(1, 1);
//        myList.add(1, 4);
//        myList.add(0, 10);
//        myList.add(myList.size(), 100);
//        myList.add(500);
//        myList.add(510);
//        myList.add(520);
        myList.addFirst(78);
        myList.addFirst(8);
        myList.addLast(100);
        myList.addLast(120);
        myList.print();

//        System.out.println();
//
//        myList.clear();
//        myList.print();
    }
}