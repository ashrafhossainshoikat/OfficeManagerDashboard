package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.JavaUtils;
import connectplus.models.User;


public class Application extends Controller {
  
    public static Result index() {
        //return ok(index.render("Your new application is ready."));
    	
    	User user = JavaUtils.getCurrentUser();
    	if(user == null)
    		return redirect(connectplus.controllers.routes.UserManagement.login());
    	
    	return redirect(JavaUtils.getCurrentUserHomepage(user));
    }
  
    public static Result dashboard() {
    	//return ok(index.render("Your new application is ready."));
    	return TODO;
    }
    
    
    public static Result backOffice() {
    	return ok(views.html.backOffice.backOfficeMenu.render());
    }
    
}
