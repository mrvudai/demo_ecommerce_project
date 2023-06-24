package daipv.controller;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class PdfController {

    @GetMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<ByteArrayResource> exportPdf() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            // Đường dẫn đến font chứa các ký tự tiếng Việt
            String fontPath = "fonts/DejaVuSans.ttf";
            ClassPathResource resource = new ClassPathResource(fontPath);
            BaseFont baseFont = BaseFont.createFont(resource.getURL().toString(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            document.open();
            // Thiết lập font cho văn bản
            Paragraph paragraph = new Paragraph("+ tên: Nguyễn Văn A\n" +
                    "+ năm sinh: 1990\n" +
                    "* quê quán: Hà Nội\n" +
                    "* căn cước: 0123456789", new com.itextpdf.text.Font(baseFont));
            document.add(paragraph);

            document.close();

            byte[] bytes = outputStream.toByteArray();
            ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=export.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .contentLength(bytes.length)
                    .body(byteArrayResource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
