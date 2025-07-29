package com.mycompany.pharmacy;

import java.util.Collections;
import java.util.Vector;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    static Vector<PharmacyItem> phis = new Vector<>();
    static Vector<Order> orders = new Vector<>();
    static Vector<Client> clients = new Vector<>();

    public static void addPharmacyItem(PharmacyItem phi) {
        phis.add(phi);
    }

    public static void removePharmacyItem(PharmacyItem phi) {

        if (phi.getQuantity() != 0) {
            throw new IllegalStateException("There's quantity from this item");
        }

        phis.remove(phi);
        
    }
    public static void makeOrder(String itnm, int reqAmount) {

        for (PharmacyItem ph : phis) {
            if (ph.getName().equals(itnm)) {
                orders.add(new Order(ph, reqAmount));
            }
        }
    }

    public static void addClient(Client c) {
        clients.add(c);
    }

    public static void removeClient(Client c) throws IllegalStateException, IllegalArgumentException {
        for (Recipe r : c.rs) {
            if (!r.isPaid()) {
                throw new IllegalStateException("Client has recipe not paid");
            }
        }

        clients.remove(c);
    }

    

    @Override

    public void start(Stage stage) {

        Label label = new Label("Pharmacy Management System");
        label.setFont(Font.font("Segoe UI", 20));
        label.setTextFill(Color.DARKGREEN);

        // Logo
        ImageView logo = new ImageView(new Image("file:\\C:\\Users\\abdel\\Documents\\NetBeansProjects\\Pharmacy\\src\\main\\java\\com\\mycompany\\pharmacy\\Farmacia1.png"));
        logo.setPreserveRatio(true);

        // Buttons
        Button btnItems = new Button("Manage Items");
        Button btnClients = new Button("Manage Clients");
        Button btnOrders = new Button("Manage Orders");

        for (Button btn : new Button[]{btnItems, btnClients, btnOrders}) {
            btn.setFont(Font.font("Segoe UI", 14));
            btn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 8;");
            btn.setMaxWidth(Double.MAX_VALUE);
        }

        VBox buttonBox = new VBox(15, btnItems, btnClients, btnOrders);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setMaxWidth(Double.MAX_VALUE);

        HBox contentBox = new HBox(30, logo, buttonBox);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(10));
        HBox.setHgrow(buttonBox, Priority.ALWAYS);

        VBox mainBox = new VBox(20, label, contentBox);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(30));
        mainBox.setBackground(new Background(new BackgroundFill(Color.web("#F0FFF0"), CornerRadii.EMPTY, Insets.EMPTY)));

        StackPane root = new StackPane(mainBox);

        // Scene setup
        Scene scene = new Scene(root, 900, 550);
        stage.setTitle("Pharmacy System");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.getIcons().add(new Image("file:\\C:\\Users\\abdel\\Documents\\NetBeansProjects\\Pharmacy\\src\\main\\java\\com\\mycompany\\pharmacy\\farmicon2.png"));

        // Responsive bindings
        logo.fitWidthProperty().bind(scene.widthProperty().multiply(0.25));
        label.fontProperty().bind(Bindings.createObjectBinding(()
                -> Font.font("Segoe UI", scene.getWidth() / 30), scene.widthProperty()));

        // Optional: Scale button font sizes
        for (Button btn : new Button[]{btnItems, btnClients, btnOrders}) {
            btn.styleProperty().bind(Bindings.createStringBinding(()
                    -> "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 8; -fx-font-size: " + (scene.getWidth() / 45) + "px;",
                    scene.widthProperty()));
        }
        btnItems.setOnAction(e -> {
            Scene itemScene = createItemScene(stage, scene);
            stage.setScene(itemScene);
        });

        btnClients.setOnAction(e -> {
            Scene clientScene = createClientScene(stage, scene);
            stage.setScene(clientScene);
        });

        btnOrders.setOnAction(e -> {
            Scene orderScene = createOrderScene(stage, scene);
            stage.setScene(orderScene);
        });

        stage.show();

    }

    private Scene createItemScene(Stage stage, Scene mainScene) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f4fdf9;");

        Label title = new Label("Pharmacy Item Management");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: #00796B; -fx-padding: 15px; -fx-font-weight: bold;");
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        VBox formBox = new VBox(10);
        formBox.setPadding(new Insets(15));
        formBox.setStyle("-fx-background-color: #e0f2f1; -fx-border-color: #00796B; -fx-border-radius: 5px;");

        ComboBox<String> field1 = new ComboBox<>();
        field1.getItems().addAll("Medicine", "Supplement", "Medical Device", "Personal Care");
        field1.setPromptText("Select Item Type");

        TextField field2 = new TextField();
        field2.setPromptText("Name");
        TextField field3 = new TextField();
        field3.setPromptText("Price");
        TextField field4 = new TextField();
        field4.setPromptText("Expiry date");
        TextField field5 = new TextField();
        field5.setPromptText("Quantity");
        TextField field6 = new TextField();
        field6.setPromptText("instructions, warranty, brand or flavor");
        TextField field7 = new TextField();
        field7.setPromptText("category, purpose, category or usage");

        formBox.getChildren().addAll(
                new Label("Enter Item Details:"),
                field1, field2, field3, field4, field5, field6, field7
        );

        ListView<String> itemList = new ListView<>();
        ListView<String> itemdetails = new ListView<>();
        VBox listBox = new VBox(10, new Label("Item List:"), itemList);
        listBox.setPadding(new Insets(10));
        listBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #00796B; -fx-border-radius: 5px;");

        for (PharmacyItem ph : phis) {
            String item = ph.getName() + " (" + ph.type + ") ";
            itemList.getItems().add(item);
            item = "Name: " + ph.getName() + "\nType: " + ph.type + "\nPrice: " + ph.getPrice() + "\nExp Date: " + ph.getExpiryDate() + "\nQuantity: " + ph.getQuantity();
            if (ph instanceof Medicine) {
                item += "\nInstruction: " + ((Medicine) ph).getInstructions() + "\nCategory: " + ((Medicine) ph).getCategory();
            } else if (ph instanceof MedicalDevice) {
                item += "\nWarranty: " + ((MedicalDevice) ph).getWarranty() + "\nPurpose: " + ((MedicalDevice) ph).getPurpose();
            } else if (ph instanceof PersonalCareItems) {
                item += "\nBrand: " + ((PersonalCareItems) ph).getBrand() + "\nCategory: " + ((PersonalCareItems) ph).getCategory();
            } else if (ph instanceof Supplements) {
                item += "\nFlavor: " + ((Supplements) ph).getFlavor() + "\nUsage: " + ((Supplements) ph).getUsage();
            }
            itemdetails.getItems().add(item);
        }

        Button addBtn = new Button("Add Item");
        addBtn.setStyle("-fx-background-color: #00796B; -fx-text-fill: white;");
        addBtn.setOnAction(e -> {
            String type = field1.getValue();
            String item = field2.getText() + " (" + type + ") ";
            itemList.getItems().add(item);
            item = "Name: " + field2.getText() + "\nType: " + type + "\nPrice: " + field3.getText() + "\nExp Date: " + field4.getText() + "\nQuantity: " + field5.getText();
            if (type.equals("Medicine")) {
                item += "\nInstruction: " + field6.getText() + "\nCategory: " + field7.getText();
                addPharmacyItem(new Medicine(field2.getText(), Float.parseFloat(field3.getText()), field4.getText(), Integer.parseInt(field5.getText()), field6.getText(), field7.getText()));
            } else if (type.equals("Medical Device")) {
                item += "\nWarranty: " + field6.getText() + "\nPurpose: " + field7.getText();
                addPharmacyItem(new MedicalDevice(field2.getText(), Float.parseFloat(field3.getText()), field4.getText(), Integer.parseInt(field5.getText()), field6.getText(), field7.getText()));
            } else if (type.equals("Personal Care")) {
                item += "\nBrand: " + field6.getText() + "\nCategory: " + field7.getText();
                addPharmacyItem(new PersonalCareItems(field2.getText(), Float.parseFloat(field3.getText()), field4.getText(), Integer.parseInt(field5.getText()), field6.getText(), field7.getText()));
            } else if (type.equals("Supplement")) {
                item += "\nFlavor: " + field6.getText() + "\nUsage: " + field7.getText();
                addPharmacyItem(new Supplements(field2.getText(), Float.parseFloat(field3.getText()), field4.getText(), Integer.parseInt(field5.getText()), field6.getText(), field7.getText()));
            }
            itemdetails.getItems().add(item);
            Collections.sort(phis);
            FXCollections.sort(itemList.getItems());
            FXCollections.sort(itemdetails.getItems());

        });

        Button removeBtn = new Button("Remove Item");
        removeBtn.setStyle("-fx-background-color: #B71C1C; -fx-text-fill: white;");
        removeBtn.setOnAction(e -> {
            try {
                String selected = itemList.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    for(PharmacyItem ph:phis)
                    {
                        if (selected.contains(ph.getName()))removePharmacyItem(ph);
                    }
                    
                    itemList.getItems().remove(selected);
                    itemdetails.getItems().remove(selected);
                }

            } catch (IllegalStateException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Removal");
                alert.setContentText("There's quantity from this type");
                alert.showAndWait();
            }
        });

        Button infoBtn = new Button("Display Item Info");
        infoBtn.setStyle("-fx-background-color: #0288D1; -fx-text-fill: white;");
        infoBtn.setOnAction(e -> {
            String selected = itemList.getSelectionModel().getSelectedItem();
                int ind = itemList.getItems().indexOf(selected);

                Stage popupStage = new Stage();
                popupStage.setTitle("Item Info");
                VBox popupLayout = new VBox(10);
                popupLayout.setPadding(new Insets(15));
                popupLayout.setStyle("-fx-background-color: #ffffff;");

                TextArea textArea = new TextArea(itemdetails.getItems().get(ind));
                textArea.setEditable(false);
                textArea.setWrapText(true);

                Button closeBtn = new Button("Close");
                closeBtn.setOnAction(ev -> popupStage.close());

                popupLayout.getChildren().addAll(textArea, closeBtn);
                popupLayout.setAlignment(Pos.CENTER);
                Scene popupScene = new Scene(popupLayout, 300, 200);
                popupStage.setScene(popupScene);
                popupStage.show();
        });

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #00796B; -fx-text-fill: white;");
        backBtn.setOnAction(e -> stage.setScene(mainScene));

        HBox buttonBox = new HBox(10, addBtn, removeBtn, infoBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        VBox centerBox = new VBox(80, formBox, buttonBox);
        centerBox.setPadding(new Insets(10));

        root.setCenter(centerBox);
        root.setRight(listBox);

        return new Scene(root, 900, 550);
    }

    private Scene createOrderScene(Stage stage, Scene mainScene) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f4fdf9;");

        Label title = new Label("Order Management");
        title.setStyle("-fx-font-size: 24px; -fx-text-fill: #00796B; -fx-padding: 15px; -fx-font-weight: bold;");
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        ListView<String> pendingOrders = new ListView<>();
        ListView<String> deliveredOrders = new ListView<>();

        pendingOrders.setPrefWidth(300);
        deliveredOrders.setPrefWidth(300);

        VBox pendingBox = new VBox(10, new Label("Pending Orders"), pendingOrders);
        VBox deliveredBox = new VBox(10, new Label("Delivered Orders"), deliveredOrders);

        pendingBox.setPadding(new Insets(10));
        deliveredBox.setPadding(new Insets(10));
        for (Order or : orders) {
            String order = or.getPhi().getName() + ", Qty: " + or.getOrderedQuantity() + " " + or.getOrderId();
            if (or.IsDelieverd()) {
                deliveredOrders.getItems().add(order);
            } else {
                pendingOrders.getItems().add(order);

            }
        }

        HBox ordersBox = new HBox(20, deliveredBox, pendingBox);
        ordersBox.setPadding(new Insets(20));
        ordersBox.setAlignment(Pos.CENTER);
        root.setCenter(ordersBox);

        ComboBox<String> orderComboBox = new ComboBox<>();
        for (PharmacyItem ph : phis) {
            orderComboBox.getItems().add(ph.getName());
        }

        orderComboBox.setPromptText("Choose Item");

        TextField quantityField = new TextField();
        quantityField.setPromptText("Enter Quantity");

        

        Button addBtn = new Button("Add Order");
        addBtn.setStyle("-fx-background-color: #00796B; -fx-text-fill: white;");
        addBtn.setOnAction(e -> {
            String selection = orderComboBox.getValue();
            String quantity = quantityField.getText();
            if (selection != null && !quantity.isEmpty()) {
                makeOrder(selection, Integer.parseInt(quantity));
                String order = selection + ", Qty: " + quantity + orders.get(orders.size() - 1).getOrderId();
                pendingOrders.getItems().add(order);

                quantityField.clear();
            }
        });

        Button deliverBtn = new Button("Set Delivered");
        deliverBtn.setStyle("-fx-background-color: #388E3C; -fx-text-fill: white;");
        deliverBtn.setOnAction(e -> {
            String selected = pendingOrders.getSelectionModel().getSelectedItem();
                deliveredOrders.getItems().add(selected);
                pendingOrders.getItems().remove(selected);
                for (Order or : orders) {
                    if (selected.contains(or.getOrderId())) {
                        or.Delieverd();
                    }

                }
        });

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #B71C1C; -fx-text-fill: white;");
        backBtn.setOnAction(e -> stage.setScene(mainScene));

        HBox inputBox = new HBox(10, orderComboBox, quantityField);
        inputBox.setPadding(new Insets(10));
        inputBox.setAlignment(Pos.CENTER);

        HBox controlBox = new HBox(15, addBtn, deliverBtn, backBtn);
        controlBox.setPadding(new Insets(10));
        controlBox.setAlignment(Pos.CENTER);

        VBox bottomBox = new VBox(10, inputBox, controlBox);
        bottomBox.setPadding(new Insets(10));

        root.setBottom(bottomBox);

        return new Scene(root, 900, 550);
    }

    private Scene createClientScene(Stage stage, Scene mainScene) {

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #e0f2f1;");

        Label title = new Label("Client Management");
        title.setStyle("-fx-font-size: 30px; -fx-text-fill: #004d40; -fx-padding: 20px; -fx-font-weight: bold;");
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        ComboBox<String> clientComboBox = new ComboBox<>();
        clientComboBox.setPromptText("Select Client");
        clientComboBox.setPrefWidth(300);

        for (Client c : clients) {
            clientComboBox.getItems().add(c.getName());
        }

        TextField nameField = new TextField();
        nameField.setPromptText("Client Name");
        nameField.setPrefWidth(200);

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        phoneField.setPrefWidth(200);

        Button addBtn = new Button("Add Client");
        addBtn.setStyle("-fx-background-color: #00796B; -fx-text-fill: white; -fx-font-weight: bold;");
        addBtn.setPrefWidth(120);
        addBtn.setOnAction(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            if (!name.isEmpty() && !phone.isEmpty()) {
                clientComboBox.getItems().add(name);
                addClient(new Client(name, phone));
                nameField.clear();
                phoneField.clear();
            }
            Collections.sort(clients);
            FXCollections.sort(clientComboBox.getItems());
        });

        Button removeBtn = new Button("Remove Client");
        removeBtn.setStyle("-fx-background-color: #E64A19; -fx-text-fill: white; -fx-font-weight: bold;");
        removeBtn.setPrefWidth(140);
        removeBtn.setOnAction(e -> {
            String selected = clientComboBox.getValue();
            if (selected != null) {
                clientComboBox.getItems().remove(selected);
                for (Client c : clients) {
                    if (selected.contains(c.getName())) {
                        clients.remove(c);
                    }
                }
            }
        });

        Button infoBtn = new Button("Display Info");
        infoBtn.setStyle("-fx-background-color: #0288D1; -fx-text-fill: white; -fx-font-weight: bold;");
        infoBtn.setPrefWidth(130);
        infoBtn.setOnAction(e -> {
            String selected = "Name: " + clientComboBox.getValue() + "\n";
            
            
            if (selected != null) {
                for (Client c : clients) {
                    if (selected.contains(c.getName())) {
                        selected += "Phone: " + c.getPhoneNum();
                    }
                }

                Stage popup = new Stage();
                VBox box = new VBox(new Label("Client Info: \n" + selected));
                box.setPadding(new Insets(20));
                popup.setScene(new Scene(box, 300, 100));
                popup.setTitle("Client Info");
                popup.show();
            }
        });

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #B71C1C; -fx-text-fill: white; -fx-font-weight: bold;");
        backBtn.setPrefWidth(100);
        backBtn.setOnAction(e -> stage.setScene(mainScene));

        HBox inputBox = new HBox(15, nameField, phoneField, addBtn);
        inputBox.setAlignment(Pos.CENTER);
        inputBox.setPadding(new Insets(20));
        Button recipesBtn = new Button("Client Recipes");
        recipesBtn.setStyle("-fx-background-color: #388E3C; -fx-text-fill: white; -fx-font-weight: bold;");
        recipesBtn.setPrefWidth(140);
        HBox controlBox = new HBox(15, clientComboBox, removeBtn, infoBtn, recipesBtn, backBtn);
        controlBox.setAlignment(Pos.CENTER);
        controlBox.setPadding(new Insets(20));

        VBox centerBox = new VBox(20, inputBox, controlBox);
        centerBox.setAlignment(Pos.CENTER);
        root.setCenter(centerBox);
        Scene clscene = new Scene(root, 900, 550);

        recipesBtn.setOnAction(e -> {
            String selected = clientComboBox.getValue();
            if (selected != null) {
                for (Client c : clients) {
                    if (selected.contains(c.getName())) {
                        Scene clientRecipeScene = createClientRecipesScene(stage, clscene, c);
                        stage.setScene(clientRecipeScene);
                    }
                }
            }

        });
        return clscene;
    }

    private Scene createClientRecipesScene(Stage stage, Scene mainScene, Client c) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f1f8e9;");

        Label title = new Label("Client Recipes Management");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill: #33691E; -fx-padding: 20px; -fx-font-weight: bold;");
        root.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        ComboBox<String> recipeComboBox = new ComboBox<>();
        recipeComboBox.setPromptText("Select Recipe");
        recipeComboBox.setPrefWidth(300);

        for (Recipe r : c.rs) {
            recipeComboBox.getItems().add("Recipe ID: " + r.getRecipeId());
        }

        Button addRecipeBtn = new Button("Add Recipe");
        addRecipeBtn.setStyle("-fx-background-color: #689F38; -fx-text-fill: white; -fx-font-weight: bold;");
        addRecipeBtn.setOnAction(e -> {

            recipeComboBox.getItems().add("Recipe ID: " + c.getRecipeNum());
            c.addRecipe(new Recipe(c.getRecipeNum()));
        });

        TextField itemNameField = new TextField();
        itemNameField.setPromptText("Item Name");
        itemNameField.setPrefWidth(150);

        TextField itemQtyField = new TextField();
        itemQtyField.setPromptText("Quantity");
        itemQtyField.setPrefWidth(100);

        TextArea recipeDisplay = new TextArea();
        recipeDisplay.setEditable(false);
        recipeDisplay.setPrefHeight(200);

        Button addItemBtn = new Button("Add Item");
        addItemBtn.setStyle("-fx-background-color: #558B2F; -fx-text-fill: white; -fx-font-weight: bold;");
        addItemBtn.setOnAction(e -> {
            String selected = recipeComboBox.getValue();
            if (selected != null && !itemNameField.getText().isEmpty() && !itemQtyField.getText().isEmpty()) {
                for (Recipe r : c.rs) {
                    if (selected.contains(String.valueOf(r.getRecipeId()))) {
                        for (PharmacyItem ph : phis) {
                            if (itemNameField.getText().contains(ph.getName())) {
                                r.addri(new RecipeItem(ph, Integer.parseInt(itemQtyField.getText())));
                            }
                        }

                    }
                }

                itemNameField.clear();
                itemQtyField.clear();
            }
        });

        Button displayRecipeBtn = new Button("Display Recipe");
        displayRecipeBtn.setStyle("-fx-background-color: #33691E; -fx-text-fill: white; -fx-font-weight: bold;");
        displayRecipeBtn.setOnAction(e -> {
            String selected = recipeComboBox.getValue();
            if (selected != null) {
                for (Recipe r : c.rs) {
                    if (selected.contains(String.valueOf(r.getRecipeId()))) {
                        String disp = "RecipeId: " + r.getRecipeId() + "\n";

                        for (RecipeItem ri : r.ris) {
                            disp += ri.getPhi().getName() + "  " + ri.getRecipeItemQuantity();
                            if (ri.isIsAvilable()) {
                                disp += " Available";
                                if (ri.getPhi() instanceof MedicalDevice || ri.getPhi() instanceof PersonalCareItems) {
                                    disp += " 70% Discount";
                                }
                            } else {
                                disp += " Not Available";
                            }
                            disp += "\n";
                        }
                        disp += r.getTotalPrice() + "   ";
                        if (r.isPaid()) {
                            disp += "Paid";
                        } else {
                            disp += "Not Paid";
                        }
                        recipeDisplay.setText(disp);
                    }
                }

            }
        });

        TextField paymentField = new TextField();
        paymentField.setPromptText("Enter Payment Amount");
        paymentField.setPrefWidth(200);

        Button makePaymentBtn = new Button("Make Payment");
        makePaymentBtn.setStyle("-fx-background-color: #1B5E20; -fx-text-fill: white; -fx-font-weight: bold;");
        makePaymentBtn.setOnAction(e -> {
            if (!paymentField.getText().isEmpty()) {
                String selected = recipeComboBox.getValue();
                if (selected != null) {
                    for (Recipe r : c.rs) {
                        if (selected.contains(String.valueOf(r.getRecipeId()))) {
                            try {
                                r.make_payment(Float.parseFloat(paymentField.getText()));
                            } catch (IllegalStateException ex) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("Invalid Payment");
                                alert.setContentText("This Recipe is already paid.");
                                alert.showAndWait();
                            } catch (IllegalArgumentException ex) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error");
                                alert.setHeaderText("Invalid Payment");
                                alert.setContentText("Please pay the right amount.");
                                alert.showAndWait();
                            }

                        }
                    }

                }
            }
        });

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #B71C1C; -fx-text-fill: white; -fx-font-weight: bold;");
        backBtn.setOnAction(e -> stage.setScene(mainScene));

        VBox topControls = new VBox(15,
                new HBox(10, recipeComboBox, addRecipeBtn),
                new HBox(10, itemNameField, itemQtyField, addItemBtn),
                new HBox(10, displayRecipeBtn, paymentField, makePaymentBtn),
                recipeDisplay
        );
        topControls.setPadding(new Insets(20));
        topControls.setAlignment(Pos.CENTER);

        root.setCenter(topControls);
        root.setBottom(backBtn);
        BorderPane.setAlignment(backBtn, Pos.CENTER);
        BorderPane.setMargin(backBtn, new Insets(15));

        return new Scene(root, 900, 550);
    }

    public static void main(String[] args) {
        addPharmacyItem(new Medicine("Panadol", 15, "12/9/2025", 4, "painkiller", "if needed"));
        addPharmacyItem(new MedicalDevice("diabetes", 1500, "12/9/2025", 2, "2 years", "measure diabetes"));
        makeOrder("Panadol", 4);
        addClient(new Client("Abdelrhman", "01140506664"));
        addClient(new Client("Perrie", "0114+90+06"));
        addClient(new Client("Seif", "011aad64"));
        addClient(new Client("Nouran", "011w6262"));
        addClient(new Client("Aaisha", "01dwd0666w4"));

        Collections.sort(phis);
        Collections.sort(clients);

        launch();
    }

}
