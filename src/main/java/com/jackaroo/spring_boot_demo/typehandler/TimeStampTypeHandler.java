package com.jackaroo.spring_boot_demo.typehandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author JackarooZhang
 * @date 2018/6/7 19:51
 * 由于在MySQL中unix_timestamp(now()) 返回的是10位的时间戳，但是在Java中的Date对象使用的是13位的时间戳，
 * 所以两者之间需要进行转换
 */
public class TimeStampTypeHandler implements TypeHandler<Long> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Long aLong, JdbcType jdbcType) throws SQLException {
        preparedStatement.setLong(i, aLong);
    }

    @Override
    public Long getResult(ResultSet resultSet, String s) throws SQLException {
        long timestamp = resultSet.getLong(s);
        return timestamp * 1000;
    }

    @Override
    public Long getResult(ResultSet resultSet, int i) throws SQLException {
        long timestamp = resultSet.getLong(i);
        return timestamp * 1000;
    }

    @Override
    public Long getResult(CallableStatement callableStatement, int i) throws SQLException {
        long timestamp = callableStatement.getLong(i);
        return timestamp * 1000;
    }
}
