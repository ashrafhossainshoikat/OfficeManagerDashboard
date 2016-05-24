package models.geo;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;

import models.task.TaskCategory;
import connectplus.models.User;
import connectplus.models.geo.Branch;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import services.JavaUtils;

@Entity
public class Region extends Model {

	@Id
	public Integer id;

	@Version
	@Column(name = "edit_count", nullable = false)
	public int version = 1;
	
	@Required
	@Column(unique=true)
	public String zoneId;

	@Required
	@Column(unique=true)
	public String name;

	//public String regionCode;

	//public String managerName;

	@Column(nullable = false)
	public boolean isActive;

	@Column(nullable = false)
	public boolean isApproved; // ** for checker

	@Column(nullable = false)
	public Date createDate;

	@Column(nullable = false)
	public String createdBy;

	public Date lastUpdateDate;

	public String lastUpdatedBy;

	//@OneToMany(mappedBy = "region")
	//public List<User> users;

	//@OneToMany(mappedBy = "region")
	//public List<Branch> branches;
	// ** ---------------------------------------------

	public static Finder<Integer, Region> find = new Finder(Integer.class, Region.class);
	
	@Transient
	public List<ValidationError> validate() {
		List<ValidationError> errors = null;
		//try{
		Region o = find.where().eq("name", this.name).findUnique();
		if((o != null) && ((this.id == null) ||(this.id!=null && o.id!=this.id))) {
			if (errors == null) { errors = new ArrayList<ValidationError>(); }
			errors.add(new ValidationError("name", "Name already exists"));
		}
		
		o = find.where().eq("zoneId", this.zoneId).findUnique();
		if((o != null) && ((this.id == null) ||(this.id!=null && o.id!=this.id))) {
			if (errors == null) { errors = new ArrayList<ValidationError>(); }
			errors.add(new ValidationError("zoneId", "zone Id already exists"));
		//}} catch(Exception e){
		//	e.printStackTrace();
		}
		
		return errors;
	}

	public void create() {
		this.createDate = new Date();
		//this.createdBy = JavaUtils.getCurrentUsername();
		this.createdBy = "SYSTEM";
		super.save();
	}

	public static void create(Region region) {
		region.create();
	}
	
	@Override
	public void save() {
		this.create();
	}
	
	@Override
	public void update() {
		this.lastUpdateDate = new Date();
		this.lastUpdatedBy = "SYSTEM";
		
		super.update();
	}

	public static List<Region> all() {
		return find.all();
	}

	public static Region get(Integer id) {
		return find.byId(id);
	}
	
	public static Region findByZoneID(String zoneId) {
		return find.where().eq("zoneId", zoneId).findUnique();
	}
	
	public List<Branch> getBranches() {
		return BranchInRegion.findBranchesByRegion(this.id);
		
	}

	public static Map<String, String> getAsMap() {
		LinkedHashMap<String, String> regions = new LinkedHashMap<String, String>();
		for (Region region : Region.find.orderBy("name").findList()) {
			regions.put(region.id.toString(), region.name);
		}

		return regions;
	}

	public static List<Region> allUnapproved() {
		return find.where().eq("isApproved", false).orderBy("name").findList();
	}

	public static boolean approveById(int id) {
		Region region = get(id);
		if (region == null)
			return false;

		try {
			region.isApproved = true;
			region.update();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
}
