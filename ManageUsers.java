package GUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.List;


import LibraryPack.Library;
import LibraryPack.Users;

public class ManageUsers implements EventHandler<ActionEvent> {
    private Stage primaryStage;
    
    public ManageUsers(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @Override
    public void handle(ActionEvent event) {
        Image backgroundImage = new Image("GUI_Material/pic.jpg");
        BackgroundImage backgroundImg = new BackgroundImage(backgroundImage,BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background background = new Background(backgroundImg);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setBackground(background);
        GridPane manageUsersPage = new GridPane();
        manageUsersPage.setBackground(background);
        manageUsersPage.setAlignment(Pos.CENTER);
        manageUsersPage.setPadding(new Insets(10, 10, 10, 10));
        manageUsersPage.setVgap(10);
        manageUsersPage.setHgap(10);
        
        
        
        Text header = new Text("Manage Users");
        header.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        header.setFill(Color.WHITE);

        HBox searchBox = new HBox();
        searchBox.setSpacing(10);
        
        Button searchButton = new Button("Search");
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setFont(Font.font(20));
        searchField.setMaxWidth(600);

        Button backButton = new Button("Back");
        backButton.setOnAction(new MainMenu_Librarians(primaryStage));

        searchBox.getChildren().addAll(searchField, searchButton, backButton);
        
        Text searchResults = new Text("Search Results");
        searchResults.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        searchResults.setFill(Color.WHITE);

        HBox[] searchResultsBox = new HBox[Library.getUsers().size()];
        
          
        searchButton.setFont(Font.font(20));
        
        
        VBox allUsersBox = new VBox(header, searchBox, searchResults);
        allUsersBox.setMaxWidth(800);
        allUsersBox.setSpacing(20);
        allUsersBox.setAlignment(Pos.CENTER);
        allUsersBox.setPadding(new Insets(10, 10, 10, 10));
        GridPane.setConstraints(allUsersBox, 0, 0, 2, 1);
        
        manageUsersPage.getChildren().add(allUsersBox);
        int row = 1;
        for (int i=0;i<Library.getUsers().size();i++){
            Text user = new Text(Library.getUsers().get(i).getFirstName()+" "+Library.getUsers().get(i).getLastName());
            user.setFont(Font.font(30));
            user.setFill(Color.WHITE);
            Button manageButton = new Button("Manage");
            manageButton.setFont(Font.font(20));
            manageButton.setOnAction(new UserManage(primaryStage, Library.getUsers().get(i)));
            
            
            GridPane.setConstraints(user, 0, row);
            GridPane.setConstraints(manageButton, 1, row);
            manageUsersPage.getChildren().addAll(user, manageButton);
            row++;
        }
        
        

        searchButton.setOnAction(e -> {
            String search = searchField.getText();
            List<Users> results = Library.searchMembers(search);
            if (results.isEmpty()) {
                searchResults.setText("No Results Found");
                allUsersBox.getChildren().removeAll(searchResultsBox);
            } 
            else {
                searchResults.setText("Search Results");
                
                allUsersBox.getChildren().removeAll(searchResultsBox);
                for (Users user : results) {

                    Text searchResultsText = new Text(user.getFirstName()+" "+user.getLastName());
                    searchResultsText.setFont(Font.font(30));
                    searchResultsText.setFill(Color.WHITE);

                    Button manageButton = new Button("Manage");
                    manageButton.setFont(Font.font(20));
                    manageButton.setOnAction(new UserManage(primaryStage, user));

                    searchResultsBox[results.indexOf(user)] = new HBox();
                    searchResultsBox[results.indexOf(user)].setSpacing(10);
                    searchResultsBox[results.indexOf(user)].getChildren().addAll(searchResultsText, manageButton);
                    searchResultsBox[results.indexOf(user)].setAlignment(Pos.CENTER);
                    allUsersBox.getChildren().add(searchResultsBox[results.indexOf(user)]);
                }
                
                

            }
        });
        
        
        scrollPane.setContent(manageUsersPage);
        scrollPane.setPadding(new Insets(50, 10, 50, 10));
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        
        
        
        

        Scene scene = new Scene(scrollPane, primaryStage.widthProperty().doubleValue(), primaryStage.heightProperty().doubleValue());
        scene.getStylesheets().add("GUI_Material/buttonStyle.css");
        primaryStage.setTitle("Library Management System");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }

}
