package gui.view.dialog.edit;

import controller.SubjectController;
import controller.focuslisteners.SubjectListener;
import gui.view.MainWindow;
import gui.view.dialog.MyDialog;
import gui.view.dialog.add.AddSubject;
import model.Semester;
import model.Subject;
import model.YearofStudy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EditSubject extends MyDialog {
    private JTextField txtIdS;
    private JTextField txtName;
    private JComboBox<String> txtFJComboBoxCurrentYear;
    private JComboBox<String> txtSemester;
    private JTextField txtPointsESPB;
    private JButton btAccept;
    private Subject oldsubject;
    private static EditSubject instance = null;
    public static EditSubject getInstance(String idS){
        if(instance==null){
            instance = new EditSubject(idS);
        }
        return instance;
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
        lbName.setToolTipText("Unesite naziv predmeta");
        lbName.setPreferredSize(cellDim);
        txtName = new JTextField();
        txtName.setToolTipText("Unesite naziv predmeta");
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
        this.add(CentralPanel,BorderLayout.CENTER);
        //button
        JPanel diaButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        diaButtonPanel.setPreferredSize(new Dimension(60,60));
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

        diaButtonPanel.add(btDecline);
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
                SubjectController.getInstance().editSubject(oldsubject,sub);
                btAccept.setEnabled(false);
                dispose();
            }
        });
        diaButtonPanel.add(btAccept);
        diaButtonPanel.add(Box.createHorizontalStrut(20));
        this.add(diaButtonPanel,BorderLayout.SOUTH);
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
    }
    }
