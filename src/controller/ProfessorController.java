package controller;

import gui.view.center.ProfessorsTable;
import model.BazaKatedri;
import model.BazaProfesora;
import model.Professor;
import model.Subject;

import java.util.List;

public class ProfessorController {
    private static  ProfessorController instance = null;
    public static ProfessorController getInstance(){
        if(instance==null){
            instance = new ProfessorController();
        }
        return instance;
    }
    private ProfessorController(){}
    
    public List<Professor> getProfessors() {
		return BazaProfesora.getInstance().getProfessors();
	}
    
    public Professor getProfesor(int rowIndex) {
		Professor p=BazaProfesora.getInstance().getProfessorByRow(rowIndex);		
		return p;
	}
    
    public void addProfessor(Professor pr){
        BazaProfesora.getInstance().addProfessor(pr);
        ProfessorsTable.getInstance().refreshTable();
    }
    
    public void deleteProfessor(int row){
        if(row <0){
            return;
        }
        Professor Professor = BazaProfesora.getInstance().getProfessorByRow(row);
        BazaProfesora.getInstance().deleteProfessor(Professor);
        //referencijalna zavisnost sa predmetom na kojem predaje i sa listom trazenih
        BazaProfesora.getInstance().deleteSearched(Professor);
       // BazaProfesora.getInstance().deleteProfFromSubject(Professor);
        //sa katedrama
        BazaKatedri.getInstance().deleteProf(Professor.getIdNumber());
        ProfessorsTable.getInstance().refreshTable();
    }
    
    public Professor getProfessorById(String id){
        Professor Professor = BazaProfesora.getInstance().getProfessorById(id);
        return Professor;
    }
    
    public void editProfessor(Professor Professor,Professor noviProf){
    	BazaProfesora.getInstance().editProfessor(Professor,noviProf);
        ProfessorsTable.getInstance().refreshTable();
    }
    
    public void AddSubjectForProfessor(Professor pro,Subject pre) {
		
		BazaProfesora.getInstance().addSubjectForProf(pro,pre);
	}

}
