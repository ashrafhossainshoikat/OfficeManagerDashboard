package connectplus.models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name="PermissionsCriteria")
public class Permission extends Model implements be.objectify.deadbolt.core.models.Permission{
	@Id
	@Column(name="PermissionID")
	public Integer id;

	//@Required
	@Column(name="PermissionName")
	public String permissionName;
	
	@Column(name="ViewOrder")
	public Integer viewOrder;
	
	@Column(name="ModuleID")
	public Integer moduleId;
	
	/** implements deadbolt method */
	public String getValue() {
        return permissionName;
    }
	/** --------------------------- */

	private static Finder<Integer, Permission> find = new Finder("connectplus", Integer.class, Permission.class);
	
	public static Permission get(int id) {
		return find.where()
			.eq("id", id)
			.findUnique();
	}
	
	public static List<Permission> all() {
		return find.all();
	}
	
	public static Permission findByPermissionName(String permissionName) {
		if(permissionName==null || permissionName.isEmpty())
			return null;
		
		return find.where()
			.eq("permissionName", permissionName)
			.findUnique();
	}

	@Override
	public String toString() {
		return "Permission [" + id + ": " + permissionName + "]";
	}

}