# to_do_task
Description:
	It is a back end application for managing all the personal tasks of an individual which user can add,edit,modify 	and view in various definitions.
	It contains most possible backed validations .
	Can be used with any front end framework and for any environment can be used by calling different APIs for particular methods.
	
Techology Used:
	TomCat 8.5
	Java 1.8
	Mysql Server
	Restlful Services 
	Maven
	
Validations:
	Task Due Date should be greater than creation date (throw exception)
	Checking if task is present or not before editing,deleting and viewing particular task with taskID (throw Exception)

Process To Run:
	Download the project and Import it into Eclipse(preferably)
	Do Maven Build or Maven Install
	Configure server for execution of Project
	Configure your database and create table using Sql.sql and edit the DBUtility according to database server
	The queries are used according to MySql so preferably use MySql
	Install Postman in your system

Urls to hit:
	To add the task http://localhost:8080/assignmnet/rest/task/add (pass the parameters in JSON or XMl format)
	To view all the tasks http://localhost:8080/assignmnet/rest/task/view (return tasks in JSOn format)
	To view a particular task http://localhost:8080/assignmnet/rest/task/view/{taskID}
	To delete a particular task with particular task id http://localhost:8080/assignmnet/rest/task/delete/{taskID}
	To edit a particular task pass perimeters in json http://localhost:8080/assignmnet/rest/task/edit
	To sort all the task as per due date http://localhost:8080/assignmnet/rest/task/view/cdate
	To get the tasks by priority http://localhost:8080/assignmnet/rest/task/view/priority/{high/medium/low}
	
Front End Validation That can be Put:
	To check all the parameters passed are not null