package models.customer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;

import play.db.ebean.Model;
import play.libs.Json;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Query;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;

import services.AppConstants;
import services.JavaUtils;
import services.ScalaUtils;
import models.ebeanhelper.time.Interval;
import models.geo.BranchInRegion;
import models.task.TaskEventHistory;
import models.task.TaskType;
import connectplus.models.User;
import connectplus.models.geo.Branch;
import dummymodels.reports.BreakReport;
import dummymodels.reports.LiveInterfaceView;
import dummymodels.reports.ServiceDetail;
import dummymodels.reports.ServiceWiseReport;
import dummymodels.reports.StartAndEndDate;


@Entity
public class CustomerServiceTask extends Model {

	@Id
	public Integer id;

	@Version
	@Column(name = "edit_count", nullable = false)
	public int version = 1;

	public Date taskIssueTime; // ** Punch In time or the time when the task was assigned
	
	public Date taskStartTime;

	public Date taskEndTime;

	// totalTaskTime= (taskEndTime - taskStartTime)
	//@Temporal(TemporalType.TIME)
	//public Date totalTaskTime;
	public Interval totalTaskTime;
	
	public Interval waitingTime;

	public String taskStatus;

	@ManyToOne
	@JoinColumn(name = "customer_history_id", referencedColumnName = "id")
	public CustomerHistory customerHistory;

	@ManyToOne
	@JoinColumn(name = "task_type_id", referencedColumnName = "id")
	public TaskType taskType;

	public String username;

	/*@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	public User user;*/
	public Integer userId;

	@OneToMany(mappedBy = "customerServiceTask")
	public List<TaskEventHistory> taskEventHistories;
	// ---------------------------------------------
	
	private static Finder<Integer, CustomerServiceTask> find = new Finder(Integer.class, CustomerServiceTask.class);

	public static CustomerServiceTask get(int id) {
		return find.byId(id);
	}
	
	@Transient
	public String cardNumber() {
		if(this.customerHistory == null)
			return "";
		
		CustomerHistory ch = CustomerHistory.get(this.customerHistory.id);
		String cn = ch.cardNumber;
		return (cn==null) ? "" : cn;
	}
	
	@Transient
	public User user() {
		if(this.userId!=null)
			return User.get(this.userId);
		else if(this.username!=null && !this.username.trim().isEmpty())
			return User.findByUsername(this.username);
		else
			return null;
	}
	
	public static void create(CustomerServiceTask customerServiceTask) {
		customerServiceTask.save();
	}

	public static void update(CustomerServiceTask customerServiceTask) {
		customerServiceTask.update();
	}

	
	/*
	public static void update(CustomerServiceTask customerServiceTask) {
		customerServiceTask.update(); 
	}
	 
	public static List<CustomerServiceTask> all() { 
		return find.all();
	}
	 
	public static CustomerServiceTask get(int id) { 
		return find.where().eq("id", id).findUnique();
	}
	*/

	public static CustomerServiceTask getPausedTask(int id) {
		return find.where()
				.eq("id", id)
				.eq("taskStatus", AppConstants.TASK_STATUS_PAUSED)
				.isNotNull("taskStartTime")
				.isNull("taskEndTime")
				.findUnique();
	}

	public static CustomerServiceTask getAssignedTask(String userName, int customerHistoryId) {
		return find.where()
				.eq("username", userName)
				.eq("customerHistory.id", customerHistoryId)
				.isNull("taskEndTime")
				.findUnique();
	}

	public static CustomerServiceTask getNotStartedTask(int taskId) {
		return find.where()
				.eq("id", taskId)
				.isNull("taskStartTime")
				.findUnique();
	}

	public static CustomerServiceTask getNotEndedTask(int taskId) {
		return find.where()
				.eq("id", taskId)
				.isNotNull("taskStartTime")
				.isNull("taskEndTime")
				.findUnique();
	}
	
	public static List<CustomerServiceTask> getCancelledTaskList() {
		return find.where()
				.eq("taskStatus", AppConstants.TASK_STATUS_CANCELLED)
				.findList();
	}

	public static List<CustomerServiceTask> singleCustomerTaskList(int customerHistoryId) {
		return find.where()
				.eq("customerHistory.id", customerHistoryId)
				.isNull("taskEndTime")
				.orderBy("taskStartTime").
				findList();
	}
	
	
	public static int getCountOfActiveTasksByCustomerHistory(int customerHistoryId) {
		return find.where()
				.eq("customerHistory.id", customerHistoryId)
				.isNull("taskEndTime")
				.eq("username", JavaUtils.getCurrentUsername())
				.findRowCount();
	}
	
	
	public static List<CustomerServiceTask> getNotEndedTaskForPunchOut(int customerHistoryId) {
		return find.where()
				.eq("customerHistory.id", customerHistoryId)
				.isNull("taskEndTime")
				.findList();
	}

	
	public static ObjectNode getRecentTask() {
		List<CustomerServiceTask> allElementList = CustomerServiceTask.find.all();
		CustomerServiceTask lastElement = allElementList.get(allElementList.size() - 1);
		ObjectNode objNode = lastElement.toJson();
		return objNode;
	}

	@Transient
	public ObjectNode toJson() {
		ObjectNode cstJson = Json.newObject();

		cstJson.put("id", id);
		cstJson.put("version", version);
		cstJson.put("taskStartTime", ((taskStartTime == null) ? "" : ScalaUtils.formatTime(taskStartTime)));
		cstJson.put("taskEndTime", ((taskEndTime == null) ? "" : ScalaUtils.formatTime(taskEndTime)));
		cstJson.put("totalTaskTime", ((totalTaskTime == null) ? "" : totalTaskTime.toString()));
		cstJson.put("customerHistoryId", ((customerHistory == null) ? "" : customerHistory.id.toString()));
		cstJson.put("taskTypeId", ((taskType == null) ? "" : taskType.id.toString()));
		cstJson.put("taskTypeName", ((taskType == null) ? "" : taskType.taskName));
		cstJson.put("username", ((username == null) ? "" : username));
		cstJson.put("taskStatus", ((taskStatus == null) ? "" : taskStatus));
		cstJson.put("cardNumber", ((customerHistory == null) ? "" : customerHistory.cardNumber));

		return cstJson;
	}

