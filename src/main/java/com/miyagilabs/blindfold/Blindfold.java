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

import com.miyagilabs.blindfold.structure.SyntaxError;
import com.miyagilabs.blindfold.structure.TreeViewTraverser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Blindfold extends Application implements Initializable, EventHandler<KeyEvent> {
    private static final Logger LOGGER = Logger.getLogger(Blindfold.class.getName());
    private static final String SAMPLE_CLASS_PATH = "sample/SampleClass.java";

    @FXML
    private Label label;
    private Stage primaryStage;
    private TreeViewTraverser treeViewTraverser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(SAMPLE_CLASS_PATH).getFile());
            byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
            String code = new String(encoded, Charset.defaultCharset());
            try {
                treeViewTraverser = new TreeViewTraverser(code);
            } catch(SyntaxError ex) {
                LOGGER.log(Level.SEVERE, SAMPLE_CLASS_PATH + " file contains syntax error.");
            }
            label.setText(treeViewTraverser.viewCurrent());
        } catch(IOException ex) {
            LOGGER.log(Level.SEVERE, "An IO error occured while reading file: "
                    + SAMPLE_CLASS_PATH, ex);
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, this);
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Blindfold");
        ClassLoader classLoader = getClass().getClassLoader();
        FXMLLoader fxmlLoader = new FXMLLoader(classLoader.getResource("fxml/Blindfold.fxml"));
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
        if(treeViewTraverser == null) {
            return;
        }
        switch(event.getCode()) {
            case UP:
                label.setText(treeViewTraverser.stepBackward());
                break;
            case DOWN:
                label.setText(treeViewTraverser.stepForward());
                break;
            case LEFT:
                label.setText(treeViewTraverser.stepOut());
                break;
            case RIGHT:
                label.setText(treeViewTraverser.stepIn());
                break;
        }
    }

    @FXML
    private void openFileOnAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(this.primaryStage);
        if(file == null) {
            return;
        }
        byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        String code = new String(encoded, Charset.defaultCharset());
        try {
            treeViewTraverser = new TreeViewTraverser(code);
        } catch(SyntaxError ex) {
            label.setText("We can not parse the code due to syntax error.");
            return;
        }
        label.setText(treeViewTraverser.viewCurrent());
    }
}
