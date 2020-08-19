package rabin_karp;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class RabinKarpExtended {

    private String text;
    private TreeMap<Integer, Integer> number2position;
    private final int MAX_PATTERN_SIZE = 18;

    public RabinKarpExtended(String text) {

        this.text = text;
        createIndex();
    }

    public List<Integer> search(String query) {

        ArrayList<Integer> indices = new ArrayList<>();
        //TODO: implement search algorithm

        if(query.length() > MAX_PATTERN_SIZE) {
            throw new IllegalArgumentException("Размер паттерна превышает 18");
        }
        if(query.length() > text.length()){
            return indices;
        }

        String alphabetQuery = textToIndex(query);
        if(alphabetQuery == null){
            return indices;
        }
        long patternL = Long.parseLong(alphabetQuery);

        for (int i  = 0; i < text.length() - query.length() + 1; i++) {
            String textSubstring = textToIndex(text.substring(i, i + query.length()));
            assert textSubstring != null;
            long substringL = Long.parseLong(textSubstring);
            if (patternL == substringL) {
                indices.add(i);
            }
        }
        return indices;
    }

    private String textToIndex(String text){
        StringBuilder sb = new StringBuilder();
        for(char c : text.toCharArray()){
            Integer index = number2position.getOrDefault((int)c, null);
            if(index == null){
                System.out.println("Index \"" + c + "\" not found");
                return null;
            }
            sb.append(index);
        }
        return sb.toString();
    }

    private void createIndex() {

        //TODO: implement text indexing

        number2position = new TreeMap<>();
        int index = 0;
        for(char c : text.toCharArray()){
            number2position.put((int)c, number2position.getOrDefault((int)c, index++));
        }
        if(number2position.size() > 10){
            throw new IllegalArgumentException("Алфавит не должен превышать 10 символов");
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
