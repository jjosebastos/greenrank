package br.com.greenrank.config;

final class DatabaseConfig {
    public DatabaseConfig() {
        throw new UnsupportedOperationException();
    }

    static String getUrl(){
        return "jdbc:oracle:thin@oracle.fiap.com.br:1521:ORCL";
    }
    static String getUser(){
        return "rm559221";
    }
    static String getPassword(){
        return "jn100800";
    }

}
