package ckcc.assignment.AirlineProject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class MainMenu extends JFrame {
	private JMenuBar menuBar;
	private JMenu mFile, mAirline, mFlight, mSchedule, mHelp;
	//For Menu File
	private JMenuItem mItemExit;
	//For Menu Airline
	private JMenuItem mItemAddAirline;
	//For Menu Flight
	private JMenuItem mItemAddFlight;
	private JMenuItem mItemUpdateFlightStatus;
	private JMenuItem mItemShowFlight;
	//For Menu Schedule
	private JMenuItem mItemDepartures;
	private JMenuItem mItemArrivals;
	private JMenuItem mItemClearSchedule;
	//For Menu Help
	private JMenuItem mItemWelcome;
	private JMenuItem mItemHelpContents;
	private JMenuItem mItemCheckUpdate;
	private JMenuItem mItemAbout;
	public MainMenu() {
		//Create Object Menu Item of File
		mItemExit = new JMenuItem("Exit");
		//Create Object Menu File and add its Item
		mFile = new JMenu("File");
		mFile.add(mItemExit);
		//================END MENU FILE==================/
		//Create Object Menu Item of Airline
		mItemAddAirline = new JMenuItem("Add Airline");
		//Create Object Menu Airline and add its Item
		mAirline = new JMenu("Airline");
		mAirline.add(mItemAddAirline);
		//================END MENU AIRLINE==================/
		//Create Object Menu Item of Flight
		mItemAddFlight = new JMenuItem("Add Flight");
		mItemUpdateFlightStatus = new JMenuItem("Update Flight Status");
		mItemShowFlight = new JMenuItem("Show Flight Information");		
		//Create Object Menu Flight and add its Item
		mFlight = new JMenu("Flight");
		mFlight.add(mItemAddFlight);
		mFlight.add(mItemUpdateFlightStatus);
		mFlight.add(mItemShowFlight);		
		//================END MENU FLIGHT==================/
		//Create Object Menu Item of Schedule
		mItemDepartures = new JMenuItem("Departures");
		mItemArrivals = new JMenuItem("Arrivals");
		mItemClearSchedule = new JMenuItem("Clear Schedule");		
		//Create Object Menu Schedule and add its Item
		mSchedule = new JMenu("Schedule");
		mSchedule.add(mItemDepartures);
		mSchedule.add(mItemArrivals);
		mSchedule.add(mItemClearSchedule);		
		//================END MENU SCHEDULE==================/
		// Create Object Menu Item of Help
		mItemWelcome = new JMenuItem("Welcome");
		mItemHelpContents = new JMenuItem("Help Contents");
		mItemCheckUpdate = new JMenuItem("Check Update");
		mItemAbout = new JMenuItem("About Us");
		// Create Object Menu Help and add its items
		mHelp = new JMenu("Help");
		mHelp.add(mItemWelcome);
		mHelp.add(mItemHelpContents);
		mHelp.add(new JSeparator()); // Add Line to Divide
		mHelp.add(mItemCheckUpdate);
		mHelp.add(mItemAbout);
		//=========================END MENU HELP ======================/
		
		// Create Object Menu Bar and add its menus
		menuBar = new JMenuBar();
		menuBar.add(mFile); //Add File
		menuBar.add(mAirline); //Add Airline
		menuBar.add(mFlight); //Add Flight
		menuBar.add(mSchedule); //Add Schedule
		menuBar.add(mHelp); //Add Help
		//========================END MENU BAR ======================/
		
		//**Start Left And Right Panel
		//Add JTree into LeftPanel
		JPanel leftPanel = new JPanel();
		leftPanel.add(new JLabel("Left"));
		//leftPanel.add(createJTreePanel());
		//JTree leftJTree = createJTree();
		//Add RightPanel
		JPanel rightPanel = new JPanel();
		rightPanel.add(new JLabel("Right"));
		//jTab = new JTabbedPane();
		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, 
										leftPanel, rightPanel);		
		jsp.setDividerLocation(180); // To set space between divider 
		//========================END LEFT AND RIGHT PANEL======================/
		
		// Add Menu bar to Frame
		add(menuBar);
		add(jsp);
		setTitle("Airline Management v1.0");
		setJMenuBar(menuBar);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	
}
