package com.epam.learn.service;

import java.awt.*;
import java.util.List;
import com.epam.learn.model.UserAccount;
import com.epam.learn.util.Checker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UsersService implements IUsersService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<UserAccount> findAll() {

        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserAccount.class));
    }

    @Transactional(readOnly = true)
    public List<UserAccount> findAllEnhanced(String order, String paginationFrom, String paginationTo) {
        String limitClause = "";
        if (paginationFrom != null) {
            limitClause = " LIMIT " + Checker.checkPaginationValue(paginationFrom);

            if (paginationTo != null){
                limitClause = limitClause + " OFFSET " + Checker.checkPaginationValue(paginationTo);
            }
        }


        String sql = "SELECT * FROM users ORDER BY id " + order + limitClause;
        System.out.println("findAllEnhanced SQL = " + sql);

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserAccount.class));
    }

    @Transactional(readOnly = true)
    public List<UserAccount> findUserByName(String name) {

        String sql = "SELECT * FROM users WHERE name='" + name + "'";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserAccount.class));
    }

//    @Transactional(readOnly = true)
//    public UserAccount findUserById(String userId) {
//
//        String sql = "SELECT * FROM users WHERE id='" + userId + "'";
//
//        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserAccount.class));
//    }

    @Transactional(readOnly = true)
    public UserAccount findOneUserByName(String name) {

        String sql = "SELECT DISTINCT * FROM users WHERE name='" + name + "' LIMIT 1";

        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserAccount.class));
    }

    @Transactional(readOnly = true)
    public List<UserAccount> findUserByNameExcludingId(String name, String id) {

        String sql = "SELECT * FROM users WHERE name='" + name + "' AND id <> '" + id +"'";

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserAccount.class));
    }

    @Transactional(readOnly = true)
    public String findUserNameById(String id) {

        String sql = "SELECT name FROM users WHERE id='" + id + "'";
        System.out.println("findUserNameById sql = " + sql);

        return jdbcTemplate.queryForObject(sql,String.class);
    }

    @Transactional(readOnly = true)
    public UserAccount findUserById(String id) {

        String sql = "SELECT * FROM users WHERE id='" + id + "'";
        System.out.println("findUserById sql = " + sql);

        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(UserAccount.class));
    }

    @Transactional(readOnly = true)
    public Integer findUserBalanceById(String id) {

        String sql = "SELECT balance FROM users WHERE id='" + id + "'";

        return jdbcTemplate.queryForObject(sql,Integer.class);
    }

    @Transactional
    public void transferMoney(int idFrom, int idTo, int amount) {

        String sqlFrom = "SELECT balance FROM users WHERE id='" + idFrom + "'";
        Integer newAmountOfOldAccount = jdbcTemplate.queryForObject(sqlFrom,Integer.class);

        String sqlTo = "SELECT balance FROM users WHERE id='" + idTo + "'";
        Integer newAmountOfNewAccount = jdbcTemplate.queryForObject(sqlTo,Integer.class);

        newAmountOfOldAccount = newAmountOfOldAccount - amount;
        newAmountOfNewAccount = newAmountOfNewAccount + amount;

        String sqlDeduct = "UPDATE users SET balance = " + newAmountOfOldAccount + " WHERE id=" + idFrom + ";";
        jdbcTemplate.update(sqlDeduct);
        System.out.println("transferMoney sqlDeduct SUCCESSFUL = " + sqlDeduct);

        String sqlAdd = "UPDATE users SET balance = " + newAmountOfNewAccount + " WHERE id=" + idTo + ";";
        jdbcTemplate.update(sqlAdd);
        System.out.println("transferMoney sqlAdd SUCCESSFUL = " + sqlAdd);
    }

    @Transactional
    public void deductFee(int idFrom, int amount) {

        String sqlFrom = "SELECT balance FROM users WHERE id='" + idFrom + "'";
        Integer newAmountOfOldAccount = jdbcTemplate.queryForObject(sqlFrom,Integer.class);


        System.out.println("newAmountOfOldAccount = " + newAmountOfOldAccount);
        System.out.println("amount = " + amount);
        newAmountOfOldAccount = newAmountOfOldAccount - amount;
        System.out.println("newAmountOfOldAccount after deduction = " + newAmountOfOldAccount);


        String sqlDeduct = "UPDATE users SET balance = " + newAmountOfOldAccount + " WHERE id=" + idFrom + ";";
        jdbcTemplate.update(sqlDeduct);
        System.out.println("deductFee sqlDeduct SUCCESSFUL = " + sqlDeduct);
    }

    /**
     * Attack vector = any text field - INSERT INTO
     */
    @Transactional
    public void updateUser(int id, UserAccount userAccount) {

        String update = "UPDATE users SET " +
                " name = '" + userAccount.getName() + "'," +
                " phoneNumber = '" + userAccount.getPhoneNumber() + "'," +
                " phoneOperator = '" + userAccount.getPhoneOperator() + "'," +
                " balance = " + userAccount.getBalance() +
                " WHERE id=" + id + ";";
        jdbcTemplate.update(update);
        System.out.println("update user SUCCESSFUL. sql = " + update);
    }


    @Override
    public void addUser(UserAccount userAccount) {
        String sqlAdd = "INSERT INTO users (name, phoneNumber, phoneOperator, balance) VALUES (" +
                //userAccount.getId().toString() + ", " +
                "'" + userAccount.getName() + "', " +
                "'" + userAccount.getPhoneNumber() + "', " +
                "'" + userAccount.getPhoneOperator() + "', " +
                userAccount.getBalance() +
                ")";
        jdbcTemplate.update(sqlAdd);
        System.out.println("addUser sql = " + sqlAdd);
    }
}

