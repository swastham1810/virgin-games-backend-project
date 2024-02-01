package com.virgingames.virgingamesinfo;

import com.virgingames.testbase.TestBase;
import com.virgingames.virginInfoSteps.VirginGamesSteps;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;
@RunWith(SerenityRunner.class)
public class VirginGamesTest extends TestBase {

    ValidatableResponse response;
    @Steps
    VirginGamesSteps steps;
    @Title("This method will get all Jackpots information")
    @Test
    public void test001(){
        steps.getAllDataInGBP().statusCode(200).log().all();
    }
    @Title("This method will verify that if the currency is equal to GBP")
    @Test
    public void test002(){
        response = steps.getAllDataInGBP();
        HashMap<String, Object> gameMap = response.extract().path("data.pots[2]");
        System.out.println("List: " + gameMap);
        Assert.assertThat(gameMap, hasValue("GBP"));
    }
    @Title("This method will verify that the jackpot Id = Bingo")
    @Test
    public void test003(){
        response = steps.getAllDataInGBP();
        response.statusCode(200).log().ifValidationFails();
        response.body("data.jackpotId", equalTo("Bingo"));
    }
}
