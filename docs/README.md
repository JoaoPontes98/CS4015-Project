# Documentation

## Project Overview
#### Tasks
Tasks are a simple object that contain a description of something that needs to be done, and an indicator of whether that thing has been done. Users can create tasks for things like chores, appointments, meetings, or habits. Tasks can be either basic tasks, or have extra functionality by being an appointment, a recurring task, or both.

#### Appointments
Tasks can also be made appointments, which contains a description, a done indicator, as well as a location and time. This extra information will also be displayed along with the description so the user knows where they have to go and when.

#### Recurring Tasks
Tasks can be defined as recurring, which means it has a description, a done indicator, as well as a number of recurrences and a type of recurrence (Daily, Weekly, etc.). Recurring tasks will display this extra information, as well as perform extra functionality when it is “checked off”: recurring tasks will repeat themselves and automatically insert themselves into a new or existing list in the future. This allows users to create repeating tasks without having to manually recreate them.

#### Lists
Lists are the primary containers of tasks. When a user wants to create a list, it must be associated with a certain day. After browsing and selecting a list by selecting its date, the app will display all tasks and their associated information contained within that list. Lists can be edited by adding additional tasks, or another list.

#### Sublists
Lists can also contain other lists which are associated with a description instead of a day. Users can add a sublist to an existing list in the same way that they can add a task. Sublists can be used to further organize a list by having a description (such as Chores), and containing other tasks and even other sublists. Sublists can also be checked off to mark them as complete, which will automatically mark all of the tasks and sublists it contains as completed.


## Using this App
#### Starting Out
Upon compiling and running the app (see the project’s main README), the user is presented with the main screen. This page is populated by first importing existing data. This is done by calling a method which will arrange the data and return it in an expected format to the app. Currently this method simply sets up test data, however in the future it could be edited to retrieve data from any source. Then, the app will determine whether a list for today’s date exists. If it does, it will display that list. If it does not, it will create one for today.

In the case of using this project’s test data, a list will be populated for the current data with various tasks.

#### Creating New Lists
Users can create new lists by selecting the “Create New” button at the bottom of the window. This will open a dialog requesting the user to enter a date. The date should be entered in the following format: yyyy/mm/dd. Upon selecting the “OK” button, if the date is not of the correct format or cannot be parsed an error will be output into the terminal and no list will be created. If the date is already associated with an existing list, that list will be displayed by the app. Otherwise, a new list for that date will be created and the empty list will be displayed.

#### Browsing Lists
Users can browse existing lists by selecting the “Browse” button at the bottom of the window. This will open a dialog providing a scrollable pane of all dates that have an existing list associated with them. Users can select any provided date and select “OK” to display that list in the app.

#### Adding Items
To add a new items, users can select the “Add” button in a list. There may be several “Add” buttons in one day, as each button is aligned with one list, including the primary list and all sublists. Selecting the “Add” button aligned with a sublist will add the item to that sublist. A menu will pop up allowing users to select “New Task” and “New Sublist”.

Upon selecting the “New Task” option, a dialog will open requesting information regarding the new task. All tasks require a description, but the user can also select whether it is recurring or an appointment by checking the “Recurring” or “Appointment” boxes. Checking either box will make new fields appear that the user must fill in. After selecting “OK”, the new task will be created.

Upon selecting the “New Sublist” option, a dialog will open requesting information regarding the new sublist. All sublists require a description. After selecting “OK”, the new sublist will be created. Initially, the sublist will appear to be completed, as it has no incomplete tasks. Upon adding an incomplete task the sublist will appear incomplete again.

#### Recurring Tasks
While appointments only provide extra information to display, a recurring task has several differences in its functionality. Upon creating a recurring task (see above), the user will be asked to enter a number of times the task should recur, and an interval it should recur at (Daily, Weekly, Bi-Weekly, or Monthly). Upon completing a recurring task and checking it off, the current list’s task will not change, but will copy itself into a future list based on the interval. It will also subtract one from the number of recurrences in the new copy, so that users can determine how many more recurrences will occur after a certain list. If the future list does not exist, it will be created.

