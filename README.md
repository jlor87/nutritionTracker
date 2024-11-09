# Nutrition Tracker

Developed by Sam Gray, Joshua Lor, and Haivan Benjamin

Description: The aim of The Nutrition Tracker is to help users meet their daily nutritional needs. It is
often said that people are not reaching the required amount of macro and micronutrients
throughout their day; in other cases, perhaps too much. It is a very arduous task to constantly
keep track of our diets and what we are putting in our bodies. The Nutrition Tracker aims to
simplify the process of cataloging an individual's diet and automatically maintaining all relevant
metrics so that a user has a "one-stop shop" for all their nutritional needs. 

Features:

This application allows the user to perform the following tasks:
- Search for food items to add to the user's food diary via the use of the USDA API.
- Input custom foods and their nutritional data and save these custom foods to their profile.
- Create a health profile and edit personal details such as sex, height, weight, and average daily exercise level in order to estimate proper caloric and nutritional needs.
- Set personal caloric and nutritional goals to be reached.
- View current status updates regarding the user's uniquely tailored daily nutrition goals.

Build and Run Instructions:
1. Clone the nutritionTracker repository using a Git-enabled terminal via the command: "git clone https://github.com/jlor87/nutritionTracker.git"
2. Using a Java IDE of your choice, configure the nutritionTracker folder as the project's root directory.
3. Install dependencies and include them in your class path. This includes JUnit version 5, GSON v2.11, and the MySQL Connector v9.1.0.
4. Using your IDE, go into the Main.java class and run main(). Alternatively, using the command line, navigate inside the /src folder and type "javac Main.java" to compile, and then "java Main" to start.


Design Choice Discussion:

Using a modular approach, application functions were split into different classes, each designed to handle a set of related tasks by their name (Main, GUI, Session, API, UserSettings, etc.).
It was decided that Main's responsibility was to simply load the application, while the bulk of responsibility for app navigation was offloaded to the GUI class. This was due to the increased advantage of having increased cohesion in the GUI class, where all screen creation, displaying, and navigation-related tasks were more easily accessible within the same class without the need of external calls to it.
To offload application functionality off of the GUI class, the Session class (uniquely created for each user that logs on) serves as a "manager," calling upon other functional classes only when needed (such as API, Print, UserSettings, etc.). While not containing much code in itself, the Session class does always know which class to delegate important tasks to.
Every other class serves their functional purpose, simplifying application maintainability. As a result, the Nutrition Tracker strategically seeks to be an easy-to-follow, scalable, and maintainable application with enormous potential.