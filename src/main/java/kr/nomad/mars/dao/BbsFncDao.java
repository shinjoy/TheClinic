package kr.nomad.mars.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.BbsFnc;

public class BbsFncDao {
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper bbsfncMapper = new RowMapper() {
		public BbsFnc mapRow(ResultSet rs, int rowNum) throws SQLException {
			BbsFnc bbsfnc = new BbsFnc();
			bbsfnc.setBbsFncSeq(rs.getInt("bbs_fnc_seq"));
			bbsfnc.setBbsSeq(rs.getInt("bbs_seq"));
			bbsfnc.setBbsCommentSeq(rs.getInt("bbs_comment_seq"));
			bbsfnc.setFncType(rs.getInt("fnc_type"));
			bbsfnc.setFncValue(rs.getInt("fnc_value"));
			bbsfnc.setUserId(rs.getString("user_id"));
			bbsfnc.setRegDate(rs.getString("reg_date"));
			bbsfnc.setPhoto(rs.getString("photo"));
			bbsfnc.setContents(rs.getString("contents"));
			bbsfnc.setFncReason(rs.getInt("fnc_reason"));
			return bbsfnc;
		}
	};
	
	private RowMapper VbbsfncMapper = new RowMapper() {
		public BbsFnc mapRow(ResultSet rs, int rowNum) throws SQLException {
			BbsFnc bbsfnc = new BbsFnc();
			bbsfnc.setBbsFncSeq(rs.getInt("bbs_fnc_seq"));
			bbsfnc.setBbsSeq(rs.getInt("bbs_seq"));
			bbsfnc.setBbsCommentSeq(rs.getInt("bbs_comment_seq"));
			bbsfnc.setFncType(rs.getInt("fnc_type"));
			bbsfnc.setFncValue(rs.getInt("fnc_value"));
			bbsfnc.setUserId(rs.getString("user_id"));
			bbsfnc.setRegDate(rs.getString("reg_date"));
			bbsfnc.setPhoto(rs.getString("photo"));
			bbsfnc.setContents(rs.getString("contents"));
			bbsfnc.setFncReason(rs.getInt("fnc_reason"));
			bbsfnc.setUserName(rs.getString("user_name"));
			bbsfnc.setType(rs.getInt("type"));
			return bbsfnc;
		}
	};


