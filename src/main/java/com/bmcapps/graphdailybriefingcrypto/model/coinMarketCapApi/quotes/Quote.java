package com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
    @JsonProperty("USD")
    private UsdData usd;
}
