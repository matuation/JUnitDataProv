import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public class TestBaseHabr {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://habr.com/ru/feed/";
        Configuration.pageLoadStrategy = "eager";
    }
    @BeforeEach
    void setUp() {
        open(baseUrl);
    }
}
