package kr.nomad.mars;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import kr.nomad.mars.dao.MissionDao;
import kr.nomad.mars.dao.PushDao;
import kr.nomad.mars.dto.Mission;
import kr.nomad.mars.dto.Push;
import kr.nomad.util.T;

public class SchedularController {

	@Autowired MissionDao missionDao;
	@Autowired PushDao pushDao;
	@Scheduled(cron = "1 * * * * *") 
	public void missionPushGo() {
	
		String time = T.getNowFomat2();
		String week = T.getWeekday();
		time = time.replace("오전", "AM").replace("오후", "PM");
		System.out.println(time);
		List<Mission>list = missionDao.getMissionPushList(time,week); 
		for(Mission ms : list){

			if (ms.getPushkey().equals("") == false&&ms.getUsePushservice()==1) {
			
				Push push = new Push();
				push.setBadge(1);
				push.setOs(ms.getOsType());
				push.setPushKey(ms.getPushkey());
				push.setMsgType(Push.MSG_TYPE_ALARM_MISSION);
				push.setUserid(ms.getUserId());
				push.setStatus(0);
				push.setServiceId("EFOODY");
				push.setPushType(1);							
				push.setMsg("Mission Time! :"+txtLength(ms.getTitle()));
				push.setMsgKey("0");
				pushDao.addPush(push);
			}
		}
		
		   
	
	}

	// 내용 자르기
	public String txtLength(String contents) {

		if (contents.length() > 5) {
			contents = contents.substring(0, 5) + "...";
		}
		return contents;
	}

}
