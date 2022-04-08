import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NoteController implements ActionListener{
    
    NoteView thisNoteView;
    DataAccess thisDAO;

    public NoteController(NoteView view, DataAccess dao){
        thisNoteView = view;
        thisDAO = dao;
        thisNoteView.loadButton.addActionListener(this);
        thisNoteView.saveButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == thisNoteView.loadButton){
            loadNoteandDisplay();
        }
        if(event.getSource() == thisNoteView.saveButton){
            saveNote();
        }
    }

    private void loadNoteandDisplay(){
        try{
            int noteID = Integer.parseInt(thisNoteView.noteIDText.getText());
            NoteModel noteModel = thisDAO.loadNote(noteID);

            if(noteModel == null){
                JOptionPane.showMessageDialog(null, "No existing note with this ID" + noteID);
            } else {
                thisNoteView.titleText.setText(noteModel.title);
                thisNoteView.textText.setText(noteModel.text);
                thisNoteView.userIDText.setText(Integer.toString(noteModel.userID));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid Note ID");
            e.printStackTrace();
        }
    }

    private void saveNote(){
        NoteModel noteModel = new NoteModel();
        try{
            noteModel.noteID = Integer.parseInt(thisNoteView.noteIDText.getText());
            noteModel.title = thisNoteView.titleText.getText();
            noteModel.text = thisNoteView.textText.getText();
            noteModel.userID = Integer.parseInt(thisNoteView.userIDText.getText());

            thisDAO.saveNote(noteModel);
            JOptionPane.showMessageDialog(null, "Note saved successfully");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Invalid Note");
            e.printStackTrace();
        }
    }
}
