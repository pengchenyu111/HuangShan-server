package cn.hfut.huangshan.controller;


import cn.hfut.huangshan.constants.ErrorCode;
import cn.hfut.huangshan.pojo.Admin;
import cn.hfut.huangshan.pojo.Tourist;
import cn.hfut.huangshan.response.ResultObj;
import cn.hfut.huangshan.service.AdminService;
import cn.hfut.huangshan.service.TouristService;
import cn.hfut.huangshan.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 登录
 * @author PCY
 */
@RestController
public class LoginController {

    @Autowired
    AdminService adminService;
    @Autowired
    TouristService touristService;

    /**
     * 登录请求
     * @param map 接收账号和密码
     * @return
     */
    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    public ResultObj login(@RequestBody Map<String,String> map){
        String account = map.get("account");
        String password = map.get("password");
        Admin admin = adminService.adminLogin(account, password);
        if (admin != null){
            return ResponseUtil.success(admin);
        } else {
            Tourist tourist = touristService.touristLogin(account, password);
            if (tourist != null){
                return ResponseUtil.success(tourist);
            }else {
                return ResponseUtil.error(ErrorCode.LOGIN_DATA_WRONG,ErrorCode.LOGIN_DATA_WRONG_MSG,null);
            }
        }
    }


}
