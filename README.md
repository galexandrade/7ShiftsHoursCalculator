[![CircleCI](https://circleci.com/gh/galexandrade/7ShiftsHoursCalculator.svg?style=svg)](https://circleci.com/gh/galexandrade/7ShiftsHoursCalculator)
[![codecov](https://codecov.io/gh/galexandrade/7ShiftsHoursCalculator/branch/master/graph/badge.svg)](https://codecov.io/gh/galexandrade/7ShiftsHoursCalculator)

**7Shifts Hours Calculator API**

Description:
This load the data (users, locations, timePunches) from an endpoint end calculate the hours a user worked over all.
The program calculate the regular hours, as well as daily and weekly overtime hours. Overtime rules come from a location’s setting. Overtime is triggered one of two ways. If an employee works more than X hours a day (daily overtime), or more than Y hours a week (weekly overtime). Overtime is paid for whichever overtime number is greater.
 
 
 Technologies applied:
 * Springboot
 * TDD (JUnit, Mokito)
 * Project Lombok
 * Swagger
 * CircleCI
 * Codcov.io
 
