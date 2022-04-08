// Java implementation of Server side
// It contains two classes : Server and ClientHandler
// Save file as Server.java

import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

import com.google.gson.Gson;


// Server class
public class DataServer
{
    public static void main(String[] args) throws IOException
    {
        // server is listening on port 5056
        ServerSocket ss = new ServerSocket(5056);

        // running infinite loop for getting
        // client request

        System.out.println("Starting server program!!!");

        int nClients = 0;

        while (true)
        {
            Socket s = null;

            try
            {
                // socket object to receive incoming client requests
                s = ss.accept();

                nClients++;
                System.out.println("A new client is connected : " + s + " client number: " + nClients);

                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() method
                t.start();

            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            }
        }
    }
}


// ClientHandler class
class ClientHandler extends Thread
{
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;

    Gson gson = new Gson();
    DataAccess dao = new SQLiteDataAdapter();

    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        dao.connect();
    }

    @Override
    public void run()
    {
        String received;
        boolean exit = false;
        while (!exit) {
            try {
                // receive the answer from client
                received = dis.readUTF();

                System.out.println("Message from client " + received);

                RequestModel req = gson.fromJson(received, RequestModel.class);
                ResponseModel res = new ResponseModel();
                
                switch(req.code){

                    case RequestModel.EXIT_REQUEST:
                        System.out.println("Client " + this.s + " sends exit...");
                        System.out.println("Closing this connection.");
                        this.s.close();
                        System.out.println("Connection closed");
                        exit = true;
                        break;
                    
                    case RequestModel.LOAD_NOTE_REQUEST:
                        int id = Integer.parseInt(req.body);
                        System.out.println("The Client asks for a note with ID = " + id);
                        NoteModel model = dao.loadNote(id);
                        if (model != null) {
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(model);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;

                    case RequestModel.SAVE_NOTE_REQUEST:
                        NoteModel note = gson.fromJson(req.body, NoteModel.class);
                        System.out.println("The Client asks to store the Note: \n" + note.toString());
                        dao.saveNote(note);
                        NoteModel confirm = dao.loadNote(note.noteID);
                        if(confirm != null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(note);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;
                    
                    case RequestModel.SEARCH_NOTE_REQUEST:
                        String keyword = req.body;
                        SearchModel found = dao.searchNotes(keyword);
                        if(found != null){
                            res.code = ResponseModel.OK;
                            res.body = gson.toJson(found);
                        } else {
                            res.code = ResponseModel.DATA_NOT_FOUND;
                            res.body = "";
                        }
                        break;
                    
                    default:
                        res.code = ResponseModel.UNKNOWN_REQUEST;
                        res.body = "";
                }

                String json = gson.toJson(res);
                System.out.println("JSON object of ResponseModel: " + json);

                dos.writeUTF(json);
                dos.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}