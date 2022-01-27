package models;

 
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
 
import java.util.Timer;
import java.util.TimerTask;
 

 
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
public class Music  implements Serializable {
 private File directory;
 private File[] files;
 private Media media ;
 private MediaPlayer mediaPlayer;
 private ArrayList<File>  songs;
 
 private int songNumber ;
 private Timer timer;
	private TimerTask task;
 

	private boolean running;
 

public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}
	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}
public Music(Label songLabe)  {
	super();
	this.directory = new File("music");
	this.files = directory.listFiles();
	this.songs=new ArrayList<File>();
	
	
	System.out.println(directory.getPath());
	System.out.println(directory.listFiles());
	if(files != null) {
		for (File file :files) {
			songs.add(file);
			System.out.println(file.getName());
		}
	}
	media = new Media(songs.get(songNumber).toURI().toString());
	System.out.println(media.toString());
	mediaPlayer = new MediaPlayer(media);
	songLabe.setText(songs.get(songNumber).getName());
}
public void playMedia(Slider volumeSlider ,ProgressBar sonProgressBar) {
	beginTimer(sonProgressBar);
	 
	mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
	mediaPlayer.play();
}
 


public void pauseMedia() {
	
	cancelTimer();
	mediaPlayer.pause();
}

public void resetMedia(ProgressBar songProgressBar) {
	
	songProgressBar.setProgress(0);
	mediaPlayer.seek(Duration.seconds(0));
}

public void previousMedia(Label songLabel, Slider volumeSlider,ProgressBar sonProgressBar) {
	
	if(songNumber > 0) {
		
		songNumber--;
		
		mediaPlayer.stop();
		
		if(running) {
			
			cancelTimer();
		}
		
		media = new Media(songs.get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		
		songLabel.setText(songs.get(songNumber).getName());
		
		playMedia(volumeSlider,sonProgressBar);
	}
	else {
		
		songNumber = songs.size() - 1;
		
		mediaPlayer.stop();
		
		if(running) {
			
			cancelTimer();
		}
		
		media = new Media(songs.get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		
		songLabel.setText(songs.get(songNumber).getName());
		
		playMedia(volumeSlider,sonProgressBar);
	}
}

public void nextMedia(Label songLabel, Slider volumeSlider,ProgressBar sonProgressBar) {
	
	if(songNumber < songs.size() - 1) {
		
		songNumber++;
		
		mediaPlayer.stop();
		
		if(running) {
			
			cancelTimer();
		}
		
		media = new Media(songs.get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		
		songLabel.setText(songs.get(songNumber).getName());
		
		playMedia(volumeSlider,sonProgressBar);
	}
	else {
		
		songNumber = 0;
		
		mediaPlayer.stop();
		
		media = new Media(songs.get(songNumber).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		
		songLabel.setText(songs.get(songNumber).getName());
		playMedia(volumeSlider,sonProgressBar);
	}
}

 

public void beginTimer(ProgressBar songProgressBar) {
	
	timer = new Timer();
	
	task = new TimerTask() {
		
		public void run() {
			
			running = true;
			double current = mediaPlayer.getCurrentTime().toSeconds();
			double end = media.getDuration().toSeconds();
			songProgressBar.setProgress(current/end);
			
			if(current/end == 1) {
				
				cancelTimer();
			}
		}
	};
	
	timer.scheduleAtFixedRate(task, 0, 1000);
}

public void cancelTimer() {
	
	running = false;
	timer.cancel();
}




















public File getDirectory() {
	return directory;
}
public void setDirectory(File directory) {
	this.directory = directory;
}
public File[] getFiles() {
	return files;
}
public void setFiles(File[] files) {
	this.files = files;
}
 
 
 
}
