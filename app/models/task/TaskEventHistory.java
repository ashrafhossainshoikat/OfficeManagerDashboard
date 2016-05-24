package models.task;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

import models.customer.CustomerCriteria;
import models.customer.CustomerHistory;
import models.customer.CustomerServiceTask;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Result;
import services.AppConstants;
import services.JavaUtils;
import services.ScalaUtils;

@Entity
public class TaskEventHistory extends Model {

	@Id
	public Integer id;

	@Version
	@Column(name = "edit_count", nullable = false)
	// public int version = 0;
	public int version = 1;

	@ManyToOne
	@JoinColumn(name = "task_id", referencedColumnName = "id")
	// @JoinColumn(name = "customer_service_task_id", referencedColumnName =
	// "id")
	public CustomerServiceTask customerServiceTask;

	// @ManyToOne
	// @JoinColumn(name = "task_event_type_id", referencedColumnName = "id")
	// public TaskEventType taskEventType;
	public String taskEventType;

	public Date eventStartTime;

	public Date eventEndTime;

	public String remarks;

	public static Finder<Integer, TaskEventHistory> find = new Finder(
			Integer.class, TaskEventHistory.class);

	/* Save User Data To Create Account */

	public static void create(TaskEventHistory taskEventHistory) {
		taskEventHistory.save();
	}

	/* Update Task Event History Data from Account */
	public static void update(TaskEventHistory taskEventHistory) {
		taskEventHistory.update();
	}

	/*
	 * public static List<TaskEventHistory> all() { // return find.all(); return
	 * find.all(); }
	 */

	public static TaskEventHistory getStartedTask(int taskId) {
		return find.where().eq("customerServiceTask.id", taskId)
				.isNotNull("eventStartTime").isNull("eventEndTime")
				.eq("taskEventType", "Start").findUnique();
	}

	public static TaskEventHistory getPausedTask(int taskId) {
		return find.where().eq("customerServiceTask.id", taskId)
				.isNotNull("eventStartTime").isNull("eventEndTime")
				.eq("taskEventType", "Pause").findUnique();
	}

	public static TaskEventHistory getResumedTask(int taskId) {
		return find.where().eq("customerServiceTask.id", taskId)
				.isNotNull("eventStartTime").isNull("eventEndTime")
				.eq("taskEventType", "Resume").findUnique();
	}

	public ObjectNode toJson() {
		ObjectNode tehJson = Json.newObject();

		tehJson.put("id", id);
		tehJson.put("version", version);
		tehJson.put("eventStartTime", ((eventStartTime == null) ? ""
				: ScalaUtils.formatTime(eventStartTime)));
		tehJson.put(
				"eventEndTime",
				((eventEndTime == null) ? "" : ScalaUtils
						.formatTime(eventEndTime)));
		tehJson.put("remarks", ((remarks == null) ? "" : remarks));
		tehJson.put("customerHistoryId",
				((customerServiceTask.customerHistory.id == null) ? ""
						: customerServiceTask.customerHistory.id.toString()));

		return tehJson;

	}

	
	public static ArrayNode allToJson() {
		JsonNodeFactory jnf = JsonNodeFactory.instance;
		ArrayNode arrayNode = new ArrayNode(jnf);

		for (TaskEventHistory t : find.all()) {
			ObjectNode o = t.toJson();
			arrayNode.add(o);
		}

		return arrayNode;
	}
	
	
	public static String getCancellationReasonByCST(CustomerServiceTask cst) {
		if(cst == null)
			return "";
		
		TaskEventHistory teh = find.where()
								.eq("customerServiceTask.id", cst.id)
								.eq("taskEventType", AppConstants.TASK_EVENT_TYPE_CANCEL)
								.orderBy("eventEndTime desc")
								.setMaxRows(0)
								.findUnique();
		
		return (teh==null || teh.remarks==null) ? "" : teh.remarks;
	}

}
