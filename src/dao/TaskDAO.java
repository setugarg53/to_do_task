package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBUtility.ConnectionManager;
import bean.TaskBean;
import exception.DueDateException;
import exception.TaskNotFound;

public class TaskDAO implements TaskInterface
{
	private ResultSet rs=null;
	private Connection con=null;
	private PreparedStatement ps = null;
	

	@Override
	public boolean addTask(TaskBean task) throws SQLException {
		con=ConnectionManager.getConnection();
		if(con!=null)
		{
			/*
			 * task_is is auto incremented
			 * and is_Completed is considered as false by default
			 */
			PreparedStatement stmt=con.prepareStatement("insert into to_do_list values(default,?,?,?,?,?,false)",Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1,task.getTaskTitle());
			stmt.setString(2,task.getTaskDesc());
			stmt.setString(3,task.getPriority().toLowerCase());
			stmt.setDate(4,task.getTaskDueDate());
			stmt.setDate(5,task.getTaskCreationDate());
			int i=stmt.executeUpdate();  
			  if(i>0)
			  {
				  
				  con.close();
				  return true;
			  }
			  ResultSet tableKeys = stmt.getGeneratedKeys();
			  tableKeys.next();
			con.close();  
		}
		return false;
	}

	@Override
	public boolean deleteTask(int taskID) throws SQLException{
		boolean result=false;
		
		try {
			//to check if the given task is present or not
			TaskBean task = this.getTaskById(taskID);
			con=ConnectionManager.getConnection();
			if(task!=null)
			{
				if(con!=null)
				{
					ps=con.prepareStatement("delete from to_do_list where task_id="+taskID);

		            int i=ps.executeUpdate();
		            if(i>0)
		            {
		            	result = true;
		            }
		        	
		    	}
			}
		} catch (TaskNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.close();
		ps.close();
		return result;
	}

	@Override
	public boolean editTask(TaskBean task) {
		boolean result = false;
		try {
			//to check if the given task is present or not
			TaskBean taskTemp = this.getTaskById(task.getTaskID());
			con=ConnectionManager.getConnection();
			if(task!=null)
			{
				if(con!=null)
				{
					ps=con.prepareStatement("update to_do_list SET task_title = ? ,task_description = ?,task_priority = ?,task_due_date = ?,task_creation_date = ?,task_isCompleted = ? where task_id = ?");
					ps.setString(1,task.getTaskTitle());
					ps.setString(2,task.getTaskDesc());
					ps.setString(3,task.getPriority().toLowerCase());
					ps.setDate(4,task.getTaskDueDate());
					ps.setDate(5,task.getTaskCreationDate());
					ps.setBoolean(6,task.isCompleted());
					ps.setInt(7,task.getTaskID());	
		            int i=ps.executeUpdate();
		            if(i>0)
		            {
		            	result = true;
		            }
		        	
		    	}
			}
			con.close();
			ps.close();
		} catch (TaskNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		}

	@Override
	public List<TaskBean> getTaskByPriority(String priority) throws SQLException,TaskNotFound
	{
		List<TaskBean> listTask = new ArrayList<TaskBean>();
		con=ConnectionManager.getConnection();
		String pr= priority;
		pr.toLowerCase();
		TaskBean task;
		if(con!=null)
		{
			ps=con.prepareStatement("select * from to_do_list where task_priority = ?");
			ps.setString(1,pr);
            rs=ps.executeQuery();
            if(rs.next())
            {
            do
            {
                task=new TaskBean();
                task.setTaskID(rs.getInt(1));
                task.setTaskTitle(rs.getString(2));
                task.setTaskDesc(rs.getString(3));
                task.setPriority(rs.getString(4));
                task.setTaskDueDate(rs.getDate(5));
                task.setTaskCreationDate(rs.getDate(6));
                task.setCompleted(rs.getBoolean(7));
                listTask.add(task);
            }
            while(rs.next());
            
		}
            else
            {
            	throw new TaskNotFound("No Task Present");
            }
		}
		con.close();
		ps.close();
		rs.close();
		return listTask;
		
	}

	@Override
	public TaskBean getTaskById(int taskID) throws SQLException, TaskNotFound {
		TaskBean task = new TaskBean();
		con=ConnectionManager.getConnection();
		if(con!=null)
		{
			//taskId given is not present
			ps=con.prepareStatement("select * from to_do_list where task_id="+taskID);

            rs=ps.executeQuery();
            
            if(rs.next())
            {
            	
            	task.setTaskID(rs.getInt(1));
                task.setTaskTitle(rs.getString(2));
                task.setTaskDesc(rs.getString(3));
                task.setPriority(rs.getString(4));
                task.setTaskDueDate(rs.getDate(5));
                task.setTaskCreationDate(rs.getDate(6));
                task.setCompleted(rs.getBoolean(7));               
		}
            else
            {
            	throw new TaskNotFound("Task with given id is not present");
            }
        	
    	}
		con.close();
		ps.close();
		rs.close();
		return task;
		}

	@Override
	public List<TaskBean> sortTaskToCompletionDate() throws SQLException,TaskNotFound {
		List<TaskBean> listTask = new ArrayList<TaskBean>();
		con=ConnectionManager.getConnection();
		TaskBean task;
		if(con!=null)
		{
			ps=con.prepareStatement("select * from to_do_list ORDER BY task_due_date");
			rs=ps.executeQuery();
            if(rs.next())
            {
            do
            {
                task=new TaskBean();
                task.setTaskID(rs.getInt(1));
                task.setTaskTitle(rs.getString(2));
                task.setTaskDesc(rs.getString(3));
                task.setPriority(rs.getString(4));
                task.setTaskDueDate(rs.getDate(5));
                task.setTaskCreationDate(rs.getDate(6));
                task.setCompleted(rs.getBoolean(7));
                listTask.add(task);
            }
            while(rs.next());
            
		}
            else
            {
            	throw new TaskNotFound("No Task Present");
            }
		}
		con.close();
		ps.close();
		rs.close();
		return listTask;
	}

	@Override
	public List<TaskBean> getAllTasks() throws SQLException, TaskNotFound {
		List<TaskBean> listTask = new ArrayList<TaskBean>();
		con=ConnectionManager.getConnection();
		TaskBean task;
		if(con!=null)
		{
			ps=con.prepareStatement("select * from to_do_list");

            rs=ps.executeQuery();
            if(rs.next())
            {
            do
            {
                task=new TaskBean();
                task.setTaskID(rs.getInt(1));
                task.setTaskTitle(rs.getString(2));
                task.setTaskDesc(rs.getString(3));
                task.setPriority(rs.getString(4));
                task.setTaskDueDate(rs.getDate(5));
                task.setTaskCreationDate(rs.getDate(6));
                task.setCompleted(rs.getBoolean(7));
                listTask.add(task);
            }
            while(rs.next());
            
		}
            else
            {
            	throw new TaskNotFound("No Task Present");
            }
		}
		con.close();
		ps.close();
		rs.close();
		return listTask;
	}

}
