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
			PreparedStatement stmt=con.prepareStatement("insert into to_do_list values(default,?,?,?,?,?,false)",Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1,task.getTaskTitle());
			stmt.setString(2,task.getTaskDesc());
			stmt.setString(3,task.getPriority());
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
			//to check if the iven task is present or not
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TaskBean> getTaskByPriority(String priority) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskBean> getTaskByCompletionDate(Date compDt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TaskBean getTaskById(int taskID) throws SQLException, TaskNotFound {
		TaskBean task = new TaskBean();
		con=ConnectionManager.getConnection();
		if(con!=null)
		{
			
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
	public List<TaskBean> taskStatus(Boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskBean> sortTaskToPriority() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskBean> sortTaskToCompletionDate() {
		// TODO Auto-generated method stub
		return null;
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
