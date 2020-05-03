import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import static java.lang.System.out;

public class RedisStorage {

    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с ключами
    private RKeys rKeys;

    private RScoredSortedSet<String> users;

    private final static String KEY = "USERS";

    // Пример вывода всех ключей
//    public void listKeys() {
//        Iterable<String> keys = rKeys.getKeys();
//        for(String key: keys) {
//            out.println("KEY: " + key + ", type:" + rKeys.getType(key));
//        }
//    }

    void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
//        rKeys = redisson.getKeys();
        users = redisson.getScoredSortedSet(KEY);
//        rKeys.delete(KEY);
    }

    public void countUsers(int count) {

        for(int request = 1; request <= count; request++) {
            users.add(request, String.valueOf(request));
        }
    }

    public String getUser() {

        String user = users.first();
        users.add(users.lastScore() + 1, user);
        return user;
    }

    public String paidService() {

        String user = String.valueOf((int) Math.ceil(Math.random() * 20));
        users.add(users.lastScore() + 1, user);
        return user;
    }

    public void shutdown() {
        redisson.shutdown();
    }
}
