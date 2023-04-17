package com.pucgo.cliserver.tocofome;

import com.pucgo.cliserver.tocofome.dao.ItemDAO;
import com.pucgo.cliserver.tocofome.model.Item;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;


public class MainViewController {

    ItemDAO itemDAO = new ItemDAO();

    @FXML
    private void initialize() {
        List<Item> itens = itemDAO.findAll();

        itemsList.setCellFactory(param -> new ListCell<Item>() {
            private final ImageView imageView = new ImageView();
            private final Label textLabel = new Label();
            private final HBox hbox = new HBox(imageView, new VBox(textLabel));


            @Override
            protected void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {

                    new Thread(() -> imageView.setImage(new Image(item.getImageUrl(), 30d, 30d, true, true))).start();
                    textLabel.setText(item.getProduct());
                    hbox.setSpacing(10);
                    setGraphic(hbox);

                    // Adiciona um evento de clique na célula
                    setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2) {
                            // Ação a ser executada no clique duplo
                            Item clickedItem = getItem();
                            selectItemImage.setImage(new Image(clickedItem.getImageUrl(), 200d, 200d, true, true));
                            priceItem.setText("R$ " + clickedItem.getPrice().toString());
                            itemName.setText(clickedItem.getProduct().toUpperCase());
                            descriptionItem.setText(clickedItem.getDescription());
                            System.out.println("Item selecionado: " + clickedItem.getProduct());
                        }
                    });
                }
            }
        });

        itemsList.getStyleClass().add("my-list-view");
        // Adicione os itens à ListView
        itemsList.getItems().addAll(itens);
    }

    @FXML
    void onMouseEnter(MouseEvent event) {
        List<Item> itens = itemDAO.findAll();
        itemsList.getItems().addAll(itens);
    }


    @FXML
    private Label priceItem;

    @FXML
    private Label descriptionItem;

    @FXML
    private Label itemName;
    @FXML
    private ListView<Item> itemsList;

    @FXML
    private ImageView mainBanner;

    @FXML
    private ImageView selectItemImage;


}
