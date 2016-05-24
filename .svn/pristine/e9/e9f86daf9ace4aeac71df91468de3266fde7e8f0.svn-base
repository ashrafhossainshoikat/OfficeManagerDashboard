import java.util.Locale;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import play.*;
import play.data.format.Formatters;
import play.data.format.Formatters.SimpleFormatter;
import play.mvc.*;
import play.mvc.Http.RequestHeader;
import static play.mvc.Results.*;

import models.ebeanhelper.time.Interval;

public class Global extends GlobalSettings {
	/*@Override
	public Result onHandlerNotFound(RequestHeader request) {
		return Results.notFound("Sorry, Page Not found : 404");
	}

	@Override
	public Result onError(RequestHeader request, Throwable t) {
		return internalServerError("Sorry, Internal Server Error : 500");
	} */
	
	// ** register Interval class so that when user posts data from html view, data can be bound as Interval
	@Override
	public void onStart(Application app) {
		Formatters.register(Interval.class, new SimpleFormatter<Interval>() {
			@Override
			public Interval parse(String input, Locale l) throws ParseException {
				return Interval.parse(input);
			}

			@Override
			public String print(Interval interval, Locale l) {
				return interval.toString();
			}
		});
	}
}