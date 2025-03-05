package jsonplaceholder.generators;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class TestDataGenerator {

    private static final Random random = new Random();

    public static <T> T generate(Class<T> clazz) {
        try {
            // Создаем экземпляр класса
            T instance = clazz.getDeclaredConstructor().newInstance();

            // Перебираем все поля класса
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true); // Делаем поле доступным

                if (field.getType() == int.class || field.getType() == Integer.class) {
                    field.setInt(instance, generateRandomInt());
                } else if (field.getType() == double.class || field.getType() == Double.class) {
                    field.setDouble(instance, generateRandomDouble());
                } else if (field.getType() == String.class) {
                    field.set(instance, generateRandomString());
                }
                // Можно добавить обработку других типов данных
            }

            return instance;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int generateRandomInt() {
        return random.nextInt(10000);
    }

    private static double generateRandomDouble() {
        return random.nextDouble() * 10000.0;
    }

    private static String generateRandomString() {
        return "Test" + random.nextInt(10000);
    }
}
