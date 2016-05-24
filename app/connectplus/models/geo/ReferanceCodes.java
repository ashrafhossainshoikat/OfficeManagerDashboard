package connectplus.models.geo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;


@Entity
@Table(name="ReferanceCodes")
public class ReferanceCodes extends Model {

	@Id
	@Column(name = "RCID")
	public Integer id;
	
	@Column(name = "RCGroup")
	public Integer groupId;
	
	@Column(name = "Code")
	public String code;
	
	@Column(name = "ParentRCID")
	public Integer parentId;

	// --------------------------------------------- //
	private static Finder<Integer, ReferanceCodes> find = new Finder("connectplus", Integer.class, ReferanceCodes.class);

	@Override
	public void save() {}

	@Override
	public void update() {}

	public static List<ReferanceCodes> all() {
		return find.all();
	}

	public static ReferanceCodes get(int id) {
		return find.byId(id);
	}

}
