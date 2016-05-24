package services;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;


public class AppConstants {
	
	public static final String APP_NAME = "svmsk";
	public static final String NAME_SEPERATOR = "-";
	
	public static final String REQUEST_HEADER_BROWSER_KEY = "User-Agent";
	
	public static final String POST_FORM_USER_ID = "userId";
	
	public static final String NULL_DATE_STRING = "---";
	
	public static final String REPORT_DEFAULT_DATE_FORMAT_JS_FORMATTER = "dd-mm-yyyy HH:MM";
	public static final String REPORT_DEFAULT_DATE_FORMAT_JQ_DATEPICKER = "d-m-Y H:i";
	public static final String REPORT_DEFAULT_DATE_FORMAT_JAVA = "dd-MM-yyyy HH:mm";
	
	public static final String REPORT_DEFAULT_TIME_FORMAT_JAVA = "HH:MM:ss";
	
	
	
	public static final int REPORT_DEFAULT_TIME_STEP = 10;
	
	public static final String PATH_OF_PUBLIC_FOLDER_FOR_PDF="public/reports/jasper-template/pdf/";
	public static final String PATH_OF_PUBLIC_FOLDER_FOR_XLS="public/reports/jasper-template/excel/";
	public static final String REPORT_TEMPLATE_PATH="public/reports/jasper-template/";
	public static final String FILE_EXT_JRXML=".jrxml";
	public static final String FILE_EXT_PDF=".pdf";
	public static final String FILE_EXT_XLS=".xls";
	public static final String PATH_OF_PUBLIC_DOWNLOAD_FOLDER="/public/reports/downloadable/";
	
	
	public static final String FLASH_KEY_SEARCH_FORM_ERROR = "search_form_err";
	public static final String FLASH_SEARCH_FORM_ERROR_MSG = "Please, select at least one of the Search Criteria";
	public static final String FLASH_FILE_GEN_ERROR = "Sorry, there was some error to generate the report. Please try later.";
	
	public static final String DOWNLOADABLE_FILE_TYPE_PDF = "application/pdf";
	public static final String DOWNLOADABLE_FILE_TYPE_XLS = "application/xls";
	
	
	public static final String FILE_NAME_SERVICE_DETAILS_REPORT="service_detailed_report";
	//public static final String FILE_NAME_SERVICE_DETAILS_REPORT_XLS="service_detailed_report_xls";
	//public static final String PDF_NAME_SERVICE_DETAILS_REPORT="service_detailed_report";
	//public static final String XLS_NAME_SERVICE_DETAILS_REPORT="service_detailed_report";
	public static final String FILE_NAME_PENDING_TOKEN_REPORT="pending_token_report";
	//public static final String FILE_NAME_PENDING_TOKEN_LIST_XLS="pending_token_list_xls";
	//public static final String PDF_NAME_PENDING_TOKEN_LIST="pending_token_list";
	//public static final String XLS_NAME_PENDING_TOKEN_LIST="pending_token_list";
	public static final String FILE_NAME_SERVICE_WISE_REPORT="servicewise_report";
	//public static final String FILE_NAME_SERVICE_WISE_REPORT_XLS="servicewise_report_xls";
	//public static final String PDF_NAME_SERVICE_WISE_REPORT="servicewise_report";
	//public static final String XLS_NAME_SERVICE_WISE_REPORT="servicewise_report";
	public static final String FILE_NAME_COUNTER_WISE_SUMMARY="counter_wise_summary";
	//public static final String FILE_NAME_COUNTER_WISE_SUMMARY_XLS="counter_wise_summary_xls";
	//public static final String PDF_NAME_COUNTER_WISE_SUMMARY="counter_wise_summary";
	//public static final String XLS_NAME_COUNTER_WISE_SUMMARY="counter_wise_summary";
	public static final String FILE_NAME_CCSO_TRANSACTION_REPORT="ccso_transaction_report";
	//public static final String FILE_NAME_CCSO_TRANSACTION_REPORT_XLS="ccso_transaction_report_xls";
	//public static final String PDF_NAME_CCSO_TRANSACTION_REPORT="ccso_transaction_report";
	//public static final String XLS_NAME_CCSO_TRANSACTION_REPORT="ccso_transaction_report";
	public static final String FILE_NAME_LIVE_INTERFACE_VIEW="live_interface_view";
	public static final String FILE_NAME_LIVE_INTERFACE_VIEW_SUBREPORT1="live_interface_view_subreport1";
	public static final String FILE_NAME_LIVE_INTERFACE_VIEW_SUBREPORT2="live_interface_view_subreport2";
	public static final String FILE_NAME_LIVE_INTERFACE_VIEW_SUBREPORT3="live_interface_view_subreport3";

