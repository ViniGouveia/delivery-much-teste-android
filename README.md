<h1 align="start">Aplicação GitHublic.io</h1>
<p align="start">Aplicação em arquitetura VIPER, usando as melhores práticas e comunicação assíncrona com a API do GitHub. </p>

<h1 align="start">Sobre</h1>
<p align="start">O objetivo da aplicação é listar os repositórios públicos do GitHub, com opção de filtrar a lista através do campo de filtro e ao clicar no repositório desejado exibir detalhes do mesmo, dando além disso, a opção de visitar o perfil do dono do repositório.</p>

<h1 align="start">Como executar</h1>
<p align="start">Primeiramente, instale o Android Studio na sua máquina, depois siga os passos a seguir:</p>

```
- Através do terminal, clone este repositório;
$ git clone https://github.com/ViniGouveia/delivery-much-teste-android

- Abra o projeto no Android Studio;

- Aguarde a indexação do projeto;

- Por fim, execute o Run do projeto em um dispositivo físico ou virtual.
```
<h1 align="start">Explicação da arquitetura</h1>
<p align="start">Foi utilizada a arquitetura VIPER, sendo uma forma de usar a Clean Architecture, para estruturação do projeto. A arquitetura consiste na utilização das seguintes camadas e suas respectivas funções:</p>
<p align="start">-View: Camada visual e de interação com o usuário (telas);</p>
<p align="start">-Interactor: Camada das regras de negócio à nível de aplicação;</p>
<p align="start">-Presenter: Camada que transforma os dados para serem apresentados ou encaminhados para o interactor;</p>
<p align="start">-Entity: Objetos ou modelos utilizados no projeto;</p>
<p align="start">-Router: Camada responsável pelo fluxo de telas.</p>

<h1 align="start">Utilizações relevantes</h1>
<p align="start">Para facilitar o carregamento e apresentação de imagens no aplicativo foram utilizadas as bibliotecas Glide e CicleImageView, respectivamente.</p>
<p align="start">Para facilitar realizar a conexão com a API REST do GitHub e lidar com as comunicações assíncronas, foram usadas as bibliotecas Retrofit, RxJava e RxKotlin, respectivamente.</p>
<p align="start">A biblioteca Dagger foi utilizada para lidar com a injeção de dependência no aplicativo.</p>
<p align="start">Para implementação dos testes unitários foi utilizada a biblioteca Mockk.</p>
