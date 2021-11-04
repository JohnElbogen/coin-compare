# coin-compare

Build and Run

1. Download Java version 17.0.1
```
https://jdk.java.net/17/
```
2. Download Gradle version 7.2
```
https://gradle.org/install/
```
3. Download node.js
```
https://nodejs.org/en/
```
4. Download the coin-compare github files.
5. Open the FetchPrice.js file
```
C:\{project filepath}\coin-compare\coins\src\main\frontend\coins-react\src\components\FetchPrice.js 
```
and edit the fetch() function in any code editor of choice.
replace
```
http://ec2-18-223-32-238.us-east-2.compute.amazonaws.com:8080/api
```
with
```
http://localhost:8080/api.
```

The code after being changed will look like the following:
```
fetch("http://localhost:8080/api")
```
This step is required to build and run on your local machine. The URL in the file originally is for the
amazon webserver that I used to host the site. Available here:
```
http://ec2-18-223-32-238.us-east-2.compute.amazonaws.com:8080/index.html
```
6. Open CMD change directory (CD) to 
```
C:\{project filepath}\coin-compare\coins\src\main\frontend\coins-react
```
7. Enter the command
```
npm install
```
8. Change directory (CD) to 
```
C:\{project filepath}\coin-compare\coins
```
9. Enter the command
```
gradle build
```
10. Change directory (CD) to 
```
C:\{your filepath location}\coin-compare\coins\build\libs
```
11. Enter the command\
```
java -jar coins-0.0.1-SNAPSHOT.jar
```
15. Open up a browser and enter in the URL bar
```  
http://localhost:8080/index.html
```
The web application should be similar to the following link if setup correctly!
```
http://ec2-18-223-32-238.us-east-2.compute.amazonaws.com:8080/index.html
```