	//public static final String FILE_NAME_LIVE_INTERFACE_VIEW_XLS="live_interface_view_xls";
	//public static final String PDF_NAME_LIVE_INTERFACE_VIEW="live_interface_view";
	//public static final String XLS_NAME_LIVE_INTERFACE_VIEW="live_interface_view";
	public static final String FILE_NAME_USER_SUMMEARY_PROFILE="user_summary_profile";
	//public static final String FILE_NAME_USER_SUMMEARY_PROFILE_XLS="user_summary_profile_xls";
	//public static final String PDF_NAME_USER_SUMMEARY_PROFILE="user_summary_profile";
	//public static final String XLS_NAME_USER_SUMMEARY_PROFILE="user_summary_profile";
	public static final String FILE_NAME_DETAILED_TOKEN_REPORT="detailed_token_report";
	//public static final String FILE_NAME_DETAILED_TOKEN_REPORT_XLS="detailed_token_report_xls";
	//public static final String PDF_NAME_DETAILED_TOKEN_REPORT="detailed_token_report";
	//public static final String XLS_NAME_DETAILED_TOKEN_REPORT="detailed_token_report";
	public static final String FILE_NAME_BREAK_REPORT="break_report";
	//public static final String FILE_NAME_BREAK_REPORT_XLS="break_report_xls";
	//public static final String PDF_NAME_BREAK_REPORT="break_report";
	//public static final String XLS_NAME_BREAK_REPORT="break_report"
	//public static final String FILE_NAME_LIF="live_interface_view";
	//public static final String PDF_NAME_LIF="liview";
	

	public static final String SESN_USERNAME = "smekrishiuser";
	public static final String SESN_BRANCH_SOL = "smekrishisol";
	public static final String SESSION_PWD_USER = "pwd-user";
	public static final String FLASH_KEY_ERROR = "error";
	public static final String FLASH_KEY_INFO = "msg";
	public static final String FLASH_MESSAGE_ERROR_DATA_SAVE = "Sorry, the data could not be saved, please try again later or contact the Administrtor";
	
	public static final String FORM_FIELD_NAME_ROLE_ID = "roleId";
	public static final String FORM_FIELD_NAME_BRANCH_ID = "branchId";
	public static final String FORM_FIELD_NAME_REGION_ID = "regionId";
	public static final String FORM_FIELD_NAME_USER_ID = "userId";
	public static final String FORM_FIELD_NAME_TASK_TYPE_ID = "taskTypeId";
	public static final String FORM_FIELD_NAME_CARD_ID = "cardId";
	public static final String FORM_FIELD_NAME_CATEGORY_ID = "categoryId";
	
	public static final int PASSWORD_MIN_LENGTH = 8;
	public static final int PASSWORD_MAX_LENGTH = 12;
	public static final String PASSWORD_SALT = "KJotn156OIm987ui2@$@4&#%^$*";
	
