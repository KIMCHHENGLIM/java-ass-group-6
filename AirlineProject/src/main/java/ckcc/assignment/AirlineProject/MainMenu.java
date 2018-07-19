package ckcc.assignment.AirlineProject;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.DayOfWeek;
import java.util.*;
import java.util.List;
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
	private JButton btnAirlineSave, btnAirlineClear;
	private JLabel lblResult;
	//List Airline
	private JTable tbAirline;
	private DefaultTableModel tbModelAirline;
	//Add Flight
	private JComboBox cboAirlineCode;	
	private JComboBox cboDepartureDay, cboDepartureHours, cboDepartureMinutes;
	private JComboBox cboArrivalDay, cboArrivalHours, cboArrivalMinutes;
	private JTextField txtFlightNumber, txtDepartureAirCode, txtDepartureGate;
	private JTextField txtArrivalAirCode, txtArrivalGate;
	private JRadioButton rdoTypeD, rdoTypeI;
	private JButton btnFlightSave, btnFlightClear;
	private JLabel lblFlightResult;
	//List Flight
	private JTable tbShowListFlight;
	private DefaultTableModel tbModelShowListFlight;
	private JComboBox cboSearchFlightAirline;
	private JTextField txtSearchFlightNumber;
	private JButton btnListFlightSearch;
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
		mItemShowAirline.addActionListener(this);
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
		mItemAddFlight.addActionListener(this);
		mItemShowFlight.addActionListener(this);
		mItemUpdateFlightStatus.addActionListener(this);
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
							performOpenListAirline();
						}
						else if(lastSelectedPath.equals("Add Flight")) {
							performOpenAddFlight();
						}
						else if(lastSelectedPath.equals("Update Flight Status")) {
							
						}
						else if(lastSelectedPath.equals("Show Flight Information")) {
							performOpenListFlight();
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
			performOpenListAirline();
		}
		else if(e.getSource() == mItemAddFlight) {
			performOpenAddFlight();
		}
		else if(e.getSource() == mItemUpdateFlightStatus) {
					
		}
		else if(e.getSource() == mItemShowFlight) {
			performOpenListFlight();
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
		//**frmAirline
		else if(e.getSource() == btnAirlineSave) {			
			SessionFactory factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Airline.class)
					.buildSessionFactory();
			Session session = factory.getCurrentSession();
			
			Airline airline = new Airline(txtAirlineName.getText(), txtAirlineCode.getText());
			airline.add(new Aircraft(txtAircraftModel.getText(),  
					Integer.parseInt(txtFirstClass.getText()),
					Integer.parseInt(txtBusinessClass.getText()), 
					Integer.parseInt(txtEconomyClass.getText())));
			
			session.beginTransaction();	
			session.save(airline);			
			session.getTransaction().commit();
			
			lblResult.setText("Success");
		}
		else if(e.getSource() == btnAirlineClear) {
			txtAirlineName.setText("");
			txtAirlineCode.setText("");
			txtAircraftModel.setText("");
			txtFirstClass.setText("");
			txtBusinessClass.setText("");
			txtEconomyClass.setText("");
			txtAirlineName.requestFocus();
		}
		//=====================END AIRLINE BUTTON=====================/
		//**frmFlight
		else if(e.getSource() == btnFlightSave) {	
			//**Get String
			String airlineCode = cboAirlineCode.getSelectedItem() + "";
			String flightCode = airlineCode + txtFlightNumber.getText();
			int flightNumber = Integer.parseInt(txtFlightNumber.getText());
			char flightStatus = 'S';
			boolean isDomestic = rdoTypeD.isSelected();
			char flightType = isDomestic ? 'D':'I';
			//Departure String
			char departureDayOfWeek = ' ';
			switch(cboDepartureDay.getSelectedIndex()) {
				case 0:
					departureDayOfWeek = 'U';
				break;
				case 1:
					departureDayOfWeek = 'M';
				break;
				case 2:
					departureDayOfWeek = 'T';
				break;
				case 3:
					departureDayOfWeek = 'W';
				break;
				case 4:
					departureDayOfWeek = 'R';
				break;
				case 5:
					departureDayOfWeek = 'F';
				break;
				case 6:
					departureDayOfWeek = 'S';
				break;					
			}			
			String departureStTime = cboDepartureHours.getSelectedItem().toString() 
							+ cboDepartureMinutes.getSelectedItem().toString();
			int departureTime = Integer.parseInt(departureStTime);
			String departureAirportCode = txtDepartureAirCode.getText();
			String departureAirportGate = txtDepartureGate.getText();
			//Arrival String
			char arrivalDayOfWeek = ' ';
			switch(cboArrivalDay.getSelectedIndex()) {
				case 0:
					arrivalDayOfWeek = 'U';
				break;
				case 1:
					arrivalDayOfWeek = 'M';
				break;
				case 2:
					arrivalDayOfWeek = 'T';
				break;
				case 3:
					arrivalDayOfWeek = 'W';
				break;
				case 4:
					arrivalDayOfWeek = 'R';
				break;
				case 5:
					arrivalDayOfWeek = 'F';
				break;
				case 6:
					arrivalDayOfWeek = 'S';
				break;					
			}			
			String arrivalStTime = cboArrivalHours.getSelectedItem().toString() 
							+ cboArrivalMinutes.getSelectedItem().toString();
			int arrivalTime = Integer.parseInt(arrivalStTime);
			String arrivalAirportCode = txtArrivalAirCode.getText();
			String arrivalAirportGate = txtArrivalGate.getText();		
			//===================END GETTING STRING==========================/
			//Add Data Into Database
			SessionFactory factory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Flight.class)
					.buildSessionFactory();
			Session session = factory.getCurrentSession();
			DepartureInfo departure = new DepartureInfo(departureDayOfWeek, departureTime, 
												departureAirportCode, departureAirportGate);
			ArrivalInfo arrival = new ArrivalInfo(arrivalDayOfWeek, arrivalTime, 
					arrivalAirportCode, arrivalAirportGate);
			Flight flight = new Flight(flightCode, airlineCode, flightNumber, flightStatus, 
					flightType, departure, arrival);
			session.beginTransaction();	
			session.save(flight);
			session.getTransaction().commit();
			
			lblFlightResult.setText("Success");
		}
		//==================END FLIGHT SAVE BUTTON===================/
		//**frmListFlight
		else if(e.getSource() == btnListFlightSearch) {
			String flightCode = cboSearchFlightAirline.getSelectedItem().toString()
							+ txtSearchFlightNumber.getText();
			//Get Data From Database and add into Table
			SessionFactory factoryFlight = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Flight.class)
					.buildSessionFactory();
			Session session = factoryFlight.getCurrentSession();	
			session.beginTransaction();	
			List<Flight> listFlights = session.createQuery("from Flight f where f.flightCode = '" 
							+ flightCode + "'").getResultList();
			//**Clear Table
			int rowCount = tbModelShowListFlight.getRowCount();
			//Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) {
				tbModelShowListFlight.removeRow(i);
			}
			for(int i=0; i<listFlights.size(); i++) {
				//==================================================
				//Get Airline Information
				SessionFactory airlineFactory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Airline.class)
						.buildSessionFactory();
				Session sessionAirline = airlineFactory.getCurrentSession();
				sessionAirline.beginTransaction();
				Airline airline = sessionAirline.get(Airline.class, listFlights.get(i).getAirlineCode());
				sessionAirline.getTransaction().commit();
				//===================================================
				String[] getrow = { 
						listFlights.get(i).getFlightCode(),
						listFlights.get(i).getFlightNumber() + "",		
						listFlights.get(i).getStatus() + "",
						listFlights.get(i).getType() + "",
						
						airline.getAircraft().getModel(),
						airline.getAircraft().getFirstClassCapacity() + "",
						airline.getAircraft().getBusinessClassCapacity() + "",
						airline.getAircraft().getEconomyClassCapacity() + "",
						
						listFlights.get(i).getDepartureInfo().getDayOfWeek() + "",
						listFlights.get(i).getDepartureInfo().getTime() + "",
						listFlights.get(i).getDepartureInfo().getAirportCode() + "",
						listFlights.get(i).getDepartureInfo().getAirportGate() + "",
						
						listFlights.get(i).getArrivalInfo().getDayOfWeek() + "",
						listFlights.get(i).getArrivalInfo().getTime() + "",
						listFlights.get(i).getArrivalInfo().getAirportCode() + "",
						listFlights.get(i).getArrivalInfo().getAirportGate() + "",
					};
				tbModelShowListFlight.addRow(getrow);
			}		
			session.getTransaction().commit();
			//====================END TABLE LIST FLIGHT============================/
			
		}
	}
	private void performOpenListFlight() {
		JPanel listFlightPanel = new JPanel(new BorderLayout(10, 10));
		// Create Group Box - Add Airline
		TitledBorder tBorderListFlight = BorderFactory.createTitledBorder("SHOW LIST OF FLIGHT");
		tBorderListFlight.setTitleJustification(TitledBorder.CENTER);
		listFlightPanel.setBorder(tBorderListFlight);

		// Create Search Area and Count Flight
		JPanel searchAndCountPanel = new JPanel(new GridLayout(1,2,10,10));
		// Add Search Area Panel
		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.add(new JLabel("Airline"));	
		searchPanel.add(cboSearchFlightAirline = new JComboBox());
		//====================================================================
		//Get Data From Database and add into Table
		SessionFactory factoryAirline = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Airline.class)
				.buildSessionFactory();
		Session sessionAir = factoryAirline.getCurrentSession();	
		sessionAir.beginTransaction();	
		List<String> listAirlines = sessionAir.createQuery("select code from Airline").getResultList();
		for(int i=0; i<listAirlines.size(); i++) {
			String item = listAirlines.get(i);
			cboSearchFlightAirline.addItem(item);
		}
		sessionAir.getTransaction().commit();
		//====================================================================
		searchPanel.add(new JLabel("Flight Number"));
		searchPanel.add(txtSearchFlightNumber = new JTextField(12));
		searchPanel.add(btnListFlightSearch = new JButton("Search"));
		btnListFlightSearch.addActionListener(this);
		// Add Search Area
		searchAndCountPanel.add(searchPanel);
		searchAndCountPanel.add(new Label(""));
		//===================END BLOCK SEARCH============================/
		
		//Table Flight
		tbModelShowListFlight = new DefaultTableModel();
		tbModelShowListFlight.addColumn("Flight Code");
		tbModelShowListFlight.addColumn("Flight Number");
		tbModelShowListFlight.addColumn("Flight Status");
		tbModelShowListFlight.addColumn("Flight Type");
		tbModelShowListFlight.addColumn("Aircraft Model");
		tbModelShowListFlight.addColumn("First Class");
		tbModelShowListFlight.addColumn("Business Class");
		tbModelShowListFlight.addColumn("Economy Class");
		tbModelShowListFlight.addColumn("Departure DayOfWeek");
		tbModelShowListFlight.addColumn("Departure Time");
		tbModelShowListFlight.addColumn("Departure AirportCode");
		tbModelShowListFlight.addColumn("Departure AirportGate");
		tbModelShowListFlight.addColumn("Arrival DayOfWeek");
		tbModelShowListFlight.addColumn("Arrival Time");
		tbModelShowListFlight.addColumn("Arrival AirportCode");
		tbModelShowListFlight.addColumn("Arrival AirportGate");
		tbShowListFlight = new JTable(tbModelShowListFlight);
		
		//Get Data From Database and add into Table
		SessionFactory factoryFlight = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Flight.class)
				.buildSessionFactory();
		Session session = factoryFlight.getCurrentSession();	
		session.beginTransaction();	
		List<Flight> listFlights = session.createQuery("from Flight").getResultList();
		for(int i=0; i<listFlights.size(); i++) {
			//==================================================
			//Get Airline Information
			SessionFactory airlineFactory = new Configuration()
					.configure("hibernate.cfg.xml")
					.addAnnotatedClass(Airline.class)
					.buildSessionFactory();
			Session sessionAirline = airlineFactory.getCurrentSession();
			sessionAirline.beginTransaction();
			Airline airline = sessionAirline.get(Airline.class, listFlights.get(i).getAirlineCode());
			sessionAirline.getTransaction().commit();
			//===================================================
			String[] getrow = { 
					listFlights.get(i).getFlightCode(),
					listFlights.get(i).getFlightNumber() + "",		
					listFlights.get(i).getStatus() + "",
					listFlights.get(i).getType() + "",
					
					airline.getAircraft().getModel(),
					airline.getAircraft().getFirstClassCapacity() + "",
					airline.getAircraft().getBusinessClassCapacity() + "",
					airline.getAircraft().getEconomyClassCapacity() + "",
					
					listFlights.get(i).getDepartureInfo().getDayOfWeek() + "",
					listFlights.get(i).getDepartureInfo().getTime() + "",
					listFlights.get(i).getDepartureInfo().getAirportCode() + "",
					listFlights.get(i).getDepartureInfo().getAirportGate() + "",
					
					listFlights.get(i).getArrivalInfo().getDayOfWeek() + "",
					listFlights.get(i).getArrivalInfo().getTime() + "",
					listFlights.get(i).getArrivalInfo().getAirportCode() + "",
					listFlights.get(i).getArrivalInfo().getAirportGate() + "",
				};
			tbModelShowListFlight.addRow(getrow);
		}		
		session.getTransaction().commit();
		//====================END TABLE FLIGHT============================/
		
		//Create Block List Flight
		JPanel blockListFlight = new JPanel(new BorderLayout(10, 10));
		blockListFlight.add(new JLabel("List Flight's Information"), BorderLayout.NORTH);
		blockListFlight.add(new JSeparator(), BorderLayout.CENTER);
		blockListFlight.add(searchAndCountPanel, BorderLayout.NORTH);
		//Create Block List Airline_Final with Add Table
		JPanel blockListFlight_Final = new JPanel(new BorderLayout(10,10));
		blockListFlight_Final.add(blockListFlight, BorderLayout.NORTH);
		blockListFlight_Final.add(new JScrollPane(tbShowListFlight), BorderLayout.CENTER);
		//===================END CREATE BLOCK LIST AIRLINE==================/
		
		listFlightPanel.add(blockListFlight_Final);
	
		 
		
		jTab.addTab("List Flight", listFlightPanel);
		jTab.setSelectedComponent(listFlightPanel);
	}

	private void performOpenAddFlight() {
		JPanel addFlightPanel = new JPanel();//new BorderLayout(10, 10));
		// Create Group Box - Add Airline
		TitledBorder tBorderAddFlight = BorderFactory.createTitledBorder("ADD NEW FLIGHT");
		tBorderAddFlight.setTitleJustification(TitledBorder.CENTER);
		addFlightPanel.setBorder(tBorderAddFlight);
		
		//Block AddFlight - Flight Info
		JPanel flightInfo = new JPanel(new GridLayout(3, 2, 10, 10));
		flightInfo.add(new JLabel("Airline Code"));		
		flightInfo.add(cboAirlineCode = new JComboBox());
		//====================================================================
		//Get Data From Database and add into Table
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Airline.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();	
		session.beginTransaction();	
		List<String> listAirlines = session.createQuery("select code from Airline").getResultList();
		for(int i=0; i<listAirlines.size(); i++) {
			String item = listAirlines.get(i);
			cboAirlineCode.addItem(item);
		}
		session.getTransaction().commit();
		//====================================================================
		flightInfo.add(new JLabel("Flight Number"));
		flightInfo.add(txtFlightNumber = new JTextField());
		flightInfo.add(new JLabel("Type(D for domestic or I for international)"));
		//==================================================================
			JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			typePanel.add(rdoTypeD = new JRadioButton("Domestic", true));
			typePanel.add(rdoTypeI = new JRadioButton("International"));
			ButtonGroup bgType = new ButtonGroup();
			bgType.add(rdoTypeD);
			bgType.add(rdoTypeI);			
		//==================================================================
		flightInfo.add(typePanel);
		//Create Block AddFlight - Flight Info - Final
		JPanel flightInfo_Final = new JPanel(new BorderLayout(10,10));
		flightInfo_Final.add(new JLabel("Flight's Information"), BorderLayout.NORTH);
		flightInfo_Final.add(new JSeparator(), BorderLayout.CENTER);
		flightInfo_Final.add(flightInfo, BorderLayout.SOUTH);
		//====================END BLOCK FLIGHT INFO==============================/
		
		//Block AddFlight - Departure Info
		JPanel departureInfo = new JPanel(new GridLayout(4, 2, 10, 10));
		departureInfo.add(new JLabel("Departure airport code"));
		departureInfo.add(txtDepartureAirCode = new JTextField());
		departureInfo.add(new JLabel("Departure airport gate"));
		departureInfo.add(txtDepartureGate = new JTextField());
		departureInfo.add(new JLabel("Departure day"));
		String[] departureDays = {"Sunday", "Monday", "Tuesday", "Wednesday", 
						"Thursday", "Friday", "Saturday"};			
		departureInfo.add(cboDepartureDay = new JComboBox(departureDays));
		departureInfo.add(new JLabel("Departure Time"));
		//==================================================================
			JPanel departureTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			String[] departureHours = new String[24];
			for(int i=0; i<24; i++)
				departureHours[i] = i < 10 ? "0" + i : i + "";
			String[] departureMinutes = new String[60];
			for(int i=0; i<60; i++)
				departureMinutes[i] = i < 10 ? "0" + i : i + "";
			departureTimePanel.add(cboDepartureHours = new JComboBox(departureHours));
			departureTimePanel.add(cboDepartureMinutes = new JComboBox(departureMinutes));
		//==================================================================
		departureInfo.add(departureTimePanel);
		//Create Block AddFlight - Departure Info With Header
		JPanel departureInfoHeader = new JPanel(new BorderLayout(10,10));
		departureInfoHeader.add(new JLabel("Departure's Information"), BorderLayout.NORTH);
		departureInfoHeader.add(new JSeparator(), BorderLayout.CENTER);
		departureInfoHeader.add(departureInfo, BorderLayout.SOUTH);
		//Create Block AddFlight - Departure Info - Final
		JPanel departureInfo_Final = new JPanel(new BorderLayout(10,10));
		departureInfo_Final.add(flightInfo_Final, BorderLayout.NORTH);
		departureInfo_Final.add(departureInfoHeader, BorderLayout.CENTER);
		//====================END BLOCK DEPARTURE INFO==============================/
		
		//Block AddFlight - Arrival Info
		JPanel arrivalInfo = new JPanel(new GridLayout(4, 2, 10, 10));
		arrivalInfo.add(new JLabel("Arrival airport code"));
		arrivalInfo.add(txtArrivalAirCode = new JTextField());
		arrivalInfo.add(new JLabel("Arrival airport gate"));
		arrivalInfo.add(txtArrivalGate = new JTextField());
		arrivalInfo.add(new JLabel("Arrival day"));
		String[] arrivalDays = {"Sunday", "Monday", "Tuesday", "Wednesday", 
						"Thursday", "Friday", "Saturday"};			
		arrivalInfo.add(cboArrivalDay = new JComboBox(arrivalDays));
		arrivalInfo.add(new JLabel("Arrival Time"));
		//==================================================================
			JPanel arrivalTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			String[] arrivalHours = new String[24];
			for(int i=0; i<24; i++)
				arrivalHours[i] = i < 10 ? "0" + i : i + "";
			String[] arrivalMinutes = new String[60];
			for(int i=0; i<60; i++)
				arrivalMinutes[i] = i < 10 ? "0" + i : i + "";
			arrivalTimePanel.add(cboArrivalHours = new JComboBox(arrivalHours));
			arrivalTimePanel.add(cboArrivalMinutes = new JComboBox(arrivalMinutes));
		//==================================================================
		arrivalInfo.add(arrivalTimePanel);
		//Create Block AddFlight - Departure Info With Header
		JPanel arrivalInfoHeader = new JPanel(new BorderLayout(10,10));
		arrivalInfoHeader.add(new JLabel("Arrival's Information"), BorderLayout.NORTH);
		arrivalInfoHeader.add(new JSeparator(), BorderLayout.CENTER);
		arrivalInfoHeader.add(arrivalInfo, BorderLayout.SOUTH);
		//Create Block AddFlight - Arrival Info - Final
		JPanel arrivalInfo_Final = new JPanel(new BorderLayout(10,10));
		arrivalInfo_Final.add(departureInfo_Final, BorderLayout.NORTH);
		arrivalInfo_Final.add(arrivalInfoHeader, BorderLayout.CENTER);
		//====================END BLOCK ARRIVAL INFO==============================/
		
		//Block AddFlight - Action Button with Action Event
		JPanel flightButtonPanel = new JPanel(new FlowLayout());
		flightButtonPanel.add(btnFlightSave = new JButton("Save"));
		flightButtonPanel.add(btnFlightClear = new JButton("Clear"));
		btnFlightSave.addActionListener(this);
		btnFlightClear.addActionListener(this);
		//Create AddFlight - Action Button - Final
		JPanel flightButtonPanel_Final = new JPanel(new BorderLayout(10, 10));
		flightButtonPanel_Final.add(arrivalInfo_Final, BorderLayout.NORTH);
		flightButtonPanel_Final.add(new JSeparator(), BorderLayout.CENTER);
		flightButtonPanel_Final.add(flightButtonPanel, BorderLayout.SOUTH);
		//====================END BLOCK ADDFLIGHT ACTION BUTTON==============================/
		
		//Create Block AddFlight Result
		JPanel flightResultPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		flightResultPanel.add(lblFlightResult = new JLabel("Fill Information, Please!"));
		//Create Block AddAirline Result - Final
		JPanel flightResultPanel_Final = new JPanel(new BorderLayout(10, 10));
		flightResultPanel_Final.add(flightButtonPanel_Final, BorderLayout.NORTH);
		flightResultPanel_Final.add(flightResultPanel, BorderLayout.CENTER);
		//====================END BLOCK ADDAIRLINE RESULT==============================/
	
		addFlightPanel.add(flightResultPanel_Final);//flightInfo_Final);
		
		jTab.addTab("Add Flight", addFlightPanel);
		jTab.setSelectedComponent(addFlightPanel);
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
		JPanel addAirlinePanel = new JPanel(); //new BorderLayout(10, 10));
		// Create Group Box - Add Airline
		TitledBorder tBorderAddAirline = BorderFactory.createTitledBorder("ADD AIRLINE");
		tBorderAddAirline.setTitleJustification(TitledBorder.CENTER);
		addAirlinePanel.setBorder(tBorderAddAirline);
		
		//Block AddAirline - Airline Info
		JPanel airlineInfo = new JPanel(new GridLayout(6, 2, 10, 10));
		airlineInfo.add(new JLabel("Airline Name"));
		airlineInfo.add(txtAirlineName = new JTextField());
		airlineInfo.add(new JLabel("Airline Code"));
		airlineInfo.add(txtAirlineCode = new JTextField());
		airlineInfo.add(new JLabel("Aircraft model"));
		airlineInfo.add(txtAircraftModel = new JTextField());
		airlineInfo.add(new JLabel("First Class Capacity"));
		airlineInfo.add(txtFirstClass = new JTextField());
		airlineInfo.add(new JLabel("Business Class Capacity"));
		airlineInfo.add(txtBusinessClass = new JTextField());
		airlineInfo.add(new JLabel("Economy Class Capacity"));
		airlineInfo.add(txtEconomyClass = new JTextField());
		//Create Block AddAirline - Airline Info - Final
		JPanel airlineInfo_Final = new JPanel(new BorderLayout(10,10));
		airlineInfo_Final.add(new JLabel("Add Airline Information"), BorderLayout.NORTH);
		airlineInfo_Final.add(new JSeparator(), BorderLayout.CENTER);
		airlineInfo_Final.add(airlineInfo,BorderLayout.SOUTH);
		//====================END BLOCK ADDAIRLINE INFO==============================/
		//Create AddAirline - Action Button with Action Event
		JPanel airlineButtonPanel = new JPanel(new FlowLayout());
		airlineButtonPanel.add(btnAirlineSave = new JButton("Save"));
		airlineButtonPanel.add(btnAirlineClear = new JButton("Clear"));
		btnAirlineSave.addActionListener(this);
		btnAirlineClear.addActionListener(this);
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
		/*
		JPanel panel = new JPanel();
		panel.setBorder(tBorderAddAirline);
		panel.add(airlineButtonPanel_Final);
		*/
		
		addAirlinePanel.add(airlineResultPanel_Final);
		jTab.addTab("Add Airline", addAirlinePanel);
		jTab.setSelectedComponent(addAirlinePanel);
	}
	private void performOpenListAirline() {
		JPanel listAirlinePanel = new JPanel(new BorderLayout(10, 10));
		// Create Group Box - Add Airline
		TitledBorder tBorderListAirline = BorderFactory.createTitledBorder("LIST OF AIRLINE");
		tBorderListAirline.setTitleJustification(TitledBorder.CENTER);
		listAirlinePanel.setBorder(tBorderListAirline);
		
		//Table Airline
		tbModelAirline = new DefaultTableModel();
		tbModelAirline.addColumn("Airline Name");
		tbModelAirline.addColumn("Airline Code");
		tbModelAirline.addColumn("Aircraft Model");
		tbModelAirline.addColumn("First Class Capacity");
		tbModelAirline.addColumn("Business Class Capacity");
		tbModelAirline.addColumn("Economy Class Capacity");
		tbAirline = new JTable(tbModelAirline);
		//Get Data From Database and add into Table
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Airline.class)
				.buildSessionFactory();
		Session session = factory.getCurrentSession();	
		session.beginTransaction();	
		List<Airline> listAirlines = session.createQuery("from Airline").getResultList();
		for(int i=0; i<listAirlines.size(); i++) {
			String[] getrow = { listAirlines.get(i).getName(),
					listAirlines.get(i).getCode(),
					listAirlines.get(i).getAircraft().getModel(),
					listAirlines.get(i).getAircraft().getFirstClassCapacity() + "",
					listAirlines.get(i).getAircraft().getBusinessClassCapacity() + "",
					listAirlines.get(i).getAircraft().getEconomyClassCapacity() + ""
				};
			tbModelAirline.addRow(getrow);
		}
		session.getTransaction().commit();
		//====================END TABLE AIRLINE============================/
		
		//Create Block List Airline
		JPanel blockListAirline = new JPanel(new BorderLayout(10, 10));
		blockListAirline.add(new JLabel("List Airline"), BorderLayout.NORTH);
		blockListAirline.add(new JSeparator(), BorderLayout.CENTER);
		//Create Block List Airline_Final with Add Table
		JPanel blockListAirline_Final = new JPanel(new BorderLayout(10,10));
		blockListAirline_Final.add(blockListAirline, BorderLayout.NORTH);
		blockListAirline_Final.add(new JScrollPane(tbAirline), BorderLayout.CENTER);
		//===================END CREATE BLOCK LIST AIRLINE==================/
		
		listAirlinePanel.add(blockListAirline_Final);
	
		jTab.addTab("List Airline", listAirlinePanel);
		jTab.setSelectedComponent(listAirlinePanel);		
	}
	public static void main(String[] args) {
		//Call MainMenu
		MainMenu main = new MainMenu();
	}	
}
