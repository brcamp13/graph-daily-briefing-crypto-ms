package com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsdData {
    private double price;

    @JsonProperty("market_cap")
    private double marketCap;

    @JsonProperty("percent_change_24h")
    private double percentChange24h;

    @JsonProperty("percent_change_60d")
    private double percentChange60d;

    @JsonProperty("percent_change_90d")
    private double percentChange90d;
}
