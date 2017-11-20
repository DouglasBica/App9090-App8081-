#executar via docker DockerTerminal
#docker build -t <nome> .
#docker build -t plataformabase .
#docker export plataformabase > latest.tar #exportar imagem
#docker run -p 8081:8081 plataformabase #executar imagem

FROM jeanblanchard/java:jdk-8

RUN mkdir /app

WORKDIR /app

COPY ./build/libs/plataformabase-*.jar /app/plataformabase.jar

EXPOSE 8081
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8 -Duser.country=BR -Duser.language=pt
CMD ["java", "-jar", "-Xmx512M", "-Xms512M", "plataformabase.jar"]