@tag
Feature: Purchase order Erro validation from the Ecommerce website
  I want to use this template for my feature file

	@ErrorValidation
  Scenario Outline: Negative test of the submitting the order
    Given I landed on the Ecommerce page
    When Logged in with the username <name> and password <password>
    Then Verify the error msg

    Examples: 
      | name  				  								| password 			|
      | akshay@gmail.com  							|    Test@000   |
      | test_all_leader_ds2@gmail.com  	|    Test@121   |
      | test_all_leader_ds3@gmail.com   |    Test@122   |