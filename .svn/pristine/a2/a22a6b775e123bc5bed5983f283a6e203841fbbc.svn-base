package connectplus.controllers;

import java.util.List;

import be.objectify.deadbolt.java.actions.Dynamic;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import services.AppConstants;
import services.JavaUtils;
import connectplus.services.deadbolt.DeadboltHandler;
import connectplus.models.User;
import connectplus.models.UserPermission;
import connectplus.models.geo.Branch;
import connectplus.dummymodels.Login;
import connectplus.services.Password;
import connectplus.views.html.loginRequest;
import connectplus.services.Constants;


public class UserManagement extends Controller {
	
	private static Form<Login> loginForm = Form.form(Login.class);
	
	
	public static Result login() {
		return ok(loginRequest.render());
	}
	
	
	public static Result authenticate() {
		Form<Login> filledForm = loginForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			//flash("error", "Error in login, please provide all information correctly");
			flash("error", "Invalid username or password");
			return ok(loginRequest.render());
		}

		Login login = filledForm.get();
		User user = User.findByUsername(login.username);

		if (user == null) {
			//flash("error", "Username does not exist.");
			flash("error", "Invalid username or password");
			return ok(loginRequest.render());
		}

		if (!user.isApproved) {
			flash("error", "User is not approved yet, please contact the admin.");
			return ok(loginRequest.render());
		}

		if (!user.isActive) {
			flash("error", "User is not active, please contact the admin.");
			return ok(loginRequest.render());
		}

		if (user.isLocked) {
			flash("error", "The account is locked, please contact the admin.");
			return ok(loginRequest.render());
		}

		int pwdSalt = Integer.parseInt(user.passwordSalt);
		Password pwd = new Password(login.password, pwdSalt);
		String hashedPwd = pwd.ComputeSaltedHash();

		if (!user.hashedPassword.equals(hashedPwd)) {
			flash("error", "Password does not match.");
			return ok(loginRequest.render());
		}
		
		List<Integer> roleIds = user.getRoleIdList();
		if(user.isBssoOrCcsoUser() || roleIds.contains(AppConstants.ROLE_ID_BRANCH_MANAGER)) {
			Branch b = user.branch();
			if(b==null){
				flash("error", "User is not mapped to any Branch");
				return ok(loginRequest.render());
			}
			
			session(AppConstants.SESN_BRANCH_SOL, b.sol.toString());
		} else {
			session(AppConstants.SESN_BRANCH_SOL, "0");
		}
		
		session(AppConstants.SESN_USERNAME, login.username);
		
		
/*		 int role = User.findRoleByUserName(login.username);
		
		if(role == 1){
			return redirect(controllers.routes.LiveManagement.showAllRequestToAdmin());
		}*/
		return redirect(JavaUtils.getCurrentUserHomepage(user, roleIds));
		//controllers.routes.FindrManagement.searchTransaction()
		//return ok("");
	}
	
	
	public static Result logout() {
		session().clear();
		return redirect(connectplus.controllers.routes.UserManagement.login());
	}
	
	
	//@Dynamic(value = "63001", handler = DeadboltHandler.class)
	public static Result dummy() {
		return ok("This is a restricted url");
	}

}
