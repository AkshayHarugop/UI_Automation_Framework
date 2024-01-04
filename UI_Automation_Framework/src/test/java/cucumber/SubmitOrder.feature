@tag
Feature: Purchase the order from the Ecommerce website
  I want to use this template for my feature file


	Background:
	Given I landed on the Ecommerce page
 

  @Regression
  Scenario Outline: Positive test of the submitting the order
    Given Logged in with the username <name> and password <password>
    When I add the product <productName> from the cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the cofirmationPage.

    Examples: 
      | name  				  								| password 			| productName 		|
      | akshay@gmail.com  							|    Test@123   | ZARA COAT 3 		|
      | test_all_leader_ds2@gmail.com  	|    Test@123   | IPHONE 13 PRO 	|
      | test_all_leader_ds3@gmail.com   |    Test@123   | ADIDAS ORIGINAL |