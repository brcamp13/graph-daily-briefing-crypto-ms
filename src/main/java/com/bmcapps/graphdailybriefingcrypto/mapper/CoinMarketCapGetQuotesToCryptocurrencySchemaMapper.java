package com.bmcapps.graphdailybriefingcrypto.mapper;

import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes.CoinMarketCapQuotesApiResponse;
import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes.CryptoCurrency;
import com.bmcapps.graphdailybriefingcrypto.model.graphSchema.CryptoSchema;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CoinMarketCapGetQuotesToCryptocurrencySchemaMapper {
    public List<CryptoSchema> mapCoinMarketCapGetQuotesToCryptoSchema(CoinMarketCapQuotesApiResponse response) {
        Map<String, CryptoCurrency> apiCryptos = response.getData();

        return apiCryptos.values().stream()
                .map(this::mapCryptoCurrency)
                .collect(Collectors.toList());
    }

    private CryptoSchema mapCryptoCurrency(CryptoCurrency crypto) {
        CryptoSchema result = new CryptoSchema();
        result.setName(crypto.getName());
        result.setSymbol(crypto.getSymbol());

        if (crypto.getQuote() != null && crypto.getQuote().getUsd() != null) {
            result.setPrice(crypto.getQuote().getUsd().getPrice());
            result.setMarketCap(crypto.getQuote().getUsd().getMarketCap());
            result.setPercentChange24h(crypto.getQuote().getUsd().getPercentChange24h());
            result.setPercentChange60d(crypto.getQuote().getUsd().getPercentChange60d());
            result.setPercentChange90d(crypto.getQuote().getUsd().getPercentChange90d());
        }

        return result;
    }
}
