# Mozio Test

This app calculate the probability to suffer Todd's Syndrome.

## Features

- Calculate the probability based in four parameters.
- Show the user the probability and the answers.
- Store and Display the previous answers usin the menu.
- Show a back button in case the device does not have a phisycal one.
- Use Android Locale to catch the devices language (currently can change the app to English and Spanish).
- If the all the questions were not answered the app show an alert dialog preventing from continue.
- If there is not a previous answers storage the app show an alert preventing to continue.
- Implemented Unit Test against the app model.

## Switching from local to Rest.

To change from local to rest it recommended to: 

- Use Retrofit 2 for the data serialization and transfer to a server
- Change the model to manage a list of Answers instead a singlr one.
- Modify the History View to show all the answer given from all user.
- Add a view to show statictics and graphics based in all the answers.

## Notes:

- Aplication developed and builded with Android Stuido 3 and Gradle 3.
