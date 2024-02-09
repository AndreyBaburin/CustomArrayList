import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        CustomArrayList<String> customArrayList = new CustomArrayList<>();

        customArrayList.add(0, "aaa");
        customArrayList.add(1, "ccc");
        customArrayList.add(2, "bb");
        customArrayList.add(3, "ll");
        customArrayList.add(4, "zzz");
        customArrayList.add(5, "nnn");
        customArrayList.add(6, "ff");
        customArrayList.add(7, "dd");
        customArrayList.add(8, "ggg");
        customArrayList.add(9, "kkk");
        customArrayList.add(10, "xx");
        System.out.println(customArrayList);

        customArrayList.add(10, "www");
        System.out.println(customArrayList);

        customArrayList.sort();

        System.out.println("Отсортированный список: " + customArrayList);
    }
}
