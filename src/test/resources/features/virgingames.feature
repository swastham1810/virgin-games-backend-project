Feature: Virgin Games Currency Test
  As a User,
  I would like to verify the currency data options

  Scenario Outline: Check User should get data of Bingo Jackpot's currency in GBP
    Given   User is on Homepage
    When    User get data of Bingo's Jackpot currency
    Then    User must get back valid status code 200
    Then    User check that the currency is in GBP
    Then    User Verify the "<potsId>"  and "<name>"
    Examples:
      | potsId | name       |
      | 193    | Enterprise |
      | 194    | Adventure  |
