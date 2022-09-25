package config;
import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/config.properties"})
public interface AndroidSettingsConfig extends Config {

    String url();

    String deviceName();

    String platformName();

    String platformVersion();

    String udid();

    String app();
}
