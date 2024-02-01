package com.virgingames.cucumber.steps;

import com.virgingames.constants.EndPoints;
import com.virgingames.virginInfoSteps.VirginGamesSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;

import java.util.List;

public class VirginGamesStepsDef {
    static ValidatableResponse response;
    static List<String> currency;
    @Steps
    VirginGamesSteps steps;
    @Given("User is on Homepage")
    public void userIsOnHomepage() {

    }

    @When("User get data of Bingo's Jackpot currency")
    public void userGetDataOfBingoSJackpotSCurrency() {
        response = steps.getAllDataInGBP();
        response.log().all();
    }

    @Then("User must get back valid status code {int}")
    public void userMustGetBackValidStatusCode(int statusCode) {
        response.statusCode(statusCode);
    }

    @When("User check that the currency is in GBP")
    public void userCheckThatTheCurrencyIsInGBP() {
        currency = response.extract().jsonPath().get("data.pots.currency");
        for (String currencyGBP : currency) {
            Assert.assertEquals(currencyGBP, EndPoints.CURRENCY);
        }
    }

    @Then("User Verify the {string}  and {string}")
    public void userVerifyTheAnd(String potsId, String name) {

        String s1 = "data.pots.find{it.id == '";
        String s2 = "'}.name";

        response.statusCode(200);
        String nameGBP = response.extract().path(s1+ potsId +s2);
        Assert.assertEquals(nameGBP, name);
        System.out.println(name + " : " + potsId);

    }
}
