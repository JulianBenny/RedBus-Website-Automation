### Exit Test 
Automation Testing Framework on www.redbus.in

### PROJECT DESCRIPTION
In this assignment, different test scenarios have been automated using Selenium and TestNg framework.


### How to Run

```
1.Clone repository from github "https://github.com/JulianBenny/RedBus-Website-Automation.git"
2.Run Runner.bat file 
```


### JENKINS IMPLEMENTATION

Access the WordFile named Jenkins for its implementaion.

### NOTE

##### HEADLESS MODE 


```
For Headless Mode:

Step 1.Go to the Resources folder
Step 2.Open the config.properties file
Step 3.Change the Headlessoption to true
				if required the headless mode
				else if required the Non-headless mode.
Step 4.Save it.

```


##### DOCKER IMPLEMENTATION


```
For Docker Mode:

Step 1.Go to the Resources folder
Step 2.Open the config.properties file
Step 3.Change the DOCKER_RUN to true
				if required the docker mode on
				else if required the normal mode.
Step 4.Save it.
```

Now to run it

```
For Docker Mode:

Step 1. Go to the the project location on cmd
Step 2. Run the command 'docker-compose -f docker-compose.yml up -d'
Step 3. And run the project 
Step 4. After the test execution done , run the command 'docker-compose -f docker-compose.yml down'
```
##### EXCEL FILE IMPLEMENTATION

```
For Excel File Access:

Step 1. Go to the Resources folder
Step 2. Open the User_Data.xlsx in Test Data folder
Step 3. Update the data accordingly.


```

##### PARALLEL IMPLEMENTATION

```
For Parallel execution:

Step 1.Open the testng.xml file 
Step 2. add  command in the suite tag
         here:-      <suite name="Suite" >
	      add this command:- 	parallel="classes"


```
## FOLDER STRUCTURE

### src/main/java DESCRIPTION


**in.redbus.pages**- It consist of page action file required to run any test file.

**pageUtils**- It consist of some common utilities file i.e required by page action action files.


### src/test/java DESCRIPTION

**com.redbus.tests** - It contains all the tests files and Base Test

**utilities** - Some utility files required while testing.


### drivers Folder

It contain all the drivers required to open the browsers like chromedriver, geckodriver etc.

### Reports Folder

Generate the extent reports for all the test cases along with the screenshots if test gets failed

### logs Folder

Stores all the logs

### Resources Folder

**Test Data** - contains TestDataRedBus.xlsx file 

**config.properies file**


### Runner Batch File

Use this to run the project through .bat file