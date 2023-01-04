package com.wipro.swthealthcare.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.wipro.swthealthcare.SWTHttpRestClient;
import com.wipro.swthealthcare.model.Patient;


/**
 * PatientOperationView class for doing modify delte and view patient details 
 * Having a patient list  form and patient locator to search a patient  by id 
 * will be listing the patient data and select the patient to specific operations on patient
 * @author  Radha 
 * @version 1.0
 * @since   1/1/2023
 */
public class PatientOperationView  {


	public Shell shell;
	public Table table ;
	public Display parentDisplay;
	public List<Patient> patientList = new ArrayList<>();
	private PatientEntryPage view;
	public PatientEntryPage getView() {
		return view;
	}


	/* method to set 1st page object for reuse */
	public void setView(PatientEntryPage view) {
		this.view = view;
	}


	private static final String[] SEARCH_CRITERIA = { "Patient id"};

	public List<Patient> getPatientList() {
		return patientList;
	}



	public void setPatientList(List<Patient> patientList) {
		this.patientList = patientList;
	}



	public PatientOperationView(Display display) {
		this.parentDisplay = display;
		System.out.println(this.parentDisplay);
	}


	/* method to show all patient details once created */
	public void showPatientDetails(boolean showallList) {
		shell = new Shell(parentDisplay);
		showAllPatientListAfterCreation(showallList);
		shell.open();
		shell.layout();
		while (!shell.isDisposed ()) {
			if (!parentDisplay.readAndDispatch ()) parentDisplay.sleep ();
		}
	}


	/* method to create load values setting in form during listing */
	public void showAllPatientListAfterCreation(boolean showallList) {
		try {
			if(showallList) {
				List<Patient> patientList  = SWTHttpRestClient.fecthAllPatients();
				this.setPatientList(patientList);
			}

			createPageView(parentDisplay, showallList);

		}catch(IOException | InterruptedException e) {
			System.out.println(e.getMessage());
			showDailogeBox(shell, "Error while listing all patient");

		}
	}
	/* method to show dialog box for error*/
	public void showDailogeBox(Shell shellCreate, String errorMessage) {
		MessageBox messageBox = new MessageBox(shellCreate, SWT.ICON_ERROR);
		messageBox.setText("Error");
		messageBox.setMessage(errorMessage);
		int buttonID = messageBox.open();
		switch(buttonID) {
		case SWT.YES:
		case SWT.NO:
			break;
		case SWT.CANCEL:
		}
	}

