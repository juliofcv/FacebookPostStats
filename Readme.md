# Facebook Video Stats

Conexión a facebook mediante Facebook Graph Api

## Ejecute la clase FacebookPostStats

### Tenga en cuenta las siguientes variables de la clase principal

## userPage
Acá se debe de especificar el usuario de la página de Facebook
```
private static final String userPage = "USER_PAGE";
```

## urlPage
Es la URL final de acceso a la página en conjunto con el ## userPage
```
private static final String urlPage = "https://www.facebook.com/"+userPage+"/";
```
     
## API_VERSION
Versión de la API de facebook, corroborar documentación oficial
```
private static final String API_VERSION = "v3.2";    
```

## ACCESS_TOKEN
Token de acceso a la página, revise los accessos de aplicación o utilice un token de usuario en el [Explorador de la API Graph](https://developers.facebook.com/tools/explorer/)
```
private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
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

## SFTPConf

En esta clase puede configurar una conexión SFTP par que el reporte sea subido directamente a un servidor
