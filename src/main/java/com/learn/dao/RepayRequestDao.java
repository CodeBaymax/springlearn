package com.learn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.entity.RepayRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepayRequestDao extends BaseMapper<RepayRequest> {

    RepayRequest selReq(@Param("param")RepayRequest param);
}
