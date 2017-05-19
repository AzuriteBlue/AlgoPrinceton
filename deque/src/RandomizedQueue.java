import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] mainArray;
    private int end; // the index of the element after the last element

    // @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        // construct an empty randomized queue
        mainArray = (Item[]) new Object[4];
        end = 0;
    }

    public boolean isEmpty() {
        // is the queue empty?
        return end == 0;
    }

    public int size() {
        // return the number of items on the queue
        return end;
    }

    // @SuppressWarnings("unchecked")
    private void resize() {
        int size = mainArray.length;

        if (end == size) {               // full
            Item[] tempArray = (Item[]) new Object[2*size];
            for (int i = 0; i < end; i++) {
                tempArray[i] = mainArray[i];
            }
            mainArray = tempArray;
        } else if (end <= size / 4 && end >= 2) {
            Item[] tempArray = (Item[]) new Object[size / 2];
            for (int i = 0; i < end; i++) {
                tempArray[i] = mainArray[i];
            }
            mainArray = tempArray;
        }
        // System.out.println("resized as " + mainArray.length);
    }

    public void enqueue(Item item) {
        // add the item
        if (item == null) throw new java.lang.NullPointerException();
        resize();
        mainArray[end] = item;
        end++;
    }

    public Item dequeue() {
        // remove and return a random item
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(end);
        swap(mainArray, index, end-1);

        Item temp = mainArray[end-1];
        mainArray[end-1] = null;
        end--;
        resize();

        return temp;
    }

    public Item sample() {
        // return (but do not remove) a random item
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(end);
        swap(mainArray, index, end-1);
        return mainArray[end-1];
    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new Itr();
    }

    private void shuffle(Item[] array) {
        if (array.length == 0 || array.length == 1) return;
        for (int i = 1; i < array.length; i++) {
            int index = StdRandom.uniform(0, i);
            swap(array, index, i);
        }
    }

    private void swap(Item[] array, int i1, int i2) {
        Item temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    private class Itr implements Iterator<Item> {

        private Item[] iterArray;
        private int curIndex;

        // @SuppressWarnings("unchecked")
        Itr() {
            iterArray = (Item[]) new Object[end];
            for (int i = 0; i < end; i++) {
                iterArray[i] = mainArray[i];
            }
            shuffle(iterArray);
            curIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return curIndex < end; // since the ptr is always at the actual "next", just check if it is null
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return iterArray[curIndex++]; // return this element and then move the ptr to the actual "next"
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        // unit testing (optional)
//        RandomizedQueue<Integer> queue = new RandomizedQueue<>();

//        for (int i = 0; i < 5 ; i++) {
//            System.out.println("i = " + i);
//            queue.enqueue(i);
//            System.out.println("size: " + queue.size());
//            System.out.println("items: ");
//            for (Integer a: queue) {
//                System.out.print(a+" ");
//            }
//            System.out.println();
//            System.out.println();
//        }
//        for (int i = 0; i < 5 ; i++) {
//            System.out.println(queue.dequeue());
//            System.out.println("size: " + queue.size());
//            for (int a: queue) {
//                System.out.print(a+" ");
//            }
//            System.out.println();
//            System.out.println();
//        }
    }
}