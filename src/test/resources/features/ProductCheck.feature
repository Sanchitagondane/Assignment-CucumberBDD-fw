@ui @productcheck
Feature: E-commerce Project Web Site Product Check

#Background: Navigation to the URL
 #Given User navigated to the home application url
 

Scenario: User is able to Open the browser, navigate to the URL and Search for Product
  #Given User opened browser
  And User navigated to the home application url
  When user viewed for application logo 
  Then application logo is displayed
  #And user close the browser


 @t
Scenario: User is click on the Product and check the Product Details
   #Given User opened browser
   And User navigated to the home application url
   When User search for product categories
   Then User Click on Any Product
   #And user closed the browser

 @t
Scenario: User is click on the Product and check the Product Details
   #Given User opened browser
   And User navigated to the home application url
   When User Search for product "T-Shirt"
   Then User can see an result contains T-shirt as Text
   #And User Click on the closed browser


 @t
Scenario: User is Click on Social media handles validation
   #Given User opened browser
   And User navigated to the home application url
   And User Click on Social media Application twitter logo image
   When Validate Url is displayed in new tab
   Then User can able to see an twitter account name as Selenium Framework
   #And user Closed the window

