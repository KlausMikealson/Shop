package com.taotao.sso.service.impl;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.component.JedisClient;
import com.taotao.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_SESSION_KEY}")
    private String REDIS_SESSION_KEY;
    @Value("${SESSION_EXPIRE}")
    private Integer SESSION_EXPIRE;

    @Override
    public TaotaoResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        //校验用户名密码
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        if(list==null || list.isEmpty())
            return TaotaoResult.build(400, "用户名或密码错误！");
        TbUser user = list.get(0);
        if(!user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes())))
            return TaotaoResult.build(400, "用户名或密码错误！");
        //生成token
        String token = UUID.randomUUID().toString();
        //把用户信息写入redis
        user.setPassword(null);
        jedisClient.set(REDIS_SESSION_KEY+":"+token, JsonUtils.objectToJson(user));
        jedisClient.expire(REDIS_SESSION_KEY+":"+token, SESSION_EXPIRE);
        //写cookie
        CookieUtils.setCookie(request, response, "TT_TOKEN", token);
        return TaotaoResult.ok(token);
    }

    @Override
    public TaotaoResult getUserByToken(String token) {
        String json = jedisClient.get(REDIS_SESSION_KEY + ":" + token);
        if(StringUtils.isEmpty(json))
            return TaotaoResult.build(400, "用户session已经过期！");
        TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
        //更新session过期时间
        jedisClient.expire(REDIS_SESSION_KEY+":"+token, SESSION_EXPIRE);
        return TaotaoResult.ok(user);
    }
}
