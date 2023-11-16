package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "Wrong format emails list")
    public static Object[][] getWrongFormatEmailsList() {
        return new Object[][]{
                {"abc.def@mail#archive.com"},
                {"abc..def@mail.com"},
                {".abc@mail.com"},
                {"abc.def@mail"},
                {"abc.def@mail..com"},
                {"email.domain.com"},
                {"email@domain@domain.com"},
                {"email.@domain.com"},
                {"mail@-domain.com"},
                {"あいうえお@domain.com"},
                {"@domain.com"},
                {"<h1>Testing</h1>"},
                {"<script>alert(123)</script>"},
                {"xxx@xxx.xxx' OR 1 = 1 LIMIT 1 -- ' ]"},
        };
    }
}
