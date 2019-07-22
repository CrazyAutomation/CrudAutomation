@PBI_7760 @Sprint_12 @nightlybuild
Feature: MAH entering only 1 or 2 characters in the Primary Source fields
  I WANT to be restricted to entering only 1 or 2 characters in the Primary Source fields of an Adverse Event report
  SO THAT the UI and the database are in sync

  Background:
    Given I am an MAH
    And I am completing an Adverse Event Report
    And I can fill the report information page
      | ID2445HJ | P17654390 | United Kingdom |
    When I click on Add an involved party link

  Scenario Outline: Restrict input to 1 or 2 characters
    Then I enter a restricted number of characters in the FirstName "<FirstName>"
    And I enter a restricted number of characters in the LastName "<LastName>"
    And I enter a restricted number of characters in the PostCode "<PostCode>"
    And I select country
    And I select category
    And I click on the save and continue button
    Examples:
      | FirstName | LastName | PostCode |
      | U         | Y        | UB       |

  Scenario:2: Verify Field Names labels
    Then  FirstName field name should be First name "Initial only"
    And  LastName field name should be Last name "Initial only"
    And  postcode field name should be as Postcode "First two characters only"

  Scenario:3: Validate mandatory Error Message
    When  I click on the save and continue button
    Then  I get the following error message displays
      | Enter a last name (initial only)       |
      | Please select a country from the list  |
      | Please select a category from the list |


  Scenario Outline: Validate error message when given more than 1 or 2 characters
    And I enter more than one character in the FirstName "<FirstName>"
    And I enter more than one character in the LastName "<LastName>"
    And I enter more than two characters in the PostCode "<PostCode>"
    When I click save and continue button it triggers the validations
    Then I shall see following error text message displayed as "<error>"
    Examples:
      | FirstName | LastName  | PostCode |  error                                            |
      | U1         | Y        | UB       |  First name can only be a maximum of 1 character  |
      | U          | Y1       | UB       |  Last name cannot exceed 1 character              |
      | U          | Y        | UB1      |  Postcode can only be a maximum of 2 characters   |
