package com.bmcapps.graphdailybriefingcrypto.mapper;

import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes.CoinMarketCapQuotesApiResponse;
import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes.CryptoCurrency;
import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes.Quote;
import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes.UsdData;
import com.bmcapps.graphdailybriefingcrypto.model.graphSchema.CryptoSchema;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinMarketCapGetQuotesToCryptocurrencySchemaMapperTest {

    private final CoinMarketCapGetQuotesToCryptocurrencySchemaMapper mapper = new CoinMarketCapGetQuotesToCryptocurrencySchemaMapper();

    @Test
    void testMapCoinMarketCapGetQuotesToCryptoSchema() {
        UsdData usdData = new UsdData();
        usdData.setPrice(50000.0);
        usdData.setMarketCap(1000000000.0);
        usdData.setPercentChange24h(2.5);
        usdData.setPercentChange60d(10.0);
        usdData.setPercentChange90d(20.0);

        Quote quote = new Quote();
        quote.setUsd(usdData);

        CryptoCurrency cryptoCurrency = new CryptoCurrency();
        cryptoCurrency.setName("Bitcoin");
        cryptoCurrency.setSymbol("BTC");
        cryptoCurrency.setQuote(quote);

        CoinMarketCapQuotesApiResponse response = new CoinMarketCapQuotesApiResponse();
        response.setData(Map.of("BTC", cryptoCurrency));

        // Act
        List<CryptoSchema> result = mapper.mapCoinMarketCapGetQuotesToCryptoSchema(response);

        // Assert
        assertEquals(1, result.size());
        CryptoSchema schema = result.get(0);
        assertEquals("Bitcoin", schema.getName());
        assertEquals("BTC", schema.getSymbol());
        assertEquals(50000.0, schema.getPrice());
        assertEquals(1000000000, schema.getMarketCap());
        assertEquals(2.5, schema.getPercentChange24h());
        assertEquals(10.0, schema.getPercentChange60d());
        assertEquals(20.0, schema.getPercentChange90d());
    }

    @Test
    void testMapCoinMarketCapGetQuotesToCryptoSchemaWithEmptyResponse() {
        // Arrange
        CoinMarketCapQuotesApiResponse response = new CoinMarketCapQuotesApiResponse();
        response.setData(Collections.emptyMap());

        // Act
        List<CryptoSchema> result = mapper.mapCoinMarketCapGetQuotesToCryptoSchema(response);

        // Assert
        assertEquals(0, result.size());
    }
}
