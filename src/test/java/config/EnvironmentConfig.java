package config;

import org.aeonbits.owner.Config;
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/remote.properties"
})
public interface EnvironmentConfig extends Config {
    @DefaultValue("chrome")
    String browser();
    @DefaultValue("91.0")
    String browserVersion();
    @DefaultValue("1920x1080")
    String browserSize();
    String remoteDriverUrl();
    String remoteDriverUser();
    String remoteDriverPassword();
    String videoStorage();
}
