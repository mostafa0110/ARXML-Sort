import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;
public class main {
    public static void main(String[] args) {
        try{
            String fName = args[0];
            if(!fName.endsWith(".arxml")){
                throw new NotValidExtension ("NotVaildAutosarFileException");
            }
            File file = new File(fName);
            FileInputStream input = new FileInputStream(file);
            int x;
            StringBuilder stringBuilder= new StringBuilder();
            if(input.read()==-1){
                throw new AutosarFileNotFoundException ("EmptyAutosarFileException");
            }
            while ((x=input.read())!=-1){
                stringBuilder.append((char) x);
            }
            String Data = stringBuilder.toString();
            Scanner scanner= new Scanner(Data);
            ArrayList<Container> Containers= new ArrayList<>();
            while (scanner.hasNextLine()){
                String line =scanner.nextLine();
                if(line.contains("<CONTAINER")){
                    String ID = line.substring(line.indexOf("UUID="),line.indexOf(">"));
                    String nShort = scanner.nextLine();
                    String Short =nShort.substring(nShort.indexOf(">")+1,nShort.indexOf("</"));
                    String nLong = scanner.nextLine();
                    String Long =nLong.substring(nLong.indexOf(">")+1,nLong.indexOf("</"));
                    Container container= new Container();
                    container.SetID(ID);
                    container.SetShort(Short);
                    container.SetLong(Long);
                    Containers.add(container);
                }
            }
            Collections.sort(Containers);
            String outN = fName.substring(0,fName.indexOf("."))+ "_mod.arxml";
            FileOutputStream outputStream =new FileOutputStream(outN);
            outputStream.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n".getBytes());
            outputStream.write("<AUTOSAR>\n".getBytes());
            for(int i=0; i< Containers.size();i++) {
                outputStream.write(Containers.get(i).toString().getBytes());
            }
            outputStream.write("</AUTOSAR>\n".getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NotValidExtension e) {
            throw new RuntimeException(e);
        } catch (AutosarFileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}