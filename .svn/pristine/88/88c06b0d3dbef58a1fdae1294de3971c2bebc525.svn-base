package services;

import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import java.io.File;

import dummymodels.reports.BreakReport;
import dummymodels.reports.LiveInterfaceView;
import dummymodels.reports.ServiceDetail;
import dummymodels.reports.ServiceWiseReport;
import dummymodels.reports.StartAndEndDate;
import play.Play;
import play.mvc.Controller;
import play.mvc.Result;
import models.customer.CustomerServiceTask;
import models.task.TaskType;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;

public class ReportGenerator extends Controller {

	// Service Detailed Report
	public static String setElementsForSdr(ServiceDetail serviceDetail) {

		HashMap<String, Object> jasperParameter = new HashMap<>();

		jasperParameter.put("start_date", (serviceDetail.startDate == null) ? "" : ScalaUtils.formatDateTime(serviceDetail.startDate));
		jasperParameter.put("end_date", (serviceDetail.endDate == null) ? "" : ScalaUtils.formatDateTime(serviceDetail.endDate));
		jasperParameter.put("user", serviceDetail.username);
		jasperParameter.put("service", (serviceDetail.taskTypeId == null) ? "" : TaskType.get(serviceDetail.taskTypeId).taskName);
		jasperParameter.put("counter", (serviceDetail.isBssoCounter == null) ? "All" : ((serviceDetail.isBssoCounter) ? "BSSO" : "CCSO"));

		String[] columnNames = { "service_name", "issue_date", "token_no", "counter_type", "issue_time", "start_time", "waiting_time", "end_time",
				"tat" };

		String reportFileName = AppConstants.FILE_NAME_SERVICE_DETAILS_REPORT;

		boolean reportType = serviceDetail.isPdf;

		String[][] dataSet = CustomerServiceTask.reportServiceDetail(serviceDetail);

		String outputFileName = createReport(reportFileName, reportType, jasperParameter, columnNames, dataSet);

		return outputFileName;

	}

	// Pending Token Report
	public static String setElementsForPtr(StartAndEndDate startAndEndDate) {

		HashMap<String, Object> jasperParameter = new HashMap<>();

		jasperParameter.put("start_date", (startAndEndDate.startDate == null) ? "" : ScalaUtils.formatDateTime(startAndEndDate.startDate));
		jasperParameter.put("end_date", (startAndEndDate.endDate == null) ? "" : ScalaUtils.formatDateTime(startAndEndDate.endDate));

		String[] columnNames = { "outlet_name", "date", "token_no", "service_name", "user", "counter_type", "benchmark_time", "issue_time",
				"call_time", "wait_time" };

		String reportFileName = AppConstants.FILE_NAME_PENDING_TOKEN_REPORT;

		boolean reportType = startAndEndDate.isPdf;

		String[][] dataSet = CustomerServiceTask.reportPendingToken(startAndEndDate);

		String outputFileName = createReport(reportFileName, reportType, jasperParameter, columnNames, dataSet);

		return outputFileName;
	}

	// Service Wise Report
	public static String setElementsForSwr(ServiceWiseReport serviceWiseReport) {

		HashMap<String, Object> jasperParameter = new HashMap<>();
		jasperParameter.put("outletName", serviceWiseReport.sol);
		jasperParameter.put("counter", (serviceWiseReport.isBssoCounter == null) ? "All" : ((serviceWiseReport.isBssoCounter) ? "BSSO" : "CCSO"));

		String[] columnNames = { "name_of_service", "number", "time" };

		String reportFileName = AppConstants.FILE_NAME_SERVICE_WISE_REPORT;

		boolean reportType = serviceWiseReport.isPdf;

		String[][] dataSet = CustomerServiceTask.reportServiceWiseReport(serviceWiseReport);
		String outputFileName = createReport(reportFileName, reportType, jasperParameter, columnNames, dataSet);
		return outputFileName;

	}

	// Counter Wise Summary
	public static String setElementsForCws(StartAndEndDate startAndEndDate) {

		HashMap<String, Object> jasperParameter = new HashMap<>();

		jasperParameter.put("start_date", (startAndEndDate.startDate == null) ? "" : ScalaUtils.formatDateTime(startAndEndDate.startDate));
		jasperParameter.put("end_date", (startAndEndDate.endDate == null) ? "" : ScalaUtils.formatDateTime(startAndEndDate.endDate));

		String[] columnNames = { "outlet_name", "month", "counter", "services", "tokens", "benchmark_time", "average_service_time",
				"average_waiting_time" };

		String reportFileName = AppConstants.FILE_NAME_COUNTER_WISE_SUMMARY;

		boolean reportType = startAndEndDate.isPdf;

		String[][] dataSet = CustomerServiceTask.reportCounterWiseSummary(startAndEndDate);

		String outputFileName = createReport(reportFileName, reportType, jasperParameter, columnNames, dataSet);
		return outputFileName;
	}

