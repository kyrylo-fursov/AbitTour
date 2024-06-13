package nure.abittour.model.enums;

public enum Subject {
    UKRAINIAN_LANGUAGE("українська мова"),
    MATHEMATICS("математика"),
    HISTORY_OF_UKRAINE("історія України"),
    FOREIGN_LANGUAGE("іноземна мова"),
    BIOLOGY("біологія"),
    PHYSICS("фізика"),
    CHEMISTRY("хімія"),
    UKRAINIAN_LITERATURE("українська література"),
    GEOGRAPHY("географія"),
    CREATIVE_COMPETITION("творчий конкурс");

    private final String ukrainianName;

    Subject(String ukrainianName) {
        this.ukrainianName = ukrainianName;
    }

    public String getUkrainianName() {
        return ukrainianName;
    }

    public static Subject fromUkrainianName(String ukrainianName) {
        for (Subject subject : values()) {
            if (subject.ukrainianName.equalsIgnoreCase(ukrainianName)) {
                return subject;
            }
        }
        throw new IllegalArgumentException("No subject found with Ukrainian name: " + ukrainianName);
    }
}
