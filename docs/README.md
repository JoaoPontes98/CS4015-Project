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

## Extensibility

## Contributions
