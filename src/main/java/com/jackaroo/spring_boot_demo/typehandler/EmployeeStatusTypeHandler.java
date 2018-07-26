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
public class EmployeeStatusTypeHandler implements TypeHandler<Constant.EmployeeStatus> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Constant.EmployeeStatus employeeStatus, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, employeeStatus.getId());
    }

    @Override
    public Constant.EmployeeStatus getResult(ResultSet resultSet, String s) throws SQLException {
        int id = resultSet.getInt(s);
        return Constant.EmployeeStatus.codeOf(id);
    }

    @Override
    public Constant.EmployeeStatus getResult(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt(i);
        return Constant.EmployeeStatus.codeOf(id);
    }

    @Override
    public Constant.EmployeeStatus getResult(CallableStatement callableStatement, int i) throws SQLException {
        int id = callableStatement.getInt(i);
        return Constant.EmployeeStatus.codeOf(id);
    }
}
