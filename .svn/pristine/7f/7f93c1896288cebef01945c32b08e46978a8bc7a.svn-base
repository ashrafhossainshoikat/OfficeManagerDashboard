package connectplus.models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "UsersInRoles")
public class UserRole extends Model {
	@Id
	@Column(name = "urID")
	public Integer id;

	// @Required
	@Column(name = "UserID")
	public Integer userId;

	@Column(name = "RoleID")
	public Integer roleId;

	private static Finder<Integer, UserRole> find = new Finder("connectplus", Integer.class, UserRole.class);

	public static UserRole get(int id) {
		return find.where().eq("id", id).findUnique();
	}

	public static List<UserRole> all() {
		return find.all();
	}

	public static List<UserRole> findListByUserId(int userId) {
		return find.where().eq("userId", userId).findList();
	}

	public static List<UserRole> findListByRoleId(int roleId) {
		return find.where().eq("roleId", roleId).findList();
	}

	public static List<Role> findRolesByUserId(int userId) {
		List<UserRole> userRole = findListByUserId(userId);
		List<Role> roles = new ArrayList<Role>();
		int defaultRoleId = -1; // -1 is dummy for initialization
		
		User user = User.get(userId);
		if(user == null)
			return roles;
		
		if(user.roleId != null) {
			Role defaultRole = Role.get(user.roleId);
			if(defaultRole != null) {
				defaultRoleId = defaultRole.id;
				roles.add(defaultRole);
			}
		}
		
		for(UserRole ur: userRole) {
			if(ur.roleId!=null && ur.roleId!=defaultRoleId){
				Role r = Role.get(ur.roleId);
				if(r != null)
					roles.add(r);
			}
		}
		
		return roles;
	}

}