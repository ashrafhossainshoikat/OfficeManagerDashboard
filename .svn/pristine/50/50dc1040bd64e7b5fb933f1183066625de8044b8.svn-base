package connectplus.models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;
import services.AppConstants;
import services.JavaUtils;
import services.ScalaUtils;

import java.util.*;

import javax.persistence.*;

import models.geo.BranchInRegion;
import models.geo.Region;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Query;
import com.avaje.ebean.Ebean;

import connectplus.models.geo.Branch;
import be.objectify.deadbolt.core.models.Permission;
import be.objectify.deadbolt.core.models.Subject;

//@PersistenceUnit(name="connectplus")
@Entity
@Table(name="UserInfo")
public class User extends Model implements Subject {
	@Id
	@Column(name="UserID")
	public Integer id;

	//@Required
	@Column(name="UserName")
	public String username;
	
	@Column(name="FullName")
	public String fullName;
	
	@Column(name="RoleID")
	public Integer roleId;
	
	@Column(name="BranchId")
	public Integer branchId;
	
	@Column(name="Zone")
	public String regionId;
	
	@Column(name="PasswordSalt")
	public String passwordSalt;
	
	@Column(name="SaltedHash")
	public String hashedPassword;
	
	@Column(name="IsActive")
	public boolean isActive;

	@Column(name="IsLocked")
	public boolean isLocked;
	
	@Column(name="IsApproved")
	public boolean isApproved;
	// ----------------------------------------------------------
	
	// ** implements deadbolt methods
    @Override
    @Transient
    public List<? extends be.objectify.deadbolt.core.models.Role> getRoles() {
        return UserRole.findRolesByUserId(this.id);
    }
    
    @Override
    @Transient
    public List<? extends Permission> getPermissions() {
        return UserPermission.findPermissionsByUserId(this.id);
    }
    
    @Override
    @Transient
    public String getIdentifier() {
        return username;
    }
	// ----------------------------------------------------------
	
 	private static Finder<Integer, User> find = new Finder("connectplus", Integer.class, User.class);

	@Transient
	public List<connectplus.models.Permission> permissions() {
		return UserPermission.findPermissionsByUserId(this.id);
	}

    @Transient
    public List<Integer> permissionFlag() {
    	List<Integer> flags = new ArrayList<Integer>();
    	
    	List<connectplus.models.Permission> permissionList = permissions();
    	
    	for(connectplus.models.Permission p : permissionList) {
    		if(AppConstants.ACTION_DEADBOLT_NAME_LIST_TASK_ENTRY.contains(p.permissionName)) {
    			flags.add(AppConstants.MENU_FLAG_TASK_ENTRY);
    			break;
    		}
    	}
    	
    	for(connectplus.models.Permission p : permissionList) {
    		if(AppConstants.ACTION_DEADBOLT_NAME_LIST_BACK_OFFICE.contains(p.permissionName)) {
    			flags.add(AppConstants.MENU_FLAG_BACK_OFFICE);
    			break;
    		}
    	}
    	
    	for(connectplus.models.Permission p : permissionList) {
    		if(AppConstants.ACTION_DEADBOLT_NAME_LIST_REPORT.contains(p.permissionName)) {
    			flags.add(AppConstants.MENU_FLAG_REPORT);
    			break;
    		}
    	}
    	
    	return flags;
    }
    
    @Transient
	public List<String> permissionNames() {
    	List<String> pn = new ArrayList<>();
    	
    	for(connectplus.models.Permission p : permissions()){
    		pn.add(p.permissionName);
    	}
		
		return pn;
	}
    // ----------------------------------------------------------
	
	@Transient
	public Branch branch() {
		return Branch.findByUser(this);
	}
	
