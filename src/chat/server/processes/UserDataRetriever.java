package chat.server.processes;

import chat.UserCredentials;
import chat.client.message.Message;
import chat.server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: skoW7
 * Date: 3/4/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserDataRetriever extends ServerProcess {
    private Socket sock;

    public UserDataRetriever(Socket sock, UserCredentials user) {
        super(user);
        this.sock = sock;
    }

    public enum UserData {
        IDNumber,UserName,IPAddress;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());

            Connection con = DriverManager.getConnection(Server.getServer().getDbUrl());
            Statement stmt = con.createStatement();

            Message msg = (Message)in.readObject();
            String data = msg.getMessageText();

            ResultSet rs;

            switch (msg.getDataType()) {
                case IDNumber:
                    rs = stmt.executeQuery("SELECT U.uid FROM User U WHERE U.username = '"+data+"'");
                    out.writeInt(rs.getInt("U.uid"));
                    break;
                case UserName:
                    rs = stmt.executeQuery("SELECT U.username FROM User U WHERE U.uid = '"+data+"'");
                    out.writeUTF(rs.getString("U.username"));
                    break;
                case IPAddress:
                    rs = stmt.executeQuery("SELECT L.ipaddress FROM Login L, User U WHERE U.uid = L.uid AND L.success = true AND U.username = '"+data+"' ORDER BY L.time DESC");
                    out.writeUTF(rs.getString("L.ipaddress"));
                    break;
            }


        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
