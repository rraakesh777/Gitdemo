 @tag
 Feature: Purchase the oder from Ecommerce website
 i want to use this template for my feature file
 Background:
 Given I landed on Ecommerce Page
 @tag2
 Scenario Outline: Postive Test of submitting the order
 Given Logged in with username <name> and password <password>
 When  i add product <productName> to cart
 And   checkout <productName> and Submit the order
 Then  "Thankyou for the order." message is displayed on ConfirmationPage
 
 Examples:
	|name					| password		| productName  |
	|rraakesh777@gmail.com  | Java@4321		| ZARA COAT 3 |
 