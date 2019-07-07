package controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import bean.TaskBean;
import service.TaskService;

@Path("/task")
public class TaskController {
	
	/*
	 * http://localhost:8080/assignmnet/rest/task/add
	 * Pass all the parameter in json or xml except TaskID as it is auto incremented
	 */
	@POST
	@Path("/add")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		public String addTask(TaskBean a1) {
		TaskService taskSrc = new TaskService();
		return taskSrc.addTask(a1);
	}
	/*
	 * http://localhost:8080/assignmnet/rest/task/edit
	 * Pass all the parameters as in bean class in json or xml
	 */
	@POST
	@Path("/edit")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String editTask(TaskBean a1) {
		TaskService taskSrc = new TaskService();
		return taskSrc.editTask(a1);
	}
	
	/*
	 * http://localhost:8080/assignmnet/rest/task/view
	 * Gives all the task as preset in database in in json format
	 */
	@GET
	@Path("/view")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TaskBean> allTask()
	{
		TaskService getAllTask = new TaskService();
		List<TaskBean> allTasks = getAllTask.getAllTask();
		return allTasks;
	}
	/*
	 * http://localhost:8080/assignmnet/rest/task/view/priority/{priority}
	 * it will give all the task as per priority given in url and in task while creation of task
	 */
	@GET
	@Path("/view/priority/{priority}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TaskBean> taskByPriority(@PathParam("priority") String priority)
	{
		TaskService getAllTask = new TaskService();
		List<TaskBean> allTasks = getAllTask.getTaskByPriority(priority);
		//System.out.println(allTasks.get(0).getTaskCreationDate());
		return allTasks;
	}
	/*
	 * http://localhost:8080/assignmnet/rest/task/view/{taskid}
	 * to view a particular task as per task id
	 * 
	 */
	@GET
	@Path("/view/{taskId}")
	@Produces(MediaType.APPLICATION_JSON)
	public TaskBean taskById(@PathParam("taskId") int taskId)
	{
		TaskService getAllTask = new TaskService();
		TaskBean task = getAllTask.getTaskById(taskId);
		return task;
	}
	/*
	 * http://localhost:8080/assignmnet/rest/task/view/cdate
	 * to view all the tasks sorted by completion date
	 */
	@GET
	@Path("/view/cdate")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TaskBean> sortByCompletionDate()
	{
		TaskService getAllTask = new TaskService();
		List<TaskBean> allTasks = getAllTask.getTaskByCompletionDate();
		return allTasks;
	}
	/*
	 * http://localhost:8080/assignmnet/rest/task/delete/503
	 * to delete a particular task as per task id
	 */
	@GET
	@Path("/delete/{deleteTaskId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteTaskById(@PathParam("deleteTaskId") int deletTaskId)
	{
		TaskService getAllTask = new TaskService();
		String result = getAllTask.deleteTask(deletTaskId);
		return result;
	}
}
