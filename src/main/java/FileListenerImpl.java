import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.List;

public class FileListenerImpl implements FileAlterationListener {
    @Override
    public void onStart(FileAlterationObserver observer) {
        System.out.println("Looking for xml file in folder: " + observer.getDirectory().getAbsolutePath());
    }

    @Override
    public void onDirectoryCreate(File directory) {

    }

    @Override
    public void onDirectoryChange(File directory) {

    }

    @Override
    public void onDirectoryDelete(File directory) {

    }

    @Override
    public void onFileCreate(final File file) {
        System.out.println(file.getAbsoluteFile() + " was added.");
        String fileName = file.getAbsolutePath();
        String str = file.getName();
        String orderNumber= str.replaceAll("[^0-9]", "");
        List<Product> products = xmlParser.parseXML(fileName);

        xmlParser.writeXml(products, orderNumber);


    }

    @Override
    public void onFileChange(File file) {
        System.out.println(file.getAbsoluteFile() + " was modified.");

    }

    @Override
    public void onFileDelete(File file) {
        System.out.println(file.getAbsoluteFile() + " was deleted.");

    }

    @Override
    public void onStop(FileAlterationObserver observer) {

    }
}
