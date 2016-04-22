package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestParam;

import kr.nomad.mars.dto.Food;
import kr.nomad.mars.dto.Vfood;

public class FoodDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper foodMapper = new RowMapper() {
		public Food mapRow(ResultSet rs, int rowNum) throws SQLException {
			Food food = new Food();
			food.setFoodSeq(rs.getInt("food_seq"));
			food.setFoodName(rs.getString("food_name"));
			food.setBloodgroupsO(rs.getInt("bloodgroups_o"));
			food.setBloodgroupsOPercent(rs.getString("bloodgroups_o_percent"));
			food.setBloodgroupsA(rs.getInt("bloodgroups_a"));
			food.setBloodgroupsAPercent(rs.getString("bloodgroups_a_percent"));
			food.setBloodgroupsB(rs.getInt("bloodgroups_b"));
			food.setBloodgroupsBPercent(rs.getString("bloodgroups_b_percent"));
			food.setBloodgroupsAb(rs.getInt("bloodgroups_ab"));
			food.setBloodgroupsAbPercent(rs.getString("bloodgroups_ab_percent"));
			food.setFiles(rs.getString("files"));
			return food;
		}
	};
	
	private RowMapper VfoodMapper = new RowMapper() {
		public Vfood mapRow(ResultSet rs, int rowNum) throws SQLException {
			Vfood food = new Vfood();
			food.setFoodSeq(rs.getInt("food_seq"));
			food.setFoodName(rs.getString("food_name"));
			food.setBloodgroups(rs.getInt("bloodgroup"));
			food.setBloodgroupsPercent(rs.getString("blood_groups_percent"));
		
			food.setFiles(rs.getString("files"));
			return food;
		}
	};

	public void addFood(final Food food) {
		String query = "" +
				"INSERT INTO T_NF_FOOD " +
				"	(food_seq, food_name, bloodgroups_o, bloodgroups_o_percent, bloodgroups_a, bloodgroups_a_percent, bloodgroups_b, bloodgroups_b_percent, bloodgroups_ab, bloodgroups_ab_percent,files) " +
				"VALUES " +
				"	(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) ";
		this.jdbcTemplate.update(query, new Object[] {
			food.getFoodSeq(),
			food.getFoodName(),
			food.getBloodgroupsO(),
			food.getBloodgroupsOPercent(),
			food.getBloodgroupsA(),
			food.getBloodgroupsAPercent(),
			food.getBloodgroupsB(),
			food.getBloodgroupsBPercent(),
			food.getBloodgroupsAb(),
			food.getBloodgroupsAbPercent(),
			food.getFiles()
		});
	}

	public void deleteFood(String food_seq) {
		String query = "DELETE FROM T_NF_FOOD WHERE food_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { food_seq });
	}

	public void updateFood(Food food) { 
		String query = "" + 
				"UPDATE T_NF_FOOD SET " +
				"	food_seq = ?, " +
				"	food_name = ?, " +
				"	bloodgroups_o = ?, " +
				"	bloodgroups_o_percent = ?, " +
				"	bloodgroups_a = ?, " +
				"	bloodgroups_a_percent = ?, " +
				"	bloodgroups_b = ?, " +
				"	bloodgroups_b_percent = ?, " +
				"	bloodgroups_ab = ?, " +
				"	bloodgroups_ab_percent = ?, " +
				"	files = ? " +
				"WHERE food_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			food.getFoodSeq(),
			food.getFoodName(),
			food.getBloodgroupsO(),
			food.getBloodgroupsOPercent(),
			food.getBloodgroupsA(),
			food.getBloodgroupsAPercent(),
			food.getBloodgroupsB(),
			food.getBloodgroupsBPercent(),
			food.getBloodgroupsAb(),
			food.getBloodgroupsAbPercent(),
			food.getFiles()
		});
	}

	public Food getFood(String food_seq) {
		String query = "" + 
				"SELECT *  " +
				"FROM T_NF_FOOD " +
				"WHERE food_seq = ? ";
		return (Food)this.jdbcTemplate.queryForObject(query, new Object[] { food_seq }, this.foodMapper);
	}

	public List<Food> getFoodList(String bloodType,int type,int page, int itemCountPerPage) {
		String query = "" 
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			ROW_NUMBER() OVER(order by food_seq desc) as row_seq, "
				+ "			A.* "
				+ "		FROM T_NF_FOOD as A where bloodgroups_"+bloodType+" = ? "
				+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		return (List<Food>)this.jdbcTemplate.query(query, new Object[] { type },this.foodMapper);
	}

	public int getFoodCount(String bloodType,int type) {
		String query = "" 
				+ "	SELECT count(*) FROM  "
			
				+ "	 T_NF_FOOD where bloodgroups_"+bloodType+" = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { type });
	}
	

	public List<Vfood> getFoodSearchList(String bloodType,String foodName) {
		String query = "" 
			/*	+ "	SELECT * FROM ( "*/
				+ "		SELECT  * "
			/*	+ "			ROW_NUMBER() OVER(order by food_seq desc) as row_seq, "
				+ "			A.* "*/
				+ "		FROM V_NF_FOOD_"+bloodType+" where food_name like  '%"+foodName+"%' order by food_seq desc";
			/*	+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" "*/
		return (List<Vfood>)this.jdbcTemplate.query(query, this.VfoodMapper);
	}

	public Vfood getFoodV(int food_seq,String bloodType) {
		String query = "" + 
				"SELECT *  " +
				"FROM V_NF_FOOD_"+bloodType+
				" WHERE food_seq = ? ";
		try{
			return (Vfood)this.jdbcTemplate.queryForObject(query, new Object[] { food_seq }, this.VfoodMapper);
		}catch(Exception e){
			return new Vfood();
		}
	}
	public int getFoodSearchCount(String bloodType,String foodName) {
		String query = "" 
				+ "	SELECT count(*) FROM  "
			
				+ "	 V_NF_FOOD_"+bloodType+" where food_name like '%"+foodName+"%'";
		return this.jdbcTemplate.queryForInt(query);
	}

}
