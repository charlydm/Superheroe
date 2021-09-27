### Bienvenidos al repositorio Súper héroes para W2M
**Descripción:**

API que permite hacer mantenimiento de súper héroes.

Este mantenimiento debe permitir:
- Consultar todos los súper héroes.
- Consultar un único súper héroe por id.
- Consultar todos los súper héroes que contienen, en su nombre, el valor de un parámetro enviado en la petición. Por ejemplo, si enviamos "man" devolverá "Spiderman", "Superman", "Manolito el fuerte", etc.
- Modificar un súper héroe.
- Eliminar un súper héroe.

**Instalación:**
- Debe tener instalado la versión de java 11 y [Maven](https://maven.apache.org/ "maven")
- Ejecutar el siguiente comando en la raíz del proyecto:

```bash
mvn install
```
**Uso**
- Ejecutar el siguiente comando en la raíz del proyecto ..\superheroe\target: 
Ej: C:\workspace\superheroe\target>java -jar superheroe-1.jar

```bash
java -jar superheroe-1.jar
```
- Luego de ejecutar el camando anterior, la api se encontrara desplegada en el siguiente swagger: Ej: "http://localhost:8080/swagger-ui/index.html" click [Aquí](http://localhost:8080/swagger-ui/index.html "Aquí")

- authorize en el siguiente control: auth-controller 
- Uri: /auth​/login

![](https://github.com/charlydm/Superheroe/blob/master/images/uri_login.png)

	{"password": "12345","user": "admin"}
	
```bash
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn0seyJhdXRob3JpdHkiOiJST0xFX01BTkFHRVIifSx7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiaWF0IjoxNjMyNzE0OTg5LCJleHAiOjE2MzI3MTg1ODl9.u6UxzSYJuCI3RKaf0OtlvV-m8gx5zS7_-tdjRbdCjeMyqDKGoi0seL1YTbEy2uSJq-VVo48yA8tcl4BjVEXCNA",
  "bearer": "Bearer",
  "userName": "admin",
  "authorities": [
    {
      "authority": "ROLE_ADMIN"
    },
    {
      "authority": "ROLE_MANAGER"
    },
    {
      "authority": "ROLE_USER"
    }
  ]
}
```

**Available authorizations:**

- **Value:** Bearer + Token

**Servicios:**

------------

Superheroe Controller


GET
​/v1​/superheroes

POST
​/v1​/superheroes

GET
​/v1​/superheroes​/{id}

PUT
​/v1​/superheroes​/{id}

DELETE
​/v1​/superheroes​/{id}

GET
​/v1​/superheroes​/search

GET
​/v1​/superheroes​/view​/order




