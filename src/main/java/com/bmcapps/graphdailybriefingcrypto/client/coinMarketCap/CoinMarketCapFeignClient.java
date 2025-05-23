package com.bmcapps.graphdailybriefingcrypto.client.coinMarketCap;

import com.bmcapps.graphdailybriefingcrypto.config.CoinMarketCapFeignConfig;
import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.fearAndGreed.CoinMarketCapFearGreedApiResponse;
import com.bmcapps.graphdailybriefingcrypto.model.coinMarketCapApi.quotes.CoinMarketCapQuotesApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.bmcapps.graphdailybriefingcrypto.client.coinMarketCap.CoinMarketCapConstants.PARAM_SLUG;

@FeignClient(name = "coinMarketCapClient", url = "https://pro-api.coinmarketcap.com/", configuration = CoinMarketCapFeignConfig.class)
public interface CoinMarketCapFeignClient {
    @GetMapping("v2/cryptocurrency/quotes/latest")
    CoinMarketCapQuotesApiResponse getCryptocurrencyQuotes(
            @RequestParam(PARAM_SLUG) String cryptoSlugs
    );

    @GetMapping("v3/fear-and-greed/latest")
    CoinMarketCapFearGreedApiResponse getFearAndGreedIndex();
}