	public static ArrayNode findAllByIdAsJson(int chId) {
		JsonNodeFactory jnf = JsonNodeFactory.instance;
		ArrayNode arrayNode = new ArrayNode(jnf);

		// for (CustomerServiceTask c : find.all()) {
		for (CustomerServiceTask c : singleCustomerTaskList(chId)) {
			ObjectNode o = c.toJson();
			arrayNode.add(o);
		}

		return arrayNode;
	}
	
	// create arraynode to punchout
	public static ArrayNode findAllNotEndedTaskForPunchOutAsJson(int chId) {
		JsonNodeFactory jnf = JsonNodeFactory.instance;
		ArrayNode arrayNode = new ArrayNode(jnf);

		// for (CustomerServiceTask c : find.all()) {
		for (CustomerServiceTask c : getNotEndedTaskForPunchOut(chId)) {
			ObjectNode o = c.toJson();
			arrayNode.add(o);
		}

		return arrayNode;
	}
	
	
	
	// -----------------------------------------------------------------
	
	// ** methods for report queries
	/*
	public static String[][] repServiceDetail(Date startDate, Date endDate, String username, 
			Boolean isBssoTask, Integer taskTypeId){
		
		User user = User.findByUsername(JavaUtils.getCurrentUsername());
		
		ExpressionList<CustomerServiceTask> el = find.where();
		
		if(user.isBssoOrCcsoUser())
			el = el.ge("username", user.username);
		else if(username!=null & !username.trim().isEmpty())
			el = el.ge("username", username);
		else
			el = el.in("username", User.findBssoOrCcsoUsersOfSameBranch(user));
		
		if (startDate != null)
			el = el.le("taskStartTime", startDate);
		if (endDate != null)
			el = el.ge("taskStartTime", startDate);
		if (isBssoTask!=null)
			el = el.ge("taskType.isBssoTask", isBssoTask);
		if (taskTypeId!=null)
			el = el.ge("taskType.id", taskTypeId);
				
		List <CustomerServiceTask> cstList = 
				el.orderBy("startTstamp desc").fetch("taskType, customerHistory").findList();
		
		ArrayList<String[]> rowList = new ArrayList<String[]>();
		for(CustomerServiceTask cst : cstList){
			String[] cols = new String[9];
			cols[0] = cst.taskType.taskName;
			cols[1] = JavaUtils.formatDate(cst.taskIssueTime);
			cols[2] = cst.customerHistory.cardNumber;
			cols[3] = cst.taskType.isBssoTask ? "BSSO" : "CCSO";
			cols[4] = JavaUtils.formatTime(cst.taskIssueTime);
			cols[5] = JavaUtils.formatTime(cst.taskStartTime);
			cols[6] = JavaUtils.formatTime(new Date(cst.taskIssueTime.getTime() - cst.taskStartTime.getTime()));
			cols[7] = JavaUtils.formatTime(cst.taskEndTime);;
			cols[8] = JavaUtils.formatTime(new Date(cst.taskEndTime.getTime() - cst.taskStartTime.getTime()));;
			
			rowList.add(cols);
		}
		
		String[][] rows = new String[9][rowList.size()];
		return rowList.toArray(rows);
	}
	*/
	public static String[][] reportServiceDetail(ServiceDetail filter) {
		final int colLen = 9;
		User user = User.findByUsername(JavaUtils.getCurrentUsername());
		if(filter==null || user==null)
			return new String[0][colLen];

		ExpressionList<CustomerServiceTask> el = find.fetch("taskType").fetch("customerHistory").where();
		
		List<Integer> roles = user.getRoleIdList();
		/*if(roles.contains(AppConstants.ROLE_ID_BSSO) || roles.contains(AppConstants.ROLE_ID_CCSO)) {
			el = el.eq("username", user.username);
			
		} else {
			if(filter.username!=null & !filter.username.trim().isEmpty()) {
				el = el.eq("username", filter.username);
				
			} else if(roles.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER)) {
				String[] users = User.findBssoOrCcsoUsersOfSameRegion(user);
				if(users.length > 0)
					el = el.in("username", users);
				
			} else if(roles.contains(AppConstants.ROLE_ID_BRANCH_MANAGER)) {
				String[] users = User.findBssoOrCcsoUsersOfSameBranch(user);
				if(users.length > 0)
					el = el.in("username", users);
			}
		}*/
		if(roles.contains(AppConstants.ROLE_ID_BSSO) || roles.contains(AppConstants.ROLE_ID_CCSO)) {
			el = el.eq("username", user.username);
			
		} else if(roles.contains(AppConstants.ROLE_ID_BRANCH_MANAGER) || roles.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER)) {
			String[] users = new String[]{};
			if(roles.contains(AppConstants.ROLE_ID_BRANCH_MANAGER))
				users = User.findBssoOrCcsoUsersOfSameBranch(user);
			else if (roles.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER))
				users = User.findBssoOrCcsoUsersOfSameRegion(user);
			
