public interface DataAccess {
    void connect();
    
    void saveNote(NoteModel note);

    NoteModel loadNote(int noteID);

    SearchModel searchNotes(String keyword);
}
