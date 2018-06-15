package br.com.cemim.igor.sql;

public enum CarroSql {
    INSERT("insert into carro(ano, modelo, montadora, placa) values (?, ?, ?, ?)"), 
    UPDATE("update carro set ano = ?, modelo = ?, montadora = ?, placa = ? where id = ?"),
    DELETE("delete from carro where id = ?"),
    FIND_BY_ID("select * from carro where id = ?"),
    LIST_ALL("select * from carro");
    
    private final String sql;

    CarroSql(String sql){
        this.sql = sql; 
    }

    public String getSql() {
        return sql;
    }
}
