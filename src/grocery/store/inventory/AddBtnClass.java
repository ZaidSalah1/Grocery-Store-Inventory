package grocery.store.inventory;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AddBtnClass extends Inventory implements EventHandler<ActionEvent> {

    Stage addWindow;
    Scene scene;
    Label brandName, saveBtnMsg;
    TextField txtBrandName;
    TextField txtType, txtQuantity, txtPrice;
    GridPane gridPane;
    RadioButton yesButton, noButton;
    Button clearMsgBtn;

    @Override
    public void handle(ActionEvent event) {
        addWindow = new Stage();
        addWindow.setTitle("Add New Item");

        //Defines a modal window that blocks events from being delivered to any other application window.
        addWindow.initModality(Modality.APPLICATION_MODAL);
        addWindow.setMinWidth(250);

        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        Label Brand = new Label("Brand");

        HBox radioBtn = new HBox(10);//Hbox for No and yes Botton
        ToggleGroup group = new ToggleGroup();
        gridPane.add(Brand, 0, 0);

        yesButton = new RadioButton("Yes");
        yesButton.setToggleGroup(group);
        radioBtn.getChildren().add(yesButton);

        noButton = new RadioButton("No");
        noButton.setSelected(true);
        noButton.setToggleGroup(group);
        radioBtn.getChildren().add(noButton);
        gridPane.add(radioBtn, 1, 0);

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

        brandName = new Label("Brand Name");
        txtBrandName = new TextField();
        gridPane.add(brandName, 0, 4);
        gridPane.add(txtBrandName, 1, 4);

        //Hide the "Brand name" label and text field by default.
        brandName.setVisible(false);
        txtBrandName.setVisible(false);

        HBox save_cancel_Btn = new HBox(10);//Hbox for Save and Cancel button
        Button saveBtn = new Button("SAVE");
        saveBtn.setOnAction(new SaveBtn());
        Button CancelBtn = new Button("Cancel");
        save_cancel_Btn.getChildren().addAll(saveBtn, CancelBtn);
        gridPane.add(save_cancel_Btn, 2, 5);
        CancelBtn.setOnAction(new CancelAddWindowBtn());

        // This buton to clear any message after doing somthing  
        clearMsgBtn = new Button("Clear This Message");
        clearMsgBtn.setVisible(false);//set this button visible by default
        gridPane.add(clearMsgBtn, 1, 7);

        //===Action events===\\
        yesButton.setOnAction(new YesBtnClass());
        noButton.setOnAction(new NoBtnClass());
        clearMsgBtn.setOnAction(new ClearMsg());

        scene = new Scene(gridPane, 500, 450);
        addWindow.setScene(scene);
        addWindow.showAndWait();//This used to show this stage and wait for it to be hidden

    }

    //This class for Cancel Button that close the add Button window
    class CancelAddWindowBtn implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            addWindow.close();
        }
    }

    //This class for Save Button 
    class SaveBtn implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            saveBtnMsg = new Label();//this label message for save button after clock it
            if (noButton.isSelected()) {
                if (findItem(txtType.getText(), true) == -1) {// if this == -1 that  mean the item not fount before
                    newItem(txtType.getText(), Integer.parseInt(txtQuantity.getText()), Double.parseDouble(txtPrice.getText()));
                    saveBtnMsg.setText(txtType.getText() + " Added successfully");
                    gridPane.add(saveBtnMsg, 1, 6);
                } else {
                    saveBtnMsg.setText(txtType.getText() + " Already exists!");
                    gridPane.add(saveBtnMsg, 1, 6);
                }
            } else if (yesButton.isSelected()) {
                if (findItem(txtType.getText(), true, txtBrandName.getText()) == -1) {
                    newItem(txtBrandName.getText(), txtType.getText(), Integer.parseInt(txtQuantity.getText()), Double.parseDouble(txtPrice.getText()));
                    saveBtnMsg.setText(txtBrandName.getText() + " " + txtType.getText() + " Added successfully");
                    gridPane.add(saveBtnMsg, 1, 6);
                } else {
                    saveBtnMsg.setText(txtBrandName.getText() + " " + txtType.getText() + " Already exists!");
                    gridPane.add(saveBtnMsg, 1, 6);
                }
            }
            saveBtnMsg.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
            saveBtnMsg.setTextFill(Color.RED);
            clearMsgBtn.setVisible(true);//This wil show to clear message Button after clicking save btn
        }
    }

    //Yes radio button class
    class YesBtnClass implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            handleBrandSelection(true);
        }
    }
 //No radio button class
    class NoBtnClass implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            handleBrandSelection(false);
        }
    }
    
    /* 
    This method for "No" and "Yes" selection 
    if you select "No" Brand label and text fielad will be not shwoing
    and vice versa for "Yes"
    */
    private void handleBrandSelection(boolean selected) {
        brandName.setVisible(selected);
        txtBrandName.setVisible(selected);
    }

    //This Addtion class from me that clear the messages
    class ClearMsg implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            saveBtnMsg.setText("");
        }

    }
}
