/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package set;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author Lenovo
 */
public class FXMLDocumentController implements Initializable  {
     
    
    ArrayList<String>  universe,sets,one,two,res,check ;
    
    
    @FXML
    private Button addUniverse,addSet;
    @FXML
    private TextField result ;
    @FXML
    private TextField universeInput,setInput;
    @FXML
    ListView<String> setView ;
    @FXML
    ComboBox<String> setOne,setTwo,operation ;
    @FXML
    private void addUniverse(ActionEvent event) {
          universe.clear();
          one.clear();
          two.clear();
          res.clear();
          sets.clear();
          setOne.getItems().clear();
          setTwo.getItems().clear();
          setView.getItems().clear();
          if( ! handleInput(universeInput,false)) return;
          String element="";
          handle(universeInput.getText(),universe);
          setView.getItems().add("universe:");
          setView.getItems().add(nonSpace(universeInput.getText()));
          operation.getItems().clear();
          operation.getItems().addAll("Union","Intersection","ComplementSetOne","ComplementSetTwo");
          universeInput.setText("");
    }
    
    @FXML
    private void addSet(ActionEvent event) {
        
        if( ! handleInput(setInput,true)) return;
        sets.add(setInput.getText());
        setView.getItems().add("Set #" + sets.size());
        setView.getItems().add(nonSpace(setInput.getText()));
        setOne.getItems().add(""+sets.size());
        setTwo.getItems().add(""+sets.size());
        setInput.setText("");
    }
    @FXML
    private void selectSetOne (ActionEvent event) {
       one.clear();
       int index=setOne.getSelectionModel().getSelectedIndex();
       handle(sets.get(index),one);
   
    }
     @FXML
    private void   selectSetTwo (ActionEvent event) {
       two.clear();
       int index= setTwo.getSelectionModel().getSelectedIndex();
       handle(sets.get(index),two);
    
    }
    @FXML
    private void selectOperation(ActionEvent event){
        String oper= operation.getValue();
        int numOper=0;
        switch (oper) {
            case "Union":
                numOper=1;
                break;
            case "Intersection":
                numOper=2;
                break;
            case "ComplementSetOne":
                numOper=3;
                break;
            case "ComplementSetTwo":
                numOper=4;
                break;
            default:
                break;
        }
        solve(numOper);
        String out="";
        for(int i=0;i<res.size();i++){
            out+=res.get(i);
            if( i!= res.size()-1) out+="-" ;
        }
        result.setText(out);
    }
    private void handle(String set,ArrayList<String> arr){
      String element="";
       arr.clear();
      for(int i=0;i<set.length();i++){
              if(set.charAt(i) != ','){
                  if(set.charAt(i) != ' ')  element+=set.charAt(i);
                  if( i ==set.length()-1){
                         arr.add(element);
                  }
              }
              else{
                  arr.add(element);
                  element="";
              }
          }
       
    }
    
    
    private Boolean handleInput(TextField input,Boolean checkSet){
       
         if(input.getText().isEmpty()){
              alert.dislay("Alert", "empty Input");
              return false;
          }
         else {
             for(int i=0;i<input.getText().length()-1;i++){
                 if(input.getText().charAt(i) == ',' && input.getText().charAt(i+1) == ','){
                     alert.dislay("Alert", "you should not add empty element");
                     return false;
                 }
             }
         }
         if(checkSet){
             handle(input.getText(),check);
             if(universe.isEmpty()){
                 alert.dislay("Alert", "You should add universe first");
                 return false;
             }
             else{
                 for(int i=0;i<check.size();i++){
                     if( ! universe.contains(check.get(i))){
                         check.clear();
                         alert.dislay("Alert", "You should add element from universe");
                         return false;
                     }
                 }
             }
         }
         
         return true;
    }
    private String nonSpace(String input){
        String output="";
        for(int i=0;i<input.length();i++){
            if(input.charAt(i) != ' ') output+=input.charAt(i);
        }
        return output;
    }
    
    
    public void solve(int numOper){
        res.clear();
        switch (numOper) {
            case 1:
                for(int i=0;i<one.size();i++){
                    res.add(one.get(i));
                }    for(int i=0;i<two.size();i++){
                    if(! res.contains(two.get(i))){
                        res.add(two.get(i));
                    }
                }    break;
            case 2:
                for(int i=0;i<one.size();i++){
                    if(two.contains(one.get(i))){
                        res.add(one.get(i));
                    }
                }   break;
            case 3:
                for(int i=0;i<universe.size();i++){
                    if(! one.contains(universe.get(i))){
                        res.add(universe.get(i));
                    }
                }   break;
            case 4:
                for(int i=0;i<universe.size();i++){
                    if(! two.contains(universe.get(i))){
                        res.add(universe.get(i));
                    }
                }   break;
            default:
                break;
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        universe =new ArrayList<>();
        sets = new ArrayList<>();
        one = new ArrayList<>();
        two = new ArrayList<>();
        res= new ArrayList<>();
        check = new ArrayList<>();
    }    
    
 
    
}