	/* method to create  form feilds */
	private void createPageView(Display display,boolean showallList) {


		Label label = new Label(shell, SWT.CENTER);
		Label labelText = new Label(shell, SWT.BORDER);
		Text text = new Text(shell, SWT.BORDER);
		text.setTextLimit(10);
		Button viewButton  = new Button(shell, SWT.NONE);
		Button modifyButton  = new Button(shell, SWT.NONE);
		Button deleteButton  = new Button(shell, SWT.NONE);
		Font font = new Font(parentDisplay, "Cambria", 22, SWT.ITALIC);
		label.setBounds(500, 5, 700, 31);

		label.setFont(font);
		label.setText("Cerner Patients List");
		Color color = new Color(display, 0, 114, 135);
		label.setForeground(color);
		label.pack();


		labelText.setText("Patient Details  ");
		labelText.setBackground(display.getSystemColor(SWT.COLOR_TITLE_BACKGROUND));
		labelText.setBounds(30, 70, 1500, 31);
		labelText.pack();

		text.setBounds(180, 100, 380, 31);




		viewButton.setBounds(400, 500, 150, 31);
		viewButton.setText("View");
		viewButton.setEnabled(false);


		modifyButton.setBounds(600, 500, 150, 31);
		modifyButton.setText("Modify");
		modifyButton.setEnabled(false);


		deleteButton.setBounds(800, 500, 150, 31);
		deleteButton.setText("Delete");
		deleteButton.setEnabled(false);



		Combo combo = new Combo(shell, SWT.DROP_DOWN);
		combo.setItems(SEARCH_CRITERIA);
		combo.setBounds(30, 100, 120, 31);
		combo.setText("Patient id");

		Button searchButton  = new Button(shell, SWT.NONE);
		searchButton.setBounds(600, 100, 150, 31);
		searchButton.setText("Search");
		Color green = display.getSystemColor(SWT.COLOR_CYAN);
		searchButton.setBackground(green);

		Button showAllButton  = new Button(shell, SWT.NONE);
		showAllButton.setBounds(900, 100, 150, 31);
		showAllButton.setText("List Patient");
		showAllButton.setBackground(green);



		boolean isRemoveAllRows = table != null &&  table.getItemCount() > 0 ? true : false;
		System.out.println("isRemoveAllRows"+isRemoveAllRows);

		if(showallList) {
			createTable(display, label, font, viewButton, modifyButton, deleteButton, getPatientList(),isRemoveAllRows);
		}



		showAllButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				List<Patient> patientList;
				try {
					patientList = SWTHttpRestClient.fecthAllPatients();
					setPatientList(patientList);
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
				boolean isRemoveAllRowsForall = table != null && table.getItemCount() > 0 ? true : false;
				System.out.println("INSIDE REMOVE ALL");
				createTable(display, label, font, viewButton, modifyButton, deleteButton, getPatientList(),isRemoveAllRowsForall);

			}
		});
		searchButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println(combo.getText());
				boolean isRemoveAllRows1 = table != null &&  table.getItemCount() > 0 ? true : false;
				boolean isValidPatientId= true;
				if("Patient id".equalsIgnoreCase(combo.getText())) {


					String patientId = text.getText();
					if(StringUtils.isEmpty(patientId)) {
						isValidPatientId=false;
					}else if(!view.validateNumbers(patientId)) {
						isValidPatientId=false;
					}
					if(isValidPatientId) {
						try {
							Patient patient = SWTHttpRestClient.fetchPatientById(Long.valueOf(text.getText()));
							List<Patient> patientList = new ArrayList<>();
							patientList.add(patient);
							createTable(display, label, font, viewButton, modifyButton, deleteButton, patientList,isRemoveAllRows1);
							System.out.println("patient alone to display::"+patient);
						} catch (IOException | InterruptedException e) { 
							System.out.println("exception");

							MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
							messageBox.setText("Info");
							messageBox.setMessage("No patient Details found");
							int buttonID = messageBox.open();
							switch(buttonID) {
							case SWT.YES:
								// saves changes ...
							case SWT.NO:
								// exits here ...
								break;
							case SWT.CANCEL:
								// does nothing ...
							}
							System.out.println(buttonID);

						}
					}else {
						MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);

						messageBox.setText("error");
						messageBox.setMessage("Please enter valid patient id");
						int buttonID = messageBox.open();
						switch(buttonID) {
						case SWT.YES:
							// saves changes ...
						case SWT.NO:
							// exits here ...
							break;
						case SWT.CANCEL:
							// does nothing ...
						}
						System.out.println(buttonID);
					}


				}



			}

		});


		deleteButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Inside delete method");
				try {
					TableItem[] item = table.getSelection();
					for(TableItem itemTable: item) {
						System.out.println("itemTable"+itemTable.getText(0));
						SWTHttpRestClient.deletePatient(Long.valueOf(itemTable.getText(0)));
						MessageBox messageBox = new MessageBox(shell, SWT.ICON_WORKING);

						messageBox.setText("Success");
						messageBox.setMessage("Patient deleted successfully");
						int buttonID = messageBox.open();
						switch(buttonID) {
						case SWT.YES:
							// saves changes ...
						case SWT.NO:
							// exits here ...
							break;
						case SWT.CANCEL:
							// does nothing ...
						}
						System.out.println(buttonID);
						view.setDataForFeildsForDefaultPage();
						shell.close();
					}
				}catch(IOException | InterruptedException e) {
					System.out.println(e.getMessage());
					MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);

					messageBox.setText("Info");
					messageBox.setMessage("error during deletion");
					int buttonID = messageBox.open();
					switch(buttonID) {
					case SWT.YES:
						// saves changes ...
					case SWT.NO:
						// exits here ...
						break;
					case SWT.CANCEL:
						// does nothing ...
					}
					System.out.println(buttonID);
				}
			}
		});

		viewButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Inside view method");

				try {
					TableItem[] item = table.getSelection();
					Patient patient = null; 
					for(TableItem itemTable: item) {
						System.out.println("itemTable"+itemTable.getText(0));
						patient = SWTHttpRestClient.fetchPatientById(Long.valueOf(itemTable.getText(0)));

					}

					view.setDataForFeildsForMofidy(patient, display,true);
					shell.close();


				}catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

		modifyButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				System.out.println("Inside view method");
				try {
					TableItem[] item = table.getSelection();
					Patient patient = null; 
					for(TableItem itemTable: item) {
						System.out.println("itemTable"+itemTable.getText(0));
						patient = SWTHttpRestClient.fetchPatientById(Long.valueOf(itemTable.getText(0)));

					}

					view.setDataForFeildsForMofidy(patient, display, false);
					shell.close();


				}catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}

		});

	}
	/*create table based on list*/

	public void createTable(Display display, Label label, Font font, Button viewButton, Button modifyButton,
			Button deleteButton, List<Patient> patientList, boolean isremoveAllfromtable) {
		if(isremoveAllfromtable) {
			System.out.println("INSIDE REMOVE ALL");
			table.clearAll();
			table.removeAll();
			TableColumn[] column = table.getColumns();
			for(int i =0;i<=column.length-1; i++) {
				column[i].dispose();
			}
			TableItem[] items = table.getItems();
			for(int i =0;i<=items.length-1; i++) {
				items[i].dispose();
			}
		} else {
			table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		}
		
		String[] titles = { "Patient Id", "Patient Name", "Patient Gender", "Patient Dob", "Patient Address", "Patient PhoneNumber" };
		for (int i = 0; i < titles.length; i++) {
			TableColumn column = new TableColumn(table, SWT.ITALIC);
			column.setWidth(100);
			column.setText(titles[i]);
			table.getColumn(i).pack();
		}
		table.setBounds(80, 200, 2000, 31);
		Font fontTable = new Font(display, "Cambria", 10, SWT.ITALIC);
		label.setFont(font);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		Color colorColumn = new Color(display,100,100, 50);
		table.setFont(fontTable);
		table.setHeaderBackground(colorColumn);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 200;
		table.setLayoutData(data);


		if(patientList != null &&  patientList.size() > 0 ) {

			for (Patient patientDetails : patientList){
				TableItem item = new TableItem(table, SWT.ITALIC);
				item.setText (0, patientDetails.getPatientId().toString());
				item.setText (1, patientDetails.getPatientName());
				item.setText (2, patientDetails.getPatientGender());
				item.setText (3, patientDetails.getPatientDob().toString());

				String addressList = patientDetails.getPatientAddressList().stream().map(x->x.getAddress())
						.collect(Collectors.joining(","));

				String phoneNumberList = patientDetails.getPatientPhList().stream().map(x->x.getPhoneNumber())
						.collect(Collectors.joining(","));
				item.setText (4, addressList);
				item.setText (5, phoneNumberList);
			}
		}


		System.out.println("table Items total : " + table.getItems().length); 

		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				String string = "";
				TableItem[] selection = table.getSelection();
				for (int i = 0; i < selection.length; i++)
					string += selection[i] + " ";
				if(StringUtils.isNotEmpty(string)) {
					viewButton.setEnabled(true);
					modifyButton.setEnabled(true);
					deleteButton.setEnabled(true);
				}
				System.out.println("Selection of table ={" + string + "}");
			}
		});
		table.pack();
	}




}
