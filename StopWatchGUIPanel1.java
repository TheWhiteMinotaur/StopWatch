package project1;

import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.*;


public class StopWatchGUIPanel1 extends JPanel {

	private StopWatch stopWatchTimer;
	private Timer javaTimer;
	private TimerListener timer;
	private JButton add;
	private JButton sub;
	private JButton start;
	private JButton stop;
	private JButton reset;
	private JButton suspend;
	private JTextField minutes;
	private JTextField seconds;
	private JTextField milliseconds;
	private JLabel minutesLabel;
	private JLabel secondsLabel;
	private JLabel millisecondsLabel;
	private JLabel lblTime;
	private JLabel Label;

	public StopWatchGUIPanel1() {

		stopWatchTimer = new StopWatch (0, 0, 0);
		timer = new TimerListener();
		javaTimer = new Timer(1, timer);

		setPreferredSize(new Dimension(500, 300));
		setLayout(new GridLayout(7,2));


		minutesLabel = new JLabel("Minutes:");
		add(minutesLabel);

		minutes = new JTextField(3);
		add(minutes);



		secondsLabel = new JLabel("Seconds:");
		add(secondsLabel);

		seconds = new JTextField(3);
		add(seconds);


		millisecondsLabel = new JLabel("Milliseconds:");
		add(millisecondsLabel);

		milliseconds = new JTextField(3);
		add(milliseconds);

		Label = new JLabel("");
		add(Label);
		
		lblTime = new JLabel("");
		add(lblTime);

		suspend = new JButton("Suspend");
		ButtonListener listener5 = new ButtonListener();
		suspend.addActionListener(listener5);
		add(suspend);

		start = new JButton("Start");
		ButtonListener listener = new ButtonListener();
		start.addActionListener(listener);
		add(start);

		reset = new JButton("Reset");
		ButtonListener listener2 = new ButtonListener();
		reset.addActionListener(listener2);
		add(reset);

		stop = new JButton("Stop");
		ButtonListener listener1 = new ButtonListener();
		stop.addActionListener(listener1);
		add(stop);


		add = new JButton("Add");
		ButtonListener listener3 = new ButtonListener();
		add.addActionListener(listener3);
		add(add);

		sub = new JButton("Sub");
		ButtonListener listener4 = new ButtonListener();
		sub.addActionListener(listener4);
		add(sub);

	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == add) {
				try {
					StopWatch s = new StopWatch(Integer.parseInt(minutes.getText()),
							Integer.parseInt(seconds.getText()),
							Integer.parseInt(milliseconds.getText()));
					stopWatchTimer.add(s);
					lblTime.setText(stopWatchTimer.toString());
				}
				catch(IllegalArgumentException i){
					JOptionPane.showMessageDialog(null, "Enter an Integer");
				}
			}

			if(e.getSource() == sub) {
				try {
					StopWatch s = new StopWatch(Integer.parseInt(minutes.getText()),
							Integer.parseInt(seconds.getText()),
							Integer.parseInt(milliseconds.getText()));
					stopWatchTimer.sub(s);
					lblTime.setText(stopWatchTimer.toString());
				}
				catch(IllegalArgumentException i){
					JOptionPane.showMessageDialog(null, "Can't Subtract below 0");
				}
			}

			if(e.getSource() == stop) {
				javaTimer.stop();
			}

			if(e.getSource() == start) {
				javaTimer.start();
			}

			if(e.getSource() == reset) {
				stopWatchTimer = new StopWatch (0, 0, 0);
				StopWatch.suspend(false);
				minutes.setText("");
				seconds.setText("");
				milliseconds.setText("");
				lblTime.setText(stopWatchTimer.toString());
			}

			if(e.getSource() == suspend){
				StopWatch.suspend(true);
			}
		}
	}
	private class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			lblTime.setText(stopWatchTimer.toString());
			stopWatchTimer.inc();

		}

	}
}
