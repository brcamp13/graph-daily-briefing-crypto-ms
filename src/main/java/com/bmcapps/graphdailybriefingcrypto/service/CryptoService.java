package com.bmcapps.graphdailybriefingcrypto.service;

import com.bmcapps.graphdailybriefingcrypto.client.coinMarketCap.CoinMarketCapFeignClient;
import com.bmcapps.graphdailybriefingcrypto.mapper.CoinMarketCapGetFearGreedToCryptoMarketSchemaMapper;
import com.bmcapps.graphdailybriefingcrypto.mapper.CoinMarketCapGetQuotesToCryptocurrencySchemaMapper;
import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.fearAndGreed.CoinMarketCapFearGreedApiResponse;
import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes.CoinMarketCapQuotesApiResponse;
import com.bmcapps.graphdailybriefingcrypto.model.graphSchema.CryptoMarketDataSchema;
import com.bmcapps.graphdailybriefingcrypto.model.graphSchema.CryptoSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryptoService {

    private final CoinMarketCapFeignClient coinMarketCapFeignClient;
    private final CoinMarketCapGetQuotesToCryptocurrencySchemaMapper coinMarketCapGetQuotesToCryptocurrencySchemaMapper;
    private final CoinMarketCapGetFearGreedToCryptoMarketSchemaMapper coinMarketCapGetFearGreedToCryptoMarketSchemaMapper;

    @Autowired
    public CryptoService(CoinMarketCapFeignClient coinMarketCapFeignClient,
                         CoinMarketCapGetQuotesToCryptocurrencySchemaMapper coinMarketCapGetQuotesToCryptocurrencySchemaMapper,
                         CoinMarketCapGetFearGreedToCryptoMarketSchemaMapper coinMarketCapGetFearGreedToCryptoMarketSchemaMapper
    ) {
        this.coinMarketCapFeignClient = coinMarketCapFeignClient;
        this.coinMarketCapGetQuotesToCryptocurrencySchemaMapper = coinMarketCapGetQuotesToCryptocurrencySchemaMapper;
        this.coinMarketCapGetFearGreedToCryptoMarketSchemaMapper = coinMarketCapGetFearGreedToCryptoMarketSchemaMapper;
    }

    public List<CryptoSchema> getCryptocurrencies(List<String> slugs) {
        String slugsParam = String.join(",", slugs);
        CoinMarketCapQuotesApiResponse response = coinMarketCapFeignClient.getCryptocurrencyQuotes(slugsParam);

        return coinMarketCapGetQuotesToCryptocurrencySchemaMapper.mapCoinMarketCapGetQuotesToCryptoSchema(response);
    }

    public CryptoMarketDataSchema getCryptoMarketData() {
        CoinMarketCapFearGreedApiResponse response = coinMarketCapFeignClient.getFearAndGreedIndex();
        return coinMarketCapGetFearGreedToCryptoMarketSchemaMapper.mapToCryptoMarketDataSchema(response);
    }
}
