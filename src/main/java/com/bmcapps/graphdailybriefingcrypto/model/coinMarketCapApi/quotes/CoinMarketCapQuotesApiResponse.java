package com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinMarketCapQuotesApiResponse {
    private Map<String, CryptoCurrency> data;
}
