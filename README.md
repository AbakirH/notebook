# notebook

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
Notebook is a simple app that allows instructor to aClass grades to his/her students. Students will be able to see their average grades for the class and for each assignment.
### Milestone 4 Progess
![](https://i.imgur.com/rgMfDzu.gif)
### Milestone 3 Progess
![](https://i.imgur.com/YDH0tWY.gif)
### Milestone 2 Progess
![](https://i.imgur.com/vKj1iy3.gif)
### Milestone 1 Progess
![](https://i.imgur.com/QxwH8Xe.gif)
### App Evaluation

- **Category:** Education
- **Mobile:**
  -   There are not that many apps that teachers and students can use to track their grades and assignments they need to complete. By creating the notebook app, we give students the ability to know when any assignment their teacher posted is up and a convenient way to check their grades and be notified when it changes.
- **Story:** 
  -  As a student, I have used many apps to check my grades for classes and either it was way to complicated to do so 
- **Market:** School Classroom
  -  There will always be students who need to learn and want to have a convenient way to access their schoolwork and know when their grades are changed. Our market is the school classroom where teachers can update grades for their students, and students can see those grades. 
- **Habit:**
  -  This app will be used when school is in session, and students will be checking this app daily to see their grades and what assignments are posted at the moment. The average user in this app, for the time being, will only be able to view their grades for each assignment the teach aClasses. In comparison, the teacher can create assignments and give grades back to students.
- **Scope:**
  -  The scope of this app is well-formed as it is simple to make and can be built upon. If we finish on time, then we will be able to implement our optional stories.


## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**

- [x] As a student/instructor, an user can sign in and log in to the app.
- [x] Student and Teacher see different Screens 
- [x] App knows if the user is logged in or out even if the app is closed.
- [x] Teacher can create class for students to join
- [x] Teacher can view the classes they are teaching in a nice format 
- [x] As a student, the user can check the classes they are in and join the class they are in.
- [x] (Work in Progress) As a student, the user can check the grades.
- [x] As an instructor, the user can post the students' grade. 
- [x] As an instructor, the user can see more information about any class they are teaching

**Optional Nice-to-have Stories**
- [x] Student profile screen looks nice
- [x] Teacher profile screen looks nice
* A student can check the GPA, school year, name in the profile.
* An instructor can see the list of students, emails, and classes. 

### 2. Screen Archetypes

* Login
   * An user can login to app with user id and password.
   * An user can choose the role: student and teacher. 
* Stream
   * Student can see the individual and average grades for a class.
   * Student can see the list of classes.  
   * An instructor can see the list of students, emails, and classes. 
   * An instructor can update the grade as needed.
* Profile
   * Student can check school year, GPA, and name.

### 3. Navigation

**Teacher’s Tab Navigation** (Tab to Screen)
* Profile
* Classes
* Input Grades

**Student’s Tab Navigation** (Tab to Screen)
* Profile
* Classes
* What-If

**Flow Navigation** (Screen to Screen)

* Login
* Register - The user can sign up or login in to their account
* Creation
   * Teacher can create an assignment that she wants to give students a grade for
* Stream - Users can scroll through important recourses in a list
   * Students can scroll through the different classes they are assigned to and check their grades for that class

## Wireframes
<img src="https://github.com/AbakirH/notebook/blob/main/IMG_0023.jpg?raw=true" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Schema 
### Models
#### Post

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | user      | String   | name user is used to login |
   | teacher        | Bool | is this account a teacher or not |
   | password       | String   | password for user to use to login |
   | commentsCount | Number   | number of comments that has been posted to an image |
   | createdAt     | DateTime | date when aClass is created (default field) |
   | updatedAt     | DateTime | date when aClass is last updated (default field) |
   
### Networking
- - Home Feed Teacher Screen
      - (Read/GET) Query all classes where the teacher teaches 
         ```swift
         let query = PFQuery(className:"Class")
         query.whereKey("teacher", equalTo: currentUser)
         query.order(byDescending: "createdAt")
         query.findObjectsInBackground { (aClasses: [PFObject]?, error: Error?) in
            if let error = error { 
               print(error.localizedDescription)
            } else if let aClasses = aClasses {
               print("Successfully retrieved \(aClasses.count) aClasses.")
           // TODO: Do something with aClasses...
            }
         }
         ```
      - (Create/POST) Create a new class they are teaching
      - (Delete) Delete existing class
      - (Create/POST) Create a new grade for a student in a class
      - (Change) Change existing grade for a student
   - Create Grade Screen
      - (Create/POST) Create a new grade object
   - Profile Screen
      - (Read/GET) Query logged in user object
      - (Update/PUT) Update user profile image
   - Home Feed Student Screen
      - (Read/GET) Query all classes the student is talking 
         ```swift
         let query = PFQuery(className:"Class")
         query.whereKey("student", equalTo: currentUser)
         query.order(byDescending: "createdAt")
         query.findObjectsInBackground { (aClasses: [PFObject]?, error: Error?) in
            if let error = error { 
               print(error.localizedDescription)
            } else if let aClasses = aClasses {
               print("Successfully retrieved \(aClasses.count) aClasses.")
           // TODO: Do something with aClasses...
            }
         }
         ```
      - (Read/GET) Query all grades the student has 
- [OPTIONAL: List endpoints if using existing API such as Yelp]
