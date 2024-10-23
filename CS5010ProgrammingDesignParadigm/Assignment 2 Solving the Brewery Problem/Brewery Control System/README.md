# Brewery Control System
## 1. Table of Contents
* [Overview](#2-overview)
* [Authors and Course Information](#3-authors-and-course-information)
* [System Workflow](#4-system-workflow-example)
* [Pre-set Test Example](#5-pre-set-test-example)
* [Usage Instructions](#6-usage-instructions)
* [Known Issues and Future Improvements](#7-known-issue-and-future-improvement)

## 2. Overview
The **Brewery Control System** is the **Assignment 2: Solving the Brewery Problem** of **CS5010 Programming Design Paradigm**. 
The system is a Java-based application designed to assist breweries in managing their key processes, including production, inventory, and recipe library. 
This command-line interface program enables users to seamlessly create beer batches, monitor stock levels, and manage recipes.   
In short, the system has three **Key System Components**:
* **Production System:** Handles create, monitor, and bottle batches, schedule and transfer batches, create and clean vats, and record the daily reading.
* **Inventory System:** Manages the brewery’s ingredients.
* **Recipe Library:** Stores and retrieves beer recipes.
## 3. Authors and Course Information
* **Author:** Erdun E
* **Course:** CS5010 Programming Design Paradigm
* **Program:** Assignment 2: Solving the Brewery Problem
* **Professor:** Dr. Lindsay Jamieson
## 4. System Workflow Example
* **Start with Main Menu**
   * User selects Production to create and manage batches.
   * User selects Inventory to add ingredients or check stock.
   * User selects Recipe Library to create or view recipes.
* **Production Workflow**
   * User creates a batch using a exist recipe.
     * The system checks if there is enough stock to produce the batch.
     * If sufficient stock is available, the batch is stored in an available vat.
   * User can monitor, transfer, or bottle batches as needed.
   * User schedule and perform transfers between vats using pipes.
   * User can also record the daily reading as needed
   * User can also create and clean the vat
* **Inventory Workflow**
   * User add ingredients to the inventory with name and among
   * User can check stock levels to ensure the availability of ingredients for future production.
* **Recipe Library Workflow**
   * User create recipes by specifying ingredient names and quantities.
   * The stored recipes can be viewed at any time to aid batch creation.
* **Error Handling and Help**
   * The system provides clear error messages for invalid input or insufficient stock levels.
   * Help menus are available to guide users through operations.
## 5. Pre-set Test Example
Below is a comprehensive test example that display the complete use of the system:
```aiignore
/Users/erdune/Library/Java/JavaVirtualMachines/openjdk-23/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=58012:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /Users/erdune/Desktop/NortheasternMiami/CS5010ProgrammingDesignParadigm/Assignment 2 Solving the Brewery Problem/Brewery Control System/out/production/Brewery Control System:/Users/erdune/.m2/repository/junit/junit/4.13.1/junit-4.13.1.jar:/Users/erdune/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/Users/erdune/.m2/repository/org/junit/jupiter/junit-jupiter/5.8.1/junit-jupiter-5.8.1.jar:/Users/erdune/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.8.1/junit-jupiter-api-5.8.1.jar:/Users/erdune/.m2/repository/org/opentest4j/opentest4j/1.2.0/opentest4j-1.2.0.jar:/Users/erdune/.m2/repository/org/junit/platform/junit-platform-commons/1.8.1/junit-platform-commons-1.8.1.jar:/Users/erdune/.m2/repository/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.jar:/Users/erdune/.m2/repository/org/junit/jupiter/junit-jupiter-params/5.8.1/junit-jupiter-params-5.8.1.jar:/Users/erdune/.m2/repository/org/junit/jupiter/junit-jupiter-engine/5.8.1/junit-jupiter-engine-5.8.1.jar:/Users/erdune/.m2/repository/org/junit/platform/junit-platform-engine/1.8.1/junit-platform-engine-1.8.1.jar:/Users/erdune/.m2/repository/org/testng/testng/7.1.0/testng-7.1.0.jar:/Users/erdune/.m2/repository/com/beust/jcommander/1.72/jcommander-1.72.jar:/Users/erdune/.m2/repository/com/google/inject/guice/4.1.0/guice-4.1.0-no_aop.jar:/Users/erdune/.m2/repository/javax/inject/javax.inject/1/javax.inject-1.jar:/Users/erdune/.m2/repository/aopalliance/aopalliance/1.0/aopalliance-1.0.jar:/Users/erdune/.m2/repository/com/google/guava/guava/19.0/guava-19.0.jar:/Users/erdune/.m2/repository/org/yaml/snakeyaml/1.21/snakeyaml-1.21.jar BreweryControlSystem.BreweryControlSystem
Welcome to Brewery Control System!
====Main Menu====
1. Production
2. Inventory
3. Recipe Library
4. Help
5. Exit
Please select an option: 
4

 Main Menu Help :
     Production : Manage beverage production processes, including batch creation, monitoring, and transfer.
      Inventory : Manage the ingredients in the brewery's inventory.
 Recipe Library : Create and view recipes for beverage production.
           Help : Display help for each options.
           Exit : Exit the application.
====Main Menu====
1. Production
2. Inventory
3. Recipe Library
4. Help
5. Exit
Please select an option: 
3
====Recipe Menu====
1. Create Recipe
2. View Recipe
3. Back to Main Menu
4. Help
5. Exit
Please select an option: 
4

    Recipe Menu Help :
       Create Recipe : Add a new recipe to the recipe library.
         View Recipe : Display a specific recipe from the recipe library.
   Back to Main Menu : Return to the main menu.
                Help : Display help for recipe management.
                Exit : Exit the application.
====Recipe Menu====
1. Create Recipe
2. View Recipe
3. Back to Main Menu
4. Help
5. Exit
Please select an option: 
1
Enter the name of the recipe:
IPA
Enter the number of ingredients (positive integer):
2
Enter the name of ingredient 1 (or type 'exit' to cancel):
Malt
Enter the quantity of ingredient 1:
150
Enter the name of ingredient 2 (or type 'exit' to cancel):
Hops
Enter the quantity of ingredient 2:
50
Recipe IPAcreated successfully and added to the Recipe Library.
====Recipe Menu====
1. Create Recipe
2. View Recipe
3. Back to Main Menu
4. Help
5. Exit
Please select an option: 
2
Enter the name of the recipe to view:
IPA
Recipe Name: IPA
Ingredients:
Hops: 50 units
Malt: 150 units
====Recipe Menu====
1. Create Recipe
2. View Recipe
3. Back to Main Menu
4. Help
5. Exit
Please select an option: 
3
====Main Menu====
1. Production
2. Inventory
3. Recipe Library
4. Help
5. Exit
Please select an option: 
2
====Inventory Menu====
1. Add Ingredient
2. Check Stock
3. Back to Main Menu
4. Help
5. Exit
Please select an option: 
4

 Inventory Menu Help : 
       Add Ingredient: Add ingredients to the inventory.
          Check Stock: Verify the stock levels of ingredients in inventory.
   Back to Main Menu : Return to the main menu.
                 Help: Display help for inventory operations.
                 Exit: Exit the application.
====Inventory Menu====
1. Add Ingredient
2. Check Stock
3. Back to Main Menu
4. Help
5. Exit
Please select an option: 
1
Enter the name of the ingredient:
Malt
Enter the quantity of the ingredient:
300
Ingredient Malt added to inventory.
====Inventory Menu====
1. Add Ingredient
2. Check Stock
3. Back to Main Menu
4. Help
5. Exit
Please select an option: 
1
Enter the name of the ingredient:
Hops
Enter the quantity of the ingredient:
100
Ingredient Hops added to inventory.
====Inventory Menu====
1. Add Ingredient
2. Check Stock
3. Back to Main Menu
4. Help
5. Exit
Please select an option: 
2
Checking stock...
Hops: 100 units
Malt: 300 units
====Inventory Menu====
1. Add Ingredient
2. Check Stock
3. Back to Main Menu
4. Help
5. Exit
Please select an option: 
3
====Main Menu====
1. Production
2. Inventory
3. Recipe Library
4. Help
5. Exit
Please select an option: 
1
====Production Menu====
1. Create Batch
2. Monitor Batch
3. Bottle Batch
4. Schedule Transfer
5. Transfer Batch
6. Clean Container
7. Record Daily Reading
8. Create Vat
9. Back to Main Menu
10. Help
11. Exit
Please select an option: 
10

Production Menu Help :
        Create Batch : Start the production of a new batch of beverage.
       Monitor Batch : Check the status of a batch in production.
        Bottle Batch : Bottle the completed batch of beverage.
   Schedule Transfer : Plan the transfer of a batch from one vat to another.
      Transfer Batch : Execute the transfer of a batch between vats.
     Clean Container : Mark a vat or container as cleaned.
Record Daily Reading : Log daily readings for a fermenting batch.
          Create Vat : Add a new vat to the brewery.
   Back to Main Menu : Return to the main menu.
                Help : Display help for production operations.
                Exit : Exit the application.
====Production Menu====
1. Create Batch
2. Monitor Batch
3. Bottle Batch
4. Schedule Transfer
5. Transfer Batch
6. Clean Container
7. Record Daily Reading
8. Create Vat
9. Back to Main Menu
10. Help
11. Exit
Please select an option: 
8
Enter the ID of the new vat:
1
Enter the capacity of the new vat:
1000
Added new Vat with ID: 1
====Production Menu====
1. Create Batch
2. Monitor Batch
3. Bottle Batch
4. Schedule Transfer
5. Transfer Batch
6. Clean Container
7. Record Daily Reading
8. Create Vat
9. Back to Main Menu
10. Help
11. Exit
Please select an option: 
1
Enter the recipe name to create a batch:
IPA
Enter the batch size:
10
Batch created and stored in Vat ID: 1
====Production Menu====
1. Create Batch
2. Monitor Batch
3. Bottle Batch
4. Schedule Transfer
5. Transfer Batch
6. Clean Container
7. Record Daily Reading
8. Create Vat
9. Back to Main Menu
10. Help
11. Exit
Please select an option: 
2
Enter the batch ID to monitor:
1
Monitoring batch ID: 1
Status: In Production
====Production Menu====
1. Create Batch
2. Monitor Batch
3. Bottle Batch
4. Schedule Transfer
5. Transfer Batch
6. Clean Container
7. Record Daily Reading
8. Create Vat
9. Back to Main Menu
10. Help
11. Exit
Please select an option: 
4
Enter the batch ID to schedule transfer:
1
Enter the destination vat ID:
2
Batch ID 1 is scheduled to transfer to Vat ID 2
====Production Menu====
1. Create Batch
2. Monitor Batch
3. Bottle Batch
4. Schedule Transfer
5. Transfer Batch
6. Clean Container
7. Record Daily Reading
8. Create Vat
9. Back to Main Menu
10. Help
11. Exit
Please select an option: 
5
Enter the batch ID to transfer:
1
Enter the source vat ID:
1
Enter the destination vat ID:
2
Pipe opened between Vat 1 and Vat 2
Transferring batch ID: 1 from Vat 1 to Vat 2
Pipe closed.
Batch transfer completed successfully.
====Production Menu====
1. Create Batch
2. Monitor Batch
3. Bottle Batch
4. Schedule Transfer
5. Transfer Batch
6. Clean Container
7. Record Daily Reading
8. Create Vat
9. Back to Main Menu
10. Help
11. Exit
Please select an option: 
7
Enter the batch ID to record the daily reading for:
1
Enter the gravity reading:
1.05
Daily gravity reading of 1.05 recorded for batch ID: 1
====Production Menu====
1. Create Batch
2. Monitor Batch
3. Bottle Batch
4. Schedule Transfer
5. Transfer Batch
6. Clean Container
7. Record Daily Reading
8. Create Vat
9. Back to Main Menu
10. Help
11. Exit
Please select an option: 
3
Enter the batch ID to bottle:
1
Batch ID 1 bottled and marked as completed.
====Production Menu====
1. Create Batch
2. Monitor Batch
3. Bottle Batch
4. Schedule Transfer
5. Transfer Batch
6. Clean Container
7. Record Daily Reading
8. Create Vat
9. Back to Main Menu
10. Help
11. Exit
Please select an option: 
6
Enter the vat ID to clean:
1
Vat ID 1 has been cleaned.
====Production Menu====
1. Create Batch
2. Monitor Batch
3. Bottle Batch
4. Schedule Transfer
5. Transfer Batch
6. Clean Container
7. Record Daily Reading
8. Create Vat
9. Back to Main Menu
10. Help
11. Exit
Please select an option: 
9
====Main Menu====
1. Production
2. Inventory
3. Recipe Library
4. Help
5. Exit
Please select an option: 
5
Brewery Control System Is Exiting...

Process finished with exit code 0

```
## 6. Usage Instructions
### Navigating the Menus
The Brewery Control System operates via nested menus. 
The Main Menu provides access to different sections of the system, including Production, Inventory, Recipe Library, Help, and Exit. 
Each section includes sub-menus that allow users to perform specific tasks.
#### Main Menu
The Main Menu is the entry point of the system. 
Users can choose between managing Production, Inventory, Recipes, or accessing Help, or exiting the system.
##### Main Menu Example
```aiignore
====Main Menu====
1. Production
2. Inventory
3. Recipe Library
4. Help
5. Exit
Please select an option: 
```
#### Production Menu
The Production Menu allows users to create, monitor, bottle the batches, schedule and transfer production batches, 
create and clean vats, record the daily reading, back to Main Menu option, help option and exit the system.
##### Production Menu Example
```aiignore
====Production Menu====
1. Create Batch
2. Monitor Batch
3. Bottle Batch
4. Schedule Transfer
5. Transfer Batch
6. Clean Container
7. Record Daily Reading
8. Create Vat
9. Back to Main Menu
10. Help
11. Exit
Please select an option: 
```
##### Create Batch
Allows users to create a new batch using an existing recipe.
```
Enter the recipe name to create a batch: IPA
Enter the batch size: 500
Batch created and stored in Vat ID: 1
```
##### Monitor Batch
Monitors an existing batch by ID and displays its current status.
```Enter the batch ID to monitor: 1
Monitoring batch ID: 1
Status: In Production
```
##### Bottle Batch
Marks a batch as bottled and updates its status to “Completed”.
```aiignore
Enter the batch ID to bottle: 1
Batch ID 1 bottled and marked as completed.
```
##### Schedule Transfer
Schedules a batch for transfer to another vat.
```aiignore
Enter the batch ID to schedule transfer: 2
Enter the destination vat ID: 3
Batch ID 2 is scheduled to transfer to Vat ID 3
```
##### Transfer Batch
Transfers a batch between vats using a pipe.
```aiignore
Enter the batch ID to transfer: 2
Enter the source vat ID: 1
Enter the destination vat ID: 2
Pipe opened between Vat 1 and Vat 2
Transferring batch ID: 2 from Vat 1 to Vat 2
Pipe closed.
Batch transfer completed successfully.
```
##### Clean Container
Cleans a vat by updating its status.
```aiignore
nter the vat ID to clean: 2
Vat ID 2 has been cleaned.
```
##### Record Daily Reading
Records a gravity reading for a batch.
```aiignore
Enter the batch ID to record the daily reading for: 1
Enter the gravity reading: 1.05
Daily gravity reading of 1.05 recorded for batch ID: 1
```
##### Create Vat
Creates a new vat with a specific capacity.
```aiignore
Enter the ID of the new vat: 4
Enter the capacity of the new vat: 1200
Added new Vat with ID: 4
```
##### Back to Main Menu
Back to the Main Menu
##### Help
Display help message for each options
```aiignore
Production Menu Help :
        Create Batch : Start the production of a new batch of beverage.
       Monitor Batch : Check the status of a batch in production.
        Bottle Batch : Bottle the completed batch of beverage.
   Schedule Transfer : Plan the transfer of a batch from one vat to another.
      Transfer Batch : Execute the transfer of a batch between vats.
     Clean Container : Mark a vat or container as cleaned.
Record Daily Reading : Log daily readings for a fermenting batch.
          Create Vat : Add a new vat to the brewery.
   Back to Main Menu : Return to the main menu.
                Help : Display help for production operations.
                Exit : Exit the application.
```
##### Exit
Exit the system
```aiignore
Brewery Control System Is Exiting...
```
#### Inventory Menu
The Inventory Menu allows users to manage ingredients, 
including adding new ingredients and checking stock levels.
##### Inventory Menu Example
```aiignore
====Inventory Menu====
1. Add Ingredient
2. Check Stock
3. Back to Main Menu
4. Help
5. Exit
Please select an option:
```
##### Add Ingredient
```aiignore
Enter the name of the ingredient: Malt
Enter the quantity of the ingredient: 200
Ingredient Malt added to inventory.
```
##### Check Stock
Displays the current stock levels for all ingredients.
```aiignore
Checking stock...
Malt: 200 units
Hops: 50 units
```
##### Back to Main Menu
Back to the Main Menu
##### Help
Display help message for each options
```aiignore
 Inventory Menu Help : 
       Add Ingredient: Add ingredients to the inventory.
          Check Stock: Verify the stock levels of ingredients in inventory.
   Back to Main Menu : Return to the main menu.
                 Help: Display help for inventory operations.
                 Exit: Exit the application.
```
##### Exit
Exit the system
```aiignore
Brewery Control System Is Exiting...
```
#### Recipe Library  Menu
The Recipe Library Menu provides options to create and view beer recipes.
##### Recipe Library  Menu Example
```aiignore
====Recipe Library Menu====
1. Create Recipe
2. View Recipe
3. Back to Main Menu
4. Help
5. Exit
Please select an option: 
```
##### Create Recipe
Prompts the user to input recipe details and stores the recipe in the library.
```aiignore
Enter the name of the recipe: Lager
Enter the number of ingredients: 2
Enter the name of ingredient 1: Malt
Enter the quantity of ingredient 1: 150
Enter the name of ingredient 2: Yeast
Enter the quantity of ingredient 2: 10
Recipe created: Lager
```
##### View Recipe
Displays the details of a specific recipe.
```aiignore
Enter the name of the recipe to view: Lager
Recipe Name: Lager
Malt: 150 units
Yeast: 10 units
```
##### Back to Main Menu
Back to the Main Menu
##### Help
Display help message for each options
```aiignore
    Recipe Menu Help :
       Create Recipe : Add a new recipe to the recipe library.
         View Recipe : Display a specific recipe from the recipe library.
   Back to Main Menu : Return to the main menu.
                Help : Display help for recipe management.
                Exit : Exit the application.
```
##### Exit
Exit the system
```aiignore
Brewery Control System Is Exiting...
```
## 7. Known Issue And Future Improvement
As the Brewery Control System project progressed through development and multiple rounds of local testing, many potential areas for optimization were found. As well, there are several fun and innovative features that could improve the user experience. However, due to lack of time and the heavy homework workload, I prioritized meeting the rubric to ensure complete it on time. Even though, the project offers great opportunities for future improvements and exploration. Given more time and resources, I plan to revisit the system, add more advanced features and making it smarter, more efficient, and user friendly.
1. **Redundant Execution Flow**
  - The system requires users to navigate multiple nested menus repeatedly, making the workflow long and inefficient, so implementing shortcuts for frequently used operations, like typing commands directly (e.g., “Create Recipe”), can improve efficiency.

2. **Boring Text-Based Interaction**
  - The heavy reliance on text-only interaction can lead to low user engagement, so adding colors, emojis, or ASCII art would make the outputs more engaging.

3. **Missing Global Exit and Back Functionality**
  - Users need to manually return to the previous menu, increasing frustration, so adding a universal back and exit command (like typing “exit” at any menu level) would enhance user experience.

4. **Inconsistent Error Messages**
  - Vague error messages can leave users unsure how to correct their input, so standardizing error messaging and providing actionable feedback will help users understand what to do next.

5. **Limited Boundary Constraints**
  - Insufficient validation for inputs like ingredient quantities can lead to invalid data entries, so introducing better validation and constraints for user input will prevent issues like negative values.

6. **Hardcoded Limits**
  - Some features depend on hardcoded limits, restricting scalability for larger breweries, so making initialization configurable would allow for dynamic scaling.

7. **No Undo Functionality for Critical Actions**
  - The inability to undo critical actions means users have to redo operations manually, so implementing an undo feature or confirmation prompts for critical actions will reduce user frustration.

8. **No User Authentication or Roles**
  - Allowing any user to access all functions without restrictions increases the risk of unauthorized changes, so adding role-based access control (e.g., admin and operator roles) will enhance security.

9. **No Notifications for Low Stock Levels**
  - Manually checking stock levels can delay production if users overlook low stock, so implementing real-time notifications for low stock levels will help prevent stockouts.

10. **Inadequate Help and Documentation**
  - The basic guidance in the help menu may leave new users struggling with workflows, so enhancing the help documentation with examples and more detailed instructions will improve user support.

11. **No Progress Tracking for Transfers or Production**
  - Without visibility into progress, users may feel uncertain about operations, so adding progress bars or status updates during critical operations will provide clarity.

12. **No Search Functionality**
  - Manually browsing lists for recipes or batches makes the system inefficient for large datasets, so adding a search function will allow users to quickly locate items.

13. **Limited Ingredient Tracking**
  - Tracking only total ingredient quantities makes it difficult for users to identify usage history, so adding ingredient usage logs will provide better insight.

14. **Duplicate Recipe Names Allowed**
  - Allowing multiple recipes with the same name can lead to confusion, so enforcing unique names or adding version control will help prevent errors.
Below is a detailed list of known issues and areas for potential improvement:
