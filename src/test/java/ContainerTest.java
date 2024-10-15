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
}
