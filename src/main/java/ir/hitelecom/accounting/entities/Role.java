package ir.hitelecom.accounting.entities;

import java.util.HashMap;
import java.util.Map;

public enum Role {
    //**_RO(XX)LE_**
    SHXXOP_ADXXMIN("ROLE_SHXXOP_ADXXMIN", "ROLE_SHXXOP_ADXXMIN"),   //modire kol
    STOCK_MANAGER("STOCK_MANAGER", "STOCK_MANAGER"),       //modire anbar
    STOCK_EMPLOYEE("STOCK_EMPLOYEE", "STOCK_EMPLOYEE"),     //anbardar
    CASHIER("CASHIER", "CASHIER"),              //sandoghdar
    INTERNAL_MANAGER("INTERNAL_MANAGER", "INTERNAL_MANAGER"),  //modire dakheli
    ADMIN("ADMIN", "ADMIN"),         //admin
    TRANSFER("TRANSFER", "TRANSFER"),     //haml o naghl
    ACCOUNTANT("ACCOUNTANT", "ACCOUNTANT"),  //hesabdar
    AUDITOR("AUDITOR", "AUDITOR"),  //hesabres
    SELLER("SELLER", "SELLER"); //foroshande

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
