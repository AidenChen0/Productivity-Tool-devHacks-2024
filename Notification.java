/**
 * HOW TO USE:
 * Use Notification.nofity(title, message); to make a notification.
 */

/**
 * "you have x hours of work left to do on this assignment"
 * 	
 * 
 * pomodoro:
 * 		"pomodoro has started, work for 25 mins"
 * 		"stop working, 5 min rest period"
 * 
 */

import java.awt.*;
import java.awt.TrayIcon.MessageType;

class Notification {
	public Notification() {
		
	}
	
	public static void notify(String title, String message) {
		SystemTray tray;
		TrayIcon trayIcon;
		Image icon;
		
		if (SystemTray.isSupported()) {
			tray = SystemTray.getSystemTray();
			icon = Toolkit.getDefaultToolkit().createImage("bulb.gif"); // change img source
			trayIcon = new TrayIcon(icon, "slay"); //change if want to
			
			try {
				tray.add(trayIcon);
				trayIcon.displayMessage(title, message, MessageType.INFO);
			} catch (AWTException e) {
				System.out.println("Failed to make notification.");
			}
			
		} else {
			System.out.println("This platform does not support SystemTray.");
		}
	}
}
