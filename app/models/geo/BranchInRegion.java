package models.geo;

import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.SqlUpdate;
import com.avaje.ebean.SqlQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import connectplus.models.geo.Branch;


@Entity
public class BranchInRegion extends Model {

	@Required
	@Column(nullable=false, unique=true)
	public int branchSolId;
	
	@Required
	@Column(nullable=false)
	public int regionId;
	// --------------------------------------------------------------------------
	
	public static Finder<Integer, BranchInRegion> find = new Finder(Integer.class, BranchInRegion.class);
	
	public static List<Integer> findBranchSolIdsByRegion(int regionId) {
		List<Integer> solIds = new ArrayList<Integer>();
		
		List<BranchInRegion> birList = find.where().eq("regionId", regionId).findList();
		if(birList.isEmpty())
			return new ArrayList<Integer>();
		
		for(BranchInRegion bir : birList) {
			solIds.add(bir.branchSolId);
		}
		
		return solIds;
	}
	
	public static List<Integer> findBranchIdsByRegion(int regionId) {
		ArrayList<Integer> branchIds = new ArrayList<>();
		List<BranchInRegion> birList =  find.where().eq("regionId", regionId).findList();
		
		for(Branch b : findBranchesByRegion(regionId))
			branchIds.add(b.id);
		
		return branchIds;
	}
	
	public static List<Branch> findBranchesByRegion(int regionId) {
		return Branch.findListBySolIds(findBranchSolIdsByRegion(regionId));
	}
	
	public static BranchInRegion findByRegionAndSol(int regionId, int branchSolId) {
		return find.where()
				.eq("branchSolId", branchSolId)
				.eq("regionId", regionId)
				.findUnique();
	}
	
	public static Region findRegionByBranch(int branchSolId) {
		BranchInRegion bir = find.where().eq("branchSolId", branchSolId).findUnique();
		
		if(bir == null)
			return null;
		
		return Region.get(bir.regionId);
	}
	
	public static boolean deleteByRegionAndSol(int regionId, int branchSolId) {
		BranchInRegion bir = findByRegionAndSol(regionId, branchSolId);
		if(bir == null)
			return false;
		
		try {
			SqlUpdate birDown = Ebean.createSqlUpdate("DELETE FROM branch_in_region WHERE region_id = :rid AND branch_sol_id = :bsId ");
			birDown.setParameter("rid", regionId);
			birDown.setParameter("bsId", branchSolId);
			birDown.execute();
			
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static String createByRegionAndSol(int regionId, int branchSolId) {
		Branch b = Branch.findBySol(branchSolId);
		Region r = Region.get(regionId);
		if(b==null)
			return "Error: Branch [SOL: " + branchSolId + "] could not be found in ConnectPlus";
		
		if(r==null)
			return "Error: Region [ID: " + regionId + "] could not be found, please add Region first";
		
		Region existingRegion = findRegionByBranch(branchSolId);
		if(existingRegion != null)
			return "Error: Branch [SOL: " + branchSolId + "] is already mappded with region [" + existingRegion.name + "]";
		
		try {
			SqlUpdate birDown = Ebean.createSqlUpdate("INSERT INTO branch_in_region (region_id, branch_sol_id) VALUES( :rid , :bsId )");
			birDown.setParameter("rid", regionId);
			birDown.setParameter("bsId", branchSolId);
			
			birDown.execute();
		} catch(Exception e){
			e.printStackTrace();
			return "Error: Internal Server Error, please try again later";
		}
		
		return null;
	}
	
	
	public static boolean solExists(int branchSolId) {
		/*
		String sql = "SELECT count(*) as r_count from branch_in_region WHERE branch_sol_id = :bsId ";

		SqlQuery query = Ebean.createSqlQuery(sql);
		query.setParameter("bsId", branchSolId);
        SqlRow sqlRow = query.findUnique();
        if(sqlRow != null && !sqlRow.isEmpty() && sqlRow.getInteger("r_count")>0)
			return true;
        
        return false;
        */
		
        return (find.where().eq("branchSolId", branchSolId).findRowCount() > 0);
	}
	
	public static List<Integer> getAllSolIds() {
	//public static Integer[] getAllSolIds() {
		ArrayList<Integer> solIds = new ArrayList<>();

		for(BranchInRegion bir : find.all()){
			solIds.add(bir.branchSolId);
		}
		
		//return solIds.toArray(new Integer[solIds.size()]);
		return solIds;
	}
	
}
