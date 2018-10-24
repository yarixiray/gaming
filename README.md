Properties: 
Config.properties: browser: chrome (or firefox optional) 

Logs:
Application.log

Run application via cmd: 
mvn clean test -Dsurefire.suiteXmlFiles={%Path to the project%}/gaming/src/test/resources/runner/testng.xml
