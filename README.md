# desafio_v1

#### curl para adicionar arquivo xml
```
curl --location 'http://localhost:8080/api/agente' \
--form 'file=@"/path/to/file"'
```

#### curl para consultar por regiao
```
curl --location 'http://localhost:8080/api/regiao?sigla=N'
```

#### Banco de dados utilizado Postgres
```
  datasource:
    url: ${postgres.url:jdbc:postgresql://localhost:5433/agente-db} 
    username: ${postgres.username:postgres} 
    password: ${postgres.password:postgres} 
```

#### Evidencias de Teste
**Upload de arquivo xml**

![image](https://github.com/mauritak/desafio_v1/assets/8314016/cb69772d-e97a-4b8c-b143-127ad1e1a495)

***Loader***
![image](https://github.com/mauritak/desafio_v1/assets/8314016/d3e7f2e3-6ac3-463a-9827-c3cefaffc2e8)

***Xml processado no back-end***
![image](https://github.com/mauritak/desafio_v1/assets/8314016/d5f6eace-495b-4c2a-9d9d-79f799af4c30)

***Impressao na saída padrão (System.out) dos códigos de agentes***
![image](https://github.com/mauritak/desafio_v1/assets/8314016/5cde9ac7-4ce0-4047-ba58-c3d53beb846a)

***Banco de dados***
![image](https://github.com/mauritak/desafio_v1/assets/8314016/7f3951c9-dac4-4691-a22f-4f701334351e)



