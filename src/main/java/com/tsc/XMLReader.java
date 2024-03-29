package com.tsc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

class XMLReader implements Reader {

    private LinkedHashMap<String, Shape> shapeMap;
    private List<Shape> shapesList;

//    XMLReader() {
//        shapeMap = null;
//        shapesList = null;
//    }

    public Map<String, Shape> getShapesMap() {
        return shapeMap;
    }

    public List<Shape> getShapesList() {
        return shapesList;
    }

    private String getValue(NodeList fields, int index) {
        NodeList list = fields.item(index).getChildNodes();
        if (list.getLength() > 0) {
            return list.item(0).getNodeValue();
        } else {
            return "";
        }
    }

    private HashMap<String, Shape> getDataNodes(NodeList nodeList, HashMap<String, Shape> allShapes) {
        shapeMap = new LinkedHashMap<>();
        shapesList = new LinkedList<>();
        String out = "";
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element shape = (Element) nodeList.item(i);
                String shapeType = shape.getNodeName();
                switch (shapeType) {
                    case "Point": {
                        String id = shape.getAttribute("id");
                        int x = 0, y = 0;
                        NodeList params = shape.getChildNodes();
                        for (int j = 0; j < params.getLength(); j++) {
                            if (params.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element parameter = (Element) params.item(j);
                                switch (parameter.getNodeName()) {
                                    case "x": {
                                        x = Integer.valueOf(parameter.getTextContent());
                                        break;
                                    }
                                    case "Y": {
                                        y = Integer.valueOf(parameter.getTextContent());
                                        break;
                                    }
                                }
                            }
                        }
                        Point p = new Point(id, x, y);
                        allShapes.put(id, p);
                        break;
                    }
                    case "Square": {
                        break;
                    }
                    case "Rectangle": {
                        break;
                    }
                }
            }
        }
        return allShapes;
    }

    public void readFile(File file) {
        //https://stackoverflow.com/questions/22236822/how-do-i-extract-data-from-nested-xml-using-java-dom
        try {
//            Desktop desktop = null;
//            if (Desktop.isDesktopSupported()) {
//                desktop = Desktop.getDesktop();
//            }
//            desktop.open(new File(path));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);//результат
            document.getDocumentElement().normalize();

            Element root = document.getDocumentElement();
            NodeList rootChilds = root.getChildNodes();
            for (int i = 0; i < rootChilds.getLength(); i++) {
                String s = rootChilds.item(i).getNodeName();
                System.out.println(s);
            }

        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            System.out.println(e.getMessage());
        }
    }


}
