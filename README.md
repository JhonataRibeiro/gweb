# gweb

O projeto é um crud de usuários e seus documentos. O usuário pode ter vários documentos cadastrados (CNH, identidade, Carteira de trabalho). É possível excluir, cadastrar novos e filtrar os documentos.

As seguintes tecnologias foram utilizadas:

Backend:

Java8
Spring boot
JPA com hibernate
e MariaDB (Mysql versão community).
Utilizei a biblioteca Orika para exemplificar o pattern DTO. Também utilizei o pattern MVC e a biblioteca Lombok para deixar o código mais enxuto.

Para executa:
	=> Instalar as dependências do maven.
	=> instalar o banco com configuração padrão ( As configurações estão no application.properties)
	=> Criar um database chamado [usuarios].
	=> As tabelas serão criadas automaticamente via DDL.
