package controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import play.libs.Json;
import be.objectify.deadbolt.java.actions.Dynamic;
import services.AppConstants;
import services.JavaUtils;
import connectplus.services.deadbolt.DeadboltHandler;
import connectplus.models.Role;
import connectplus.models.User;
import connectplus.models.geo.Branch;
import dummymodels.test.IntervalTest;
import dummymodels.test.SpTest;
import models.customer.CustomerHistory;
import models.customer.CustomerServiceTask;
import models.punchcard.Card;
import models.task.TaskType;
import models.ebeanhelper.time.Interval;
//import models.usermanagement.MenuPermission;
import models.geo.BranchInRegion;

import views.html.test.*;


public class Test extends Controller {
	static Form<IntervalTest> regionForm=Form.form(IntervalTest.class);
	
	/*public static Result roleMenu() {
		
		 * MenuPermission mp = MenuPermission.findByRoleIdAndPermissionName(11,
		 * "SecondPermission"); if(mp!=null) System.out.println(mp.id); else
		 * System.out.println("Permission not found");
		 

		for (MenuPermission mp : Role.get(1).getPermissions()) {
			System.out.println(mp.permissionName);
		}

		return TODO;
	}*/
	
	
	public static Result testTime() {
		 return ok(inputTimeTest.render());
		
	}

	public static Result login(String username) {
		session(AppConstants.SESN_USERNAME, username);
		return ok("You have been logged in");
	}

	public static Result logout() {
		session().clear();
		return ok("You have been logged out");
	}

	@Dynamic(value = "Test" + AppConstants.NAME_SEPERATOR + "permission", handler = DeadboltHandler.class)
	public static Result permission() {
		return ok("Entry restricted");
	}

	public static Result punchIn() {
		List<Card> cards = Card.getAvailableCards();
		int len = cards.size();
		if (len == 0)
			return ok("No card is available for new customer");

		int rand = (len == 1) ? 0 : (int) Math.floor(Math.random() * len);
		Card c = cards.get(rand);
		CustomerHistory ch = new CustomerHistory();
		ch.card = c;
		ch.cardNumber = c.cardNumber;
		ch.punchInTime = new Date();
		CustomerHistory.create(ch);

		return ok("Card [" + c.cardNumber + "] has been punched in");
		// return TODO;
	}

	// @BodyParser.Of(BodyParser.Json.class)
	public static Result jsonArray(String customerHistoryId) {

		int chId = Integer.parseInt(customerHistoryId);
		// System.out.print("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+chId);
		ArrayNode cstArray = CustomerServiceTask.findAllByIdAsJson(chId);
		if (cstArray.size() == 0)
			return ok(JavaUtils.generateJsonFailureResponse());
		else
			return ok(JavaUtils.generateJsonSuccessResponse(cstArray));
		// return ok(CustomerServiceTask.find.all().toString());
	}
	
	//method to get current logged in user from session
	public static Result currentUser() {
		//return ok(session().get(AppConstants.SESN_USERNAME));
		return ok(JavaUtils.getCurrentUsername());
	}

	public static Result getMyAssinedTask() {

		String currentUserName = "";
		int chId = 0;

		final Map<String, String[]> values = request().body().asFormUrlEncoded();

		String[] userName = values.get("userName");
		if (userName.length > 0) {
			currentUserName = userName[0];
		}

		String[] customerHistoryId = values.get("customerHistoryId");
		if (customerHistoryId.length > 0) {
			String custHistId = customerHistoryId[0];
			chId = Integer.parseInt(custHistId);
		}

		CustomerServiceTask cuSeTask = CustomerServiceTask.getAssignedTask(currentUserName, chId);

		if (cuSeTask == null)
			return ok(JavaUtils.generateJsonFailureResponse());
		else
			return ok(JavaUtils.generateJsonSuccessResponse(cuSeTask.toJson()));

	}

	public static Result chkMethod(String userName) {
		return ok(userName);

	}

	@BodyParser.Of(BodyParser.Json.class)
	public static Result accInfoToJson(String accNum) {

		ObjectNode accInfoJson = Json.newObject();

		accInfoJson.put("ACCT_NAME", "MD. FIROZ AHSAN");
		accInfoJson.put("CLR_BAL_AMT", "2491129.8");
		accInfoJson.put("CUST_PAGER_NO", "01714097681");
		accInfoJson.put("FORACID", "1501100014805001");
		accInfoJson.put("SCHM_DESC", "SAVINGS ACCOUNT STAFF");
		accInfoJson.put("STATUS", "Active");

		if (accInfoJson == null)
			return ok(JavaUtils.generateJsonFailureResponse());
		else
			return ok(JavaUtils.generateJsonSuccessResponse(accInfoJson));
	}
	
	public static Result otherUser() {
		for(User u: User.findOtherBssoOrCcsoUsersOfSameBranch("BSSO_USER1"))
			System.out.println(u.username);
		
		return TODO;
	}

	public static Result ebeanCustomDataType() {
		String[] p = {"13:05:35", "03:05:35", "3:5:35", "05:35", "5:35", "05:07", "5:7", "9:07:05"};
		for(String s : p)
			System.out.println("Pattern: [" + s +"], Interval: [" + Interval.parse(s).toString() +"]");
		
		return TODO;
	}
	
	
	public static Result branchQuery(){
		System.out.println(JavaUtils.makeString(BranchInRegion.getAllSolIds()));
		System.out.println(Branch.all());
		
		return TODO;
	}
	
	
	public static Result intervalShow() {
		return ok(views.html.test.intervalBind.render(regionForm));
	}
	
	public static Result intervalSubmit() {
		 Form<IntervalTest> frm = regionForm.bindFromRequest();
		 
		 if(frm.hasErrors()) {
			 System.out.println(frm.errors().toString());
		 } else {
			IntervalTest it = frm.get();
		 	System.out.println(it.toString());
		 }
		 
		 return TODO;
	}
	
	public static Result timeStrip() {
		return ok(JavaUtils.stripTime(new Date()).toString());
	}
	
	public static Result subReport() {
		NewReportGenerator nrg = new NewReportGenerator();
		return TODO;
	}
	
	public static Result storedProcedure() {
		SpTest.getMenu();
		return TODO;
	}
	
}
