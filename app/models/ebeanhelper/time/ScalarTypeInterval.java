package models.ebeanhelper.time;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;

import com.avaje.ebeaninternal.server.core.BasicTypeConverter;
import com.avaje.ebeaninternal.server.type.DataBind;
import com.avaje.ebeaninternal.server.type.DataReader;
import com.avaje.ebeaninternal.server.type.ScalarTypeBase;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;


/**
 * ScalarType for Interval.
 */
public class ScalarTypeInterval extends ScalarTypeBase<Interval> {

	public ScalarTypeInterval() {
		super(Interval.class, true, Types.INTEGER);
	}
	
	@Override
	public void bind(DataBind b, Interval value) throws SQLException {
		if (value == null) {
			b.setNull(Types.INTEGER);
		} else {
			b.setInt(value.toInt());
		}
	}
	
	@Override
	public Interval read(DataReader dataReader) throws SQLException {
		return Interval.generate(dataReader.getInt());
	}
	
	@Override
	public Object toJdbcType(Object value) {
		return BasicTypeConverter.toInteger(value);
	}
	
	@Override
	public Interval toBeanType(Object value) {
		return Interval.generate(BasicTypeConverter.toInteger(value));
	}
	
	@Override
	public String formatValue(Interval t) {
		return t.toString();
	}
	
	@Override
	public Interval parse(String value) {
		return Interval.parse(value);
	}
	
	@Override
	public Interval parseDateTime(long systemTimeMillis) {
		return Interval.generate(systemTimeMillis);
	}
	
	@Override
	public boolean isDateTimeCapable() {
		return true;
	}
	
	@Override
	public Object readData(DataInput dataInput) throws IOException {
		if (!dataInput.readBoolean()) {
			return null;
		} else {
			int val = dataInput.readInt();
			return new Interval(val);
		}
	}
	
	@Override
	public void writeData(DataOutput dataOutput, Object v) throws IOException {
		Interval value = (Interval) v;
		if (value == null) {
			dataOutput.writeBoolean(false);
		} else {
			dataOutput.writeBoolean(true);
			dataOutput.writeInt(value.toInt());
		}
	}
	
	public Object jsonRead(JsonParser ctx, JsonToken event) throws IOException {
		return Interval.parse(ctx.getValueAsString());
	}
	
	 public void jsonWrite(JsonGenerator ctx, String name, Object value) throws IOException {
		 ctx.writeStringField(name, ((Interval)value).toString());
	 }
	 
}
