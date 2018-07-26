package com.jackaroo.spring_boot_demo.typehandler;

import com.jackaroo.spring_boot_demo.util.Constant;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author JackarooZhang
 * @date 2018/6/7 13:31
 */
public class GenderTypeHandler implements TypeHandler<Constant.Gender> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Constant.Gender gender, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, gender.getId());
    }

    @Override
    public Constant.Gender getResult(ResultSet resultSet, String s) throws SQLException {
        int id = resultSet.getInt(s);
        return Constant.Gender.getSex(id);
    }

    @Override
    public Constant.Gender getResult(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(i);
        return Constant.Gender.getSex(id);
    }

    @Override
    public Constant.Gender getResult(CallableStatement callableStatement, int i) throws SQLException {
        int id = callableStatement.getInt(i);
        return Constant.Gender.getSex(id);
    }
}
