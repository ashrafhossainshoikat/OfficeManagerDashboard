package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

import play.Play;
import services.AppConstants;
import services.JavaUtils;
import services.ScalaUtils;
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

public class NewReportGenerator {

	public NewReportGenerator() {

		JasperReport jasperReport;
		JasperReport jasperReport1;
		JasperReport jasperReport2;
		JasperReport jasperReport3;
		JasperPrint jasperPrint;
		JasperPrint jasperPrint1;
		JasperPrint jasperPrint2;
		JasperPrint jasperPrint3;

		try {

			// String reportFileName =
			// AppConstants.FILE_NAME_LIVE_INTERFACE_VIEW_SUBREPORT1;

			String reportFileName = AppConstants.FILE_NAME_LIVE_INTERFACE_VIEW;

			String JrxmlFileName = "E:\\Workspaces\\j2ee-workspace\\service-delivery-model\\public\\reports\\jasper-template\\live_interface_view.jrxml";
			String JrxmlFileName1 = "E:\\Workspaces\\j2ee-workspace\\service-delivery-model\\public\\reports\\jasper-template\\live_interface_view_subreport1.jrxml";
			String JrxmlFileName2 = "E:\\Workspaces\\j2ee-workspace\\service-delivery-model\\public\\reports\\jasper-template\\live_interface_view_subreport2.jrxml";
			String JrxmlFileName3 = "E:\\Workspaces\\j2ee-workspace\\service-delivery-model\\public\\reports\\jasper-template\\live_interface_view_subreport3.jrxml";

			HashMap<String, Object> jasperParameter = new HashMap<>();

			//jasperParameter.put("token_no", "C005");
			jasperParameter.put("SUBREPORT_DIR", Play.application().getFile(AppConstants.PATH_OF_PUBLIC_FOLDER_FOR_PDF).getAbsolutePath());
			jasperParameter.put("tm1", getTableModelData1());
			jasperParameter.put("tm2", getTableModelData2());
			jasperParameter.put("tm3", getTableModelData3());

			// jrxml compiling process
			jasperReport = JasperCompileManager.compileReport(JrxmlFileName);
			jasperReport1 = JasperCompileManager.compileReport(JrxmlFileName1);
			jasperReport2 = JasperCompileManager.compileReport(JrxmlFileName2);
			jasperReport3 = JasperCompileManager.compileReport(JrxmlFileName3);
						
			jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, new JREmptyDataSource());

			// ** PDF
			String fileName = JavaUtils.getReportPath(reportFileName + AppConstants.FILE_EXT_PDF);
			
			JasperExportManager.exportReportToPdfFile(jasperPrint, fileName);
			
			// ** Excel
			/*
			String fileName = JavaUtils.getReportPath(reportFileName + AppConstants.FILE_EXT_XLS);
			JRXlsExporter exporter = new JRXlsExporter();
			SimpleXlsExporterConfiguration configuration = new SimpleXlsExporterConfiguration();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, fileName);
			exporter.exportReport();
			*/
			
		} catch (JRException ex) {
			ex.printStackTrace();
		}

	}

	private DefaultTableModel getTableModelData1() {
		String[] columnNames = { "category", "visitor_type", "token_served", "avg_waiting_time" };

		String[][] data = { { "Cash Withdrawl", "25-04-2014", "C005", "1" }, { "Account Opening", "26-04-2014", "C006", "1" },
				{ "Account Info", "27-04-2014", "S006", "1" }, { "Cash Info", "28-04-2014", "C009", "1" }, };

		return new DefaultTableModel(data, columnNames);
	}

	private DefaultTableModel getTableModelData2() {
		
		String[] columnNames = { "category", "visitor_type", "services" };
		
		String[][] data = { { "Cash Withdrawl", "25-04-2014", "C005" }, { "Account Opening", "26-04-2014", "C006" }, { "Account Info", "27-04-2014", "S006" },
				{ "Cash Info", "28-04-2014", "C009" }, };
		
		return new DefaultTableModel(data, columnNames);
	}
	
	private DefaultTableModel getTableModelData3() {

		String[] columnNames = { "services", "total", "served", "serving", "waiting", "cancelled" };

				String[][] data = {
						{ "Cash Withdrawldaddddddd", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Opening", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Joint Account", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Loan", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Internet Banking", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Withdrawldaddddddd", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Opening", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Joint Account", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Loan", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Internet Banking", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Withdrawldaddddddd", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Opening", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Joint Account", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Loan", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Internet Banking", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Withdrawldaddddddd", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Opening", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Joint Account", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Loan", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Internet Banking", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Withdrawldaddddddd", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Opening", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Joint Account", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Loan", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Internet Banking", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Withdrawldaddddddd", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Opening", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Joint Account", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Loan", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Internet Banking", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Withdrawldaddddddd", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Opening", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Joint Account", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Loan", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Internet Banking", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Withdrawldaddddddd", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Opening", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Joint Account", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Loan", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Internet Banking", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Withdrawldaddddddd", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Opening", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Joint Account", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Loan", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Internet Banking", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Withdrawldaddddddd", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Opening", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Joint Account", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Loan", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Internet Banking", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Withdrawldaddddddd", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Opening", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Joint Account", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Loan", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Internet Banking", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Withdrawldaddddddd", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Opening", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Account Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Cash Info", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Joint Account", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Loan", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" },
						{ "Internet Banking", "1", "12 PM", "12:15 PM", "15 Min", "12:45 PM" }
					};

		return new DefaultTableModel(data, columnNames);
	}
}
