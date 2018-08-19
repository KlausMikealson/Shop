package com.taotao.sso.service.impl;

import com.mysql.jdbc.StringUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private TbUserMapper userMapper;

    @Override
    public TaotaoResult checkData(String param, int type) {

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        //1——username, 2——phone, 3——email
        if(type==1)
            criteria.andUsernameEqualTo(param);
        else if(type==2)
            criteria.andPhoneEqualTo(param);
        else if(type==3)
            criteria.andEmailEqualTo(param);
        List<TbUser> list = userMapper.selectByExample(example);
        if(list ==  null || list.isEmpty())
            return TaotaoResult.ok(true);
        return TaotaoResult.ok(false);
    }

    @Override
    public TaotaoResult register(TbUser user) {
        //校验数据
        if(StringUtils.isNullOrEmpty(user.getUsername()) || StringUtils.isNullOrEmpty(user.getPassword()))
        {
            return TaotaoResult.build(400, "用户名或密码不能为空！");
        }
        TaotaoResult result = checkData(user.getUsername(), 1);
        if((boolean)result.getData()==false)
            return TaotaoResult.build(400, "用户名已存在！");
        if(user.getPhone()!=null)
        {
            result = checkData(user.getPhone(), 2);
            if((boolean)result.getData()==false)
                return TaotaoResult.build(400, "手机号已被占用！");
        }
        if(user.getEmail()!=null)
        {
            result = checkData(user.getPhone(), 3);
            if((boolean)result.getData()==false)
                return TaotaoResult.build(400, "邮箱已被占用！");
        }
        //插入数据
        user.setCreated(new Date());
        user.setUpdated(new Date());
        //对密码进行MD5加密
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
        return TaotaoResult.ok();
    }
}
