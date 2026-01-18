package Data;

public enum Language {
    Русский("Настройки страницы"),
    English("Page settings");

public final String description;

    Language(String description) {
        this.description = description;
    }
}
