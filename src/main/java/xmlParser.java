import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;


import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.*;
import java.util.*;

public class xmlParser {
    final static File directory = new File("./src/main/resources/inputFolder");
    final static File outPutDir = new File(".\\src\\main\\resources\\outputFolder\\");


    public static void main(String[] args) throws Exception {


        IOFileFilter files = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(".xml"));
        final FileAlterationObserver fao = new FileAlterationObserver(directory, files);
        fao.addListener(new FileListenerImpl());
        final FileAlterationMonitor monitor = new FileAlterationMonitor();
        monitor.addObserver(fao);
        System.out.println("Starting monitor for xml files. Ctrl + C to stop");
        monitor.start();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            @Override
            public void run() {
                try {


                    System.out.println("Stopping monitor.");
                    monitor.stop();
                } catch (Exception ignored) {
                }
            }
        }));

    }


    static List<Product> parseXML(String fileName) {
        List<Product> products = new ArrayList<>();
        Order order = null;
        Product product = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();

                    switch (startElement.getName().getLocalPart().toLowerCase()) {

                        case "order":
                            order = new Order();

                            Attribute idAttr = startElement.getAttributeByName(new QName("ID"));
                            if (idAttr != null) {
                                order.setOrderId(Integer.parseInt(idAttr.getValue()));
                            }
                            break;

                        case "product":
                            product = new Product();
                            break;
                        case "description":
                            xmlEvent = xmlEventReader.nextEvent();
                            product.setDescription(xmlEvent.asCharacters().getData());
                            break;
                        case "gtin":
                            xmlEvent = xmlEventReader.nextEvent();
                            product.setGtin(Long.parseLong(xmlEvent.asCharacters().getData()));
                            break;
                        case "price":
                            xmlEvent = xmlEventReader.nextEvent();
                            product.setPrice(Double.parseDouble(xmlEvent.asCharacters().getData()));
                            break;

                        case "supplier":
                            xmlEvent = xmlEventReader.nextEvent();
                            product.setSupplier(xmlEvent.asCharacters().getData());
                            break;

                        default:
                            break;
                    }
                }

                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals("product")) {
                        product.setOrdeId(order.getOrderId());
                        products.add(product);
                    }
                }
            }


        } catch (FileNotFoundException | XMLStreamException | NullPointerException e) {
            e.printStackTrace();
        }
        return products;
    }

    static void writeXml(List<Product> products, String orderNumber) {
        List<List<Product>> myList = sortedList(products);
        for (List<Product> list : myList) {
            for (Product product : list) {

                try {

                    XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
                    XMLStreamWriter xMLStreamWriter = new IndentingXMLStreamWriter
                            (xMLOutputFactory.createXMLStreamWriter(new FileOutputStream(outPutDir + "/" + product.getSupplier() + orderNumber + ".xml")));

                    xMLStreamWriter.writeStartDocument("UTF-8", "1.0");
                    xMLStreamWriter.writeStartElement("products");
                    for (Product product1 : list) {

                        xMLStreamWriter.writeStartElement("product");

                        xMLStreamWriter.writeStartElement("description");
                        xMLStreamWriter.writeCharacters(product1.getDescription());
                        xMLStreamWriter.writeEndElement();

                        xMLStreamWriter.writeStartElement("gtin");
                        xMLStreamWriter.writeCharacters(String.valueOf(product1.getGtin()));
                        xMLStreamWriter.writeEndElement();

                        xMLStreamWriter.writeStartElement("price");
                        xMLStreamWriter.writeAttribute("currency", "USD");
                        xMLStreamWriter.writeCharacters(String.valueOf(product1.getPrice()));
                        xMLStreamWriter.writeEndElement();

                        xMLStreamWriter.writeStartElement("orderId");
                        xMLStreamWriter.writeCharacters(String.valueOf(product1.getOrdeId()));
                        xMLStreamWriter.writeEndElement();

                        xMLStreamWriter.writeEndElement();

                    }

                    xMLStreamWriter.writeEndDocument();

                    xMLStreamWriter.flush();
                    xMLStreamWriter.close();

                } catch (XMLStreamException | IOException e) {
                    e.printStackTrace();
                }
            }

        }
        System.out.println("Files were created in outputFolder!");

    }

    private static List<List<Product>> sortedList(List<Product> productList) {
        List<Product> sony = new ArrayList<>();
        List<Product> apple = new ArrayList<>();
        List<Product> panasonic = new ArrayList<>();
        List<List<Product>> sorted = new ArrayList<>();
        List<Product> newSupplier = new ArrayList<>();
        for (Product product : productList) {

            switch (product.getSupplier().toLowerCase()) {

                case "sony":
                    sony.add(product);
                    break;
                case "apple":
                    apple.add(product);
                    break;
                case "panasonic":
                    panasonic.add(product);
                default:
                    newSupplier = new ArrayList<>();
                    newSupplier.add(product);
                    break;
            }

            sorted.add(sony);
            sorted.add(apple);
            sorted.add(panasonic);
            sorted.add(newSupplier);


        }

        return sorted;

    }
}





