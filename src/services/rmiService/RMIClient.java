package services.rmiService;

import java.rmi.Naming;

import util.utility.AlertPopUp;

public class RMIClient {
    public static RMIInterface rmiInterface;

    public boolean runClient() {
        try {
            rmiInterface = (RMIInterface) Naming.lookup("rmi://localhost/SalesBuddy");
            return true;
        } catch (Exception e) {
            AlertPopUp.failMessage("Server Connection Status", "Server Connection Failed.\n Please Contact Your Administrator!");
            return false;
        }
    }

    public static void clearRMISession() {
        rmiInterface = null;
    }

    public static boolean isServerUp() {
        return rmiInterface != null;
    }
}
