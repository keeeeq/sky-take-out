package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sky.constant.MessageConstant;
import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.exception.LoginFailedException;
import com.sky.mapper.UserMapper;
import com.sky.properties.WeChatProperties;
import com.sky.service.UserService;
import com.sky.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private WeChatProperties wechatProperties;
    @Autowired
    private UserMapper userMapper;
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    public User wxlogin(UserLoginDTO userLoginDTO) {
        //调用微信接口，获取openid
        String openid = getOpenId(userLoginDTO.getCode());
        //判断openid是否可用
         if(openid==null){
                    throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
                }
        //判断是否新用户
        User user = userMapper.getByOpenid(openid);
        //是，注册
        if(user==null){
            user=User.builder().openid(openid).createTime(LocalDateTime.now()).build();
            userMapper.insert(user);
        }
        //返回用户对象
        return user;
    }
    private String getOpenId(String code){
        //调用微信接口，获取openid
        Map<String,String> map=new HashMap<>();
        map.put("appid",wechatProperties.getAppid());
        map.put("secret",wechatProperties.getSecret());
        map.put("js_code",code);
        map.put("grant_type","authorization_code");
        String s = HttpClientUtil.doGet(WX_LOGIN_URL, map);
        JSONObject jsonObject = JSON.parseObject(s);
        String openid = jsonObject.getString("openid");
        return openid;
    }
}
