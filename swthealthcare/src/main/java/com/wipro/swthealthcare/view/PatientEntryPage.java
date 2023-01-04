package com.wipro.swthealthcare.view;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

import com.wipro.swthealthcare.SWTHttpRestClient;
import com.wipro.swthealthcare.model.Address;
import com.wipro.swthealthcare.model.Patient;
import com.wipro.swthealthcare.model.PhoneNumber;


/**
 * PatientEntryPage class for getting the patient details to save
 * Having a patient details form and patient locator to search a patient 
 * @author  Radha 
 * @version 1.0
 * @since   1/1/2023
 */

public class PatientEntryPage {

	public Shell shell;

	public Table table ;

	public Text nameText ;

	public Button femaleRadio ;


	public Button maleRadio ;

	public DateTime dateTimeText ;

	public Text phoneNumberText ;

	public Text phoneNumberText2 ;

	public Text addressText ;


	public Text addressCityText ;

	public Text addressPicodeText ;

	public Text addressText2 ;

	public Text addressCityText2 ;

	public Text addressPicodeText2 ;

	Shell shellCreate ;

	Button createButton ;

	Button modify ;

	public Patient getAvailablePatient() {
		return availablePatient;
	}


	public void setAvailablePatient(Patient availablePatient) {
		this.availablePatient = availablePatient;
	}


	public static PatientEntryPage view ;
	Patient availablePatient;
	
