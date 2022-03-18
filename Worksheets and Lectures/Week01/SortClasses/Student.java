public class Student 
{
    private String name;
    private int studentID;
    
    public Student(String pName, int pStudentID)
    {
        name = pName;
        studentID = pStudentID;
    }
    
    public Student(Student pStudent)
    {
        name = pStudent.getName();
        studentID = pStudent.getStudentID();
    }

    public Student()
    {
        name = "None";
        studentID = 00000000;
    }

    public String getName()
    {
        return this.name;
    }

    public int getStudentID()
    {
        return this.studentID;
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public void setStudentID(int newStudentID)
    {
        studentID = newStudentID;
    }
}
