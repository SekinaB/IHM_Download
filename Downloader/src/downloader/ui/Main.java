package downloader.ui;

import downloader.fc.Downloader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import java.nio.file.Paths;


public class Main extends Application {
	public void start(Stage stage) {
		ScrollPane root = new ScrollPane();
		VBox vbox = new VBox();
		root.setPrefSize(500, 500);
		root.setContent(vbox);
		for(String url : getParameters().getRaw()){
			Downloader downloader;
			try {
				downloader = new Downloader(url);
				ProgressBar bar = new ProgressBar();
				bar.progressProperty().bind(downloader.progressProperty());
				bar.setPrefWidth(500);
				vbox.getChildren().add(bar);
				Thread th = new Thread(downloader);
				th.setDaemon(true);
				th.start();
				System.out.println(Paths.get(".").toAbsolutePath().normalize().toString());	
			 }
			 catch(RuntimeException e) {
				 continue;
			 }
		}
	
			
		stage.setTitle("Downloader");
		stage.setScene(new Scene(root));
		stage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
