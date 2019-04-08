package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {
    @Autowired
    private RegisterService  registerService;

    @RequestMapping("/user/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback)
    {
        TaotaoResult result = registerService.checkData(param, type);
        if(!StringUtils.isBlank(callback))
        {
            //请求为jsonp调用，需要支持
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return result;
    }

    @RequestMapping(value = "user/register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult register(TbUser user)
    {
        return registerService.register(user);
    }

}
