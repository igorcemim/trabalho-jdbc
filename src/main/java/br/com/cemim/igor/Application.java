package br.com.cemim.igor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Scanner;
import java.util.function.Predicate;

import br.com.cemim.igor.classes.Carro;
import br.com.cemim.igor.classes.Placa;
import br.com.cemim.igor.dao.GenericDAO;
import br.com.cemim.igor.exception.DatabaseConnectionException;
import br.com.cemim.igor.factory.ConnectionFactory;

/**
 * @author Igor Ferreira Cemim
 */
public class Application {

	public static Connection connection;
	private static final int OPCAO_SAIR = 6;

	public static void main(String[] args) {
		try (Connection newConnection = new ConnectionFactory().create()) {
			newConnection.setAutoCommit(false);
			connection = newConnection;
			menu();
		} catch (ClassNotFoundException e) {
			System.out.println("Não foi possível localizar o conector MySQL.");
		} catch (DatabaseConnectionException e) {
			System.out.println("Não foi possível conectar-se ao banco de dados.");
		} catch (SQLException e) {
			System.out.println("Não foi possível habilitar as transações.");
		}
	}

	public static void menu() {
		int opcao = 0;
		Scanner scanner = new Scanner(System.in);
		Application app = new Application();

		while (opcao != OPCAO_SAIR) {

			System.out.println("\nCARROS\n");
			System.out.println("1 - Cadastrar Carro");
			System.out.println("2 - Atualizar Carro");
			System.out.println("3 - Listar Carros");
			System.out.println("4 - Excluir Carro");
			System.out.println("5 - Pesquisar Carros");
			System.out.println("6 - Sair");
			System.out.println("Informe a opção:");
			opcao = scanner.nextInt();

			switch (opcao) {
				case 1:
					app.cadastrarCarro(scanner);
					break;

				case 2:
					app.atualizarCarro(scanner);
					break;

				case 3:
					app.listarCarros();
					break;

				case 4:
					app.excluirCarroPorPlaca(scanner);
					break;

				case 5:
					app.pesquisarCarroPorAno(scanner);
					break;

				case 6:
					System.out.println("\nSaindo...\n");
					break;

				default:
					System.out.println("\nOpção inválida.\n");
			}
		}
	}

	public void cadastrarCarro(Scanner scanner) {
		try {
			System.out.println("\n* Cadastrar Carro *\n");
			System.out.println("Informe o ano:");
			int ano = scanner.nextInt();
			System.out.println("Informe o modelo:");
			String modelo = scanner.next();
			System.out.println("Informe a montadora:");
			String montadora = scanner.next();

			System.out.println("* Placa *");
			System.out.println("Informe as letras:");
			String letras = scanner.next();
			System.out.println("Informe os números:");
			String numeros = scanner.next();

			Placa placa = new Placa();
			placa.setLetras(letras);
			placa.setNumeros(numeros);

			Carro carro = new Carro();
			carro.setAno(ano);
			carro.setModelo(modelo);
			carro.setMontadora(montadora);
			carro.setPlaca(placa);

			if (placa.insert() == GenericDAO.ERRO_OPERACAO) {
				throw new Exception("Ocorreu um erro ao inserir a placa.");
			}
			if (carro.insert() == GenericDAO.ERRO_OPERACAO) {
				throw new Exception("Ocorreu um erro ao inserir o carro.");
			}
			connection.commit();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar confirmar a transação.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void atualizarCarro(Scanner scanner) {
		try {
			int id = 0;
			System.out.println("Informe o código do carro:");
			id = scanner.nextInt();

			Carro carroModel = new Carro();
			carroModel.setId(id);
			Carro carro = carroModel.findByID();
			if (carro == null) {
				throw new Exception("Carro não encontrado.");
			}
			System.out.println(carro);

			System.out.println("\n* Atualizar Carro *\n");
			System.out.println("Informe o ano:");
			int ano = scanner.nextInt();
			System.out.println("Informe o modelo:");
			String modelo = scanner.next();
			System.out.println("Informe a montadora:");
			String montadora = scanner.next();

			System.out.println("* Placa *");
			System.out.println("Informe as letras:");
			String letras = scanner.next();
			System.out.println("Informe os números:");
			String numeros = scanner.next();

			carro.getPlaca().setLetras(letras);
			carro.getPlaca().setNumeros(numeros);

			carro.setAno(ano);
			carro.setModelo(modelo);
			carro.setMontadora(montadora);

			if (carro.getPlaca().update() == GenericDAO.ERRO_OPERACAO) {
				throw new Exception("Não foi possível atualizar a placa.");
			}
			if (carro.update() == GenericDAO.ERRO_OPERACAO) {
				throw new Exception("Não foi possível atualizar o carro.");
			}
			connection.commit();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar confirmar a transação.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }

    public void listarCarros() {
		System.out.println("\n* Listar Carros *\n");

		Carro carro = new Carro();
		Collection<Carro> carros = carro.listAll();

		if (carros.size() == 0) {
			System.out.println("Nenhum carro cadastrado.");
			return;
		}

		for (Carro carroAtual : carros) {
			System.out.println(carroAtual);
		}
    }

    public void excluirCarroPorPlaca(Scanner scanner) {
		System.out.println("\n* Excluir Carro *\n");

		System.out.println("Informe as letras:");
		String letras = scanner.next();
		System.out.println("Informe os números:");
		String numeros = scanner.next();

		Placa placa = new Placa();
		placa.setLetras(letras);
		placa.setNumeros(numeros);

		Carro carro = new Carro();
		carro.setPlaca(placa);

		try {
			if (carro.delete() == GenericDAO.ERRO_OPERACAO) {
				throw new Exception("O carro não foi encontrado.");
			}
			if (placa.delete() == GenericDAO.ERRO_OPERACAO) {
				throw new Exception("A placa não foi encontrada.");
			}
			System.out.println("O carro foi apagado.");
			System.out.println("A placa foi apagada.");

			connection.commit();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar confirmar a transação.");
		} catch (Exception e) {
			try {
				System.out.println(e.getMessage());
				connection.rollback();
			} catch (SQLException rollbackException) {
				System.out.println("Ocorreu um erro ao tentar cancelar a transação.");
			}
		}
    }

    public void pesquisarCarroPorAno(Scanner scanner) {
		System.out.println("\n* Pesquisar Carros *\n");

		System.out.println("Informe o ano:");
		int ano = scanner.nextInt();

		Collection<Carro> carros = new Carro().listAll();
		Predicate<Carro> filtro = carroAtual -> carroAtual.getAno() == ano;
		long quantidadeResultados = carros.stream().filter(filtro).count();
		if (quantidadeResultados > 0) {
			carros
				.stream()
				.filter(filtro)
				.forEach(carroAtual -> System.out.println(carroAtual));
		} else {
			System.out.println("Nenhum carro encontrado.");
		}
    }

}
