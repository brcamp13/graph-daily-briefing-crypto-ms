package com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.fearAndGreed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResponse {
    @JsonProperty("value")
    private int value;

    @JsonProperty("value_classification")
    private String valueClassification;
}
