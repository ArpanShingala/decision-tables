package com.demo.rules;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.util.Arrays;

public class DroolsDecisionTableExample {

    public static void main(String[] args) {
        // using KieServices we can create new instances of KieFileSystem, KieBuilder, and KieContainer if required
        KieServices kieServices = KieServices.Factory.get();

        // for this tutorial we will use KieClasspathContainer, which eventually refers meta-inf kmodule file
        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        // create a stateless session
        StatelessKieSession kieSession = kieContainer.newStatelessKieSession("DecisionTable");

        // setup driver test data
        Driver driver = new Driver();
        driver.setAge(27);
        driver.setPriorClaims(1);
        driver.setLocationRiskProfile("MED");

        // setup policy test data
        Policy policy = new Policy();
        policy.setType("COMPREHENSIVE");

        // pass the objects to kie session and execute
        kieSession.execute(Arrays.asList(new Object[]{driver, policy}));

        System.out.println("BASE PRICE IS: " + policy.getBasePrice());
        System.out.printf("DISCOUNT IS: %d%%", policy.getDiscountPercent());
    }
}
