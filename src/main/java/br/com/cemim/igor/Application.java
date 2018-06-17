package br.com.cemim.igor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Stream;

import br.com.cemim.igor.classes.Carro;
import br.com.cemim.igor.classes.Placa;
import br.com.cemim.igor.factory.ConnectionFactory;

public class Application {

	public static Connection connection;

	public static void main(String[] args) {
		try (Connection connection = new ConnectionFactory().create()) {
			Application.connection = connection;
			Application.menu();
		} catch (ClassNotFoundException e) {
			System.out.println("Não foi possível localizar o conector ao banco MySQL.");
		} catch (SQLException e) {
			System.out.println("Não foi possível conectar-se ao banco de dados.");
		}
	}

	public static void menu() {
		int opcao = 0;
		Scanner scanner = new Scanner(System.in);
		Application app = new Application();

		while (opcao != 5) {

			System.out.println("CARROS");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Listar");
			System.out.println("3 - Excluir");
			System.out.println("4 - Pesquisar");
			System.out.println("5 - Sair");
			System.out.println("Informe a opção:");
			opcao = scanner.nextInt();

			switch (opcao) {
				case 1:
					app.cadastrarCarro(scanner);
					break;

				case 2:
					app.listarCarros();
					break;

				case 3:
					app.excluirCarroPorPlaca(scanner);
					break;

				case 4:
					app.pesquisarCarroPorAno(scanner);
					break;

				case 5:
					System.out.println("Saindo...");
					break;

				default:
					System.out.println("Opção inválida.");
			}
		}
	}

	public void cadastrarCarro(Scanner scanner) {
		System.out.println("* Carro *");
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

		placa.insert();
		carro.insert();
    }

    public void listarCarros() {
		System.out.println("* Listar Carros *");

		Carro carro = new Carro();
		for (Carro carroAtual : carro.listAll()) {
			System.out.println(carroAtual);
		}
    }

    public void excluirCarroPorPlaca(Scanner scanner) {
		System.out.println("* Excluir Carro Por Placa *");

		System.out.println("Informe as letras:");
		String letras = scanner.next();
		System.out.println("Informe os números:");
		String numeros = scanner.next();

		Placa placa = new Placa();
		placa.setLetras(letras);
		placa.setNumeros(numeros);

		Carro carro = new Carro();
		carro.setPlaca(placa);

		carro.delete();
		placa.delete();
    }

    public void pesquisarCarroPorAno(Scanner scanner) {
		System.out.println("* Pesquisar Carro Por Ano *");

		System.out.println("Informe o ano:");
		int ano = scanner.nextInt();

		Stream<Carro> carros = new Carro().listAll().stream();
		Predicate<Carro> filtro = carroAtual -> carroAtual.getAno() == ano;
		long resultados = carros.filter(filtro).count();
		if (resultados > 0) {
			carros
				.filter(filtro)
				.forEach(carroAtual -> System.out.println(carroAtual));
		} else {
			System.out.println("Nenhum carro encontrado.");
		}
    }

}
