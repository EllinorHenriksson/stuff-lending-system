# A2
Project for Assignment 2

A project template based on gradle and a gitlab pipeline. You should always build and run the application using gradle regularely.

[design.md](design.md) contains the prescribed architectural design of the application.

## Contributors
Both students contributed equally.   

Emma Fransson ef222hr   
Ellinor Henriksson eh224kr

## Usage of application
The application lets the user view and edit the members of a stuff lending system, including their items and lending contracts.

Run the application by entering `./gradlew run -q --console=plain`.

The user is presented with a main menu. Choose one of the options to proceed. 

The main menu lets the user view the members of the registry, add new members, select members and quit the application.   

The member menu (after selecting a member) lets the user delete the member, add items to the member and show the member information.   

The update member menu (after selecting update in the member menu) lets the user edit the member information.   

The item menu (after selecting an item) lets the user delete the item, establish contracts for the item, and show the item information.   

The update item menu (after selecting update in the item menu) lets the user edit the item information.

## Building
The build must pass by running console command:  
`./gradlew build`  
Note that you should get a report over the quality like:
```
CodeQualityTests > codeQuality() STANDARD_OUT
    0 CheckStyle Issues in controller/App.java
    0 CheckStyle Issues in controller/Simple.java
    0 CheckStyle Issues in model/Simple.java
    0 CheckStyle Issues in view/Simple.java
    0 FindBugs Issues in controller/App.java
    0 FindBugs Issues in model/Simple.java
    0 FindBugs Issues in view/Simple.java
    0 FindBugs Issues in controller/Simple.java
```

Removing or manipulating the code quality checks results in an immediate assignment **Fail**. 

## Running
The application should start by running console command:  
`./gradlew run -q --console=plain`

## Adding Your Own Code
The `Simple` classes project should likely be removed do not forget to also remove the test case associated to `model.Simple`.  

Add your own code to the packages respectively and feel free to add automatic test cases for your own code. A good process is to design a little - code a little - test a little one feature at a time and then iterate.

## Versioning
Adhere to the git versioning instructions according to the assignment.

## System test
Adhere to the instructions according to the assigment.

## Handing In
Adhere to the instructions according to the assigment.

