import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOMParseEscrituraBOOK {

  public static void main(String argv[]) {
    try {
        //**** 1. Se crea el documento para ello:
        //Se crea una instancia de DocumentBuilderFactory, que es la fábrica para construir objetos DocumentBuilder. 
        //Este objeto permitirá construir un documento XML en memoria.
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

        //Se obtiene un DocumentBuilder a partir de la fábrica. Este se utiliza para crear un nuevo documento XML.
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        //Se crea un nuevo documento XML vacío en memoria. 
        //Este documento será la estructura que se va a escribir en un archivo XML.
        Document doc = docBuilder.newDocument();

        //**** 2. Se crea el elemento ROOT ó RAIZ para ello:
        //Se crea un nuevo elemento llamado rootElement (le puedes llamar como quieras), que será el elemento raíz del XML.
        Element rootElement = doc.createElement("Catalogo");

        //Se agrega el elemento root como el hijo principal del documento, convirtiéndose en el nodo raíz del XML.
        doc.appendChild(rootElement);

        //**** 3. Se crea un hijo del nodo root ó raiz para ello:
        //Se crea un nuevo elemento llamado libro.
        Element libro = doc.createElement("Libro");
        //Se añade el libro como hijo del nodo raíz (rootElement).
        rootElement.appendChild(libro);
        //**** **** 3.1 Se pueden crear atributos asociados al elemento libro, para ello:
        //Se crea un atributo llamado id.
        Attr attr = doc.createAttribute("id");
        //Se asigna un valor al atributo id.
        attr.setValue("bk101");
        //Se asocia el atributo id con el nodo libro. 
        //Esto significa que el elemento libro tendrá un atributo id con el valor "valor del atributo".
        libro.setAttributeNode(attr);

        //NOTA: Aqui se podrían crear más atributos para el elemento libro de la misma forma 
        attr = doc.createAttribute("isbn");
        attr.setValue("01");
        libro.setAttributeNode(attr);

        //**** **** 3.2 Se crean hijos del elemento libro, para ello:
        Element autor=doc.createElement("Autor");
        autor.setTextContent("Garghentini, Davide");

        Element titulo=doc.createElement("Titulo");
        titulo.setTextContent("XML Developer's Guide");

        Element genero=doc.createElement("Genero");
        genero.setTextContent("Computer");

        Element precio=doc.createElement("Precio");
        precio.setTextContent("44.95");

        Element fechapublicacion=doc.createElement("FechaPublicacion");
        fechapublicacion.setTextContent("2000-10-01");

        Element descripcion=doc.createElement("Descripcion");
        descripcion.setTextContent("An in-depth look at creating applications with XML.");
        
        //Se agregan los elementos como hijos del elemento libro.
        libro.appendChild(autor);
        libro.appendChild(titulo);
        libro.appendChild(genero);
        libro.appendChild(precio);
        libro.appendChild(fechapublicacion);
        libro.appendChild(descripcion);

        //**** 4. Se crea otro hijo del nodo root ó raiz para ello:
        //Se crea un nuevo elemento llamado libro2.
        Element libro2 = doc.createElement("Libro");
        //Se añade el libro2 como hijo del nodo raíz (root).
        rootElement.appendChild(libro2);
        //**** **** 4.1 Se pueden crear atributos asociados al elemento libro2, para ello:
        // Se crea un atributo llamado id.
        Attr attr2 = doc.createAttribute("id");
        //Se asigna un valor al atributo id.
        attr2.setValue("bk102");
        //Se asocia el atributo id con el nodo libro2. 
        //Esto significa que el elemento libro2 tendrá un atributo id con el valor "valor del atributo".
        libro2.setAttributeNode(attr2);

        //NOTA: Aqui se podrían crear más atributos para el elemento libro2 de la misma forma 
        attr2 = doc.createAttribute("isbn");
        attr2.setValue("02");
        libro2.setAttributeNode(attr2);

        //**** **** 4.2 Se crean hijos del elemento2, para ello
        Element autor2=doc.createElement("Autor");
        autor2.setTextContent("Garcia, Debra");

        Element titulo2=doc.createElement("Titulo");
        titulo2.setTextContent("Midnight Rain");

        Element genero2=doc.createElement("Genero");
        genero2.setTextContent("Fantasy");

        Element precio2=doc.createElement("Precio");
        precio2.setTextContent("5.95");

        Element fechapublicacion2=doc.createElement("FechaPublicacion");
        fechapublicacion2.setTextContent("2000-12-16");

        Element descripcion2=doc.createElement("Descripcion");
        descripcion2.setTextContent("A former architect battles corporate zombies,an evil sorceress, and her own childhood to become queen of the world.");

        //Se agregan los elementos como hijos del elemento libro2.
        libro2.appendChild(autor2);
        libro2.appendChild(titulo2);
        libro2.appendChild(genero2);
        libro2.appendChild(precio2);
        libro2.appendChild(fechapublicacion2);
        libro2.appendChild(descripcion2);

        //**** 5. Se escribe el contenido del XML en un archivo, para ello:
        //Se crea un objeto factory para construir objetos Transformer, 
        //que se encargan de transformar el contenido DOM en un formato XML legible.
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        //Se obtiene un Transformer que se encargará de transformar el documento DOM en un archivo XML.
        Transformer transformer = transformerFactory.newTransformer();
        //La siguiente línea se utiliza para configurar el formato de salida de un documento XML cuando se utiliza un transformador
        //esta línea establece que la salida XML debe estar "indentada" (con sangría), lo que facilita la lectura del XML generado.
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //Se establece la fuente de datos, que en este caso es el documento XML en memoria (doc).
        DOMSource source = new DOMSource(doc);

        //Define el archivo de salida donde se escribirá el XML. 
        //Aquí, el archivo se guardará en la carpeta ficheros con el nombre libros2.xml.
        StreamResult result = new StreamResult(new File("libros2.xml"));

        //Se realiza la transformación del documento DOM a XML y se guarda en el archivo especificado.
        transformer.transform(source, result);
        System.out.println("Fcihero XML creado con éxito");

    } catch (ParserConfigurationException pce) { //Captura los errores relacionados con la configuración del parser DOM.
        pce.printStackTrace();
    } catch (TransformerException tfe) { //Captura los errores que puedan ocurrir durante la transformación del documento a formato XML.
        tfe.printStackTrace();
    }
  }
}

