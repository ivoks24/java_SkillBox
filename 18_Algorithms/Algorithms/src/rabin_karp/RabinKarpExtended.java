package rabin_karp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class RabinKarpExtended {

    private String text;
    private TreeMap<Integer, Integer> number2position;
    private TreeMap<Character, Integer> letterNumber;

    public RabinKarpExtended(String text) {

        this.text = text;
        createIndex();
    }

    public List<Integer> search(String query) {

        ArrayList<Integer> indices = new ArrayList<>();
        //TODO: implement search algorithm

        int queryLength = query.length();
        int textLength = text.length();
        int queryNumber = 0;

        for (int i = 0; i < queryLength; i++) {
            queryNumber = queryNumber * 10 + letterNumber.get(query.charAt(i));
        }

        for (int i = 0; i < textLength - queryLength; i++) {

        }


        return indices;
    }

    private void createIndex() {

        //TODO: implement text indexing

        int number = 1;
        for (int position = 0; position < text.length(); position++) {

            char c = text.charAt(position);
            if (!letterNumber.containsKey(c)) {
                letterNumber.put(c, number++);
            }
            number2position.put(position, letterNumber.get(c));
        }
    }
}
//
//
//    int M = query.length();
//    int N = text.length();
//    int i, j;
//    int p = 0; // хеш-значение для шаблона
//    int t = 0; // хеш-значение для txt
//    int h = 1;
//
//// Значение h будет "pow (d, M-1)% q"
//        for (i = 0; i < M - 1; i++)
//        h = (h * d) % q;
//
//        // Вычисляем значение хеша pattern и first
//        // окно текста
//        for (i = 0; i < M; i++) {
//        p = (d * p + query.charAt(i)) % q;
//        t = (d * t + text.charAt(i)) % q;
//        }
//
//        // Скользим шаблон по тексту один за другим
//        for (i = 0; i <= N - M; i++) {
//
//        // Проверяем значения хеш-функции текущего окна текста
//        // и шаблон. Если значения хеша совпадают, то только
//        // проверяем наличие символов по одному
//        if (p == t) {
//        /* Проверять символы по одному */
//        for (j = 0; j < M; j++) {
//        if (text.charAt(i + j) != query.charAt(j))
//        break;
//        }
//
//        // если p == t и pat [0 ... M-1] = txt [i, i + 1, ... i + M-1]
//        if (j == M)
//        System.out.println("Pattern found at index " + i);
//        }
//
//
//        // Рассчитать значение хеша для следующего окна текста: Удалить
//        // начальная цифра, добавляем завершающую цифру
//        if (i < N - M) {
//        t = (d * (t - text.charAt(i) * h) + text.charAt(i + M)) % q;
//
//        // Мы можем получить отрицательное значение t, преобразовав его
//        // к положительному
//        if (t < 0)
//        t = (t + q);
//        }
//        }
