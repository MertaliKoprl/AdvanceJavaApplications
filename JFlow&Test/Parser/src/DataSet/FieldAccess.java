package DataSet;

import java.util.Scanner;

public class FieldAccess {


    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int a =scn.nextInt();

        DummyObject db1= new DummyObject();

        db1.m2();

        System.out.println(a);



    }
}
