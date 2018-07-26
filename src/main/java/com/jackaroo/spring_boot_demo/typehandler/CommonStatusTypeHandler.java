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
 * @date 2018/6/7 13:35
 */
public class CommonStatusTypeHandler implements TypeHandler<Constant.CommonStatus> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Constant.CommonStatus commonStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, commonStatus.getId());
    }

    @Override
    public Constant.CommonStatus getResult(ResultSet resultSet, String s) throws SQLException {
        int id = resultSet.getInt(s);
        return Constant.CommonStatus.codeOf(id);
    }

    @Override
    public Constant.CommonStatus getResult(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(i);
        return Constant.CommonStatus.codeOf(id);
    }

    @Override
    public Constant.CommonStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        int id = callableStatement.getInt(i);
        return Constant.CommonStatus.codeOf(id);
    }
}
