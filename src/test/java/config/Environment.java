package config;

import org.aeonbits.owner.ConfigFactory;

public class Environment {
    public static EnvironmentConfig config = ConfigFactory.create(EnvironmentConfig.class, System.getProperties());

    public static boolean isRemoteWebDriver() {
        return !config.remoteDriverUrl().equals("");
    }

    public static boolean isVideoOn() {
        return !config.videoStorage().equals("");
    }
}
