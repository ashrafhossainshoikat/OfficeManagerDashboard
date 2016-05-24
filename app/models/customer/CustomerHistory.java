package models.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.JsonNodeFactory;
import org.codehaus.jackson.node.ObjectNode;



import models.punchcard.Card;
import play.db.ebean.Model;
import play.libs.Json;
import services.JavaUtils;
import services.ScalaUtils;

@Entity
public class CustomerHistory extends Model {

	@Id
	public Integer id;
	
	@Version
	@Column(name = "edit_count", nullable=false)
	public int version = 1;

	public String customerName;

	public String accountNumber;

	public String customerPhone;

	public String customerAddress;

	public Date punchInTime;

	public Date punchOutTime;
	
	public String punchOutStatus;
	
	//@Column(nullable = false)
	//public String createdBy;

	public String cardNumber;
	
	@ManyToOne
	@JoinColumn(name = "card_id", referencedColumnName = "id")
	public Card card;

	@ManyToOne
	@JoinColumn(name = "customer_criteria_id", referencedColumnName = "id")
	public CustomerCriteria customerCriteria;

	
	@OneToMany(mappedBy = "customerHistory")
	public List<CustomerServiceTask> customerServiceTasks;

	// --------------------------------------------- //
	public static Finder<Integer, CustomerHistory> find = new Finder(Integer.class, CustomerHistory.class);

	public static void create(CustomerHistory customerHistory) {
		customerHistory.save();
	}

	public static void update(CustomerHistory customerHistory) {
		customerHistory.update();
	}

	public static List<CustomerHistory> all() {
		return find.all();
	}

	public static CustomerHistory get(int id) {
		return find.where().eq("id", id).findUnique();
	}
	
	public static List<CustomerHistory> getCurrentCustomers() {
		return find.where().isNull("punchOutTime").findList();
	}
	
	public static List<CustomerHistory> getCurrentCustomersAsJson() {
		List<CustomerHistory> chList =  
				find.fetch("card").where()
				.isNull("punchOutTime")
				.eq("card.branchSolId", JavaUtils.getCurrentUserBranchSolId())
				.findList();
		
		return chList;
	}
	
	
	public static CustomerHistory getCustomerByCardNumber(String cardNumber) {
		return find.where().eq("cardNumber", cardNumber).isNull("punchOutTime").findUnique();
	}

	public ObjectNode toJson() {
		if(this.id==null)
			throw new IllegalArgumentException("`id` field of CustomerHistory object is null");
		
		ObjectNode chAsJson = Json.newObject();

		chAsJson.put("id", id);
		chAsJson.put("version", version);
		chAsJson.put("cardNumber", ((cardNumber == null) ? "" : cardNumber));
		chAsJson.put("customerName", ((customerName == null) ? "" : customerName));
		chAsJson.put("accountNumber", ((accountNumber == null) ? "" : accountNumber));
		chAsJson.put("customerPhone", ((customerPhone == null) ? "" : customerPhone));
		chAsJson.put("customerAddress", ((customerAddress == null) ? "" : customerAddress));
		chAsJson.put("punchInTime", ScalaUtils.formatTime(punchInTime));
		chAsJson.put("customerCriteriaId", ((customerCriteria == null) ? "" : customerCriteria.id.toString()));
		chAsJson.put("customerCriteriaValue", ((customerCriteria == null) ? null : CustomerCriteria.get(customerCriteria.id).criteriaName));

		chAsJson.put("taskCount", CustomerServiceTask.getCountOfActiveTasksByCustomerHistory(id));
		
		/*
		if(getTheCustomerWhoHaveNotEndedTask().contains(id))
			chAsJson.put("hasTask", true);
		else
			chAsJson.put("hasTask", false);
		*/
		
		return chAsJson;
	}
	
	
	public static ArrayNode getAllPunchedInCustomerAsJsonNode() {
		JsonNodeFactory jnf = JsonNodeFactory.instance;
		ArrayNode arrayNode = new ArrayNode(jnf);

		for (CustomerHistory c : getCurrentCustomersAsJson()) {
			ObjectNode o = c.toJson();
			arrayNode.add(o);
		}

		return arrayNode;
	}
	
	
	
	
	
	

	// ** for punch in simulation ** //
	// ** Used in application from 29-Nov-2014 for Card manual input
	public static List<Integer> getCardIdsInService(int solId){
		List<Integer> cards = new ArrayList<Integer>();
		List<CustomerHistory> chList = find
				.fetch("card")
				.where()
				.eq("card.branchSolId", solId)
				.isNull("punchOutTime")
				.findList();
		
		for(CustomerHistory ch: chList) {
			if((new Date().getTime() - ch.punchInTime.getTime()) < 24*60*60*1000)
				cards.add(ch.card.id);
		}
		
		return cards;
	}
	
	public static List<Integer> getCardIdsInService(){
		List<Integer> cards = new ArrayList<Integer>();
		List<CustomerHistory> chList = find
				.fetch("card")
				.where()
				.isNull("punchOutTime")
				.findList();
		
		for(CustomerHistory ch: chList) {
			if((new Date().getTime() - ch.punchInTime.getTime()) < 24*60*60*1000)
				cards.add(ch.card.id);
		}
		
		return cards;
	}
	
	public static List<Integer> getTheCustomerWhoHaveNotEndedTask() {
		List<Integer> chIds = new ArrayList<>();
		List<CustomerHistory> chList = find.fetch("customerServiceTasks")
										.where()
										.isNull("customerServiceTasks.taskEndTime")
										.eq("customerServiceTasks.username", JavaUtils.getCurrentUsername())
										.findList();
		
		for(CustomerHistory ch : chList) {
			chIds.add(ch.id);
		}
		
		return chIds;
	}

}
