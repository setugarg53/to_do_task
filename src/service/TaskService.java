package service;

import java.sql.SQLException;
import java.util.List;

import bean.TaskBean;
import dao.TaskDAO;
import dao.TaskInterface;
import exception.DueDateException;
import exception.TaskNotFound;

public class TaskService 

{
public String addTask(TaskBean task) {
	if(task.getTaskCreationDate().compareTo(task.getTaskDueDate())<=0)
	{
		TaskInterface addTask = new TaskDAO();
		try {
			if(addTask.addTask(task))
			{
				return "Task Successfully Created";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}else
	{
		try {
			throw new DueDateException("Enter Due date greater than creation date");
		} catch (DueDateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	return "Task Not Created";
}

public List<TaskBean> getAllTask()
{
	TaskInterface getAllTask = new TaskDAO();
	List<TaskBean> allTasks=null;
	try {
		try {
			allTasks = getAllTask.getAllTasks();
		} catch (TaskNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return allTasks;
	
}

public TaskBean getTaskById(int taskID)
{
	TaskInterface getAllTask = new TaskDAO();
	TaskBean task=null;
	try {
		task = getAllTask.getTaskById(taskID);
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (TaskNotFound e) {
			e.printStackTrace();
		}
	
	
	return task;
	
	}

	public String deleteTask(int taskId)
	{
		String result="Task is not Deleted";
		TaskInterface dao = new TaskDAO();
		try {
			if(dao.deleteTask(taskId))
			{
			result="Task is successfully deleted";	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public String editTask(TaskBean task)
	{
		String result = "Task Not Edited";
		TaskInterface editTask = new TaskDAO();
		if(editTask.editTask(task))
		{
			result = "Task Sucessfully edited";
		}	
		return result;
		
	}
	
	public List<TaskBean> getTaskByPriority(String priority)
	{
		TaskInterface getTaskByPriority = new TaskDAO();
		List<TaskBean> allTasks=null;
		try {
			allTasks = getTaskByPriority.getTaskByPriority(priority);
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TaskNotFound e) {
				e.printStackTrace();
			}
		return allTasks;
	}
	public List<TaskBean> getTaskByCompletionDate()
	{
		TaskInterface getTaskByPriority = new TaskDAO();
		List<TaskBean> allTasks=null;
		try {
			allTasks = getTaskByPriority.sortTaskToCompletionDate();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TaskNotFound e) {
				e.printStackTrace();
			}
		return allTasks;
	}

}
