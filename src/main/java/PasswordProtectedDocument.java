import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import org.apache.poi.hssf.record.crypto.Biff8EncryptionKey;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

public class PasswordProtectedDocument {

    // Application MIME Types
    static final String MSEXCEL_OLE2  = "application/vnd.ms-excel";
    static final String MSEXCEL_OOXML = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static final String MSWORD_OLE2   = "application/msword";
    static final String MSWORD_OOXML  = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    static final String PDF           = "application/pdf";

    // OLE2 format - 2003
    static final String ORIGINAL_EXCEL_OLE2 = "./src/main/resources/SampleExcel2003.xls";
    static final String ENCRYPTED_EXCEL_OLE2 = "./src/main/resources/SampleExcel2003Encrypted.xls";

    // static final String DAVID_ORIGINAL_EXCEL_OLE2 = "./src/main/resources/Book2.xls";
    // static final String DAVID_ENCRYPTED_EXCEL_OLE2 = "./src/main/resources/Book2Encrypted.xls";

    static final String ORIGINAL_WORD_OLE2 = "./src/main/resources/SampleWord2003.doc";
    static final String ENCRYPTED_WORD_OLE2 = "./src/main/resources/SampleWord2003Encrypted.doc";

    // OOXML format
    static final String ORIGINAL_EXCEL_OOXML = "./src/main/resources/SampleExcel.xlsx";
    static final String ENCRYPTED_EXCEL_OOXML = "./src/main/resources/SampleExcelEncrypted.xlsx";
    static final String ORIGINAL_WORD_OOXML = "./src/main/resources/SampleWord.docx";
    static final String ENCRYPTED_WORD_OOXML = "./src/main/resources/SampleWordEncrypted.docx";

    // PDF format
    static final String ORIGINAL_PDF = "./src/main/resources/SamplePDF.pdf";
    static final String ENCRYPTED_PDF = "./src/main/resources/SamplePDFEncrypted.pdf";

    static final String PASSWORD = "password";

    public static void main(final String... args) throws Exception {

        generateEncryptedDocument(ORIGINAL_EXCEL_OLE2, ENCRYPTED_EXCEL_OLE2, PASSWORD);
//        generateEncryptedDocument(DAVID_ORIGINAL_EXCEL_OLE2, DAVID_ENCRYPTED_EXCEL_OLE2, PASSWORD);
        generateEncryptedDocument(ORIGINAL_WORD_OLE2, ENCRYPTED_WORD_OLE2, PASSWORD);
        generateEncryptedDocument(ORIGINAL_EXCEL_OOXML, ENCRYPTED_EXCEL_OOXML, PASSWORD);
        generateEncryptedDocument(ORIGINAL_WORD_OOXML, ENCRYPTED_WORD_OOXML, PASSWORD);
        generateEncryptedDocument (ORIGINAL_PDF, ENCRYPTED_PDF, PASSWORD);
    }

    public static void generateEncryptedDocument (final String originalFile, final String encryptedFile, final String password) {

        Workbook workbook = null;
        FileOutputStream fileOut = null;
        OutputStream os = null;
        POIFSFileSystem fs = null;
        HWPFDocument wordDocument = null;
        PDDocument document = null;

        System.out.println ("Generating encrypted " + getFileTypeByProbeContentType(originalFile) + "...");

        try {

            if (MSEXCEL_OLE2.equals(getFileTypeByProbeContentType(originalFile))) {
                // EXCEL OLE2

                // Prepare for Encryption
                Biff8EncryptionKey.setCurrentUserPassword(password);

                workbook = WorkbookFactory.create(new File(originalFile));
                fileOut = new FileOutputStream(encryptedFile);
                ((HSSFWorkbook)workbook).writeProtectWorkbook(Biff8EncryptionKey.getCurrentUserPassword(), "");
                workbook.write(fileOut);
                workbook.close();

            } else if (MSWORD_OLE2.equals(getFileTypeByProbeContentType(originalFile))) {
                // WORD OLE2

                // Prepare for Encryption
                Biff8EncryptionKey.setCurrentUserPassword(password);

                wordDocument = new HWPFDocument(new POIFSFileSystem(new BufferedInputStream(new FileInputStream(originalFile))));
                wordDocument.write(new FileOutputStream(encryptedFile));
                wordDocument.close();

            } else if ((MSEXCEL_OOXML.equals(getFileTypeByProbeContentType(originalFile))) || 
                       (MSWORD_OOXML.equals(getFileTypeByProbeContentType(originalFile)))) {
                // EXCEL or WORD OOXML
                // Same process for EXCEL and WORD documents

                // Prepare
                fs = new POIFSFileSystem();
                EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
                Encryptor enc = info.getEncryptor();
                enc.confirmPassword(password);

                File inputFile = new File (originalFile);
                OPCPackage opc = OPCPackage.open(inputFile, PackageAccess.READ_WRITE);

                // Encrypt
                os = enc.getDataStream(fs);
                opc.save(os);
                os.close();

                // Save
                fileOut = new FileOutputStream(encryptedFile);
                fs.writeFilesystem(fileOut);
                fileOut.close();
                fs.close(); 

            } else if (PDF.equals(getFileTypeByProbeContentType(originalFile))) {
                // PDF

                //Loading an existing document
                File file = new File(originalFile);
                document = PDDocument.load(file);
        
                //Creating access permission object
                AccessPermission ap = new AccessPermission();         
        
                //Creating StandardProtectionPolicy object
                StandardProtectionPolicy spp = new StandardProtectionPolicy(password, password, ap);
        
                //Setting the length of the encryption key
                spp.setEncryptionKeyLength(256);
        
                //Setting the access permissions
                spp.setPermissions(ap);
        
                //Protecting the document
                document.protect(spp);
        
                //Saving the document
                document.save(encryptedFile);
                //Closing the document
                document.close();
            } 
            System.out.println ("Generating encrypted " + getFileTypeByProbeContentType(originalFile) + "...done");                       
        }
        catch (Exception ex) {
            System.out.println(ex.getClass() + " - " + ex.getMessage());
        }
        finally {
            try {
                if (workbook != null) workbook.close();
            } catch (IOException ex) {}
            try {
                if (fileOut != null) fileOut.close();
            } catch (IOException ex) {}
            try {
                if (os != null) os.close();
            } catch (IOException ex) {}
            try {
                if (fs != null) fs.close();
            } catch (IOException ex) {}
            try {
                if (document != null) document.close();
            } catch (IOException ex) {}
        }
    }

    public static String getFileTypeByProbeContentType(String originalFile){
        String fileType = "Undetermined";
        final File file = new File(originalFile);
        try{
            fileType = Files.probeContentType(file.toPath());
        }
        catch (IOException ioException){
            System.out.println("  - File type not detected for " + originalFile);
        }
        return fileType;
    }   
}