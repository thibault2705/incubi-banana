package com.banana.timer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TimerApplication extends Application {

	public static void main(String[] args) {
		try {
			Application.launch(args);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/timer.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.initStyle(StageStyle.UTILITY);
		stage.resizableProperty().setValue(Boolean.FALSE);
		stage.show();
	}

}
