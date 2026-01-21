package com.example.mybaits.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybaits.entity.Users;
import com.example.mybaits.mapper.UsersMapper;
import com.example.mybaits.service.UsersService;
import org.springframework.stereotype.Service;

/**
 * @author chenyang
 * @description 针对表【users】的数据库操作Service实现
 * @createDate 2026-01-21 08:53:11
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
        implements UsersService {

}




