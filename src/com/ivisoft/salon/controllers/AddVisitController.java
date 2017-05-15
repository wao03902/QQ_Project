package com.ivisoft.salon.controllers;

import static com.ivisoft.salon.utils.JavaFXUtil.createScene;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.ivisoft.salon.Salon;
import com.ivisoft.salon.custom.Clock;
import com.ivisoft.salon.dao.ClientDao;
import com.ivisoft.salon.dao.DictionaryDao;
import com.ivisoft.salon.dao.MasterDao;
import com.ivisoft.salon.dao.ServiceDao;
import com.ivisoft.salon.dao.VisitDao;
import com.ivisoft.salon.model.Client;
import com.ivisoft.salon.model.Dictionary;
import com.ivisoft.salon.model.Master;
import com.ivisoft.salon.model.Service;
import com.ivisoft.salon.model.Visit;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

public class AddVisitController implements Initializable {
    
    @FXML
    private ChoiceBox<Dictionary> sectionField;
    
    @FXML
    private ChoiceBox<Service> serviceField;
    
    @FXML
    private ChoiceBox<DiscountType> discountTypeField;
    
    public enum DiscountType {
        PERCENTAGE("%"), CURRENCY("грн.");
        
        private final String value;
        
        private DiscountType(String value) {
            this.value = value;
        }
        
        @Override
        public String toString() {
            return value;
        }
    }
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private ComboBox<LocalTime> timeField;

    @FXML
    private ChoiceBox<Master> masterField;
    
    @FXML
    private TextField priceField;
    
    @FXML
    private TextArea noteField;
    
    @FXML
    private TextField discountField;
    
    @FXML
    private TextField additionallyField;
    
    @FXML
    private TextField durationField;
    
    @FXML
    private TextField nameFiled;
    
    @FXML
    private TextField telField;
    
    @FXML
    private TextField baseTelField;
    
    @FXML
    private TextField totalField;
    
    @FXML
    private Button scheduleButton;
    
    @FXML
    private Button servicesButton;
    
    @FXML
    private Button clientsButton;
    
    @FXML
    private Button staffButton;
    
    @FXML
    private Button statusButton;
    
    @FXML
    private Button saveButton;
    
    @FXML
    private Label timeLabel;

    @FXML
    private Button logoutButton;
    
    @FXML
    private Label dataLabel;
    
    private Client client = null;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock clock = new Clock();
        clock.setFont(new Font("Andalus", 36));
        timeLabel.setGraphic(clock);
        
        sectionField.setItems(FXCollections.observableList(DictionaryDao.getDictsByType("service_type")));
        serviceField.setItems(FXCollections.observableList(ServiceDao.getAllServices()));
        
        sectionField.valueProperty().addListener((p, oldValue, newValue) -> {
            serviceField.setItems(FXCollections.observableList(ServiceDao.getServicesByTypeId(newValue.getId())));
        });
        
        serviceField.valueProperty().addListener((p, oldValue, newValue) -> {
            if (newValue == null) {
                masterField.setItems(FXCollections.emptyObservableList());
            } else {
                masterField.setItems(FXCollections.observableList(MasterDao.getMastersByServiceId(newValue.getId())));
            }
        });
        
        discountTypeField.setItems(FXCollections.observableList(Arrays.asList(DiscountType.CURRENCY, DiscountType.PERCENTAGE)));
        
        priceField.textProperty().addListener((o, oldText, newText) -> {
            refreshTotalPrice();
        });
        
        totalField.setText(String.valueOf(0));
        
        additionallyField.textProperty().addListener((o, oldText, newText) -> {
            System.out.println("qwerty");
            refreshTotalPrice();
        });
        
        discountField.textProperty().addListener((o, oldText, newText) -> {
            refreshTotalPrice();
        });
        
        discountTypeField.valueProperty().addListener((o, oldText, newText) -> {
            refreshTotalPrice();
        });
        
