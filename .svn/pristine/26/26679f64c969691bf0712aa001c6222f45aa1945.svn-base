package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import be.objectify.deadbolt.java.actions.Dynamic;
import be.objectify.deadbolt.java.actions.SubjectNotPresent;
import be.objectify.deadbolt.java.actions.SubjectPresent;
import connectplus.services.deadbolt.DeadboltHandler;
import dummymodels.reports.ServiceDetail;
import dummymodels.reports.ServiceWiseReport;
import dummymodels.reports.StartAndEndDate;
import dummymodels.reports.BreakReport;
import dummymodels.reports.LiveInterfaceView;
import models.customer.CustomerServiceTask;
import models.punchcard.Card;
import net.sf.jasperreports.engine.Renderable;
import play.Play;
import play.data.Form;
import play.mvc.Call;
import play.mvc.Controller;
import play.mvc.Result;
import services.JavaUtils;
import services.ReportGenerator;
import services.AppConstants;
import views.html.reportView.*;

public class ReportManagement extends Controller {

	static Form<ServiceDetail> sdrForm = Form.form(ServiceDetail.class);
	static Form<StartAndEndDate> ptrForm = Form.form(StartAndEndDate.class);
	static Form<ServiceWiseReport> swrForm = Form.form(ServiceWiseReport.class);
	static Form<StartAndEndDate> cwsForm = Form.form(StartAndEndDate.class);

	static Form<StartAndEndDate> ctrForm = Form.form(StartAndEndDate.class);
	static Form<StartAndEndDate> uspForm = Form.form(StartAndEndDate.class);
	static Form<StartAndEndDate> dtrForm = Form.form(StartAndEndDate.class);
	static Form<BreakReport> brForm = Form.form(BreakReport.class);

	static Form<LiveInterfaceView> livForm = Form.form(LiveInterfaceView.class);

	//@SubjectPresent(handler = DeadboltHandler.class)
	public static Result menu() {
		return ok(reportMenu.render());
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_SERVICE_DETAILED_REPORT, handler = DeadboltHandler.class)
	public static Result searchServiceDetailedReport() {
		return ok(servicedetailedreport.render(sdrForm, null));
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_SERVICE_DETAILED_REPORT, handler = DeadboltHandler.class)
	public static Result generateServiceDetailedReport() {

		Form<ServiceDetail> filledForm = sdrForm.bindFromRequest();

		if (filledForm.hasErrors()) {
			return badRequest(servicedetailedreport.render(filledForm, null));
		} else {
			ServiceDetail serviceDetail = filledForm.get();
			if (serviceDetail.isPdf == null) {
				String[][] dataSet = CustomerServiceTask.reportServiceDetail(serviceDetail);
				return ok(servicedetailedreport.render(filledForm, dataSet));
			} else {
				String outputFileName = null;
				outputFileName = ReportGenerator.setElementsForSdr(serviceDetail);
				String outputFileNameWithExt = outputFileName + (serviceDetail.isPdf ? AppConstants.FILE_EXT_PDF : AppConstants.FILE_EXT_XLS);
				String outputFileType = (serviceDetail.isPdf ? AppConstants.DOWNLOADABLE_FILE_TYPE_PDF : AppConstants.DOWNLOADABLE_FILE_TYPE_XLS);
				return downloadReport(outputFileNameWithExt, outputFileType, (routes.ReportManagement.searchServiceDetailedReport()));

			}

		}
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_PENDING_TOKEN_REPORT, handler = DeadboltHandler.class)
	public static Result searchPendingTokenReport() {

		return ok(pendingtokenreport.render(ptrForm, null));

	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_PENDING_TOKEN_REPORT, handler = DeadboltHandler.class)
	public static Result generatePendingTokenReport() {

		Form<StartAndEndDate> filledForm = ptrForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(pendingtokenreport.render(filledForm, null));
		}

