package dummymodels.reports;

import java.util.Date;

import play.data.format.Formats.DateTime;
import play.data.validation.Constraints.Required;
import services.AppConstants;

public class BreakReport {
	
	public Integer sol;

	@Required
	@DateTime(pattern=AppConstants.REPORT_DEFAULT_DATE_FORMAT_JAVA)
	public Date startDate;
	
	@Required
	@DateTime(pattern=AppConstants.REPORT_DEFAULT_DATE_FORMAT_JAVA)
	public Date endDate;
	
	public String username;
	
	public Boolean isPdf;
}
