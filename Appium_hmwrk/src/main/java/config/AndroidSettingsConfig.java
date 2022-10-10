package config;
import org.aeonbits.owner.Config;

/**
 * Интерфейс с настройками параметров устройства
 */
@Config.Sources({"classpath:config/config.properties"})
public interface AndroidSettingsConfig extends Config {


    String runType();
    /**
     * Метод для возвращения параметра url из config.properties
     *
     * @return URL приложения
     */
    String url();

    /**
     * Метод для возвращения параметра deviceName из config.properties
     *
     * @return deviceName устройства
     */
    String deviceName();

    /**
     * Метод для возвращения параметра platformName из config.properties
     *
     * @return platformName устройства
     */
    String platformName();

    /**
     * Метод для возвращения параметра platformVersion из config.properties
     *
     * @return platformVersion устройства
     */
    String platformVersion();

    /**
     * Метод для возвращения параметра udid из config.properties
     *
     * @return udid устройства
     */
    String udid();

    /**
     * Метод для возвращения параметра app из config.properties
     *
     * @return app устройства
     */
    String app();

    /**
     * Èìÿ óñòðîéñòâà â îáëàêå
     */
    String deviceNameBrowserStack();

    /**
     * Âåðñèÿ îïåðàöèîííîé ñèñòåìû â îáëàêå
     */
    String osVersionBrowserStack();
}
