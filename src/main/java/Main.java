import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String directoryName;
        File dir;
        long size = 0;
        System.out.println("Подсчет размера каталога или файла");
        for (; ; ) {
            System.out.println("Укажите путь к каталогу или файлу");
            directoryName = reader.readLine();
            dir = new File(directoryName);
            if (!dir.isDirectory() && !dir.isFile()) {
                System.out.println("Задан неправильный путь. Правильно, например, C:\\Program Files");
                continue;
            }
            size = getDirSize(dir);
            printSize(size);
        }
    }

    private static long getDirSize(File dir) {
        long size = 0;
        if (dir.isFile()) {
            size = dir.length();
        } else {
            File[] subFiles = dir.listFiles();
            for (File file : subFiles) {
                if (file.isFile()) {
                    size += file.length();
                } else {
                    size += getDirSize(file);
                }
            }
        }
        return size;
    }

    private static void printSize(Long size)
    {
        long biteInKbite = 1024;
        long biteInMbite = 1048576;
        long biteInGbite = 1073741824;

        if(size < biteInKbite) {
            System.out.println("Размер: " + size + "байт");
        }
        else if(size < biteInMbite){
            System.out.printf("Размер: %.3f килобайт%n", (double)size/biteInKbite);
        }
        else if(size < biteInGbite){
            System.out.printf("Размер: %.3f мегабайт%n", (double)size/biteInMbite);
        }else{
            System.out.printf("Размер: %.3f гигабайт%n", (double)size/biteInGbite);
        }
    }
}
