import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class DOMParseLecturaBOOK {
    public static void main(String[] args) {
         try {
            //**** 1. Obtiene el objeto doc que representa el documento en memoria
            //Define un objeto File para el archivo XML llamado books.xml.
            File inputFile = new File("books.xml");
            
            //Crea una instancia de DocumentBuilderFactory, que es la clase responsable de la construcción del documento XML.
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            
            //Se crea un DocumentBuilder, que será utilizado para analizar el archivo XML.
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            //El método parse() se utiliza para analizar el archivo XML y 
            //devolver un objeto Document que representa la estructura del documento XML en memoria.
            Document doc = dBuilder.parse(inputFile);
            
            //Este paso asegura que el documento esté en una forma consistente, 
            //eliminando nodos vacíos o innecesarios. Es un paso de "limpieza" del XML.
            //No es necesario pero es una buena práctica
            doc.getDocumentElement().normalize();

            //**** 2. Obtiene el nombre del elemento raíz del documento XML. Por ejemplo, 
            //si el XML tiene una estructura como <Catalogo>...</Catalogo>, entonces esto devolvería Catalogo.
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            //**** 3. Obtiene todos los nodos con el nombre de etiqueta Book dentro del XML, de la siguiente forma: 
            //Esto es importante ya que se asume que cada elemento Book contiene información sobre un libro.
            NodeList nList = doc.getElementsByTagName("Book");
            System.out.println("----------------------------");
            
            //**** 4. Se recorre la lista de nodos Book. La variable temp es el índice del libro actual.
            for (int temp = 0; temp < nList.getLength(); temp++) {
                //Recupera el nodo Book actual.
                Node nNode = nList.item(temp);

                //Imprime el nombre del nodo actual, que debería ser Book.
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                //Verifica si el nodo actual es un nodo de tipo ELEMENT_NODE, 
                //lo cual significa que es un nodo que contiene un elemento (en este caso, Book).
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    //Convierte el nodo a un Element, lo que permite acceder a sus atributos y subelementos.
                    Element eElement = (Element) nNode;

                    //Obtiene el atributo id del elemento Book.
                    System.out.println("ID : "+ eElement.getAttribute("id")); 
                    
                    //Accede al contenido del primer (y único) elemento Author dentro del elemento Book. 
                    //Lo mismo aplica para los elementos Title, Genre, Price, PublishDate, y Description.
                    System.out.println("Author : "+ eElement.getElementsByTagName("Author").item(0).getTextContent());
                    System.out.println("Title : "+ eElement.getElementsByTagName("Title").item(0).getTextContent());
                    System.out.println("Genre :"+ eElement.getElementsByTagName("Genre").item(0).getTextContent());
                    System.out.println("Price : "+ eElement.getElementsByTagName("Price").item(0).getTextContent());
                    System.out.println("PublishDate : "+eElement.getElementsByTagName("PublishDate").item(0).getTextContent());
                    System.out.println("Descripcion : "+eElement.getElementsByTagName("Description").item(0).getTextContent());
                }
            }
            } catch (Exception e) {
                //Captura cualquier excepción que ocurra durante la ejecución del bloque try, 
                //como errores al leer el archivo XML o problemas al analizar el contenido.
                e.printStackTrace(); //Imprime el stack trace para depurar el error.
                System.err.println(e); //Imprime el error en la consola.
        }
        
    }
}

