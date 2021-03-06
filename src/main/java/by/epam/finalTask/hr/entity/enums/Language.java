package by.epam.finalTask.hr.entity.enums;

public enum Language {
    EN, RU;

    private static final Language DEFAULT_LANGUAGE = Language.EN;

    public Language getLanguage(String value) {
        for (Language language : Language.values()) {
            if (language.name().equals(value)) {
                return Language.valueOf(value);
            }
        }
        return DEFAULT_LANGUAGE;
    }
}