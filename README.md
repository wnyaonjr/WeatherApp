## Name
Weather App

## Description
An Android application that allows user to get current weather based on current location from Openweather API, support local saving of weather history

## Persistence
The application uses Android Room for data persistence, it saves all the response from server which contains weather information


## Architecture
The application uses Dependency Injection pattern along with "screaming architecture" for use cases definition.
I often use this since it makes components reusable in the application, it also makes the functionalities descriptive through the use cases.

## Other information
Jetpack compose was used for the weather history list to showcase enthusiasm in learning new technologies related to Android, I also find it useful specially in reducing boilerplate codes when creating lists.

## How to use Openweather API key
Create an API_KEY value in local.properties file i.e.
API_KEY=123456789

## Limitation
Need to improve on error case handling
