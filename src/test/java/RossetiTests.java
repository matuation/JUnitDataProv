import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class RossetiTests {
    @BeforeAll
    static void beforeAll() {
        //Configuration.browserSize = "1920x1080";
        baseUrl = "https://www.rosseti.ru/";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
        //Configuration.timeout = 5000; // default 4000
    }

    @BeforeEach
    void setUp() {

        open(baseUrl);
    }

    @ValueSource(strings = {
            "Свет", "Счётчик", "Оплата"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой результат")
    @Tag("BLOCKER")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        open(baseUrl);
        $("#header-search-show").click();
        $("#title-search-input").setValue(searchQuery).pressEnter();
        $$(".search-result-item")
                .shouldBe(sizeGreaterThan(0));
    }
//    @CsvSource (value = {
//            "Свет, Поздравление Генерального директора ПАО «Россети» Андрея Рюмина с Новым годом",
//            "Счётчик, «Россети» установили цифровое оборудование для учета электроэнергии на ключевых центрах питания Поволжья"
//    })
    @CsvFileSource (resources = "searchResultsShouldHaveUrlTitle.csv")
    @ParameterizedTest(name = "Для поиска по {0} в заголовке должна быть ссылка {1}")
    @Tag("BLOCKER")
    void searchResultsShouldHaveUrlTitle(String searchQuery, String expectedText) {
        open(baseUrl);
        $("#header-search-show").click();
        $("#title-search-input").setValue(searchQuery).pressEnter();
        $(".result-title").shouldHave(text(expectedText));
    }
}
