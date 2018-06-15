package br.com.cemim.igor.sql;

public enum PlacaSql {
    INSERT("insert into placa(letras, numeros) values (?, ?)"), 
    UPDATE("update placa set letras = ?, numeros = ? where id = ?"),
    DELETE("delete from placa where id = ?"),
    FIND_BY_ID("select * from placa where id = ?"),
    LIST_ALL("select * from placa");
    
    private final String sql;

    PlacaSql(String sql){
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
