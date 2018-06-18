# Trabalho de JDBC
Trabalho de JDBC para a disciplina de Programação Web I do IFRS.

## Definições do trabalho
1) Crie as classes POJO Carro e Placa.
2) Agora, crie as classes DAO correspondentes, bem como as enumerações para os SQLs das duas classes. Nessas classes crie todos os métodos definidos pela interface GenericDAO.
3) Nas classes DAO faça o tratamento das exceções.
4) Inclua nas classes Carro e Placa os métodos que chamam os métodos dos DAOs (insert, listAll, delete, update e findById).
5) Para fazer o método listAll() use um conjunto ordenado, pois os carros mais recentes devem aparecer no topo do resultado obtido.
6) Todas as consultas devem ser definidas utilizando enum.
7) Crie o banco de dados pw1_trabalho para armazenar os dados dos objetos.
8) Monte um menu que permite
    1) Cadastrar carro
    2) Listar todos os carros
    3) Excluir carro usando a placa
    4) Pesquisar carro usando o ano
    5) Sair do sistema
