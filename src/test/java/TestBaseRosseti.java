import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

public class TestBaseRosseti {
    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://www.rosseti.ru/";
        Configuration.pageLoadStrategy = "eager";
    }
    @BeforeEach
    void setUp() {
        open(baseUrl);
    }
}
