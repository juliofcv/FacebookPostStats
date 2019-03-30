# Facebook Video Stats #

Conexión a facebook mediante Facebook Graph Api

##Ejecute la clase FacebookPostStats##

######Tenga en cuenta las siguientes variables de la clase principal######

##userPage##
>private static final String userPage = "USER_PAGE";
>Acá se debe de especificar el usuario de la página de Facebook

##urlPage##
>private static final String urlPage = "https://www.facebook.com/"+userPage+"/";
>Es la URL final de acceso a la página en conjunto con el ##userPage##
     
##API_VERSION##
>private static final String API_VERSION = "v3.2";    
>Versión de la API de facebook, corroborar documentación oficial

##ACCESS_TOKEN##
>private static final String ACCESS_TOKEN = "ACCESS_TOKEN";
>Token de acceso a la página, revise los accessos de aplicación o utilice un token de usuario en el [Explorador de la API Graph] (https://developers.facebook.com/tools/explorer/)

##localFilePath##
>private static final String localFilePath = "X:\\";
>Ruta local donde desea guardar el reporte en HTML
     
##publish##
>private static final boolean publish = true;
>Valor booleano para indiciar si desea publicar el reporte mediante conexión SFTP, si es true configure SFTPConf

##SFTPConf##

En esta clase puede configurar una conexión SFTP par que el reporte sea subido directamente a un servidor
