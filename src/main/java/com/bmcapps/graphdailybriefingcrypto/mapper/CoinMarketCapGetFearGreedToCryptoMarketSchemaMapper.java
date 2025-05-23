package com.bmcapps.graphdailybriefingcrypto.mapper;

import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.fearAndGreed.CoinMarketCapFearGreedApiResponse;
import com.bmcapps.graphdailybriefingcrypto.model.graphSchema.CryptoMarketDataSchema;
import org.springframework.stereotype.Component;

@Component
public class CoinMarketCapGetFearGreedToCryptoMarketSchemaMapper {

    public CryptoMarketDataSchema mapToCryptoMarketDataSchema(CoinMarketCapFearGreedApiResponse response) {
        CryptoMarketDataSchema cryptoMarketDataSchema = new CryptoMarketDataSchema();
        cryptoMarketDataSchema.setFearAndGreedIndexValue(response.getData().getValue());
        cryptoMarketDataSchema.setFearAndGreedIndexValueClassification(response.getData().getValueClassification());

        return cryptoMarketDataSchema;
    }
}
