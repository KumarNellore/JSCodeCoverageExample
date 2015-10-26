Code Coverage of JavaScript Application With JSCoverage.


Prerequisite

1. Download JsCoverage executable from site http://siliconforks.com/jscoverage/download.html
  a. If Operating System is Window download jscoverage-X.X.X-windows.zip [Where X represent version of release].  
  b. If Operating System is Linux download  jscoverage-X.X.X.tar.xz [Where X represent version of release].
2. If we have node application kindly install node exec from site https://nodejs.org/en/download/ [depending on os of individual machine]

Running Code Coverage for Application without instrumenting application code

1. Navigate to directory where jscoverage-server.exe or jscoverage-server.c file is placed.
2. Type command jscoverage-server.exe --document-root=<Root directory of javascript application> --ip-address=127.0.0.1 --port=3456 --verbose
   [This will start code coverage of your application in server mode, We can access code coverage application in browser by opening url :"http://127.0.0.1:3456/jscoverage.html"]
3. Now Start your javascript application Example case : node app\expressserver.js [Testing Example]
4. Now in URL "http://127.0.0.1:3456/jscoverage.html" enter application running url[Please replace localhost with 127.0.0.1]
5. Keep on performing operation in browser.
6. Now check in Summary tab.[We can see update on coverage]


For Testing Purpose, I have download application from https://github.com/juliemr/protractor-demo/

1. In command prompt go to directory where protractor-demo is downloaded.
2. Type command npm install from root directory of project to download all dependency for application.

What is this framework about

It Include

1. Example of event covered when selenium run test case in browser.
2. Example of Custom ReportNg Report with screen shot.
3. Javascript application Code Coverage with example


How to run Example Test Case

1. From command line go to root directory of project and type mvn clean.[To clean project]
2. From command line go to root directory of project and type mvn install.[To install dependency and run test case]
