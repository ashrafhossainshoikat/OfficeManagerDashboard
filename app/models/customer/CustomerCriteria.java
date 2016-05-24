package models.customer;

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

import models.geo.Region;
import play.data.validation.ValidationError;
import play.db.ebean.Model;


@Entity
public class CustomerCriteria extends Model {

	@Id
	public Integer id;

	/*@Version
	@Column(name = "edit_count", nullable=false)
	public int version = 1;*/

	public String criteriaName;
	
	@Column(nullable = false)
	public Date createDate;
	
	@Column(nullable = false)
	public String createdBy;
	
	public Date lastUpdateDate;
	
	public String lastUpdatedBy;

	@OneToMany(mappedBy = "customerCriteria")
	public List<CustomerHistory> customerHistories;
	// --------------------------------------------- //
	
	public static Finder<Integer, CustomerCriteria> find = new Finder(Integer.class, CustomerCriteria.class);
	
	@Transient
	public List<ValidationError> validate() {
		List<ValidationError> errors = null;
		
		CustomerCriteria o = find.where().eq("criteriaName", this.criteriaName).findUnique();
		if(o != null || (o.id != this.id)) {
			if (errors == null) { errors = new ArrayList<ValidationError>(); }
			errors.add(new ValidationError("criteriaName", "Criteria Name already exists"));
		}
		
		return errors;
	}
	

	/*public static void create(CustomerCriteria customerCriteria) {
		customerCriteria.save();
	}

	public static void update(CustomerCriteria customerCriteria) {
		customerCriteria.update();
	}

	public static List<CustomerCriteria> all() {
		return find.all();
	}*/

	public static CustomerCriteria get(int id) {
		return find.where().eq("id", id).findUnique();
	}
	
	public static Map<String, String> getAsMap() {
		LinkedHashMap<String, String> branchs = new LinkedHashMap<String, String>();
		for (CustomerCriteria cc : CustomerCriteria.find.orderBy("criteriaName").findList()) {
			branchs.put(cc.id.toString(), cc.criteriaName);
		}

		return branchs;
	}

}
