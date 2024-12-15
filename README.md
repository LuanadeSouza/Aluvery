# Aluvery App

Este é o projeto **Aluvery**, um aplicativo de estudo desenvolvido em Kotlin com Jetpack Compose. O objetivo deste projeto é explorar os conceitos de composição no Android, incluindo a criação de interfaces dinâmicas e reutilizáveis para exibição de produtos.

---

## 🚀 Funcionalidades

- **Busca de Produtos**: Permite pesquisar produtos por nome ou descrição.
- **Tela Principal**: Exibição de seções de produtos categorizados, como "Promoções", "Doces" e "Bebidas".
- **Itens de Produto**: Layout dinâmico para exibir nome, preço, imagem e descrição de cada produto.
- **Tema Personalizado**: Utilização de cores personalizadas e suporte a tema claro e escuro.

---

## 🛠️ Tecnologias Utilizadas

- **Kotlin**: Linguagem principal.
- **Jetpack Compose**: Framework declarativo para construção de interfaces no Android.
- **Material Design 3**: Implementação de componentes modernos e acessíveis.
- **Coil**: Biblioteca para carregamento de imagens assíncronas.
- **Extensões Kotlin**: Para formatação de moeda em padrão brasileiro.

---

## 🔧 Estrutura do Projeto

### 1. **MainActivity**
- Ponto de entrada do aplicativo.
- Configuração do tema e inicialização da tela principal.

### 2. **HomeScreen**
- Tela principal que exibe as seções de produtos e a barra de pesquisa.
- Implementação de scroll vertical e horizontal para navegação entre os produtos.

### 3. **Components**
- **ProductItem**: Componente individual para exibir informações de um produto com imagem e preço.
- **CardProductItem**: Componente alternativo para exibir produtos com descrição detalhada.
- **ProductsSection**: Componente para agrupar itens de uma categoria de produtos.
- **SearchTextField**: Campo de texto com suporte a busca dinâmica.

### 4. **Model**
- **Product**: Classe representando os dados do produto com nome, preço, imagem e descrição.

### 5. **SampleData**
- Dados mockados para exibição de produtos, incluindo URLs de imagens e descrições.

### 6. **Extensions**
- Método de extensão para formatar preços no padrão brasileiro (exemplo: R$ 12,99).

### 7. **Theme**
- Configuração de temas claro e escuro com Material Design 3.
- Definição de cores e formas personalizadas.

---

## 🎨 Capturas de Tela

### Tela Principal
Exibição de seções de produtos com scroll vertical e horizontal.

<img src="https://github.com/user-attachments/assets/67ff3425-e5e2-4d9e-9f66-c806038c235f" alt="Screenshot_20241212_191925" width="300">         

<img src="https://github.com/user-attachments/assets/2fd20d4b-7fea-4887-90fb-686278477ff6" alt="Screenshot_20241212_191957" width="300">

---

## 💻 Como Executar

### Pré-requisitos
1. **Android Studio** instalado.
2. Configuração mínima: Android SDK 21+.

### Passos
1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/aluvery.git
   ```
2. Abra o projeto no Android Studio.
3. Execute o app em um emulador ou dispositivo físico.

---

## 📚 Aprendizados

- Construção de layouts com **Jetpack Compose**.
- Gerenciamento de estados com `remember` e `mutableStateOf`.
- Criação de componentes reutilizáveis com boas práticas de composição.
- Implementação de temas utilizando **MaterialTheme**.
- Utilização de bibliotecas para carregamento de imagens como **Coil**.
- Filtragem dinâmica de listas com base em entradas do usuário.

---

## 🔮 Melhorias Futuras

- Integração com uma API para obter produtos dinamicamente.
- Adicionar navegação entre telas.
- Implementar funcionalidade de carrinho de compras.
- Melhorar a acessibilidade do aplicativo.

---

## 🧑‍💻 Autor

Desenvolvido por **Luana Dev** como parte de um estudo em Android com Kotlin e Jetpack Compose.

---

## 📝 Licença

Este projeto é para fins educacionais e está sob licença [MIT](https://opensource.org/licenses/MIT).
