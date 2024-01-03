package org.mym.parser;

import org.junit.jupiter.api.Test;
import org.mym.model.CurrencyRate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class CurrencyRateParserXMLTest {
    @Test
    void parseXmlFileThatSimilarToAnswerFromCbrRu() throws URISyntaxException, IOException {
        // given
        var parser = new CurrencyRateParserXML();
        var uri = ClassLoader.getSystemResource("cbr_response.xml").toURI();
        var ratesXml = Files.readString(Paths.get(uri), StandardCharsets.UTF_8);

        // when
        var rates = parser.parse(ratesXml);

        // then
        assertThat(rates.size()).isEqualTo(43);
        assertThat(rates.contains(getUsdRate())).isTrue();
        assertThat(rates.contains(getEurRate())).isTrue();
        assertThat(rates.contains(getJpyRate())).isTrue();
    }

    private CurrencyRate getUsdRate() {
        return CurrencyRate.builder()
                .numCode(840)
                .charCode("USD")
                .nominal(1)
                .name("Доллар США")
                .value("97,0018")
                .vunitRate("97,0018")
                .build();
    }

    private CurrencyRate getEurRate() {
        return CurrencyRate.builder()
                .numCode(978)
                .charCode("EUR")
                .nominal(1)
                .name("Евро")
                .value("102,0979")
                .vunitRate("102,0979")
                .build();
    }

    private CurrencyRate getJpyRate() {
        return CurrencyRate.builder()
                .numCode(392)
                .charCode("JPY")
                .nominal(100)
                .name("Японских иен")
                .value("64,8885")
                .vunitRate("0,648885")
                .build();
    }
}