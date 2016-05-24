package services;

import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.time.DateUtils;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

import play.Play;
import play.data.validation.ValidationError;
import play.libs.Json;
import play.mvc.Call;
import play.mvc.Http.Context;
import play.mvc.Http.Session;
import connectplus.models.Permission;
import connectplus.models.User;
import connectplus.models.geo.Branch;
import controllers.routes;


public class JavaUtils {
	public static String listToString(List<? extends Object> list, String separator, String header, String footer) {
		String delim = "";
		StringBuilder sb = new StringBuilder(header);

		for (int i = 0; i < list.size(); i++) {
			sb.append(delim).append("" + list.get(i));
			delim = separator;
		}

		return sb.append(footer).toString();
	}

	
	public static String listToString(List<? extends Object> list, String separator) {
		return listToString(list, separator, "", "");
	}

	
	public static String errorMapToString(Map<String, List<ValidationError>> errs) {
		String errMsg = "";
		String delim = "";
		for (Map.Entry<String, List<ValidationError>> entry : errs.entrySet()) {
			errMsg += delim + entry.getKey() + ": " + JavaUtils.listToString(entry.getValue(), ", ");
			delim = "\r\n";
		}

		return errMsg;
	}

	
	/** Converts XML Document to String */
	public static String documentToString(Document doc) {
		try {
			// DocumentBuilderFactory domFact =
			// DocumentBuilderFactory.newInstance();
			// DocumentBuilder builder = domFact.newDocumentBuilder();
			// Document doc = builder.parse(st);
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
		/*
		 * catch (ParserConfigurationException ex) { ex.printStackTrace();
		 * return null; }
		 */

	}

	
	public static boolean isUsernamePresent(String username) {
		User user = User.findByUsername(username);
		if (user != null)
			return true;

		return false;
	}

	
	//TODO implement well
	public static String getCurrentUsername() {
		User user = getCurrentUser();
		return ((user==null) ? "" : user.username);
	}
	
	
	public static User getCurrentUser() {
		Session session = Context.current().session();
		if (session != null) {
			String username = session.get(AppConstants.SESN_USERNAME);
			if (username != null && !username.trim().isEmpty()) {
				return User.findActiveUserByUsername(username);
			}
		}
		
		return null;
	}
	
	
	public static List<Permission> getCurrentUsersPermission() {
		User user = getCurrentUser();
		return ((user==null) ? new ArrayList<Permission>() : user.permissions());
	}
	
	public static boolean isCurrentUserBssoOrCcso() {
		User user = getCurrentUser();		
		return ((user == null) ? false : getCurrentUser().isBssoOrCcsoUser());
	}
	
	
	public static int getCurrentUserRoleId() {
		User user = getCurrentUser();
		return ((user==null) ? 0 : user.roleId);
	}
	
	
	public static Call getCurrentUserHomepage() {
		return getCurrentUserHomepage(getCurrentUser());
	}
	
	public static Call getCurrentUserHomepage(User user) {
		return getCurrentUserHomepage(user, user.getRoleIdList());
	}
	
	public static Call getCurrentUserHomepage(User user, List<Integer> roleIds) {
		if(user == null)
			return connectplus.controllers.routes.UserManagement.login();

		if(roleIds.contains(AppConstants.ROLE_ID_BSSO)
				|| roleIds.contains(AppConstants.ROLE_ID_CCSO)) {
			
			return routes.CustomerAndTaskManagement.homepage();
		}
		
		if(roleIds.contains(AppConstants.ROLE_ID_BRANCH_MANAGER)
				|| roleIds.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER)
				|| roleIds.contains(AppConstants.ROLE_ID_HEAD_OF_SME)) {
			
			return routes.ReportManagement.menu();
		}
		
		if(roleIds.contains(AppConstants.ROLE_ID_MAKER)
				|| roleIds.contains(AppConstants.ROLE_ID_CHECKER)) {
			
			return routes.Application.backOffice();
		}
		
		return connectplus.controllers.routes.UserManagement.login();
	}
	
	
	//TODO implement well
	public static int getCurrentUserBranchSolId() {
		/*
		User user = getCurrentUser();
		if (user==null || user.branchId==null)
			return 0;
		
		Session session = Context.current().session();
		if (session != null) {
			String sol = session.get(AppConstants.SESN_BRANCH_SOL);
			if (sol!=null) {
				try {
					int solAsInt = Integer.parseInt(sol);
					Branch b = Branch.findBySol(solAsInt);
					
					return ((b==null || !sol.equals(b.sol)) ? 0 : solAsInt);
					
				} catch(NumberFormatException e) {
					e.printStackTrace();
					return 0;
				}
			}
		}
		
		return 0;
		*/
		
		Session session = Context.current().session();
		String sol = session.get(AppConstants.SESN_BRANCH_SOL);
		if (sol!=null) {
			try {
				return Integer.parseInt(sol);
			} catch(NumberFormatException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return 0;
	}
	

	public static int getRandNumber() {
		return ((int) (Math.random() * 9999) + 1000);
	}

	
	public static boolean containsDigit(String input) {
		return input.matches(".*\\d.*");
	}

	
	public static boolean containsUpperCase(String input) {
		return input.matches(".*[A-Z].*");
	}

	
	public static boolean containsLowerCase(String input) {
		return input.matches(".*[a-z].*");
	}

	
	public static boolean isPwdFormatValid(String password) {
		boolean validity = ((password != null) && containsDigit(password) && containsLowerCase(password) && containsUpperCase(password)
				&& (password.length() >= AppConstants.PASSWORD_MIN_LENGTH) && (password.length() <= AppConstants.PASSWORD_MAX_LENGTH));

		return validity;
	}

	
	public static String generateJsonResponse(String obj) {
		if (obj == null || obj.isEmpty() || obj.trim().equals("null"))
			return "{\"success\":false,\"obj\":null}";
		else
			return "{\"success\":true,\"obj\":" + obj + "}";
	}

	
	public static ObjectNode generateJsonSuccessResponse(ObjectNode obj) {
		ObjectNode on = Json.newObject();
		on.put("success", true);
		on.put("obj", obj);

		return on;
	}

	
	public static ObjectNode generateJsonSuccessResponse(ArrayNode obj) {
		ObjectNode on = Json.newObject();
		on.put("success", true);
		on.put("obj", obj);
		return on;
	}
	
	public static ObjectNode generateJsonPunchOutFailureResponse(ArrayNode obj) {
		ObjectNode on = Json.newObject();
		on.put("success", false);
		on.put("obj", obj);
		return on;
	}

	
	public static ObjectNode generateJsonSuccessResponse(ArrayNode obj, ObjectNode recentTask) {
		ObjectNode on = Json.newObject();
		on.put("success", true);
		on.put("obj", obj);
		on.put("myTask", recentTask);

		return on;
	}

	
	public static ObjectNode generateJsonFailureResponse() {
		String nullStr = null;

		ObjectNode on = Json.newObject();
		on.put("success", false);
		on.put("obj", nullStr);
		return on;
	}
	
	
	public static ObjectNode generateJsonPunchOutSuccessResponse() {
		String nullStr = null;

		ObjectNode on = Json.newObject();
		on.put("success", true);
		on.put("obj", nullStr);
		return on;
	}
	
	
	public static ObjectNode generateFinacleResponseFailureMsgAsJson() {
		String nullStr = null;

		ObjectNode on = Json.newObject();
		on.put("success", false);
		on.put("error", AppConstants.ERR_MSG_FINACLE_ERROR);
		on.put("obj", nullStr);
		return on;
	}
	
	
	public static ObjectNode generateXmlResponseFailureMsgAsJson() {
		String nullStr = null;

		ObjectNode on = Json.newObject();
		on.put("success", false);
		on.put("error", AppConstants.ERR_MSG_XML_PARSE);
		on.put("obj", nullStr);
		return on;
	}
	
	
	public static ObjectNode generateElementResponseFailureMsgAsJson() {
		String nullStr = null;

		ObjectNode on = Json.newObject();
		on.put("success", false);
		on.put("error", AppConstants.ERR_MSG_ELEMENT_CAST);
		on.put("obj", nullStr);
		return on;
	}
	
	
	public static String XmlDocumentToStr(Document doc)    {
	    DOMImplementationLS domImplementation = (DOMImplementationLS) doc.getImplementation();
	    LSSerializer lsSerializer = domImplementation.createLSSerializer();
	    return lsSerializer.writeToString(doc);   
	}
	
	
	public static void printElements(Document doc) {
		//NodeList nl = doc.getElementsByTagName("*");
		NodeList nl = doc.getElementsByTagName("sp:ACCOUNT_NAME");
		Element nsElement;
		String prefix;
		String localName;

		System.out.println("The elements are: ");
		for (int i = 0; i < nl.getLength(); i++) {
			nsElement = (Element) nl.item(i);
			/*
			prefix = nsElement.getPrefix();
			System.out.println("  ELEMENT Prefix Name :" + prefix);

			localName = nsElement.getLocalName();
			System.out.println("  ELEMENT Local Name    :" + localName);
			*/
			System.out.println("Node value ====>" + nsElement.getTextContent());
		}
		System.out.println();
	}
	
	
	public static void printElementAttributes(Document doc) {
		NodeList nl = doc.getElementsByTagName("*");
		Element e;
		Attr nsAttr;
		String attrpfx;
		String attrname;
		String attrval;
		NamedNodeMap nnm;
		int i, len;

		len = nl.getLength();

		for (int j = 0; j < len; j++) {
			e = (Element) nl.item(j);
			System.out.println(e.getTagName() + ":");

			nnm = e.getAttributes();

			if (nnm != null) {
				for (i = 0; i < nnm.getLength(); i++) {
					nsAttr = (Attr) nnm.item(i);

					attrpfx = nsAttr.getPrefix();
					attrname = nsAttr.getLocalName();
					attrval = nsAttr.getNodeValue();

					System.out.println(" " + attrpfx + ":" + attrname + " = " + attrval);
				}
			}
			System.out.println();
		}
	}
	
	
	public static String currentDateTime(){
		return AppConstants.SDF_DEFAULT_DATETIME.format(new Date());
	}
	
	
	public static String formatDate(Date date) {
		if(date == null)
			return "0000-00-00";
					
		return AppConstants.SDF_DEFAULT_DATE.format(date);
	}
	
	
	public static String formatTime(Date date) {
		if(date == null)
			return "00:00:00";
		
		return AppConstants.SDF_DEFAULT_TIME.format(date);
	}
	
	
	public static String makeString(List<Integer> list, String pattern) {
		if(list.isEmpty())
			return "";
		
		String str = list.get(0).toString();
		
		for(int i = 1; i<list.size(); i++)
			str = str+pattern+list.get(i);
		
		return str;
	}
	
	
	public static String makeString (List<Integer> list) {
		return makeString(list, ", ");
	}
	
	
	public static String makeString (Integer[] list) {
		return makeString(Arrays.asList(list));
	}
	
	
	public static String sqlify(String data) {
		if(data==null)
			return ("''");
		
		return ("'" + data + "'");
	}
	
	
	public static String makeString(String[] list, String pattern) {
		int len = list.length;
		if(len==0)
			return sqlify("");
		
		String str = sqlify(list[0]);
		
		for(int i = 1; i<len; i++)
			str = str+pattern+sqlify(list[i]);
		
		return str;
	}
	
	
	public static String makeString (String[] list) {
		return makeString(list, ", ");
	}
	
	
	public static SimpleDateFormat getSdf(String format) {
		if(format==null || format.trim().isEmpty())
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf;
	}
	
	
	public static Integer[] userListToUserArr(List<User> users){
		ArrayList<Integer> usrArr = new ArrayList<Integer>();
		
		for(User u : users)
			usrArr.add(u.id);
			
		return usrArr.toArray(new Integer[usrArr.size()]);
	}
	
	
	public static String getMonthName(String data, String dateFormat) {
		SimpleDateFormat sdfMon = new SimpleDateFormat("MMMMM");
		SimpleDateFormat sdfOriginal = new SimpleDateFormat(dateFormat);
		
		try {
			return sdfMon.format(sdfOriginal.parse(data));
		} catch (ParseException e){
			return "";
		}
	}
	
	
	public static String getMonthName(String data) {
		return getMonthName(data, "yyyy-MM");
	}
	
	
	public static String getReportPath(String reportName){
		return Play.application().getFile(AppConstants.PATH_OF_PUBLIC_DOWNLOAD_FOLDER+reportName).getAbsolutePath();
	}
	
	
	public static String getReportTemplatePath(String reportName) {
		return Play.application()
				.getFile(AppConstants.REPORT_TEMPLATE_PATH + reportName + AppConstants.FILE_EXT_JRXML)
				.getAbsolutePath();
	}
	
	
	public static String getReportTemplatePath(String reportName, boolean isPdf) {
		String templateFileNameFlag = (isPdf) ? "_pdf" : "_xls";
		return getReportTemplatePath(reportName + templateFileNameFlag);
	}
	
	
	public static Date stripTime(Date dateTime){
		return DateUtils.truncate(dateTime, Calendar.DATE);
	}
	
	public static String getCurrentUserPunchOutStatus(){
		return "Normal [User: " + getCurrentUsername() + "]";
	}
	
	// ** this method is used for test purpose only
	public static void main(String[] args) {
		//System.out.println("[" + makeString(new String[]{}) + "]");
		//System.out.println(getMonthName("2014-11"));
		System.out.println(stripTime(new Date()).toString());
	}
}
