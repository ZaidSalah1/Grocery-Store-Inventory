package grocery.store.inventory;
import java.awt.SystemColor;
import java.io.File;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScren extends Application {

    Stage MainStage, stage2;//Main Window
    Scene mainWindow;//Masin window
    Button btnAdd, btnModify, btnDelete, btnSrockReport;
    HBox textPane;
    @Override
    public void start(Stage primaryStage) {
        MainStage = primaryStage;
        primaryStage.setTitle("Inventory Management System");

        //BorderPane that contain the text on the top and Image in the center and buttons in bottom
        BorderPane mainPane = new BorderPane();
        
        
        textPane = new HBox();
        textPane.setAlignment(Pos.CENTER);
        Label mainText = new Label("Inventory Management System : Comp2311 Projct, Phase 2");
        mainText.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));//set font Times New Roman, Weight Bold, Font Posture ITALIC, and for size 20
        mainText.setTextFill(Color.RED);//Set mainText RED color
        textPane.getChildren().add(mainText);//Add this msg to textPane(HBOX)
        
        
        mainPane.setTop(textPane);//Let this text in to top of the window (main Pane)
        
        VBox imgPabe = new VBox(10);//Vbox to add 
        imgPabe.setAlignment(Pos.CENTER);
        try {
            Image image = new Image("file:///C:/Users/zaid7/OneDrive/سطح%20المكتب/ProjcetPh2/inventoryManagement.jpg");
            ImageView imgView = new ImageView();
            imgView.setImage(image);
            imgView.setFitWidth(300);
            imgView.setFitHeight(200);
            imgPabe.setRotate(45);
            imgPabe.getChildren().add(imgView);
            
            mainPane.setCenter(imgPabe);//set this image in the center of the window (mainPane)
            
        } catch (IllegalArgumentException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        HBox BPane = new HBox(15);//HBox for add this Buttons Horizontal (Add, Modify, Delete, Stock report)
        BPane.setAlignment(Pos.CENTER);
        btnAdd = new Button("Add");
        btnModify = new Button("Modify");
        btnDelete = new Button("Delete");
        btnSrockReport = new Button("Stock Report");
        BPane.getChildren().addAll(btnAdd, btnModify, btnDelete, btnSrockReport);//Add all Buttons
        BPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.setBottom(BPane);//Add these buttons in Bottom of the window (mainPane)

        //      Buttons Action       \\
        //=============================
        btnAdd.setOnAction(new AddBtnClass());
        btnSrockReport.setOnAction(new StockReportBtn());
        btnModify.setOnAction(new UpdateBtnClass());
        btnDelete.setOnAction(new Delete());

        //=============================
        mainWindow = new Scene(mainPane, 600, 500);
        MainStage.setScene(mainWindow);
        MainStage.show();
    }

    //main mrthod
    public static void main(String[] args) {
        Inventory store = new Inventory("groceries");
        store.newItem("bread", 15, 9.99);
        store.newItem("al-jebrini", "milk", 2, 2.00);
        store.newItem("eggs", 3, 1.50);
        store.newItem("bread", 2, 1.25);

        
//        try {
//            File file = new File("inventoryDatabase.txt");
//            Scanner reader = new Scanner(file);
//            while (reader.hasNextLine()) {
//                String line = reader.nextLine();
//                String[] parts = line.split(" - in stock: ");
//                String[] parts2 = parts[1].split(", price: ");
//                
//                store.setType(parts[0]);
//                store.setQuantity(Integer.parseInt(parts2[0]));
//                store.setPrice(Double.parseDouble(parts2[1].replace("$", ""))); 
//                store.newItem(store.getType(), store.getQuantity(), store.getQuantity());
//            }
//                        
//            reader.close();
//
//        } catch (Exception e) {
//             System.out.println(e);
//        }
        launch(args);

    }

}