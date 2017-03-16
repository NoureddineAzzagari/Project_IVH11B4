package main.domain;

/**
 * Created by ids on 15-3-2017.
 */
public final class Configuration {

    private static final Configuration configuration = new Configuration();

    public final String webSiteName = "onze fantastische website";
    public final String versie = "2098.1.1.5.9.102.0";


    private Configuration() {
    }
    public static Configuration getConfiguration(){
        return configuration;
    }
}
