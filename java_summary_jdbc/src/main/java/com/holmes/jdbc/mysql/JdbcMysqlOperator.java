package com.holmes.jdbc.mysql;

import com.holmes.entity.Customer;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
public class JdbcMysqlOperator {

    private final static String DRIVER = "com.mysql.jdbc.Driver";

    private final static String URL = "jdbc:mysql://localhost:3306/holmes?useUnicode=true&characterEncoding=utf-8";

    private final static String USER_NAME = "root";

    private final static String PASSWORD = "root";

    private static Connection connection = null;

    static {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> select() {

        String sql = "select *from customer";
        Statement statement = null;
        ResultSet resultSet = null;
        List list = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            list = new LinkedList();
            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);
                Customer customer = new Customer(id, name, address, phone);
                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, statement, resultSet);
        }
        return list;
    }

    public static List<Customer> selectById(Integer id) {

        String sql = "select *from customer where id = ?";
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List list = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            list = new LinkedList();
            while (resultSet.next()) {
                Integer idd = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String phone = resultSet.getString(4);
                System.out.println("id:" + idd + ",name:" + name + ",address:" + address + ",phone:" + phone);
                Customer customer = new Customer(id, name, address, phone);
                list.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, ps, resultSet);
        }
        return list;
    }

    public static void insert(Customer customer) {

        String sql = "insert into customer(name,address,phone) value(?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPhone());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, ps, null);
        }
    }

    public static void insertList(List<Customer> list) {

        String sql = "insert into customer(name,address,phone) value(?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            for (Customer customer : list) {
                ps.setString(1, customer.getName());
                ps.setString(2, customer.getAddress());
                ps.setString(3, customer.getPhone());
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(null, ps, null);
        }
    }

    public static void update(Customer customer) {

        String sql = "update customer set name = ?, address = ?, phone = ? where id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getPhone());
            ps.setInt(4, customer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (null != connection) {
                connection.close();
            }
            if (null != statement) {
                statement.close();
            }
            if (null != resultSet) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.println(JdbcMysqlOperator.select());

        System.out.println(JdbcMysqlOperator.selectById(100001));

        JdbcMysqlOperator.insert(new Customer("张三", "杭州", "13022336633"));

        System.out.println(JdbcMysqlOperator.select());

        List<Customer> list = new LinkedList<>();
        list.add(new Customer("李四", "杭州", "13022336633"));
        list.add(new Customer("王五", "杭州", "13022336633"));

        JdbcMysqlOperator.insertList(list);

        JdbcMysqlOperator.update(new Customer(100001, "张三", "杭州", "13022336633"));
        System.out.println(JdbcMysqlOperator.selectById(100001));
    }
}
