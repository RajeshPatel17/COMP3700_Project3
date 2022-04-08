public class NoteModel {
    
    public int noteID;
    public String title;
    public String text;
    public int userID;

    NoteModel(){};

    NoteModel(int nID, String ttl, String txt, int uID){
        noteID = nID;
        title = ttl;
        text = txt;
        userID = uID;
    }

    @Override
    public String toString(){
        return "Note ID: " + noteID + ", Title: " + title + ", Text: " + text + ", User ID: " + userID;
    }
}