	@Transient
	public Integer regionIdAsInt() {
		if(this.regionId == null)
			return null;
		
		try {
			Region r = Region.findByZoneID(this.regionId);
			return (r==null) ? 0 : r.id;
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public static User get(int id) {
		return find.where()
			.eq("id", id)
			.findUnique();
	}
	
	public static User findByUsername(String username) {
		return find.where()
				.eq("username", username)
				.findUnique();
	}
	
	
	public static User findActiveUserByUsername(String username) {
		return find.where()
				.eq("username", username)
				.eq("IsActive", true)
				.eq("isLocked", false)
				.eq("isApproved", true)
				.findUnique();
	}


	public static User findByUsernameAndPassword(String username, String password) {
		return find.where()
				.eq("username", username)
				.eq("hashedPassword", password)
				.findUnique();
	}
	
	
	 public static int findRoleByUserName(String userName){
		 return find.where()
			 .eq("userName", userName)
			 .findUnique().roleId;
		} 
	 
	
	public static List<User> all() {
		return find.all();
	}

	public static void create(User userInfo) {
		userInfo.save();
	}

	public static void delete(int id) {
		find.ref(id).delete();
	}
	
	public static List<User> findListByBranch(int branchId) {
		return find.where().eq("branchId", branchId).findList();
	}
	
	public static List<User> findByBranchSolId(int sol) {
		Branch b = Branch.findBySol(sol);
		if(b==null)
			return new ArrayList<User>();
			
		return findListByBranch(b.id);
	}
	
	public static String[] findUsernamesByBranchSolId(int sol) {
		ArrayList<String> users = new ArrayList<String>();
		for(User u : findByBranchSolId(sol)){
			users.add(u.username);
		}
		
		return users.toArray(new String[users.size()]);
	}
	
	public static List<User> findOtherUsersOfSameBranch(int branchId, String username) {
		if(username==null || username.isEmpty())
			return new ArrayList<User>();
		
		return find.where()
				.eq("branchId", branchId)
				.ne("username", username)
				.findList();
	}
	
	public static List<User> findOtherUsersOfSameBranch(String username) {
		if(username==null || username.isEmpty())
			return new ArrayList<User>();
		
		User user = findByUsername(username);
		if(user==null || user.branchId==null)
			return new ArrayList<User>();
		
		return findOtherUsersOfSameBranch(user.branchId, username);
	}
	
	/*public static List<User> findOtherBssoOrCcsoUsersOfSameBranch(int branchId, String username) {
		if(username==null || username.isEmpty())
			return new ArrayList<User>();
		
		Query<User> query = Ebean.createQuery(User.class);
		//query.where("BranchId = :bid and UserName <> ':uname' and ("
		//		+ "(RoleID = :bssoid or RoleID = :ccsoid) or " 
		//		+ "")
		query.where(" WHERE BranchId = :bid and UserName <> ':uname' and ( "
				+ " (RoleID in ( :bssoid , :ccsoid )) or UserID in ( "
				+ " SELECT UserID FROM UsersInRoles WHERE RoleID IN ( :bssoid , :ccsoid )))")
		     .setParameter("bid", branchId)
		     .setParameter("uname", username)
		     .setParameter("bssoid", AppConstants.ROLE_ID_BSSO)
		     .setParameter("ccsoid", AppConstants.ROLE_ID_CCSO)
		     .setDistinct(true);
		
		System.out.println("----- query :" + query.getGeneratedSql());

		return query.findList();
	}*/
	public static List<User> findOtherBssoOrCcsoUsersOfSameBranch(int branchId, String username) {
		if(username==null || username.isEmpty())
			return new ArrayList<User>();
		
		return find.where(" BranchId = :bid and UserName <> :uname and ( "
				+ " (RoleID in ( :bssoid , :ccsoid )) or (UserID in ( "
				+ " SELECT UserID FROM UsersInRoles WHERE RoleID IN ( :bssoid , :ccsoid ))))")
				.setParameter("bid", branchId)
				.setParameter("uname", username)
				.setParameter("bssoid", AppConstants.ROLE_ID_BSSO)
				.setParameter("ccsoid", AppConstants.ROLE_ID_CCSO)
				.findList();
	}
	
	public static List<User> findOtherBssoOrCcsoUsersOfSameRegion(int regionId) {
		List<Integer> branches = BranchInRegion.findBranchIdsByRegion(regionId);
		if(branches.size()==0)
			return new ArrayList<User>();
		
		String branchList = JavaUtils.makeString(branches);
		
		return find.where(" BranchId in ( " + branchList + " ) and ( "
				+ " (RoleID in ( :bssoid , :ccsoid )) or (UserID in ( "
				+ " SELECT UserID FROM UsersInRoles WHERE RoleID IN ( :bssoid , :ccsoid ))))")
				.setParameter("bssoid", AppConstants.ROLE_ID_BSSO)
				.setParameter("ccsoid", AppConstants.ROLE_ID_CCSO)
				.findList();
	}
	
	public static List<User> findOtherBssoOrCcsoUsersOfSameBranch(String username) {
		if(username==null || username.isEmpty())
			return new ArrayList<User>();
		
		User user = findByUsername(username);
		if(user==null || user.branchId==null)
			return new ArrayList<User>();
		
		return findOtherBssoOrCcsoUsersOfSameBranch(user.branchId, username);
	}
	
	public static List<User> getAllBssoOrCcsoUser(){
		//TODO implement
		//return find.where().or(Expr.eq("", value), arg1)
		
		return null;
	}
	
	public static String[] findBssoOrCcsoUsersOfSameBranch(User user) {
		ArrayList<String> usernames = new ArrayList<String>();
		
		if(user!=null && user.branchId!=null) {
			for(User u: findOtherBssoOrCcsoUsersOfSameBranch(user.branchId, user.username)) {
				if(u.username!=null && !u.username.trim().isEmpty())
					usernames.add(u.username);
	        }
		}
		
		return usernames.toArray(new String[usernames.size()]);
	}
	
	public static String[] findBssoOrCcsoUsersOfSameRegion(User user) {
		ArrayList<String> usernames = new ArrayList<String>();
		
		if(user!=null && user.regionId!=null) {
			for(User u: findOtherBssoOrCcsoUsersOfSameRegion(user.regionIdAsInt())) {
				if(u.username!=null && !u.username.trim().isEmpty())
					usernames.add(u.username);
			}
		}
		
		return usernames.toArray(new String[usernames.size()]);
	}
	
	public static Map<String,String> getOtherUsernameAsMap(String username) {
		LinkedHashMap<String,String> users = new LinkedHashMap<String,String>();
		User user = User.findByUsername(username);
		
		if(user==null)
			return users;
		
        for(User u: findOtherUsersOfSameBranch(user.branchId, username)) {
        	if(u.username!=null && !u.username.trim().isEmpty())
        		users.put(u.username, u.username);
        }

        return users;
    }
	
	public static Map<String,String> getUsernameAsMapForReportFilter() {
		LinkedHashMap<String,String> users = new LinkedHashMap<String,String>();
		
		User user = JavaUtils.getCurrentUser();
		if(user == null)
			return users;
		
		List<User> userList;
		
		if(user.isBssoOrCcsoUser()) {
			userList = new ArrayList<User>();
			userList.add(user);
		} else if (user.getRoleIdList().contains(AppConstants.ROLE_ID_BRANCH_MANAGER)) {
			userList = findOtherBssoOrCcsoUsersOfSameBranch(user.branchId, user.username);
		} else if (user.getRoleIdList().contains(AppConstants.ROLE_ID_REGIONAL_MANAGER)) {
			userList = findOtherBssoOrCcsoUsersOfSameRegion(user.regionIdAsInt());
		} else {
			userList = getAllBssoOrCcsoUser();
		}
		
        for(User u: userList) {
        	if(u.username!=null && !u.username.trim().isEmpty())
        		users.put(u.username, u.username);
        }

        return users;
    }
	
	public static Map<String,String> getOtherBssoOrCcsoUsernameAsMap(String username) {
		LinkedHashMap<String,String> users = new LinkedHashMap<String,String>();
		User user = User.findByUsername(username);
		
		if(user==null)
			return users;
		
		for(User u: findOtherBssoOrCcsoUsersOfSameBranch(user.branchId, username)) {
			if(u.username!=null && !u.username.trim().isEmpty())
				users.put(u.username, u.username);
		}
		
		return users;
	}
	
	public boolean isBssoOrCcsoUser(){

		for(Role r : UserRole.findRolesByUserId(this.id)) {
			if(r.id==AppConstants.ROLE_ID_BSSO || r.id==AppConstants.ROLE_ID_CCSO)
				return true;
		}

		return false;
	}
	
	public List<Integer> getRoleIdList() {
		ArrayList<Integer> roles = new ArrayList<Integer>();
		
		for(Role r : UserRole.findRolesByUserId(this.id))
				roles.add(r.id);
		
		return roles;
	}
	
	
	public static String getBranchNameByUsername(String username) {
		if(username==null)
			return null;
		
		User user = findByUsername(username);
		if(user==null)
			return null;
		
		if(user.branchId == null)
			return "";
		
		Branch b = Branch.get(user.branchId);
		if(b == null)
			return "";
		
		return b.name();
	}
	
	
	@Override
	public String toString() {
		return username + ": " + fullName;
	}
	
}