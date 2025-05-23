package com.bmcapps.graphdailybriefingcrypto.model.graphSchema;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CryptoSchema {
    private String name;
    private String symbol;
    private double price;
    private double marketCap;
    private double percentChange24h;
    private double percentChange60d;
    private double percentChange90d;
}
