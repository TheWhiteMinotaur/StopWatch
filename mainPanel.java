package project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class mainPanel extends JPanel {

	private StopWatchGUIPanel1 p1;
	private StopWatchGUIPanel1 p2;
	private StopWatchGUIPanel1 p3;

	public mainPanel () {
		p1 = new StopWatchGUIPanel1();
		p2 = new StopWatchGUIPanel1();
		p3 = new StopWatchGUIPanel1();

		add (p1);
		add (p2);
		add (p3);

	}
}
