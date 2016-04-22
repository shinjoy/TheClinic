package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.BbsComment;
import kr.nomad.mars.dto.MissionComment;
import kr.nomad.mars.dto.MissionComment;
public class MissionCommentDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper MissioncommentMapper = new RowMapper() {
		public MissionComment mapRow(ResultSet rs, int rowNum) throws SQLException {
			MissionComment missioncomment = new MissionComment();
			missioncomment.setMissionCommentSeq(rs.getInt("mission_comment_seq"));
			missioncomment.setMissionSeq(rs.getInt("mission_seq"));
			missioncomment.setBbsCategory(rs.getString("Bbs_category"));
			missioncomment.setBbsHeader(rs.getString("Bbs_header"));
			missioncomment.setUserId(rs.getString("user_id"));
			missioncomment.setMissionTitle(rs.getString("mission_title"));
			missioncomment.setMissionContents(rs.getString("mission_contents"));
			missioncomment.setFiles(rs.getString("files"));
			missioncomment.setLinkUrl(rs.getString("link_url"));
			missioncomment.setExtraContents(rs.getString("extra_contents"));
			missioncomment.setViewCount(rs.getInt("view_count"));
			missioncomment.setLikeCount(rs.getInt("like_count"));
			missioncomment.setDislikeCount(rs.getInt("dislike_count"));
			missioncomment.setReportCount(rs.getInt("report_count"));
			missioncomment.setFileCount(rs.getInt("file_count"));
			missioncomment.setCommentCount(rs.getInt("comment_count"));
			missioncomment.setRegDate(rs.getString("reg_date"));
			missioncomment.setRLevel(rs.getInt("r_level"));
			missioncomment.setRCommentSeq(rs.getInt("r_comment_seq"));
			missioncomment.setRRegDate(rs.getString("r_reg_date"));
			return missioncomment;
		}
	};

	private RowMapper VMissioncommentMapper = new RowMapper() {
		public MissionComment mapRow(ResultSet rs, int rowNum) throws SQLException {
			MissionComment missioncomment = new MissionComment();
			missioncomment.setMissionCommentSeq(rs.getInt("mission_comment_seq"));
			missioncomment.setMissionSeq(rs.getInt("mission_seq"));
			missioncomment.setBbsCategory(rs.getString("Bbs_category"));
			missioncomment.setBbsHeader(rs.getString("Bbs_header"));
			missioncomment.setUserId(rs.getString("user_id"));
			missioncomment.setMissionTitle(rs.getString("mission_title"));
			missioncomment.setMissionContents(rs.getString("mission_contents"));
			missioncomment.setFiles(rs.getString("files"));
			missioncomment.setLinkUrl(rs.getString("link_url"));
			missioncomment.setExtraContents(rs.getString("extra_contents"));
			missioncomment.setViewCount(rs.getInt("view_count"));
			missioncomment.setLikeCount(rs.getInt("like_count"));
			missioncomment.setDislikeCount(rs.getInt("dislike_count"));
			missioncomment.setReportCount(rs.getInt("report_count"));
			missioncomment.setFileCount(rs.getInt("file_count"));
			missioncomment.setCommentCount(rs.getInt("comment_count"));
			missioncomment.setRegDate(rs.getString("reg_date"));
			missioncomment.setRLevel(rs.getInt("r_level"));
			missioncomment.setRCommentSeq(rs.getInt("r_comment_seq"));
			missioncomment.setRRegDate(rs.getString("r_reg_date"));

			missioncomment.setUserName(rs.getString("user_name"));
			missioncomment.setBirthday(rs.getString("birthday"));
			missioncomment.setGender(rs.getInt("gender"));
			missioncomment.setOsType(rs.getString("os_type"));
			missioncomment.setOsVersion(rs.getString("os_version"));
			missioncomment.setAppVersion(rs.getString("app_version"));
			missioncomment.setDeviceName(rs.getString("device_name"));
			missioncomment.setDeviceId(rs.getString("device_id"));
			missioncomment.setPushkey(rs.getString("pushkey"));
			missioncomment.setUsePushservice(rs.getInt("use_pushservice"));
			missioncomment.setPhoto(rs.getString("photo"));
			return missioncomment;
		}
	};
	
	
	
	
	public void addMissionComment(final MissionComment MissionComment) {
		String query = "" +
				"INSERT INTO T_NF_MISSION_COMMENT " +
				"	(  mission_seq, Bbs_category, Bbs_header,"
				+ " user_id, mission_title, mission_contents,"
				+ " files, link_url, extra_contents,"
				+ " view_count, like_count, dislike_count,"
				+ " report_count, file_count, comment_count,"
				+ " reg_date, r_level, r_comment_seq,"
				+ " r_reg_date) " +
				"VALUES " +
				"	( ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " getDate(), ?, ?,"
				+ " getDate()) ";
		this.jdbcTemplate.update(query, new Object[] {
				
				MissionComment.getMissionSeq(),
				MissionComment.getBbsCategory(),
				MissionComment.getBbsHeader(),
				MissionComment.getUserId(),
				MissionComment.getMissionTitle(),
				MissionComment.getMissionContents(),
				MissionComment.getFiles(),
				MissionComment.getLinkUrl(),
				MissionComment.getExtraContents(),
				MissionComment.getViewCount(),
				MissionComment.getLikeCount(),
				MissionComment.getDislikeCount(),
				MissionComment.getReportCount(),
				MissionComment.getFileCount(),
				MissionComment.getCommentCount(),
			
				MissionComment.getRLevel(),
				MissionComment.getRCommentSeq()
		
		});
	}

	public void deleteMissionCommentId(String userId) {
		String query = "DELETE FROM T_NF_MISSION_COMMENT WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { userId });
	}

	public void updateMissionComment(MissionComment Missioncomment) { 
		String query = "" + 
				"UPDATE T_NF_MISSION_COMMENT SET " +
				
				"	Mission_seq = ?, " +
				"	Bbs_category = ?, " +
				"	Bbs_header = ?, " +
				"	user_id = ?, " +
				"	Mission_title = ?, " +
				"	Mission_contents = ?, " +
				"	files = ?, " +
				"	link_url = ?, " +
				"	extra_contents = ?, " +
				"	view_count = ?, " +
				"	like_count = ?, " +
				"	dislike_count = ?, " +
				"	report_count = ?, " +
				"	file_count = ?, " +
				"	comment_count = ?, " +
				"	r_level = ?, " +
				"	r_comment_seq = ? " +

				"WHERE Mission_comment_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] {
		
			Missioncomment.getMissionSeq(),
			Missioncomment.getBbsCategory(),
			Missioncomment.getBbsHeader(),
			Missioncomment.getUserId(),
			Missioncomment.getMissionTitle(),
			Missioncomment.getMissionContents(),
			Missioncomment.getFiles(),
			Missioncomment.getLinkUrl(),
			Missioncomment.getExtraContents(),
			Missioncomment.getViewCount(),
			Missioncomment.getLikeCount(),
			Missioncomment.getDislikeCount(),
			Missioncomment.getReportCount(),
			Missioncomment.getFileCount(),
			Missioncomment.getCommentCount(),
			Missioncomment.getRLevel(),
			Missioncomment.getRCommentSeq(),
			Missioncomment.getMissionCommentSeq(),

		});
	}

	public MissionComment getMissionComment(int Mission_comment_seq) {
		String query = "" + 
				"SELECT * " +
				"FROM V_NF_MISSION_COMMENT " +
				"WHERE Mission_comment_seq = ? ";
		return (MissionComment)this.jdbcTemplate.queryForObject(query, new Object[] { Mission_comment_seq }, this.VMissioncommentMapper);
	}

	public List<MissionComment> getMissionCommentList(int Mission_seq,int Mission_comment_seq,int page, int itemCountPerPage) {
		String con ="";
		if(Mission_comment_seq==0){//댓글
			con += " and r_level = 0";
		}else{ //댓글에 댓글 
			con += " and r_level > 0 and  r_comment_seq =  "+Mission_comment_seq;	
		}
		String query = "" 
				+ "	SELECT * FROM ( "
				+ "		SELECT "
				+ "			ROW_NUMBER() OVER(order by reg_date asc) as row_seq, "
				+ "			A.* "
				+ "		FROM V_NF_MISSION_COMMENT AS A where Mission_seq = ? "+con
				+ "	) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		return (List<MissionComment>)this.jdbcTemplate.query(query, new Object[] { Mission_seq }, this.VMissioncommentMapper);
	}
	
	public int getMissionCommentCount(int MissionSeq,int MissionCommentSeq ) {
		String con ="";
		if(MissionCommentSeq==0){//댓글
			con += " and r_level = 0";
		}else{ //댓글에 댓글 
			con += " and r_level > 0";	
		}
		String query = "" 
				+ "	SELECT count(*) FROM  "
				+ "		 V_NF_MISSION_COMMENT where Mission_seq = ?  "+con;
		return this.jdbcTemplate.queryForInt(query ,new Object[] { MissionSeq });
	}
	
	public List<MissionComment> getMissionCommentListAll(int MissionSeq) {
		String query = "" 
				+ "	SELECT * FROM  "
				+ "	 V_NF_MISSION_COMMENT  where Mission_seq = ? ";
		return (List<MissionComment>)this.jdbcTemplate.query(query,new Object[] { MissionSeq }, this.VMissioncommentMapper);
	}
	public List<MissionComment> getMissionCommentListMy(String userId) {
		String query = "" 
				+ "	SELECT * FROM  "
				+ "	 V_NF_MISSION_COMMENT  where user_id = ? ";
		return (List<MissionComment>)this.jdbcTemplate.query(query,new Object[] { userId }, this.VMissioncommentMapper);
	}	

	public void deleteMissionCommentAll(int MissionSeq) {
		String query = "DELETE FROM T_NF_MISSION_COMMENT WHERE Mission_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { MissionSeq });
	}
	
	public void deleteMissionComment(int MissionCommentSeq) {
		String query = "DELETE FROM T_NF_MISSION_COMMENT WHERE mission_comment_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { MissionCommentSeq });
	}
	
	public void deleteRMissionComment(int MissionCommentSeq) {
		String query = "DELETE FROM T_NF_MISSION_COMMENT WHERE r_comment_seq = ? ";
		this.jdbcTemplate.update(query, new Object[] { MissionCommentSeq });
	}
	public List<MissionComment> getMissionCommentListComment(int MissionCommentSeq) {
		String query = "" 
				+ "	SELECT * FROM  "
				+ "	 V_NF_MISSION_COMMENT  where r_comment_seq = ? ";
		return (List<MissionComment>)this.jdbcTemplate.query(query,new Object[] { MissionCommentSeq }, this.MissioncommentMapper);
	}
	
	//관리자댓글리스트 
	public List<MissionComment> getMissionList(int MissionSeq, int page, int itemCountPerPage) {
		String query = ""
	            + "    SELECT * FROM ( "
	            + "        SELECT "
	            + "            ROW_NUMBER() OVER(order by case when r_comment_seq = 0 then mission_comment_seq else r_comment_seq end asc, r_level asc) as row_seq, * "
	            + "        FROM V_NF_MISSION_COMMENT WHERE Mission_seq = ? "
	    		+ "   ) AS a WHERE row_seq BETWEEN (("+page+" - 1) * "+itemCountPerPage+") +1 AND "+page+" * "+itemCountPerPage+" ";
		try {
			return (List<MissionComment>)this.jdbcTemplate.query(query, new Object[] {MissionSeq}, this.VMissioncommentMapper);
		} catch (Exception e) {
			return null;
		}
		
	}
	//관리자댓글카운트
	public int getMissionCount(int MissionSeq) {
		String query = ""
	            + "    SELECT count(*) FROM  "
	            + "         V_NF_MISSION_COMMENT WHERE Mission_seq = ? ";
		try {
			return this.jdbcTemplate.queryForInt(query, new Object[] {MissionSeq});
		} catch (Exception e) {
			return 0;
		}
		
	}
	
	
	public List<MissionComment> getMissionRCommentListComment(int missionCommentSeq) {
		String query = "" 
				+ "	SELECT * FROM  "
				+ "	 V_NF_MISSION_COMMENT  where r_comment_seq = ? ";
		return (List<MissionComment>)this.jdbcTemplate.query(query,new Object[] { missionCommentSeq }, this.VMissioncommentMapper);
	}
}