	public static final String LOGIN_INVALID_USERNAME_OR_PASSWORD = "Username or password is incorrect. Please, check whether Caps-Lock is on.";
	public static final String LOGIN_USERNAME_EMPTY = "You must provide a valid username.";
	public static final String LOGIN_PASSWORD_EMPTY = "You must provide a valid password.";
	public static final String LOGIN_USERNAME_NOT_FOUND = "Provided username is not found.";
	public static final String LOGIN_INACTIVE_ACCOUNT = "Your account has been locked. Please contact with administrator to unlock the account";
	public static final String LOGIN_ACCOUNT_DISABLED = "Users account is currently inactive.";
	public static final String LOGIN_PASSWORD_MISMATCH = "Username exists, but password is wrong.";

	public static final String LOGGER_ERROR = "apperror";
	
	public static final String DEFAULT_MAIL_USER_NAME = "SDM Activation";
	public static final String DEFAULT_MAIL_USER_EMAIL = "sdm@bracits.com";
	public static final String DEFAULT_MAIL_VERIFY_URL = "http://10.100.13.122:9000/user/verify?vid=";
	
	public static final String TASK_STATUS_ASSIGNED="Assigned";
	public static final String TASK_STATUS_ACTIVE="Active";
	public static final String TASK_STATUS_PAUSED="Paused";
	public static final String TASK_STATUS_ENDED="Ended Successfully";
	public static final String TASK_STATUS_CANCELLED="Cancelled";
	
	public static final String TASK_EVENT_TYPE_START = "Start";
	public static final String TASK_EVENT_TYPE_PAUSE = "Pause";
	public static final String TASK_EVENT_TYPE_RESUME = "Resume";
	public static final String TASK_EVENT_TYPE_REASSIGN = "Reassign";
	public static final String TASK_EVENT_TYPE_ASSIGN = "Assign";
	public static final String TASK_EVENT_TYPE_CANCEL = "Cancel";
	public static final String TASK_EVENT_TYPE_COMPLETE = "Complete";
	
	public static final String CUSTOMER_STATUS_IN_QUEUE = "In Queue";
	public static final String CUSTOMER_STATUS_AT_SERVIVE_DESK = "At Service Desk";
	public static final String CUSTOMER_STATUS_IN_SERVICE = "In Service";
	public static final String CUSTOMER_STATUS_SERVICE_PAUSED = "Service Paused";
	public static final String CUSTOMER_STATUS_IN_QUEUE_BETWEEN_SERVICES = "In Queue Between Services";
	public static final String CUSTOMER_STATUS_PUNCED_OUT = "Punched Out";
	
	public static final String APPROVAL_STATUS_SENT_FOR_APPROVAL = "Sent for Approval";
	public static final String APPROVAL_STATUS_SENT_BACK_TO_MAKER = "Sent Back to Maker";
	public static final String APPROVAL_STATUS_RESENT_FOR_APPROVAL = "Resent for Approval";
	public static final String APPROVAL_STATUS_APPROVED = "Approved";
	public static final String APPROVAL_STATUS_REJECTED = "Rejected";
	
	public static final String AUDIT_LOG_ACCOUNT_VERIFIED = "Account Verified";
	public static final String AUDIT_LOG_PASSWORD_CHANGE = "Password Change (Own)";
	public static final String AUDIT_LOG_PASSWORD_RESET = "Password Reset (Other)";
	public static final String AUDIT_LOG_ACTION_LOGIN = "Login";
	public static final String AUDIT_LOG_ACTION_LOGOUT = "Logout";
	public static final String AUDIT_LOG_ACTION_INSERT = "Insert";
	public static final String AUDIT_LOG_ACTION_UPDATE = "Update";
	public static final String AUDIT_LOG_ACTION_INSERT_APPROVED = "Insert Approved";
	public static final String AUDIT_LOG_ACTION_UPDATE_APPROVED = "Update Approved";
	
