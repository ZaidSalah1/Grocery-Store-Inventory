package grocery.store.inventory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UpdateBtnClass extends Inventory implements EventHandler<ActionEvent> {

    Stage updateWindow, warrningMsg;
    GridPane gridPane;
    TextField txtType, txtQuantity, txtPrice, txtBrand;
    Button updateBtn, cancelBtn, searchBtn, clearMsg;
    ComboBox<String> comboBox;
    Label showOutPutMsgForSearchBtn, showOutputMsgforUpdate;

    @Override
    public void handle(ActionEvent event) {
        updateWindow = new Stage();
        updateWindow.setTitle("Update Item");

        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(15, 15, 15, 15));
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Brand", "Not Brand");//Add Brand and Not Brand selections
        comboBox.setEditable(true);//this let you type in the combo box

        gridPane.add(comboBox, 1, 0);

        HBox hboxForSerchBtn_BrandTxt = new HBox(10);

        txtBrand = new TextField();
        txtBrand.setVisible(false);
        searchBtn = new Button("Search");
        searchBtn.setVisible(false);
        hboxForSerchBtn_BrandTxt.getChildren().addAll(txtBrand, searchBtn);
        gridPane.add(hboxForSerchBtn_BrandTxt, 2, 1);

        comboBox.setOnAction(new BrandSelection());

        Label Type = new Label("Type");
        txtType = new TextField();
        gridPane.add(Type, 0, 1);
        gridPane.add(txtType, 1, 1);

        Label Quantity = new Label("Quantity");
        txtQuantity = new TextField();
        gridPane.add(Quantity, 0, 2);
        gridPane.add(txtQuantity, 1, 2);

        Label Price = new Label("Price");
        txtPrice = new TextField();
        gridPane.add(Price, 0, 3);
        gridPane.add(txtPrice, 1, 3);

        txtBrand = new TextField();
        gridPane.add(txtBrand, 2, 1);
        txtBrand.setVisible(false);

        HBox update_Cancel_Btn = new HBox(10);
        updateBtn = new Button("Update");
        cancelBtn = new Button("Cancel");
        update_Cancel_Btn.getChildren().addAll(updateBtn, cancelBtn);
        gridPane.add(update_Cancel_Btn, 1, 5);

        clearMsg = new Button("Clear This Message!");
        clearMsg.setVisible(false);
        gridPane.add(clearMsg, 2, 8);
        
        //Actions
        cancelBtn.setOnAction(new CacenlBtn());
        searchBtn.setOnAction(new SearchBtn());
        updateBtn.setOnAction(new UpdateBtn());
        clearMsg.setOnAction(new ClearMsg());

        Scene scene = new Scene(gridPane, 500, 400);
        updateWindow.setScene(scene);
        updateWindow.showAndWait();//Shows this stage and waits for it to be hidden (closed) 
    }

    class BrandSelection implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            searchBtn.setVisible(true);
            if (comboBox.getValue().equals("Brand")) {
                if (gridPane.getChildren().contains(searchBtn)) {//if gridPane contains the search button 
                    gridPane.getChildren().remove(searchBtn);//then remove it (this steps for adding this button again but in different postion)
                }
                gridPane.add(searchBtn, 3, 1);
                txtBrand.setVisible(true);
            } else if (comboBox.getValue().equals("Not Brand")) {
                if (gridPane.getChildren().contains(searchBtn)) {
                    gridPane.getChildren().remove(searchBtn);
                }
                gridPane.add(searchBtn, 2, 1);
                txtBrand.setVisible(false);
            }
        }
    }

    //Cancel button class 
    class CacenlBtn implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            updateWindow.close();
        }
    }
    
    
    //Search button class that serach for items
    class SearchBtn implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            if (findItem(txtType.getText(), true) == -1) {
                warning();
            } else {
                showOutPutMsgForSearchBtn = new Label(txtType.getText() + " is found");
            }
            showOutPutMsgForSearchBtn.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
            showOutPutMsgForSearchBtn.setTextFill(Color.RED);
            gridPane.add(showOutPutMsgForSearchBtn, 2, 6);
        }
    }
    
    
    //Update button class for items
    class UpdateBtn implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            showOutputMsgforUpdate = new Label();
            if (comboBox.getValue().equals("Not Brand")) {
                if (txtQuantity.getText() != "" && txtType.getText() != "") {//Update quantity for item tyep
                    if (findItem(txtType.getText(), true) != -1) {
                        update(txtType.getText(), Integer.parseInt(txtQuantity.getText()));//Update for Quantity
                        update(txtType.getText(), Double.parseDouble(txtPrice.getText()));//Update for price
                        showOutputMsgforUpdate.setText(txtType.getText() + " Updated successfully!");
                    }
                } else {
                    showOutputMsgforUpdate.setText("Pleas Fill All Texts fielad");
                }
            } else if (comboBox.getValue().equals("Brand")) {
                if (txtQuantity.getText() != "" && txtType.getText() != "") {//Update quantity for item tyep
                    if (findItem(txtType.getText(), true, txtBrand.getText()) != -1) {
                        update(txtBrand.getText(), txtType.getText(), Integer.parseInt(txtQuantity.getText()));//Update for Quantity
                        update(txtBrand.getText(), txtType.getText(), Double.parseDouble(txtPrice.getText()));//Update for price
                        showOutputMsgforUpdate.setText(txtBrand.getText() + " " + txtType.getText() + " Updated successfully!");
                    }
                } else {//else if nothing typed in text fielad
                    showOutputMsgforUpdate.setText("Pleas Fill All Texts fielad");
                }
            }

            showOutputMsgforUpdate.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
            showOutputMsgforUpdate.setTextFill(Color.RED);
            gridPane.add(showOutputMsgforUpdate, 2, 7);
            clearMsg.setVisible(true);//show clear message after clicked update
        }

    }
    
    
    //Clear Button class for clear messages
    class ClearMsg implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            showOutputMsgforUpdate.setText("");
            showOutPutMsgForSearchBtn.setText("");
        }
    }
    
    //OK class that shows if item not found in search button 
    class OKBtn implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            warrningMsg.close();
        }
    }
    
    //method that create a new stage for warrning window
    public void warning() {
        warrningMsg = new Stage();
        warrningMsg.setTitle("warrning");

        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        Button ok = new Button("OK");
        ok.setOnAction(new OKBtn());
        Label label = new Label("Cannot find item type");
        vBox.getChildren().addAll(label, ok);

        Scene scene = new Scene(vBox, 300, 100);
        warrningMsg.setScene(scene);
        warrningMsg.showAndWait();
    }

}

