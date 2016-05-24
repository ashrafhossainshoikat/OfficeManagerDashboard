package models.security;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import connectplus.models.User;
import play.db.ebean.Model;
import play.mvc.Http.Context;
import services.AppConstants;
import services.JavaUtils;
import services.ScalaUtils;

@Entity
public class AuditLog extends Model {

	@Id
	public Long id;

	@Column(nullable=false)
	public int userId;

	@Column(nullable=false)
	public String username;
	
	@Column(nullable=false)
	public int roleId;
	
	public Integer branchId;
	
	//public Integer regionId;

	//@Required
	//@ManyToOne
	//@JoinColumn(name = "activity_type", referencedColumnName = "id")
	//public StatusCode activityType;

	@Column(nullable=false)
	public String userAction;
	
	@Column(nullable=false)
	public String tableName;
	
	@Column(nullable=false)
	public long primaryKeyValue;
	
	public String remarks;

	//public String accessedUrl;

	public String visitorIp;

	public String visitorBrowser;

	@Column(nullable=false)
	public Date activityDateTime;
	// ** ----------------------------------------------

	public static Finder<Long, AuditLog> find = new Finder(Long.class, AuditLog.class);

	public static List<AuditLog> all() {
		return find.all();
	}

	public static AuditLog get(long id) {
		return find.where().eq("id", id).findUnique();
	}
	
	private boolean create() {
		try {
			Context ctx = Context.current();
			User user = null;
			
			String sessionUser = JavaUtils.getCurrentUsername();
			if(!sessionUser.isEmpty())
				user = User.findByUsername(sessionUser);
			
			username = sessionUser;
			if(user!=null)
				userId = user.id;
			if(user.roleId!=null)
				roleId = user.roleId;
			if(user.branchId != null)
				branchId = user.branchId;
			//if(user.region != null)
			//	regionId = user.region.id;
			visitorIp = ctx.request().remoteAddress();
			visitorBrowser = ScalaUtils.stripSome(Context.current().request().getHeader(AppConstants.REQUEST_HEADER_BROWSER_KEY));
			activityDateTime = new Date();
			this.save();
			
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}

	/*public static Long create(AuditLog al) {
		if (al == null)
			return null;

		al.activityDateTime = new Date();
		al.save();
		return al.id;
	}*/

	public static boolean logUserActivity(String actionName, String tableName, long modelObjId) {
		return logUserActivity(actionName, tableName, modelObjId, null);
	}
	
	public static boolean logUserActivity(String actionName, String tableName, long modelObjId, String remarks) {
		if(actionName==null || actionName.isEmpty() || tableName==null || tableName.isEmpty() || modelObjId==0L)
			return false;
		
		AuditLog al = new AuditLog();
		al.userAction = actionName;
		al.tableName = tableName;
		al.primaryKeyValue = modelObjId;
		al.remarks = remarks;
		
		return al.create();
	}
}
