package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import database.Build;

public class GUI {
	
	private JFrame mainFrame; // the mainframe/backbone of the GUI
	private JTextArea moduleTranscript, studentTranscript; // text areas for the Student Transcript and moduleTranscript
	private String[] titles = { "Mr", "Mrs", "Miss", "Ms", "Dr", "Madam" }; // add more titles here when needed in the future
	private String[] type = { "Sit", "Resit", "Repeat" }; // add more types in here when needed in the future
	private Integer[] mark = new Integer[101]; { for(int i = 0; i < 101; i++) { mark[i] = i; } };
	private static final int padding = 5; // blank space for layout management
	private Component errorFrame; // used when producing the error message
	private boolean tablesCreatedPopulated, registerStudentOpen, markStudentOpen, moduleTranscriptOpen, studentTranscriptOpen = false; // initialising so that we know whether the frames are open
	private Build build;
	
	
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		final GUI gui = new GUI();
	}

	public GUI() {
		build = new Build();
		// Step 1: create the components
		JButton createPopulate = new JButton("Create & Populate Tables");
		JButton clearDatabase = new JButton("Clear Database");
		JButton registerStudent = new JButton("Register Student"); // creating/initialising the mainframe buttons
		JButton markStudent = new JButton("Add Marks");
		JButton produceModuleTranscript = new JButton("Module Transcript");
		JButton produceStudentTranscript = new JButton("Student Transcript");
		
		moduleTranscript = new JTextArea(); // initialising the text areas and making them uneditable
		moduleTranscript.setEditable(false);
		studentTranscript = new JTextArea(); // initialising the text areas and making them uneditable
		studentTranscript.setEditable(false);
		
		// Step 2: Set the properties of the components
		createPopulate.setToolTipText("Create and Populate the Database Tables");
		createPopulate.setPreferredSize(new Dimension(250, 34));
		clearDatabase.setToolTipText("Clear the database of all tables/data.");
		clearDatabase.setPreferredSize(new Dimension(250, 34));
		
		
		registerStudent.setToolTipText("Register a new student"); // setting the tooltip of the buttons and their preferred size
		registerStudent.setPreferredSize(new Dimension(250, 34));
		markStudent.setToolTipText("Add Marks for a student"); // setting the tooltip of the buttons and their preferred size
		markStudent.setPreferredSize(new Dimension(200, 34));
		registerStudent.setToolTipText("Register a new student"); // setting the tooltip of the buttons and their preferred size
		
		produceModuleTranscript.setToolTipText("Produce module Transcript."); // setting the tooltip of the buttons and their preferred size
		produceModuleTranscript.setPreferredSize(new Dimension(280, 34));
		produceStudentTranscript.setToolTipText("Produce Student Transcript"); // setting the tooltip of the buttons and their preferred size
		produceStudentTranscript.setPreferredSize(new Dimension(280, 34));
		
		// Step 3: Create containers to hold the components
		mainFrame = new JFrame("SSC Exercise 2"); // initialising the mainframe
		mainFrame.getContentPane().setBackground(new Color(61, 145, 64)); // setting the background colour of the mainframe
		mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // setting the default close operation
		mainFrame.setPreferredSize(new Dimension(300, 290)); // setting the preferred size
		mainFrame.setResizable(false); // making it so that the frame cannot be resized, making the preferred size the fixed size.
		
		JPanel topBox = new JPanel(); // initialising the panels that will be present on the mainframe
		JPanel bottomBox = new JPanel(); // that will hold the components that need to be used
		
		// Step 4: Specify LayoutManagers
		mainFrame.getContentPane().setLayout(new BorderLayout()); // set the layout for the mainframe
		((JPanel) mainFrame.getContentPane()).setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		topBox.setLayout(new BorderLayout(0, 6)); // set the layout for the command panel
		topBox.setBorder(new EmptyBorder(padding, padding, padding, padding));

		bottomBox.setLayout(new BorderLayout(0, 6)); // set the layout for the slider panel
		bottomBox.setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		topBox.add(createPopulate, BorderLayout.NORTH);
		topBox.add(clearDatabase, BorderLayout.CENTER);
		topBox.add(registerStudent, BorderLayout.SOUTH); // adding the buttons to the top box
		
		bottomBox.add(markStudent, BorderLayout.NORTH); // adding the buttons to the top box
		bottomBox.add(produceModuleTranscript, BorderLayout.CENTER); // adding the buttons to the top box
		bottomBox.add(produceStudentTranscript, BorderLayout.SOUTH); // adding the buttons to the top box
		
		mainFrame.getContentPane().add(topBox, BorderLayout.NORTH); // adding the command box to the right of the mainframe
		mainFrame.getContentPane().add(bottomBox, BorderLayout.SOUTH); // adding the slider box to the left of the mainframe
		
		// Step 6: Arrange to handle events in the user interface
		createPopulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablesCreatedPopulated) {
					JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
							"The Tables are already Created/Populated!",
							"Already Created/Populated.", JOptionPane.ERROR_MESSAGE);	
				}
				else {
					createTables();
					populateTables();
					tablesCreatedPopulated = true;
				}
			}
		});	
		
		clearDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(! tablesCreatedPopulated) {
					JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
							"The database is already empty!",
							"Database already empty.", JOptionPane.ERROR_MESSAGE);	
				}
				else {
					clearDatabase();
					tablesCreatedPopulated = false;
				}
			}
		});
		
		registerStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(registerStudentOpen) {
					// do nothing if the frame is already open
				}
				else {
					registerStudent(); // open the frame to register a student;
				}
			}
		});	
		
		markStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(markStudentOpen) {
					// do nothing if the frame is already open
				}
				else {
					markStudent(); // open the frame to register a student;
				}
			}
		});
		
		produceModuleTranscript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(moduleTranscriptOpen) {
					// do nothing if the frame is already open
				}
				else {
					moduleTranscript(); // open the frame to register a student;
				}
			}
		});
		
		produceStudentTranscript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(studentTranscriptOpen) {
					// do nothing if the frame is already open
				}
				else {
					studentTranscript(); // open the frame to register a student;
				}
			}
		});
		
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitApp(); // run the exitApp method if the window 'x' button is pressed
			}
		});
		
		// Step 7: Display the GUI
		mainFrame.pack(); // build the mainframe
		centreWindow(mainFrame); // centre the mainframe
		mainFrame.setVisible(true); // and make it visible	
	}
	
	public void registerStudent() {
		registerStudentOpen = true;
		
		// Step 1: create the components
		final JFrame registerStudentFrame = new JFrame("Register a new Student");
		JComboBox<String> titleCombo = new JComboBox<String>(titles); // initialising the combo box
		
		JButton addStudentButton = new JButton("Add Student");
		JButton closeButton = new JButton("Close");
		
		JLabel title = new JLabel("Title: ");
		JLabel forename = new JLabel("Forename: ");
		JLabel surname = new JLabel("Surname: ");
		JLabel dateOfBirth = new JLabel("Date Of Birth (yyyy-mm-dd): ");
		
		JTextField forenameInput = new JTextField("John");
		JTextField surnameInput = new JTextField("Doe");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		dateFormat.setLenient(false);
		JFormattedTextField dateInput = new JFormattedTextField(dateFormat);
		
		// Step 2: Set the properties of the components
		addStudentButton.setToolTipText("Add a new Student to the database");
		closeButton.setToolTipText("Close this window.");
		
		forenameInput.setColumns(16);
		surnameInput.setColumns(16);
		dateInput.setColumns(12);
		
		// Step 3: Create containers to hold the components
		registerStudentFrame.setPreferredSize(new Dimension(350, 220));
		registerStudentFrame.setResizable(false); // make it so the frame can't be resized
		registerStudentFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // setting default close operation
		
		JPanel topPanel = new JPanel();
		JPanel centrePanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		
		JPanel titlePanel = new JPanel();
		JPanel forenamePanel = new JPanel();
		JPanel surnamePanel = new JPanel();
		JPanel dobPanel = new JPanel();
		
		// Step 4: Specify LayoutManagers
		registerStudentFrame.getContentPane().setLayout(new BorderLayout()); // setting the layout for the report frame
		((JComponent) registerStudentFrame.getContentPane()).setBorder(new EmptyBorder(
				padding, padding, padding, padding));
		
		topPanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		topPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		centrePanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		centrePanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		bottomPanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		bottomPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		
		
		titlePanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		titlePanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		forenamePanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		forenamePanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		surnamePanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		surnamePanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		dobPanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		dobPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		// Step 5: Add components to containers
		titlePanel.add(title, BorderLayout.WEST);
		titlePanel.add(titleCombo, BorderLayout.EAST);
		
		forenamePanel.add(forename, BorderLayout.WEST);
		forenamePanel.add(forenameInput, BorderLayout.EAST);
		
		surnamePanel.add(surname, BorderLayout.WEST);
		surnamePanel.add(surnameInput, BorderLayout.EAST);
		
		dobPanel.add(dateOfBirth, BorderLayout.WEST);
		dobPanel.add(dateInput, BorderLayout.EAST);
		
		topPanel.add(titlePanel, BorderLayout.NORTH);
		topPanel.add(forenamePanel, BorderLayout.SOUTH);
		centrePanel.add(surnamePanel, BorderLayout.NORTH);
		centrePanel.add(dobPanel, BorderLayout.SOUTH);
		bottomPanel.add(addStudentButton, BorderLayout.WEST);
		bottomPanel.add(closeButton, BorderLayout.EAST);
		
		
		registerStudentFrame.getContentPane().add(topPanel, BorderLayout.NORTH);
		registerStudentFrame.getContentPane().add(centrePanel, BorderLayout.CENTER);
		registerStudentFrame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		// Step 6: Arrange to handle events in the user interface
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerStudentFrame.dispose(); // close the window and switch focus back to the mainframe
				registerStudentOpen = false; // the frame is no longer open
			}
		});
		
		addStudentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean dateError = false;
				
			    try {
			      dateFormat.parse(dateInput.getText().trim());
			      dateError = false;
			    } catch (ParseException pe) {
			    	dateError = true;
			    }
				if (forenameInput.getText().length() > 30 || forenameInput.getText().length() < 1) {
					JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
							"The Forename text cannot be more than 30 characters, or less than 1 character.",
							"String size error", JOptionPane.ERROR_MESSAGE);			
				}
				else if (surnameInput.getText().length() > 30 || surnameInput.getText().length() < 1) {
					JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
							"The Surname text cannot be more than 30 characters, or less than 1 character.",
							"String size error", JOptionPane.ERROR_MESSAGE);	
				}
				else if (dateError) {
					JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
							"Invalid date.",
							"Bad date.", JOptionPane.ERROR_MESSAGE);
				}
				else {
					System.out.println(dateInput.getText());
					try {
					build.addNewStudent((titleCombo.getSelectedIndex()+1), forenameInput.getText(), surnameInput.getText(), dateInput.getText());
					
					registerStudentFrame.dispose(); // close the frame
					registerStudentOpen = false; // the frame is no longer open
					
					JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
							("Student " + forenameInput.getText() + " " + surnameInput.getText() + " has been added!"),
							"New Student added", JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
								"Failed to add new Student!",
								"Failed to add new Student!", JOptionPane.ERROR_MESSAGE);
						System.out.println("Failed to add new Student!");
					}
				}
			}
		});
		
		registerStudentFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				registerStudentOpen = false; // the frame is no longer open
			}
		});
		
		// Step 7: Display the GUI
		registerStudentFrame.pack(); // pack the report frame
		centreWindow(registerStudentFrame); // centre the report frame
		registerStudentFrame.setVisible(true); // and make it visible
	}
	
	public void markStudent() {
		markStudentOpen = true;
		String[] students = build.getAllStudents();
		
		String [] modules = build.getAllModules();
		
		// Step 1: create the components
		final JFrame markStudentFrame = new JFrame("Add Marks for a Student");
		JButton addMarkButton = new JButton("Add Mark");
		JButton closeButton = new JButton("Close");
		
		JComboBox<String> studentCombo = new JComboBox<String>(students); // initialising the combo box
		JComboBox<String> moduleCombo = new JComboBox<String>(modules); // initialising the combo box
		JComboBox<Integer> markCombo = new JComboBox<Integer>(mark); // initialising the combo box
		JTextField yearInput = new JTextField();
		JComboBox<String> typeCombo = new JComboBox<String>(type); // initialising the combo box
		JTextArea notesInput = new JTextArea();
		
		JLabel studentLabel = new JLabel("Student: ");
		JLabel moduleLabel = new JLabel("Module: ");
		JLabel markLabel = new JLabel("Mark: ");
		JLabel yearLabel = new JLabel("Year: ");
		JLabel typeLabel = new JLabel("Type: ");
		JLabel notesLabel = new JLabel("Notes: ");
		
		// Step 2: Set the properties of the components
		addMarkButton.setToolTipText("Add a new Student to the database");
		closeButton.setToolTipText("Close this window.");
		markCombo.setPreferredSize(new Dimension(60, 34));
		notesInput.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
		
		yearInput.setColumns(8);
		notesInput.setColumns(20);
		notesInput.setRows(4);
		
		// Step 3: Create containers to hold the components
		markStudentFrame.setPreferredSize(new Dimension(320, 390));
		markStudentFrame.setResizable(false); // make it so the frame can't be resized
		markStudentFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // setting default close operation
		
		JPanel contentPanel = new JPanel();
		JPanel topContentPanel = new JPanel();
		JPanel bottomContentPanel = new JPanel();
		
		JPanel topTopPanel = new JPanel();
		JPanel topBottomPanel = new JPanel();
		
		JPanel bottomTopPanel = new JPanel();
		JPanel bottomBottomPanel = new JPanel();
		
		JPanel studentPanel = new JPanel();
		JPanel modulePanel = new JPanel();
		JPanel markPanel = new JPanel();
		JPanel yearPanel = new JPanel();
		JPanel typePanel = new JPanel();
		JPanel notesPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		
		// Step 4: Specify LayoutManagers
		markStudentFrame.getContentPane().setLayout(new BorderLayout()); // setting the layout for the report frame
		((JComponent) markStudentFrame.getContentPane()).setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		contentPanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		contentPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		topContentPanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		topContentPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		bottomContentPanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		bottomContentPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		topTopPanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		topTopPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		topBottomPanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		topBottomPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		bottomTopPanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		bottomTopPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		bottomBottomPanel.setLayout(new BorderLayout()); // setting the layout for the panel holding the text
		bottomBottomPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
		
		// Step 5: Add components to containers
		studentPanel.add(studentLabel, BorderLayout.WEST);
		studentPanel.add(studentCombo, BorderLayout.EAST);
		
		modulePanel.add(moduleLabel, BorderLayout.WEST);
		modulePanel.add(moduleCombo, BorderLayout.WEST);
		
		markPanel.add(markLabel, BorderLayout.WEST);
		markPanel.add(markCombo, BorderLayout.EAST);
		
		yearPanel.add(yearLabel, BorderLayout.WEST);
		yearPanel.add(yearInput, BorderLayout.EAST);
		
		typePanel.add(typeLabel, BorderLayout.WEST);
		typePanel.add(typeCombo, BorderLayout.EAST);
		
		notesPanel.add(notesLabel, BorderLayout.WEST);
		notesPanel.add(notesInput, BorderLayout.EAST);
		
		buttonPanel.add(addMarkButton, BorderLayout.WEST);
		buttonPanel.add(closeButton, BorderLayout.EAST);
		
		topTopPanel.add(studentPanel, BorderLayout.NORTH);
		topTopPanel.add(modulePanel, BorderLayout.SOUTH);
		topBottomPanel.add(markPanel, BorderLayout.NORTH);
		topBottomPanel.add(yearPanel, BorderLayout.SOUTH);
		bottomTopPanel.add(typePanel, BorderLayout.NORTH);
		bottomTopPanel.add(notesPanel, BorderLayout.SOUTH);
		bottomBottomPanel.add(buttonPanel, BorderLayout.CENTER);
		
		topContentPanel.add(topTopPanel, BorderLayout.NORTH);
		topContentPanel.add(topBottomPanel, BorderLayout.SOUTH);
		bottomContentPanel.add(bottomTopPanel, BorderLayout.NORTH);
		bottomContentPanel.add(bottomBottomPanel, BorderLayout.SOUTH);
		
		contentPanel.add(topContentPanel, BorderLayout.NORTH);
		contentPanel.add(bottomContentPanel, BorderLayout.SOUTH);
		
		markStudentFrame.getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		// Step 6: Arrange to handle events in the user interface
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				markStudentFrame.dispose(); // close the window and switch focus back to the mainframe
				markStudentOpen = false; // the frame is no longer open
			}
		});
		
		addMarkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int year = 0;
				try {
				year = Integer.parseInt(yearInput.getText());
				}
				catch (Exception e1) {
					JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
							"Invalid Year entered.",
							"Invalid Year", JOptionPane.ERROR_MESSAGE);	
				}
				if(studentCombo.getSelectedIndex() < 1) {
					JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
							"No Student Selected.",
							"No Student", JOptionPane.ERROR_MESSAGE);	
				}
				else if(moduleCombo.getSelectedIndex() < 1) {
					JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
							"No Module Selected.",
							"No Module", JOptionPane.ERROR_MESSAGE);	
				}
				else if(year > 2014 || year < 1980) {
					JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
							"Invalid Year entered.",
							"Invalid Year", JOptionPane.ERROR_MESSAGE);	
				}
				else {
					try {
						build.markStudent(studentCombo.getSelectedIndex(), moduleCombo.getSelectedIndex(), year, (typeCombo.getSelectedIndex()+1), markCombo.getSelectedIndex(), notesInput.getText());
						markStudentFrame.dispose();
						markStudentOpen = false;
						JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
								("Marks for Student " + studentCombo.getSelectedItem() + " has been added!"),
								"New Marks added", JOptionPane.INFORMATION_MESSAGE);
					}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(errorFrame, // then show an informative error message
								"Failed to add Marks for Student!",
								"Failed to add Marks for Student!", JOptionPane.ERROR_MESSAGE);
						System.out.println("Failed to Marks for Student!");
					}
				}
			}
		});
		
		markStudentFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				markStudentOpen = false; // the frame is no longer open
			}
		});
		
		// Step 7: Display the GUI
		markStudentFrame.pack(); // pack the report frame
		centreWindow(markStudentFrame); // centre the report frame
		markStudentFrame.setVisible(true); // and make it visible
		
	}
	
	public void moduleTranscript() {
		// code to display module transcript
	}
	
	public void studentTranscript() {
		// code to display student transcript
	}
	
	public void createTables() {
		build.createTables();
	}
	
	public void populateTables() {
		build.populateTables();
	}
	
	public void clearDatabase() {
		build.clearDatabase();
	}
	
	/**
	 * Helper method to ensure consistency in leaving application.
	 * With credit to Aston University Computer Science.
	 */
	private void exitApp() {
		// Display confirmation dialog before exiting application
		int response = JOptionPane.showConfirmDialog(mainFrame,
				"Do you really want to quit?", "Quit?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		// Don't quit
	}
	
	/**
	 * This method centres the GUI window and works on all screen sizes.
	 * With credit to Don @ StackOverflow.
	 * 
	 * @param frame the window/frame to be centred
	 * @link http://stackoverflow.com/questions/144892/how-to-center-a-window-in-java
	 */
	private static void centreWindow(Window frame) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
		frame.setLocation(x, y);
	}

}
