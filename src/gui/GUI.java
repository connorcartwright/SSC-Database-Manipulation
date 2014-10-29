package gui;

import java.awt.Component;

import javax.swing.*;

public class GUI {
	
	private JFrame mainFrame; // the mainframe/backbone of the GUI
	
	private static final int padding = 5; // blank space for layout management
	private Component errorFrame; // used when producing the error message
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		final GUI gui = new GUI();
	}

	public GUI() {
		// Step 1: create the components
		JButton registerStudent = new JButton("Register Student"); // creating/initialising the mainframe buttons
		JButton markStudent = new JButton("Add Marks");
		JButton produceStudentTranscript = new JButton("Student Transcript");
		JButton produceModuleTranscript = new JButton("Module Transcript");
	}

}
