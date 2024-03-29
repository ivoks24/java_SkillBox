public class Loader {
    public static void main(String[] args) {

        String englishText = "Everyone could notice that people often complain and say that it was better before. It may be a little nostalgia; the old people would like to return in the 50s, forty-years-old people prefer the 70s. It can be explained of the life period when they were younger. As it’s known people like their youth time.\n" +
                "In general living is better now. We live when there aren’t as many wars in the world as before. And people live longer; the level of lifespan is higher than it was 40 years ago. People work less and they can afford to go to the vacation and buy more things.\n" +
                "So, it is true that there is more pollution now than in the last century but there is more conveniences. Everyone has a shower, a TV, a fridge, a computer, etc. And now there are more means of communication than before. Earlier it was dangerous to travel and it was almost impossible to go around the world.\n" +
                "Someone say that people were kinder before. But now people are more educated and now everyone has an opportunity to go to school and to get education. It’s better to think of advantages to live in the modern world than to regret something that no longer exists.";

        englishText = englishText.replaceAll("\n", " ");
        englishText = englishText.replaceAll("^[A-B[a-b]]\\s", "");
        String[] fragments = englishText.split(" ");
        for (String fragment : fragments) {
            System.out.println(fragment);
        }
    }
}
