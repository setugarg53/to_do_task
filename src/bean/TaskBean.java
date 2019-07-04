package bean;

import java.sql.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TaskBean
{
	public TaskBean()
	{}
	private String taskTitle,taskDesc,priority;
	private Date taskCreationDate,taskDueDate;
	private boolean isCompleted;
private int taskID;
public int getTaskID() {
	return taskID;
}
public void setTaskID(int taskID) {
	this.taskID = taskID;
}
public String getTaskTitle() {
	return taskTitle;
}
public void setTaskTitle(String taskTitle) {
	this.taskTitle = taskTitle;
}
public String getTaskDesc() {
	return taskDesc;
}
public void setTaskDesc(String taskDesc) {
	this.taskDesc = taskDesc;
}
public String getPriority() {
	return priority;
}
public void setPriority(String priority) {
	this.priority = priority;
}
public Date getTaskDueDate() {
	return taskDueDate;
}
public void setTaskDueDate(Date taskDueDate) {
	this.taskDueDate = taskDueDate;
}
public boolean isCompleted() {
	return isCompleted;
}
public void setCompleted(boolean isCompleted) {
	this.isCompleted = isCompleted;
}

public Date getTaskCreationDate() {
	return taskCreationDate;
}
@Override
public String toString() {
	return "TaskBean [taskTitle=" + taskTitle + ", taskDesc=" + taskDesc + ", priority=" + priority
			+ ", taskCreationDate=" + taskCreationDate + ", taskDueDate=" + taskDueDate + ", isCompleted=" + isCompleted
			+ ", taskID=" + taskID + "]";
}
public void setTaskCreationDate(Date taskCreationDate) {
	this.taskCreationDate = taskCreationDate;
}

}
