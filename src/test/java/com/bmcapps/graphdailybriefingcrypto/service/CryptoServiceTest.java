package com.bmcapps.graphdailybriefingcrypto.service;

import com.bmcapps.graphdailybriefingcrypto.client.coinMarketCap.CoinMarketCapFeignClient;
import com.bmcapps.graphdailybriefingcrypto.mapper.CoinMarketCapGetFearGreedToCryptoMarketSchemaMapper;
import com.bmcapps.graphdailybriefingcrypto.mapper.CoinMarketCapGetQuotesToCryptocurrencySchemaMapper;
import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.fearAndGreed.CoinMarketCapFearGreedApiResponse;
import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.fearAndGreed.DataResponse;
import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes.CoinMarketCapQuotesApiResponse;
import com.bmcapps.graphdailybriefingcrypto.model.graphSchema.CryptoMarketDataSchema;
import com.bmcapps.graphdailybriefingcrypto.model.graphSchema.CryptoSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CryptoServiceTest {

    @Mock
    private CoinMarketCapFeignClient coinMarketCapFeignClient;

    @Mock
    private CoinMarketCapGetQuotesToCryptocurrencySchemaMapper coinMarketCapGetQuotesToCryptocurrencySchemaMapper;

    @Mock
    private CoinMarketCapGetFearGreedToCryptoMarketSchemaMapper coinMarketCapGetFearGreedToCryptoMarketSchemaMapper;

    @InjectMocks
    private CryptoService cryptoService;

    // Coin market cap current quote setup
    private List<String> slugs;
    private CoinMarketCapQuotesApiResponse coinMarketCapQuotesApiResponse;
    private List<CryptoSchema> expectedCryptoSchemas;

    // Coin market cap fear greed setup
    CoinMarketCapFearGreedApiResponse fearGreedApiResponse;
    CryptoMarketDataSchema expectedMarketData;

    @BeforeEach
    void setUp() {

        // Initialize the CoinMarketCapQuotesApiResponse and expectedCryptoSchemas

        slugs = Arrays.asList("Bitcoin", "Ethereum");
        coinMarketCapQuotesApiResponse = new CoinMarketCapQuotesApiResponse();

        CryptoSchema bitcoinSchema = new CryptoSchema();
        bitcoinSchema.setName("Bitcoin");
        bitcoinSchema.setSymbol("BTC");
        bitcoinSchema.setPrice(50000.0);
        bitcoinSchema.setPercentChange24h(1.5);
        bitcoinSchema.setPercentChange60d(5.0);
        bitcoinSchema.setPercentChange90d(10.0);
        bitcoinSchema.setMarketCap(1000000000.0);

        CryptoSchema ethereumSchema = new CryptoSchema();
        ethereumSchema.setName("Ethereum");
        ethereumSchema.setSymbol("ETH");
        ethereumSchema.setPrice(4000.0);
        ethereumSchema.setPercentChange24h(0.5);
        ethereumSchema.setMarketCap(500000000.0);

        expectedCryptoSchemas = Arrays.asList(bitcoinSchema, ethereumSchema);

        // Initialize the CoinMarketCapFearGreedApiResponse and expectedMarketData

        fearGreedApiResponse = new CoinMarketCapFearGreedApiResponse();
        fearGreedApiResponse.setData(new DataResponse());
        fearGreedApiResponse.getData().setValue(55);
        fearGreedApiResponse.getData().setValueClassification("Neutral");

        expectedMarketData = new CryptoMarketDataSchema();
        expectedMarketData.setFearAndGreedIndexValue(fearGreedApiResponse.getData().getValue());
        expectedMarketData.setFearAndGreedIndexValueClassification(fearGreedApiResponse.getData().getValueClassification());
    }

    @Test
    void getCryptocurrencies_ShouldReturnMappedCryptoData() {
        // Arrange
        String slugsParam = String.join(",", slugs);
        when(coinMarketCapFeignClient.getCryptocurrencyQuotes(slugsParam)).thenReturn(coinMarketCapQuotesApiResponse);
        when(coinMarketCapGetQuotesToCryptocurrencySchemaMapper.mapCoinMarketCapGetQuotesToCryptoSchema(coinMarketCapQuotesApiResponse)).thenReturn(expectedCryptoSchemas);

        // Act
        List<CryptoSchema> result = cryptoService.getCryptocurrencies(slugs);

        // Assert
        assertNotNull(result);
        assertEquals(expectedCryptoSchemas.size(), result.size());
        assertEquals(expectedCryptoSchemas, result);
        verify(coinMarketCapFeignClient).getCryptocurrencyQuotes(slugsParam);
        verify(coinMarketCapGetQuotesToCryptocurrencySchemaMapper).mapCoinMarketCapGetQuotesToCryptoSchema(coinMarketCapQuotesApiResponse);
    }

    @Test
    void getCryptocurrencies_WithEmptySlugs_ShouldReturnEmptyList() {
        // Arrange
        List<String> emptySlugs = Collections.emptyList();
        String slugsParam = ""; // Empty string when slugs list is empty
        CoinMarketCapQuotesApiResponse emptyApiResponse = new CoinMarketCapQuotesApiResponse(); // Or null, depending on expected behavior
        List<CryptoSchema> emptyExpectedSchemas = Collections.emptyList();

        when(coinMarketCapFeignClient.getCryptocurrencyQuotes(slugsParam)).thenReturn(emptyApiResponse);
        when(coinMarketCapGetQuotesToCryptocurrencySchemaMapper.mapCoinMarketCapGetQuotesToCryptoSchema(emptyApiResponse)).thenReturn(emptyExpectedSchemas);

        // Act
        List<CryptoSchema> result = cryptoService.getCryptocurrencies(emptySlugs);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(coinMarketCapFeignClient).getCryptocurrencyQuotes(slugsParam);
        verify(coinMarketCapGetQuotesToCryptocurrencySchemaMapper).mapCoinMarketCapGetQuotesToCryptoSchema(emptyApiResponse);
    }

    @Test
    void getCryptoMarketData_ShouldReturnMappedMarketData() {
        // Arrange
        when(coinMarketCapFeignClient.getFearAndGreedIndex()).thenReturn(fearGreedApiResponse);
        when(coinMarketCapGetFearGreedToCryptoMarketSchemaMapper.mapToCryptoMarketDataSchema(fearGreedApiResponse))
                .thenReturn(expectedMarketData);

        // Act
        CryptoMarketDataSchema result = cryptoService.getCryptoMarketData();

        // Assert
        assertNotNull(result);
        assertEquals(expectedMarketData, result);
        verify(coinMarketCapFeignClient).getFearAndGreedIndex();
        verify(coinMarketCapGetFearGreedToCryptoMarketSchemaMapper).mapToCryptoMarketDataSchema(fearGreedApiResponse);
    }
}