	// CCSO Transaction Report
	public static String setElementsForCtr(StartAndEndDate startAndEndDate) {

		HashMap<String, Object> jasperParameter = new HashMap<>();

		jasperParameter.put("start_date", (startAndEndDate.startDate == null) ? "" : ScalaUtils.formatDateTime(startAndEndDate.startDate));
		jasperParameter.put("end_date", (startAndEndDate.endDate == null) ? "" : ScalaUtils.formatDateTime(startAndEndDate.endDate));

		String[] columnNames = { "outlet_name", "user_name", "service_name", "benchmark_time", "actual_time", "deviation", "token", "date_time" };

		String reportFileName = AppConstants.FILE_NAME_CCSO_TRANSACTION_REPORT;

		boolean reportType = startAndEndDate.isPdf;

		String[][] dataSet = CustomerServiceTask.reportCcsoTransaction(startAndEndDate);

		String outputFileName = createReport(reportFileName, reportType, jasperParameter, columnNames, dataSet);
		return outputFileName;
	}

	// Live Interface View
	public static String setElementsForLiv(LiveInterfaceView filter) {
		try {
			JasperReport jasperReport;
			JasperPrint jasperPrint;
			
			HashMap<String, Object> jasperParameter = new HashMap<>();
			jasperParameter.put("tm1", new DefaultTableModel(CustomerServiceTask.reportLiveInterfaceViewSub1(filter),
												new String[] { "category", "visitor_type", "token_served", "avg_waiting_time" }));
			
			jasperParameter.put("tm2", new DefaultTableModel(CustomerServiceTask.reportLiveInterfaceViewSub2(filter),
					new String[] { "category", "visitor_type", "services" }));
			
			jasperParameter.put("tm3", new DefaultTableModel(CustomerServiceTask.reportLiveInterfaceViewSub3(filter),
					new String[] { "services", "total", "served", "serving", "waiting", "cancelled" }));
			
			jasperParameter.put("token_no", ((filter.cardNumber==null) ? "" : filter.cardNumber));
			jasperParameter.put("SUBREPORT_DIR", Play.application().getFile(AppConstants.REPORT_TEMPLATE_PATH).getAbsolutePath()+File.separator);
	
			String reportFileName = AppConstants.FILE_NAME_LIVE_INTERFACE_VIEW;
			
			String JrxmlFileName = JavaUtils.getReportTemplatePath(AppConstants.FILE_NAME_LIVE_INTERFACE_VIEW, filter.isPdf);
			String JrxmlFileName1 = JavaUtils.getReportTemplatePath(AppConstants.FILE_NAME_LIVE_INTERFACE_VIEW_SUBREPORT1, filter.isPdf);
			String JrxmlFileName2 = JavaUtils.getReportTemplatePath(AppConstants.FILE_NAME_LIVE_INTERFACE_VIEW_SUBREPORT2, filter.isPdf);
			String JrxmlFileName3 = JavaUtils.getReportTemplatePath(AppConstants.FILE_NAME_LIVE_INTERFACE_VIEW_SUBREPORT3, filter.isPdf);
			
			// jrxml compiling process
			jasperReport = JasperCompileManager.compileReport(JrxmlFileName);
			JasperCompileManager.compileReport(JrxmlFileName1);
			JasperCompileManager.compileReport(JrxmlFileName2);
			JasperCompileManager.compileReport(JrxmlFileName3);
	
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, new JREmptyDataSource());
			String generatedFileName = reportFileName +  "-" + JavaUtils.getCurrentUsername() + "-" + JavaUtils.currentDateTime();
			String outputFileName = JavaUtils.getReportPath(generatedFileName);
			if(filter.isPdf) {
				JasperExportManager.exportReportToPdfFile(jasperPrint, (outputFileName+AppConstants.FILE_EXT_PDF));
			} else {
				JRXlsExporter exporter = new JRXlsExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, (outputFileName+AppConstants.FILE_EXT_XLS));
				exporter.exportReport();
			}
			
