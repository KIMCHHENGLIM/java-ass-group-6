package ckcc.assignment.AirlineProject;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
public class MainMenu extends JFrame implements ActionListener{
	private JMenuBar menuBar;
	private JMenu mFile, mAirline, mFlight, mSchedule, mHelp;
	//For Menu File
	private JMenuItem mItemExit;
	//For Menu Airline
	private JMenuItem mItemAddAirline, mItemShowAirline;
	//For Menu Flight
	private JMenuItem mItemAddFlight, mItemUpdateFlightStatus, mItemShowFlight;
	//For Menu Schedule
	private JMenuItem mItemDepartures, mItemArrivals, mItemClearSchedule;
	//For Menu Help
	private JMenuItem mItemWelcome, mItemHelpContents, mItemCheckUpdate, mItemAbout;
	//JTree 
	private JTree jTree;
	//JTab
	private JTabbedPane jTab;
	//Add Airline
	private JTextField txtAirlineName, txtAirlineCode, txtAircraftModel;
	private JTextField txtFirstClass, txtBusinessClass, txtEconomyClass;
	private JButton btnSave, btnClear;
	private JLabel lblResult;
	
	public MainMenu() {
		//Create Object Menu Item of File
		mItemExit = new JMenuItem("Exit");
		//Create Object Menu File and add its Item
		mFile = new JMenu("File");
		mFile.add(mItemExit);
		mItemExit.addActionListener(this);
		//================END MENU FILE==================/
		//Create Object Menu Item of Airline
		mItemAddAirline = new JMenuItem("Add Airline");
		mItemShowAirline = new JMenuItem("Show Airline");
		//Create Object Menu Airline and add its Item
		mAirline = new JMenu("Airline");
		mAirline.add(mItemAddAirline);
		mAirline.add(mItemShowAirline);
		mItemAddAirline.addActionListener(this);
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
		mItemWelcome.addActionListener(this);
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
		JTree leftJTree = createJTree();
		//Add JTab into RightPanel	
		jTab = new JTabbedPane();
		performOpenWelcome(false);	
		//Split to 2 Panel
		JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, 
								leftJTree, jTab);		
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
	
