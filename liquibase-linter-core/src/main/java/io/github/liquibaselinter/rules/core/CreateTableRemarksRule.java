package io.github.liquibaselinter.rules.core;

import com.google.auto.service.AutoService;
import io.github.liquibaselinter.config.RuleConfig;
import io.github.liquibaselinter.rules.ChangeRule;
import io.github.liquibaselinter.rules.LintRuleChecker;
import io.github.liquibaselinter.rules.LintRuleMessageGenerator;
import io.github.liquibaselinter.rules.RuleViolation;
import java.util.Collection;
import java.util.Collections;
import liquibase.change.Change;
import liquibase.change.core.CreateTableChange;

@AutoService(ChangeRule.class)
public class CreateTableRemarksRule implements ChangeRule {

    private static final String NAME = "create-table-remarks";
    private static final String DEFAULT_MESSAGE = "Create table must contain remark attribute";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Collection<RuleViolation> check(Change change, RuleConfig ruleConfig) {
        if (!(change instanceof CreateTableChange)) {
            return Collections.emptyList();
        }
        CreateTableChange createTableChange = (CreateTableChange) change;
        LintRuleChecker ruleChecker = new LintRuleChecker(ruleConfig);
        if (ruleChecker.checkNotBlank(createTableChange.getRemarks())) {
            LintRuleMessageGenerator messageGenerator = new LintRuleMessageGenerator(DEFAULT_MESSAGE, ruleConfig);
            return Collections.singleton(new RuleViolation(messageGenerator.getMessage()));
        }

        return Collections.emptyList();
    }
}
