package sample.web.ui.domain;

import lombok.Getter;

@Getter
public final class Configuration {
    private final String version = "1.1.1.2.1.9";
    private final String name = "Amazing netflix clone";

    private Configuration() {
    }

    public static Configuration getConfiguration(){
        return new Configuration();
    }


}