	public static final String ERR_MSG_FINACLE_ERROR = "Finacle is not responding. Please, Try later.";
	public static final String ERR_MSG_XML_PARSE = "XML response error";
	public static final String ERR_MSG_ELEMENT_CAST = "There exists no data according to your provided account number. Please, enter the correct account number.";
	public static final String ERR_MSG_AJAX_RESPONSE = "Sorry, there was an error processing your request. Please try again after some time";
	
	public static final String RESPONSE_MSG_SUCCESS="Success";
	public static final String RESPONSE_MSG_FAILURE="Failure";
	public static final String FLASH_MSG_CUST_HIST_UPDATE_SUCCESS="Customer infromation has been updated successfully";
	public static final String FLASH_MSG_CUST_HIST_UPDATE_FAILURE="Sorry, Customer infromation could not be updated, please try again";
	
	public static final SimpleDateFormat SDF_DEFAULT_DATETIME = JavaUtils.getSdf("yyyyMMddHHmmss");
	public static final SimpleDateFormat SDF_DEFAULT_DATE = JavaUtils.getSdf("yyyy-MM-dd");
	public static final SimpleDateFormat SDF_DEFAULT_TIME = JavaUtils.getSdf("HH:mm:ss");
	
	public static final int ROLE_ID_BSSO = 125;
	public static final int ROLE_ID_CCSO = 126;
	public static final int ROLE_ID_BRANCH_MANAGER = 127;
	public static final int ROLE_ID_REGIONAL_MANAGER = 128;
	public static final int ROLE_ID_HEAD_OF_SME = 129;
	public static final int ROLE_ID_MAKER = 130;
	public static final int ROLE_ID_CHECKER = 131;
	
	public static final int MENU_FLAG_TASK_ENTRY = 1;
	public static final int MENU_FLAG_BACK_OFFICE = 2;
	public static final int MENU_FLAG_REPORT = 3;
	
	public static final String ACTION_DEADBOLT_NAME_TASK_ENTRY_PAGE = "sdm.CustomerAndTaskManagement.homepage";
	
	public static final String ACTION_DEADBOLT_NAME_TASK_CREATE = "sdm.TaskTypeManagement.create";
	public static final String ACTION_DEADBOLT_NAME_TASK_VIEW = "sdm.TaskTypeManagement.view";
	public static final String ACTION_DEADBOLT_NAME_TASK_UPDATE = "sdm.TaskTypeManagement.update";
	public static final String ACTION_DEADBOLT_NAME_TASK_APPROVE = "sdm.TaskTypeManagement.approve";
	
	public static final String ACTION_DEADBOLT_NAME_CARD_CREATE = "sdm.CardManagement.create";
	public static final String ACTION_DEADBOLT_NAME_CARD_VIEW = "sdm.CardManagement.view";
	public static final String ACTION_DEADBOLT_NAME_CARD_UPDATE = "sdm.CardManagement.update";
	public static final String ACTION_DEADBOLT_NAME_CARD_APPROVE = "sdm.CardManagement.approve";
	
	public static final String ACTION_DEADBOLT_NAME_CATEGORY_CREATE = "sdm.CategoryManagement.create";
	public static final String ACTION_DEADBOLT_NAME_CATEGORY_VIEW = "sdm.CategoryManagement.view";
	public static final String ACTION_DEADBOLT_NAME_CATEGORY_UPDATE = "sdm.CategoryManagement.update";
	public static final String ACTION_DEADBOLT_NAME_CATEGORY_APPROVE = "sdm.CategoryManagement.approve";
	
	public static final String ACTION_DEADBOLT_NAME_REGION_CREATE = "sdm.RegionManagement.create";
	public static final String ACTION_DEADBOLT_NAME_REGION_VIEW = "sdm.RegionManagement.view";
	public static final String ACTION_DEADBOLT_NAME_REGION_UPDATE = "sdm.RegionManagement.update";
	public static final String ACTION_DEADBOLT_NAME_REGION_APPROVE = "sdm.RegionManagement.approve";
	
