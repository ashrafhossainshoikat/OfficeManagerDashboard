package connectplus.models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name="Roles")
public class Role extends Model implements be.objectify.deadbolt.core.models.Role {
	@Id
	@Column(name="RoleID")
	public Integer id;

	@Column(name="RoleName")
	public String roleName;
	
	/** implements deadbolt method */
    public String getName() {
        return roleName;
    }
    /** --------------------------- */
	
	private static Finder<Integer, Role> find = new Finder("connectplus", Integer.class, Role.class);
	
	public static Role get(int id) {
		return find.where()
			.eq("id", id)
			.findUnique();
	}
	
	public static List<Role> all() {
		return find.all();
	}
}