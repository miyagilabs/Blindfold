/*
 * Copyright (C) 2017 Miyagilabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.miyagilabs.blindfold;

import com.miyagilabs.blindfold.structure.BaseWalker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Görkem Mülayim
 */
public class Demo extends Application implements Initializable, EventHandler<KeyEvent> {
    @FXML
    private Label label;
    private Stage primaryStage;
    private BaseWalker baseWalker;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, this);
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Demo");
        ClassLoader classLoader = Demo.class.getClassLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/Demo.fxml"));
        fxmlLoader.setControllerFactory((Class<?> param) -> {
            return this;
        });
        Parent root = (Parent) fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(KeyEvent event) {
        if(baseWalker == null) {
            return;
        }
        switch(event.getCode()) {
            case UP:
                label.setText(baseWalker.stepBackward());
                break;
            case DOWN:
                label.setText(baseWalker.stepForward());
                break;
            case LEFT:
                label.setText(baseWalker.stepOut());
                break;
            case RIGHT:
                label.setText(baseWalker.stepIn());
                break;
        }
    }

    @FXML
    private void openFileOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(this.primaryStage);
        if(file != null) {
            label.setText("");
            StringBuilder code = new StringBuilder(256);
            try(BufferedReader bf = new BufferedReader(new FileReader(file))) {
                String line;
                while((line = bf.readLine()) != null) {
                    code.append(line).append('\n');
                }
            }
            if(baseWalker == null) {
                baseWalker = new BaseWalker(code.toString());
            }
            baseWalker.setCode(code.toString());
        }
    }
}
