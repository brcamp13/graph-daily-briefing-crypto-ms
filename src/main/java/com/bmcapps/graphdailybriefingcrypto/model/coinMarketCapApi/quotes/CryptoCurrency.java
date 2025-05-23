package com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CryptoCurrency {
    private String name;
    private String symbol;
    private Quote quote;
}
