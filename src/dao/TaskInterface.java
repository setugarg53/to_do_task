package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import bean.TaskBean;
import exception.DueDateException;
import exception.TaskNotFound;

public interface TaskInterface 
{
public boolean addTask(TaskBean task) throws SQLException;
public List<TaskBean> getAllTasks() throws SQLException,TaskNotFound;
public boolean deleteTask(int taskID) throws SQLException;
public boolean editTask(TaskBean task);
public List<TaskBean> getTaskByPriority(String priority) throws SQLException, TaskNotFound;
public TaskBean getTaskById(int taskID) throws SQLException,TaskNotFound;
public List<TaskBean> sortTaskToCompletionDate() throws SQLException, TaskNotFound;
}
