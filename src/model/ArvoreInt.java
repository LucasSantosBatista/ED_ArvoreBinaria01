package model;

public class ArvoreInt {

	private No raiz;

	public ArvoreInt() {
		raiz = null;
	}

	public void insert(int dado) {
		No no = new No();
		no.dado = dado;
		no.esquerda = null;
		no.direita = null;
		insertLeaf(no, raiz);
	}

	private void insertLeaf(No no, No raizSubArvore) {
		if (raiz == null) {
			raiz = no;
		} else {
			if (no.dado < raizSubArvore.dado) {
				if (raizSubArvore.esquerda == null) {
					raizSubArvore.esquerda = no;
				} else {
					insertLeaf(no, raizSubArvore.esquerda);
				}
			}
			if (no.dado > raizSubArvore.dado) {
				if (raizSubArvore.direita == null) {
					raizSubArvore.direita = no;
				} else {
					insertLeaf(no, raizSubArvore.direita);
				}
			}
		}
	}

	public void prefixSearch() throws Exception {
		prefix(raiz);
	}

	public void infixSearch() throws Exception {
		infix(raiz);
	}

	public void postfixSearch() throws Exception {
		postfix(raiz);
	}

	private void prefix(No raizSubArvore) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore Vazia");
		} else {
			System.out.print(raizSubArvore.dado + " ");
			if (raizSubArvore.esquerda != null) {
				prefix(raizSubArvore.esquerda);
			}
			if (raizSubArvore.direita != null) {
				prefix(raizSubArvore.direita);
			}
		}
	}

	private void infix(No raizSubArvore) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore Vazia");
		} else {

			if (raizSubArvore.esquerda != null) {
				prefix(raizSubArvore.esquerda);
			}
			System.out.print(raizSubArvore.dado + " ");
			if (raizSubArvore.direita != null) {
				prefix(raizSubArvore.direita);
			}
		}
	}

	private void postfix(No raizSubArvore) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore Vazia");
		} else {

			if (raizSubArvore.esquerda != null) {
				prefix(raizSubArvore.esquerda);
			}

			if (raizSubArvore.direita != null) {
				prefix(raizSubArvore.direita);
			}
			System.out.print(raizSubArvore.dado + " ");
		}
	}

	public void search(int valor) throws Exception {
		try {
			No no = nodeSearch(raiz, valor);
			int level = nodeLevel(raiz, valor);
			System.out.println("Dado: " + no.dado + "\tNível: " + level);
		} catch (Exception e) {
			throw new Exception("Valor não encontrado");
		}

	}

	private No nodeSearch(No raizSubArvore, int valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore Vazia");
		} else if (valor < raizSubArvore.dado) {
			return nodeSearch(raizSubArvore.esquerda, valor);
		} else if (valor > raizSubArvore.dado) {
			return nodeSearch(raizSubArvore.direita, valor);
		} else {
			return raizSubArvore;
		}
	}

	private int nodeLevel(No raizSubArvore, int valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore Vazia");
		} else if (valor < raizSubArvore.dado) {
			return 1 + nodeLevel(raizSubArvore.esquerda, valor);
		} else if (valor > raizSubArvore.dado) {
			return 1 + nodeLevel(raizSubArvore.direita, valor);
		} else {
			return 0;
		}
	}

	public void remove(int valor) throws Exception {
		try {
			removeChild(raiz, valor);
		} catch (Exception e) {
			throw new Exception("Valor não encontrado");
		}
	}

	private void removeChild(No raizSubArvore, int valor) throws Exception {
		No no = nodeSearch(raizSubArvore, valor);
		if (no != null) {
			No pai = nodeParent(null, raiz, no.dado);
			;
			if (no.esquerda != null && no.direita != null) {
				No noTroca = no.esquerda;
				while (noTroca.direita != null) {
					noTroca = noTroca.direita;
				}
				pai = nodeParent(null, raiz, noTroca.dado);
				no.dado = noTroca.dado;
				removeOneOrZeroLeaf(pai, noTroca);
			} else {
				removeOneOrZeroLeaf(pai, no);
			}
		} else {
			throw new Exception("Valor não encontrado");
		}

	}

	private No nodeParent(No parent, No raizSubArvore, int valor) throws Exception {
		if (raiz == null) {
			throw new Exception("Árvore Vazia");
		} else if (valor < raizSubArvore.dado) {
			return nodeParent(raizSubArvore, raizSubArvore.esquerda, valor);
		} else if (valor > raizSubArvore.dado) {
			return nodeParent(raizSubArvore, raizSubArvore.direita, valor);
		} else {
			if (parent == null) {
				return raiz;
			} else {
				return parent;
			}
		}
	}

	private void removeOneOrZeroLeaf(No pai, No no) {
		if (no.esquerda == null && no.direita == null) {
			change(pai, no, null);
		} else if (no.esquerda == null) {
			change(pai, no, no.direita);
		} else if (no.direita == null) {
			change(pai, no, no.esquerda);
		}

	}

	private void change(No pai, No no, No novoNo) {
		if (pai.esquerda != null && pai.esquerda.dado == no.dado) {
			pai.esquerda = novoNo;
		} else if (pai.direita.dado == no.dado) {
			pai.direita = novoNo;
		}

	}
}