	/* this is the entry point for the application
	 * it will open a page and open a display */
	public static void main (String [] args) {
		try {
			view = new PatientEntryPage();
			view.open();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/* method to create shell and display */
	public void open() {
		Display display = new Display();	
		shellCreate = new Shell();
		createNewPatient(display, shellCreate);
	}
	/* form  to create new patient  */
	private void createNewPatient(Display display,Shell shellCreate ) {
		shellCreate.setText("WELCOME TO CERNER HEALTH CARE");
		Label label = new Label(shellCreate, SWT.CENTER);
		label.setBounds(500, 5, 700, 31);
		Font font = new Font(display, "Cambria", 22, SWT.ITALIC);
		label.setFont(font);
		label.setText("Patient Entry Form");
		Color color = new Color(display, 0, 114, 135);
		label.setForeground(color);
		createPatientDetailsSection(display, shellCreate);
		label.pack();
		shellCreate.open();
		shellCreate.layout();
		while (!shellCreate.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
	}


	private void createPatientDetailsSection(Display display, Shell shellCreate) {

		Label patientName = new Label(shellCreate, SWT.NONE);
		patientName.setText("NAME*");
		patientName.setBounds(100,80,900,31);
		Color color1 = new Color(display, 150, 0, 0);
		patientName.setForeground(color1);

		Label patientGender = new Label(shellCreate, SWT.NONE);
		patientGender.setText("GENDER*");
		patientGender.setBounds(100,140,900,31);
		patientGender.setForeground(color1);


		Label patientDob = new Label(shellCreate, SWT.NONE);
		patientDob.setText("DATE OF BIRTH*");
		patientDob.setBounds(100,200,900,31);
		patientDob.setForeground(color1);

		Label patientPhoneNumber = new Label(shellCreate, SWT.NONE);
		patientPhoneNumber.setText("PHONE NUMBER*");
		patientPhoneNumber.setBounds(100,260,900,31);
		patientPhoneNumber.setForeground(color1);


		Label patientPhoneNumber2 = new Label(shellCreate, SWT.NONE);
		patientPhoneNumber2.setText("ALTERNATIVE PHONE NUMBER");
		patientPhoneNumber2.setBounds(700,260,900,31);


		Label patientAddress = new Label(shellCreate, SWT.NONE);
		patientAddress.setText("DOOR.NO/STREET*");
		patientAddress.setBounds(100,320,900,31);
		patientAddress.setForeground(color1);


		Label patientAddressCity = new Label(shellCreate, SWT.NONE);
		patientAddressCity.setText("CITY*");
		patientAddressCity.setBounds(100,380,900,31);
		patientAddressCity.setForeground(color1);
		Label patientAddressPincode = new Label(shellCreate, SWT.NONE);
		patientAddressPincode.setText("PINCODE*");
		patientAddressPincode.setBounds(100,440,900,31);
		patientAddressPincode.setForeground(color1);


		Label patientAddress2 = new Label(shellCreate, SWT.NONE);
		patientAddress2.setText("DOOR.NO/STREET-2");
		patientAddress2.setBounds(700,320,900,31);


		Label patientAddressCity2 = new Label(shellCreate, SWT.NONE);
		patientAddressCity2.setText("CITY-2");
		patientAddressCity2.setBounds(700,380,900,31);

		Label patientAddressPincode2 = new Label(shellCreate, SWT.NONE);
		patientAddressPincode2.setText("PINCODE-2");
		patientAddressPincode2.setBounds(700,440,900,31);



		patientName.pack();
		patientGender.pack();
		patientDob.pack();
		patientPhoneNumber.pack();
		patientPhoneNumber2.pack();
		patientAddress.pack();
		patientAddressCity.pack();
		patientAddressPincode.pack();
		patientAddress2.pack();
		patientAddressCity2.pack();
		patientAddressPincode2.pack();



		Button locateButton  = new Button(shellCreate, SWT.NONE);
		locateButton.setBounds(900, 80, 180, 31);
		locateButton.setText("Patient Locator");

		locateButton.setBackground(display.getSystemColor(SWT.COLOR_LIST_BACKGROUND));

		nameText = new Text(shellCreate, SWT.BORDER);
		nameText.setBounds(300, 80, 300, 31);
		nameText.setTextLimit(30);

		femaleRadio = new Button(shellCreate, SWT.RADIO);
		femaleRadio.setBounds(300, 140, 100, 31);
		femaleRadio.setText("Female");
		maleRadio = new Button(shellCreate, SWT.RADIO);
		maleRadio.setBounds(410, 140, 110, 31);
		maleRadio.setText("Male");

		dateTimeText = new DateTime(shellCreate, SWT.BORDER);
		dateTimeText.setBounds(300, 200, 300, 31);

		phoneNumberText = new Text(shellCreate, SWT.BORDER);
		phoneNumberText.setBounds(300, 260, 300, 31);
		phoneNumberText.setTextLimit(10);

		phoneNumberText2 = new Text(shellCreate, SWT.BORDER);
		phoneNumberText2.setBounds(950, 260, 300, 31);
		phoneNumberText2.setTextLimit(10);

		addressText = new Text(shellCreate, SWT.BORDER);
		addressText.setBounds(300, 320, 300, 31);
		addressText.setTextLimit(40);

		addressCityText = new Text(shellCreate, SWT.BORDER);
		addressCityText.setBounds(300,380,300,31);
		addressCityText.setTextLimit(15);

		addressPicodeText = new Text(shellCreate, SWT.BORDER);
		addressPicodeText.setBounds(300,440,300,31);
		addressPicodeText.setTextLimit(10);

		addressText2 = new Text(shellCreate, SWT.BORDER);
		addressText2.setBounds(950, 320, 300, 31);
		addressText2.setTextLimit(40);

		addressCityText2 = new Text(shellCreate, SWT.BORDER);
		addressCityText2.setBounds(950,380,300,31);
		addressCityText2.setTextLimit(15);

		addressPicodeText2 = new Text(shellCreate, SWT.BORDER);
		addressPicodeText2.setBounds(950,440,300,31);
		addressPicodeText2.setTextLimit(10);

		Label notelabel = new Label(shellCreate, SWT.NONE);
		notelabel.setText("Note:The feilds mentioned wtih * are mandatory");
		notelabel.setBounds(100,510,900,31);
		Color color2 = new Color(display, 224, 0, 0);
		notelabel.setForeground(color2);
		notelabel.pack();

		createButton  = new Button(shellCreate, SWT.NONE);
		createButton.setBounds(620, 570, 180, 31);
		createButton.setText("SAVE");
		Color color = new Color(display, 0, 204, 0);
		createButton.setBackground(color);

		modify = new Button(shellCreate, SWT.NONE);
		modify.setBounds(800, 570, 180, 31);
		modify.setText("MODIFY");
		modify.setBackground(color);
		modify.setVisible(false);
		locateButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				PatientOperationView opView = new PatientOperationView(display);
				opView.setView(view);
				opView.showPatientDetails(false);
			}
		});

		createButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				createOrModifyPatient(display, shellCreate, false);
			}
		});
		modify.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				createOrModifyPatient(display, shellCreate, true);
			}
		});
	}
	/* method to create onclick of modify from next page */
	private void createOrModifyPatient(Display display, Shell shellCreate, boolean ismodify) {
		boolean isAllDataValid = true;
		System.out.println("Inside create patient process");
		String name  = nameText.getText();
		Integer day =dateTimeText.getDay();
		Integer month=dateTimeText.getMonth();
		Integer year =dateTimeText.getYear();

		Calendar cal = Calendar.getInstance();
		cal.set( Calendar.YEAR, year );
		cal.set( Calendar.MONTH, month );
		cal.set( Calendar.DATE, day );
		cal.set( Calendar.HOUR, 0 );
		cal.set( Calendar.MINUTE, 0 );
		cal.set( Calendar.SECOND, 0 );
		java.sql.Date patientDob = new java.sql.Date( cal.getTimeInMillis() );
		System.out.println(patientDob);
		String phoneNumber =phoneNumberText.getText();
		String doorNo=addressText.getText();
		String city=addressCityText.getText();
		String pincode=addressPicodeText.getText();
		String phoneNumber2 = phoneNumberText2.getText();
		String doorNo2 =addressText2.getText() ;
		String city2 =addressCityText2.getText();
		String pincod2=addressPicodeText2.getText();
		boolean femaleRadioButton = femaleRadio.getSelection();
		boolean maleRadioButton = maleRadio.getSelection();
		nameText.setEditable(true);
		dateTimeText.setRedraw(true);

		phoneNumberText.setEditable(true);
		phoneNumberText2.setEditable(true);
		addressText.setEditable(true);
		addressCityText.setEditable(true);
		addressPicodeText.setEditable(true);
		addressText2.setEditable(true);
		addressCityText2.setEditable(true);
		addressPicodeText2.setEditable(true);
		createButton.setEnabled(true);
		modify.setVisible(false);
		femaleRadio.setRedraw(true);
		maleRadio.setRedraw(true);

		if(StringUtils.isEmpty(name)) {
			isAllDataValid = false;
			showDailogeBox(shellCreate, "Please Enter Patient Name");
		}if(!validateAlpabets(name)) {
			isAllDataValid = false;
			showDailogeBox(shellCreate, "Please Enter valid patient Name");
		}else if( !femaleRadioButton  && !maleRadioButton ) {
			isAllDataValid = false;
			showDailogeBox(shellCreate, "Please Choose  Pateint Gender");

		}else if(StringUtils.isEmpty(phoneNumber)) {
			isAllDataValid = false;
			showDailogeBox(shellCreate, "Please Enter Patient PhoneNumber");
		}else if(phoneNumber.length() < 10) {
			isAllDataValid = false;
			showDailogeBox(shellCreate, "Please Enter Patient  valid PhoneNumber");
		}else if(!validateNumbers(phoneNumber)) {
			isAllDataValid = false;
			showDailogeBox(shellCreate, "Please Enter Patient  valid PhoneNumber");
		}else if(StringUtils.isEmpty(doorNo)) {
			isAllDataValid = false;
			showDailogeBox(shellCreate, "Please Enter Patient DoorNo");
		}else if(StringUtils.isEmpty(city)) {
			isAllDataValid = false;
			showDailogeBox(shellCreate, "Please Enter Patient City");
		}else if(StringUtils.isEmpty(pincode)) {
			isAllDataValid = false;
			showDailogeBox(shellCreate, "Please Enter Patient Pincode");
		}else if(!validateNumbers(pincode)) {
			isAllDataValid = false;
			showDailogeBox(shellCreate, "Please Enter Patient  valid pincode");
		}else if( StringUtils.isNotEmpty(phoneNumber2) && !validateNumbers(phoneNumber2)) {
			isAllDataValid = false;
			showDailogeBox(shellCreate, "Please Enter  valid alternative PhoneNumber");
		}
		String address1 = new StringBuilder(doorNo).append("-").append(city).append("-").append(pincode).toString();
		System.out.println("Address1 "+address1);
		String address2 = StringUtils.EMPTY;
		if(StringUtils.isNotEmpty(doorNo2) || StringUtils.isNotEmpty(city2) || StringUtils.isNotEmpty(pincod2)) {
			address2 = new StringBuilder(doorNo2).append("-").append(city2).append("-").append(pincod2).toString();
			if(!validateNumbers(pincod2)) {
				isAllDataValid = false;
				showDailogeBox(shellCreate, "Please Enter  valid Pincode-2");
			}
			System.out.println("Address2 "+address2);
		}

		String gender = "Male";
		if(femaleRadioButton) {
			gender= "Female";
		}
		if(isAllDataValid) {
			Patient patient;
			if(ismodify) {
				System.out.println("Inside modified today");
				patient = constructPatientObject(name, gender, patientDob,phoneNumber,address1 ,phoneNumber2, address2,availablePatient, true);
			}else {
				this.setAvailablePatient(null);
				patient = constructPatientObject(name, gender, patientDob,phoneNumber,address1 ,phoneNumber2, address2,availablePatient,false);
			}

			try {
				PatientOperationView opView = new PatientOperationView(display);
				HttpResponse<String> reponsestring;
				if(ismodify) {
					reponsestring= SWTHttpRestClient.updatePatient(patient);
				}else {
					reponsestring = SWTHttpRestClient.savePatient(patient);
				}

				if(reponsestring.statusCode() == 200) {
					opView.setView(view);
					opView.showPatientDetails(true);

				}
			}catch(IOException | InterruptedException e) {

				System.out.println(e.getMessage()); 
				showDailogeBox(shellCreate,
						"Error while creating new patient");
			}
		}
	}

	/* method to create default values setting in form */
	public void setDataForFeildsForDefaultPage( ) {
		nameText.setText("");
		femaleRadio.setSelection(false);
		maleRadio.setSelection(false);
		phoneNumberText.setText("");
		phoneNumberText2.setText("");
		addressText.setText("");
		addressText2.setText("");
		addressCityText.setText("");
		addressCityText2.setText("");
		addressPicodeText2.setText("");
		addressPicodeText.setText("");
	}

	/* method to validate characters setting in form */
	public  boolean validateAlpabets(String input)  {
		String regex ="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$";
		boolean isValidInput = false;
		Pattern p = Pattern.compile(regex);
		if (!Objects.isNull(input)) {
			Matcher m = p.matcher(input);
			isValidInput = m.matches() ? true : false;
		}
		return  isValidInput;
	}
	/* method to validate numbers*/
	public   boolean validateNumbers(String input)  {
		String regex = "[0-9]+";
		boolean isValidInput = false;
		Pattern p = Pattern.compile(regex);
		if (!Objects.isNull(input)) {
			Matcher m = p.matcher(input);
			isValidInput = m.matches() ? true : false;
		}
		return  isValidInput;
	}

	/* method to set old default values during modification  in form */
	public void setDataForFeildsForMofidy( Patient patient, Display  display, boolean isDisabled) {
		System.out.println("inside modify seting values");
		nameText.setText(patient.getPatientName());
		if("Female".equalsIgnoreCase(patient.getPatientGender())) {
			femaleRadio.setSelection(true);
			if(isDisabled) {
				maleRadio.setRedraw(false);
			}else {
				maleRadio.setRedraw(true);
			}

		}else {
			maleRadio.setSelection(true);
			if(isDisabled) {
				femaleRadio.setRedraw(false);
			}else {
				femaleRadio.setRedraw(true);
			}
		}


		if(patient.getPatientPhList() != null && patient.getPatientPhList().size() == 1) {
			phoneNumberText.setText(patient.getPatientPhList().get(0).getPhoneNumber());
		}else if(patient.getPatientPhList() != null && patient.getPatientPhList().size() == 2) {
			phoneNumberText.setText(patient.getPatientPhList().get(0).getPhoneNumber());
			phoneNumberText2.setText(patient.getPatientPhList().get(1).getPhoneNumber());
		}else {
			phoneNumberText.setText(""); 
			phoneNumberText2.setText("");
		}
		java.util.Date patientDobUtil =   patient.getPatientDob();
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(patientDobUtil);
		dateTimeText.setRedraw(true);
		dateTimeText.setDay(calendar1.get(Calendar.DATE));
		dateTimeText.setMonth(calendar1.get(Calendar.MONTH));
		dateTimeText.setYear(calendar1.get(Calendar.YEAR));

		System.out.println("patientDobUtil"+patientDobUtil);
		System.out.println(dateTimeText.getDay());
		if(patient.getPatientAddressList() != null && patient.getPatientAddressList().size() == 1) {
			Address add1 = patient.getPatientAddressList().get(0);
			String address1 = add1.getAddress();
			String[] addArray = address1.split("-");
			addressText.setText(addArray[0]);
			addressCityText.setText(addArray[1]);
			addressPicodeText.setText(addArray[2]);
		}else if(patient.getPatientAddressList() != null && patient.getPatientAddressList().size() == 2) {
			Address add1 = patient.getPatientAddressList().get(0);
			String address1 = add1.getAddress();
			String[] addArray = address1.split("-");
			addressText.setText(addArray[0]);
			addressCityText.setText(addArray[1]);
			addressPicodeText.setText(addArray[2]);
			Address add2 = patient.getPatientAddressList().get(1);
			String address2 = add2.getAddress();
			String[] addArray2 = address2.split("-");

			addressText2.setText(addArray2[0]);
			addressCityText2.setText(addArray2[1]);
			addressPicodeText2.setText(addArray2[2]);
		}else {
			addressText.setText("");
			addressCityText.setText("");
			addressPicodeText.setText("");
			addressText2.setText("");
			addressCityText2.setText("");
			addressPicodeText2.setText(""); 
		}
		this.setAvailablePatient(patient);
		if(isDisabled) {

			nameText.setEditable(false);
			dateTimeText.setRedraw(false);

			phoneNumberText.setEditable(false);
			phoneNumberText2.setEditable(false);
			addressText.setEditable(false);
			addressCityText.setEditable(false);
			addressPicodeText.setEditable(false);
			addressText2.setEditable(false);
			addressCityText2.setEditable(false);
			addressPicodeText2.setEditable(false);
		}else {
			nameText.setEditable(true);
			dateTimeText.setRedraw(true);

			phoneNumberText.setEditable(true);
			phoneNumberText2.setEditable(true);
			addressText.setEditable(true);
			addressCityText.setEditable(true);
			addressPicodeText.setEditable(true);
			addressText2.setEditable(true);
			addressCityText2.setEditable(true);
			addressPicodeText2.setEditable(true);
		}
		createButton.setEnabled(false);
		if(!isDisabled) {
			modify.setVisible(true);
		}else {
			modify.setVisible(false);
		}

	}


	/* method to create patient  object from form */

	protected Patient constructPatientObject(String name, String gender, Date patientDob, String phoneNumber1,
			String address1, String phoneNumber2, String address2, Patient oldPatient, boolean isModify) {
		Patient patient = new Patient();
		if(isModify) {
			patient.setPatientId(oldPatient.getPatientId());
		}
		patient.setPatientName(name);
		patient.setPatientGender(gender);
		patient.setPatientDob(patientDob);

		List<Address> addressList = new ArrayList<>();

		Address addressObj1 = new Address();
		if(isModify) {
			addressObj1.setAddId(oldPatient.getPatientAddressList().get(0).getAddId());
		}
		addressObj1.setAddress(address1);
		addressList.add(addressObj1);
		
		if(StringUtils.isNotEmpty(address2)) {
			Address addressObj2 = new Address(); 
			if(isModify && oldPatient.getPatientAddressList() != null && oldPatient.getPatientAddressList().size() == 2) {
				addressObj2.setAddId(oldPatient.getPatientAddressList().get(1).getAddId());
			}
			addressObj2.setAddress(address2);
			addressList.add(addressObj2);
		}

		List<PhoneNumber> phoneNumberList = new ArrayList<>();
		PhoneNumber phoneNumberObj1= new PhoneNumber();
		if(isModify) {
			phoneNumberObj1.setPhId(oldPatient.getPatientPhList().get(0).getPhId());
		}
		phoneNumberObj1.setPhoneNumber(phoneNumber1);
		phoneNumberList.add(phoneNumberObj1);
		if(StringUtils.isNotEmpty(phoneNumber2)) {
			PhoneNumber phoneNumberObj2= new PhoneNumber();
			if(isModify && oldPatient.getPatientPhList()!= null && oldPatient.getPatientPhList().size() == 2) {
				phoneNumberObj2.setPhId(oldPatient.getPatientPhList().get(1).getPhId());
			}
			phoneNumberObj2.setPhoneNumber(phoneNumber2);
			phoneNumberList.add(phoneNumberObj2);
		}
		patient.setPatientAddressList(addressList);
		patient.setPatientPhList(phoneNumberList);
		System.out.println("patient object before going to modify"+patient);



		return patient;
	}

	/* method to create dialogue box*/
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
}
