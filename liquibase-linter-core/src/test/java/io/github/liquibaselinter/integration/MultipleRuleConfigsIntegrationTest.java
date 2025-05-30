package io.github.liquibaselinter.integration;

class MultipleRuleConfigsIntegrationTest extends LinterIntegrationTest {

    @Override
    void registerTests() {
        shouldFail(
            "Should fail on the uppercase etc pattern",
            "multiple-rule-configs/fail-first.xml",
            "multiple-rule-configs/lqlint.json",
            "Object name 'nope' name must be uppercase and use '_' separation"
        );

        shouldFail(
            "Should fail on the prefix pattern",
            "multiple-rule-configs/fail-second.xml",
            "multiple-rule-configs/lqlint.json",
            "Object name 'NOPE' name must begin with 'POWER'"
        );

        shouldPass(
            "Should pass with mutiple configs if they all pass",
            "multiple-rule-configs/pass.xml",
            "multiple-rule-configs/lqlint.json"
        );
    }
}
