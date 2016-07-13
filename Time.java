import java.rmi.*;
import java.util.*;

public class Time {
	public static void main(String arg[]){
		TimeServer TS = null;
		try{
			TS = (TimeServer) Naming.lookup("rmi://127.0.0.1:1099/TimeServer");
		}catch (NotBoundException e) {
			System.out.println("TimeServer was not found in registry");
			System.exit(0);
		} catch (java.net.MalformedURLException e) {
			System.out.println("URL error: " + e);
			System.exit(0);;
		} catch (RemoteException e) {
			System.out.println("Time error: " + e);
			System.exit(0);
		}
		
		Date remoteTime = null, localTime = null;
		while(true)
		{
			try{
				remoteTime = TS.getTime();
				localTime = new java.util.Date();
			}
			catch (RemoteException e) {
				System.out.println("Time error: " + e);
				System.exit(0);
			}
			
			if(remoteTime != null)
			{
				System.out.println("Remote time: "+remoteTime);
				System.out.println("Local time: "+localTime);
				try {
					Thread.sleep(1000);
				} catch (java.lang.InterruptedException e) {
					/* do nothing */
				}
			}
		}
	}
}
