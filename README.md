## Test Otomasyon Çalışması

### UI Test Otomasyonu
• Kullanıcı https://www.hepsiburada.com/ adresine gider.  
• Tüm Kategoriler -> Elektronik -> Tablet kategorisine gider.  
• Filtrelerden Marka->Apple, Ekran Boyutu-> 13,2 inç olacak şekilde seçer.  
• Sıralama filtresini kullanmadan çıkan sonuçlardan en yüksek fiyatlı ürüne tıklar  
• Açılan ürün detayı sayfasındaki Sepete ekle butonuna tıklar.  
• Ürünün sepete eklendiğini ve fiyatının ürün detay sayfasıyla aynı fiyat olduğunu doğrular.

## Test Startup Methods

### Running the Tests

1. **Using TestNG XML File**

   To run the project with predefined browser configurations:

   Use the testngChromeAndFireFox.xml file located under the test resources.  
   This will execute the tests as defined in the XML file.

2. **Start with Maven:**

   To start the tests using Maven, run the following command:
   ```bash
   mvn clean test
   ```
   In this case, the tests will run using the `testngChromeAndFireFox.xml` file. This XML file defines tests to be executed on both Chrome and Firefox browsers.

3. **Start with Feature File:**

   If you are using Cucumber, you can start the tests with a feature file. In this case, you will need to check the values defined inside the TestRunner.

4. **Remote Execution**  
   If you want to run tests in a remote environment, you must first start the Docker containers. Follow these steps:  
   Use the `docker-compose.yml` file to start the required services:
   ```bash
   docker-compose up -d
   ```

## Report

You can review the report at `test-report/html-reports/CaseReport.html` path after running the test.

## Contact Me

You can reach out to me via email at [oncecengizhan@gmail.com](mailto:oncecengizhan@gmail.com). I'd love to hear from you!
