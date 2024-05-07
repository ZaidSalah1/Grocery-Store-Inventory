package grocery.store.inventory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;


public class Delete extends Inventory implements EventHandler<ActionEvent> {

    Stage deleteWindow;
    ComboBox<String> comboBox;//combo box to select Brand or Not brand
    GridPane gridPane;
    TextField txtType, txtQuantity, txtPrice, txtBrandName;
    Button cancelBtn, deleteBtn, clearMsg;
    Label brandName, txtMasg;

    @Override
    public void handle(ActionEvent event) {
        deleteWindow = new Stage();
        deleteWindow.setTitle("Delete Item");
        gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(15, 15, 15, 15));

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Brand", "Not Brand");
        comboBox.setValue("Not Brand");
        gridPane.add(comboBox, 1, 0);

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

        brandName = new Label("Brand");
        txtBrandName = new TextField();
        gridPane.add(brandName, 0, 4);
        gridPane.add(txtBrandName, 1, 4);

        //Hide the "Brand name" label and text field by default.
        brandName.setVisible(false);
        txtBrandName.setVisible(false);

        HBox update_Cancel_Btn = new HBox(10);
        deleteBtn = new Button("Delete");
        cancelBtn = new Button("Cancel");
        update_Cancel_Btn.getChildren().addAll(deleteBtn, cancelBtn);
        gridPane.add(update_Cancel_Btn, 1, 7);

        clearMsg = new Button("Clear This Message");
        clearMsg.setVisible(false);
        gridPane.add(clearMsg, 3, 9);

        //===Action events===\\
        comboBox.setOnAction(new BrandSelection());
        deleteBtn.setOnAction(new DeleteBtn());
        cancelBtn.setOnAction(new CancelBtn());
        clearMsg.setOnAction(new ClearMsg());

        //===================\\
        Scene scene = new Scene(gridPane, 500, 400);
        deleteWindow.setScene(scene);
        deleteWindow.showAndWait();//This used to show this stage and wait for it to be hidden

    }

    class BrandSelection implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (comboBox.getSelectionModel().getSelectedItem().equals("Brand")) {
                comboBoxSelection(true);
            } else if (comboBox.getSelectionModel().getSelectedItem().equals("Not Brand")) {
                comboBoxSelection(false);
            }
        }
    }

    class DeleteBtn implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            try {
                if (comboBox.getValue().equals("Not Brand")) {
                    if(findItem(txtType.getText(), true) != -1){//if != -1 thats mean item found
                        deleteItem(txtType.getText(), Integer.parseInt(txtQuantity.getText()), Double.parseDouble(txtPrice.getText()));//delete method for item type
                        txtMasg = new Label(txtType.getText() + " is succssfuly deleted! ");
                    } else {
                        txtMasg = new Label(" Cannot found " + txtType.getText());
                    }

                } else if (comboBox.getValue().equals("Brand")) {
                    if (findItem(txtType.getText(), true, txtBrandName.getText()) != 1) {
                       deleteItem(txtBrandName.getText(), txtType.getText(), Integer.parseInt(txtQuantity.getText()), Double.parseDouble(txtPrice.getText())); //delete method for brand type
                       txtMasg = new Label(txtBrandName.getText() + " " + txtType.getText() + " is succssfuly deleted! ");
                    }else{
                         txtMasg = new Label(" Cannot found " + txtBrandName.getText() + " " + txtType.getText());
                    }
                }
            } catch (Exception ex) {
                txtMasg = new Label("Pleas fill the texts");
            }
            txtMasg.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
            txtMasg.setTextFill(Color.RED);
            gridPane.add(txtMasg, 3, 8);
            clearMsg.setVisible(true);
        }

    }

    //clear messages
    class ClearMsg implements EventHandler<ActionEvent> {
        
        @Override
        public void handle(ActionEvent event) {
            txtMasg.setText("");
        }
    }
    
    //close window
    class CancelBtn implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            deleteWindow.close();
        }

    }
    
      /* 
    This method for "Brand" and "Not brand" selection 
    if you select "Not brand" Brand label and text fielad will be not shwoing
    and vice versa for "Brand" selection
    */
    private void comboBoxSelection(boolean select) {
        brandName.setVisible(select);
        txtBrandName.setVisible(select);
    }
}

