package org.mym.model;

import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
@Builder
public record CurrencyRate(int numCode, String charCode, int nominal, String name, String value, String vunitRate) {
}
