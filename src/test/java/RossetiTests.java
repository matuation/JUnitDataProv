import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RossetiTests extends TestBaseRosseti {

    @ValueSource(strings = {
            "Свет", "Счётчик", "Оплата"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой результат")
    @Tag("BLOCKER")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("#header-search-show").click();
        $("#title-search-input").setValue(searchQuery).pressEnter();
        $$(".search-result-item")
                .shouldBe(sizeGreaterThan(0));
    }
    @CsvFileSource (resources = "searchResultsShouldHaveUrlTitle.csv")
    @ParameterizedTest(name = "Для поиска по {0} в заголовке должна быть ссылка {1}")
    @Tag("BLOCKER")
    void searchResultsShouldHaveUrlTitle(String searchQuery, String expectedText) {
        $("#header-search-show").click();
        $("#title-search-input").setValue(searchQuery).pressEnter();
        $(".result-title").shouldHave(text(expectedText));
    }
}
