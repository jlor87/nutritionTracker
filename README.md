# Nutrition Tracker

Developed by Sam Gray, Joshua Lor, and Haivan Benjamin

Description: The aim of The Nutrition Tracker is to help users meet their daily nutritional needs. It is
often said that people are not reaching the required amount of macro and micronutrients
throughout their day; in other cases, perhaps too much. It is a very arduous task to constantly
keep track of our diets and what we are putting in our bodies. The Nutrition Tracker aims to
simplify the process of cataloging an individual's diet and automatically maintaining all relevant
metrics so that a user has a "one-stop shop" for all their nutritional needs. 

Features:

Thus far in our engineering, our software will allow a user to perform the following tasks:

- Input what foods and drinks have been consumed throughout the day, this is done through the use of the USDA API. This will save all sorts of nutrition consumption information to your profile.
- Edit profile details such as sex, height, weight, and average daily exercise in order to
estimate daily caloric and nutritional needs.
- Set caloric or nutritional goals to be reached.
-take a look at a status update comparing your consumption to your goals.

Build and Run Instructions:
1. Clone the nutritionTracker repository using a Git-enabled terminal via the command: "git clone https://github.com/jlor87/nutritionTracker.git"
2. Using a Java IDE of your choice, configure the nutrionTracker folder as the project root directory.
3. Install dependencies and include them in your class path. This includes JUnit version 5 and GSON. (The GSON .jar file is in the /jar folder.)
4. Using your IDE, go into the Main.java class and run main(). Alternatively, using the command line, navigate inside the /src folder and type "javac Main.java" to compile, and then "java Main" to start.


Design Choice Discussion:

We chose to go for a modular approach, with several different classes that are split up to handle specific tasks. This is not near the last iteration of our project however, and classes are subject to much potential updating
and refactoring. For the main menu of our code, we are using a do/while loop in the session class. This class gets called at execution by Main. Main's only purpose is to start the program. 
