package org.example.experiments

import groovy.util.logging.Log

@Log
class PriceObject {

    List <Map> priceMap = [
             [ basePrice: 1000,
             currency: 'USD',
             productCode: 'PROD-1',
            customerSegment: 'B2B',
            region: 'APAC',
            discount: 0.10,
            ],
                     [
            basePrice: -50,
            currency: 'USD',
            productCode: 'PROD-2',
            customerSegment: 'B2B',
            region: 'EU',
],
            [
            basePrice: 500,
            currency: 'EUR',
            productCode: 'PROD-3',
            customerSegment: 'FREE',
            region: 'EU',
],
            [
            basePrice: 800,
            currency: 'USD',
            productCode: 'PROD-4',
            customerSegment: 'ENTERPRISE',
            region: 'US',
            discount: 0.50,
],
            [
            basePrice: 300,
            currency: 'USD',
            productCode: 'PROD-5',
            customerSegment: 'ENTERPRISE',
            region: 'US',
             ]
    ]

    List <Map> getPriceMap() {
        return priceMap
    }

    List <Map> calculateFinalPrices() {
        return getPriceMap().collect { item  ->
            if (item.basePrice > 0) {
                return  [*:item, finalPrice : item.basePrice * (1 - (item.discount ?: 0))]
            }
            log.warning("Negative basePrice for ${item.productCode} with basePrice ${item.basePrice}")
            return [*:item, finalPrice :0]
        }
    }

    List <Map> filterByRegion(String region) {
        return getPriceMap().findAll { item -> item.region == region }
    }

}
