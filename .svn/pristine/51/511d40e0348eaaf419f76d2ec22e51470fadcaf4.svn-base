package connectplus.models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;

import com.avaje.ebean.Expr;
import com.avaje.ebean.ExpressionList;


@Entity
@Table(name="UserPrivilege")
public class UserPermission extends Model {

	@Id
	@Column(name="UserPermissionID")
	public Long id;
	
	@Column(name="PermissionID")
	public Integer permissionId;

	@Column(name="UserID")
	public Integer userId;
	
	@Column(name="RoleID")
	public Integer roleId;

	private static Finder<Long, UserPermission> find = new Finder("connectplus", Long.class, UserPermission.class);
	
	public static UserPermission get(long id) {
		return find.where()
			.eq("id", id)
			.findUnique();
	}
	
	public static List<UserPermission> all() {
		return find.all();
	}

	/*
	public static List<UserPermission> findListByUserId(int userId) {
		//TODO also find permissions for user's assigned roles
		return find.where()
				.eq("userId", userId)
				.findList();
	}
	*/
	
	public static List<UserPermission> findListByRoleId(int roleId) {
		return find.where()
				.eq("roleId", roleId)
				.findList();
	}
	
	
	public static List<Permission> findPermissionsByUserId(int userId) {
		//List<UserPermission> userPermissions = findListByUserId(userId);
		List<UserPermission> userPermissions = findListForUserId(userId);
		List<Permission> permissions = new ArrayList<Permission>();
		
		for(UserPermission up : userPermissions){
			Permission p = Permission.get(up.permissionId);
			if(p != null)
				permissions.add(p);
		}
		
		return permissions;
	}
	
	
	public static List<UserPermission> findListByUserIdOrRoleIds(int userId, List<Integer> roles) {
		ExpressionList<UserPermission> el = find.where();
		
		if(roles==null || roles.size()==0)
			return el.eq("userId", userId).findList();
		
		return el
				.or(Expr.eq("userId", userId), Expr.in("roleId", roles))
				.findList();
	}
	
	public static List<UserPermission> findListForUserId(int userId) {
		List<Role> roles = UserRole.findRolesByUserId(userId);
		List<Integer> rolesAsInt = rolesToInts(roles);
		return findListByUserIdOrRoleIds(userId, rolesAsInt);
	}
	
	public static List<UserPermission> findListForAccess(int permissionId, int userId, List<Integer> roles) {
		ExpressionList<UserPermission> el = find.where();
		
		if(roles==null || roles.size()==0)
			return el
					.eq("permissionId", permissionId)
					.eq("userId", userId)
					.findList();
		
		return el
				.eq("permissionId", permissionId)
				.or(Expr.eq("userId", userId), Expr.in("roleId", roles))
				.findList();
	}
	
	public static List<UserPermission> findListByUserIdAndPermissionId(int userId, int permissionId) {
		List<Role> roles = UserRole.findRolesByUserId(userId);
		List<Integer> rolesAsInt = rolesToInts(roles);
		return findListForAccess(permissionId, userId, rolesAsInt);
	}
	
	private static List<Integer> rolesToInts(List<Role> roles){
		List<Integer> rolesAsInt = new ArrayList<Integer>();
		for(Role r:roles){
			rolesAsInt.add(r.id);
		}
		return rolesAsInt;
	}
	
}