package Employee;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

class EmployeeDetails implements Serializable{
	int emp_no;
	String emp_email;
	String emp_name;
	String cmpny_name;
	int emp_salary;
	public EmployeeDetails(int emp_no, String emp_email, String emp_name, String cmpny_name, int emp_salary) {
		super();
		this.emp_no = emp_no;
		this.emp_email = emp_email;
		this.emp_name=emp_name;
		this.cmpny_name = cmpny_name;
		this.emp_salary = emp_salary;
	}
	@Override
	public String toString() {
		return "EmployeeDetails [emp_no=" + emp_no + ", emp_email=" + emp_email + ", emp_name=" + emp_name
				+ ", cmpny_name=" + cmpny_name + ", emp_salary=" + emp_salary + "]";
	}
}
class EmployeeCRUD{
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		int option=-1;
		Scanner sc=new Scanner(System.in);
		Scanner sc1=new Scanner(System.in);
		File file=new File("employee.txt");
		ArrayList<EmployeeDetails> arrayList=new ArrayList<EmployeeDetails>();
		ObjectOutputStream objectOutputStream=null;
		ObjectInputStream objectinputStream=null;
		ListIterator li=null;
		
		if(file.isFile()) {
			objectinputStream=new ObjectInputStream(new FileInputStream(file));
			arrayList=(ArrayList<EmployeeDetails>)objectinputStream.readObject();
			objectinputStream.close();
		}
		do {
			System.out.println("1.INSERT");
			System.out.println("2.DISPLAY");
			System.out.println("3.SEARCH");
			System.out.println("4.DELETE");
			System.out.println("5.UPDATE");
			System.out.println("6.EXIT");
			System.out.println("Enter your option:");
			option=sc.nextInt();
			switch(option) {
			case 1:
				System.out.println("Enter how many employee you want to insert");
				int n=sc.nextInt();
				
				for(int i=0;i<n;i++) {
				System.out.println("Enter the employee no ");
				int emp_no=sc.nextInt();
				
				System.out.println("Enter employee email");
				String emp_email=sc1.nextLine();
				
				System.out.println("Enter employee name");
				String emp_name=sc1.nextLine();
				
				System.out.println("Enter company name");
				String cmpny_name=sc1.nextLine();
				
				System.out.println("Enter employee salary");
				int emp_salary=sc.nextInt();
				
				
				arrayList.add(new EmployeeDetails(emp_no, emp_email, emp_name, cmpny_name, emp_salary));
				
				}
				objectOutputStream=new ObjectOutputStream(new FileOutputStream(file));
				objectOutputStream.writeObject(arrayList);
				objectOutputStream.close();
				break;
			case 2:
				li = arrayList.listIterator();
				while (li.hasNext())
					System.out.println(li.next());
				System.out.println(".............................");
				break;
			case 3:
				if (file.isFile()) {
					objectinputStream = new ObjectInputStream(new FileInputStream(file));
					arrayList = (ArrayList<EmployeeDetails>) objectinputStream.readObject();
					objectinputStream.close();
					boolean found = false;
					System.out.print("Enter emp_id to search:");
					int emp_id = sc.nextInt();
					System.out.print("...................................");
					li = arrayList.listIterator();
					while (li.hasNext()) {
						EmployeeDetails e = (EmployeeDetails) li.next();
						if (e.emp_no == emp_id) {
							System.out.println(e);
							found = true;
						}
					}
					if (!found)
						System.out.println("Record not found");
					System.out.println("...........................");
				} else {
					System.out.println("File not exists");
				}
				break;
			case 4:
				if (file.isFile()) {
					objectinputStream = new ObjectInputStream(new FileInputStream(file));
					arrayList = (ArrayList<EmployeeDetails>) objectinputStream.readObject();
					objectinputStream.close();
					boolean found = false;
					System.out.print("Enter emp_id to delete:");
					int emp_id = sc.nextInt();
					System.out.print("...................................");
					li = arrayList.listIterator();
					while (li.hasNext()) {
						EmployeeDetails e = (EmployeeDetails) li.next();
						if (e.emp_no == emp_id) {
							System.out.println(e);
							li.remove();
							found = true;
						}
					}
					if (found) {
						objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
						objectOutputStream.writeObject(arrayList);
						objectOutputStream.close();
						System.out.println("Record deleted successfully");
					} else {
						System.out.println("Record not found");
					}
					System.out.println("...........................");
				} else {
					System.out.println("File not exists");
				}
				break;
			case 5:
				if (file.isFile()) {
					objectinputStream = new ObjectInputStream(new FileInputStream(file));
					arrayList = (ArrayList<EmployeeDetails>) objectinputStream.readObject();
					objectinputStream.close();
					boolean found = false;
					System.out.print("Enter emp_id to update:");
					int emp_id = sc.nextInt();
					System.out.print("...................................");
					li = arrayList.listIterator();
					while (li.hasNext()) {
						EmployeeDetails e = (EmployeeDetails) li.next();
						if (e.emp_no == emp_id) {
							System.out.println("Enter new emp_no");
							int emp_no = sc.nextInt();

							System.out.println("Enter new emp_email");
							String emp_email = sc1.nextLine();

							System.out.println("Enter new emp_name");
							String emp_name = sc1.nextLine();

							System.out.println("Enter new cmpny_name");
							String cmpny_name = sc1.nextLine();
							
							System.out.println("Enter new emp_salary");
							int emp_salary=sc.nextInt();

							li.set(new EmployeeDetails(emp_no, emp_email, emp_name, cmpny_name, emp_salary));
							found = true;
						}
					}
					if (found) {
						objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
						objectOutputStream.writeObject(arrayList);
						objectOutputStream.close();
						System.out.println("Record updated successfully");
					} else {
						System.out.println("Record not found");
					}
					System.out.println("...........................");
				} else {
					System.out.println("File not exists");
				}
				break;
			}	
		}
		while(option !=0);
	}
	}

