package org.example.experiments

import groovy.util.logging.Log
import groovy.json.JsonSlurper
import org.example.exceptions.*

@Log
class ConfigLoader {

    def getAllPricingRules() {
        // File file = new File('/Users/sonu/Developer/Groovy-Study/Groovy_Lab/app/src/main/resources/pricing-rules.json')
        def stream = getClass().getResourceAsStream('/pricing-rules.json')
        def parsedJson = new JsonSlurper().parse(stream)
        validateRules(parsedJson)
        return parsedJson
    }

    def getPricingRulesBySegment(String segment) {
        def rules = getAllPricingRules()
        def rulesBySegment = rules?.rules?.findAll { item -> item.segment == segment }
        return rulesBySegment
    }

    def validateRules(Map parsedJson) {
        def allValid =   parsedJson.rules.every { rule ->
            rule.segment && rule.discountPercent != null && rule.priority != null
        }
        if (!allValid) {
            log.severe('Some rules missing required fields!')
            throw new ConfigValidationException()
        }
    }

}
