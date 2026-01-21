package org.example.experiments

import groovy.util.logging.Log

@Log
class PricingEngine {

    List<Map> applyRules(List<Map> products) {
        List<Map>  productsWithFP = []
        def config = new ConfigLoader()
        def maxDiscount = config.getAllPricingRules().maxDiscountPercent / 100
        products.each { item ->
            def ruleDiscount
            def segmentRules =  config.getPricingRulesBySegment(item.customerSegment)
            if (segmentRules) {
                segmentRules.sort { -it.priority }
                ruleDiscount = (segmentRules[0].discountPercent) / 100
                log.info("${item.productCode} -> Rule ${segmentRules[0].name}(priority : ${segmentRules[0].priority}),discount:${segmentRules[0].discountPercent}%")
            } else {
                log.warning("No segment rules provided for product ${item.productCode} with segment ${item.customerSegment}")
                ruleDiscount = (config.getAllPricingRules().defaultDiscount) / 100
            }
            // def discount = item.discount && ruleDiscount ? [item.discount + ruleDiscount, maxDiscount].min() : 0
            def discount
            if (ruleDiscount && item.discount) {
                discount =  Math.min(item.discount + ruleDiscount, maxDiscount)
                if (item.discount + ruleDiscount > maxDiscount) {
                    log.info("${item.productCode} -> Discount ${segmentRules[0].discountPercent}% capped to ${maxDiscount*100}% ")
                }
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
