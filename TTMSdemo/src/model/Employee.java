package model;

import java.io.Serializable;

public class Employee implements Serializable {

    private int emp_id;
    private String emp_no;//工号
    private String emp_name;
    private String emp_tel_num;
    private String emp_addr;
    private String emp_email;
    private String emp_identity;

    public Employee(){

    }

    public Employee(String emp_no, String emp_name,
                    String emp_tel_num, String emp_addr, String emp_email,String emp_identity) {

        this.emp_no = emp_no;
        this.emp_name = emp_name;
        this.emp_tel_num = emp_tel_num;
        this.emp_addr = emp_addr;
        this.emp_email = emp_email;
        this.emp_identity = emp_identity;
    }





    public String getEmp_no() {
        return emp_no;
    }




    public void setEmp_no(String emp_no) {
        this.emp_no = emp_no;
    }




    public String getEmp_name() {
        return emp_name;
    }




    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }




    public String getEmp_tel_num() {
        return emp_tel_num;
    }




    public void setEmp_tel_num(String emp_tel_num) {
        this.emp_tel_num = emp_tel_num;
    }




    public String getEmp_addr() {
        return emp_addr;
    }




    public void setEmp_addr(String emp_addr) {
        this.emp_addr = emp_addr;
    }




    public String getEmp_email() {
        return emp_email;
    }




    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }





    public int getEmp_id() {
        return emp_id;
    }




    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }




    public String getEmp_identity() {
        return emp_identity;
    }

    public void setEmp_identity(String emp_identity) {
        this.emp_identity = emp_identity;
    }

    @Override
    public String toString() {
        return "Employee [emp_id=" + emp_id + ", emp_no=" + emp_no
                + ", emp_name=" + emp_name + ", emp_tel_num=" + emp_tel_num
                + ", emp_addr=" + emp_addr + ", emp_email=" + emp_email + "]";
    }








}
