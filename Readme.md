# Facebook Post Stats

![fbPostStats](https://i.imgur.com/NNVEQZX.png)

Conexión a Facebook mediante Graph Api para verificación de estadísticas de Posts de páginas, verifique hasta 28 parámetros estadísticos para medir el rendimiento de su página

## Autor
* [**Julio Chinchilla**](https://www.linkedin.com/in/juliofcv)

## Herramientas utilizadas
* JDK 12
* Google Charts
* Netbeans 11

# FacebookPostStats.java
Clase principal a ejecutar

### Tenga en cuenta las siguientes variables de la clase principal

## userPage
Acá se debe de especificar el usuario de la página de Facebook
```
private static final String userPage = "USER_PAGE";
```

## urlPage
Es la URL final de acceso a la página en conjunto con el userPage
```
private static final String urlPage = "https://www.facebook.com/"+userPage+"/";
```
     
## API_VERSION
Versión de la API de facebook, corroborar la versión a utilizar y la documentación oficial [Facebook Page Insights](https://developers.facebook.com/docs/graph-api/reference/insights) la versión se debe de especificar con el formato "vM.m" donde 'M' es la versión Mayor y 'm' es la versión menor, la inicial 'v' debe de colocarse
```
private static final String API_VERSION = "v3.2";    
```

## ACCESS_TOKEN
Token de acceso a la página, revise los accessos de aplicación o utilice un token de usuario en el [Explorador de la API Graph](https://developers.facebook.com/tools/explorer/)
```
private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
```

## startDate
Hora de inicio del reporte defido en tiempo internacional UTC
defina 06:00:00 para comenzar a las 00:00 horas del día II Hora de Guatemala
```
private static final String startDate  = "01-03-2019T06:00:00";
```

## endDate
Hora de finalización del reporte defido en tiempo internacional UTC
defina 05:59:59 para finalizar a las 23:59 horas Hora de Guatemala
```
private static final String endDate    = "30-03-2019T05:59:59";
```

## localFilePath
Ruta local donde desea guardar el reporte en HTML
```
private static final String localFilePath = "X:\\";
```
     
## publish
Valor booleano para indiciar si desea publicar el reporte mediante conexión SFTP, si es true configure SFTPConf
```
private static final boolean publish = true;
```

# Parameters.java
En esta clase puede agregar las metricas determinadas en la documentación de [Facebook Page Insights](https://developers.facebook.com/docs/graph-api/reference/insights)
```
metric.add("PARAMETER");
```
el script está optimizado para los siguientes parámetros dedicados a Post
```
post_impressions
post_impressions_unique
post_impressions_organic_unique
post_impressions_fan_unique
post_impressions_viral_unique
post_impressions_paid_unique
post_impressions_nonviral_unique
post_impressions_by_story_type_unique
post_impressions_fan_paid_unique
post_impressions_organic
post_impressions_fan
post_impressions_viral
post_impressions_paid
post_impressions_nonviral
post_impressions_by_story_type
post_impressions_fan_paid
post_reactions_like_total
post_reactions_love_total
post_reactions_wow_total
post_reactions_haha_total
post_reactions_sorry_total
post_reactions_anger_total
post_engaged_users
post_negative_feedback
post_negative_feedback_unique
post_engaged_fan
post_clicks
post_clicks_unique
```

# SFTPConf.java
En esta clase puede configurar una conexión SFTP par que el reporte sea subido directamente a un servidor

## server
Dirección del servidor SFTPT
```
public static final String server = "SERVER";
```

## port
Número de puerto de conexión por default 22
```
public static final int port = 22;
```

## login
login o usuario de conexión
```
public static final String login = "LOGIN";
```

## password
password de conexión
```
public static final String password = "PASSWORD";
```

## workdir
directorio de trabajo de la conexión
```
public static final String workdir = "WORK DIR"; 
```