			return generatedFileName;
			
		} catch(JRException e){
			e.printStackTrace();
			return null;
		}
	}

	// User Summary Profile
	public static String setElementsForUsp(StartAndEndDate startAndEndDate) {

		HashMap<String, Object> jasperParameter = new HashMap<>();

		jasperParameter.put("start_date", (startAndEndDate.startDate == null) ? "" : ScalaUtils.formatDateTime(startAndEndDate.startDate));
		jasperParameter.put("end_date", (startAndEndDate.endDate == null) ? "" : ScalaUtils.formatDateTime(startAndEndDate.endDate));

		String[] columnNames = { "outlet_name", "month", "counter", "services", "tokens", "benchmark_time", "avg_service_time", "avg_waiting_time" };

		String reportFileName = AppConstants.FILE_NAME_USER_SUMMEARY_PROFILE;

		boolean reportType = startAndEndDate.isPdf;

		String[][] dataSet = CustomerServiceTask.reportUserSummaryProfile(startAndEndDate);

		String outputFileName = createReport(reportFileName, reportType, jasperParameter, columnNames, dataSet);
		return outputFileName;

	}

	// Detailed Token Report
	public static String setElementsForDtr(StartAndEndDate startAndEndDate) {

		HashMap<String, Object> jasperParameter = new HashMap<>();

		jasperParameter.put("start_date", (startAndEndDate.startDate == null) ? "" : ScalaUtils.formatDateTime(startAndEndDate.startDate));
		jasperParameter.put("end_date", (startAndEndDate.endDate == null) ? "" : ScalaUtils.formatDateTime(startAndEndDate.endDate));

		String[] columnNames = { "outlet_name", "token_no", "service", "user", "date", "counter", "benchmark_time", "issue_time", "call_time",
				"served_time", "end_time", "waiting_time" };

		String reportFileName = AppConstants.FILE_NAME_DETAILED_TOKEN_REPORT;

		boolean reportType = startAndEndDate.isPdf;

		String[][] dataSet = CustomerServiceTask.reportUserSummaryProfile(startAndEndDate);

		String outputFileName = createReport(reportFileName, reportType, jasperParameter, columnNames, dataSet);
		return outputFileName;
	}

	// Break Report
	public static String setElementsForBr(BreakReport breakReport) {

		HashMap<String, Object> jasperParameter = new HashMap<>();

		jasperParameter.put("start_date", (breakReport.startDate == null) ? "" : ScalaUtils.formatDateTime(breakReport.startDate));
		jasperParameter.put("end_date", (breakReport.endDate == null) ? "" : ScalaUtils.formatDateTime(breakReport.endDate));
		jasperParameter.put("outletName", breakReport.sol);
		jasperParameter.put("userName", breakReport.username);

		String[] columnNames = { "outlet_name", "date", "user", "counter", "reason_for_break", "start_time", "end_time" };

		String reportFileName = AppConstants.FILE_NAME_BREAK_REPORT;

		boolean reportType = breakReport.isPdf;

		String[][] dataSet = CustomerServiceTask.reportBreakReport(breakReport);

		String outputFileName = createReport(reportFileName, reportType, jasperParameter, columnNames, dataSet);
		return outputFileName;
	}

	private static String createReport(String reportFileName, boolean isPdf, HashMap<String, Object> jasperParameter, String[] columnNames,
			String[][] data) {

		JasperReport jasperReport;
		JasperPrint jasperPrint;
		boolean isSuccess;
		String fileNameWithoutExt = reportFileName + "-" + JavaUtils.getCurrentUsername() + "-" + JavaUtils.currentDateTime();

		//String templateFileNameFlag = (isPdf) ? "_pdf" : "_xls";

		try {
			// compiled JRXML file path & Name
//			String JrxmlFileName = Play.application()
//					.getFile(AppConstants.REPORT_TEMPLATE_PATH + reportFileName + templateFileNameFlag + AppConstants.FILE_EXT_JRXML)
//					.getAbsolutePath();
			String jrxmlFileName = JavaUtils.getReportTemplatePath(reportFileName, isPdf);
			// jrxml compiling process

			// TODO enhance to use compiled .jasper file
			jasperReport = JasperCompileManager.compileReport(jrxmlFileName);

			// filling report with data from data source
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, new JRTableModelDataSource(new DefaultTableModel(data,
					columnNames)));

			if (jasperReport == null)
				System.out.println("-------------null");
			if (jasperPrint == null)
				System.out.println("--+++++++++++++++++++------null");

			if (isPdf) {
				isSuccess = createPdfReport(jasperPrint, fileNameWithoutExt);
			} else {
				isSuccess = createXlsReport(jasperPrint, fileNameWithoutExt);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		if (isSuccess)
			return fileNameWithoutExt;
		else
			return null;
	}

	private static boolean createPdfReport(JasperPrint jasperPrint, String pdfFileName) {
		String fileName = Play.application().getFile(AppConstants.PATH_OF_PUBLIC_DOWNLOAD_FOLDER + pdfFileName + AppConstants.FILE_EXT_PDF)
				.getAbsolutePath();

		try {
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
		} catch (JRException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private static boolean createXlsReport(JasperPrint jasperPrint, String xlsFileName) {
		String fileName = Play.application().getFile(AppConstants.PATH_OF_PUBLIC_DOWNLOAD_FOLDER + xlsFileName + AppConstants.FILE_EXT_XLS)
				.getAbsolutePath();

		try {
			// export to Excel sheet
			JRXlsExporter exporter = new JRXlsExporter();
			System.out.println("--------> step1");
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			System.out.println("--------> step2");
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
			System.out.println("--------> step3");
			exporter.exportReport();
			System.out.println("--------> step4");
		} catch (JRException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
