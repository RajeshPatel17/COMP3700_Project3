import java.sql.*;
import java.util.*;

public class SQLiteDataAdapter implements DataAccess {
    Connection conn = null;

    @Override
    public void connect() {
        try {
            // db parameters
            String url = "jdbc:sqlite:store.db";
            // create a connection to the database
            Class.forName("org.sqlite.JDBC");

            conn = DriverManager.getConnection(url);

            if (conn == null)
                System.out.println("Cannot make the connection!!!");
            else
                System.out.println("The connection object is " + conn);

            System.out.println("Connection to SQLite has been established.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveNote(NoteModel note) {
        try {
            Statement stmt = conn.createStatement();

            if (loadNote(note.noteID) == null) {           // this is a new product!
                stmt.execute("INSERT INTO Notes(NoteID, Title, Text, UserID) VALUES ("
                        + note.noteID + ","
                        + '\'' + note.title + '\'' + ","
                        + '\'' + note.text + '\'' + ","
                        + note.userID + ")"
                );
            }
            else {
                stmt.executeUpdate("UPDATE Notes SET "
                        + "NoteID = " + note.noteID + ","
                        + "Title = " + '\'' + note.title + '\'' + ","
                        + "Text = " + '\'' + note.text + '\'' + ","
                        + "UserID = " + note.userID +
                        " WHERE NoteID = " + note.noteID
                );

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public NoteModel loadNote(int noteID) {
        NoteModel note = null;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Notes WHERE NoteID = " + noteID);
            if (rs.next()) {
                note = new NoteModel();
                note.noteID = rs.getInt(1);
                note.title = rs.getString(2);
                note.text = rs.getString(3);
                note.userID = rs.getInt(4);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return note;
    }

    @Override
    public SearchModel searchNotes(String keyword){
        SearchModel notes = new SearchModel();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Notes WHERE Title LIKE \'%" +  keyword + "%\'");
            while(rs.next()){
                notes.add(new NoteModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return notes;
    }
}
