package ua.goit.service;

import ua.goit.config.DataBase;
import ua.goit.exception.ConnectionException;
import ua.goit.exception.NoDataFoundException;
import ua.goit.service.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static ua.goit.constant.Constant.CONNECTION_TROUBLE_MESSAGE;

public class ClientService {
    private static final String CREATE_CLIENT_SQL_TEMPLATE =
            "INSERT INTO client(name) VALUES (?)";
    private static final String SELECT_CLIENT_BY_ID_SQL_TEMPLATE =
            "SELECT name FROM client WHERE id = ?";
    private static final String UPDATE_CLIENT_NAME_BY_ID_SQL_TEMPLATE =
            "UPDATE client SET name = ? WHERE id = ?";
    private static final String DELETE_CLIENT_BY_ID_SQL_TEMPLATE =
            "DELETE FROM client WHERE id = ?";
    private static final String SELECT_ALL_CLIENTS_SQL_TEMPLATE =
            "SELECT id, name FROM client";

    public long create(String name) {
        checkName(name);
        Connection connection;
        long result = -1;
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(CONNECTION_TROUBLE_MESSAGE);
        }
        try (PreparedStatement statement = connection.prepareStatement(CREATE_CLIENT_SQL_TEMPLATE,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            generatedKey.next();
            result = generatedKey.getObject("id", Long.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getById(long id) {
        checkId(id);
        Connection connection;
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(CONNECTION_TROUBLE_MESSAGE);
        }
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CLIENT_BY_ID_SQL_TEMPLATE)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new NoDataFoundException("No data found for id = " + id);
            }
            return resultSet.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new NoDataFoundException("No data found for id = " + id);
    }

    public void setName(long id, String name) {
        checkId(id);
        checkName(name);
        Connection connection;
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(CONNECTION_TROUBLE_MESSAGE);
        }
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CLIENT_NAME_BY_ID_SQL_TEMPLATE)) {
            statement.setString(1, name);
            statement.setLong(2, id);
            int cntUpdated = statement.executeUpdate();
            if (cntUpdated == 0) {
                throw new NoDataFoundException("No data found for update!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        checkId(id);
        Connection connection;
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(CONNECTION_TROUBLE_MESSAGE);
        }
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CLIENT_BY_ID_SQL_TEMPLATE)) {
            statement.setLong(1, id);
            int cntUpdated = statement.executeUpdate();
            if (cntUpdated == 0) {
                throw new NoDataFoundException("No data found for update!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> listAll() {
        List<Client> result = new ArrayList<>();
        Connection connection;
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(CONNECTION_TROUBLE_MESSAGE);
        }
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CLIENTS_SQL_TEMPLATE)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(Client.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    private static void checkName(String name) {
        if (name.length() < 2 || name.length() > 1000) {
            throw new IllegalArgumentException("Name's length must be between 2 and 1000 characters. But it's " + name.length());
        }
    }

    private static void checkId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id must be positive. Now is " + id);
        }
    }
}
