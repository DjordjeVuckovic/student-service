package gui.view.dialog.edit;

import controller.ProfessorController;
import controller.SubjectController;
import controller.focuslisteners.SubjectListener;
import gui.view.MainWindow;
import gui.view.dialog.MyDialog;
import model.Professor;
import model.Semester;
import model.Subject;
import model.YearofStudy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serial;
import java.util.ArrayList;


public class EditSubject extends MyDialog {
    @Serial
    private static final long serialVersionUID = -7200703964240153029L;
    private JTextField txtIdS;
    private JTextField txtName;
    private JComboBox<String> txtFJComboBoxCurrentYear;
    private JComboBox<String> txtSemester;
    private JTextField txtPointsESPB;
    private JTextField txtProfesor;
    
    private JButton btAccept;
    private Subject oldsubject;
    
    private JButton btnDodajProf;
    private JButton btnUkloniProf;
    
    private Professor profesor;
    
    /*
    private static EditSubject instance = null;
    public static EditSubject getInstance(String idS){
        if(instance==null){
            instance = new EditSubject(idS);
        }
        return instance;
    }
    */
    
    public void setEnableButtProf(boolean yes) {
		if(yes) {
			btnDodajProf.setEnabled(true);
			btnUkloniProf.setEnabled(false);
		}else {
			btnDodajProf.setEnabled(false);
			btnUkloniProf.setEnabled(true);
		}
	}
    
    
    public boolean allValid(){
        for(SubjectListener sb:validations){
            if(!sb.getValidation()){
                return false;
            }
        }
        return true;
    }
    public void EnableButt(){
        btAccept.setEnabled(allValid());
    }
    
