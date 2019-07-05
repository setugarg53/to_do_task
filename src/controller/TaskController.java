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

	@POST
	@Path("/add")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public String addTask(TaskBean a1) {
		TaskService taskSrc = new TaskService();
		return taskSrc.addTask(a1);
	}
	
	@GET
	@Path("/view")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TaskBean> allTask()
	{
		TaskService getAllTask = new TaskService();
		List<TaskBean> allTasks = getAllTask.getAllTask();
		//System.out.println(allTasks.get(0).getTaskCreationDate());
		return allTasks;
	}
	
	@GET
	@Path("/view/{taskId}")
	@Produces(MediaType.APPLICATION_JSON)
	public TaskBean taskById(@PathParam("taskId") int taskId)
	{
		TaskService getAllTask = new TaskService();
		TaskBean task = getAllTask.getTaskById(taskId);
		return task;
	}
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
