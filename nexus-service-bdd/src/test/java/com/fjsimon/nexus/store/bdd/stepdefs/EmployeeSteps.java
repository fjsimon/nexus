package com.fjsimon.nexus.store.bdd.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import io.restassured.response.Response;

import java.util.List;


/**
 * Step Definition Class for Employee.
 *
 * <p>Uses Java Lambda style step definitions so that we don't need to worry
 * about method naming for each step definition</p>
 */
public class EmployeeSteps extends AbstractSteps implements En {

    public EmployeeSteps() {

        Given("user wants to call greeting endpoint {string}", (String name) -> {
            testContext().reset();
            super.testContext().set("name", name);
        });

        And("with the following phone numbers", (DataTable phoneDt) -> {
//            List<Phone> phoneList = phoneDt.asList(Phone.class);
//            super.testContext()
//                    .getPayload(Employee.class)
//                    .setPhones(phoneList);
        });

        When("get greeting request", () -> {
            String greetingUrl = "/greeting";
            executeGet(greetingUrl);
        });

        Then("call {string}", (String expectedResult) -> {
            Response response = testContext().getResponse();

            switch (expectedResult) {
                case "IS SUCCESSFUL":
                    assertThat(response.statusCode()).isIn(200, 201);
                    break;
                case "FAILS":
                    assertThat(response.statusCode()).isBetween(400, 504);
                    break;
                default:
                    fail("Unexpected error");
            }
        });

    }
}
