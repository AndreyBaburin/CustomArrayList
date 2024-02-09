import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * Класс предостовляет список с упорядоченным набором данных (аналог Arraylist)
 * Список поддерживает динамическое изменение размера и реализует методы для добавления (есть вариант по индексу),
 * получения, замены элемента (по индексу), удаления (по индексу или значению), очистка всего списка,
 * проверка на пустоту списка и сортировка списка
 *
 * @param <T> - Тип элементов в списке, должен реализовывать интерфейс Comparable
 * @author Andrey
 * @version 1.0
 */
public class CustomArrayList<T extends Comparable<T>> implements Comparator<T> {
    /**
     * Поле рамера списка по умолчанию
     */
    private static final int DEFAULT_SIZE = 10;
    /**
     * Поле массива с параметризованным типом (дженериком)
     */
    private T[] array;
    /**
     * Поле заполненности массива
     */
    private int size = 0;

    /**
     * Конструктор - создание нового списка (с размером по умолчанию)
     *
     * @see CustomArrayList#CustomArrayList(int)
     */
    public CustomArrayList() {
        this(DEFAULT_SIZE);
    }

    /**
     * Конструктор - создание нового списка определённого размера
     *
     * @param length - размер
     */
    public CustomArrayList(int length) {
        array = (T[]) Array.newInstance(Comparable.class, length);
    }

    /**
     * @return - массив
     */
    public T[] getArray() {
        return array;
    }

    /**
     * @return - заполненность списка
     */
    public int getSize() {
        return size;
    }

    /**
     * Добавляет элемент в конец списка
     *
     * @param t - элемент, который нужно добавить
     */
    public void add(T t) {
        add(size, t);
    }
    /**
     * Добавляет элемент по индексу
     *
     * @param t - элемент, который нужно добавить
     * @param index - индекс в списке
     * @throws IndexOutOfBoundsException если индекс за пределами списка
     */
    public void add(int index, T t) {
        if (index < 0 && index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " outside the list");
        }
        if (size == array.length) {
            int newLength = (int) (array.length * 1.75);
            T[] newArray = Arrays.copyOf(array, newLength);
            array = newArray;
        }
        if (index < size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = t;
        size++;
    }

    /**
     * Получение значения по индексу
     *
     * @param index - индекс в списке
     * @return - значение
     * @throws IndexOutOfBoundsException если индекс за пределами списка
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " outside the list");
        }
        return array[index];
    }

    /**
     * Замена значения по индексу
     *
     * @param index - индекс в списке
     * @param t - новое значение
     * @throws IndexOutOfBoundsException если индекс за пределами списка
     */
    public void set(int index, T t) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " outside the list");
        }
        array[index] = t;
    }

    /**
     * Удаление значения по индексу
     *
     * @param index - индекс в списке
     * @return - true или false
     * @throws IndexOutOfBoundsException если индекс за пределами списка
     */
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " outside the list");
        }
        System.arraycopy(array, index + 1, array, index, size - 1 - index);
        size--;
        return true;
    }

    /**
     * Удаление значения из списка
     *
     * @param t - значение
     * @return - true или false
     */
    public boolean remove(T t) {
        for (int i = 0; i < size; i++) {
            T newValue = array[i];
            if (t.equals(newValue)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Очистка всего списка
     */
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    /**
     * Порверка на пустоту списка
     *
     * @return - true или false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomArrayList<?> that = (CustomArrayList<?>) o;
        return size == that.size && Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "array=" + Arrays.toString(array) +
                ", size=" + size +
                '}';
    }

    @Override
    public int compare(T a, T b) {
        return a.compareTo(b);
    }

    /**
     * Сортировка списка с использованием алгоритма быстрой сортировки (QuickSort)
     */
    public void sort() {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Вспомогательный метод для алгоритма быстрой сортировки
     * Рекурсивно сортирует подмассив в пределах указанных индексов
     *
     * @param array - массив для сортировки
     * @param low - индекс начала подмассива
     * @param high - индекс конца подмассива
     */
    private void quickSort(T[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);
            quickSort(array, low, pivot - 1);
            quickSort(array, pivot + 1, high);
        }
    }

    /**
     * Вспомогательный метод для алгоритма быстрой сортировки
     * Выбирает опорный элемент (pivot) и перемещает все элементы меньшие опорного слева от него,
     * а все большие - справа
     * @param array - массив для сортировки
     * @param low - индекс начала подмассива
     * @param high - индекс конца подмассива
     * @return - индекс опорного элемента после перемещения
     */
    private int partition(T[] array, int low, int high) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] != null && (pivot == null || array[j].compareTo(pivot) <= 0)) { //compare (array[j], pivot)
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Вспомогательный метод для обмена двух элементов в массиве
     * @param array - массив
     * @param i - индекс первого элемента
     * @param j - индекс второго элемента
     */
    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
