package application;

import entities.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        String path = "c:\\temp\\in.txt";
        System.out.println("Enter full file path: " + path);

        try(BufferedReader br = new BufferedReader(new FileReader(path))) {

            List<Employee> list = new ArrayList<Employee>();

            String line = br.readLine();
            while (line != null){
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            System.out.print("Enter Salary: ");
            Double salary = sc.nextDouble();

            List<String> emails = list.stream().filter(x -> x.getSalary() > salary).map(x -> x.getEmail()).sorted().collect(Collectors.toList());

            System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary) + ":");
            emails.forEach(System.out::println);

            Double sum = list.stream().filter(x -> x.getName().charAt(0) == 'M').map(x -> x.getSalary()).reduce(0.0, (x,y) -> x + y);

            System.out.print("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

        } catch (IOException e){
            System.out.println("Error: "+ e.getMessage());
        }
        sc.close();
    }

}
