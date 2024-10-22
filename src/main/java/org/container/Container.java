package org.container;
import java.util.Iterator;

/**
 * A container for storing an arbitrary number of objects.
 */
public class Container implements Iterable<Object> {

    static private class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    /**
     * Creates an empty container.
     */
    public Container() {
        head = null;
        size = 0;
    }

    /**
     * Adds a new element to the container.
     *
     * @param item the element to add
     */
    public void add(Object item) {
        Node newNode = new Node(item);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    /**
     * Retrieves an element by its index.
     *
     * @param index the index of the element
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bound: " + index);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Removes an element by its index.
     *
     * @param index the index of the element to be removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bound: " + index);
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }

    /**
     * Returns the number of elements in the container.
     *
     * @return the size of the container
     */
    public int size() {
        return size;
    }

    /**
     * Returns a string representation of the container. The string representation
     * consists of a list of the container's elements, enclosed in square brackets
     * ("[]"). Adjacent elements are separated by the characters ", " (comma and
     * space).
     * Example:
     * <pre>
     *     Container container = new Container();
     *     container.add("A");
     *     container.add("B");
     *     System.out.println(container); // Outputs: [A, B]
     * </pre>
     *
     * @return a string representation of the container and its elements
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Compares the specified object with this container for equality. Returns
     * {@code true} if and only if the specified object is also a container,
     * both containers have the same size, and all corresponding pairs of elements
     * in the two containers are equal.
     * Two containers are considered equal if they have the same elements in the
     * same order.
     *
     * @param obj the object to be compared for equality with this container
     * @return {@code true} if the specified object is equal to this container,
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Container other = (Container) obj;
        if (this.size != other.size) {
            return false;
        }
        Node currentThis = this.head;
        Node currentOther = other.head;
        while (currentThis != null) {
            if (!currentThis.data.equals(currentOther.data)) {
                return false;
            }
            currentThis = currentThis.next;
            currentOther = currentOther.next;
        }
        return true;
    }

    /**
     * Returns the hash code value for this container. The hash code of a container
     * is defined as the sum of the hash codes of its elements, where the hash code
     * of a {@code null} element is defined to be 0.
     * The formula used for calculating the hash code is:
     * <pre>
     *     int hashCode = 1;
     *     for (Object element : container) {
     *         hashCode = 31 * hashCode + (element == null ? 0 : element.hashCode());
     *     }
     * </pre>
     *
     * This ensures that {@code container1.equals(container2)} implies that
     * {@code container1.hashCode() == container2.hashCode()}.
     *
     * @return the hash code value for this container
     */
    @Override
    public int hashCode() {
        int result = 1;
        Node current = head;
        while (current != null) {
            result = 31 * result + (current.data == null ? 0 : current.data.hashCode());
            current = current.next;
        }
        return result;
    }

    /**
     * Returns an iterator over the elements in this container in proper sequence.
     * The returned iterator will iterate over the elements in the order in which
     * they were added to the container.
     * Example usage:
     * <pre>
     *     Container container = new Container();
     *     container.add("A");
     *     container.add("B");
     *     for (Object item : container) {
     *         System.out.println(item); // Outputs: A, B
     *     }
     * </pre>
     *
     * @return an iterator over the elements in this container
     */
    @Override
    public Iterator<Object> iterator() {
        return new ContainerIterator();
    }

    private class ContainerIterator implements Iterator<Object> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object data = current.data;
            current = current.next;
            return data;
        }
    }

}
