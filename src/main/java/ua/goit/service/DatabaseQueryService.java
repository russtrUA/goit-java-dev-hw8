package ua.goit.service;

import ua.goit.config.DataBase;
import ua.goit.exception.ConnectionException;
import ua.goit.reader.SQLReader;
import ua.goit.service.entity.*;

import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static ua.goit.constant.Constant.CONNECTION_TROUBLE_MESSAGE;

public class DatabaseQueryService {


    public List<LongestProject> findLongestProject() {
        List<LongestProject> result = new ArrayList<>();
        String contentSql = new SQLReader().readContent(Path.of("./sql/find_longest_project.sql"));
        Connection connection;
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(CONNECTION_TROUBLE_MESSAGE);
        }
        try (PreparedStatement statement = connection.prepareStatement(contentSql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(LongestProject.builder()
                        .projectName(resultSet.getString("name"))
                        .monthDuration(resultSet.getObject("months_between", Long.class))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<MaxProjectsClient> findMaxProjectsClient() {
        List<MaxProjectsClient> result = new ArrayList<>();
        String contentSql = new SQLReader().readContent(Path.of("./sql/find_max_projects_client.sql"));
        Connection connection;
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(CONNECTION_TROUBLE_MESSAGE);
        }
        try (PreparedStatement statement = connection.prepareStatement(contentSql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(MaxProjectsClient.builder()
                        .clientName(resultSet.getString("name"))
                        .countOfProjects(resultSet.getObject("kol", Long.class))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<MaxSalaryWorker> findMaxSalaryWorker() {
        List<MaxSalaryWorker> result = new ArrayList<>();
        String contentSql = new SQLReader().readContent(Path.of("./sql/find_max_salary_worker.sql"));
        Connection connection;
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(CONNECTION_TROUBLE_MESSAGE);
        }
        try (PreparedStatement statement = connection.prepareStatement(contentSql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(MaxSalaryWorker.builder()
                        .workerName(resultSet.getString("name"))
                        .workerLevel(resultSet.getString("level"))
                        .workerSalary(resultSet.getObject("salary", Integer.class))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<YoungestEldestWorker> findYoungestEldestWorkers() {
        List<YoungestEldestWorker> result = new ArrayList<>();
        String contentSql = new SQLReader().readContent(Path.of("./sql/find_youngest_eldest_workers.sql"));
        Connection connection;
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(CONNECTION_TROUBLE_MESSAGE);
        }
        try (PreparedStatement statement = connection.prepareStatement(contentSql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(YoungestEldestWorker.builder()
                        .typeOfAge(resultSet.getString("type"))
                        .workerName(resultSet.getString("name"))
                        .birthDate(resultSet.getObject("birthday", LocalDate.class))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public List<ProjectPrice> getProjectPrices() {
        List<ProjectPrice> result = new ArrayList<>();
        String contentSql = new SQLReader().readContent(Path.of("./sql/print_project_prices.sql"));
        Connection connection;
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionException(CONNECTION_TROUBLE_MESSAGE);
        }
        try (PreparedStatement statement = connection.prepareStatement(contentSql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(ProjectPrice.builder()
                        .projectName(resultSet.getString("name"))
                        .price(resultSet.getObject("total_price", Integer.class))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}