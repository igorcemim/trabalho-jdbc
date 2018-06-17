package br.com.cemim.igor.sql;

public enum CarroSql {
    INSERT("insert into carro(ano, modelo, montadora, placa_id) values (?, ?, ?, ?)"),
    UPDATE("update carro set ano = ?, modelo = ?, montadora = ?, placa_id = ? where id = ?"),
    DELETE("delete from carro where placa_id = (select p.id from placa p where p.letras = ? and p.numeros = ?)"),
    FIND_BY_ID("select * from carro where id = ?"),
    LIST_ALL("select * from carro c inner join placa p on p.id = c.placa_id");

    private final String sql;

    CarroSql(String sql){
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
