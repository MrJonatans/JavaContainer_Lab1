import org.container.Container;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerTest {

    private Container container;

    @BeforeEach
    void setUp() {
        container = new Container();
    }

    @Test
    void testAddAndSize() {
        container.add("Первый элемент");
        container.add("Второй элемент");
        assertEquals(2, container.size(), "Размер контейнера должен быть 2");
    }

    @Test
    void testGetElement() {
        container.add("Первый элемент");
        container.add("Второй элемент");
        assertEquals("Первый элемент", container.get(0), "Элемент по индексу 0 должен быть 'Первый элемент'");
        assertEquals("Второй элемент", container.get(1), "Элемент по индексу 1 должен быть 'Второй элемент'");
    }

    @Test
    void testRemoveElement() {
        container.add("Первый элемент");
        container.add("Второй элемент");
        container.remove(0);
        assertEquals(1, container.size(), "Размер контейнера после удаления должен быть 1");
        assertEquals("Второй элемент", container.get(0), "Элемент по индексу 0 должен быть 'Второй элемент'");
    }

    @Test
    void testGetOutOfBounds() {
        container.add("Первый элемент");
        assertThrows(IndexOutOfBoundsException.class, () -> container.get(1), "Должно выбросить IndexOutOfBoundsException");
    }

    @Test
    void testRemoveOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> container.remove(0), "Должно выбросить IndexOutOfBoundsException");
    }
    @Test
    public void testRemoveThrowsIndexOutOfBoundsException() {
        Container container = new Container();
        container.add("First");

        assertThrows(IndexOutOfBoundsException.class, () -> {
            container.remove(1); // индекс вне диапазона
        }, "Expected IndexOutOfBoundsException when removing invalid index");
    }

    @Test
    public void testToString() {
        Container container = new Container();
        assertEquals("[]", container.toString(), "Empty container should return '[]'");

        container.add("First");
        assertEquals("[First]", container.toString(), "Container should return '[First]' after adding 'First'");

        container.add("Second");
        assertEquals("[First, Second]", container.toString(), "Container should return '[First, Second]' after adding two elements");
    }

    @Test
    public void testEqualsAndHashCode() {
        Container container1 = new Container();
        Container container2 = new Container();

        // Оба контейнера пусты, должны быть равны
        assertEquals(container1, container2, "Two empty containers should be equal");
        assertEquals(container1.hashCode(), container2.hashCode(), "Hash codes of two empty containers should be equal");

        container1.add("First");
        container2.add("First");

        assertEquals(container1, container2, "Containers with same elements should be equal");
        assertEquals(container1.hashCode(), container2.hashCode(), "Hash codes of containers with same elements should be equal");

        container2.add("Second");

        assertNotEquals(container1, container2, "Containers with different elements should not be equal");
        assertNotEquals(container1.hashCode(), container2.hashCode(), "Hash codes should not match for different containers");
    }

    @Test
    public void testIterable() {
        Container container = new Container();
        container.add("First");
        container.add("Second");
        container.add("Third");

        StringBuilder sb = new StringBuilder();
        for (Object item : container) {
            sb.append(item).append(" ");
        }

        assertEquals("First Second Third ", sb.toString(), "Iteration over container should return correct order");
    }
}
