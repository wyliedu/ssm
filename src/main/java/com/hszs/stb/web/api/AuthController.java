package com.hszs.stb.web.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hszs.stb.common.ApiCode;
import com.hszs.stb.common.exception.ServiceException;
import com.hszs.stb.common.helper.RequestHelper;
import com.hszs.stb.model.api.AccessToken;
import com.hszs.stb.model.api.ApiResponseBody;
import com.hszs.stb.model.api.LoginView;
import com.hszs.stb.model.api.UserInfo;
import com.hszs.stb.model.auth.Msgcode;
import com.hszs.stb.model.enums.AppType;
import com.hszs.stb.model.home.ParameterInfo;
import com.hszs.stb.model.rly.SMSResponse.TemplateSMS;
import com.hszs.stb.service.services.api.AuthService;
import com.hszs.stb.service.services.api.RonglianyunService;
import com.hszs.stb.service.services.home.HomeService;
import com.hszs.stb.web.home.AbstractController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = {"/apis/auth"})
public class AuthController extends AbstractController {

    static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthService authService;
    @Autowired
    RonglianyunService ronglianyunService;
	@Autowired
    private HomeService homeService;

    /**
     * 用户登录
     * @param account
     * @param passwd
     * @param deviceId
     * @param apptypeVal
     * @param getuiCid
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
/*    @RequestMapping(value = {"/login.action"}, method = {GET, POST})
    @ResponseBody
    public ApiResponseBody actionLogin(
            @RequestParam("account") String account,
            @RequestParam("passwd") String passwd,
            @RequestParam("deviceid") String deviceId,
            @RequestParam("apptype") int apptypeVal,
            @RequestParam(value = "getuicid", required = false) String getuiCid,

            HttpServletRequest request, HttpServletResponse response) throws Exception {
        final AppType apptype = AppType.of(apptypeVal);
        if (apptype == null) {
            throw new ServiceException(ApiCode.ERR_WRONG_PARAMS, "参数apptype应该取值1，2，3");
        }
        final UserInfo sui = authService.login(account, passwd, deviceId, apptype, getuiCid);
        final AccessToken atoken = AccessToken.of(sui, deviceId, RequestHelper.getRemoteAddr(request), apptype.value);
        List<BankInfo> bankInfos = new ArrayList<>();
        if(sui.OutletId!=null){
            for(Integer outletid:sui.OutletId) {
                OutletInfo outlet = outletService.getOutletInfo(outletid);
                OperateInfo operateinfo = outletService.getOperateInfo(outletid);
                BankInfo bankInfo = new BankInfo();
                bankInfo.outlet = outlet;
                bankInfo.operateinfo = operateinfo;
                bankInfos.add(bankInfo);
            }
        }


        final LoginView lview = LoginView.of(sui, atoken.toToken());

        //lview.verifyInfo = userService.getUserVerifyInfo(sui.uid);

        return ApiResponseBody.createSuccessBody(lview);
    }*/
    

	

	

	
	/**
	 * 师傅添加邀请
	 * @param payphone
	 * @param teamid
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/seniorSendMsgcode.action"}, method = {GET, POST })
	@ResponseBody
	public ApiResponseBody seniorSendMsgcode(
			@RequestParam("payphone")String payphone,
			@RequestParam("teamid")int teamid,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msgcode = this.authService.seniorSendMsgcode(payphone, teamid);
		TemplateSMS tsms = ronglianyunService.sendVerifyCodeSMS(payphone, msgcode);
		if(tsms == null) {
			throw new ServiceException(ApiCode.ERR_NO_AUTHORITY, "获取验证码失败，荣联运接口限制发送");
		}
		return ApiResponseBody.createSuccessBody(true);
	}
	
	@RequestMapping(value = {"/test.action/{payphone}"}, method = {GET, POST },produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ApiResponseBody test(
			@PathVariable String payphone,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return ApiResponseBody.createSuccessBody(payphone);
	}
	
	/**
	 * 测试分页查询
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/test.action/{pageNo}/{pageSize}"}, method = {GET, POST },produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ApiResponseBody test3(
			@PathVariable Integer pageNo,
			@PathVariable Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//在你需要进行分页的Mybatis方法前调用PageHelper.startPage静态方法即可，紧跟在这个方法后的第一个Mybatis查询方法会被进行分页。
	    pageNo = pageNo == null?1:pageNo;
	    pageSize = pageSize == null?1:pageSize;
	    PageHelper.startPage(pageNo, pageSize);
		List<ParameterInfo> list = this.homeService.queryParameterByCode("education");
		//用PageInfo对结果进行包装
		PageInfo page = new PageInfo(list);
		System.out.println(page.getPageNum());
	    System.out.println(page.getPageSize());
	    System.out.println(page.getStartRow());
	    System.out.println(page.getEndRow());
	    System.out.println(page.getTotal());
	    System.out.println(page.getPages());
	    System.out.println(page.getFirstPage());
	    System.out.println(page.getLastPage());
	    System.out.println(page.isHasPreviousPage());
	    System.out.println(page.isHasNextPage());
		return ApiResponseBody.createSuccessBody(list);
	}
	
	/**
     * 上传头像
     * @param userid 徒弟/师傅/承包商员工编号
     * @param category 人员类别徒弟1/师傅2/承包商员工3
     * @param portraitAddress 头像图片oss地址
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/updatePortrait.action"}, method = {GET, POST})
    @ResponseBody
    public ApiResponseBody updatePortrait(
    		@RequestParam("userid") int userid,
            @RequestParam("category") int category,
            @RequestParam("portraitAddress") String portraitAddress,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
    	this.authService.updatePortrait(userid, category, portraitAddress);
        return ApiResponseBody.createSuccessBody(true);
    }
}