	public static final String ACTION_DEADBOLT_NAME_SERVICE_DETAILED_REPORT = "sdm.ReportManagement.serviceDetailedReport";
	public static final String ACTION_DEADBOLT_NAME_PENDING_TOKEN_REPORT = "sdm.ReportManagement.pendingTokenReport";
	public static final String ACTION_DEADBOLT_NAME_SERVICE_WISE_REPORT = "sdm.ReportManagement.serviceWiseReport";
	public static final String ACTION_DEADBOLT_NAME_COUNTER_WISE_SUMMARY= "sdm.ReportManagement.counterWiseSummary";
	public static final String ACTION_DEADBOLT_NAME_CCSO_TRANSACTION_REPORT = "sdm.ReportManagement.ccsoTransactionReport";
	public static final String ACTION_DEADBOLT_NAME_LIVE_INTERFACE_VIEW= "sdm.ReportManagement.liveInterfaceView";
	public static final String ACTION_DEADBOLT_NAME_USER_SUMMARY_PROFILE = "sdm.ReportManagement.userSummaryProfile";
	public static final String ACTION_DEADBOLT_NAME_DETAIL_TOKEN_REPORT = "sdm.ReportManagement.detailedTokenReport";
	public static final String ACTION_DEADBOLT_NAME_BREAK_REPORT = "sdm.ReportManagement.breakReport";

	public static final List<String> ACTION_DEADBOLT_NAME_LIST_TASK_ENTRY = Arrays.asList(new String[]{ACTION_DEADBOLT_NAME_TASK_ENTRY_PAGE});
	
	public static final List<String> ACTION_DEADBOLT_NAME_LIST_BACK_OFFICE = Arrays.asList(new String[]{
																				ACTION_DEADBOLT_NAME_TASK_CREATE
																				, ACTION_DEADBOLT_NAME_TASK_VIEW
																				, ACTION_DEADBOLT_NAME_TASK_UPDATE
																				, ACTION_DEADBOLT_NAME_TASK_APPROVE
																				, ACTION_DEADBOLT_NAME_CARD_CREATE
																				, ACTION_DEADBOLT_NAME_CARD_VIEW
																				, ACTION_DEADBOLT_NAME_CARD_UPDATE
																				, ACTION_DEADBOLT_NAME_CARD_APPROVE
																				, ACTION_DEADBOLT_NAME_CATEGORY_CREATE
																				, ACTION_DEADBOLT_NAME_CATEGORY_VIEW
																				, ACTION_DEADBOLT_NAME_CATEGORY_UPDATE
																				, ACTION_DEADBOLT_NAME_CATEGORY_APPROVE
																				, ACTION_DEADBOLT_NAME_REGION_CREATE
																				, ACTION_DEADBOLT_NAME_REGION_VIEW
																				, ACTION_DEADBOLT_NAME_REGION_UPDATE
																				, ACTION_DEADBOLT_NAME_REGION_APPROVE
																			});
	
	public static final List<String> ACTION_DEADBOLT_NAME_LIST_REPORT = Arrays.asList(new String[]{
																			ACTION_DEADBOLT_NAME_SERVICE_DETAILED_REPORT 
																			, ACTION_DEADBOLT_NAME_PENDING_TOKEN_REPORT
																			, ACTION_DEADBOLT_NAME_SERVICE_WISE_REPORT
																			, ACTION_DEADBOLT_NAME_COUNTER_WISE_SUMMARY
																			, ACTION_DEADBOLT_NAME_CCSO_TRANSACTION_REPORT 
																			, ACTION_DEADBOLT_NAME_LIVE_INTERFACE_VIEW
																			, ACTION_DEADBOLT_NAME_USER_SUMMARY_PROFILE
																			, ACTION_DEADBOLT_NAME_DETAIL_TOKEN_REPORT
																			, ACTION_DEADBOLT_NAME_BREAK_REPORT
																		});
	
	
}
