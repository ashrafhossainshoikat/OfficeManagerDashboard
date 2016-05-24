package models;

import java.util.Date;

import javax.persistence.Column;

import play.db.ebean.Model;


public abstract class BaseModel extends Model  {

	public Date createDate;
	
	public String createdBy;
	
	public String createCheckedBy;
	
	public String createCheckRemarks;
	
	@Column(nullable=false)
	public boolean isChecked;
	
	public Date lastUpdateDate;
	
	public String lastUpdatedBy;
	
	public String lastUpdateCheckedBy;
	
	@Column(length=1000)
	public String updateCheckRemarks;
	
	@Column(nullable=false)
	public boolean isActive;;
	
	@Column(nullable=false, name="edit_count")
	public int version = 0;
}