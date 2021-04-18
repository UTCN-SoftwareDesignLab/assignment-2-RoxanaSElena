package com.example.demo.report;

import com.example.demo.frontoffice.BookRepository;
import com.example.demo.frontoffice.model.Book;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.report.ReportType.PDF;
import static org.apache.pdfbox.pdmodel.font.PDType1Font.TIMES_ROMAN;

@Service
@AllArgsConstructor
public class PdfReportService implements ReportService {

    private BookRepository bookRepository;

    public String export() {

        List<Book> books = new ArrayList<>(bookRepository.findBookByQuantity(0));
        String filename = "OutOfStockBooks.pdf";
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        try{
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(TIMES_ROMAN, 14);
            contentStream.setLeading(14.5f);
            contentStream.newLineAtOffset(50,700);
            contentStream.showText("Out of stock books:");
            contentStream.newLine();
            for (Book book: books)
            {
                contentStream.showText("Title=" + book.getTitle());
                contentStream.newLine();
                contentStream.showText("Author="+ book.getAuthor());
                contentStream.newLine();
                contentStream.showText("Price="+book.getPrice());
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.close();
            document.save(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return "Something not ok";
        }
        return filename;
    }
    @Override
    public ReportType getType() {
        return PDF;
    }
}
