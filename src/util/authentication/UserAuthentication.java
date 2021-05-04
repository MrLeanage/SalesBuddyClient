package util.authentication;

import model.User;
import services.rmiService.RMIClient;
import util.utility.DataEncryption;

import javax.management.remote.rmi.RMIServer;
import java.rmi.RemoteException;

public class UserAuthentication {
    private static User AuthenticatedUser = null;

    public static User getAuthenticatedUser() {
        return AuthenticatedUser;
    }

    public static void setAuthenticatedUser(User AuthenticatedUser) {
        UserAuthentication.AuthenticatedUser = AuthenticatedUser;
    }

    public static String authenticateUser(String userName, String password) {
        if (RMIClient.isServerUp()) {
            User user = null;
            try {
                user = RMIClient.rmiInterface.loadSpecificUser(userName);
            } catch (RemoteException remoteException) {
                remoteException.printStackTrace();
            }
            if (user == null) {
                return "Invalid User Email!!";
            } else if (!user.getuPassword().equals(DataEncryption.passwordEncryption(password))) {
                return "Invalid Password!!";
            } else {
                setAuthenticatedUser(user);
                return "true";
            }
        } else
            return "false";
    }
}
