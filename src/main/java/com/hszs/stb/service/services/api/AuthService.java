package com.hszs.stb.service.services.api;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hszs.stb.common.ApiCode;
import com.hszs.stb.common.exception.ServiceException;
import com.hszs.stb.common.helper.PwdHelper;
import com.hszs.stb.dao.SysAccountDAO;
import com.hszs.stb.model.api.UserInfo;
import com.hszs.stb.model.auth.Msgcode;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    SysAccountDAO sysAccountDAO;
    /**
     * 登陆
     *
     * @param account
     * @param passwd
     * @param deviceId
     * @param apptype
     * @param getuiCid
     * @return
     */
/*    public UserInfo login(String account, String passwd, String deviceId, final AppType apptype, final String getuiCid) {
        UserInfo userInfo = new UserInfo();
        if (apptype == AppType.apprentice) {   //徒弟app
        	Apprentice apprentice= sysApprenticeDAO.selectByUsername(account);
            if (apprentice==null) {
                throw new ServiceException(ApiCode.ERR_PWD_NOTMATCH, "用户不存在");
            }
            if (!StringUtils.equals(apprentice.getPassword(), passwd) && !StringUtils.equalsIgnoreCase(apprentice.getPassword(), PwdHelper.hash(account, passwd))) {
                throw new ServiceException(ApiCode.ERR_PWD_NOTMATCH, "用户名或者密码不正确");
            }
            boolean deviceChanged = !StringUtils.equals(deviceId, apprentice.getDeviceid());
            if (deviceChanged) {
                //pushKickoffMessage(deviceId, user.getGetuicid(), user.getId(), user.getDeviceid());
            }
            userInfo.uid = apprentice.getApprenticeid();
            userInfo.name = apprentice.getName();
            userInfo.account = apprentice.getPayphone();
            userInfo.authcode = RandomUtils.nextInt();
            userInfo.passwd = apprentice.getPassword();
            userInfo.teamid = apprentice.getTeamid();
            userInfo.portrait = apprentice.getPortrait();
            sysApprenticeDAO.updateDeviceIdAndPushid(deviceId, getuiCid,apprentice.getApprenticeid());
            sysApprenticeDAO.updatePushid(deviceId, apprentice.getApprenticeid());
          
        } else if (apptype == AppType.senior) {   //师傅app
        	Senior senior= sysSeniorDAO.selectByUsername(account);
            if (senior==null) {
                throw new ServiceException(ApiCode.ERR_PWD_NOTMATCH, "用户不存在");
            }
            if (!StringUtils.equals(senior.getPassword(), passwd) && !StringUtils.equalsIgnoreCase(senior.getPassword(), PwdHelper.hash(account, passwd))) {
                throw new ServiceException(ApiCode.ERR_PWD_NOTMATCH, "用户名或者密码不正确");
            }
            boolean deviceChanged = !StringUtils.equals(deviceId, senior.getDeviceid());
            if (deviceChanged) {
                //pushKickoffMessage(deviceId, user.getGetuicid(), user.getId(), user.getDeviceid());
            }
            userInfo.uid = senior.getTeamid();
            userInfo.name = senior.getName();
            userInfo.account = senior.getPayphone();
            userInfo.authcode = RandomUtils.nextInt();
            userInfo.passwd = senior.getPassword();
            userInfo.teamid = senior.getTeamid();
            userInfo.teamname = senior.getTeamname();
            userInfo.portrait = senior.getPortrait();
            sysSeniorDAO.updateDeviceIdAndPushid(deviceId, getuiCid,senior.getTeamid());
            sysSeniorDAO.updatePushid(deviceId, senior.getTeamid());
        } else if (apptype == AppType.contractor) {   //承包商app
        	Employee employee = sysEmployeeDAO.selectByUsername(account);
            if (employee==null) {
                throw new ServiceException(ApiCode.ERR_PWD_NOTMATCH, "用户不存在");
            }
            if (!StringUtils.equals(employee.getPassword(), passwd) && !StringUtils.equalsIgnoreCase(employee.getPassword(), PwdHelper.hash(account, passwd))) {
                throw new ServiceException(ApiCode.ERR_PWD_NOTMATCH, "用户名或者密码不正确");
            }
            userInfo.uid = employee.getEmployeeid();
            userInfo.name = employee.getName();
            userInfo.account = employee.getPayphone();
            userInfo.authcode = RandomUtils.nextInt();
            userInfo.passwd = employee.getPassword();
            userInfo.portrait = employee.getPortrait();
            sysSeniorDAO.updateDeviceIdAndPushid(deviceId, getuiCid,employee.getEmployeeid());
            sysSeniorDAO.updatePushid(deviceId, employee.getEmployeeid());
        }
        //yuntuService.updateYuntuidIfNeed(user);
        return userInfo;
    }*/

    /**
     * 更换手机登陆踢出之前登陆的
     *
     * @param deviceId
     * @param cid
     * @param userId
     * @param userDeviceid
     */
/*    public void pushKickoffMessage(String deviceId, String cid, int userId, String userDeviceid) {
        try {
            //final String cid = user.getGetuicid();
            if (StringUtils.isNotEmpty(cid)) {
                logger.info("设备变化，推送消息给老设备：{},{},{},{}", userId, userDeviceid, cid, deviceId);
                final KickoffTransmission ktrans = new KickoffTransmission(userId, deviceId, userDeviceid);
                final AbstractTemplate template = getuiService.truansmissionTemplate(PushMessageType.Kickoff.create(ktrans));

                getuiService.pushToUsers(template, Lists.newArrayList(cid), false);
            } else {
                logger.info("没有个推ID，无法推送踢出消息：{},{},{},{}", userId, userDeviceid, cid, deviceId);
            }
        } catch (Exception ex) {
            logger.info("推送踢出消息失败：{}", ex);
        }
    }*/
  	
	//添加邀请
	public String seniorSendMsgcode(String payphone,int teamid){
		this.sysAccountDAO.deleteMsgcode(payphone);
		final int vode = RandomUtils.nextInt(899999)+100000;
		String msgcode = String.format("%6d", vode);
		this.sysAccountDAO.addMsgcode(teamid, payphone, msgcode);
		return msgcode;
	}
	
	//上传头像
	public void updatePortrait(int userid,int category,String portraitAddress){
		if(category==1){
			this.sysAccountDAO.updateApprenticePortrait(userid, portraitAddress);
		}else if(category==2){
			this.sysAccountDAO.updateSeniorPortrait(userid, portraitAddress);
		}else{
			this.sysAccountDAO.updateEmployeePortrait(userid, portraitAddress);
		}
  		
  	}
}
