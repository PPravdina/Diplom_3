import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfProperties {
    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = ConfProperties.class.getClassLoader().getResourceAsStream("conf.properties")) {
            if (input == null) {
                System.out.println("Извините, не удается найти файл conf.properties");
            } else {
                PROPERTIES.load(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка при загрузке файла conf.properties", e);
        }
    }

    /**
     * Метод для возврата строки со значением из файла с настройками
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    /**
     * Дополнительный метод для возврата значения пути к драйверу по его имени
     */
    public static String getDriverPath(String driverName) {
        return getProperty(driverName);
    }
}
