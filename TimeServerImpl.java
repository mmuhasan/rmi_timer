import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class TimeServerImpl extends UnicastRemoteObject implements TimeServer{

	protected TimeServerImpl() throws RemoteException {
		System.out.println("Initializing Time Server");
	}

	@Override
	public Date getTime() throws RemoteException {
		return new java.util.Date();
	}
	
	public static void main(String arg[])
	{
		System.setProperty("java.security.policy","file:./java.policy");
		
		System.setSecurityManager(
				new RMISecurityManager()
				);
		try{
			TimeServerImpl TSI = new TimeServerImpl();
			Naming.rebind("TimeServer", TSI);
			System.out.println("Registerd with registry");
			
		}
		catch (RemoteException e){
			System.out.println("URL Error"+e);
		}
		catch (java.net.MalformedURLException e){
			System.out.println("URL Error"+e);
		}
	}
}