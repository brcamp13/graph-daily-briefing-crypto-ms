package com.bmcapps.graphdailybriefingcrypto.mapper;

import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.fearAndGreed.CoinMarketCapFearGreedApiResponse;
import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.fearAndGreed.DataResponse;
import com.bmcapps.graphdailybriefingcrypto.model.graphSchema.CryptoMarketDataSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinMarketCapGetFearGreedToCryptoMarketSchemaMapperTest {

    private final CoinMarketCapGetFearGreedToCryptoMarketSchemaMapper mapper = new CoinMarketCapGetFearGreedToCryptoMarketSchemaMapper();

    @Test
    void testMapToCryptoMarketDataSchema() {
        CoinMarketCapFearGreedApiResponse response = new CoinMarketCapFearGreedApiResponse();
        DataResponse dataResponse = new DataResponse();

        dataResponse.setValue(50);
        dataResponse.setValueClassification("Neutral");

        response.setData(dataResponse);

        // Act
        CryptoMarketDataSchema result = mapper.mapToCryptoMarketDataSchema(response);

        // Assert
        assertEquals(50, result.getFearAndGreedIndexValue());
        assertEquals("Neutral", result.getFearAndGreedIndexValueClassification());
    }
}
