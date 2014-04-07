package org.ms.explorer.bo.file;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.ms.explorer.bo.ElementBO;
import org.ms.explorer.element.Artikel;
import org.ms.explorer.element.Element;
import org.ms.explorer.element.Entity;
import org.ms.explorer.element.Transmitter;
import org.ms.explorer.registry.Registry;
import org.ms.explorer.type.GenusType;
import org.ms.explorer.type.KasusType;
import org.ms.explorer.type.NumerusType;


public class ElementFileBO implements ElementBO {

    public static final String FILE_PATH = "C:\\Users\\marks\\drive\\dev\\workspaces\\jdev\\12.1.2\\Model\\data\\";
    public static final String FILE_NAME = FILE_PATH + "iknow";
    public static final Charset ENCODING = StandardCharsets.UTF_8;
    public static final String DELIMITER = ";";
    public static final String FORMAT_DATE = "yyyy.MM.dd HH:mm:ss";

    private Registry registry = Registry.instance();

    public Element updateElement(Element element) throws Exception {

        String line;
        String filename;
        ElementType elementType = ElementType.getElementType(element);

        line = toEntry(element);
        filename = getFileName(elementType);

        //registry.addEntry(element.getId(), filename);
        //writeFile(line, filename);
        addToFile(line, filename);        

        return element;
    }

    protected List<Element> findAllElement(Element element, int depth) throws Exception {

        //String filename = registry.getEntry(element.getId());
        //List<String> lines = readFile(filename);
        //String line = findEntry(element.getId(), lines);

        // TO IMPLEMENT Logic.
        List<Element> elements = new ArrayList<Element>();
        //elements.add(toElement(line, getElementType(filename)));
        return elements;
        //return toElement(line, getElementType(filename));
    }

    protected Element findElement(Element element, int depth) throws Exception {

        /* String filename = registry.getEntry(element.getId());
        List<String> lines = readFile(filename);
        String line = findEntry(element.getId(), lines);

        return toElement(line, getElementType(filename)); */ 
                  return null;
    }

    private List<Transmitter> findTransmitters(List<String> ids) throws IOException {

        List<Transmitter> transmitters = null;

        if (ids != null) {
            String filename;
            List<String> lines;
            String line;

            for (String id : ids) {
                filename = null;
                lines = readFile(filename);
                line = findEntry(id, lines);
                transmitters.add(toTransmitter(line));
            }
        }

        return transmitters;
    }

    private ElementType getElementType(String filename) {

        ElementType elementType = null;

        if (filename.endsWith(ElementType.ENTITY.getSuffix())) {
            elementType = ElementType.ENTITY;
        }
        else if (filename.endsWith(ElementType.TRANSMITTER.getSuffix())) {
            elementType = ElementType.TRANSMITTER;
        }

        return elementType;
    }

    protected List<String> readFile(String filename) throws IOException {

        List<String> list = new ArrayList<>();

        Path path = Paths.get(filename);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        try (Scanner scanner =  new Scanner(path, ENCODING.name())){
            while (scanner.hasNextLine()){
                list.add(scanner.nextLine());
            }
        }

        return list;
    }
    
    public void addToFile(String line, String filename) throws IOException {
        List<String> lines = readFile(filename);
        lines.add(line);
        writeFile(lines, filename);
    }

    public void writeFile(String line, String filename) throws IOException {
        System.out.println("line : " + line + "\nfilename : " + filename);
        List<String> lines = new ArrayList<>();
        lines.add(line);
        writeFile(lines, filename);
    }

