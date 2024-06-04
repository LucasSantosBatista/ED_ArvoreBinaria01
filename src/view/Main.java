package view;

import model.ArvoreInt;

public class Main {

	public static void main(String[] args) {
		int[] vet = { 108, 130, 127, 10, 0, 13, 131, 184, 26, 2, 14, 158, 144, 69, 79, 111 };

		ArvoreInt arvore = new ArvoreInt();

		for (int i : vet) {
			arvore.insert(i);
		}

		try {
			System.out.print("Pré-ordem: \t");
			arvore.prefixSearch();
			System.out.print("\nEm ordem: \t");
			arvore.infixSearch();
			System.out.print("\nPós-ordem: \t");
			arvore.postfixSearch();

			System.out.println("\n");
			arvore.search(158);

			arvore.remove(144);
			System.out.println("\nRemovido: " + 144);
			arvore.remove(69);
			System.out.println("Removido: " + 69);
			arvore.remove(127);
			System.out.println("Removido: " + 127);
			arvore.remove(10);
			System.out.println("Removido: " + 10);
			arvore.remove(108);
			System.out.println("Removido: " + 108);

			System.out.print("\nEm ordem: \t");
			arvore.infixSearch();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
