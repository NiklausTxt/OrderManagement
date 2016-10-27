package com.orderManagement.util;

import java.io.InputStream;
import java.util.Scanner;

public class Console {
	 // private 保护不创建类的实例
    private static Scanner scanner;
    
    static {
        scanner = new Scanner(System.in);
    }

    private Console(InputStream is) {
        scanner = new Scanner(is);
    }

    public static String NEW_LINE = "\n";

    public static void print(String line) {
        System.out.print(line);
    }

    public static void println(Object object) {
        System.out.println(object);
    }
    
    public static void println(String line) {
        System.out.println(line);
    }

    public static String askUserInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (!"".equals(input)) {
                return input;
            }
            System.out.println("不合法输入");
        }
    }
    public static int askUserInputint(String prompt) {
    	int result = -1;
        while (true) {
           String out = askUserInput(prompt);
           try {
        	   result = Integer.parseInt(out);			
           } catch (Exception e) {
        	   println("请输入合法的数字");
           }
           if(result!=-1){
        	   break;
           }
        }
        return result;
    }
}
