package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.Data;
public class DataDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper dataMapper = new RowMapper() {
		public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
			Data data = new Data();
			data.setDataSeq(rs.getInt("data_seq"));
			data.setBloodgroupsKind(rs.getString("bloodgroups_kind"));
			data.setComment(rs.getString("comment"));
			data.setTitle(rs.getString("title"));
			return data;
		}
	};

	public void addData(final Data data) {
		String query = "" +
				"INSERT INTO T_NF_DATA " +
				"	(data_seq, bloodgroups_kind, comment,title) " +
				"VALUES " +
				"	(?, ?, ?,?) ";
		this.jdbcTemplate.update(query, new Object[] {
			data.getDataSeq(),
			data.getBloodgroupsKind(),
			data.getComment(),
			data.getTitle()
		});
	}

	public void deleteData(String data_seq) {
		String query = "DELETE FROM T_NF_DATA WHERE data_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { data_seq });
	}

	public void updateData(Data data) { 
		String query = "" + 
				"UPDATE T_NF_DATA SET " +
				"	data_seq = ?, " +
				"	bloodgroups_kind = ?, " +
				"	title = ?, " +
				"	comment = ? " +
				"WHERE data_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			data.getDataSeq(),
			data.getBloodgroupsKind(),
			data.getTitle(),
			data.getComment()
		});
	}

	public Data getData(String bloodgroups_kind) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_DATA " +
				"WHERE bloodgroups_kind = ? ";
		return (Data)this.jdbcTemplate.queryForObject(query, new Object[] { bloodgroups_kind }, this.dataMapper);
	}

	public List<Data> getDataList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" data_seq, bloodgroups_kind, comment " +
				"FROM T_NF_DATA " +
				"WHERE data_seq <= ( " +
				"	SELECT MIN(data_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" data_seq " +
				"		FROM T_NF_DATA " +
				"		ORDER BY data_seq DESC " +
				"	) AS A) " +
				"ORDER BY data_seq DESC";
		return (List<Data>)this.jdbcTemplate.query(query, this.dataMapper);
	}
	

}
