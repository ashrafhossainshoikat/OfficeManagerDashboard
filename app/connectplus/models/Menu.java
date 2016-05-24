package connectplus.models;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import java.util.*;

import javax.persistence.*;


@Entity
@Table(name="Menu")
public class Menu extends Model {
	@Id
	@Column(name="MenuId")
	public Integer id;
	
	@Column(name="ParentID")
	public Integer parentMenuId;
	
	@Column(name="PermissionID")
	public Integer permissionId;

	@Column(name="MenuText")
	public String name;
	
	@Column(name="NavigateUrl")
	public String menuUrl;
	
	@Column(name="MenuOrder")
	public Integer menuOrder;
	
	@Column(name="IsVisible")
	public Integer isVisible;

	private static Finder<Integer, Menu> find = new Finder("connectplus", Integer.class, Menu.class);
	
	public static Menu get(int id) {
		return find.where()
			.eq("id", id)
			.findUnique();
	}
	
	

}