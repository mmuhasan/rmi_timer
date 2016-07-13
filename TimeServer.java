import java.util.*;

public interface TimeServer extends java.rmi.Remote{

	public Date getTime()
		throws java.rmi.RemoteException;
}
