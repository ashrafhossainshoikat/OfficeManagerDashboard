package connectplus.services.deadbolt;

import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import be.objectify.deadbolt.core.models.Subject;
import play.mvc.Http;
import play.mvc.Result;
import services.JavaUtils;
import connectplus.views.html.accessFailed;
import connectplus.models.User;
import connectplus.services.Constants;


public class DeadboltHandler extends AbstractDeadboltHandler {
	public Result beforeAuthCheck(Http.Context context) {
		// returning null means that everything is OK. Return a real result if
		// you want a redirect to a login page or somewhere else
		return null;
	}

	public Subject getSubject(Http.Context context) {
		// in a real application, the user name would probably be in the session
		// following a login process
		//String user = context.session().get(Constants.SESN_USERNAME);
		//return User.findByUsername("steve");
		//return User.findByUsername(user);
		
		return JavaUtils.getCurrentUser();
	}

	public DynamicResourceHandler getDynamicResourceHandler(Http.Context context) {
		return new DeadboltDynamicResourceHandler();
	}

	@Override
	public Result onAuthFailure(Http.Context context, String content) {
		// you can return any result from here - forbidden, etc
		return ok(accessFailed.render());
	}
}
