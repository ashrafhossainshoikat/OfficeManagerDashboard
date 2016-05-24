package models.ebeanhelper.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import services.JavaUtils;
import services.ScalaUtils;

/**
 * Class for storing interval in seconds between two timestamp
 */
public class Interval {
	private int value;
	private static final String DEFAULT_PATTERN = "HH:mm:ss";
	private static final SimpleDateFormat DEFAULT_SDF = JavaUtils.getSdf(DEFAULT_PATTERN);
	
	
	private Interval() {} // ** just to make sure that no one tries to pass empty parameter
	
	private Interval(Integer value) { // ** just to make sure that no one tries to pass Integer object
		this.value = value.intValue();
	}
	
	public Interval(int value) {
		this.value = value;
	}
	
	public static Interval generate(Date date) {
		if(date ==null)
			throw new IllegalArgumentException("Date is null");
		
		return new Interval((int) (date.getTime()/1000));
	}
	
	public static Interval generate(long millis) {
		return new Interval((int) (millis/1000));
	}
	
	public static Interval generate(float value) {
		return new Interval((int) (value));
	}
	
	public static Interval generate(double value) {
		return new Interval((int) (value));
	}
	
	public static Interval generate(Integer obj) {
		if(obj == null)
			//throw new IllegalArgumentException("Null object provided");
			return null;
		
		return new Interval(obj);
	}
	
	public static Interval generate(Date start, Date end) {
		if(start==null || end==null)
			//throw new IllegalArgumentException("Null object provided");
			return null;
		
		return new Interval((int)((end.getTime() - start.getTime())/1000));
	}
	
	public static Interval generate(Interval start, Date end) {
		if(start==null || end==null)
			//throw new IllegalArgumentException("Null object provided");
			return null;
		
		return new Interval((int)((end.getTime() - start.toInt()*1000)/1000));
	}
	
	public static Interval generate(Date start, Interval end) {
		if(start==null || end==null)
			//throw new IllegalArgumentException("Null object provided");
			return null;
		
		return new Interval((int)((end.toInt()*1000 - start.getTime())/1000));
	}
	
	public static Interval generate(Interval start, Interval end) {
		if(start==null || end==null)
			//throw new IllegalArgumentException("Null object provided");
			return null;
		
		return new Interval((int)(end.toInt() - start.toInt()));
	}
	
	public int toInt() {
		return value;
	}
	
	
	@Override
	public String toString() {
		return DEFAULT_SDF.format(new Date(value*1000));
	}
	
	//TODO implement
	public static Interval parse(String s) {
		if(s==null || s.trim().isEmpty())
			throw new IllegalArgumentException("No value to parse");
		
		if(ScalaUtils.isInteger(s))
			return new Interval(Integer.parseInt(s));
		
		String[] patterns = {DEFAULT_PATTERN, "mm:ss", "H:m:s", "m:s"};
		int len = patterns.length;
		boolean done = false;
		Date d = null;
		
		for(int i=0; (i<len & !done); i++) {
			try {
				d = JavaUtils.getSdf(patterns[i]).parse(s);
				done = true;
			} catch (ParseException e) {}
		}
		
		if(d==null)
			return null;
		
		return Interval.generate(d);
	}
}
