import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private Node first = null;
    private Node last = null;
    private int size = 0;

    private class Node {
        private Node next = null;
        private Node prev = null;
        private Item item;
        Node(Node prev, Node next, Item item) {
            this.next = next;
            this.prev = prev;
            this.item = item;
        }
    }

    public Deque() {
        // construct an empty deque

    }

    public boolean isEmpty() {
        // is the deque empty?
        return first == null && last == null;
    }

    public int size() {
        // return the number of items on the deque
        return size;
    }

    public void addFirst(Item item) {
        // add the item to the front
        if (item == null) throw new java.lang.NullPointerException();
        else {
            Node node = new Node(null, first, item);

            if (first != null) first.prev = node; // not empty
            else last = node; // empty

            first = node;
            size++;
        }
    }

    public void addLast(Item item) {
        // add the item to the end
        if (item == null) throw new java.lang.NullPointerException();
        else {
            Node node = new Node(last, null, item);

            if (last != null) last.next = node;
            else first = node;

            last = node;
            size++;
        }
    }

    public Item removeFirst() {
        // remove and return the item from the front
        if (isEmpty()) throw new java.util.NoSuchElementException(); // removing from an empty deque - exception
        Item result = first.item;

        if (first.next == null) {
            first = null; // removing from a one-element deque - clear it
            last = null;
        } else {
            first.next.prev = null; // removing from a normal deque - only care about `first.next`
            first = first.next;
        }

        size--;
        return result;
    }

    public Item removeLast() {
        // remove and return the item from the end
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item result = last.item;

        if (last.prev == null) {
            first = null;
            last = null;
        } else {
            last.prev.next = null;
            last = last.prev;
        }

        size--;
        return result;
    }

    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new Itr();
    }

    private class Itr implements Iterator<Item> {
        private Node thisNode = first;

        @Override
        public boolean hasNext() {
            return thisNode != null; // since the ptr is always at the actual "next", just check if it is null
        }

        @Override
        public Item next() {
            if (thisNode == null) throw new java.util.NoSuchElementException();
            Item temp = thisNode.item;
            thisNode = thisNode.next; // move the ptr to the actual "next"
            return temp; // return this
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        // unit testing (optional)
        // Deque<Integer> deque = new Deque<>();
        // for (int i=0; i<5; i++) {
        //     deque.addFirst(i);
        // }

        // System.out.println(deque.last.prev);


        // System.out.println(deque.size());
        // for (int i=5; i<10; i++) {
        //     deque.addLast(i);
        // }
        // System.out.println(deque.size());
        // System.out.println();

        // for (int i : deque) {
        //     System.out.print(i+" ");
        // }
        // System.out.println();

        // for (int i=0; i<5; i++) {
        //     deque.removeLast();
        //     for (int item : deque) {
        //         System.out.print(item+" ");
        //     }
        //     System.out.println();
        //     System.out.println(deque.size());
        //     System.out.println();
        // }
        // for (int i=0; i<5; i++) {
        //     deque.removeFirst();
        //     for (int item : deque) {
        //         System.out.print(item+" ");
        //     }
        //     System.out.println();
        //     System.out.println(deque.size());
        //     System.out.println();
        // }



    }
}