		else {
			StartAndEndDate startAndEndDate = filledForm.get();
			if (startAndEndDate.isPdf == null) {
				String[][] dataSet = CustomerServiceTask.reportPendingToken(startAndEndDate);
				return ok(pendingtokenreport.render(filledForm, dataSet));
			} else {
				String outputFileName = null;
				outputFileName = ReportGenerator.setElementsForPtr(startAndEndDate);
				String outputFileNameWithExt = outputFileName + (startAndEndDate.isPdf ? AppConstants.FILE_EXT_PDF : AppConstants.FILE_EXT_XLS);
				String outputFileType = (startAndEndDate.isPdf ? AppConstants.DOWNLOADABLE_FILE_TYPE_PDF : AppConstants.DOWNLOADABLE_FILE_TYPE_XLS);
				return downloadReport(outputFileNameWithExt, outputFileType, (routes.ReportManagement.searchPendingTokenReport()));

			}
		}
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_SERVICE_WISE_REPORT, handler = DeadboltHandler.class)
	public static Result searchServiceWiseReport() {

		return ok(servicewisereport.render(swrForm, null));

	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_SERVICE_WISE_REPORT, handler = DeadboltHandler.class)
	public static Result generateServiceWiseReport() {
		Form<ServiceWiseReport> filledForm = swrForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(servicewisereport.render(filledForm, null));
		}

		else {
			ServiceWiseReport serviceWiseReport = filledForm.get();
			if (serviceWiseReport.isPdf == null) {
				String[][] dataSet = CustomerServiceTask.reportServiceWiseReport(serviceWiseReport);
				return ok(servicewisereport.render(filledForm, dataSet));
			} else {
				String outputFileName = null;
				outputFileName = ReportGenerator.setElementsForSwr(serviceWiseReport);
				String outputFileNameWithExt = outputFileName + (serviceWiseReport.isPdf ? AppConstants.FILE_EXT_PDF : AppConstants.FILE_EXT_XLS);
				String outputFileType = (serviceWiseReport.isPdf ? AppConstants.DOWNLOADABLE_FILE_TYPE_PDF : AppConstants.DOWNLOADABLE_FILE_TYPE_XLS);
				return downloadReport(outputFileNameWithExt, outputFileType, (routes.ReportManagement.searchServiceWiseReport()));

			}
		}
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_COUNTER_WISE_SUMMARY, handler = DeadboltHandler.class)
	public static Result searchCounterWiseSummary() {

		return ok(counterwisesummary.render(cwsForm, null));

	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_COUNTER_WISE_SUMMARY, handler = DeadboltHandler.class)
	public static Result generateCounterWiseSummary() {
		Form<StartAndEndDate> filledForm = cwsForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(counterwisesummary.render(filledForm, null));
		}

		else {
			StartAndEndDate startAndEndDate = filledForm.get();
			String[][] dataSet = CustomerServiceTask.reportCounterWiseSummary(startAndEndDate);
			if (startAndEndDate.isPdf == null) {
				return ok(counterwisesummary.render(filledForm, dataSet));
			} else {
				String outputFileName = null;
				outputFileName = ReportGenerator.setElementsForCws(startAndEndDate);
				String outputFileNameWithExt = outputFileName + (startAndEndDate.isPdf ? AppConstants.FILE_EXT_PDF : AppConstants.FILE_EXT_XLS);
				String outputFileType = (startAndEndDate.isPdf ? AppConstants.DOWNLOADABLE_FILE_TYPE_PDF : AppConstants.DOWNLOADABLE_FILE_TYPE_XLS);
				return downloadReport(outputFileNameWithExt, outputFileType, (routes.ReportManagement.searchCounterWiseSummary()));
			}

		}
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_CCSO_TRANSACTION_REPORT, handler = DeadboltHandler.class)
	public static Result searchCcsoTransactionReport() {

		return ok(ccsotransactionreport.render(ctrForm, null));

	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_CCSO_TRANSACTION_REPORT, handler = DeadboltHandler.class)
	public static Result generateCcsoTransactionReport() {
		Form<StartAndEndDate> filledForm = ctrForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(ccsotransactionreport.render(filledForm, null));
		}

		else {
			StartAndEndDate startAndEndDate = filledForm.get();
			if (startAndEndDate.isPdf == null) {
				String[][] dataSet = CustomerServiceTask.reportCcsoTransaction(startAndEndDate);
				return ok(ccsotransactionreport.render(filledForm, dataSet));
			} else {

				String outputFileName = null;
				outputFileName = ReportGenerator.setElementsForCtr(startAndEndDate);
				String outputFileNameWithExt = outputFileName + (startAndEndDate.isPdf ? AppConstants.FILE_EXT_PDF : AppConstants.FILE_EXT_XLS);
				String outputFileType = (startAndEndDate.isPdf ? AppConstants.DOWNLOADABLE_FILE_TYPE_PDF : AppConstants.DOWNLOADABLE_FILE_TYPE_XLS);
				return downloadReport(outputFileNameWithExt, outputFileType, (routes.ReportManagement.searchCcsoTransactionReport()));
			}

		}
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_LIVE_INTERFACE_VIEW, handler = DeadboltHandler.class)
	public static Result searchLiveInterfaceView() {

		return ok(liveinterfaceview.render(livForm, null, null, null));

	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_LIVE_INTERFACE_VIEW, handler = DeadboltHandler.class)
	public static Result generateLiveInterfaceView() {
		Form<LiveInterfaceView> filledForm = livForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(liveinterfaceview.render(filledForm, null, null, null));
		}

		else {
			LiveInterfaceView liveInterfaceView = filledForm.get();
			if (liveInterfaceView.isPdf == null) {
				String[][] dataSet1 = CustomerServiceTask.reportLiveInterfaceViewSub1(liveInterfaceView);

				String[][] dataSet2 = CustomerServiceTask.reportLiveInterfaceViewSub2(liveInterfaceView);

				String[][] dataSet3 = CustomerServiceTask.reportLiveInterfaceViewSub3(liveInterfaceView);

				return ok(liveinterfaceview.render(filledForm, dataSet1, dataSet2, dataSet3));
			} else {

				String outputFileName = ReportGenerator.setElementsForLiv(liveInterfaceView);
				System.out.println("---------------> " + outputFileName);
				String outputFileNameWithExt = outputFileName + (liveInterfaceView.isPdf ? AppConstants.FILE_EXT_PDF : AppConstants.FILE_EXT_XLS);
				String outputFileType = (liveInterfaceView.isPdf ? AppConstants.DOWNLOADABLE_FILE_TYPE_PDF : AppConstants.DOWNLOADABLE_FILE_TYPE_XLS);
				return downloadReport(outputFileNameWithExt, outputFileType, (routes.ReportManagement.searchLiveInterfaceView()));
			}

		}
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_USER_SUMMARY_PROFILE, handler = DeadboltHandler.class)
	public static Result searchUserSummaryProfile() {

		return ok(usersummaryprofile.render(uspForm, null));

	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_USER_SUMMARY_PROFILE, handler = DeadboltHandler.class)
	public static Result generateUserSummaryProfile() {
		Form<StartAndEndDate> filledForm = uspForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(usersummaryprofile.render(filledForm, null));
		}

		else {
			StartAndEndDate startAndEndDate = filledForm.get();
			if (startAndEndDate.isPdf == null) {
				String[][] dataSet = CustomerServiceTask.reportUserSummaryProfile(startAndEndDate);
				return ok(usersummaryprofile.render(filledForm, dataSet));
			} else {

				String outputFileName = null;
				outputFileName = ReportGenerator.setElementsForUsp(startAndEndDate);
				String outputFileNameWithExt = outputFileName + (startAndEndDate.isPdf ? AppConstants.FILE_EXT_PDF : AppConstants.FILE_EXT_XLS);
				String outputFileType = (startAndEndDate.isPdf ? AppConstants.DOWNLOADABLE_FILE_TYPE_PDF : AppConstants.DOWNLOADABLE_FILE_TYPE_XLS);
				return downloadReport(outputFileNameWithExt, outputFileType, (routes.ReportManagement.searchUserSummaryProfile()));

			}

		}
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_DETAIL_TOKEN_REPORT, handler = DeadboltHandler.class)
	public static Result searchDetailedTokenReport() {

		return ok(detailedtokenreport.render(dtrForm, null));

	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_DETAIL_TOKEN_REPORT, handler = DeadboltHandler.class)
	public static Result generateDetailedTokenReport() {
		Form<StartAndEndDate> filledForm = dtrForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(detailedtokenreport.render(filledForm, null));
		}

		else {
			StartAndEndDate startAndEndDate = filledForm.get();

			if (startAndEndDate.isPdf == null) {
				String[][] dataSet = CustomerServiceTask.reportDetailedTokenReport(startAndEndDate);
				return ok(detailedtokenreport.render(filledForm, dataSet));
			} else {

				String outputFileName = null;
				outputFileName = ReportGenerator.setElementsForDtr(startAndEndDate);
				String outputFileNameWithExt = outputFileName + (startAndEndDate.isPdf ? AppConstants.FILE_EXT_PDF : AppConstants.FILE_EXT_XLS);
				String outputFileType = (startAndEndDate.isPdf ? AppConstants.DOWNLOADABLE_FILE_TYPE_PDF : AppConstants.DOWNLOADABLE_FILE_TYPE_XLS);
				return downloadReport(outputFileNameWithExt, outputFileType, (routes.ReportManagement.searchDetailedTokenReport()));

			}

		}
	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_BREAK_REPORT, handler = DeadboltHandler.class)
	public static Result searchBreakReport() {

		return ok(breakreport.render(brForm, null));

	}

	@Dynamic(value = AppConstants.ACTION_DEADBOLT_NAME_BREAK_REPORT, handler = DeadboltHandler.class)
	public static Result generateBreakReport() {
		Form<BreakReport> filledForm = brForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(breakreport.render(filledForm, null));
		} else {
			BreakReport breakReport = filledForm.get();
			if (breakReport.isPdf == null) {
				String[][] dataSet = CustomerServiceTask.reportBreakReport(breakReport);
				return ok(breakreport.render(filledForm, dataSet));
			} else {

				String outputFileName = null;
				outputFileName = ReportGenerator.setElementsForBr(breakReport);
				String outputFileNameWithExt = outputFileName + (breakReport.isPdf ? AppConstants.FILE_EXT_PDF : AppConstants.FILE_EXT_XLS);
				String outputFileType = (breakReport.isPdf ? AppConstants.DOWNLOADABLE_FILE_TYPE_PDF : AppConstants.DOWNLOADABLE_FILE_TYPE_XLS);
				return downloadReport(outputFileNameWithExt, outputFileType, (routes.ReportManagement.searchBreakReport()));
			}

		}
	}

	// method for download report
	private static Result downloadReport(String filename, String fileType, Call redirectUrl) {
		if (filename == null || filename.isEmpty())
			return downloadReportFailure(redirectUrl);

		try {
			return downloadReportSuccess(filename, fileType);
		} catch (FileNotFoundException e) {
			return downloadReportFailure(redirectUrl);
		}
	}

	// method for succesfully execute download option of report
	private static Result downloadReportSuccess(String fileNameWithExt, String fileType) throws FileNotFoundException {
		response().setContentType("application/x-download");
		response().setHeader("Content-disposition", "attachment; filename=" + fileNameWithExt);
		String filePath = JavaUtils.getReportPath(fileNameWithExt);

		return ok(new FileInputStream(filePath)).as(fileType);
	}

	// method for failure response to execute download option of report
	private static Result downloadReportFailure(Call callable) {
		response().getHeaders().remove("Content-disposition");
		response().setContentType("text/html");
		flash(AppConstants.FLASH_KEY_ERROR, AppConstants.FLASH_FILE_GEN_ERROR);
		// return redirect(routes.ReportManagement.searchBreakReport());
		return redirect(callable);

	}

}
