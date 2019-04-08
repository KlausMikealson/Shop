package com.taotao.portal.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
public class UserServiceImpl implements UserService {

    @Value("${SS0_BASE_URL}")
    private String SS0_BASE_URL;
    @Value("${SSO_USER_TOKEN_SERVICE}")
    private String SSO_USER_TOKEN_SERVICE;

    @Override
    public TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
        if(StringUtils.isEmpty(token))
        {
            return null;
        }
        String json = HttpClientUtil.doGet(SS0_BASE_URL+SSO_USER_TOKEN_SERVICE+token);
        TaotaoResult result = TaotaoResult.format(json);
        if(result.getStatus()!=200)
            return null;
        result = TaotaoResult.formatToPojo(json, TbUser.class);
        TbUser user = (TbUser) result.getData();
        return user;

    }
}
