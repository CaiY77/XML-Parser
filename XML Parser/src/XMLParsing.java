import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class XMLParsing {
    private static Document document;

    static void getFile(File XMLfile){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            /*
            * Change/Edit the file path below to reference different XML files or target different filepaths
            */
            document = builder.parse(XMLfile);
            document.getDocumentElement().normalize();
            
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
    static void generateOutputFile(String fileName){
        try {
            /*
            * Change/Edit the output filename below to generate different text files
            */
            File myFile = new File(fileName + ".txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                myFile.delete();
                System.out.println("File already exists. Deleting");
                myFile.createNewFile();
                System.out.println("File recreated: " + myFile.getName());
            }
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

    /*
     *
     * -----------------------------------------------
     * Code beyond this point do not need to be altered
     * -----------------------------------------------
     * 
     * 
     */

    static void getBuRules(FileWriter myWriter){
        NodeList elementList = document.getElementsByTagName("business-rule");
        try {
            myWriter.write("\n-------------------------\n");
            myWriter.write("Number of Business Rules: " + elementList.getLength() + '\n');
            myWriter.write("-------------------------\n");

            for (int i = 0; i <elementList.getLength(); i++) {
                Node each = elementList.item(i);
                if(each.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) each;
                    //Pretty Print
                    int length = 60 - element.getAttribute("name").length();
                    String temp = "";
                    while (length > 0){
                        temp += " ";
                        length--;
                    }
                    myWriter.write( "\n" + "Name: " + element.getAttribute("name") + temp + "ID: " + element.getAttribute("id"));
                }
            }
            System.out.println("Business Rule details added to output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void getBuAction(FileWriter myWriter){
        NodeList elementList = document.getElementsByTagName("business-action");
        try {
            myWriter.write("\n-------------------------\n");
            myWriter.write("Number of Business Actions: " + elementList.getLength() + '\n');
            myWriter.write("-------------------------\n");

            for (int i = 0; i <elementList.getLength(); i++) {
                Node each = elementList.item(i);
                if(each.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) each;
                    //Pretty Print
                    myWriter.write( "\n" + "Name: " + element.getAttribute("id"));
                }
            }
            System.out.println("Business Actions details added to output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void getWorkerRules(FileWriter myWriter){
        NodeList elementList = document.getElementsByTagName("rule");
        try {
            myWriter.write("\n\n-------------------------\n");
            myWriter.write("Number of Worker Rules: " + elementList.getLength() + '\n');
            myWriter.write("-------------------------\n");
            for (int i = 0; i <elementList.getLength(); i++) {
                Node each = elementList.item(i);
                if(each.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) each;
                    //Pretty Print
                    int length = 60 - element.getAttribute("name").length();
                    String temp = "";
                    while (length > 0){
                        temp += " ";
                        length--;
                    }
                    myWriter.write( "\n" + "Name: " + element.getAttribute("name") + temp + "ID: " + element.getAttribute("id"));
                }
            }
            System.out.println("Worker Rule details added to output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void getBuRulesPlus(FileWriter myWriter){
        NodeList elementList = document.getElementsByTagName("business-rule");
        try {
            myWriter.write("\n\n-------------------------\n");
            myWriter.write("Number of Business Rules: " + elementList.getLength() + "\n");
            myWriter.write("-------------------------\n");
            for (int i = 0; i <elementList.getLength(); i++) {
                Node each = elementList.item(i);
                if(each.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) each;
    
                    //Pretty Print
                    int length = 60 - element.getAttribute("name").length();
                    String temp = "";
                    while (length > 0){
                        temp += " ";
                        length--;
                    }
                    myWriter.write("\n" + "Name: " + element.getAttribute("name") + temp + "ID: " + element.getAttribute("id") + "\n");
    
                    NodeList BRinfo = each.getChildNodes();
                    for (int j = 0; j < 4; j++) {
                        String elementName = BRinfo.item(j).getNodeName();
                        // Adding BU
                        if(elementName == "business-unit-id"){
                            Element elementBU = (Element) BRinfo.item(j);
                            myWriter.write("   " + "Business Unit: " + elementBU.getTextContent() + "\n");
                        }
    
                        //Adding AG
                        if(elementName == "agent-profile"){
                            if(BRinfo.item(j).getChildNodes().item(1).getNodeName() == "agent-group-id-ref"){
                                Element elementBU = (Element) BRinfo.item(j).getChildNodes().item(1).getChildNodes().item(1);
                                myWriter.write("   " + "Agent Group Ref: " + elementBU.getAttribute("var-id") + "\n");
                            } else if( BRinfo.item(j).getChildNodes().item(1).getNodeName() == "agent-group-id"){
                                Element elementBU = (Element) BRinfo.item(j).getChildNodes().item(1);
                                myWriter.write("   " + "Agent Group: " + elementBU.getTextContent() + "\n");
                            }
                        }
                    } // BR plus info
                }
            }
            System.out.println("Business Rule details extended added to output.txt");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    static void getChatSpecName(FileWriter myWriter){
        NodeList elementList = document.getElementsByTagName("chat-spec-name");
        try {
            myWriter.write("\n-------------------------------------------------------\n");
            myWriter.write("Chat Specification Name" + "                      " + "Occurrence");
            myWriter.write("\n-------------------------------------------------------\n");

            Map<String, Integer> Counter = new HashMap<String, Integer>();

            for (int i = 0; i <elementList.getLength(); i++) {
                Node each = elementList.item(i);
                if(each.getNodeType() == Node.ELEMENT_NODE){
                    // Check if var reference or just name
                    if(each.getChildNodes().item(1).getNodeName() == "string-variable-ref"){
                        Element element = (Element) each.getChildNodes().item(1);
                        String temp = "|var-id|: " + element.getAttribute("var-id");
                        if(Counter.containsKey(temp)){
                            Counter.put(temp, Counter.get(temp) + 1);
                        } else {
                            Counter.put(temp, 1);
                        }
                    } else {
                        Element element = (Element) each;
                        String key = element.getTextContent();
                        if(Counter.containsKey(key)){
                            Counter.put(key,Counter.get(key) + 1);
                        } else {
                            Counter.put(key, 1);
                        }
                    }
                } 
            }
            TreeMap<String, Integer> sorted = new TreeMap<>(Counter);
            for (String name : sorted.keySet()) {
                    String formatName = name.replaceAll("[\\n\\t ]", "");
                    int length = 50 - formatName.length();
                    String temp = "";
                    while (length > 0){
                        temp += " ";
                        length--;
                    }
                myWriter.write("\n" + formatName + temp + sorted.get(name).toString());
            }
            
            System.out.println("Chat Spec Name details extended added to output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void getCustomEvents(FileWriter myWriter){
        try {
            NodeList elementList = document.getElementsByTagName("custom-event-def");
            myWriter.write("\n\n-------------------------");
            myWriter.write('\n' + "Number of Custom Event: " + elementList.getLength() + '\n');
            myWriter.write("-------------------------\n");
            for (int i = 0; i <elementList.getLength(); i++) {
                Node each = elementList.item(i);
                if(each.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) each;
                    myWriter.write("\nName: " + element.getAttribute("name"));
                }
            }
            System.out.println("Custom Events details added to output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void getVars( FileWriter myWriter){
        String[] varTypes = {"number","string","string-list","object","boolean","map"};
        for(int a = 0; a < varTypes.length; a++){
            NodeList elementList = document.getElementsByTagName(varTypes[a] + "-variable");
            try {
                String[] unFiltered = new String[elementList.getLength() + 1];
                for (int i = 0; i <elementList.getLength(); i++) {
                    Node each = elementList.item(i);
                    NodeList eachRss = each.getChildNodes();
                    if(each.getNodeType() == Node.ELEMENT_NODE){
                        Element element = (Element) each;
                        Element rss = (Element) eachRss.item(1);

                        //Pretty Print
                        int length = 40 - element.getAttribute("name").length();
                        String space = "";
                        while (length > 0){
                            space += " ";
                            length--;
                        }
                        unFiltered[i] = "\nName: " + element.getAttribute("name") + space + rss.getAttribute("resource-id");
                    }
                }

                Set<String> filtered = new HashSet<String>();
                Collections.addAll( filtered, unFiltered);
                int size = filtered.size() - 1;
                myWriter.write("\n\n-------------------------");
                myWriter.write('\n' + "Number of " + varTypes[a] + "-variables: " + size + '\n');
                myWriter.write("-------------------------\n");

                for (String varInfo : filtered){
                    if(varInfo != null){
                        myWriter.write(varInfo);
                    }
                }
                System.out.println("All " + varTypes[a].toUpperCase() + " variable details added to output.txt");
            } catch (IOException e) {
                e.printStackTrace();
            } 
        }
    }
    static void getVars( FileWriter myWriter, String varType){
        String temp = varType.toLowerCase();
        NodeList elementList = document.getElementsByTagName(temp + "-variable");

        try {
            String[] unFiltered = new String[elementList.getLength() + 1];

            for (int i = 0; i <elementList.getLength(); i++) {
                Node each = elementList.item(i);
                NodeList eachRss = each.getChildNodes();
                if(each.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) each;
                    Element rss = (Element) eachRss.item(1);

                    //Pretty Print
                    int length = 40 - element.getAttribute("name").length();
                    String space = "";
                    while (length > 0){
                        space += " ";
                        length--;
                    }
                    unFiltered[i] = "\nName: " + element.getAttribute("name") + space + rss.getAttribute("resource-id");
                }
            }
            
            Set<String> filtered = new HashSet<String>();
            Collections.addAll( filtered, unFiltered);
            int size = filtered.size() - 1;
            myWriter.write("\n\n-------------------------");
            myWriter.write('\n' + "Number of " + temp + "-variables: " + size + '\n');
            myWriter.write("-------------------------\n");
            for (String varInfo : filtered){
                if(varInfo != null){
                    myWriter.write(varInfo);
                }
            }
            System.out.println(varType.toUpperCase() + " Variable details added to output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void getCG(FileWriter myWriter){
        NodeList elementList = document.getElementsByTagName("content-group");
        try {
            myWriter.write("\n\n-------------------------");
            myWriter.write('\n' + "Number of Content Groups: " + elementList.getLength() + '\n');
            myWriter.write("-------------------------\n");

            for (int i = 0; i <elementList.getLength(); i++) {
                Node each = elementList.item(i);
                if(each.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) each;
                    myWriter.write("\nName: " + element.getAttribute("id"));
                }
            }
            System.out.println("Content groups added to output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void getJS(FileWriter myWriter){
        NodeList elementList = document.getElementsByTagName("js-function");
        try {
            myWriter.write("\n\n-------------------------");
            myWriter.write('\n' + "Number of js-functions: " + elementList.getLength() + '\n');
            myWriter.write("-------------------------\n");
            for (int i = 0; i <elementList.getLength(); i++) {
                Node each = elementList.item(i);
                if(each.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) each;
                    myWriter.write("\nName: " + element.getAttribute("name"));
                }
            }
            System.out.println("Customized JavaScrips added to output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void getPreRichMedia(FileWriter myWriter){
        NodeList elementList = document.getElementsByTagName("rich-prechat-survey-id");
        try {
            myWriter.write("\n-------------------------------------------------------\n");
            myWriter.write("RichMedia Prechat" + "                      " + "Occurrence");
            myWriter.write("\n-------------------------------------------------------\n");

            Map<String, Integer> Counter = new HashMap<String, Integer>();

            for (int i = 0; i <elementList.getLength(); i++) {
                Node each = elementList.item(i);
                if(each.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) each.getChildNodes();
                    String key = element.getTextContent();
                    if(Counter.containsKey(key)){
                        Counter.put(key , Counter.get(key) + 1);
                    } else {
                        Counter.put(key, 1);
                    }
                } 
            }
            TreeMap<String, Integer> sorted = new TreeMap<>(Counter);
            for (String name : sorted.keySet()) {
                    String formatName = name.replaceAll("[\\n\\t ]", "");
                    int length = 44 - formatName.length();
                    String temp = "";
                    while (length > 0){
                        temp += " ";
                        length--;
                    }
                myWriter.write("\n" + formatName + temp + sorted.get(name).toString());
            }
            
            System.out.println("Rich Media prechat details extended added to output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}