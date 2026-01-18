import Data.Language;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;

public class HabrTests {
    @BeforeAll
    static void beforeAll() {
        //Configuration.browserSize = "1920x1080";
        baseUrl = "https://habr.com/ru/feed/";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
        //Configuration.timeout = 5000; // default 4000
    }

    @BeforeEach
    void setUp() {

        open(baseUrl);
    }

    @EnumSource(Language.class)
    @ParameterizedTest(name = "При выборе языка {0} должен менятся заголовок")
    @Tag("BLOCKER")
    void languageShouldSwitchCorrectly(Language language) {
        open(baseUrl);
        $(".tm-header-user-menu__toggle").click();
        $$(".tm-input-radio-labeled").find(text(language.name())).click();
        $(".tm-page-settings-form__title")
                .shouldHave(text(language.description));
    }

    static Stream<Arguments> whenLanguageSwitchedHeadersShouldSwitch() {
        return Stream.of(
                Arguments.of(Language.Русский, List.of("Интерфейс", "Публикации", "Вид ленты", "Цветовая тема")),
                Arguments.of(Language.English, List.of("Interface", "Content", "Feed", "View options"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "При смене языка {0} должны смениться заголовки")
    void whenLanguageSwitchedHeadersShouldSwitch(Language language, List<String> expectedHeaders) {
        open(baseUrl);
        $(".tm-header-user-menu__toggle").click();
        $$(".tm-input-radio-labeled").find(text(language.name())).click();
        $$(".tm-fieldset__header")
                .shouldHave(texts(expectedHeaders));
    }
}
