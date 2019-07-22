@PBI-11740, @regression
Feature: AER - Check your answers page basic(Animal)
  As a MAH (Marketing Authorisation Holder) user
  I want to view my answers I provided for my draft 'Animal AER'
  So that I can either submit or edit or delete my report



  Scenario:1 - Verify the Student application page title
    Given CRUD application
    When I validated Student Application title

  @Test01
  Scenario:2 - Verify the user is able to create a new Student record in Student Application
    Given CRUD application
    When I click on Create new link
    Then I should be on the "StudentModel" page
    When I submit "StudentModel" page
    Then I should see "Student Details Added Successfully" text on the page


  Scenario:3 - Verify a student record and delete it from the DB
    Given CRUD application
    When I click on Create new link
    Then I should be on the "StudentModel" page
    When I submit "StudentModel" page
    Then I should see "Student Details Added Successfully" text on the page


  Scenario:4 - Verify a student record and Edit it from the DB
    Given CRUD application
    When I click on Create new link
    Then I should be on the "StudentModel" page
    When I submit "StudentModel" page
    Then I should see "Student Details Added Successfully" text on the page