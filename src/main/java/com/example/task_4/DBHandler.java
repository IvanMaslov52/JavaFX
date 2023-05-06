package com.example.task_4;


import java.sql.*;
import java.util.List;

public class DBHandler {
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:F:\\Programms\\task_4\\db\\users.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     private   static Statement statement;

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DBHandler() throws SQLException {
    }

    public void  createbook() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS  books ("
            + " id         INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " title      TEXT,"
            + " author TEXT,"
            + " colvalue       TEXT,"
            + " rencost     TEXT);");
    }
    public void createreader() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS  readers ("
                + " id         INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " name      TEXT,"
                + " surname      TEXT,"
                + " patronymic TEXT,"
                + " adress       TEXT,"
                + " telephone     TEXT);");

    }
    public void createorder() throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS issbook ("
                + " id         INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " gdata      TEXT,"
                + " rdata      TEXT,"
                + " reader     INTEGER,"
                + " book       INTEGER,"
                + " FOREIGN KEY (reader) REFERENCES readers (id) ON DELETE CASCADE,"
                + " FOREIGN KEY (book) REFERENCES books (id) ON DELETE CASCADE);"  );
    }
    public void  insertbook(Book book) throws SQLException
    {
            statement.executeUpdate("INSERT INTO books (title, author, colvalue, rencost) "+
                "VALUES('" + book.title.get() + "', '" + book.author.get() + "', '" + book.colvalue.get() + "', '" + book.rencost.get() + "');");

    }
    public void  insertreader(Reader reader) throws SQLException
    {
            statement.executeUpdate("INSERT INTO readers (name, surname, patronymic, adress, telephone) "+
                    "VALUES('" + reader.name.get() + "', '" + reader.surname.get() + "', '" + reader.patronymic.get() + "', '"  + reader.adress.get() + "', '" + reader.telephone.get() + "');");
    }
     public void  insertorder(Order order) throws SQLException
    {
            statement.executeUpdate("INSERT INTO issbook (gdata, rdata, reader, book) "+
                    "VALUES('" + order.gdata.get() + "', '" + order.rdata.get() + "', '" + order.reader.getId() + "', '" + order.book.getId() + "');");

    }
    public void selectbook() throws SQLException {
        CollectionBook booklist = CollectionBook.init();

        ResultSet rs = statement.executeQuery("SELECT id, title, author, colvalue, rencost FROM books;");
        while (rs.next())
        {

            int id1 = rs.getInt("id");
            String title1 = rs.getString("title");
            String author1 = rs.getString("author") ;
            String colvalue1 = rs.getString("colvalue");
            String rencost1 = rs.getString("rencost");
            Book book = new Book(id1,title1, author1,colvalue1,rencost1);
            booklist.addBook(book);
        }
        rs.close();

    }
    public void selectreader() throws SQLException {
        CollectionReader readerlist = CollectionReader.init();
        ResultSet rs1 = statement.executeQuery("SELECT id, name, surname, patronymic, adress, telephone FROM readers;");
        while (rs1.next())
        {
            int id1 = rs1.getInt("id");
            String name1 = rs1.getString("name");
            String surname1 = rs1.getString("surname") ;
            String patronymic1 = rs1.getString("patronymic");
            String adress1 = rs1.getString("adress");
            String telephone1 = rs1.getString("telephone");
            Reader Reader1 = new Reader(id1,name1,surname1,patronymic1,adress1,telephone1);
            readerlist.addReader(Reader1);
        }
        rs1.close();

    }
    public void selectorder() throws SQLException {

        CollectionOrder orderlist =CollectionOrder.init();
        CollectionBook booklist = CollectionBook.init();
        booklist.fillBookdata();
        CollectionReader readerlist = CollectionReader.init();
        readerlist.fillReaderdata();
        ResultSet rs3 = statement.executeQuery("SELECT id, gdata, rdata, reader, book FROM issbook;");
        while (rs3.next())
        {
            int id1 = rs3.getInt("id");
            String gdata1 = rs3.getString("gdata");
            String rdata1 = rs3.getString("rdata") ;
            int reader_1 = rs3.getInt("reader");
            int book_1 = rs3.getInt("book");
            Order adorder = new Order(id1, gdata1, rdata1,FinderRCode1(readerlist.getReaderList(),reader_1),FinderBCode1(booklist.getBookList(), book_1));
            orderlist.addOrder(adorder);

        }
        rs3.close();

    }
    public static Reader FinderRCode1(List<Reader> readerList, int ind)
    {
        Reader reader1 = null;
        for(Reader test  : readerList)
        {
            if(test.getId() == (int)ind)
                reader1 =  test;
        }
        return reader1;
    }
    public static Book FinderBCode1(List<Book>booklist, int ind)
    {
        Book book1 = null;
        for(Book test  : booklist)
        {
            if(test.getId() == (int)ind)
                book1 =  test;
        }
        return book1;
    }
    public static void deletereader(Reader reader) throws SQLException {
          statement.executeUpdate("DELETE FROM readers WHERE id = " + reader.getId());
    }
    public static void deletebook(Book book) throws SQLException {
        statement.executeUpdate("DELETE FROM books WHERE id = " + book.getId());
    }
    public static void deleteorder(Order order) throws SQLException {
        statement.executeUpdate("DELETE FROM issbook WHERE id = " + order.getId());
    }
    public static void updatereader(Reader reader) throws SQLException {
        statement.executeUpdate("UPDATE readers SET name ='"+reader.getName() +"', surname  = '" +reader.getSurname() +"', patronymic = '"+reader.getPatronymic() + "' , adress ='"+reader.getAdress() +"' , telephone = '"+reader.getTelephone()+"' WHERE id = " + reader.getId());
    }
    public static void updatebook(Book book) throws SQLException {
        statement.executeUpdate("UPDATE books SET title ='"+book.getTitle() +"', author  = '" +book.getAuthor() +"', colvalue = '"+book.getColvalue() + "' , rencost ='"+book.getRencost()+"' WHERE id = " + book.getId());
    }
    public static void updateorder(Order order) throws SQLException {
        statement.executeUpdate("UPDATE issbook SET gdata ='"+order.getGdata() +"', rdata  = '" +order.getRdata() +"', reader = '"+order.getReader() + "' , book ='"+order.getBook()+"' WHERE id = " + order.getId());
    }
    public static void deleteorderfrombook(Book book) throws SQLException {
        statement.executeUpdate("DELETE FROM issbook WHERE book = " + book.getId());
    }
    public static void deleteorderfromreader(Reader reader) throws SQLException {
        statement.executeUpdate("DELETE FROM issbook WHERE reader = " + reader.getId());
    }

}