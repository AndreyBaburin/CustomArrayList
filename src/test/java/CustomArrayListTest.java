import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Класс предостовляет тесты для класса CustomArrayList
 * @author Andrey
 * @version 1.0
 */

public class CustomArrayListTest {

    /**
     * Тест для метода add() и get() с добавлением элементов по значению
     */
    @Test
    public void testAddByValueAndGet() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertThat(list.getSize(), is(0));

        list.add(8);
        list.add(9);
        list.add(2);
        list.add(7);
        assertThat(list.getSize(), is(4));
        assertThat(list.get(0), is(8));
        assertThat(list.get(1), is(9));
        assertThat(list.get(2), is(2));
        assertThat(list.get(3), is(7));
    }

    /**
     * Тест для метода add() и get() с добавлением элементов по индексу
     */
    @Test
    public void testAddByIndexAndGet() {
        CustomArrayList<String> list = new CustomArrayList<>();
        assertThat(list.getSize(), is(0));

        list.add("Tom");
        list.add("Bob");
        list.add("Alex");
        list.add(0, "one");
        list.add(1, "two");
        list.add(2, "three");

        assertThat(list.getSize(), is(6));
        assertThat(list.get(0), is("one"));
        assertThat(list.get(1), is("two"));

    }

    /**
     * Тест для случая, когда новый размер списка больше размера по умолчанию
     */
    @Test
    public void testWhenNewSizeMoreDefaultSize() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        assertThat(list.getSize(), is(0));
        for (int i = 0; i < 21; i++) {
            list.add(i);
        }

        assertTrue(list.getSize() > 10);
        assertTrue(list.getSize() > 20);
    }

    /**
     * Тест для метода remove() по индексу
     */
    @Test
    public void testRemoveByIndex() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(8);
        list.add(9);
        list.add(2);
        list.add(7);
        list.remove(1);
        assertTrue(list.get(1) != 9);
        assertEquals(3, list.getSize());
        assertThat(list.get(0), is(8));
        assertThat(list.get(1), is(2));
        assertThat(list.get(2), is(7));
    }

    /**
     * Тест для метода remove() по значению
     */
    @Test
    public void testRemoveByValue() {
        CustomArrayList<String> list = new CustomArrayList<>();
        assertThat(list.getSize(), is(0));

        list.add("Tom");
        list.add("Bob");
        list.add("Alex");
        list.remove("Bob");
        assertNotSame("Bob", list.get(1));
        assertEquals(2, list.getSize());
        assertThat(list.get(0), is("Tom"));
        assertThat(list.get(1), is("Alex"));
    }

    /**
     * Тест для методов clear() и isEmpty()
     */
    @Test
    public void testClearAndIsEmpty() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(8);
        list.add(9);
        list.add(2);
        list.add(7);
        assertEquals(4, list.getSize());
        list.clear();
        assertEquals(0, list.getSize());
        assertTrue(list.isEmpty());
    }

    /**
     * Тест для метода set()
     */
    @Test
    public void testSet(){
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("Tom");
        list.add("Bob");
        list.add("Alex");

        list.set(1,"Mike");

        assertEquals("Mike", list.get(1));
    }

    /**
     * Тест для метода sort().
     */
    @Test
    public void testSort() {
        CustomArrayList<Integer> unsortedList = new CustomArrayList<>();
        unsortedList.add(8);
        unsortedList.add(9);
        unsortedList.add(2);
        unsortedList.add(7);

        unsortedList.sort();
        assertEquals(4, unsortedList.getSize());

        CustomArrayList<Integer> sortedList = new CustomArrayList<>();
        sortedList.add(2);
        sortedList.add(7);
        sortedList.add(8);
        sortedList.add(9);

        assertEquals(sortedList, unsortedList);
    }

    /**
     * Тест для метода toString().
     */
    @Test
    public void testToString() {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(8);
        list.add(9);
        list.add(2);
        list.add(7);

        assertEquals("CustomArrayList{array=[8, 9, 2, 7, null, null, null, null, null, null], size=4}",
                list.toString());
    }
}