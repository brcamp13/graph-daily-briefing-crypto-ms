package com.bmcapps.graphdailybriefingcrypto.service.GrpcService;



import com.bmcapps.graphdailybriefingcrypto.*;
import com.bmcapps.graphdailybriefingcrypto.model.graphSchema.CryptoMarketDataSchema;
import com.bmcapps.graphdailybriefingcrypto.model.graphSchema.CryptoSchema;
import com.bmcapps.graphdailybriefingcrypto.service.CryptoService;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;


@GrpcService
public class CryptoGrpcService extends CryptoServiceGrpc.CryptoServiceImplBase {

    private final CryptoService cryptoService;

    public CryptoGrpcService(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @Override
    public void getCryptoCurrencies(CryptoRequest request, StreamObserver<CryptoResponse> responseObserver) {
        try {
            List<String> slugs = request.getSlugsList();
            List<CryptoSchema> cryptoSchemas = cryptoService.getCryptocurrencies(slugs);
            CryptoResponse response = CryptoResponse.newBuilder()
                    .addAllCurrencies(cryptoSchemas.stream()
                            .map(this::mapToCryptoCurrency)
                            .collect(Collectors.toList()))
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getCryptoMarketData(MarketDataRequest request, StreamObserver<MarketDataResponse> responseObserver) {
        try {
            CryptoMarketDataSchema marketData = cryptoService.getCryptoMarketData();
            MarketDataResponse response = MarketDataResponse.newBuilder()
                    .setFearAndGreedIndexValue(marketData.getFearAndGreedIndexValue())
                    .setFearAndGreedIndexValueClassification(marketData.getFearAndGreedIndexValueClassification())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    private CryptoCurrency mapToCryptoCurrency(CryptoSchema schema) {
        return CryptoCurrency.newBuilder()
                .setName(schema.getName())
                .setSymbol(schema.getSymbol())
                .setPrice(schema.getPrice())
                .setMarketCap(schema.getMarketCap())
                .setPercentChange24H(schema.getPercentChange24h())
                .setPercentChange60D(schema.getPercentChange60d())
                .setPercentChange90D(schema.getPercentChange90d())
                .build();
    }
}