			if(filter.username!=null & Arrays.asList(users).contains(filter.username))
					el = el.eq("username", user.username);
			else if(users.length > 0)
				el = el.in("username", users);
			else // ** Branch/Region Manager is trying to view other Area's user's info
				return new String[0][colLen];
		}
		
		if (filter.startDate != null)
			el = el.ge("taskStartTime", filter.startDate);
		if (filter.endDate != null) //TODO whether 'endDate' is compared with `taskStartTime` or `taskEndTime`
			el = el.le("taskStartTime", filter.endDate);
		if (filter.isBssoCounter!=null)
			el = el.ge("taskType.isBssoTask", filter.isBssoCounter);
		if (filter.taskTypeId!=null)
			el = el.ge("taskType.id", filter.taskTypeId);
				
		//List <CustomerServiceTask> cstList = 
		//		el.orderBy("taskIssueTime desc").fetch("taskType, customerHistory").findList();
		List <CustomerServiceTask> cstList = 
				el.orderBy("taskIssueTime desc").findList();
		
		//ArrayList<String[]> rowList = new ArrayList<String[]>();
		
		int rowLen = cstList.size();
		/*if(rowLen==0)
			return new String[][] {{}};*/
		String[][] rows = new String[rowLen][colLen];
		
		//for(CustomerServiceTask cst : cstList){
		for(int i = 0; i<rowLen; i++){
			CustomerServiceTask cst = cstList.get(i);
			
			String[] cols = new String[colLen];
			cols[0] = cst.taskType.taskName;
			cols[1] = JavaUtils.formatDate(cst.taskIssueTime);
			cols[2] = cst.customerHistory.cardNumber;
			cols[3] = cst.taskType.bssoOrCcso();
			cols[4] = JavaUtils.formatTime(cst.taskIssueTime);
			cols[5] = JavaUtils.formatTime(cst.taskStartTime);
			cols[6] = cst.waitingTime.toString();
			cols[7] = JavaUtils.formatTime(cst.taskEndTime);;
			cols[8] = (cst.totalTaskTime==null) ? "00:00:00" : cst.totalTaskTime.toString();
			
			//rowList.add(cols);
			rows[i] = cols;
		}
		
		//return rowList.toArray(rows);
		return rows;
	}
	
	
	public static String[][] reportPendingToken(StartAndEndDate filter) {
		final int colLen = 10;
		String username = JavaUtils.getCurrentUsername();
		if(username.isEmpty() || filter==null)
			return new String[0][colLen];

		User user = User.findByUsername(username);
		ExpressionList<CustomerServiceTask> el = find.fetch("taskType").fetch("customerHistory").where();
		el = el.isNotNull("taskStartTime");  // ** assigned and started
		el = el.isNull("taskEndTime");  // ** but not completed yet
		
		//TODO modify as per reportServiceDetail
		List<Integer> roles = user.getRoleIdList();
		if(roles.contains(AppConstants.ROLE_ID_BSSO) || roles.contains(AppConstants.ROLE_ID_CCSO)) {
			el = el.eq("username", user.username);
			
		} else if(roles.contains(AppConstants.ROLE_ID_BRANCH_MANAGER)) {
			String[] users = User.findBssoOrCcsoUsersOfSameBranch(user);
			if(users.length > 0)
				el = el.in("username", users);
		} else if(roles.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER)) {
			String[] users = User.findBssoOrCcsoUsersOfSameRegion(user);
			if(users.length > 0)
				el = el.in("username", users);
			
		}

		if (filter.startDate != null)
			el = el.ge("taskStartTime", filter.startDate);
		if (filter.endDate != null)
			el = el.le("taskStartTime", filter.endDate);
		
		List <CustomerServiceTask> cstList = 
				el.orderBy("taskIssueTime desc").findList();
		
		int rowLen = cstList.size();
		String[][] rows = new String[rowLen][colLen];
		
		for(int i = 0; i<rowLen; i++) {
			CustomerServiceTask cst = cstList.get(i);
			
			String[] cols = new String[colLen];
			cols[0] = User.getBranchNameByUsername(cst.username);
			cols[1] = JavaUtils.formatDate(cst.taskIssueTime);
			cols[2] = cst.customerHistory.cardNumber;
			cols[3] = cst.taskType.taskName;
			cols[4] = User.findByUsername(cst.username).toString();
			cols[5] = cst.taskType.bssoOrCcso();
			cols[6] = cst.taskType.benchmarkTime.toString();
			cols[7] = JavaUtils.formatTime(cst.taskIssueTime);
			cols[8] = JavaUtils.formatTime(cst.taskStartTime);
			cols[9] = Interval.generate(cst.taskIssueTime, cst.taskStartTime).toString();

			rows[i] = cols;
		}

		return rows;
	}
	
	
	/*
	public static String[][] reportServiceWiseReport(ServiceWiseReport filter) {
		String username = JavaUtils.getCurrentUsername();
		if(username.isEmpty() || filter==null)
			return new String[][] {{}};

		User user = User.findByUsername(username);
		ExpressionList<CustomerServiceTask> el = find.where();
		
		List<Integer> roles = user.getRoleIdList();
		if(roles.contains(AppConstants.ROLE_ID_BSSO) || roles.contains(AppConstants.ROLE_ID_CCSO)) {
			el = el.eq("username", user.username);
			
		} else if(roles.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER)) {
			String[] users = User.findBssoOrCcsoUsersOfSameRegion(user);
			if(users.length > 0)
				el = el.in("username", users);
			
		} else if(roles.contains(AppConstants.ROLE_ID_BRANCH_MANAGER)) {
			String[] users = User.findBssoOrCcsoUsersOfSameBranch(user);
			if(users.length > 0)
				el = el.in("username", users);
		}
		
		if(filter.isBssoCounter != null)
			el = el.eq("taskType.isBssoTask", filter.isBssoCounter);
		
		if(filter.sol != null)
			el = el.eq("taskType.isBssoTask", filter.isBssoCounter);
		
		List <CustomerServiceTask> cstList = 
				el.orderBy("taskIssueTime desc").fetch("taskType, customerHistory").findList();
		
		final int colLen = 10;
		int rowLen = cstList.size();
		if(rowLen==0)
			return new String[][] {{}};
		
		String[][] rows = new String[rowLen][colLen];
		for(int i = 0; i<rowLen; i++) {
			CustomerServiceTask cst = cstList.get(i);
			
			String[] cols = new String[colLen];
			cols[0] = User.getBranchNameByUsername(cst.username);
			cols[1] = User.findByUsername(cst.username).toString();
			cols[2] = cst.taskType.taskName;
			cols[3] = cst.taskType.benchmarkTime.toString();
			cols[4] = Interval.generate(cst.taskType.benchmarkTime, cst.totalTaskTime).toString();
			cols[5] = cst.customerHistory.cardNumber;
			cols[6] = JavaUtils.formatTime(cst.taskIssueTime);

			rows[i] = cols;
		}

		return rows;
	}
	*/
	public static String[][] reportServiceWiseReport(ServiceWiseReport filter) {
		String sql = "SELECT t.task_name AS task_name, s.task_count AS task_count, s.avg_time AS avg_time "
						+ " FROM task_type t, ( "
						+ " SELECT c.task_type_id as task_type_id, COUNT(c.total_task_time) AS task_count, AVG(c.total_task_time) AS avg_time "
						+ " FROM customer_service_task c "
						+ " GROUP BY c.task_type_id) s "
						+ " WHERE s.task_type_id = t.id ";
		if(filter.isBssoCounter != null)
			sql = sql + " AND t.is_bsso_task = :ttype ";
		if(filter.sol != null)
			sql = sql + " AND c.username IN(:usrs) ";
		
		sql = sql + " ORDER BY t.task_name ";
		
		SqlQuery query = Ebean.createSqlQuery(sql);
		if(filter.isBssoCounter != null)
			query.setParameter("ttype", filter.isBssoCounter);
		if(filter.sol != null)
			query.setParameter("usrs", JavaUtils.makeString(User.findUsernamesByBranchSolId(filter.sol)));
		
		List<SqlRow> cstList = query.findList();

		final int colLen = 3;
		int rowLen = cstList.size();
		String[][] rows = new String[rowLen][colLen];

		for(int i = 0; i<rowLen; i++) {
			SqlRow r = cstList.get(i);
			if(!r.isEmpty()){
				String[] cols = new String[colLen];
				cols[0] = r.getString("task_name");
				cols[1] = r.getInteger("task_count").toString();
				//cols[2] = Interval.generate(r.getFloat("avg_time")).toString();
				cols[2] = Interval.generate((r.getFloat("avg_time")==null) ? 0 : r.getFloat("avg_time")).toString();
				
				rows[i] = cols;
				
				//System.out.println(r.get("avg_time").toString());
				//Object obj = r.get("avg_time");
				//if(obj != null)
				//	System.out.println(r.get("avg_time").getClass().getName());
	        }
		}
		
		return rows;
	}
	

	public static String[][] reportCounterWiseSummary(StartAndEndDate filter) {
		ArrayList<String[]> rowsList = new ArrayList<>();
		final int colLen = 8;
		String username = JavaUtils.getCurrentUsername();
		if(username.isEmpty() || filter==null)
			return new String[0][colLen];

		User user = User.findByUsername(username);
		List<Branch> branches = Branch.getListByUser(user);
		
		for(Branch b : branches) {
			int branchTotal = 0;
			rowsList.add(new String[] {b.name(), "", "", "", "", "", "", ""});
			
			boolean[] bssoFlag = {true, false};
			List<User> branchUsers = User.findListByBranch(b.id);
			
			String monSql = "SELECT DISTINCT TO_CHAR(task_issue_time, 'yyyy-MM') AS task_mon "
							+ " FROM customer_service_task "
							//+ " WHERE user_id IN( :usrs ) ";
							+ " WHERE user_id IN( " + JavaUtils.makeString(JavaUtils.userListToUserArr(branchUsers)) +" ) ";
			
			if(filter.startDate != null)
				monSql = monSql + " AND task_start_time >= :stime ";
			if(filter.endDate != null)
				monSql = monSql + " AND task_end_time <= :etime ";
			
			SqlQuery monQuery = Ebean.createSqlQuery(monSql);
			if(filter.startDate != null)
				monQuery.setParameter("stime", filter.startDate);
			if(filter.endDate != null)
				monQuery.setParameter("etime", filter.endDate);

			//monQuery.setParameter("usrs", JavaUtils.userListToUserArr(branchUsers));
			
			List<SqlRow> monthList = monQuery.findList();
			for(SqlRow r : monthList) {
				if(!r.isEmpty()){
					String month = r.getString("task_mon");
					rowsList.add(new String[] {"", JavaUtils.getMonthName(month), "", "", "", "", "", ""});
					
					for(boolean f : bssoFlag) {
						rowsList.add(new String[] {"", "", (f ? "BSSO" : "CCSO"), "", "", "", "", ""});
						int flagTotal = 0;
					
						String sql = " SELECT c.task_type_id AS task_type_id, count(c.task_type_id) AS tokens, AVG(c.total_task_time) AS avg_svc_time, AVG(c.waiting_time) AS avg_wait_time "
										+ " FROM customer_service_task c, task_type t "
										+ " WHERE c.task_type_id = t.id "
										+ " AND c.user_id IN( " + JavaUtils.makeString(JavaUtils.userListToUserArr(branchUsers)) + " ) "
										+ " AND TO_CHAR(c.task_issue_time, 'yyyy-MM') = :tm "
										+ " AND t.is_bsso_task = :bf ";

						if(filter.startDate != null)
							sql = sql + " AND task_start_time >= :stime ";
						if(filter.endDate != null)
							sql = sql + " AND task_end_time <= :etime ";
										
						sql = sql+ " GROUP BY c.task_type_id ";
						
						SqlQuery query = Ebean.createSqlQuery(sql);
						//query.setParameter("usrs", JavaUtils.userListToUserArr(branchUsers));
						query.setParameter("tm", r.getString("task_mon"));
						query.setParameter("bf", f);						
						if(filter.startDate != null)
							query.setParameter("stime", filter.startDate);
						if(filter.endDate != null)
							query.setParameter("etime", filter.endDate);
						
						List<SqlRow> rows = query.findList();
						for(SqlRow data : rows) {
							if(!data.isEmpty()) {
								String[] cols = new String[colLen];
								cols[0] = "";
								cols[1] = "";
								cols[2] = "";
								cols[3] = TaskType.get(data.getInteger("task_type_id")).taskName;
								cols[4] = data.getInteger("tokens").toString();
								cols[5] = TaskType.get(data.getInteger("task_type_id")).benchmarkTime.toString();
								cols[6] = Interval.generate(data.getInteger("avg_svc_time")).toString();
								cols[7] = Interval.generate(data.getInteger("avg_wait_time")).toString();
								
								rowsList.add(cols);
								
								flagTotal += data.getInteger("tokens");
							}
						}
						rowsList.add(new String[] {"", "", ((f ? "BSSO" : "CCSO") + " Total"), "", (""+flagTotal), "", "", ""});
						
						branchTotal += flagTotal;
					}
					
		        }
			}
			
			rowsList.add(new String[] {"", "", "Branch Total", "", (""+branchTotal), "", "", ""});
		}

		return rowsList.toArray(new String[rowsList.size()][colLen]);
	}
	
	public static String[][] reportCcsoTransaction(StartAndEndDate filter) {
		final int colLen = 7;
		String username = JavaUtils.getCurrentUsername();
		if(username.isEmpty() || filter==null)
			return new String[0][colLen];

		User user = User.findByUsername(username);
		ExpressionList<CustomerServiceTask> el = find.fetch("taskType").fetch("customerHistory").where();
		el.eq("taskType.isBssoTask", false);  // ** i.e., only CCSO tasks
		
		//TODO modify as per reportServiceDetail
		List<Integer> roles = user.getRoleIdList();
		if(roles.contains(AppConstants.ROLE_ID_BSSO) || roles.contains(AppConstants.ROLE_ID_CCSO)) {
			el = el.eq("username", user.username);
			
		} else if(roles.contains(AppConstants.ROLE_ID_BRANCH_MANAGER)) {
			String[] users = User.findBssoOrCcsoUsersOfSameBranch(user);
			if(users.length > 0)
				el = el.in("username", users);
		} else if(roles.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER)) {
			String[] users = User.findBssoOrCcsoUsersOfSameRegion(user);
			if(users.length > 0)
				el = el.in("username", users);
			
		}

		if (filter.startDate != null)
			el = el.ge("taskStartTime", filter.startDate);
		if (filter.endDate != null)
			el = el.le("taskStartTime", filter.endDate);
		
		List <CustomerServiceTask> cstList = 
				el.orderBy("taskIssueTime desc").findList();
		
		
		int rowLen = cstList.size();		
		String[][] rows = new String[rowLen][colLen];
		
		for(int i = 0; i<rowLen; i++) {
			CustomerServiceTask cst = cstList.get(i);
			
			String[] cols = new String[colLen];
			cols[0] = User.getBranchNameByUsername(cst.username);
			cols[1] = User.findByUsername(cst.username).toString();
			cols[2] = cst.taskType.taskName;
			//cols[3] = JavaUtils.formatTime(cst.taskType.benchmarkTime);
			cols[3] = cst.taskType.benchmarkTime.toString();
			cols[4] = (cst.totalTaskTime == null) ? "00:00:00" : Interval.generate(cst.taskType.benchmarkTime, cst.totalTaskTime).toString();
			cols[5] = cst.customerHistory.cardNumber;
			cols[6] = JavaUtils.formatTime(cst.taskIssueTime);

			rows[i] = cols;
		}

		return rows;
	}
	
	
	public static String[][] reportLiveInterfaceViewSub1(LiveInterfaceView filter) {
		ArrayList<String[]> rowsList = new ArrayList<>();
		final int colLen = 4;
		
		String sql = " SELECT tc.category_name AS category, cc.criteria_name AS visitor, COUNT(t.waiting_time) AS tokens, AVG(t.waiting_time) AS avg_wait_time "
						+ " FROM customer_service_task t, task_category tc, task_type tt, customer_history ch, customer_criteria cc "
						+ " WHERE t.task_type_id = tt.id "
						+ " AND tt.task_category_id = tc.id "
						+ " AND t.customer_history_id = ch.id "
						+ " AND ch.customer_criteria_id = cc.id ";

		if(filter.cardNumber != null & !filter.cardNumber.trim().isEmpty())
			sql = sql + " AND ch.card_number = :cn ";    //sql = sql + " AND ch.card_number = ':cn' ";
		if(filter.startDate != null)
			sql = sql + " AND t.task_start_time >= :stime ";
		if(filter.endDate != null)
			sql = sql + " AND t.task_end_time <= :etime ";
		
		sql = sql + " GROUP BY (tc.category_name, cc.criteria_name) ";
		
		SqlQuery query = Ebean.createSqlQuery(sql);
		if(filter.cardNumber != null & !filter.cardNumber.trim().isEmpty())
			query.setParameter("cn", filter.cardNumber);
		if(filter.startDate != null)
			query.setParameter("stime", filter.startDate);
		if(filter.endDate != null)
			query.setParameter("etime", filter.endDate);

		List<SqlRow> rows = query.findList();
		for(SqlRow r : rows) {
			if(!r.isEmpty()) {
				String[] cols = new String[colLen];
				cols[0] = r.getString("category");
				cols[1] = r.getString("visitor");
				cols[2] = r.getInteger("tokens").toString();
				cols[3] = Interval.generate(r.getInteger("avg_wait_time")).toString();
				
				rowsList.add(cols);
			}
		}
		
		return rowsList.toArray(new String[rowsList.size()][colLen]);
	}
	
	
	public static String[][] reportLiveInterfaceViewSub2(LiveInterfaceView filter) {
		ArrayList<String[]> rowsList = new ArrayList<>();
		final int colLen = 3;
		
		String sql = " SELECT tc.category_name AS category_name, cc.criteria_name AS criteria_name, COUNT(t.waiting_time) AS tokens "
						+ " FROM customer_service_task t, task_category tc, task_type tt, customer_history ch, customer_criteria cc "
						+ " WHERE t.task_type_id = tt.id "
						+ " AND tt.task_category_id = tc.id "
						+ " AND t.customer_history_id = ch.id "
						+ " AND ch.customer_criteria_id = cc.id "
						+ " AND t.task_status = '" + AppConstants.TASK_STATUS_CANCELLED + "' ";

		if(filter.cardNumber != null & !filter.cardNumber.trim().isEmpty())
			sql = sql + " AND ch.card_number = :cn ";    //sql = sql + " AND ch.card_number = ':cn' ";
		if(filter.startDate != null)
			sql = sql + " AND t.task_start_time >= :stime ";
		if(filter.endDate != null)
			sql = sql + " AND t.task_end_time <= :etime ";
		
		sql = sql + " GROUP BY (tc.category_name, cc.criteria_name) ";
		
		SqlQuery query = Ebean.createSqlQuery(sql);
		if(filter.cardNumber != null & !filter.cardNumber.trim().isEmpty())
			query.setParameter("cn", filter.cardNumber);
		if(filter.startDate != null)
			query.setParameter("stime", filter.startDate);
		if(filter.endDate != null)
			query.setParameter("etime", filter.endDate);
		
		List<SqlRow> rows = query.findList();
		for(SqlRow r : rows) {
			if(!r.isEmpty()) {
				String[] cols = new String[colLen];
				cols[0] = r.getString("category_name");
				cols[1] = r.getString("criteria_name");
				cols[2] = r.getInteger("tokens").toString();
				
				rowsList.add(cols);
			}
		}
		
		return rowsList.toArray(new String[rowsList.size()][colLen]);
	}
	
	
	public static String[][] reportLiveInterfaceViewSub3(LiveInterfaceView filter) {
		ArrayList<String[]> rowsList = new ArrayList<>();
		final int colLen = 6;
		
		String sqlFilterStatement = "";
		if(filter.cardNumber != null & !filter.cardNumber.trim().isEmpty())
			sqlFilterStatement = sqlFilterStatement + " AND ch.card_number = :cn ";    //sql = sql + " AND ch.card_number = ':cn' ";
		if(filter.startDate != null)
			sqlFilterStatement = sqlFilterStatement + " AND t.task_start_time >= :stime ";
		if(filter.endDate != null)
			sqlFilterStatement = sqlFilterStatement + " AND t.task_end_time <= :etime ";
		
		String sql = "SELECT tt.task_name AS task_name, (c.served+c.serving+c.waiting+c.cancelled) AS total_count, "
							+ "	c.served AS served, c.serving AS serving, c.waiting AS waiting, c.cancelled AS cancelled "
						+ "FROM ( "
						+ "  SELECT cst.task_type_id AS task_type_id, SUM(cst.served) AS served, SUM(cst.serving) AS serving, SUM(cst.waiting) AS waiting, SUM(cst.cancelled) AS cancelled "
						+ "  FROM ( "
						+ "      SELECT t.task_type_id AS task_type_id, COUNT(t.task_type_id) AS served, 0 AS serving, 0 AS waiting, 0 AS cancelled "
						+ "      FROM customer_service_task t , customer_history ch "
						+ "      WHERE t.customer_history_id = ch.id " + sqlFilterStatement
						+ "      AND t.task_status = '" + AppConstants.TASK_STATUS_ENDED + "' "
						+ "      GROUP BY t.task_type_id "
						+ "    UNION "
						+ "      SELECT t.task_type_id AS task_type_id, 0 AS served, COUNT(t.task_type_id) AS serving, 0 AS waiting, 0 AS cancelled "
						+ "      FROM customer_service_task t , customer_history ch "
						+ "      WHERE t.customer_history_id = ch.id " + sqlFilterStatement
						+ "      AND t.task_status = '" + AppConstants.TASK_STATUS_ACTIVE + "' "
						+ "      GROUP BY t.task_type_id "
						+ "    UNION "
						+ "      SELECT t.task_type_id AS task_type_id, 0 AS served, 0 AS serving, COUNT(t.task_type_id) AS waiting, 0 AS cancelled "
						+ "      FROM customer_service_task t , customer_history ch "
						+ "      WHERE t.customer_history_id = ch.id " + sqlFilterStatement
						+ "      AND t.task_status = '" + AppConstants.TASK_STATUS_ASSIGNED + "' "
						+ "      GROUP BY t.task_type_id "
						+ "    UNION "
						+ "      SELECT t.task_type_id AS task_type_id, 0 AS served, 0 AS serving, 0 AS waiting, COUNT(t.task_type_id) AS cancelled "
						+ "      FROM customer_service_task t , customer_history ch "
						+ "      WHERE t.customer_history_id = ch.id " + sqlFilterStatement
						+ "      AND t.task_status = '" + AppConstants.TASK_STATUS_CANCELLED + "' "
						+ "      GROUP BY t.task_type_id "
						+ "  ) cst "
						+ "  GROUP BY cst.task_type_id "
						+ " ) c, task_type tt "
						+ " WHERE c.task_type_id = tt.id "
						+ " ORDER BY tt.task_name ";
		
		SqlQuery query = Ebean.createSqlQuery(sql);
		if(filter.cardNumber != null & !filter.cardNumber.trim().isEmpty())
			query.setParameter("cn", filter.cardNumber);
		if(filter.startDate != null)
			query.setParameter("stime", filter.startDate);
		if(filter.endDate != null)
			query.setParameter("etime", filter.endDate);
		
		List<SqlRow> rows = query.findList();
		for(SqlRow r : rows) {
			if(!r.isEmpty()) {
				String[] cols = new String[colLen];
				cols[0] = r.getString("task_name");
				cols[1] = r.getInteger("total_count").toString();
				cols[2] = r.getInteger("served").toString();
				cols[3] = r.getInteger("serving").toString();
				cols[4] = r.getInteger("waiting").toString();
				cols[5] = r.getInteger("cancelled").toString();
				
				rowsList.add(cols);
			}
		}
		
		return rowsList.toArray(new String[rowsList.size()][colLen]);
	}

	
	//TODO implement
	public static String[][] reportLiveInterfaceView(Integer sol) {
		/*
"SELECT tc.category_name AS category, cc.criteria_name AS visitor, COUNT(t.waiting_time) AS tokens, AVG(t.waiting_time) AS avg_wait_time
FROM customer_service_task t, task_category tc, customer_criteria cc, task_type tt
WHERE t.task_type_id = tt.id
AND tt.task_category_id = tc.id
AND t.customer_history_id = cc.id
GROUP BY (tc.category_name, cc.criteria_name)"


"SELECT tc.category_name, cc.criteria_name, COUNT(t.waiting_time) AS tokens
FROM customer_service_task t, task_category tc, customer_criteria cc, task_type tt
WHERE t.task_type_id = tt.id
AND tt.task_category_id = tc.id
AND t.customer_history_id = cc.id
AND t.task_status = '" + AppConstants.TASK_STATUS_CANCELLED + "'"


"SELECT tt.task_name AS task_name, c.served AS served, c.serving AS serving, c.waiting AS waiting, c.cancelled AS cancelled
FROM (
  SELECT cst.task_type_id AS task_type_id, SUM(cst.served) AS served, SUM(cst.serving) AS serving, SUM(cst.waiting) AS waiting, SUM(cst.cancelled) AS cancelled
  FROM (
      SELECT t.task_type_id AS task_type_id, COUNT(t.task_type_id) AS served, 0 AS serving, 0 AS waiting, 0 AS cancelled
      FROM customer_service_task t
      WHERE t.task_status = '" + AppConstants.TASK_STATUS_ENDED + "'"
      GROUP BY t.task_type_id
    UNION
      SELECT t.task_type_id AS task_type_id, 0 AS served, COUNT(t.task_type_id) AS serving, 0 AS waiting, 0 AS cancelled
      FROM customer_service_task t
      WHERE t.task_status = '" + AppConstants.TASK_STATUS_ACTIVE + "'"
      GROUP BY t.task_type_id
    UNION
      SELECT t.task_type_id AS task_type_id, 0 AS served, 0 AS serving, COUNT(t.task_type_id) AS waiting, 0 AS cancelled
      FROM customer_service_task t
      WHERE t.task_status = '" + AppConstants.TASK_STATUS_ASSIGNED + "'"
      GROUP BY t.task_type_id
    UNION
       SELECT t.task_type_id AS task_type_id, 0 AS served, 0 AS serving, 0 AS waiting, COUNT(t.task_type_id) AS cancelled
       FROM customer_service_task t
       WHERE t.task_status = '" + AppConstants.TASK_STATUS_CANCELLED + "'"
       GROUP BY t.task_type_id
  ) cst
  GROUP BY cst.task_type_id
) c, task_type tt
ORDER BY tt.task_name"

		*/
			
		return new String[][]{{}};
	}
	
	
	public static String[][] reportUserSummaryProfile(StartAndEndDate filter) {
		ArrayList<String[]> rowsList = new ArrayList<>();
		final int colLen = 8;
		String username = JavaUtils.getCurrentUsername();
		if(username.isEmpty() || filter==null)
			return new String[0][colLen];

		User user = User.findByUsername(username);
		List<Branch> branches = Branch.getListByUser(user);
		
		for(Branch b : branches) {
			int branchTotal = 0;
			rowsList.add(new String[] {b.name(), "", "", "", "", "", "", ""});
			
			List<User> branchUsers = User.findListByBranch(b.id);
			String userList = JavaUtils.makeString(JavaUtils.userListToUserArr(branchUsers));
			if(userList==null || userList.trim().isEmpty())
				continue;
			
			String monSql = "SELECT DISTINCT TO_CHAR(task_issue_time, 'yyyy-MM') AS task_mon "
							+ " FROM customer_service_task "
							+ " WHERE user_id IN( " + userList  + " ) ";
			
			if(filter.startDate != null)
				monSql = monSql + " AND task_start_time >= :stime ";
			if(filter.endDate != null)
				monSql = monSql + " AND task_end_time <= :etime ";
			
			SqlQuery monQuery = Ebean.createSqlQuery(monSql);
			if(filter.startDate != null)
				monQuery.setParameter("stime", filter.startDate);
			if(filter.endDate != null)
				monQuery.setParameter("etime", filter.endDate);
			
			monQuery.setParameter("usrs", JavaUtils.userListToUserArr(branchUsers));
			
			List<SqlRow> monthList = monQuery.findList();
			for(SqlRow r : monthList) {
				if(!r.isEmpty()){
					String month = r.getString("task_mon");
					rowsList.add(new String[] {"", JavaUtils.getMonthName(month), "", "", "", "", "", ""});
					
					for(User u : branchUsers) {
						rowsList.add(new String[] {"", "", u.toString(), "", "", "", "", ""});
						int usrTotal = 0;
					
						String sql = " SELECT task_type_id, count(*) as tokens, AVG(total_task_time) AS avg_svc_time, AVG(waiting_time) AS avg_wait_time "
										+ " FROM customer_service_task "
										+ " WHERE user_id = :usr  AND TO_CHAR(task_issue_time, 'yyyy-MM') = :tm ";
						if(filter.startDate != null)
							sql = sql + " AND task_start_time >= :stime ";
						if(filter.endDate != null)
							sql = sql + " AND task_end_time <= :etime ";
										
						sql = sql+ " GROUP BY task_type_id ";
						
						SqlQuery query = Ebean.createSqlQuery(sql);
						query.setParameter("usr", u.id);
						query.setParameter("tm", r.getString("task_mon"));
						if(filter.startDate != null)
							query.setParameter("stime", filter.startDate);
						if(filter.endDate != null)
							query.setParameter("etime", filter.endDate);
						
						List<SqlRow> rows = query.findList();
						for(SqlRow data : rows) {
							if(!data.isEmpty()) {
								String[] cols = new String[colLen];
								cols[0] = "";
								cols[1] = "";
								cols[2] = "";
								cols[3] = TaskType.get(data.getInteger("task_type_id")).taskName;
								cols[4] = data.getInteger("tokens").toString();
								cols[5] = TaskType.get(data.getInteger("task_type_id")).benchmarkTime.toString();
								cols[6] = Interval.generate(data.getInteger("avg_svc_time")).toString();
								cols[7] = Interval.generate(data.getInteger("avg_wait_time")).toString();
								
								rowsList.add(cols);
								
								usrTotal += data.getInteger("tokens");
							}
						}
						rowsList.add(new String[] {"", "", (u.username + " Total"), "", (""+usrTotal), "", "", ""});
						branchTotal += usrTotal;
					}
		        }
			}
			
			rowsList.add(new String[] {"", "", "Branch Total", "", (""+branchTotal), "", "", ""});
		}

		return rowsList.toArray(new String[rowsList.size()][colLen]);
	}
	
	
	public static String[][] reportDetailedTokenReport(StartAndEndDate filter) {
		final int colLen = 12;
		String username = JavaUtils.getCurrentUsername();
		if(username.isEmpty() || filter==null)
			return new String[0][colLen];

		User user = User.findByUsername(username);
		ExpressionList<CustomerServiceTask> el = find.fetch("taskType").fetch("customerHistory").where();
		
		List<Integer> roles = user.getRoleIdList();
		if(roles.contains(AppConstants.ROLE_ID_BSSO) || roles.contains(AppConstants.ROLE_ID_CCSO)) {
			el = el.eq("username", user.username);
			
		} else if(roles.contains(AppConstants.ROLE_ID_BRANCH_MANAGER)) {
			String[] users = User.findBssoOrCcsoUsersOfSameBranch(user);
			if(users.length > 0)
				el = el.in("username", users);
		} else if(roles.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER)) {
			String[] users = User.findBssoOrCcsoUsersOfSameRegion(user);
			if(users.length > 0)
				el = el.in("username", users);
			
		}

		if (filter.startDate != null)
			el = el.ge("taskStartTime", filter.startDate);
		if (filter.endDate != null)
			el = el.le("taskStartTime", filter.endDate);
		
		List <CustomerServiceTask> cstList = 
				el.orderBy("taskIssueTime desc").findList();
		
		
		int rowLen = cstList.size();		
		String[][] rows = new String[rowLen][colLen];
		
		for(int i = 0; i<rowLen; i++) {
			CustomerServiceTask cst = cstList.get(i);
			
			String[] cols = new String[colLen];
			cols[0] = User.getBranchNameByUsername(cst.username);
			cols[1] = cst.customerHistory.cardNumber;
			cols[2] = cst.taskType.taskName;
			cols[3] = User.findByUsername(cst.username).toString();
			cols[4] = JavaUtils.formatDate(cst.taskIssueTime);
			cols[5] = cst.taskType.bssoOrCcso();
			cols[6] = (cst.taskType.benchmarkTime==null) ? "" : cst.taskType.benchmarkTime.toString();
			cols[7] = JavaUtils.formatTime(cst.taskIssueTime);
			cols[8] = (cst.taskStartTime==null) ? "00:00:00" :JavaUtils.formatTime(cst.taskStartTime);
			cols[9] = (cst.totalTaskTime==null) ? "00:00:00" : cst.totalTaskTime.toString();
			cols[10] = (cst.taskEndTime==null) ?  "00:00:00" : JavaUtils.formatTime(cst.taskEndTime);
			cols[11] = (cst.waitingTime==null) ?  "00:00:00" : cst.waitingTime.toString();
			
			rows[i] = cols;
		}

		return rows;
	}
	

	public static String[][] reportBreakReport(BreakReport filter) {
		final int colLen = 7;
		User user = User.findByUsername(JavaUtils.getCurrentUsername());
		if(filter==null || user==null)
			return new String[0][colLen];
		
		ExpressionList<CustomerServiceTask> el = find.fetch("taskType").fetch("customerHistory").where();
		el = el.eq("taskStatus", AppConstants.TASK_STATUS_CANCELLED);  // ** Broken (=cancelled) tasks
		
		List<Integer> roles = user.getRoleIdList();
		if(roles.contains(AppConstants.ROLE_ID_BSSO) || roles.contains(AppConstants.ROLE_ID_CCSO)) {
			el = el.eq("username", user.username);
			
		} else if(roles.contains(AppConstants.ROLE_ID_BRANCH_MANAGER) || roles.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER)) {
			String[] users = new String[]{};
			if(roles.contains(AppConstants.ROLE_ID_BRANCH_MANAGER)) {
				users = User.findBssoOrCcsoUsersOfSameBranch(user);
				
			} else if (roles.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER)) {
				users = User.findBssoOrCcsoUsersOfSameRegion(user);
				/*if(filter.username!=null) {
					if(Arrays.asList(users).contains(filter.username))
						el = el.eq("username", user.username);
					else // ** Regional Manager is trying to view other Region's user's info
						return new String[0][colLen];
				} else if(users.length > 0) {
					el = el.in("username", users);
				}*/
			}
			
			/*
			if(filter.username!=null & Arrays.asList(users).contains(filter.username))
				el = el.eq("username", user.username);
			else if(filter.sol!=null &
				((roles.contains(AppConstants.ROLE_ID_BRANCH_MANAGER) & user.branch()!=null & filter.sol.equals(user.branch().sol)) ||  // ** Branch Manager of this branch
						(roles.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER) & user.regionId!=null & BranchInRegion.findByRegionAndSol(user.regionIdAsInt(), filter.sol)!=null))) {
					el = el.in("username", User.findUsernamesByBranchSolId(filter.sol));
			} else if(users.length > 0)
				el = el.in("username", users);
			else // ** Branch/Region Manager is trying to view other Area's user's info
				return new String[0][colLen];
			*/
			
			if(filter.username!=null & Arrays.asList(users).contains(filter.username)) {
				el = el.eq("username", user.username);
			} else if(filter.sol!=null) {
				if ((roles.contains(AppConstants.ROLE_ID_BRANCH_MANAGER) & user.branch()!=null & filter.sol.equals(user.branch().sol)) ||  (roles.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER) & user.regionId!=null & BranchInRegion.findByRegionAndSol(user.regionIdAsInt(), filter.sol)!=null)) {
					el = el.in("username", User.findUsernamesByBranchSolId(filter.sol));
				}
			} else if(users.length > 0) {
				el = el.in("username", users);
			} else {
				return new String[0][colLen];
			}
		}

		if (filter.startDate != null)
			el = el.ge("taskStartTime", filter.startDate);
		if (filter.endDate != null)
			el = el.le("taskStartTime", filter.endDate);
		
		List <CustomerServiceTask> cstList = el.orderBy("taskIssueTime desc").findList();
		
		int rowLen = cstList.size();		
		String[][] rows = new String[rowLen][colLen];
		
		for(int i = 0; i<rowLen; i++) {
			CustomerServiceTask cst = cstList.get(i);
			
			String[] cols = new String[colLen];
			cols[0] = User.getBranchNameByUsername(cst.username);
			cols[1] = JavaUtils.formatDate(cst.taskIssueTime);
			cols[2] = User.findByUsername(cst.username).toString();
			cols[3] = cst.taskType.bssoOrCcso();
			cols[4] = TaskEventHistory.getCancellationReasonByCST(cst);
			cols[5] = JavaUtils.formatTime(cst.taskStartTime);
			cols[6] = JavaUtils.formatTime(cst.taskEndTime);

			rows[i] = cols;
		}

		return rows;
	}
	
	// -----------------------------------------------------------------
	
}
