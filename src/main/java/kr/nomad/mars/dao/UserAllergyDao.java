package kr.nomad.mars.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.nomad.mars.dto.UserAllergy;
public class UserAllergyDao {
	
	private JdbcTemplate jdbcTemplate;
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	private RowMapper userallergyMapper = new RowMapper() {
		public UserAllergy mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserAllergy userallergy = new UserAllergy();
			userallergy.setUserId(rs.getString("user_id"));
			userallergy.setFrequentUrination(rs.getInt("Frequent_urination"));
			userallergy.setHotUrine(rs.getInt("hot_urine"));
			userallergy.setUrinaryTractInfection(rs.getInt("urinary_tract_infection"));
			userallergy.setVaginitis(rs.getInt("vaginitis"));
			userallergy.setPruritus(rs.getInt("pruritus"));
			userallergy.setAcne(rs.getInt("acne"));
			userallergy.setSkinRash(rs.getInt("skin_rash"));
			userallergy.setHives(rs.getInt("hives"));
			userallergy.setPallorDry(rs.getInt("pallor_dry"));
			userallergy.setCrack(rs.getInt("crack"));
			userallergy.setWhim(rs.getInt("whim"));
			userallergy.setAnger(rs.getInt("anger"));
			userallergy.setStress(rs.getInt("stress"));
			userallergy.setDepression(rs.getInt("depression"));
			userallergy.setCraving(rs.getInt("craving"));
			userallergy.setVoracity(rs.getInt("voracity"));
			userallergy.setTiredness(rs.getInt("tiredness"));
			userallergy.setDecreasedConcentration(rs.getInt("decreased_concentration"));
			userallergy.setHypersensitivity(rs.getInt("Hypersensitivity"));
			userallergy.setSevereEmotional(rs.getInt("severe_emotional"));
			userallergy.setSevereWeight(rs.getInt("severe_weight"));
			userallergy.setEczema(rs.getInt("eczema"));
			userallergy.setRash(rs.getInt("rash"));
			userallergy.setPimple(rs.getInt("pimple"));
			userallergy.setSweat(rs.getInt("sweat"));
			userallergy.setMuscleTremors(rs.getInt("muscle_tremors"));
			userallergy.setMusclePain(rs.getInt("muscle_pain"));
			userallergy.setLessMuscle(rs.getInt("less_muscle"));
			userallergy.setHyperglycemia(rs.getInt("Hyperglycemia"));
			userallergy.setHypoglycemia(rs.getInt("Hypoglycemia"));
			userallergy.setHeadache(rs.getInt("headache"));
			userallergy.setDizziness(rs.getInt("dizziness"));
			userallergy.setRhinitis(rs.getInt("rhinitis"));
			userallergy.setMigraine(rs.getInt("migraine"));
			userallergy.setTiredness2(rs.getInt("tiredness2"));
			userallergy.setEarInfections(rs.getInt("ear_infections"));
			userallergy.setCloggedNose(rs.getInt("clogged_nose"));
			userallergy.setSoreThroat(rs.getInt("sore_throat"));
			userallergy.setSinusitis(rs.getInt("sinusitis"));
			userallergy.setCough(rs.getInt("cough"));
			userallergy.setEyelid(rs.getInt("eyelid"));
			userallergy.setCold(rs.getInt("cold"));
			userallergy.setAsthma(rs.getInt("asthma"));
			userallergy.setArrhythmias(rs.getInt("Arrhythmias"));
			userallergy.setWheeze(rs.getInt("wheeze"));
			userallergy.setIndigestion(rs.getInt("indigestion"));
			userallergy.setBurp(rs.getInt("burp"));
			userallergy.setThrowUp(rs.getInt("throw_up"));
			userallergy.setIrritableBowelSyndrome(rs.getInt("irritable_bowel_syndrome"));
			userallergy.setDiarrhea(rs.getInt("diarrhea"));
			userallergy.setConstipation(rs.getInt("constipation"));
			userallergy.setAbdomenGas(rs.getInt("abdomen_gas"));
			userallergy.setUlcer(rs.getInt("ulcer"));
			userallergy.setStomachCramps(rs.getInt("stomach_cramps"));
			userallergy.setAnusItch(rs.getInt("anus_itch"));
			userallergy.setStomachache(rs.getInt("stomachache"));
			userallergy.setBabyStomachache(rs.getInt("baby_stomachache"));
			userallergy.setMyalgia(rs.getInt("myalgia"));
			userallergy.setArthralgia(rs.getInt("arthralgia"));
			userallergy.setArthritis(rs.getInt("arthritis"));
			return userallergy;
		}
	};

	public void addUserAllergy(final UserAllergy userallergy) {
		String query = "" +
				"INSERT INTO T_NF_USER_ALLERGY " +
				"	(user_id, Frequent_urination, hot_urine,"
				+ " urinary_tract_infection, vaginitis, pruritus,"
				+ " acne, skin_rash, hives,"
				+ " pallor_dry, crack, whim,"
				+ " anger, stress, depression,"
				+ " craving, voracity, tiredness,"
				+ " decreased_concentration, Hypersensitivity, severe_emotional,"
				+ " severe_weight, eczema, rash,"
				+ " pimple, sweat, muscle_tremors,"
				+ " muscle_pain, less_muscle, Hyperglycemia,"
				+ " Hypoglycemia, headache, dizziness,"
				+ " rhinitis, migraine, tiredness2,"
				+ " ear_infections, clogged_nose, sore_throat,"
				+ " sinusitis, cough, eyelid,"
				+ " cold, asthma, Arrhythmias,"
				+ " wheeze, indigestion, burp,"
				+ " throw_up, irritable_bowel_syndrome, diarrhea,"
				+ " constipation, abdomen_gas, ulcer,"
				+ " stomach_cramps, anus_itch, stomachache,"
				+ " baby_stomachache, myalgia, arthralgia, arthritis) " +
				"VALUES " +
				"	(?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?,"
				+ " ?, ?, ?, ?) ";
		this.jdbcTemplate.update(query, new Object[] {
			userallergy.getUserId(),
			userallergy.getFrequentUrination(),
			userallergy.getHotUrine(),
			userallergy.getUrinaryTractInfection(),
			userallergy.getVaginitis(),
			userallergy.getPruritus(),
			userallergy.getAcne(),
			userallergy.getSkinRash(),
			userallergy.getHives(),
			userallergy.getPallorDry(),
			userallergy.getCrack(),
			userallergy.getWhim(),
			userallergy.getAnger(),
			userallergy.getStress(),
			userallergy.getDepression(),
			userallergy.getCraving(),
			userallergy.getVoracity(),
			userallergy.getTiredness(),
			userallergy.getDecreasedConcentration(),
			userallergy.getHypersensitivity(),
			userallergy.getSevereEmotional(),
			userallergy.getSevereWeight(),
			userallergy.getEczema(),
			userallergy.getRash(),
			userallergy.getPimple(),
			userallergy.getSweat(),
			userallergy.getMuscleTremors(),
			userallergy.getMusclePain(),
			userallergy.getLessMuscle(),
			userallergy.getHyperglycemia(),
			userallergy.getHypoglycemia(),
			userallergy.getHeadache(),
			userallergy.getDizziness(),
			userallergy.getRhinitis(),
			userallergy.getMigraine(),
			userallergy.getTiredness2(),
			userallergy.getEarInfections(),
			userallergy.getCloggedNose(),
			userallergy.getSoreThroat(),
			userallergy.getSinusitis(),
			userallergy.getCough(),
			userallergy.getEyelid(),
			userallergy.getCold(),
			userallergy.getAsthma(),
			userallergy.getArrhythmias(),
			userallergy.getWheeze(),
			userallergy.getIndigestion(),
			userallergy.getBurp(),
			userallergy.getThrowUp(),
			userallergy.getIrritableBowelSyndrome(),
			userallergy.getDiarrhea(),
			userallergy.getConstipation(),
			userallergy.getAbdomenGas(),
			userallergy.getUlcer(),
			userallergy.getStomachCramps(),
			userallergy.getAnusItch(),
			userallergy.getStomachache(),
			userallergy.getBabyStomachache(),
			userallergy.getMyalgia(),
			userallergy.getArthralgia(),
			userallergy.getArthritis()
		});
	}

	public void deleteUserAllergy(String user_id) {
		String query = "DELETE FROM T_NF_USER_ALLERGY WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] { user_id });
	}

	public void updateUserAllergy(UserAllergy userallergy) { 
		String query = "" + 
				"UPDATE T_NF_USER_ALLERGY SET " +
			
				"	Frequent_urination = ?, " +
				"	hot_urine = ?, " +
				"	urinary_tract_infection = ?, " +
				"	vaginitis = ?, " +
				"	pruritus = ?, " +
				"	acne = ?, " +
				"	skin_rash = ?, " +
				"	hives = ?, " +
				"	pallor_dry = ?, " +
				"	crack = ?, " +
				"	whim = ?, " +
				"	anger = ?, " +
				"	stress = ?, " +
				"	depression = ?, " +
				"	craving = ?, " +
				"	voracity = ?, " +
				"	tiredness = ?, " +
				"	decreased_concentration = ?, " +
				"	Hypersensitivity = ?, " +
				"	severe_emotional = ?, " +
				"	severe_weight = ?, " +
				"	eczema = ?, " +
				"	rash = ?, " +
				"	pimple = ?, " +
				"	sweat = ?, " +
				"	muscle_tremors = ?, " +
				"	muscle_pain = ?, " +
				"	less_muscle = ?, " +
				"	Hyperglycemia = ?, " +
				"	Hypoglycemia = ?, " +
				"	headache = ?, " +
				"	dizziness = ?, " +
				"	rhinitis = ?, " +
				"	migraine = ?, " +
				"	tiredness2 = ?, " +
				"	ear_infections = ?, " +
				"	clogged_nose = ?, " +
				"	sore_throat = ?, " +
				"	sinusitis = ?, " +
				"	cough = ?, " +
				"	eyelid = ?, " +
				"	cold = ?, " +
				"	asthma = ?, " +
				"	Arrhythmias = ?, " +
				"	wheeze = ?, " +
				"	indigestion = ?, " +
				"	burp = ?, " +
				"	throw_up = ?, " +
				"	irritable_bowel_syndrome = ?, " +
				"	diarrhea = ?, " +
				"	constipation = ?, " +
				"	abdomen_gas = ?, " +
				"	ulcer = ?, " +
				"	stomach_cramps = ?, " +
				"	anus_itch = ?, " +
				"	stomachache = ?, " +
				"	baby_stomachache = ?, " +
				"	myalgia = ?, " +
				"	arthralgia = ?, " +
				"	arthritis = ? " +
				"WHERE user_id = ? ";
		this.jdbcTemplate.update(query, new Object[] {
		
			userallergy.getFrequentUrination(),
			userallergy.getHotUrine(),
			userallergy.getUrinaryTractInfection(),
			userallergy.getVaginitis(),
			userallergy.getPruritus(),
			userallergy.getAcne(),
			userallergy.getSkinRash(),
			userallergy.getHives(),
			userallergy.getPallorDry(),
			userallergy.getCrack(),
			userallergy.getWhim(),
			userallergy.getAnger(),
			userallergy.getStress(),
			userallergy.getDepression(),
			userallergy.getCraving(),
			userallergy.getVoracity(),
			userallergy.getTiredness(),
			userallergy.getDecreasedConcentration(),
			userallergy.getHypersensitivity(),
			userallergy.getSevereEmotional(),
			userallergy.getSevereWeight(),
			userallergy.getEczema(),
			userallergy.getRash(),
			userallergy.getPimple(),
			userallergy.getSweat(),
			userallergy.getMuscleTremors(),
			userallergy.getMusclePain(),
			userallergy.getLessMuscle(),
			userallergy.getHyperglycemia(),
			userallergy.getHypoglycemia(),
			userallergy.getHeadache(),
			userallergy.getDizziness(),
			userallergy.getRhinitis(),
			userallergy.getMigraine(),
			userallergy.getTiredness2(),
			userallergy.getEarInfections(),
			userallergy.getCloggedNose(),
			userallergy.getSoreThroat(),
			userallergy.getSinusitis(),
			userallergy.getCough(),
			userallergy.getEyelid(),
			userallergy.getCold(),
			userallergy.getAsthma(),
			userallergy.getArrhythmias(),
			userallergy.getWheeze(),
			userallergy.getIndigestion(),
			userallergy.getBurp(),
			userallergy.getThrowUp(),
			userallergy.getIrritableBowelSyndrome(),
			userallergy.getDiarrhea(),
			userallergy.getConstipation(),
			userallergy.getAbdomenGas(),
			userallergy.getUlcer(),
			userallergy.getStomachCramps(),
			userallergy.getAnusItch(),
			userallergy.getStomachache(),
			userallergy.getBabyStomachache(),
			userallergy.getMyalgia(),
			userallergy.getArthralgia(),
			userallergy.getArthritis(),
			userallergy.getUserId()
		});
	}

	public int getUserAllergyChk(String user_id) {
		String query = "" + 
				"SELECT count(*) " +
				"FROM T_NF_USER_ALLERGY " +
				"WHERE user_id = ? ";
		return this.jdbcTemplate.queryForInt(query, new Object[] { user_id });
	}
	public UserAllergy getUserAllergy(String user_id) {
		String query = "" + 
				"SELECT * " +
				"FROM T_NF_USER_ALLERGY " +
				"WHERE user_id = ? ";
		try{
			return (UserAllergy)this.jdbcTemplate.queryForObject(query, new Object[] { user_id }, this.userallergyMapper);
		}catch(Exception e){
			return new UserAllergy();
		}
	}

	public List<UserAllergy> getUserAllergyList(int page, int itemCountPerPage) {
		String query = "" +
				"SELECT TOP "+ itemCountPerPage +" user_id, Frequent_urination, hot_urine, urinary_tract_infection, vaginitis, pruritus, acne, skin_rash, hives, pallor_dry, crack, whim, anger, stress, depression, craving, voracity, tiredness, decreased_concentration, Hypersensitivity, severe_emotional, severe_weight, eczema, rash, pimple, sweat, muscle_tremors, muscle_pain, less_muscle, Hyperglycemia, Hypoglycemia, headache, dizziness, rhinitis, migraine, tiredness2, ear_infections, clogged_nose, sore_throat, sinusitis, cough, eyelid, cold, asthma, Arrhythmias, wheeze, indigestion, burp, throw_up, irritable_bowel_syndrome, diarrhea, constipation, abdomen_gas, ulcer, stomach_cramps, anus_itch, stomachache, baby_stomachache, myalgia, arthralgia, arthritis " +
				"FROM T_NF_USER_ALLERGY " +
				"WHERE user_id <= ( " +
				"	SELECT MIN(user_id) " +
				"	FROM ( " +
				"		SELECT TOP "+ ((page-1) * itemCountPerPage + 1) +" user_id " +
				"		FROM T_NF_USER_ALLERGY " +
				"		ORDER BY user_id DESC " +
				"	) AS A) " +
				"ORDER BY user_id DESC";
		return (List<UserAllergy>)this.jdbcTemplate.query(query, this.userallergyMapper);
	}

}
