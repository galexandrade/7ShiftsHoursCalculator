[![CircleCI](https://circleci.com/gh/galexandrade/7ShiftsHoursCalculator.svg?style=svg)](https://circleci.com/gh/galexandrade/7ShiftsHoursCalculator)
[![codecov](https://codecov.io/gh/galexandrade/7ShiftsHoursCalculator/branch/master/graph/badge.svg)](https://codecov.io/gh/galexandrade/7ShiftsHoursCalculator)

**7Shifts Hours Calculator API**

Description:
This load the data (users, locations, timePunches) from an endpoint end calculate the hours a user worked over all.
The program calculate the regular hours, as well as daily and weekly overtime hours. Overtime rules come from a location’s setting. Overtime is triggered one of two ways. If an employee works more than X hours a day (daily overtime), or more than Y hours a week (weekly overtime). Overtime is paid for whichever overtime number is greater.

Create a REST API (/api/v1/hours/{userId}) that retrive the calculated data. 
 
Live: https://sevenshifts-hours-calculator.herokuapp.com/swagger-ui.html
 
 Technologies applied:
 * Gradle - Dependencies management
 * Springboot - Java Framework
 * TDD (JUnit) - Tests 
 * Project Lombok - Getters and Setters, Constructors
 * Swagger - API Documentation
 * CircleCI - Continuous Integration
 * Codcov.io - Tests coverage
 
Run in local environment:

1 - Install Gradle

    https://gradle.org/install/ 
    
2 - Run tests (Command line)

    gradlew test
    
3 - Run project

    gradle bootRun
    

BUNUS 1

Due to internet connection issues, I was not able to develop with Angular, as I intend, so I implemented the solution with pure JavaScript + Jquery 

    Available in: /resources/static/index.html


**Author**

Name: Alex P. Andrade

Email: g.alex.andrade@gmail.com
