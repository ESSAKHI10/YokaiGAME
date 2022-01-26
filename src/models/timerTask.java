package models;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class timerTask {
	static long min, sec, hr, totalSec = 0;
	String time ;

	public openNewWindowa opneNEwWindows;
    public timerTask() {
	 
	

}
    public void setTimer( Label lab) {
    	
    	totalSec=1200;
    	Timer timer = new Timer();
    	TimerTask timerTask = new TimerTask() {
    		//Timer timer = new Timer();

    		@Override
    		public void run() {
    	Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				//System.out.println("after 1 sec ...");
	    		time = converTime();
	    		lab.setText(time);
	    		 if (totalSec<0) {
	    			 System.out.println("time over");
	    			 timer.cancel();
	    				opneNEwWindows= new openNewWindowa();
	    				opneNEwWindows.open("/FichierXml/gameOver.fxml");
	    		 }
	    		 
			
			}
		}
    	);
    		}
    		
    	};
    	
    	timer.schedule(timerTask ,  0, 1000);
    	
    }

public   String converTime () {
	min = TimeUnit.SECONDS.toMinutes(totalSec);
	sec = totalSec - (min * 60);
	hr = TimeUnit.MINUTES.toHours(min);
	min = min - (hr * 60);
//	System.out.println(format(hr) + ":" + format(min) + ":" + format(sec));
	totalSec--;
	return format(hr) + ":" + format(min) + ":" + format(sec);
}
private   String format (long value ) {
	 if (value <10) {
		 return 0+""+value;
	 }
	 return value+"";
}

}