package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alavu.fileupload_in_springboot_csv_to_db.entity.AccountDetails;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParserSettings;

import repository.AccountRepository;

@RestController
public class BankController {
    @Autowired
    AccountRepository service;
    
    @PostMapping("/upload")
    public String uploadData(@RequestParam("file") MultipartFile file) throws Exception {
        List<AccountDetails> accountList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        CsvParser parser = new CsvParser(settings);
        List<Record> parseAllRecords =  parser.parseAllRecords(inputStream);
        parseAllRecords.forEach(record->{
            AccountDetails account = new AccountDetails();
            account.setAccountNumber(Integer.parseInt(record.getString("account_number")));
            account.setFirstName(record.getString("first_name"));
            account.setLastName(record.getString("last_name"));
            account.setAccountType(record.getString("account_type"));
            account.setAdddress(record.getString("address"));
            account.setEmail(record.getString("email"));
            account.setZipcode(record.getString("zipcode"));
            accountList.add(account);
        });
        service.saveAll(accountList);
        return "Upload Successfull !!!";
    }
}
