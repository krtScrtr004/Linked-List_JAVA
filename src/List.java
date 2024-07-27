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

    public List(final List other) {
        // Copy constructor
    }

    public static void main(String[] args) {
    }
}