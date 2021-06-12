package nl.pjiwm.kaizominecraft.managers;

public enum WorldTypes {
    CUSTOM("custom"), NORMAL("normal");

    private final String worldType;

    WorldTypes(String worldType) {
        this.worldType = worldType;
    }

    @Override
    public String toString() {
        return worldType;
    }
}
