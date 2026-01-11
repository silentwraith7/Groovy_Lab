package org.example.experiments

import groovy.util.logging.Log
import groovy.json.JsonSlurper

@Log
class ConfigLoader {

    def getAllPricingRules() {
        try {
            // File file = new File('/Users/sonu/Developer/Groovy-Study/Groovy_Lab/app/src/main/resources/pricing-rules.json')
            def stream = getClass().getResourceAsStream('/pricing-rules.json')
            def parsedJson = new JsonSlurper().parse(stream)
            return parsedJson
        }
        catch (Exception e) {
            log.severe('You done fucked up, add the json in resources folder')
            return [rules : []]
        }
    }

    def getPricingRulesBySegment(String segment) {
        def rules = getAllPricingRules()
        def rulesBySegment = rules?.rules?.findAll { item -> item.segment == segment }
        return rulesBySegment
    }

}