    public void writeFile(List<String> lines, String filename) throws IOException {
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
            for(String line : lines){
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private String toEntry(Element element) {

        String line = null;

        if (element.getId() == null) {
            element.setId(element.createUUID());
        }
        if (element instanceof Entity) {
            line = toEntityEntry((Entity) element);
        }
        else if (element instanceof Transmitter) {
            line = toTransmitterEntry((Transmitter) element);
        }

        return line;
    }

    private String toEntityEntry(Entity entity) {

        StringBuilder line;

        line = new StringBuilder();
        appendToken(line, entity.getId(), true);
        appendToken(line, getFormattedDate(entity.getObserved()), true);
        appendToken(line, entity.getName(), true);
        appendToken(line, entity.getArtikel().getKasus().getValue(), true);
        appendToken(line, entity.getArtikel().getGenus().getValue(), true);
        appendToken(line, entity.getArtikel().getNumerus().getValue(), true);
        appendToken(line, getTransmitterIdList(entity.getTransmitters()), false);

        return line.toString();
    }

    private String toTransmitterEntry(Transmitter transmitter) {

        StringBuilder line;

        line = new StringBuilder();
        appendToken(line, transmitter.getId(), true);
        appendToken(line, getFormattedDate(transmitter.getObserved()), true);
        appendToken(line, transmitter.getWeight(), true);
        appendToken(line, transmitter.getQuantity().getValue(), true);
        appendToken(line, transmitter.getRelationType().getValue(), true);
        appendToken(line, (transmitter.getFrom() != null ? transmitter.getFrom().getId() : null), true);
        appendToken(line, (transmitter.getTo() != null ? transmitter.getTo().getId() : null), false);

        return line.toString();
    }

    private Element toElement(String line, ElementType elementType) throws IOException {

        Element element = null;

        if (elementType.equals(ElementType.ENTITY)) {
            Entity entity = toEntity(line);
            List<Transmitter> transmitters = findTransmitters(entity.getTransmitterIds());
            if (transmitters != null) {
                entity.addTransmitter(transmitters);
            }
            element = entity;
        }
        else if (elementType.equals(ElementType.TRANSMITTER)) {
            element = toTransmitter(line);
        }

        return element;
    }

    private Entity toEntity(String line) {
        Entity entity = new Entity();
        Scanner scanner = getScanner(line);

        entity.setId(scanner.next());
        entity.setObserved(toDate(scanner.next()));
        entity.setName(scanner.next());

        KasusType kasus = KasusType.getType(Integer.parseInt(scanner.next()));
        GenusType genus = GenusType.getType(Integer.parseInt(scanner.next()));
        NumerusType numerus = NumerusType.getType(Integer.parseInt(scanner.next()));

        Artikel artikel = new Artikel(kasus, genus, numerus);
        entity.setArtikel(artikel);
        while (scanner.hasNext()) {
            entity.addTransmitterId(scanner.next());
        }

        return entity;
    }

    private Transmitter toTransmitter(String line) {

        Transmitter transmitter = new Transmitter();
        Scanner scanner = getScanner(line);

        transmitter.setId(scanner.next());
        transmitter.setObserved(toDate(scanner.next()));
        transmitter.setWeight(Integer.parseInt(scanner.next()));
        transmitter.setQuantity(Transmitter.Quantity.getQuantity(Integer.parseInt(scanner.next())));
        transmitter.setRelationType(Transmitter.RelationType.getRelationType(Integer.parseInt(scanner.next())));
        scanner.next(); // transmitter.getFrom().setId();
        scanner.next(); // transmitter.getTo().setId();

        System.out.println("transmitter=" + transmitter);
        return transmitter;
    }

    private String findEntry(String id, List<String> lines) {
        for (String line : lines) {
            Scanner scanner = getScanner(line);
            scanner.useDelimiter(DELIMITER);
            String token = scanner.next();
            if (token.equals(id)) {
                return line;
            }
        }

        return null;
    }

    private String getTransmitterIdList(List<Transmitter> transmitters) {
        StringBuilder ids = new StringBuilder();

        if (transmitters != null) {
            for (Transmitter transmitter : transmitters) {
                appendToken(ids, transmitter.getId(), true);
            }
        }

        return ids.toString();
    }

    private void appendToken(StringBuilder line, Integer token, boolean addLimiter) {
        appendToken(line, String.valueOf(token), addLimiter);
    }

    private void appendToken(StringBuilder line, String token, boolean addLimiter) {
        line.append(token);
        if (addLimiter) {
            line.append(DELIMITER);
        }
    }

    private String getFileName(ElementType elementType) {

        StringBuilder filename = new StringBuilder();

        filename.append(FILE_NAME);
        switch (elementType) {
            case ENTITY:
                filename.append(ElementType.ENTITY.getSuffix());
                break;
            case TRANSMITTER:
                filename.append(ElementType.TRANSMITTER.getSuffix());
                break;
        }

        return filename.toString();
    }

    private String getFormattedDate(Date date) {
        return getDateFormat().format(date);
    }

    private Date toDate(String value) {

        Date date = null;
        try {
            date = getDateFormat().parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    private SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat(FORMAT_DATE);
    }

    private Scanner getScanner(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(DELIMITER);
        return scanner;
    }

    enum ElementType {

        ENTITY(".ike"), TRANSMITTER(".ikt");

        private String suffix;
        static List<ElementType> list;

        static {
            list = new ArrayList<>();

            list.add(ENTITY);
            list.add(TRANSMITTER);
        }

        ElementType(String suffix) {
            this.suffix = suffix;
        }

        public static ElementType getElementType(Element element) {

            ElementType elementType = null;

            if (element instanceof Entity) {
                elementType = ElementFileBO.ElementType.ENTITY;
            }
            else if (element instanceof Transmitter) {
                elementType = ElementFileBO.ElementType.TRANSMITTER;
            }

            return elementType;
        }

        public String getSuffix() {
            return suffix;
        }
    }
}
