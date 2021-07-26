package nl.pjiwm.kaizominecraft.managers;

public class CustomMobManagerFactory {

    private static CustomMobManager customMobManager;

    public CustomMobManagerFactory(String version) {
        switch (version) {
            case "1.16.5":
                customMobManager = new CustomMobManagerVersion16();
                break;
            default:
                customMobManager = null;
        }
    }

    public static CustomMobManager getCustomMobManager() {
        return customMobManager;
    }
}
