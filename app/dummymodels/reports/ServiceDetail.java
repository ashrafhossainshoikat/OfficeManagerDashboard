package dummymodels.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import models.task.TaskType;
import play.data.format.Formats.DateTime;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.Required;
import services.AppConstants;

public class ServiceDetail {
	
	//16-11-2014 00:00
	@Required
	@DateTime(pattern=AppConstants.REPORT_DEFAULT_DATE_FORMAT_JAVA)
	public Date startDate;
	
	@Required
	@DateTime(pattern=AppConstants.REPORT_DEFAULT_DATE_FORMAT_JAVA)
	public Date endDate;
	
	public String username;
	
	public Boolean isBssoCounter;
	
	public Integer taskTypeId;
	
	public Boolean isPdf;
	
	@Transient
	public List<ValidationError> validate() {
		List<ValidationError> errors = null;
		
		if(startDate.getTime() > endDate.getTime()) {
			if (errors == null) { errors = new ArrayList<ValidationError>(); }
			errors.add(new ValidationError("endDate", "End Date must be later than Start Date"));
		}
		
		return errors;
	}
}
