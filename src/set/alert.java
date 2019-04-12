/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package set;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class alert extends Application{
    
    public static void dislay(String title ,String message){
         Stage window = new Stage();
         window.initModality(Modality.APPLICATION_MODAL);
         window.setTitle(title);
         window.setMinWidth(250);
         
         Label label= new Label(message);
         Button closeButton = new Button("CLose");
         closeButton.setOnAction(e -> window.close());
         
         VBox layout = new VBox(15);
         layout.getChildren().addAll(label,closeButton);
         layout.setAlignment(Pos.CENTER);
         
         Scene scene = new Scene(layout,150,100);
         
         window.setScene(scene);
         window.showAndWait();
         
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
