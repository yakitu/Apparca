package wb;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Apparca {

	protected Shell shlAppaparcaDePrez;
	private Group grpDadesVehicle;
	private Label lblMatrcula;
	private Group grpDadesEstacionament;
	private Group grpErrorsIAlertes;
	private Text txtError;
	private Label lblEtiqueta;
	private Text txtMatricula;
	private Combo comboEtiqueta;
	private Button btnValidar;
	private Label lblTempsAparcaten;
	private Text txtTempsAparcat;
	private Label lblResident;
	private Combo comboResident;
	private Button btnRestaurar;
	private Button btnCalcular;
	private Label lblElPreuDe;
	private Text txtTotalPreu;
	private Label lblEuros;
	private String missatge;
	private int indicaEtiqueta;
	private int indicaResident;
	private int tempsAparcat;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Apparca window = new Apparca();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlAppaparcaDePrez.open();
		shlAppaparcaDePrez.layout();
		while (!shlAppaparcaDePrez.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAppaparcaDePrez = new Shell();
		shlAppaparcaDePrez.setSize(701, 714);
		shlAppaparcaDePrez.setText("Apparca deveps");
		
		grpDadesVehicle = new Group(shlAppaparcaDePrez, SWT.NONE);
		grpDadesVehicle.setText("Dades vehicle");
		grpDadesVehicle.setBounds(10, 10, 663, 231);
		
		lblMatrcula = new Label(grpDadesVehicle, SWT.NONE);
		lblMatrcula.setBounds(10, 57, 70, 20);
		lblMatrcula.setText("Matrícula");
		
		lblEtiqueta = new Label(grpDadesVehicle, SWT.NONE);
		lblEtiqueta.setBounds(10, 131, 70, 20);
		lblEtiqueta.setText("Etiqueta");
		
		txtMatricula = new Text(grpDadesVehicle, SWT.BORDER);
		txtMatricula.setToolTipText("Matrícula del vehicle");
		txtMatricula.setBounds(124, 57, 184, 26);
		
		comboEtiqueta = new Combo(grpDadesVehicle, SWT.NONE);
		comboEtiqueta.setToolTipText("Etiqueta ambiental");
		comboEtiqueta.setItems(new String[] {"0", "ECO", "C", "B", "Sense distintiu"});
		comboEtiqueta.setBounds(124, 131, 184, 28);
		
		btnValidar = new Button(grpDadesVehicle, SWT.NONE);
		btnValidar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(controlDades()) {
					disposaControls(false);
					disposaCalcul(true);
					txtError.setText("");
				}else {
					txtError.setText(missatge);
				}
			}
		});
		btnValidar.setBounds(423, 92, 90, 30);
		btnValidar.setText("VALIDAR");
		
		grpDadesEstacionament = new Group(shlAppaparcaDePrez, SWT.NONE);
		grpDadesEstacionament.setEnabled(false);
		grpDadesEstacionament.setText("Dades Estacionament");
		grpDadesEstacionament.setBounds(10, 254, 663, 324);
		
		lblTempsAparcaten = new Label(grpDadesEstacionament, SWT.NONE);
		lblTempsAparcaten.setBounds(10, 50, 182, 20);
		lblTempsAparcaten.setText("Temps aparcat (en minuts) :");
		
		txtTempsAparcat = new Text(grpDadesEstacionament, SWT.BORDER);
		txtTempsAparcat.setToolTipText("Temps en minuts d'estacionament del vehicle");
		txtTempsAparcat.setEditable(false);
		txtTempsAparcat.setBounds(216, 50, 118, 26);
		
		lblResident = new Label(grpDadesEstacionament, SWT.NONE);
		lblResident.setBounds(10, 104, 70, 20);
		lblResident.setText("Resident");
		
		comboResident = new Combo(grpDadesEstacionament, SWT.NONE);
		comboResident.setToolTipText("És resident?");
		comboResident.setItems(new String[] {"Sí", "No"});
		comboResident.setBounds(95, 104, 97, 28);
		comboResident.setEnabled(false);
		
		btnRestaurar = new Button(grpDadesEstacionament, SWT.NONE);
		btnRestaurar.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				disposaControls(true);
				disposaCalcul(false);
			}
		});
		btnRestaurar.setBounds(465, 104, 90, 30);
		btnRestaurar.setText("RESTAURAR");
		
		btnCalcular = new Button(grpDadesEstacionament, SWT.NONE);
		btnCalcular.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(controlCalcul()) {
					calculs();
					txtError.setText("");
				}else {
					esborraTotal();
					txtError.setText(missatge);
				}
			}
		});
		btnCalcular.setBounds(244, 163, 132, 43);
		btnCalcular.setText("CALCULAR");
		
		lblElPreuDe = new Label(grpDadesEstacionament, SWT.NONE);
		lblElPreuDe.setBounds(74, 250, 209, 20);
		lblElPreuDe.setText("El preu de l'aparcament serà de");
		
		txtTotalPreu = new Text(grpDadesEstacionament, SWT.BORDER);
		txtTotalPreu.setEditable(false);
		txtTotalPreu.setBounds(294, 247, 60, 26);
		
		lblEuros = new Label(grpDadesEstacionament, SWT.NONE);
		lblEuros.setBounds(360, 250, 39, 20);
		lblEuros.setText("euros.");
		
		grpErrorsIAlertes = new Group(shlAppaparcaDePrez, SWT.NONE);
		grpErrorsIAlertes.setEnabled(false);
		grpErrorsIAlertes.setText("Errors i Alertes");
		grpErrorsIAlertes.setBounds(10, 584, 663, 73);
		
		txtError = new Text(grpErrorsIAlertes, SWT.BORDER);
		txtError.setEditable(false);
		txtError.setBounds(10, 37, 643, 26);

	}
	
	private boolean controlDades() {
		
		missatge = "";
		
		if (campBuit(txtMatricula.getText())) {
			enfocaText(txtMatricula);
			missatge = "És obligatori introduir la matrícula";
			return false;
		}
		
		if (!txtMatricula.getText().toUpperCase().matches("^[0-9]{4}[BCDFGHJKLMNPQRSTVWXYZ]{3}$")) {
			enfocaText(txtMatricula);
			missatge = "El format de la matrícula no és l'esperat, format de 4 números i 3 lletres (Ex. 5674BJD)";
	        return false;
	    }
			
		indicaEtiqueta = comboEtiqueta.getSelectionIndex();
		int maxIndex = comboEtiqueta.getItemCount()-1;
		if(indicaEtiqueta<0) {
			comboEtiqueta.setFocus();
			missatge = "La categoria no pot estar buida";
			return false;
		}else if(maxIndex>4) {
			comboEtiqueta.setFocus();
			missatge = "Tipus d'etiqueta incorrecte";
			return false;
		}
		
		return true;
	}
	
	private boolean controlCalcul() {
		
		if (campBuit(txtTempsAparcat.getText())) {
			enfocaText(txtTempsAparcat);
			missatge = "Heu d'indicar el temps aparcat";
			return false;
		}
		
		try {
			tempsAparcat = Integer.parseInt(txtTempsAparcat.getText());
		}catch(NumberFormatException | NullPointerException ex){  
			enfocaText(txtTempsAparcat);
			missatge = "Heu de posar un nombre enter";
			return false;
		}
		
		if(tempsAparcat < 1) {
			enfocaText(txtTempsAparcat);
			missatge = "El temps mínim ha de ser 1 minut";
			return false;
		}
		
		indicaResident= comboResident.getSelectionIndex();
		int maxIndex = comboResident.getItemCount()-1;
		if(indicaResident<0) {
			comboResident.setFocus();
			missatge = "La categoria no pot estar buida";
			return false;
		}else if(maxIndex>1) {
			comboResident.setFocus();
			missatge = "Tipus de resposta incorrecte";
			return false;
		}
		
		if(tempsAparcat > 120 && indicaResident==1) {
			enfocaText(txtTempsAparcat);
			missatge = "El temps màxim d'aparcament per als no residents és de dues hores";
			return false;
		}
		
		missatge = "";
		return true;
	}
	
	private void disposaControls(boolean habilitacio) {
		
		grpDadesVehicle.setEnabled(habilitacio);
		txtMatricula.setEditable(habilitacio);
		comboEtiqueta.setEnabled(habilitacio);
		if(habilitacio) {
			txtMatricula.setText("");
			comboEtiqueta.select(-1);
			comboEtiqueta.setText("");
		}
		
	}
	
	private void disposaCalcul(boolean habilitacio) {
		
		grpDadesEstacionament.setEnabled(habilitacio);
		txtTempsAparcat.setEditable(habilitacio);
		comboResident.setEnabled(habilitacio);
		
		if(!habilitacio) {
			txtTempsAparcat.setText("");
			comboResident.select(-1);
			comboResident.setText("");
			esborraTotal();
		}
	}
	
	private void calculs() {
		
		double hores;
		double etiqueta;
		double preuBase;
		double preuTotal;
		
		hores = tempsAparcat/60;
		etiqueta = calculEtiqueta(indicaEtiqueta);
		preuBase = etiqueta*hores;
		if(indicaResident==0) {
			preuTotal = preuBase/2;	
		} else {
			preuTotal = preuBase;
		}
		
		txtTotalPreu.setText(String.format( "%.2f", preuTotal));
	}
	
	private double calculEtiqueta(int tipusEtiqueta) {
		
		switch(tipusEtiqueta) {
		case 0:
			return 0.25;
		case 1:
			return 1;
		case 2:
			return 1.75;
		case 3:
			return 2.25;
		case 4:
			return 3;
		default:
			return 0;
		}
	}

	private void esborraTotal() {
		
		txtTotalPreu.setText("");
	}

	private boolean campBuit(String s){
		
		return s.trim().isEmpty();
	}
	
	private void enfocaText(Text t){
		
		t.setFocus();
		t.selectAll();
	}
}