    private ArrayList<SubjectListener> validations=new java.util.ArrayList<>();
    public EditSubject(String idS) {
        super(MainWindow.getInstance(), "Izmena predmeta");
        oldsubject = SubjectController.getInstance().findSubjectById(idS);
        initEditDialog();
    }
    private void initEditDialog(){
        Dimension cellDim = new Dimension(200, 20);
        setLayout(new BorderLayout());
        
        JLabel lbIds = new JLabel("Sifra*");
        lbIds.setToolTipText("Unesite jedinstvenu sifru predmeta");
        lbIds.setPreferredSize(cellDim);
        txtIdS = new JTextField();
        txtIdS.setToolTipText("Unesite jedinstvenu sifru predmeta");
        txtIdS.setPreferredSize(cellDim);
        txtIdS.setName("txtId");
        JPanel panelA = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelA.add(lbIds);
        panelA.add(txtIdS);


        JLabel lbName = new JLabel("Naziv*");
        lbName.setToolTipText("Unesite naziv oldsubjecta");
        lbName.setPreferredSize(cellDim);
        txtName = new JTextField();
        txtName.setToolTipText("Unesite naziv oldsubjecta");
        txtName.setPreferredSize(cellDim);
        txtName.setName("txtName");
        JPanel panelB = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelB.add(lbName);
        panelB.add(txtName);


        JLabel lbESPB = new JLabel("ESPB*");
        lbESPB.setToolTipText("Unesite broj ESPB bodova");
        lbESPB.setPreferredSize(cellDim);
        txtPointsESPB = new JTextField();
        txtPointsESPB.setToolTipText("Unesite broj ESPB bodova");
        txtPointsESPB.setPreferredSize(cellDim);
        txtPointsESPB.setName("txtESPB");
        JPanel panelC = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelC.add(lbESPB);
        panelC.add(txtPointsESPB);

        JLabel lbCurrentYear = new JLabel("Godina izvodjenja predmeta*");
        lbCurrentYear.setPreferredSize(cellDim);
        String []currentYear = new String[]{"I", "II", "III", "IV"};
        txtFJComboBoxCurrentYear = new JComboBox<>(currentYear);
        txtFJComboBoxCurrentYear.setName("txtCurrentYear");
        txtFJComboBoxCurrentYear.setPreferredSize(cellDim);
        JPanel panelD= new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelD.add(lbCurrentYear);
        panelD.add(txtFJComboBoxCurrentYear);

        JLabel lbSem = new JLabel("Semestar*");
        lbSem.setPreferredSize(cellDim);
        String []sem = new String[]{"SUMMER", "WINTER"};
        txtSemester = new JComboBox<>(sem);
        txtSemester.setName("txtCurrentYear");
        txtSemester.setPreferredSize(cellDim);
        JPanel panelE = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelE.add(lbSem);
        panelE.add(txtSemester);

        
        JLabel lbProf = new JLabel("Profesor*");
        lbProf.setToolTipText("Samo jedan profesor");
        lbProf.setPreferredSize(new Dimension(135,20));
        txtProfesor = new JTextField();
        txtProfesor.setToolTipText("Samo jedan profesor");
        txtProfesor.setPreferredSize(cellDim);
        txtProfesor.setName("txtProfesor");
        txtProfesor.setEditable(false);  
        JPanel panelP = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelP.add(lbProf);
        panelP.add(txtProfesor);
     
        
		btnDodajProf = new JButton();
		btnDodajProf.setIcon(new ImageIcon("img/add-icon.png"));
		btnDodajProf.setPreferredSize(new Dimension(20,20));
		btnUkloniProf = new JButton();
		btnUkloniProf.setIcon(new ImageIcon("img/iconDelete.png"));
		btnUkloniProf.setPreferredSize(new Dimension(20,20));
		btnUkloniProf.setEnabled(false);
		
		
		btnDodajProf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddProfessorForSubject dodajProfPredmetu = new AddProfessorForSubject(parent);
				profesor = dodajProfPredmetu.getSelectedProf();
				if(profesor != null) {
					txtProfesor.setText(profesor.getName() + " " + profesor.getSurname());
					setEnableButtProf(false);
					allValid();
				}
			}
		});
		
		btnUkloniProf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String poruka = "Da li \u017Eelite da uklonite predmetnog profesora?";
				Object[] opcije = {"Odustani","Potvrdi"};
				int option = JOptionPane.showOptionDialog(parent, poruka, "Ukloni Profesora", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE, null, opcije, null);
				if(option == JOptionPane.NO_OPTION) {
					profesor = null; 
					setEnableButtProf(true);
					txtProfesor.setText("");
					allValid();
				}
			}
		});
		
		
        //validation
        SubjectListener val1 = new SubjectListener(lbIds,txtIdS,this,oldsubject.getIdS());
        txtIdS.addFocusListener(val1);
        validations.add(val1);

        SubjectListener val2 = new SubjectListener(lbESPB,txtPointsESPB,this);
        txtPointsESPB.addFocusListener(val2);
        validations.add(val2);

        SubjectListener val3 = new SubjectListener(lbName,txtName,this);
        txtName.addFocusListener(val3);
        validations.add(val3);
        //
        initFields(oldsubject);
        //
        JPanel CentralPanel = new JPanel();
        BoxLayout boxCenter = new BoxLayout(CentralPanel,BoxLayout.Y_AXIS);
        CentralPanel.setLayout(boxCenter);
        CentralPanel.add(Box.createVerticalGlue());
        CentralPanel.add(panelA);
        CentralPanel.add(panelB);
        CentralPanel.add(panelC);
        CentralPanel.add(panelD);
        CentralPanel.add(panelE);
        CentralPanel.add(panelP);
        
        panelP.add(btnDodajProf);
        panelP.add(Box.createHorizontalStrut(7));
        panelP.add(btnUkloniProf);
        
        this.add(CentralPanel,BorderLayout.CENTER);
        //button
        JPanel JButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButtonPanel.setPreferredSize(new Dimension(60,60));
        btAccept = new JButton("Potvrdi");
        btAccept.setEnabled(false);
        btAccept.setMnemonic(KeyEvent.VK_S);
        btAccept.setForeground(Color.GREEN);
        JButton btDecline = new JButton("Odustani");
        btDecline.setForeground(Color.BLACK);
        btDecline.setMnemonic(KeyEvent.VK_Q);
        btDecline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JButtonPanel.add(btDecline);
        btAccept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Subject sub = new Subject();
                sub.setIdS(txtIdS.getText());
                val1.setIdSub(sub.getIdS());
                sub.setNameSub(txtName.getText());
                sub.setESPBpoints(Integer.parseInt(txtPointsESPB.getText()));
                if(txtFJComboBoxCurrentYear.getSelectedIndex()==0){
                    sub.setYearOfStudy(YearofStudy.I);
                }
                else if(txtFJComboBoxCurrentYear.getSelectedIndex()==1){
                    sub.setYearOfStudy(YearofStudy.II);
                }
                else if(txtFJComboBoxCurrentYear.getSelectedIndex()==2){
                    sub.setYearOfStudy(YearofStudy.III);
                }
                else if(txtFJComboBoxCurrentYear.getSelectedIndex()==3){
                    sub.setYearOfStudy(YearofStudy.IV);
                }
                if(txtSemester.getSelectedIndex()==0){
                    sub.setSemester(Semester.SUMMER);
                }
                else{
                    sub.setSemester(Semester.WINTER);
                }
                if(profesor != null) {
					sub.setProfessor(profesor);
				}
                //prvo menjamo predmet pa ga dodajemo profesoru
                SubjectController.getInstance().editSubject(oldsubject, sub);
                
                ProfessorController.getInstance().AddSubjectForProfessor(profesor, sub);
                
                btAccept.setEnabled(false);
                dispose();
            }
        });
        JButtonPanel.add(btAccept);
        JButtonPanel.add(Box.createHorizontalStrut(20));
        this.add(JButtonPanel,BorderLayout.SOUTH);
    }
    private void initFields(Subject subject){
        txtIdS.setText(subject.getIdS());
        txtName.setText(subject.getNameSub());
        txtPointsESPB.setText(String.valueOf(subject.getESPBpoints()));
        if(subject.getSemester() ==Semester.SUMMER){
            txtSemester.setSelectedIndex(0);
        }else{
            txtSemester.setSelectedIndex(1);
        }
        if(subject.getYearOfStudy()== YearofStudy.I) {
            txtFJComboBoxCurrentYear.setSelectedIndex(0);
        }
        if(subject.getYearOfStudy()== YearofStudy.II) {
            txtFJComboBoxCurrentYear.setSelectedIndex(1);
        }
        if(subject.getYearOfStudy()== YearofStudy.I) {
            txtFJComboBoxCurrentYear.setSelectedIndex(2);
        }
        if(subject.getYearOfStudy()== YearofStudy.I) {
            txtFJComboBoxCurrentYear.setSelectedIndex(3);
        }
        if(oldsubject.getProfessor() != null) {
			profesor = oldsubject.getProfessor();
			txtProfesor.setText(profesor.getName()+" "+profesor.getSurname());
			setEnableButtProf(false);
		}else {
			txtProfesor.setText("");
			setEnableButtProf(true);
		}
  
        
    }
    }
