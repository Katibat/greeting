package com.example.greeting.db;

import java.sql.*;

public class Connection {
    public static java.sql.Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:sqlite:table_greeting.sqlite");
        System.out.println("База Подключена!");
    }

    // --------Создание таблицы--------
    public static void CreateDB() throws ClassNotFoundException, SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'table_greeting' (" +
                "'greeting_id' INTEGER PRIMARY KEY AUTO_INCREMENT," +
                "'content' text, " +
                "'data' DATE);");

        System.out.println("Таблица создана или уже существует.");
    }

    // --------Заполнение таблицы--------
    public static void WriteDB() throws SQLException
    {
        statmt.execute("INSERT INTO 'table_greeting' ('content', 'data') VALUES ('Police', 12-04-2024); ");
        statmt.execute("INSERT INTO 'table_greeting' ('content', 'data') VALUES ('MO', 10-04-2024); ");
        statmt.execute("INSERT INTO 'table_greeting' ('content', 'data') VALUES ('Government', 07-04-2024); ");

        System.out.println("Таблица заполнена");
    }

    // -------- Вывод таблицы--------
    public static void ReadDB() throws ClassNotFoundException, SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM table_greeting");

        while(resSet.next())
        {
            int id = resSet.getInt("greeting_id");
            String content = resSet.getString("content");
            Date data = resSet.getDate("data");
            System.out.println( "greeting_id = " + id );
            System.out.println( "content = " + content );
            System.out.println( "data = " + data );
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }
}