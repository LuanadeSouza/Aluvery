# Aluvery App

Este é o projeto **Aluvery**, um aplicativo de estudo desenvolvido em Kotlin com Jetpack Compose. O objetivo deste projeto é explorar os conceitos de composição no Android, incluindo a criação de interfaces dinâmicas e reutilizáveis para exibição de produtos.

---

## 🚀 Funcionalidades

- **Tela Principal**: Exibição de seções de produtos categorizados, como "Promoções", "Doces" e "Bebidas".
- **Itens de Produto**: Layout dinâmico para exibir nome, preço, descrição e imagem de cada produto.
- **Barra de Busca**: Permite filtrar produtos pelo nome ou descrição.
- **Formulário de Produtos**: Interface para criação de novos produtos com pré-visualização da imagem.
- **Tema Personalizado**: Utilização de cores personalizadas e suporte a tema claro e escuro.
- **Feedbacks de Usuário**: Mensagens de sucesso e vibração ao salvar produtos.

---

## 🛠️ Tecnologias Utilizadas

- **Kotlin**: Linguagem principal.
- **Jetpack Compose**: Framework declarativo para construção de interfaces no Android.
- **Material Design 3**: Implementação de componentes modernos e acessíveis.
- **StateFlow**: Gerenciamento de estados reativos.
- **Coil**: Biblioteca para carregamento de imagens.
- **JUnit**: Testes unitários para validação de lógicas.
- **Mockito**: Mock de dependências para testes.

---

## 🔧 Estrutura do Projeto

### 1. **MainActivity**
- Ponto de entrada do aplicativo.
- Configuração do tema, navegação e inicialização das telas principais e formulário de produtos.

### 2. **HomeScreen**
- Tela principal que exibe as seções de produtos.
- Implementação de scroll vertical e barra de busca.

### 3. **Components**
- **ProductItem**: Componente individual para exibir informações de um produto.
- **ProductsSection**: Componente para agrupar itens de uma categoria de produtos.
- **SearchTextField**: Campo de busca com suporte a entradas dinâmicas.

### 4. **Formulário de Produtos**
- **ProductFormScreen**: Tela para criação de novos produtos com validação e feedbacks visuais.
- **ProductFormScreenViewModel**: Gerenciamento de estado para o formulário de produtos.

### 5. **DAO e Model**
- **ProductDao**: Simula um banco de dados em memória com fluxo reativo de produtos.
- **Product**: Classe representando os dados do produto com nome, preço, imagem e descrição.

### 6. **SampleData**
- Dados mockados para exibição inicial de produtos como hambúrguer, pizza e batata frita.

### 7. **Extensions**
- Método de extensão para formatar preços no padrão brasileiro (exemplo: R$ 12,99).

### 8. **Testes Unitários**
- Cobertura de testes para validação de lógicas nas classes **ProductDao**, **HomeScreenViewModel**, **ProductFormScreenViewModel**, e extensões Kotlin.

---

## 🎨 Capturas de Tela

### Tela Principal
Exibição de seções de produtos com scroll vertical.

<img src="https://github.com/user-attachments/assets/e2ee134c-f1f7-4dac-b760-935a7131d319" alt="Screenshot_20241215_153733" width="300">

<img src="https://github.com/user-attachments/assets/fe6f6576-5347-4534-8595-e1cdf229b9e1" alt="Screenshot_20241215_153720" width="300">

<img src="https://github.com/user-attachments/assets/ffc71392-ac6b-4ede-a398-5ba864e5d987" alt="Screenshot_20241212_202858" width="300">

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
- Gerenciamento de estados com `StateFlow` e `ViewModel`.
- Criação de componentes reutilizáveis com boas práticas de composição.
- Implementação de temas utilizando **MaterialTheme**.
- Integração de bibliotecas para carregamento de imagens.
- Escrita de testes unitários para validação de lógicas e fluxos de dados.

---

## 🔮 Melhorias Futuras

- Integração com uma API para obter produtos dinamicamente.
- Adicionar navegação entre telas para detalhes de produtos.
- Implementar funcionalidade de carrinho de compras.
- Criar um banco de dados local persistente com Room.

---

## 🧑‍💻 Autor

Desenvolvido por **Luana Dev** como parte de um estudo em Android com Kotlin e Jetpack Compose.

---

## 📝 Licença

Este projeto é para fins educacionais e está sob licença [MIT](https://opensource.org/licenses/MIT).
