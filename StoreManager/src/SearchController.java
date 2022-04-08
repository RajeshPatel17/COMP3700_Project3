import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchController implements ActionListener{
    
    SearchView thisSearchView;
    DataAccess thisDAO;

    public SearchController(SearchView view, DataAccess dao){
        thisSearchView = view;
        thisDAO = dao;
        thisSearchView.searchButton.addActionListener(this);
        thisSearchView.clearButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == thisSearchView.searchButton){
            searchNotesandDisplay();
        }
        if(event.getSource() == thisSearchView.clearButton){
            clearFields();
        }
    }

    private void clearFields(){
        thisSearchView.keywordField.setText("");
        thisSearchView.results.setText("");
    }

    private void searchNotesandDisplay(){
        try{
            String keywordField = thisSearchView.keywordField.getText();
            String[] keywords = keywordField.split("[ ]+");
            if(keywords.length==1){
                thisSearchView.results.setText(thisDAO.searchNotes(keywords[0]).toString());
            } else {
                throw new IllegalArgumentException("Invalid keyword");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Invalid Keyword");
            e.printStackTrace();
        }
    }
}
