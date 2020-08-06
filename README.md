# springboot-api

Gerando build:

mvn clean install -DskipTests

Executando em linha de comando:

java -jar -Dspring.profiles.active=postgres <nome do jar>
  
Rodando imagem dockerizada do postgresql:

docker run -d -p 5432:5432 -e POSTGRES_PASSWORD=yssy yssy/postgresql

Rodando imagem dockerizada:

docker run -d -p 8888:8888 yssy/springboot-api


 
