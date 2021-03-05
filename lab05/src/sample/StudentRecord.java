package sample;

public class StudentRecord {
    private String stuId;
    private float midterm;
    private float assignments;
    private float finalExam;
    private float finalMark;
    private String letterGrade;

    public StudentRecord(String stuId, float assignments, float midterm, float finalExam){
        this.stuId = stuId;
        this.assignments = assignments;
        this.midterm = midterm;
        this.finalExam = finalExam;
        finalMark = (float) ((0.20*this.assignments) + (0.30*this.midterm) + (0.50*this.finalExam));

        if(finalMark >= 0 && finalMark <= 49) { letterGrade = "F"; }
        else if(finalMark >= 50 && finalMark <= 59){ letterGrade = "D"; }
        else if(finalMark >= 60 && finalMark <= 69){ letterGrade = "C"; }
        else if(finalMark >= 70 && finalMark<= 79){ letterGrade = "B"; }
        else if(finalMark >= 80 && finalMark <= 100){ letterGrade = "A"; }
    }


    public String getStuId() { return this.stuId; }

    public float getMidterm() { return this.midterm; }

    public float getAssignments() { return this.assignments; }

    public float getFinalExam() { return this.finalExam; }

    public float getFinalMark() { return finalMark; }

    public String getLetterGrade() { return letterGrade; }

}
