package connectplus.dummymodels;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;

@Entity
//class Login extends Model{
public class Login {
	
	//public Login(){super();}
	
	@Id
	public Integer id;
	
	@Required
	public String username;
	
	@Required
	public String password;
	

}