	public boolean addBbsFnc(final BbsFnc bbsfnc) {
		boolean result = false;
		try {
			String query = "" +
					"INSERT INTO T_NF_BBS_FNC " +
					"	(bbs_seq, bbs_comment_seq, fnc_type, fnc_value, user_id, reg_date,"
					+ "contents, photo,fnc_reason) " +
					"VALUES " +
					"	(?, ?, ?, ?, ?, getdate(),?,?,?) ";
			int qResult = this.jdbcTemplate.update(query, new Object[] {
				bbsfnc.getBbsSeq(),
				bbsfnc.getBbsCommentSeq(),
				bbsfnc.getFncType(),
				bbsfnc.getFncValue(),
				bbsfnc.getUserId(),
				bbsfnc.getContents(),
				bbsfnc.getPhoto(),
				bbsfnc.getFncReason()
			});
			
			if(qResult >0) {
				result = true;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	//탈퇴시 삭제
	public void deleteBbsFnc(String userId) {
		String query = "DELETE FROM T_NF_BBS_FNC WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { userId });
	}
	public void deleteBbsFnc(int bbs_fnc_seq) {
		String query = "DELETE FROM T_NF_BBS_FNC WHERE bbs_fnc_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { bbs_fnc_seq });
	}
	//좋아요취소
	public void deleteBbsFncGood(int bbs_seq,String userId,int type) {
		String query = "DELETE FROM T_NF_BBS_FNC WHERE bbs_seq = ? and user_id = ? and fnc_type = ? ";
		this.jdbcTemplate.update(query, new Object[] {bbs_seq,userId,type  });
	}
	//게시글 삭제
	public void deleteBbsGood(int bbs_seq) {
		String query = "DELETE FROM T_NF_BBS_FNC WHERE bbs_seq = ?  ";
		this.jdbcTemplate.update(query, new Object[] {bbs_seq });
	}
	
	//댓글 삭제
	public void deleteBbsCommentGood(int bbsCommentSeq) {
			String query = "DELETE FROM T_NF_BBS_FNC WHERE bbs_comment_seq = ?  ";
			this.jdbcTemplate.update(query, new Object[] {bbsCommentSeq });
	}


	public void updateBbsFnc(BbsFnc bbsfnc) { 
		String query = "" + 
				"UPDATE T_NF_BBS_FNC SET " +
				"	bbs_fnc_seq = ?, " +
				"	bbs_seq = ?, " +
				"	bbs_comment_seq = ?, " +
				"	fnc_type = ?, " +
				"	fnc_value = ?, " +
				"	user_id = ?, " +
				"	reg_date = ? " +
				"WHERE bbs_fnc_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
			bbsfnc.getBbsFncSeq(),
			bbsfnc.getBbsSeq(),
			bbsfnc.getBbsCommentSeq(),
			bbsfnc.getFncType(),
			bbsfnc.getFncValue(),
			bbsfnc.getUserId(),
			bbsfnc.getRegDate()
		});
	}

	public BbsFnc getBbsFnc(int bbs_fnc_seq) {
		String query = "" + 
				"SELECT bbs_fnc_seq, bbs_seq, bbs_comment_seq, fnc_type, fnc_value, user_id, reg_date " +
				"FROM T_NF_BBS_FNC " +
				"WHERE bbs_fnc_seq = ? ";
		return (BbsFnc)this.jdbcTemplate.queryForObject(query, new Object[] { bbs_fnc_seq }, this.bbsfncMapper);
	}

	public List<BbsFnc> getBbsFncList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" bbs_fnc_seq, bbs_seq, bbs_comment_seq, fnc_type, fnc_value, user_id, reg_date " +
				"FROM T_NF_BBS_FNC " +
				"WHERE bbs_fnc_seq <= ( " +
				"	SELECT MIN(bbs_fnc_seq) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" bbs_fnc_seq " +
				"		FROM T_NF_BBS_FNC " +
				"		ORDER BY bbs_fnc_seq DESC " +
				"	) AS A) " +
				"ORDER BY bbs_fnc_seq DESC";
		return (List<BbsFnc>)this.jdbcTemplate.query(query, this.bbsfncMapper);
	}
	
	
	public int getCount(int bbsSeq, int bbs_comment_seq) {
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_BBS_FNC WHERE bbs_seq = ? and bbs_comment_seq = ? ";
		try {
			return this.jdbcTemplate.queryForInt(query, new Object[] { bbsSeq, bbs_comment_seq });
		} catch (Exception e) {
			return 0;
		}
	}
	


	
	public int getCount(int bbsSeq, int bbs_comment_seq, String userId) {
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_BBS_FNC WHERE bbs_seq = ? and bbs_comment_seq = ? AND user_id = ? ";
		try {
			return this.jdbcTemplate.queryForInt(query, new Object[] { bbsSeq, bbs_comment_seq, userId });
		} catch (Exception e) {
			return 0;
		}
	}
	
