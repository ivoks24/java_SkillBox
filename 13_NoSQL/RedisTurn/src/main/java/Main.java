

import static java.lang.System.out;

public class Main {

    // Запуск докер-контейнера:
    // docker run --rm --name skill-redis -p 127.0.0.1:6379:6379/tcp -d redis

    private static final int SLEEP = 100;

    private static void log(String user) {
        String log = String.format("— На главной странице показываем пользователя %s", user);
        out.println(log);
    }

    private static void logPaid(String user) {
        String log = String.format("> Пользователь %s оплатил платную услугу", user);
        out.println(log);
    }

    public static void main(String[] args) throws InterruptedException {

        RedisStorage redis = new RedisStorage();
        redis.init();

        redis.countUsers(20);

        for (;;) {

            if (Math.random() <= 0.1) {
                logPaid(redis.paidService());
            } else {
                log(redis.getUser());
            }

            Thread.sleep(SLEEP);
        }
//        redis.shutdown();
    }
}
