package ir.hitelecom.accounting.entities;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    //**_RO(XX)LE_**
    SHXXOP_ADXXMIN("ROLE_SHXXOP_ADXXMIN", "Boba_Fett");

    private final String server;
    private final String client;

    private static final Map<String,String> serverMap;
    private static final Map<String,String> clientMap;

    static {
        serverMap = new HashMap<>();
        clientMap = new HashMap<>();
        for (Role v : Role.values()) {
            serverMap.put(v.client, v.server);
            clientMap.put(v.server, v.client);
        }
    }

    Role(String server, String client) {
        this.server = server;
        this.client = client;
    }

    public static String findServerRole(String client) {
        return serverMap.get(client);
    }

    public static String findClientRole(String server) {
        return clientMap.get(server);
    }

    public String getServer() {
        return server;
    }

    public String getClient() {
        return client;
    }

}