	public JTree createJTree() {

		//Create Tree Node Airline
		DefaultMutableTreeNode nodeAirline = new DefaultMutableTreeNode("Airline");
		//Create Tree Node Add Airline
		DefaultMutableTreeNode nodeAddAirline = new DefaultMutableTreeNode("Add Airline");
		DefaultMutableTreeNode nodeShowAirline = new DefaultMutableTreeNode("Show Airline");
		nodeAirline.add(nodeAddAirline);
		nodeAirline.add(nodeShowAirline);
		//===========================END TREE NODE OF AIRLINE ====================/		
		//Create Tree Node Flight
		DefaultMutableTreeNode nodeFlight = new DefaultMutableTreeNode("Flight");
		//Create Tree Node Add Flight
		DefaultMutableTreeNode nodeAddFlight = new DefaultMutableTreeNode("Add Flight");
		DefaultMutableTreeNode nodeUpdateFlight = new DefaultMutableTreeNode("Update Flight Status");
		DefaultMutableTreeNode nodeShowFlight = new DefaultMutableTreeNode("Show Flight Information");
		nodeFlight.add(nodeAddFlight);
		nodeFlight.add(nodeUpdateFlight);
		nodeFlight.add(nodeShowFlight);
		//===========================END TREE NODE OF FLIGHT ====================/
		//Create Tree Node Schedule
		DefaultMutableTreeNode nodeSchedule = new DefaultMutableTreeNode("Schedule");
		//Create Tree Node Add Schedule
		DefaultMutableTreeNode nodeDepartures = new DefaultMutableTreeNode("Departures");
		DefaultMutableTreeNode nodeArrivals = new DefaultMutableTreeNode("Arrivals");
		DefaultMutableTreeNode nodeClearSchedule = new DefaultMutableTreeNode("Clear Schedule");
		nodeSchedule.add(nodeDepartures);
		nodeSchedule.add(nodeArrivals);
		nodeSchedule.add(nodeClearSchedule);
		//===========================END TREE NODE OF SCHEDULE ====================/
		
		//**Add to root	
		//Create Tree Root Node
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Root Node");
		//Add node to root	
		rootNode.add(nodeAirline);
		rootNode.add(nodeFlight);
		rootNode.add(nodeSchedule);
		//==========================END ADD TO ROOT NODE ============================/
		
		//Create Object of JTree 
		jTree = new JTree(rootNode);
		jTree.setRowHeight(20);
		jTree.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		jTree.setRootVisible(false);
		//==========================END CREATE OBJECT JTREE==========================/
		
		//Expand all tree nodes
		for(int i=0; i<=jTree.getRowCount(); i++)
			jTree.expandRow(i); //Expand root node
		//Add Mouse Listener To Tree
		jTree.addMouseListener(new MouseAdapter() { //Double click event		
			public void mousePressed(MouseEvent e) {
				//Find Selected Node of Trees
				int selectedNode = jTree.getRowForLocation(e.getX(), e.getY());//It's mean Have we selected on any tree node?
				// Condition when mouse pressed on a specific node
				if(selectedNode != -1) { //If select == -1 means it didn't select on any Node
										//If select != -1 means it selected on any node 
					// When mouse pressed is double clicked
					if(e.getClickCount() == 2) {
						//Get for tree path
						TreePath treePath = jTree.getPathForLocation(e.getX(), e.getY());
											//It's result [Root Node, Airline, Add Airline] When We Code System.out.println(treePath);
						//Get last selected tree path
						String lastSelectedPath = treePath.getLastPathComponent().toString();
											//We use lastSelectedPath to get Last element of Array treePath
						if(lastSelectedPath.equals("Add Airline")) {
							performOpenAddAirline();
						}
						else if(lastSelectedPath.equals("Show Airline")) {
							
						}
						else if(lastSelectedPath.equals("Add Flight")) {
							
						}
						else if(lastSelectedPath.equals("Update Flight Status")) {
							
						}
						else if(lastSelectedPath.equals("Show Flight Information")) {
							
						}
						else if(lastSelectedPath.equals("Departures")) {
							
						}
						else if(lastSelectedPath.equals("Arrivals")) {
							
						}
						else if(lastSelectedPath.equals("Clear Schedule")) {
							
						}
					}
				}
			}
		});
		//=======================END ADD MOUSELISTENER EVENT=====================/
		return jTree;
	}
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == mItemExit) {
			dispose();
		}
		else if(e.getSource() == mItemAddAirline) {
			performOpenAddAirline();			
		}
		else if(e.getSource() == mItemShowAirline) {
					
		}
		else if(e.getSource() == mItemAddFlight) {
			
		}
		else if(e.getSource() == mItemUpdateFlightStatus) {
					
		}
		else if(e.getSource() == mItemShowFlight) {
			
		}
		else if(e.getSource() == mItemDepartures) {
			
		}
		else if(e.getSource() == mItemArrivals) {
			
		}
		else if(e.getSource() == mItemClearSchedule) {
					
		}
		else if(e.getSource() == mItemWelcome) {
			performOpenWelcome(true);			
		}
		else if(e.getSource() == mItemHelpContents) {
			
		}
		else if(e.getSource() == mItemCheckUpdate) {
			
		}
		else if(e.getSource() == mItemAbout) {
			
		}
	}
	private void performOpenWelcome(Boolean welcome) {
		JLabel lblPhoto = new JLabel("", SwingConstants.CENTER);
		ImageIcon icon = new ImageIcon("images/Airline-Logo.PNG");
		lblPhoto.setIcon(icon);
		
		JPanel panelPhoto = new JPanel(new BorderLayout(10,10));
		panelPhoto.add(lblPhoto, BorderLayout.CENTER);
		
		if(welcome) {
			jTab.addTab("Welcome", panelPhoto);
		}
		else {
			jTab.addTab("", panelPhoto);
		}
		jTab.setSelectedComponent(panelPhoto);
	}
	private void performOpenAddAirline() {
		JPanel addAirlinePanel = new JPanel();//new BorderLayout(10, 10));
		// Create Group Box - Add Airline
		TitledBorder tBorderNewEmp = BorderFactory.createTitledBorder("ADD AIRLINE");
		tBorderNewEmp.setTitleJustification(TitledBorder.CENTER);
		addAirlinePanel.setBorder(tBorderNewEmp);
		
		//Block AddAirline - Airline Info
		JPanel airlineInfo = new JPanel(new GridLayout(6, 2, 10, 10));
		airlineInfo.add(new Label("Airline Name"));
		airlineInfo.add(txtAirlineName = new JTextField());
		airlineInfo.add(new Label("Airline Code"));
		airlineInfo.add(txtAirlineCode = new JTextField());
		airlineInfo.add(new Label("Aircraft model"));
		airlineInfo.add(txtAircraftModel = new JTextField());
		airlineInfo.add(new Label("First Class Capacity"));
		airlineInfo.add(txtFirstClass = new JTextField());
		airlineInfo.add(new Label("Business Class Capacity"));
		airlineInfo.add(txtBusinessClass = new JTextField());
		airlineInfo.add(new Label("Economy Class Capacity"));
		airlineInfo.add(txtEconomyClass = new JTextField());
		//Create Block AddAirline - Airline Info - Final
		JPanel airlineInfo_Final = new JPanel(new BorderLayout(10,10));
		airlineInfo_Final.add(new JLabel("Add Airline Information"), BorderLayout.NORTH);
		airlineInfo_Final.add(new JSeparator(), BorderLayout.CENTER);
		airlineInfo_Final.add(airlineInfo,BorderLayout.SOUTH);
		//====================END BLOCK ADDAIRLINE INFO==============================/
		//Create AddAirline - Action Button with Action Event
		JPanel airlineButtonPanel = new JPanel(new FlowLayout());
		airlineButtonPanel.add(btnSave = new JButton("Save"));
		airlineButtonPanel.add(btnClear = new JButton("Clear"));
		btnSave.addActionListener(this);
		btnClear.addActionListener(this);
		//Create AddAirline - Action Button - Final
		JPanel airlineButtonPanel_Final = new JPanel(new BorderLayout(10, 10));
		airlineButtonPanel_Final.add(airlineInfo_Final, BorderLayout.NORTH);
		airlineButtonPanel_Final.add(new JSeparator(), BorderLayout.CENTER);
		airlineButtonPanel_Final.add(airlineButtonPanel, BorderLayout.SOUTH);
		//====================END BLOCK ADDAIRLINE ACTION BUTTON==============================/
		//Create Block AddAirline Result
		JPanel airlineResultPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		airlineResultPanel.add(lblResult = new JLabel("Fill Information, Please!"));
		//Create Block AddAirline Result - Final
		JPanel airlineResultPanel_Final = new JPanel(new BorderLayout(10, 10));
		airlineResultPanel_Final.add(airlineButtonPanel_Final, BorderLayout.NORTH);
		airlineResultPanel_Final.add(airlineResultPanel, BorderLayout.CENTER);
		//====================END BLOCK ADDAIRLINE RESULT==============================/
		
		addAirlinePanel.add(airlineResultPanel_Final);
		
		jTab.addTab("Add Airline", addAirlinePanel);
		jTab.setSelectedComponent(addAirlinePanel);
	}

	public static void main(String[] args) {
		//Call MainMenu
		MainMenu main = new MainMenu();
	}	
}