## Design Patterns
#### Composite Pattern
This pattern is the backbone of the to-do list structure. In this app, the lists are the containers for many items. These items can be single units (Tasks) or other containers (Sublists). Regardless of how these items are structured, or their depth, we would like to be able to interact with the entire structure of a list as if it were one single object. So, lists and tasks were implemented as a composite structure. This needs to be done for several reasons:
- Firstly, we need to be able to easily pass around data for an entire a list. This can be done by placing the entire set of tasks and sublists within a List object, so that all of its data is accessible from a single parent object. Otherwise we would have to find some other way to link separate objects together, find them, and return them whenever required, which would require much more code and planning while also being prone to errors.
- Secondly, we need to be able to naturally implement sublists without unnecessary restrictions. Without a composite, we could implement sublists by making a separate class, but this would require us to define where in lists sublists can be created, what combinations of tasks and other sublists they can contain, and what their maximum depth would be. However, as a tree we can allow users to create endless combinations of sublists and tasks at any depth they wish with minimal planning and coding on our part.
- Thirdly, we need to be able to easily display a list in the UI. This involves displaying the date associated with a list, and all of its tasks, all of its sublists, all of the tasks within the sublists, and so on and so forth. Instead of trying to anticipate how many sublists will exist and in what order tasks and sublists will appear in the list, we can simply display all the tasks, and when we encounter a sublist we can indent it to visually separate it from the rest, and recurse into it performing the same operation again of displaying all its tasks and recursing on sublists without needing to anticipate the structure.
- Finally, we need to be able to perform actions on sublists that propagate throughout all of their children. Actions like marking a sublist as complete or incomplete should cause all of the contained tasks and sublists to also be marked as complete or incomplete. Or, when displaying a sublist we need to be able to determine whether a sublist should be marked as complete (only if all its tasks and sublists are also marked complete). Without a composite we would encounter issues determining which objects need to perform these actions without knowing the structure, which would again require lots of code and be prone to errors. Using a composite allows us to easily implement these cascading actions by having lists perform the actions on their children and returning the results so that we can call any method on the top-level list to receive results for the entire structure regardless of its depth.

#### Decorator Pattern
This pattern is used to implement different functionality for different types of tasks in a list. Originally, we had wanted to make several types of tasks (such as recurring & appointment) and have them both extend a parent class (basic task). However, when developing this we realized there was no good reason why a task could not be both recurring and an appointment. So with this, we had new requirements for our task structure: we needed to be able to easily add new functionality for different types in any order and combination, but still be able to store it in a list. So, we used a decorator pattern.

Using the decorator pattern, we are extending the composite pattern by having our “leaves” be an interface implemented by both the task and the base decorator. This way, we can use polymorphism to not only treat decorators as tasks, but to treat them as leaves in our composite structure as well. This makes the decorator very natural to implement alongside the existing structure so long as decorators and tasks follow the same interface required by the composite pattern.

As well, the decorator pattern allows us to add new functionality to the basic tasks. When we add new functionality to our basic task, the task should still be able to perform its own functionality, but the decorator should do something in addition:
- The appointment decorator has new instance variables to store a time and place, and when the method to display itself is called it should display whatever task or decorator it wraps followed by the time and place. 
- The recurring decorator has new instance variables for types and numbers of recurrences, and it must also display these new variables in the same way as the appointment decorator. The recurring decorator also has new functionality for when it is marked as completed, as it must not only change the completion for whatever it wraps, but in addition should copy itself and add itself to a new list in the future. 
- And of course, a task marked as both an appointment and recurring should be able to perform all the functionality listed above, preferrably with no extra code required.

Using a decorator allows us to implement all the above functionality in any combination while only have to implement them in code once. As well, because we have a basic class we want to be able to dynamically add new functionality to while still treating it as an item in our composite structure, the decorator pattern is a natural choice.

#### Prototype Pattern
This pattern is used to implement the recurring functionality, where a recurring task must create itself again in a future list when completed. Initially, when trying to implement this we had the recurring decorator add itself (and whatever it wrapped) into the new list. But, the issue here is that if the task that is wrapped is only reused instead of copied, when the task is displayed in the future list it would share whatever completion status the task has in the original list, since it is the same object after all. But, we wanted to be able to have the task in the original list remain checked, while the task in the future list would start unchecked. As well, the number of recurrences displayed should decrease each time in the new list only, not the original.

This leads to the idea of being able to copy the recurring decorator, but a new problem arises! The recurring decorator can wrap any number of other decorators and a task, and as mentioned we cannot reuse the task since it must have a different completion status. So we need to be able to copy not only the recurring decorator, but every decorator as well as tasks. The solution we found is then to define a clone() function in our Item interface, which tasks and decorators implement. The Item interface then acts as the prototype interface, and we can be assured that any item which implements it is able to be cloned and returned as an Item type. 

So after implementing the clone() function in each class we want to clone, the recurring decorator can now fully clone itself and have it wrap a recursively-copied clone of whatever the original wraps, regardless of if it is a decorator, task, or any combination of them. As a result, we can easily clone the task and its decorators in their entirety without having to anticipate their structure, so we can update the completion status and number of recurrences without affecting the original with minimal code required.

## Extensibility

## Contributions
