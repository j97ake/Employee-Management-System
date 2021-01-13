import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import com.alpha.prts.prtsDb;

public class prtsLogin1
{ 				
	public static void main(String args[])
	{
		String username="",password="";
		int loginCount=0;
		System.out.println();
		System.out.println("**********Welcome to Professional Recruitment Tracking System**********");
		System.out.println();
		
		MainFrame main_obj;
		prtsDb db_obj=new prtsDb();
		while(loginCount<3){
			try{
				BufferedReader br=new BufferedReader(new InputStreamReader(System.in));		
				System.out.print("Enter username :");
				username=br.readLine();
				System.out.print("Enter password :");
				password=br.readLine();
				System.out.println();
			}
			catch(Exception e){
				System.out.println("prts1 class exception"+e);
			}
			String status=db_obj.validateUser(username,password);
			if(status.equals("admin")){
				//System.out.println("Admin login");
				main_obj =new MainFrame();
				main_obj.init(status);
				loginCount=3;
			}
			else if(status.equals("manager")){
				//System.out.println("Manager login");
				main_obj =new MainFrame();
				main_obj.init(status);	
				loginCount=3;
			}					
			else if(status.equals("operator")){
				//System.out.println("operator login");
				main_obj =new MainFrame();
				main_obj.init(status);	
				loginCount=3;
			}
			else if(status.equals("invalid")){
	
				loginCount++;
				if(loginCount>2){
					System.out.println("\t\tUnauthorised user-Closing application");
				}
				else{
					System.out.println("\t\tEither username or password or both are wrong-Try Again");
					System.out.println();
				}
			}
		}
	}
}
			