package grocery.store.inventory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StockReportBtn extends Inventory implements EventHandler<ActionEvent> {

    Stage stockWindow;//Stage for stock
    CheckBox exportBox, exportTextAreaBox;//CheckBoxs
    TextField exportText;//for Export Button
    TextArea areaTextFiead;//Area to print all items information
    Button ExportBtn;//Export Button
    Scene scene;

    @Override
    public void handle(ActionEvent event) {

        stockWindow = new Stage();
        stockWindow.setTitle("Stock Report");

        //Defines a modal window that blocks events from being delivered to any other application window.
        stockWindow.initModality(Modality.APPLICATION_MODAL);

        BorderPane borderPane = new BorderPane();
        Label label = new Label("The following option can be used to print a report");
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        label.setPadding(new Insets(15, 15, 15, 15));
        borderPane.setTop(label);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15, 15, 15, 15));
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        exportBox = new CheckBox("Export a copy to a file");//Export Box
        exportTextAreaBox = new CheckBox("Text Area");//Export Text Area
        areaTextFiead = new TextArea();
        areaTextFiead.setPrefHeight(200);
        areaTextFiead.setPrefWidth(450);

        //Print all items in Area Text fieald     
        for (int i = 0; i < inventory.size(); i++) {
            areaTextFiead.appendText(inventory.get(i).toString() + "\n");
        }

        gridPane.add(exportBox, 0, 0);//Add Export Box to grid pane
        gridPane.add(exportTextAreaBox, 1, 0);//Add Export Text Area Box to grid pane

        exportText = new TextField();//This for Export a copy to a file Box
        ExportBtn = new Button("Export");//This for Export a copy to a file Box
        ExportBtn.setOnAction(new ExportBtnClass());
        gridPane.add(exportText, 0, 1);
        gridPane.add(ExportBtn, 0, 2);
        gridPane.add(areaTextFiead, 1, 3);
        borderPane.setLeft(gridPane);//Set Grid Pane to Border Pane

        //Hide export TextFieal,button and Area TextFiead by default
        exportText.setVisible(false);
        ExportBtn.setVisible(false);
        areaTextFiead.setVisible(false);

        //===Action Events===\\
        exportBox.setOnAction(new ExportBox());
        exportTextAreaBox.setOnAction(new ExportText());

        scene = new Scene(borderPane, 750, 400);
        stockWindow.setScene(scene);
        stockWindow.showAndWait();//This used to show this stage and wait for it to be hidden

    }

    //if "Export a copy to a file" clicked then show exportText and ExportBtn
    private void exportSelection(boolean selected) {
        exportText.setVisible(selected);
        ExportBtn.setVisible(selected);
    }

    //if Export a "Text Area" clicked then show areaTextFiead
    private void textAreaSelection(boolean selected) {
        areaTextFiead.setVisible(selected);
    }

    //This class for Export button that export items inforamtion to a text file called "inventoryDatabase.txt"
    class ExportBtnClass implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            File file = new File("inventoryDatabase.txt");

            try {
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);

                for (int i = 0; i < inventory.size(); i++) {
                    bw.write(inventory.get(i).toString());
                    bw.newLine();
                    bw.flush();
                }

            } catch (FileNotFoundException e) {//give exception if file not found 
                System.out.println(e);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    //Export box selection

    class ExportBox implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            exportSelection(true);
        }
    }
    //Text Area box selection

    class ExportText implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            textAreaSelection(true);
        }
    }
}
