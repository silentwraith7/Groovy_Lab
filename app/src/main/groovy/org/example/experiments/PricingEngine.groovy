package org.example.experiments

import groovy.util.logging.Log

@Log
class PricingEngine {

    List<Map> applyRules(List<Map> products) {
        List<Map>  productsWithFP = []
        def config = new ConfigLoader()
        def maxDiscount = config.getAllPricingRules().maxDiscountPercent / 100
        println(maxDiscount)
        products.each { item ->
            def segmentRules =  config.getPricingRulesBySegment(item.customerSegment)
            def ruleDiscount = (segmentRules[0].discountPercent) / 100
            // def discount = item.discount && ruleDiscount ? [item.discount + ruleDiscount, maxDiscount].min() : 0
            def discount
            if (ruleDiscount && item.discount) {
                discount =  Math.min(item.discount + ruleDiscount, maxDiscount)
            } else {
                if (!item.discount) {
                    discount =  ruleDiscount
                }
            }
            if (item.basePrice > 0) {
                productsWithFP.add([*:item, finalPrice : item.basePrice * (1 - discount)])
            }
              else {
                log.warning("Negative basePrice for ${item.productCode} with basePrice ${item.basePrice}")
                productsWithFP.add([*:item, finalPrice : 0])
              }
        }
        return productsWithFP
    }

}
