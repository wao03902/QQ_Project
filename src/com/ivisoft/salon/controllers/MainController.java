package com.ivisoft.salon.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

import com.ivisoft.salon.Salon;
import com.ivisoft.salon.custom.Clock;
import com.ivisoft.salon.dao.MasterDao;
import com.ivisoft.salon.dao.VisitDao;
import com.ivisoft.salon.model.Master;
import com.ivisoft.salon.model.Visit;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class MainController implements Initializable {
    
    @FXML
    private GridPane root;
    
    @FXML
    private ImageView logoPhoto;
    
    @FXML
    private ImageView userPhoto;
    
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
    private Button logoutButton;
    
    @FXML
    private Label timeLabel;
    
    @FXML
    private Label dataLabel;
    
    @FXML
    private SpreadsheetView mainTable;
    
    @FXML
    private DatePicker dataPicker;

    private LocalTime startTime = LocalTime.of(8, 0, 0);
    private LocalTime endTime = LocalTime.of(17, 0, 0);
    
    private List<Master> masters;
    private List<Visit> visits;
    
    private GridBase getGridByDate(LocalDate date) {
        masters = MasterDao.getAllMasters();
        visits = VisitDao.getVisitsByDate(date);
        LocalTime tempTime = startTime;
        
        int rowCount = (endTime.getHour() - startTime.getHour()) * 4 + 2;
        int columnCount = MasterDao.countMasters() + 1;
        GridBase grid = new GridBase(rowCount, columnCount);
        
        ObservableList<ObservableList<SpreadsheetCell>> rows = FXCollections.observableArrayList();
        
        final ObservableList<SpreadsheetCell> header = FXCollections.observableArrayList();
        SpreadsheetCell tempCell = SpreadsheetCellType.STRING.createCell(0, 0, 1, 1, "Мастер:");
        tempCell.setStyle("-fx-alignment: center; -fx-background-color: palegreen;");
        header.add(tempCell);
        
        int i = 1;
        for (Master master : masters) {
            SpreadsheetCell cell = SpreadsheetCellType.STRING.createCell(0, i++, 1, 1, master.getName());
            cell.setStyle("-fx-alignment: center; -fx-background-color: palegreen;");
            header.add(cell);
        }
        rows.add(header);
        
        for (int row = 1; row < grid.getRowCount(); ++row) {
            final ObservableList<SpreadsheetCell> list = FXCollections.observableArrayList();
            for (int column = 0; column < grid.getColumnCount(); ++column) {
                if (column == 0) {
                    String hour = tempTime.getHour() < 10 ? "0" + tempTime.getHour() : tempTime.getHour() + "";
                    String minute = tempTime.getMinute() < 10 ? "0" + tempTime.getMinute() : tempTime.getMinute() + "";
                    SpreadsheetCell cell = SpreadsheetCellType.STRING.createCell(row, column, 1, 1, hour + ":" + minute);
                    cell.setStyle("-fx-alignment: center; -fx-background-color: palegreen;");
                    list.add(cell);
                    tempTime = tempTime.plusMinutes(15);
                } else {
                    list.add(SpreadsheetCellType.STRING.createCell(row, column, 1, 1, " "));
                }
            }
            rows.add(list);
        }
        
        for (Visit visit : visits) {
            int row = ((visit.getDateAndTime().getHour() * 60 + visit.getDateAndTime().getMinute()) - (startTime.getHour() * 60 + startTime.getMinute())) / 15 + 1;
            int col = masters.indexOf(visit.getMaster()) + 1;
            if (visit.getService().getDuration() > 15) {
                int span = visit.getService().getDuration() / 15;
                rows.get(row).get(col).setRowSpan(span);
            }
            rows.get(row).get(col).setWrapText(true);
            rows.get(row).get(col).setItem(visit.getDateAndTime().getHour() + ":" + visit.getDateAndTime().getMinute() + " " + visit.getMaster().getName());
        }
        
        grid.setRows(rows);
        
        return grid;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Clock clock = new Clock();
        clock.setFont(new Font("Andalus", 36));
        timeLabel.setGraphic(clock);
        
        dataPicker.setValue(LocalDate.now());
        dataPicker.setEditable(false);
        masters = MasterDao.getAllMasters();
        
        mainTable.setEditable(false);
        
        mainTable.setGrid(getGridByDate(LocalDate.now()));
        mainTable.getColumns().get(0).setPrefWidth(50);
        
        dataPicker.valueProperty().addListener((ov, oldDate, newDate) -> {
            mainTable.setGrid(getGridByDate(newDate));
            mainTable.getGrid().getRows().get(0).get(0);
        });
    }
    
    @FXML
    private void scheduleAction(ActionEvent event) {
        
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
    
    @FXML
    private void addVisit(ActionEvent event) {
        
    }
}
