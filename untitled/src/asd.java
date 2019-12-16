public class asd {

    public static void main(String[] args) {
    String a ="ssn";
    String b ="courseId";
    String c="select * from ";
    String d="test1";


        StringBuilder sb1= new StringBuilder();

        String whereStatement= " where ";
        sb1.append(whereStatement);
        sb1.append(b+" =? and ");
        sb1.append(a+" =? ");

        System.out.println(c+d+sb1.toString());

    }
}