	//본글 갯수
	public int getBBsCount( int bbsSeq,String userId,int fnc_type,int fnc_type2) {
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_BBS_FNC WHERE fnc_type in ( ?, ? )    and bbs_seq = ?  AND user_id = ? ";
		try {
				return this.jdbcTemplate.queryForInt(query, new Object[] { fnc_type,fnc_type2, bbsSeq, userId });
		} catch (Exception e) {
				return 0;
		}
	}
	
	
	//본글 갯수
	public int getBBsCount( int bbsSeq,String userId,int fnc_type) {
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_BBS_FNC WHERE fnc_type = ? "
				+ "and bbs_seq = ?  AND user_id = ? ";
		try {
			return this.jdbcTemplate.queryForInt(query, new Object[] { fnc_type, bbsSeq, userId });
		} catch (Exception e) {
			return 0;
		}
	}
	//댓글갯수
	public int getBBsCommentCount( int bbsCommentSeq,String userId,int fnc_type) {
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_BBS_FNC WHERE fnc_type = ? and bbs_comment_seq = ?  AND user_id = ? ";
		try {
			return this.jdbcTemplate.queryForInt(query, new Object[] { fnc_type, bbsCommentSeq, userId });
		} catch (Exception e) {
			return 0;
		}
	}
	
	
	public int getCount(int fnc_type, int bbsSeq, int bbs_comment_seq) {
		String query = "SELECT COUNT(*) AS cnt FROM T_NF_BBS_FNC WHERE fnc_type = ? and bbs_seq = ? and bbs_comment_seq = ?";
		try {
			return this.jdbcTemplate.queryForInt(query, new Object[] { fnc_type, bbsSeq, bbs_comment_seq});
		} catch (Exception e) {
			return 0;
		}
	}

	public int getSum(int fnc_type, int bbsSeq, int bbs_comment_seq) {
		String query = "SELECT COUNT(isnull(fnc_value,0)) FROM T_NF_BBS_FNC WHERE fnc_type = ? and bbs_seq = ? and bbs_comment_seq = ?";
		try {
			return this.jdbcTemplate.queryForInt(query, new Object[] { fnc_type, bbsSeq, bbs_comment_seq});
		} catch (Exception e) {
			return 0;
		}
	}
	//두개 판별 (공유글 좋아요,좋아요)
	public int getSum(int fnc_type,int fnc_type2, int bbsSeq, int bbs_comment_seq) {
		String query = "SELECT COUNT(isnull(fnc_value,0)) FROM T_NF_BBS_FNC WHERE fnc_type in ( ? ,? )"
				+ " and bbs_seq = ? and bbs_comment_seq = ?";
		try {
			return this.jdbcTemplate.queryForInt(query, new Object[] { fnc_type,fnc_type2, bbsSeq, bbs_comment_seq});
		} catch (Exception e) {
			return 0;
		}
	}
	
	//신고리스트
	
	public List<BbsFnc> getadBbsFncList(String startDate,String endDate,int fncReason,String keyword,String sort,String colname,int page,int itemCountPerPage) {
		String con ="where 1=1";
		
		if(fncReason!=0){
			con+= " and fnc_reason = "+fncReason;
		}
		if(!keyword.equals("")){
			con+=" and ( user_id like '%"+keyword+"%' or user_name like '%"+keyword+"%' )";
		}
		String order = " order by reg_date ";
		String order2 =sort;
		
		if(!colname.equals("")){
			order = " order by "+colname+""; 
		}
		if(!startDate.equals("") &&! endDate.equals("")){
			con+= " and reg_date between '"+ startDate+ " 00:00:00' and  '"+endDate+" 23:59:59'" ;
		}
		String query = "" 
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			ROW_NUMBER() OVER("+order+" "+order2+" ) as row_seq, "
				+ "			A.* "
				+ "		FROM V_NF_FNC AS A "
				+ "	"+ con 
				+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
				
		return (List<BbsFnc>)this.jdbcTemplate.query(query, this.VbbsfncMapper);
	}
	
	
	public int getadCount(String startDate,String endDate,int fncReason,String keyword) {
		String con ="where 1=1";
		
		if(fncReason!=0){
			con+= " and fnc_reason = "+fncReason;
		}
		if(!keyword.equals("")){
			con+=" and ( user_id like '%"+keyword+"%' or user_name like '%"+keyword+"%' )";
		}
		
		if(!startDate.equals("") &&! endDate.equals("")){
			con+= " and reg_date between '"+ startDate+ " 00:00:00' and  '"+endDate+" 23:59:59'" ;
		}
		String query = "" 
				+ "	SELECT count(*) FROM  "
				+ "		 V_NF_FNC  "
				+ 	 con ;
		try {
			return this.jdbcTemplate.queryForInt(query);
		} catch (Exception e) {
			return 0;
		}
	}

}
