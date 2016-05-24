package connectplus.models.geo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Query;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;

import models.geo.BranchInRegion;
import models.punchcard.Card;
import play.db.ebean.Model;
import services.AppConstants;
import services.JavaUtils;
import connectplus.models.User;


@Entity
@Table(name="BRACBankBranch")
public class Branch extends Model {

	@Id
	@Column(name = "BranchId")
	public Integer id;
	
	@Column(name = "SOL")
	public Integer sol;
	
	@Column(name = "BranchRCCode")
	public Integer rcCode;
	
	@Column(name = "BranchAddress")
	public String address;

	// --------------------------------------------- //
	private static Finder<Integer, Branch> find = new Finder("connectplus", Integer.class, Branch.class);
	
	@Transient
	public String name() {
		if(this.rcCode == null)
			return "";
		
		ReferanceCodes rc = ReferanceCodes.get(rcCode);
		if(rc == null)
			return "";
		
		return rc.code;
	}
	

	@Override
	public void save() {}

	@Override
	public void update() {}

	public static List<Branch> all() {
		//return find.all();
		
		//TODO get only sme krishi branches order by branch name; use BranchInRegion branch ids
		RawSql sql = RawSqlBuilder
				.parse("SELECT b.BranchId AS id, b.SOL AS sol, b.BranchRCCode AS rcCode, b.BranchAddress AS address "
						//+ " , r.Code AS Code "
						+ " FROM BRACBankBranch b, ReferanceCodes r "
						+ " WHERE SOL IN (:solIds) "
						+ " AND b.BranchRCCode = r.RCID"
						+ " ORDER BY r.Code "
					)
				//.columnMapping("BranchId", "id")
				//.columnMapping("SOL", "sol")
				//.columnMapping("BranchRCCode", "rcCode")
				//.columnMapping("BranchAddress", "address")
				.create();
		
		Query<Branch> query = Ebean.getServer("connectplus").find(Branch.class);
		query.setRawSql(sql);
		//query.setParameter("solIds", JavaUtils.makeString(BranchInRegion.getAllSolIds()));
		query.setParameter("solIds", BranchInRegion.getAllSolIds());

		return query.findList();
	}

	public static Branch get(int id) {
		return find.byId(id);
	}
	
	public static Branch findBySol(int sol) {
		return find.where().eq("sol", sol).findUnique();
	}

	public static Map<String, String> getAsMap() {
		//TODO order by branch name
		LinkedHashMap<String, String> branches = new LinkedHashMap<String, String>();
		for (Branch b : all()) {
			branches.put(b.sol.toString(), b.toString());
		}

		return branches;
	}
	
	public static Map<String, String> getAsMapForCurrentUser() {
		LinkedHashMap<String, String> branches = new LinkedHashMap<String, String>();
		
		for (Branch branch : getListByUser(JavaUtils.getCurrentUser())) {
			branches.put(branch.sol.toString(), branch.toString());
		}

		return branches;
	}
	
	
	public static Map<String, String> getAsMapForPunchIn() {
		LinkedHashMap<String, String> branches = new LinkedHashMap<String, String>();
		
		for (Branch branch : findListBySolIds(Card.getDistinctSolId())) {
			branches.put(branch.sol.toString(), branch.toString());
		}

		return branches;
	}
	
	
	public static List<Branch> findListBySolIds(List<Integer> solIds) {
		if(solIds.size()==0)
			return new ArrayList<Branch>();
		
		return find.where()
					.in("sol", solIds)
					.orderBy("sol")
					.findList();
	}
	
	public static Branch findByUser(User user) {
		if(user==null || user.branchId==null)
			return null;
		
		return get(user.branchId);
	}
	
	public static Branch findByUser(String username) {
		return findByUser(User.findByUsername(username));
	}
	
	public static Branch findByUser(int userId) {
		return findByUser(User.get(userId));
	}
	
	public static List<Branch> getListByUser(User user) {
		ArrayList<Branch> branchList = new ArrayList<Branch>();
		
		if(user==null || user.isBssoOrCcsoUser())
			return branchList;
		
		List<Integer> roles = user.getRoleIdList();
		if (roles.contains(AppConstants.ROLE_ID_BRANCH_MANAGER)) {
			branchList.add(user.branch());
			return branchList;
		} else if (roles.contains(AppConstants.ROLE_ID_REGIONAL_MANAGER)) {
			return BranchInRegion.findBranchesByRegion(user.regionIdAsInt());
		} else {
			return Branch.all();
		}
	}
	
	public static Integer[] findSolIdArrayByUser(User user) {
		List<Branch> branchList = getListByUser(user);
		List<Integer> branchArr = new ArrayList<>();
		
		for(Branch b : getListByUser(user))
			branchArr.add(b.sol);
		
		return branchArr.toArray(new Integer[branchArr.size()]);
	}
	
	
	
	@Override
	public String toString() {
		return (this.sol.toString() + ": " + this.name());
	}

}
