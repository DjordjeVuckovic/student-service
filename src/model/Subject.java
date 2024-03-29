package model;

import java.io.Serializable;
import java.util.ArrayList;



public class Subject implements Serializable{
		private static final long serialVersionUID =  2906642554793891389L;
		private String idS;
		private String nameSub;
		private Semester semester;
		private YearofStudy yearOfStudy;
		private Professor professor;
		private int pointsESPB;

		private ArrayList<Student> passed = new ArrayList<>();
		private ArrayList<Student> failed = new ArrayList<>();

		private ArrayList<Professor> predmeti = new ArrayList<Professor>();



	public Subject(){

	}


	public Subject(String idS, String nameSub, Semester semester, YearofStudy yearOfStudy, Professor professor, int pointsESPB) {
		this.idS = idS;
		this.nameSub = nameSub;
		this.semester = semester;
		this.yearOfStudy = yearOfStudy;
		this.professor = professor;
		this.pointsESPB = pointsESPB;
	}



	
	public Subject(String id, String nameSub, Semester semester, YearofStudy yearOfStudy, int pointsESPB, Professor professor) {

		this.idS = id;
		this.nameSub = nameSub;
		this.semester = semester;
		this.yearOfStudy = yearOfStudy;
		this.pointsESPB = pointsESPB;
		this.professor = professor;
	}
	
	
	public Subject(String id, String nameSub, Semester semester, YearofStudy yearOfStudy, int pointsESPB) { this.idS = id; this.nameSub = nameSub;
	  this.semester = semester;
	  this.yearOfStudy = yearOfStudy;
	  this.pointsESPB =pointsESPB;
	}
	 

	public String getIdS() {
		return idS;
	}

	public void setIdS(String idS) {
		this.idS = idS;
	}

	public String getNameSub() {
		return nameSub;
	}

	public void setNameSub(String nameSub) {
		this.nameSub = nameSub;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public YearofStudy getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(YearofStudy yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public int getESPBpoints() {
		return pointsESPB;
	}

	public void setESPBpoints(int ESPBpoints) {
		this.pointsESPB = ESPBpoints;
	}

	public ArrayList<Student> getPassed() {
		return passed;
	}

	public void setPassed(ArrayList<Student> passed) {
		this.passed = passed;
	}

	public ArrayList<Student> getFailed() {
		return failed;
	}

	public void setFailed(ArrayList<Student> failed) {
		this.failed = failed;
	}
	
	public ArrayList<Professor> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(ArrayList<Professor> predmeti) {
		this.predmeti = predmeti;
	}



}