        discountTypeField.getSelectionModel().select(DiscountType.CURRENCY);
        
        telField.textProperty().addListener((o, oldText, newText) -> {
            if (newText.length() == 10) {
                client = ClientDao.getClientByPhone(newText);
                if (client != null) {
                    nameFiled.setText(client.getName());
                }
            }
        });
        
        datePicker.setValue(LocalDate.now());
        timeField.getSelectionModel().select(0);
    }
    
    private void refreshTotalPrice() {
        Integer result = 0;
        
        if (!priceField.getText().equals("")) {
            result += Integer.valueOf(priceField.getText());
        }
        
        if (!additionallyField.getText().equals("")) {
            result += Integer.valueOf(additionallyField.getText());
        }
        
        if (!discountField.getText().equals("") && discountTypeField.getSelectionModel().getSelectedItem().equals(AddVisitController.DiscountType.CURRENCY)) {
            result -= Integer.valueOf(discountField.getText());
        } else if (!discountField.getText().equals("") && discountTypeField.getSelectionModel().getSelectedItem().equals(AddVisitController.DiscountType.PERCENTAGE)) {
            int percent = result / 100 * Integer.valueOf(discountField.getText());
            result -= percent;
        }
        
        totalField.setText(String.valueOf(result));
        
        LocalTime temp = MainController.startTime;
        ObservableList<LocalTime> options = FXCollections.observableArrayList();
        while (!temp.equals(MainController.endTime.plusMinutes(15))) {
            options.add(temp);
            temp = temp.plusMinutes(15);
        }
        timeField.setItems(options);
    }
    
    @FXML
    private void saveAction(ActionEvent event) {
        Visit visit = new Visit();
        visit.setPrice(Integer.valueOf(priceField.getText().trim()));
        visit.setDuration(Integer.valueOf(durationField.getText().trim()));
        visit.setMaster(masterField.getValue());
        visit.setService(serviceField.getValue());
        visit.setStatus(1);
        visit.setTotalPrice(Integer.valueOf(totalField.getText().trim()));
        visit.setDateAndTime(LocalDateTime.of(datePicker.getValue(), timeField.getValue()));
        visit.setDiscountType(DictionaryDao.getDictByCaption(discountTypeField.getValue().value));
        visit.setDiscount(Integer.valueOf(discountField.getText()));
        
        if (client != null) {
            visit.setClient(client);
        } else {
            client = new Client();
            client.setName(nameFiled.getText().trim());
            client.setPhone(telField.getText().trim());
            client.setDescription(noteField.getText().trim());
            client.setSex(DictionaryDao.getDictByCaption("Мужской"));
            client.setStatus(1);
            ClientDao.createClient(client);
            client = ClientDao.getClientByPhone(client.getPhone());
            visit.setClient(client);
        }
        
        VisitDao.createVisit(visit);
        
        Salon.mainScene = createScene(this, "main");
        scheduleAction(event);
    }
    
    @FXML
    private void scheduleAction(ActionEvent event) {
        Salon.stage.setTitle("Новый визит");
        Salon.stage.setScene(Salon.mainScene);
    }
    
    @FXML
    private void servicesAction(ActionEvent event) {
        Salon.stage.setTitle("Услуги");
        Salon.stage.setScene(Salon.servicesScene);
    }
    
    @FXML
    private void clientsAction(ActionEvent event) throws Exception {
        Salon.stage.setTitle("Клиенты");
        Salon.stage.setScene(Salon.clientsScene);
    }
    
    @FXML
    private void staffAction(ActionEvent event) throws IOException {
        Salon.stage.setTitle("Персонал");
        Salon.stage.setScene(Salon.staffScene);
    }

    @FXML
    private void statusAction(ActionEvent event) {
        
    }
    
    @FXML
    private void logoutAction(ActionEvent event) {
        
    }
    
    @FXML
    private void fullScreenAction(ActionEvent event) {
        
    }
}
