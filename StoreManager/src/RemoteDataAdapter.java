import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class RemoteDataAdapter implements DataAccess {
    Gson gson = new Gson();
    Socket s = null;
    DataInputStream dis = null;
    DataOutputStream dos = null;

    @Override
    public void connect() {
        try {
            s = new Socket("localhost", 5056);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public NoteModel loadNote(int noteID) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.LOAD_NOTE_REQUEST;
        req.body = Integer.toString(noteID);

        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            } else {
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a note with that ID!");
                    return null;
                } else {
                    NoteModel model = gson.fromJson(res.body, NoteModel.class);
                    System.out.println("Receiving a NoteModel object");
                    System.out.println("NoteID = " + model.noteID);
                    System.out.println("Note title = " + model.title);
                    System.out.println("Note text = " + model.text);
                    System.out.println("UserID = " + model.userID);
                    return model; // found this note and return!!!
                }
            }        // this is a JSON string for a product information
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveNote(NoteModel note) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.SAVE_NOTE_REQUEST;
        req.body = gson.toJson(note);
        
        String json = gson.toJson(req);
        try {

            dos.writeUTF(json);
            String received = dis.readUTF();
            System.out.println("Server response:" + received);
            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
            } else { 
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not save the Note!");
                } else {
                    NoteModel model = gson.fromJson(res.body, NoteModel.class);
                    System.out.println("Saved the following Note!");
                    System.out.println("NoteID = " + model.noteID);
                    System.out.println("Note title = " + model.title);
                    System.out.println("Note text = " + model.text);
                    System.out.println("UserID = " + model.userID);
                }
            }         
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public SearchModel searchNotes(String keyword) {
        RequestModel req = new RequestModel();
        req.code = RequestModel.SEARCH_NOTE_REQUEST;
        req.body = keyword;

        String json = gson.toJson(req);
        try {
            dos.writeUTF(json);

            String received = dis.readUTF();

            System.out.println("Server response:" + received);

            ResponseModel res = gson.fromJson(received, ResponseModel.class);

            if (res.code == ResponseModel.UNKNOWN_REQUEST) {
                System.out.println("The request is not recognized by the Server");
                return null;
            } else { 
                if (res.code == ResponseModel.DATA_NOT_FOUND) {
                    System.out.println("The Server could not find a note that matches your keyword!");
                    return null;
                } else {
                    SearchModel model = gson.fromJson(res.body, SearchModel.class);
                    System.out.println("Receiving a SearchModel object");
                    System.out.println(model.toString());
                    return model; // found this product and return!!!
                }
            }        

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
