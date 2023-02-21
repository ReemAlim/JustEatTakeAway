# JustEatTakeAway
An E2E and API selenide/selenium testing framework for JustEatTakeAway website and handling some of this http://coop.apps.symfonycasts.com/api
apis

# Getting Started
# Pre-requisits:
- Java 11
- IntelliJ IDE
- Chrome Browser 

# Libraries and Plugins used:
- Selenium 4
- Selenide 6
- TestNG
- RestAssured 
- AssertJ
- Maven surefireplugin

# Maven Commandlines to run the UI/API:
Run the following commandlines to run the tests:

For E2E tests:
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/uiTests.xml

For API tests:
mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/apiTests.xml

# E2E covered tests:
The Two mentioned sceantios in the assignment were covered 

# API covered endpoints:
Chose the following endpoints to cover:
- eggs-collect
- eggs-count
- toiletseat-down

# Notes:
There are alot to be enhanced and covered but for the sake of simplicity they werent handled for now, The following list states what could be enhanced later:
- Handle errors in a better way and wording
- Log file
- Multiple browsers to run (based on user preference), already added the code for Firefox but used Chrome 
- For api, some more cases should be covered especially for the cases the respond sent twice for the covered endpoints mentioned above
- For e2e, should get ALL the elements in the page not only ones in the viewport to assert on elements <= 10 as minimum charge



