package com.bmcapps.graphdailybriefingcrypto.model.graphSchema;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CryptoMarketDataSchema {
    private int fearAndGreedIndexValue;
    private String fearAndGreedIndexValueClassification;
}
