package com.example.demo.report;

import com.example.demo.frontoffice.BookRepository;
import com.example.demo.frontoffice.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.report.ReportType.CSV;

@AllArgsConstructor
@Service
public class CSVReportService implements ReportService {

    private BookRepository bookRepository;
    @Override
    public String export() {
           createCSVReport();
           return "I am a CSV reporter.";
    }

    public void createCSVReport ()
    {
        List<Book> books = new ArrayList<>(bookRepository.findBookByQuantity(0));
        FileWriter fileWriter;

        try{
            fileWriter = new FileWriter("OutOfStockBooks.csv");
            fileWriter.append("\n");

            for(Book book: books)
            {
                fileWriter.append(book.getAuthor());
                fileWriter.append(",");
                fileWriter.append(book.getTitle());
                fileWriter.append(",");
                fileWriter.append((char) book.getPrice());
                fileWriter.append(",");
                fileWriter.append("\n");
            }
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ReportType getType() {
        return CSV;
    }
}
